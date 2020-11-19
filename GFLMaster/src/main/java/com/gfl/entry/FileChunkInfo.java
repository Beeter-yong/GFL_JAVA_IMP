package com.gfl.entry;

import java.io.Serializable;
import java.util.List;

public class FileChunkInfo {
    private int start;
    private int end;
    private List<ChunkCopyInfo> chunkCopys;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<ChunkCopyInfo> getChunkCopys() {
        return chunkCopys;
    }

    public void setChunkCopys(List<ChunkCopyInfo> chunkCopys) {
        this.chunkCopys = chunkCopys;
    }
}
