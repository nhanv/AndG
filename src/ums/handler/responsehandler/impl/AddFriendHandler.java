/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler.impl;

import ums.dao.DAO;
import ums.entity.User;
import ums.handler.requesthandler.request.impl.AddFriendRequest;
import ums.entity.ClientResponse;
import ums.handler.responsehandler.IHandler;
import ums.handler.responsehandler.constant.FieldResponse;
import ums.result.Result;
import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class AddFriendHandler implements IHandler {

    @Override
    public ClientResponse execute(String request) {
        //get data from request string
        AddFriendRequest a = new AddFriendRequest();
        AddFriendRequest r = (AddFriendRequest)a.createRequest(request);
        
        ClientResponse result;
        JSONObject js = new JSONObject();
        User u = new User(r.getFriendEmail());
        Result rs = DAO.AccountDAO.addFriend(r.getUId(), u);
        js.put(FieldResponse.FIELD_RESPONSECODE, rs.getCode());
        result = new ClientResponse(js);
        return result;
    }
    
}
