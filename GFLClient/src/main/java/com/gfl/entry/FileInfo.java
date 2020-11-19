package com.gfl.entry;

import java.io.Serializable;
import java.util.List;

public class FileInfo implements Serializable {
    private String id;
    private String fileName;
    private long fileLength;
    private int ChunkNum;
    private List<FileChunkInfo> chunks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public int getChunkNum() {
        return ChunkNum;
    }

    public void setChunkNum(int chunkNum) {
        ChunkNum = chunkNum;
    }

    public List<FileChunkInfo> getChunks() {
        return chunks;
    }

    public void setChunks(List<FileChunkInfo> chunks) {
        this.chunks = chunks;
    }
}
