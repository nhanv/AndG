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
public class ChangePassRequest implements IRequest {
    private String uId;
    private String oldPass;
    private String newPass;

    public ChangePassRequest() {
        uId = null;
        oldPass = null;
        newPass = null;
    }

    public ChangePassRequest(String uId, String oldPass, String newPass) {
        this.uId = uId;
        this.oldPass = oldPass;
        this.newPass = newPass;
    }

    public String getUId() {
        return uId;
    }

    public String getOldPass() {
        return oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    @Override
    public IRequest createRequest(String request) {
        IRequest result = null;
        if (request != null){
            try {
                JSONParser jsp = new JSONParser();
                JSONObject jso = (JSONObject)jsp.parse(request);
                String tk = (String)jso.get(FieldCollection.FIELD_UID);
                String oP = (String)jso.get(FieldCollection.FIELD_PASSWORD);
                String nP = (String)jso.get(FieldCollection.FIELD_NEWPASSWORD);
                
                result = new ChangePassRequest(tk, oP, nP);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        
        return result;
    }
    
}
