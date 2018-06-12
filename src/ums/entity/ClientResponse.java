/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.entity;

import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class ClientResponse {
    private JSONObject clientResponse;

    public ClientResponse() {
        this.clientResponse = null;
    }

    public ClientResponse(JSONObject clientResponse) {
        this.clientResponse = clientResponse;
    }

    public JSONObject getClientResponse() {
        return clientResponse;
    }
}
