package com.gfl.RmiService;

import com.gfl.entry.FileInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {
    public FileInfo Write(String fileName, Long fileLength) throws RemoteException;
}
