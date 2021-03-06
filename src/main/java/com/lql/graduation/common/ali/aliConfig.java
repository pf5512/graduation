package com.lql.graduation.common.ali;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;

public class aliConfig {

     private String accessKeyId = "LTAInnUiYQ9Ty7Yk";
     private String accessKeySecret = "zevYTuE0hjyLp9oqUmmIJ6r7Hb2W3F";
     private String accountEndpoint = "http://1213996060683026.mns.cn-shanghai.aliyuncs.com/";



     private CloudAccount account = null;
    public aliConfig() {

        account = new CloudAccount(
                accessKeyId,
                accessKeySecret,
                accountEndpoint
        );
    }

    /**
     *
     * 从指定的队列中接收消息
     */
    public void ReceiverQueMessage(){

        MNSClient client = account.getMNSClient();
        CloudQueue queue = client.getQueueRef("aliyun-iot-jqLf0X9GFja"); //参数请输入IoT自动创建的队列名称，例如上面截图中的aliyun-iot-3AbL0062osF
        while (true) {
            // 获取消息
            Message popMsg = queue.popMessage(10); //长轮询等待时间为10秒
            if (popMsg != null) {
                System.out.println("PopMessage Body: "
                        + popMsg.getMessageBodyAsRawString()); //获取原始消息
                queue.deleteMessage(popMsg.getReceiptHandle()); //从队列中删除消息
            } else {
                System.out.println("Continuing");
            }
        }


    }

    /**
     *
     * 从指定的队列中接收消息,设备的上线下线消息默认发往默认创建的队列中
     */
    public CloudQueue getQue(String queName){
        MNSClient client = account.getMNSClient();
        CloudQueue queue = client.getQueueRef(queName); //参数请输入IoT自动创建的队列名称，例如上面截图中的aliyun-iot-3AbL0062osF
        return queue;
    }



}
