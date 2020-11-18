package com.gfl.service;

import com.gfl.service.imp.ChunkClientServiceImp;

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
            String url = "rmi://127.0.0.1:7777/chunkClientService01";
            try {
                Naming.rebind(url, chunkClientService);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
