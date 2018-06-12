/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.requesthandler.request.impl;

import ums.handler.requesthandler.request.IRequest;
import ums.handler.requesthandler.request.constant.FieldCollection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Nguyen Van Nha
 */
public class ViewFriendInfoRequest implements IRequest {
    private String uId;
    private String frEmail;

    public ViewFriendInfoRequest() {
        uId = null;
        frEmail = null;
    }

    public ViewFriendInfoRequest(String uId, String frEmail) {
        this.uId = uId;
        this.frEmail = frEmail;
    }

    public String getUId() {
        return uId;
    }

    public String getFrEmail() {
        return frEmail;
    }

    @Override
    public IRequest createRequest(String request) {
        IRequest result = null;
        if (request != null){
            try {
                JSONParser jsp = new JSONParser();
                JSONObject jso = (JSONObject)jsp.parse(request);
                String tk = (String)jso.get(FieldCollection.FIELD_UID);
                String frE = (String)jso.get(FieldCollection.FIELD_FREMAIL);
                
                result = new LoginRequest(tk, frE);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        
        return result;
    }
    
}
