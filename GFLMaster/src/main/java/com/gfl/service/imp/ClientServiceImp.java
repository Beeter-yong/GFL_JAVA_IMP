package com.gfl.service.imp;

import com.gfl.service.ChunkServerService;
import com.gfl.service.ClientService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientServiceImp extends UnicastRemoteObject implements ClientService {
    public ClientServiceImp() throws RemoteException {
    }
}
