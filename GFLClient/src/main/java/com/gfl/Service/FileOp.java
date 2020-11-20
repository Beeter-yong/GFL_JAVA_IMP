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

    public FileOp(String filePath) {
        file = new File(filePath);
        fileName = file.getName();
        fileLength = file.length();
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
