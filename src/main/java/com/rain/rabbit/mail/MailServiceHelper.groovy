package com.rain.rabbit.mail

import com.alibaba.fastjson.JSONObject
import org.apache.velocity.Template
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.springframework.stereotype.Component

import javax.annotation.Resource

/**
 * Created by tpeng on 2017/5/10.
 */
@Component
class MailServiceHelper {

    @Resource
    MailService mailService

    String getTemplateContent(String templatePath, Map<String, Object> data){
        engine.init()
        Template template = engine.getTemplate(templatePath);
        VelocityContext context = new VelocityContext()
        data.each {k,v ->
            context.put(k, v)
        }
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString()
    }

    void sendEmailByJavaMailSender(String receiverAddress, String subject, String body, List<Object> attachments){
        mailService.sendEmailByJavaMailSender(receiverAddress, subject, body, attachments)
    }

    /**
     * 航司高管-发送员工邀请邮件
     * @param
     * @return
     * @author tpeng
     * @date 2017/5/13 14:45
     * @update
     */
    String sendUserQrcodeMail(String receiverAddress, String qrcode, String helpPhone){
        String json = getTemplateContent("templates/email/航司高管-邀请员工邮件.email", [
            qrcode : qrcode,
            helpPhone : helpPhone
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            //mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            mailService.sendEmailByJavaMailSender(receiverAddress, subject, emailBody, null)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 航司-发送高管邀请邮件
     * @param
     * @return
     * @author tpeng
     * @date 2017/5/13 14:45
     * @update
     */
    String sendZGUserQrcodeMail(String receiverAddress, String qrcode, String helpPhone){
        String json = getTemplateContent("templates/email/航司高管-邀请员工邮件.email", [
                qrcode : qrcode,
                helpPhone : helpPhone
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            //mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            mailService.sendEmailByJavaMailSender(receiverAddress, subject, emailBody, null)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 需方发起求援，提醒利顿人员
     * {
     subject : "$!{companyName} 发起求援",
     emailBody: "求援地点$!{cityName} 求援件号：$!{partNo} 求援数量$!{partNum}。"
     }
     * @return
     */
    String sendTempMessage01(String receiverAddress, String companyName, String cityName, String partNo, String partNum){
        String json = getTemplateContent("templates/email/01.email", [
                companyName : companyName,
                cityName : cityName,
                partNo : partNo,
                partNum : partNum
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 利顿-创建响应单-通知供方人员响应
     * {
     subject : "求援任务$!{askOrderNo} 响应提醒",
     emailBody: "有新的求援单，请您响应。 求援地点$!{cityName} 求援件号：$!{partNo} 求援数量$!{partNum}。"
     }
     * @return
     */
    String sendTempMessage02(String receiverAddress, String askOrderNo, String cityName, String partNo, String partNum){
        String json = getTemplateContent("templates/email/02.email", [
                askOrderNo : askOrderNo,
                cityName : cityName,
                partNo : partNo,
                partNum : partNum
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 利顿-创建响应单-通知供方人员响应
     * {
     subject : "求援任务$!{askOrderNo} 响应提醒",
     emailBody: "有新的求援单，请您响应。 求援地点$!{cityName} 求援件号：$!{partNo} 求援数量$!{partNum}。"
     }
     * @return
     */
    String sendTempMessage03(String receiverAddress, String zqydno, String companyName, String helpPhone){
        String json = getTemplateContent("templates/email/03.email", [
                zqydno : zqydno,
                companyName : companyName,
                helpPhone : helpPhone
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 利顿-需方可见-提醒需方创建求援单人员
     * {
     subject : "求援任务$!{askOrderNo}响应提醒-已有响应",
     emailBody: "您提交的求援需求已有响应, 求援单号:$!{askOrderNo} 件号$!{partNo} 响应地点：$!{respondAddr} 请您登录系统查看响应详情，如需帮助请致电 $!{helpPhone}。"
     }
     * @return
     */
    String sendTempMessage04(String receiverAddress, String askOrderNo, String partNo, String respondAddr, String helpPhone){
        String json = getTemplateContent("templates/email/04.email", [
                askOrderNo : askOrderNo,
                partNo : partNo,
                respondAddr : respondAddr,
                helpPhone : helpPhone
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 需方-选择供方-提醒供方人员
     * {
     subject : "求援任务$!{askOrderNo},需方已选择",
     emailBody: "您的响应，需方已选择，请尽快供货。求援单号:$!{askOrderNo} 件号$!{partNo}  需方地区：$!{supplyAddr} 请您登录系统查看响应详情，如需帮助请致电 $!{helpPhone}。"
     }
     * @return
     */
    String sendTempMessage06(String receiverAddress, String askOrderNo, String partNo, String supplyAddr, String helpPhone){
        String json = getTemplateContent("templates/email/06.email", [
                askOrderNo : askOrderNo,
                partNo : partNo,
                supplyAddr : supplyAddr,
                helpPhone : helpPhone
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 需方-选择需审核-提醒选择审核的母公司人员
     * {
     subject : "求援任务$!{askOrderNo},需要审核",
     emailBody: "您好，分公司$!{companyName}有求援需求需要您审核，请尽快处理, 求援单号:$!{askOrderNo} 请您登录系统查看响应详情，如需帮助请致电 $!{helpPhone}。"
     }
     * @return
     */
    String sendTempMessage07(String receiverAddress, String companyName, String askOrderNo, String helpPhone){
        String json = getTemplateContent("templates/email/07.email", [
                companyName : companyName,
                askOrderNo : askOrderNo,
                helpPhone : helpPhone
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 供方-拒绝供件-提醒需方创建求援单人员
     *{
     subject : "供方-拒绝供件-$!{askOrderNo}",
     emailBody: "您好，您的求援单：$!{askOrderNo}, 供方-拒绝供件。请您登录系统查看响应详情，如需帮助请致电 $!{helpPhone}。"
     }
     * @return
     */
    String sendTempMessage09(String receiverAddress, String askOrderNo, String helpPhone){
        String json = getTemplateContent("templates/email/09.email", [
                askOrderNo : askOrderNo,
                helpPhone : helpPhone
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 供方-确认供件-提醒需方创建求援单人员
     *{
     subject : "供方-确认供件-$!{askOrderNo}",
     emailBody: "求援单：$!{askOrderNo}, 供方-确认供件, 已生成订单号：$!{orderNo}。"
     }
     * @return
     */
    String sendTempMessage11(String receiverAddress, String askOrderNo, String partNo,
                             String orderNo){
        String json = getTemplateContent("templates/email/11.email", [
                askOrderNo : askOrderNo,
                partNo : partNo,
                orderNo : orderNo
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 利顿-超协议费用提交后-提醒求援方
     *{
     subject : "超协议费用待确认-$!{orderNo}",
     emailBody: "订单号：$!{orderNo}, 有超协议费用需要确认, 加价：$!{price}，原因：$!{reason}。"
     }
     * @return
     */
    String sendTempMessage35(String receiverAddress, String orderNo, String price, String reason){
        String json = getTemplateContent("templates/email/35.email", [
                orderNo : orderNo,
                price : price,
                reason : reason
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /**
     * 利顿-运单信息发送
     * @return
     */
    String sendTempMessage19(String receiverAddress, String askCompanyName, String waybillNo){
        String json = getTemplateContent("templates/email/19.email", [
                companyName : askCompanyName,
                dorderNo : waybillNo
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }

    /*
    * 提运材料上传完成-邮件提醒发送
    * */
    String sendTempMessage20(String receiverAddress, String LogisticsCompanyName, String logisticsNo){
        String json = getTemplateContent("templates/email/20.email", [
                LogisticsCompanyName : LogisticsCompanyName,
                logisticsNo : logisticsNo
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }
    String sendTempMessage40(String receiverAddress,String text){
        String json = getTemplateContent("templates/email/40.email", [
                orderContent : text
        ])
        try{
            JSONObject object = JSONObject.parseObject(json)
            String subject =  object.getString("subject")
            String emailBody =  object.getString("emailBody")
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }
    String sendTempMessage41(String receiverAddress, String company, String matnr,String city){
        String json = getTemplateContent("templates/email/41.email", [
                company : company,
                city:city,
                matnr:matnr
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }
    String sendTempMessage42(String receiverAddress, String company, String matnr,String city){
        String json = getTemplateContent("templates/email/42.email", [
                company : company,
                matnr : matnr,
                city:city
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }
    String sendTempMessage43(String receiverAddress, String qydate, String matnr,String orderType,int menge,String city,
                             String price,String dailyRent,String gfCompany,String confirmUser,String confirmDate){
        String json = getTemplateContent("templates/email/43.email", [
                qydate:qydate,
                matnr : matnr,
                orderType:orderType,
                menge:menge,
                city:city,
                price: price,
                dailyRent:dailyRent,
                gfCompany:gfCompany,
                confirmUser:confirmUser,
                confirmDate:confirmDate
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }
    String sendTempMessage21(String receiverAddress, String logisticsNo){
        String json = getTemplateContent("templates/email/21.email", [
                logisticsNo : logisticsNo,
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }
    String sendTempMessage44(String receiverAddress,String linkAddress,String userid){
        String json = getTemplateContent("templates/email/44.email", [
                linkAddress : linkAddress,
                userid : userid,
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }
    String sendTempMessage45(String receiverAddress,String linkAddress,String userid){
        String json = getTemplateContent("templates/email/45.email", [
                linkAddress : linkAddress,
                userid : userid,
        ])
        JSONObject object = JSONObject.parseObject(json)
        String subject =  object.getString("subject")
        String emailBody =  object.getString("emailBody")
        try{
            mailService.sendEmails(receiverAddress, subject, emailBody, null, true)
            return "S";
        }catch (Exception ex){
            ex.printStackTrace()
        }
        return "F";
    }
}

