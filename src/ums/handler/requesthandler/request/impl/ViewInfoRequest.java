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
public class ViewInfoRequest implements IRequest {
    private String uId;

    public ViewInfoRequest(String uId) {
        this.uId = uId;
    }

    public ViewInfoRequest() {
        uId = null;
    }

    public String getUId() {
        return uId;
    }

    @Override
    public IRequest createRequest(String request) {
        IRequest result = null;
        if (request != null){
            try {
                JSONParser jsp = new JSONParser();
                JSONObject jso = (JSONObject)jsp.parse(request);
                String tk = (String)jso.get(FieldCollection.FIELD_UID);
                
                result = new ViewInfoRequest(tk);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        
        return result;
    }
    
    
}
