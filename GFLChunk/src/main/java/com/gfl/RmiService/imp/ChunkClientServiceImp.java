package com.gfl.RmiService.imp;

import com.gfl.RmiService.ChunkClientService;
import com.gfl.entry.ChunkClientInfo;
import com.gfl.service.FileOp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChunkClientServiceImp extends UnicastRemoteObject implements ChunkClientService {
    public ChunkClientServiceImp() throws RemoteException {
    }

    public String test(String name) throws RemoteException {
        System.out.println("接受到：" + name);
        return "Hello World!";
    }

    //将Chunk 保存到本地，并记录
    public boolean Write(ChunkClientInfo chunkClientInfo) throws RemoteException {
        System.out.println("保存Chunk函数被调用");
        FileOp fileOp = new FileOp();
        String filePath = "src/main/resources/" + chunkClientInfo.getChunkNum() + chunkClientInfo.getFileName();
        fileOp.CreateChunk(filePath);
        boolean writeBytesSucess = fileOp.WriteBytes(chunkClientInfo.getBytes());
        if (writeBytesSucess == true) {
            return true;
        }

        System.out.println("写入失败");
        return false;
    }
}
