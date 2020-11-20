package com.gfl.service;

import java.io.*;

/**
 * 负责本地对文件的操作
 */
public class FileOp {

    private File file;
    private byte[] bytes;
    private String fileName;

    public FileOp() {
    }

    //创建一个文件保存Chunk
    public boolean CreateChunk(String filePath){
        file = new File(filePath);
        if (file.exists()) {
            System.out.println("文件已经存在");
            return false;
        }else {
            try {
                file.createNewFile();
                System.out.println("创建一个新的文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    //读取数据
    public byte[] ReadBytes(String filePath, int size){
        file = new File(filePath);
        byte[] bytes = new byte[size];
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);

            bufferedInputStream = new BufferedInputStream(fileInputStream);

            bufferedInputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    //将指定 byte 数组写入磁盘中
    public boolean WriteBytes(byte[] bytes){
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(bytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入本地失败！！  ");
        return false;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
