/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.dao;

import ums.result.Result;
/**
 *
 * @author Nguyen Van Nha
 * @param <T>
 */
public interface IDAO <T> {
    public Result insertData (T u);
    public Result removeData (T u);
    public Result removeAll ();
}
