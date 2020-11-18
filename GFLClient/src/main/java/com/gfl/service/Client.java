package com.gfl.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 提供 Client 操作相关接口
 */
public class Client {

    public Client() {
        try {
            Context nameingContext = new InitialContext();
            String urlMaster = "rmi://127.0.0.1:6666/clientService";

            //从注册表中获取服务类
            ClientService clientService = (ClientService) nameingContext.lookup(urlMaster);

            //相关动作
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
