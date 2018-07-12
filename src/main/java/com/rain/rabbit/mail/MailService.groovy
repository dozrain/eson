package com.rain.rabbit.mail

import com.alibaba.fastjson.JSON
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

/**
 * Created by carl.shen on 2017/3/17.
 */
@Slf4j
@Service
class MailService {

    @Autowired
    MailConfig mailConfig
    @Autowired
    JavaMailSender javaMailSender

    private final static String default_charset = "UTF-8"

    public static enum EncryptionTypes {
        Default, TLS, SSL
    }


    private String mail_host = ""
    private int mail_port = 25
    private int encryptionType = MailService.EncryptionTypes.Default.ordinal()
    private boolean auth = false;
    private String mail_host_account = ""
    private String mail_host_password = ""
    private boolean isHtml = false

    public void sendEmailsByMq(Message message){
        if (message && message.msgtype == BusinessStatus.MESSAGE_TYPE_1.intValue()){
            Map emailMap = JSON.parseObject(message.jsondata,Map)
            String receiver = emailMap.get("receiver")
            String sub = emailMap.get("sub")
            String msg = emailMap.get("msg")
            List  c =(List) emailMap.get("attachments")
            boolean  isHtml = emailMap.get("isHtml")
            sendEmails(receiver,sub,msg,c,isHtml)
        }
    }

    void sendEmailByJavaMailSender(String receiverAddress, String subject, String body, List<Object> attachments){
        MimeMessage message = getJavaMailSender().createMimeMessage();
        //message.setText(sub, "utf-8");
        message.addFrom([new InternetAddress(mailConfig.sendAddress)] as Address[]);
        message.setRecipients(Message.RecipientType.TO, receiverAddress);
        message.setSubject(subject);
        // 将邮件中各个部分组合到一个"mixed"型的 MimeMultipart 对象
        MimeMultipart allPart = new MimeMultipart("mixed");
        if(attachments != null && !attachments.isEmpty()){
            MimeBodyPart textBody = new MimeBodyPart();
            textBody.setContent(body, "text/html;charset=utf-8");
            attachments.each { attach ->
                File file = attach instanceof File ? (File)attach : new File(attach)
                allPart.addBodyPart(createAttachment(file))
            }
            allPart.addBodyPart(textBody)
            message.setContent(allPart)
        }else{
            message.setContent(body, "text/html;charset=utf-8");
        }
        getJavaMailSender().send(message);
    }

    /**
     * 根据传入的文件路径创建附件并返回
     */
    public MimeBodyPart createAttachment(File file) throws Exception {
        MimeBodyPart attachmentPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(file);
        attachmentPart.setDataHandler(new DataHandler(fds));
        attachmentPart.setFileName(fds.getName());
        return attachmentPart;
    }

    /**
     *
     * @param receiverAddress 邮件接收者
     * @param sub 邮件主题
     * @param msg  邮件正文内容
     * @param attachments 附件
     * @param isHtml  是否是html
     * @throws Exception
     */
    public void sendEmails( String receiverAddress, String sub, String msg, Collection attachments,boolean isHtml)
            throws Exception {
        sendEmailByJavaMailSender(receiverAddress, sub, msg, attachments)
        return

        this.mail_host = mailConfig.smtpServer
        this.mail_port = mailConfig.smtpPort
        if (mailConfig.ssl){
            this.encryptionType = MailService.EncryptionTypes.SSL.ordinal()
        }else {
            this.encryptionType =   MailService.EncryptionTypes.Default.ordinal()
        }
        this.auth = mailConfig.auth
        this.mail_host_account = mailConfig.sendAddress
        this.mail_host_password = mailConfig.password
        this.isHtml = isHtml

        String[] address = receiverAddress.split(";")
        List recipients = new ArrayList()
        for (int i = 0; i < address.length; i++) {
            if (address[i].trim().length() > 0) {
                recipients.add(address[i])
            }
        }
        String senderAddress = mailConfig.sendAddress
        String senderName =mailConfig.displayName
        this.sendEmail(senderAddress, senderName, recipients, sub, msg, attachments)
    }

    /**
     * Send email to a list of recipients.
     *
     * @param senderAddress the sender email address
     * @param senderName    the sender name
     * @param recipients    a list of receipients email addresses
     * @param sub           the subject of the email
     * @param msg           the message content of the email
     * @param attachments   attachments list of the email
     */
    private void sendEmail(String senderAddress,
                          String senderName, List recipients, String sub, String msg, Collection attachments)
            throws SendFailedException {

        log.debug("mail subject: " + sub
                + " mail_port: " + this.mail_port
                + " encryptionType: " + this.encryptionType
                + " auth: " + this.auth
                + " mail_host_account: " + this.mail_host_account
                + " mail_host_password: " + this.mail_host_password)

        Transport transport = null

        try {

            Properties props = this.getProperties()

            Session session = this.getSession(props)

            MimeMessage message = new MimeMessage(session)

            if (this.getDefaultIsHtml()) {
                message.addHeader("Content-type", "text/html")
            } else {
                message.addHeader("Content-type", "text/plain")
            }

            message.setSubject(sub, default_charset);
            message.setFrom(new InternetAddress(senderAddress, senderName))

            for (Iterator it = recipients.iterator(); it.hasNext(); ) {
                String email = (String) it.next()
                message.addRecipients(Message.RecipientType.TO, email)
            }


            //正文
            MimeBodyPart contentPart = new MimeBodyPart()
            if (this.getDefaultIsHtml()) {
                contentPart.setContent("<meta http-equiv=Content-Type content=text/html; charset=" + default_charset + ">" + msg, "text/html;charset=" + default_charset)
            } else {
                contentPart.setText(msg, default_charset)
            }

            //图片
            /*MimeBodyPart img = new MimeBodyPart()
            //todo 这里需要指定邮件中图片的位置
            FileDataSource fdss =   new FileDataSource("d:/b-01.jpg")
            img.setDataHandler(new DataHandler(fdss))
            img.setHeader("Content-ID","<IMG1>")//这里的<IMG1>对应 邮件正文html模板中的src='cid:<IMG1>'

            MimeMultipart mp = new MimeMultipart("related")
            mp.addBodyPart(contentPart)
            mp.addBodyPart(img)
            */
            //附件
            if (attachments != null) {

                MimeBodyPart all = new MimeBodyPart()
                all.setContent(mp)
                MimeMultipart mm2 = new MimeMultipart()
                mm2.addBodyPart(all)
                MimeBodyPart attachPart
                for (Iterator it = attachments.iterator(); it.hasNext(); ) {
                    attachPart = new MimeBodyPart()
                    FileDataSource fds = new FileDataSource(it.next().toString().trim())
                    attachPart.setDataHandler(new DataHandler(fds))
                    if (fds.getName().indexOf('$') != -1) {
                        attachPart.setFileName(fds.getName().substring(fds.getName().indexOf('$') + 1, fds.getName().length()))
                    } else {
                        attachPart.setFileName(fds.getName())
                    }
                    mm2.addBodyPart(attachPart)
                }
                message.setContent(mm2)

            }else {
                //message.setContent(mp)
            }



            message.setSentDate(new Date())
            if (this.getDefaultEncryptionType() == EncryptionTypes.SSL.ordinal()) {
                Transport.send(message)
            } else {
                transport = session.getTransport("smtp")

                transport.connect(this.mail_host,
                        this.mail_port,
                        this.mail_host_account,
                        this.mail_host_password)

                transport.sendMessage(message, message.getAllRecipients())
            }

        } catch (Exception e) {
            log.error("send mail error", e)
            throw new SendFailedException(e.toString())
        } finally {
            if (transport != null) {
                try {
                    transport.close()
                } catch (Exception ex) {
                    throw  ex.toString()
                }
            }
        }
    }

    private Properties getProperties() {

        Properties props = System.getProperties()

        int defaultEncryptionType = this.getDefaultEncryptionType()

        if (defaultEncryptionType == EncryptionTypes.TLS.ordinal()) {
            props.put("mail.smtp.auth", String.valueOf(this.auth))
            props.put("mail.smtp.starttls.enable", "true")
        } else if (defaultEncryptionType == EncryptionTypes.SSL.ordinal()) {
            props.put("mail.smtp.host", this.mail_host)
            props.put("mail.smtp.socketFactory.port", this.mail_port)
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            props.put("mail.smtp.debug", "true")
            props.put("mail.smtp.auth", String.valueOf(this.auth))
            props.put("mail.smtp.port", this.mail_port)
        } else {
            props.put("mail.smtp.host", this.mail_host)
            props.put("mail.smtp.auth", String.valueOf(this.auth))
        }

        return props
    }

    private Session getSession(Properties props) {
        Session session = null

        if (this.getDefaultEncryptionType() == EncryptionTypes.TLS.ordinal()) {
            session = Session.getInstance(props);
        } else if (this.getDefaultEncryptionType() == EncryptionTypes.SSL.ordinal()) {
            session = Session.getInstance(props, new MyAuthenticator(this.mail_host_account, this.mail_host_password))
        } else {
            session = Session.getDefaultInstance(props, null)
        }

        return session
    }

    private boolean getDefaultIsHtml() {
        boolean rst = this.isHtml
        return rst
    }

    private class MyAuthenticator extends javax.mail.Authenticator {
        String user
        String password


         MyAuthenticator(String user, String password) {
            this.user = user
            this.password = password
        }

        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(this.user, this.password)
        }
    }

    /**
     * get default encryption type,
     * for 465, SSL
     * for 587, TLS
     *
     * @return
     */
    private int getDefaultEncryptionType() {
        int rst = this.encryptionType
        if (this.encryptionType == EncryptionTypes.Default.ordinal()) {
            if (this.mail_port == 465) {
                rst = EncryptionTypes.SSL.ordinal()
            } else if (this.mail_port == 587) {
                rst = EncryptionTypes.TLS.ordinal()
            }
        }

        return rst
    }

}
