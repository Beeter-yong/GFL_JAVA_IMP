package com.gfl.RmiService;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
//    String filePath = "src/main/resources/gfs.pdf";
    String filePath = "src/main/resources/aaa.text";

    @Test
    public void test01(){
        Client client = new Client();
        client. write(filePath);

    }

    @Test
    public void test02(){
        System.out.println(Math.ceil(5 / 2.2));
    }
}