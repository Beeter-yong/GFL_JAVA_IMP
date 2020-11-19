package com.gfl.entry;

import java.io.Serializable;

public class ChunkCopyInfo implements Serializable {
    private String chunkIP;
    private String port;

    public String getChunkIP() {
        return chunkIP;
    }

    public void setChunkIP(String chunkIP) {
        this.chunkIP = chunkIP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
