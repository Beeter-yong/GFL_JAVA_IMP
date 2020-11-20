package com.gfl.Service;

import java.io.*;

/**
 * 对文件相关操作
 */
public class FileOp {
    private File file;
    private byte[] chunk;

    private String fileName;
    private long fileLength;

    public FileOp() {
    }
    public FileOp(String filePath) {
        file = new File(filePath);
        fileName = file.getName();
        fileLength = file.length();
    }

    //将字节数组写入本地
    public boolean BytesWriteFile (String filePath, byte[] bytes) {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath, true);
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

    //对文件进行字节化
    public byte[] getFileBytes(int start, int end) {
        int size = end - start;
        byte[] bytes = new byte[size];

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);

            bufferedInputStream = new BufferedInputStream(fileInputStream);

            bufferedInputStream.skip(start);

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



    public String getFileName() {
        return fileName;
    }

    public long getFileLength() {
        return fileLength;
    }

}
