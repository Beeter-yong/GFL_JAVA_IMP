package com.gfl.RmiService.imp;

import com.gfl.RmiService.ChunkServerService;
import com.gfl.entry.GlobalHeartInfos;
import com.gfl.entry.HeartInfo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChunkServerServiceImp extends UnicastRemoteObject implements ChunkServerService {
    public ChunkServerServiceImp() throws RemoteException {
    }

    public String Test(String name) throws RemoteException {
        System.out.println("接受到：" + name);
        return "HEllo World";
    }

    public boolean GetChunkServerHeart(HeartInfo heartInfo) throws RemoteException {
        System.out.println("接受到心跳");
        for (HeartInfo localHeartInfo : GlobalHeartInfos.heartInfos) {
            if (localHeartInfo.getIp().equals(heartInfo.getIp()) && localHeartInfo.getPort().equals(heartInfo.getPort())){
                return true;
            }
        }
        GlobalHeartInfos.heartInfos.add(heartInfo);
        System.out.println(GlobalHeartInfos.heartInfos.size());
        return true;
    }
}
