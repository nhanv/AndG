/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler.impl;

import ums.dao.DAO;
import ums.entity.User;
import ums.handler.requesthandler.request.impl.RegisterRequest;
import ums.entity.ClientResponse;
import ums.handler.responsehandler.IHandler;
import ums.handler.responsehandler.constant.FieldResponse;
import ums.result.Result;
import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class RegisterHandler implements IHandler {    
    
    @Override
    public ClientResponse execute(String request) {
        ClientResponse result;
        //get data from request string
        RegisterRequest rg = new RegisterRequest();
        RegisterRequest r = (RegisterRequest)rg.createRequest(request);
        
        User u = new User(r.getEmail(), r.getPassword(), r.getName(), r.getAge());
        Result rs = DAO.AccountDAO.insertData(u);
        JSONObject js = new JSONObject();
        js.put(FieldResponse.FIELD_RESPONSECODE, rs.getCode());
        
        result = new ClientResponse(js);
        return result;
    }
    
}
