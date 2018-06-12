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
public class RegisterRequest implements IRequest {
    private String email;
    private String password;
    private String name;
    private int age;

    public RegisterRequest() {
        email = null;
        password = null;
        name = null;
        age = -1;
    }

    public RegisterRequest(String email, String password, String name, int age) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public IRequest createRequest(String request) {
        IRequest result = null;
        if (request != null){
            try {
                JSONParser jsp = new JSONParser();
                JSONObject jso = (JSONObject)jsp.parse(request);
                
                String em = (String)jso.get(FieldCollection.FIELD_EMAIL);
                String ps = (String)jso.get(FieldCollection.FIELD_PASSWORD);
                String nm = (String)jso.get(FieldCollection.FIELD_NAME);
                long ag = (long)jso.get(FieldCollection.FIELD_AGE);
                
                result = new RegisterRequest(em, ps, nm, (int)ag);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        
        return result;
    }
    
}
