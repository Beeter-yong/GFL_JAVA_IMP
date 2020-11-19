package com.gfl.RmiService.imp;

import com.gfl.RmiService.ChunkClientService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChunkClientServiceImp extends UnicastRemoteObject implements ChunkClientService {
    public ChunkClientServiceImp() throws RemoteException {
    }

    public String test(String name) throws RemoteException {
        System.out.println("接受到：" + name);
        return "Hello World!";
    }
}
