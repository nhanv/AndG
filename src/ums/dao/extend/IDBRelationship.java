/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.dao.extend;

import ums.dao.IDAO;
import ums.entity.Relationship;
import ums.result.extend.ListResult;

/**
 *
 * @author Nguyen Van Nha
 */
public interface IDBRelationship extends IDAO<Relationship> {
    public ListResult getIdFriendList (String uId);
}
