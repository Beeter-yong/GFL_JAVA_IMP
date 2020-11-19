package com.gfl.RmiService;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    String filePath = "src/main/resources/gfs.pdf";

    @Test
    public void test01(){
        Client client = new Client();
        client.write(filePath);

    }
}