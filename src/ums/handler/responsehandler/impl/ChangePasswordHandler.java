/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler.impl;

import ums.dao.DAO;
import ums.handler.requesthandler.request.impl.ChangePassRequest;
import ums.entity.ClientResponse;
import ums.handler.responsehandler.IHandler;
import ums.handler.responsehandler.constant.FieldResponse;
import ums.result.Result;
import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class ChangePasswordHandler implements IHandler {

    @Override
    public ClientResponse execute(String request) {
        //get data from request string
        ChangePassRequest c = new ChangePassRequest();
        ChangePassRequest r = (ChangePassRequest)c.createRequest(request);
        
        ClientResponse result;
        JSONObject js = new JSONObject ();
        Result rs = DAO.AccountDAO.changePass(r.getUId(), r.getOldPass(),
                                                      r.getNewPass());
        js.put(FieldResponse.FIELD_RESPONSECODE, rs.getCode());
        result = new ClientResponse(js);
        return result;
    }
    
}
