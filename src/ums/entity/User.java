/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.entity;

import ums.handler.requesthandler.request.constant.FieldCollection;
import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class User {
    private String name;
    private String password;
    private String email;
    private int age;

    public User() {
        name = null;
        password = null;
        email = null;
        age = -1;
    }

    public User(String email) {
        this.email = email;
        this.password = null;
        this.name = null;
        this.age = -1;
    }

    public User(String email, String password) {
        this.password = password;
        this.email = email;
        this.name = null;
        this.age = -1;
    }

    public User(String email, String password, String name, int age) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
    
    @Override
    public String toString (){
        StringBuilder str = new StringBuilder ();
        if (email != null && !email.isEmpty())
            str.append("Email: ").append(this.getEmail()).append("\n");
        if (password != null && !password.isEmpty())
            str.append("Password: ").append(this.getPassword()).append("\n");
        if (name != null && !name.isEmpty())
            str.append("Name: ").append(this.getName()).append("\n");
        if (age > 0 && age < 150)
            str.append("Age: ").append(this.getAge()).append("\n");
        return str.toString();
    }
    
    public JSONObject toJSON (){
        JSONObject result = new JSONObject();
        if (email != null && !email.isEmpty())
            result.put(FieldCollection.FIELD_EMAIL, email);
        if (password != null && !password.isEmpty())
            result.put(FieldCollection.FIELD_PASSWORD, password);
        if (name != null && !name.isEmpty())
            result.put(FieldCollection.FIELD_NAME, name);
        if (age > 0 && age < 150)
            result.put(FieldCollection.FIELD_AGE, age);
        return result;
    }
}
