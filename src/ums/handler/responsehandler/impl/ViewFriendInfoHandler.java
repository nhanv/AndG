/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler.impl;

import ums.dao.DAO;
import ums.entity.ClientResponse;
import ums.handler.requesthandler.request.impl.ViewFriendInfoRequest;
import ums.handler.responsehandler.IHandler;
import ums.handler.responsehandler.constant.FieldResponse;
import ums.result.extend.StringResult;
import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class ViewFriendInfoHandler implements IHandler {

    @Override
    public ClientResponse execute(String request) {
        //get data from request string
        ViewFriendInfoRequest v = new ViewFriendInfoRequest();
        ViewFriendInfoRequest r = (ViewFriendInfoRequest)v.createRequest(request);
        
        ClientResponse result;
        JSONObject js = new JSONObject ();
        StringResult rs = DAO.AccountDAO.getFriendInfo(r.getUId(), r.getFrEmail());
        
        js.put(FieldResponse.FIELD_RESPONSECODE, rs.getCode());
        js.put(FieldResponse.FIELD_RESPONSE, rs.toString());
        
        result = new ClientResponse(js);
        return result;
    }
    
}
