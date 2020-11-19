package com.gfl.RmiService.imp;

import com.gfl.RmiService.ChunkServerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChunkServerServiceImp extends UnicastRemoteObject implements ChunkServerService {
    public ChunkServerServiceImp() throws RemoteException {
    }
}
