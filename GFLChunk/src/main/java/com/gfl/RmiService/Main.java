package com.gfl.RmiService;

import com.gfl.RmiService.imp.ChunkClientServiceImp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
    //启动 ChunkServer
    public static void main(String[] args) {

        try {
            //1.产生服务器对象
            ChunkClientService chunkClientService = new ChunkClientServiceImp();

            //2.绑定端口
            LocateRegistry.createRegistry(7777);

            //3.绑定对象
            String url = "rmi://127.0.0.1:7777/chunkClientService";
            try {
                Naming.rebind(url, chunkClientService);
                System.out.println("等待客户端调用！！！！");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //发送心跳信息
        SendMasterHeart sendMasterHeart = new SendMasterHeart();
        sendMasterHeart.sendHeartLoop();
    }
}
