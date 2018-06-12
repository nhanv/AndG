/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler.impl;

import ums.dao.DAO;
import ums.entity.User;
import ums.handler.requesthandler.request.impl.ViewFriendListRequest;
import ums.entity.ClientResponse;
import ums.handler.responsehandler.IHandler;
import ums.handler.responsehandler.constant.FieldResponse;
import ums.result.extend.ListResult;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class ViewFriendListHandler implements IHandler {
    
    @Override
    public ClientResponse execute(String request) {
        ClientResponse result;
        //get data from request string
        ViewFriendListRequest v = new ViewFriendListRequest();
        ViewFriendListRequest r = (ViewFriendListRequest)v.createRequest(request);
        
        JSONObject js = new JSONObject();
        ListResult rs = DAO.AccountDAO.getFriend(r.getUId());
        js.put(FieldResponse.FIELD_RESPONSECODE, rs.getCode());
        if (rs.getListResult() != null){
            JSONArray jsArr = new JSONArray();
            List<User> list = rs.getListResult();
            int size = rs.getListResult().size();
            
            for (int i = 0; i < size; i++) {
                jsArr.add(list.get(i).toJSON());
            }
            js.put(FieldResponse.FIELD_RESPONSE, jsArr);
        }
        
        result = new ClientResponse(js);
        return result;
    }
    
}
