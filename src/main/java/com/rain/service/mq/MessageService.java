package com.rain.service.mq;

import com.rain.mapper.MessageMapper;
import com.rain.model.dao.Message;
import com.rain.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by tpeng on 2016/12/22.
 */
@Service
public class MessageService extends BaseService<Message> implements IMessageService {

    private Logger logger = LoggerFactory.getLogger(MessageService.class);

    public static final String KEY_PREFIX = "TempMsg";

    @Resource
    MessageMapper messageMapper;

    static Properties properties = null;
    static {
//        InputStream ins = MessageService.class.getClassLoader().getResourceAsStream("config/aog-message.properties");
//        try{
//            BufferedReader bf = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
//            properties = new Properties();
//            properties.load(bf);
//        } catch (IOException e1){
//            e1.printStackTrace();
//        }
    }
    /**
     * 测试0： 上传凭证消息
     * @param message
     */
//    @Override
//    public void recordMessage(Message message) {
//        AppContext context = ContextUtil.getAppContext();
//        if(message.getMsgtype() != BusinessStatus.MESSAGE_TYPE_SMS.intValue()){
//            logger.error("消息类型使用错误，请检查！ {}", JSONObject.toJSONString(message));
//            return;
//        }
//        String key = String.valueOf(message.getMsgtype());
//        String msgBodyBy = properties.getProperty(KEY_PREFIX + key);
//        message.setMsgbody(msgBodyBy);
//        messageMapper.insertSelective(message);
//        //更新全局消息数量统计
//    }
    @Override
    public Mapper getMapper() {
        return messageMapper;
    }
}
