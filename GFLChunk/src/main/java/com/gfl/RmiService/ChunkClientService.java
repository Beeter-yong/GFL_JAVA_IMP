package com.gfl.RmiService;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChunkClientService extends Remote {
    public String test(String name) throws RemoteException;
}
