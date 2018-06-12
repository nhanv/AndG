/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler.impl;

import ums.dao.DAO;
import ums.entity.User;
import ums.handler.requesthandler.request.impl.LoginRequest;
import ums.entity.ClientResponse;
import ums.handler.responsehandler.IHandler;
import ums.handler.responsehandler.constant.FieldResponse;
import ums.result.extend.StringResult;
import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class LoginHandler implements IHandler {
    
    @Override
    public ClientResponse execute(String request) {
        ClientResponse result;
        //get data from request string
        LoginRequest r = (LoginRequest)new LoginRequest().createRequest(request);
        
        User u = new User (r.getEmail(), r.getPassword());
        StringResult rs = DAO.AccountDAO.login(u);
        JSONObject js = new JSONObject();
        StringBuilder tk = new StringBuilder().append(rs.getResult());
        
        js.put(FieldResponse.FIELD_RESPONSECODE, rs.getCode());
        js.put(FieldResponse.FIELD_RESPONSE, tk.toString());
        
        result = new ClientResponse(js);
        return result;
    }
    
    
}
