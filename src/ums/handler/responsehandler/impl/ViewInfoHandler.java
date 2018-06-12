/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler.impl;

import ums.dao.DAO;
import ums.entity.User;
import ums.handler.requesthandler.request.impl.ViewInfoRequest;
import ums.entity.ClientResponse;
import ums.handler.requesthandler.request.constant.FieldCollection;
import ums.handler.responsehandler.IHandler;
import ums.handler.responsehandler.constant.FieldResponse;
import ums.result.extend.ListResult;
import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class ViewInfoHandler implements IHandler {
    
    @Override
    public ClientResponse execute(String request) {
        ClientResponse result;
        //get data from request string
        ViewInfoRequest v = new ViewInfoRequest();
        ViewInfoRequest r = (ViewInfoRequest)v.createRequest(request);
        
        JSONObject js = new JSONObject();
        ListResult rs = DAO.AccountDAO.getInfo(r.getUId());
        
        js.put(FieldResponse.FIELD_RESPONSECODE, rs.getCode());
        if (rs.getListResult()!=null){
            User u = (User)rs.getListResult().get(0);
            JSONObject user = new JSONObject();
            user.put(FieldCollection.FIELD_EMAIL, u.getEmail());
            user.put(FieldCollection.FIELD_NAME, u.getName());
            
            String info = user.toString();
            js.put(FieldResponse.FIELD_RESPONSE, info);
        }
        
        result = new ClientResponse(js);
        return result;
    }
    
}
