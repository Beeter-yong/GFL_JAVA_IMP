package com.gfl.service.imp;

import com.gfl.service.ChunkClientService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChunkClientServiceImp extends UnicastRemoteObject implements ChunkClientService {
    public ChunkClientServiceImp() throws RemoteException {
    }
}
