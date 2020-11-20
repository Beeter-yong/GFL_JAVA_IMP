package com.gfl.RmiService;

import com.gfl.entry.ChunkClientInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChunkClientService extends Remote {
    public String test(String name) throws RemoteException;

    public boolean Write(ChunkClientInfo chunkClientInfo) throws RemoteException;
}
