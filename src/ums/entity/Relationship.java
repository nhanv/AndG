/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.entity;

/**
 *
 * @author Nguyen Van Nha
 */
public class Relationship {
    private String uId;
    private String frId;

    public Relationship() {
        uId = null;
        frId = null;
    }

    public Relationship(String uId, String frId) {
        this.uId = uId;
        this.frId = frId;
    }

    public String getuId() {
        return uId;
    }

    public String getFrId() {
        return frId;
    }
}
