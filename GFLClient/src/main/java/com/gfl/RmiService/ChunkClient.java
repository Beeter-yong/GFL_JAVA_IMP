package com.gfl.RmiService;

import com.gfl.entry.ChunkClientInfo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Enumeration;

/**
 * 负责与ChunkServer通信，包括传输文件
 */
public class ChunkClient {
    private ChunkClientService chunkClientService;

    public ChunkClient(String ip, String port) {
        try {
            Context nameingContext = new InitialContext();
            String urlChunkServer = "rmi://" + ip + ":" + port + "/chunkClientService";

//            Enumeration<NameClassPair> e = nameingContext.list("rmi://127.0.0.1:6666/");
//            while (e.hasMoreElements()) {
//                System.out.println(e.nextElement().getName());
//            }
            //从注册表中获取服务类
            chunkClientService = (ChunkClientService) nameingContext.lookup(urlChunkServer);

            System.out.println("获得ChunkServer服务");

            //相关动作
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    //传输文件
    public boolean SendChunkFile(ChunkClientInfo chunkClientInfo) {
        boolean success = false;
        try {
            success = chunkClientService.Write(chunkClientInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (success == false) {
            System.out.println("发送Chunk有问题，还没有解决");
            return false;
        }
        return true;
    }

    public void Test(){
        try {
            String rEn = chunkClientService.test("REn");
            System.out.println(rEn);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
