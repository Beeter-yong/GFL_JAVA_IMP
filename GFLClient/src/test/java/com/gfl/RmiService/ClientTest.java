package com.gfl.RmiService;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    String filePath = "src/main/resources/gfs.pdf";
//    String filePath = "src/main/resources/aaa.text";
//    String WriteFilePath = "src/main/resources/bbb.text";
    String WriteFilePath = "src/main/resources/bbb.pdf";

    @Test
    public void test01(){
        Client client = new Client();
        client. write(filePath);

    }

    @Test
    public void test02(){
        Client client = new Client();
//        client.Read("aaa.text", WriteFilePath);
        client.Read("gfs.pdf", WriteFilePath);
    }
}