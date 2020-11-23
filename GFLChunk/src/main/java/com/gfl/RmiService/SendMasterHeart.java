package com.gfl.RmiService;

import com.gfl.entry.HeartInfo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Enumeration;

public class SendMasterHeart {
    private final String ip = "127.0.0.1";
    private final String port = "8888";
    private HeartInfo heartInfo = new HeartInfo();

    ChunkServerService chunkServerService;
    public SendMasterHeart() {
        try {
            Context nameingContext = new InitialContext();
            String urlMaster = "rmi://127.0.0.1:8888/chunkService";

            Enumeration<NameClassPair> e = nameingContext.list("rmi://127.0.0.1:8888/");
            while (e.hasMoreElements()) {
                System.out.println(e.nextElement().getName());
            }
            //从注册表中获取服务类
            chunkServerService = (ChunkServerService) nameingContext.lookup(urlMaster);

            System.out.println("获得Master服务");

            //相关动作
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void sendHeartLoop(){
        heartInfo.setIp("127.0.0.1");
        heartInfo.setPort("7777");
        while (true){
            try {
                boolean success = chunkServerService.GetChunkServerHeart(heartInfo);
                if (success == false){
                    System.out.println("发出心跳有问题");
                    break;
                }
                Thread.sleep(8 * 1000);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test(){
        try {
            System.out.println(chunkServerService.Test("ren"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
