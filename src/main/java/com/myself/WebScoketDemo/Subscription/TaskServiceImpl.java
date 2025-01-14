package com.myself.WebScoketDemo.Subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


@Component
public class TaskServiceImpl implements TaskService {

    private final static Logger logger= LoggerFactory.getLogger(TaskServiceImpl.class);


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;



//    @Scheduled(cron="0/10 * *  * * ? ")   //CRON表达式每10秒执行一次
    @Override
    public void selectCurDayDepotOrderInfo() {
        logger.info("=====================AAA频道每10秒执行一次刷新=====================");
        simpMessagingTemplate.convertAndSend(Const.Channel.AAA_CHANNEL.getName(),"123");
    }

//    @Scheduled(cron="0/13 * *  * * ? ")
    @Override
    public void selectCustomerOrderInfo() {
        logger.info("=====================BBB频道每13秒执行一次刷新=====================");
        simpMessagingTemplate.convertAndSend(Const.Channel.BBB_CHANNEL.getName(), "456");
    }


}
