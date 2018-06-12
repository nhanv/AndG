/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler.impl;

import ums.dao.DAO;
import ums.entity.ClientResponse;
import ums.handler.responsehandler.IHandler;
import ums.result.extend.StringResult;

/**
 *
 * @author Nguyen Van Nha
 */
public class ViewAllAccount implements IHandler {

    @Override
    public ClientResponse execute(String request) {
        StringResult rs = DAO.AccountDAO.getAllInfo();
        System.out.println("All Account:");
        System.out.println(rs.getResult());
        
        return null;
    }
    
}
