package com.gfl.RmiService;

import com.gfl.entry.HeartInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChunkServerService extends Remote {
    public String Test(String name) throws RemoteException;

    public boolean GetChunkServerHeart(HeartInfo heartInfo) throws RemoteException;
}
