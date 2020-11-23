package com.gfl.RmiService;

public class Test {
    @org.junit.Test
    public void test01(){
        SendMasterHeart sendMasterHeart = new SendMasterHeart();
        sendMasterHeart.sendHeartLoop();
    }
}
