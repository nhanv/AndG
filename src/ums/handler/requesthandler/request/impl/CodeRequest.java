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
public class CodeRequest implements IRequest {
    private int code;

    public CodeRequest() {
        this.code = -1;
    }

    public CodeRequest(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public IRequest createRequest(String request) {
        IRequest result = null;
        if (request != null){
            try {
                JSONParser jsp = new JSONParser();
                JSONObject jso = (JSONObject)jsp.parse(request);
                long rc = (Long)jso.get(FieldCollection.FIELD_REQUESTCODE);
                result = new CodeRequest((int)rc);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        
        return result;
    }
    
}
