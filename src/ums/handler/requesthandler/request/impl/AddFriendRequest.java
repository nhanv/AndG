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
public class AddFriendRequest implements IRequest {
    private String uId;
    private String friendEmail;

    public AddFriendRequest() {
        uId = null;
        friendEmail = null;
    }

    public AddFriendRequest(String uId, String friendEmail) {
        this.uId = uId;
        this.friendEmail = friendEmail;
    }

    public String getUId() {
        return uId;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    @Override
    public IRequest createRequest(String request) {
        IRequest result = null;
        if (request != null){
            try {
                JSONParser jsp = new JSONParser();
                JSONObject jso = (JSONObject)jsp.parse(request);
                String uI = (String)jso.get(FieldCollection.FIELD_UID);
                String fE = (String)jso.get(FieldCollection.FIELD_FREMAIL);
                
                result = new AddFriendRequest (uI, fE);
            }catch (ParseException | NumberFormatException e){
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
}
