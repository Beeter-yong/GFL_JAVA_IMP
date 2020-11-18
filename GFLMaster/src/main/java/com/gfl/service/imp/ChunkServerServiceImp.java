package com.gfl.service.imp;

import com.gfl.service.ChunkServerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChunkServerServiceImp extends UnicastRemoteObject implements ChunkServerService {
    public ChunkServerServiceImp() throws RemoteException {
    }
}
