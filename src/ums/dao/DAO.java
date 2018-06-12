/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.dao;

import ums.dao.imp.DBAccountHandler;
import ums.handler.responsehandler.constant.DBParamer;

/**
 *
 * @author Nguyen Van Nha
 */
public class DAO {
    public static final DBAccountHandler AccountDAO = new DBAccountHandler(DBParamer.HOST,
                                                        DBParamer.MONGO_PORT);
}
