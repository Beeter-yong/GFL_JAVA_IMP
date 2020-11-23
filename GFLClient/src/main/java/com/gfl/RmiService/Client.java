package com.gfl.RmiService;

import com.gfl.Service.FileOp;
import com.gfl.entry.ChunkClientInfo;
import com.gfl.entry.ChunkCopyInfo;
import com.gfl.entry.FileChunkInfo;
import com.gfl.entry.FileInfo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.List;

/**
 * 提供 Client 操作相关接口
 */
public class Client {

    private ClientService clientService;

    public Client() {
        try {
            Context nameingContext = new InitialContext();
            String urlMaster = "rmi://127.0.0.1:6666/clientService";

            Enumeration<NameClassPair> e = nameingContext.list("rmi://127.0.0.1:6666/");
            while (e.hasMoreElements()) {
                System.out.println(e.nextElement().getName());
            }
            //从注册表中获取服务类
            clientService = (ClientService) nameingContext.lookup(urlMaster);

            System.out.println("获得Master服务");

            //相关动作
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读文件
     * @param fileName 读的文件名称
     * @param writteFilePath 将读取的文件放置位置
     */
    public void Read(String fileName, String writteFilePath) {
        //1.发送给 Master 文件名获取文件信息
        FileInfo fileInfo = null;
        try {
            fileInfo = clientService.Read(fileName);
            if (fileInfo == null) {
                System.out.println("不存在此文件！！！");
                return;
            }else {
                //2. 联系 ChunkServer 拿去文件
                System.out.println("成功！！");
                boolean sucess = ReadFileByChunkServer(fileInfo, writteFilePath);
                if (sucess == false) {
                    System.out.println("读取文件失败！！");
                    return;
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写文件
     * @param filePath 要写的本地文件路径
     */
    public void write(String filePath) {
        //1.从Master 获取文件分配成 Chunk 的信息
        FileInfo fileInfo = GetFileInfoByMaster(filePath);
        if (fileInfo == null){
            System.out.println("有问题");
        }
        FileChunkInfo fileChunkInfo;
        //2.根据信息将文件传输给对应 ChunkServer 保存
        //循环获取 文件分成的多个 Chunk
        for (int i = 0; i < fileInfo.getChunkNum(); i++) {
            fileChunkInfo = fileInfo.getChunks().get(i);
            int fileChunkInfoNum = fileChunkInfo.getChunkCopys().size();
            //读取指定大小的文件
            byte[] bytes = new FileOp(filePath).getFileBytes(fileChunkInfo.getStart(), fileChunkInfo.getEnd());
            //创建传输的基本对象，并填充，后续发送给ChunkServer
            ChunkClientInfo chunkClientInfo = new ChunkClientInfo();
            chunkClientInfo.setId(fileInfo.getId());
            chunkClientInfo.setChunkNum(i);
            chunkClientInfo.setFileName(fileInfo.getFileName());
            chunkClientInfo.setBytes(bytes);

            //循环对每一个文件Chunk对应的副本进行传输保存
            for (int j = 0; j < fileChunkInfoNum; j++) {
                ChunkCopyInfo chunkCopyInfo = fileChunkInfo.getChunkCopys().get(j);
                ChunkClient chunkClient = new ChunkClient(chunkCopyInfo.getChunkIP(), chunkCopyInfo.getPort());
//                ChunkClient chunkClient = new ChunkClient("127.0.0.1", "7777");
                //进行文件保存
                boolean success = chunkClient.SendChunkFile(chunkClientInfo);
                System.out.println(success);
            }
        }
    }

    // 根据文件信息获取文件
    private boolean ReadFileByChunkServer(FileInfo fileInfo, String writeFilePath){
        int chunkNum = fileInfo.getChunkNum();
        FileChunkInfo fileChunkInfo;
        ChunkCopyInfo chunkCopyInfo;
        for (int  i = 0; i < chunkNum; i++) {
            fileChunkInfo = fileInfo.getChunks().get(i);
            //获取最好的 ChunkServer
            chunkCopyInfo = GetBestChunk(fileChunkInfo.getChunkCopys());
            String ip = chunkCopyInfo.getChunkIP();
            String port = chunkCopyInfo.getPort();
//            String ip = "127.0.0.1";
//            String port = "7777";
            ChunkClient chunkClient = new ChunkClient(ip, port);
            //获取Chunk
            byte[] bytes = chunkClient.ReadBytes(i, fileInfo.getFileName(), fileChunkInfo.getEnd() - fileChunkInfo.getStart());
            //将bytes写入到本地
            FileOp fileOp = new FileOp();
            fileOp.BytesWriteFile(writeFilePath, bytes);
        }
        return true;
    }

    //从多个副本 Chunk 中选取
    private ChunkCopyInfo GetBestChunk(List<ChunkCopyInfo> chunkCopyInfos){
        return chunkCopyInfos.get(0);
    }

    //写操作中与 Master 操作
    private FileInfo GetFileInfoByMaster(String filePath){
        FileOp fileOp = new FileOp(filePath);
        String fileName = fileOp.getFileName();
        Long fileLength = fileOp.getFileLength();
        System.out.println("fileNameClient： " + fileName);
        System.out.println("fileLengthClient： " + fileLength);
        try {
            return clientService.Write(fileName, fileLength);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
       return null;
    }

}
