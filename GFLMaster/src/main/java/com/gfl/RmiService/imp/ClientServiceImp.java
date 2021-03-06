package com.gfl.RmiService.imp;

import com.gfl.RmiService.ClientService;
import com.gfl.entry.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ClientServiceImp extends UnicastRemoteObject implements ClientService {
    //每个 Chunk 最大所占字节
    private final int CHUNK_SIZE = 64 * 1024;
    private final int CHUNK_COPY_NUM = 3;

    private Map<String, FileInfo> namespace = new HashMap<String, FileInfo>(32);
    public ClientServiceImp() throws RemoteException {
    }

    //响应 Client 的读文件
    public FileInfo Read(String fileName) throws RemoteException {
        System.out.println("调用 Read!");
        FileInfo fileInfo = namespace.get(fileName);
        if (fileInfo != null) {
            return fileInfo;
        }
        System.out.println("Master 没有此文件记录");
        return null;
    }

    //响应 Client 的写文件
    public FileInfo Write(String fileName, Long fileLength) throws RemoteException {
        System.out.println("调用 Write ！");
        if (fileLength == 0) {
            return null;
        }
        int chunkNum = (int) Math.ceil(fileLength / (double)CHUNK_SIZE);
        String id = RandomString();
        FileInfo fileInfo = new FileInfo();


        fileInfo.setId(id);
        fileInfo.setFileLength(fileLength);
        fileInfo.setChunkNum(chunkNum);
        fileInfo.setFileName(fileName);
        fileInfo.setChunks(FileChunkInfos(fileLength, chunkNum));

        namespace.put(fileName, fileInfo);
        if (fileInfo == null) {
            System.out.println("Master Write 有错误");
        }
        return fileInfo;
    }



    //创建每个文件对应的 Chunk
    private List FileChunkInfos(Long fileLength, int chunkNum){
        List<FileChunkInfo> fileChunkInfos = new ArrayList<FileChunkInfo>();

        for (int i = 0; i < chunkNum; i++){
            FileChunkInfo fileChunkInfo = new FileChunkInfo();
            int start = i * CHUNK_SIZE;
            int end;
            if (i != chunkNum - 1) {
                end = start + CHUNK_SIZE;
            }else {
                end = start + (int) Math.ceil(fileLength % CHUNK_SIZE);
            }
            fileChunkInfo.setStart(start);
            fileChunkInfo.setEnd(end);
            fileChunkInfo.setChunkCopys(ChunkCopyInfos());

            fileChunkInfos.add(fileChunkInfo);
        }

        return fileChunkInfos;
    }

    //创建 Chunk 所存在的位置
    private List ChunkCopyInfos() {
        ChunkCopyInfo chunkCopyInfo = new ChunkCopyInfo();
        List<ChunkCopyInfo> chunkCopyInfos = new ArrayList<ChunkCopyInfo>();
        HeartInfo heartInfo;

        for (int i = 0; i < CHUNK_COPY_NUM; i++){
            //此处应该使用某种算法随机响应 Chunk 位置
            heartInfo = GlobalHeartInfos.heartInfos.get((int)Math.random()*GlobalHeartInfos.heartInfos.size());
            chunkCopyInfo.setChunkIP(heartInfo.getIp());
            chunkCopyInfo.setPort(heartInfo.getPort());
            chunkCopyInfos.add(chunkCopyInfo);
        }
        return chunkCopyInfos;
    }

    private String RandomString(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
