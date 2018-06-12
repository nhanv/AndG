/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.dao.extend;

import ums.dao.IDAO;
import ums.entity.User;
import ums.result.Result;
import ums.result.extend.ListResult;
import ums.result.extend.StringResult;

/**
 *
 * @author Nguyen Van Nha
 */
public interface IDBAcounnt extends IDAO<User> {
    public StringResult login (User u);
    public Result addFriend (String uId, User u);
    public ListResult getInfo (String uId);
    public Result changePass (String uId, String oldPass, String newPass);
    public ListResult getFriend (String uId);
    public StringResult getFriendInfo (String uId, String frEmail);
    public StringResult getAllInfo ();
}
