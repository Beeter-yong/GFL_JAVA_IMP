package com.gfl.RmiService;

import com.gfl.RmiService.imp.ChunkServerServiceImp;
import com.gfl.RmiService.imp.ClientServiceImp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
    //启动 Master
    public static void main(String[] args) {
        try {
            //1.产生服务器对象
            ClientService clientService = new ClientServiceImp();
            ChunkServerService chunkServerService = new ChunkServerServiceImp();

            //2.绑定端口
            LocateRegistry.createRegistry(6666);
            LocateRegistry.createRegistry(8888);

            //3.绑定对象
            String urlClient = "rmi://127.0.0.1:6666/clientService";
            String urlChunkServer = "rmi://127.0.0.1:8888/chunkService";

            try {
                Naming.rebind(urlClient, clientService);
                Naming.rebind(urlChunkServer, chunkServerService);

                System.out.println("等待客户端调用");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
