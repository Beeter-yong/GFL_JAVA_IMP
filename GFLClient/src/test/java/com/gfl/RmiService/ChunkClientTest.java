package com.gfl.RmiService;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChunkClientTest {

    @Test
    public void test01(){
        String ip = "127.0.0.1";
        String port = "7777";
        ChunkClient chunkClient = new ChunkClient(ip, port);
        chunkClient.Test();
    }

    @Test
    public void test02(){
        String ip = "127.0.0.1";
        String port = "7777";
        ChunkClient chunkClient = new ChunkClient(ip, port);
        chunkClient.Test();
    }

}