/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler;

import ums.entity.ClientResponse;

/**
 *
 * @author Nguyen Van Nha
 */
public interface IHandler {
    public ClientResponse execute (String request);
}
