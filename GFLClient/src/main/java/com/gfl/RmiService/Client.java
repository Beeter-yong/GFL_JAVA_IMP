package com.gfl.RmiService;

import com.gfl.Service.FileOp;
import com.gfl.entry.FileInfo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Enumeration;

/**
 * 提供 Client 操作相关接口
 */
public class Client {

    private ClientService clientService;

    public Client() {
        try {
            Context nameingContext = new InitialContext();
            String urlMaster = "rmi://127.0.0.1:6666/clientService";

//            Enumeration<NameClassPair> e = nameingContext.list("rmi://127.0.0.1:6666/");
//            while (e.hasMoreElements()) {
//                System.out.println(e.nextElement().getName());
//            }
            //从注册表中获取服务类
            clientService = (ClientService) nameingContext.lookup(urlMaster);

            //相关动作
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    //写文件
    public void write(String filePath) {
        FileOp fileOp = new FileOp(filePath);
        String fileName = fileOp.getFileName();
        Long fileLength = fileOp.getFileLength();
        System.out.println("fileNameClient： " + fileName);
        System.out.println("fileLengthClient： " + fileLength);
        try {
            FileInfo fileInfo = clientService.Write(fileName, fileLength);
            System.out.println(fileInfo.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
