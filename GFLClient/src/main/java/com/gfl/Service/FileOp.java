package com.gfl.Service;

import java.io.File;
import java.io.Serializable;

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

    public String getFileName() {
        return fileName;
    }

    public long getFileLength() {
        return fileLength;
    }
}
