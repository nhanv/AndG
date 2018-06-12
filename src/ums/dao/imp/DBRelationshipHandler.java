/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.dao.imp;

import ums.constant.errorcode.UMSErrorCode;
import ums.dao.extend.IDBRelationship;
import ums.entity.Relationship;
import ums.dao.checker.DataChecker;
import ums.result.Result;
import ums.result.extend.ListResult;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Van Nha
 */
public class DBRelationshipHandler implements IDBRelationship{
    private final String COLLECTION_FRIENDLIST = "friendlist";
    
    private final String FIELD_IDUSER = "iduser";
    private final String FIELD_IDFRIEND = "idfriend";
    
    private static DBCollection friendListCollection;
    
    public DBRelationshipHandler (DB db){
        friendListCollection = db.getCollection(COLLECTION_FRIENDLIST);
    }

    @Override
    public ListResult getIdFriendList(String uId) {
        ListResult<String> result = new ListResult();
        int code = DataChecker.checkId(uId);
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put(FIELD_IDUSER, uId);
                DBCursor cursor = friendListCollection.find(whereQuery);
                if (cursor.hasNext()){
                    List<String> idList = new ArrayList<>(); //store friend list id
                    //get friend list id
                    while (cursor.hasNext()){
                        idList.add(cursor.next().get(FIELD_IDFRIEND).toString());
                    }
                    
                    result = new ListResult<>(idList, UMSErrorCode.SUCCESSFULLY_CODE);
                }else{
                    result = new ListResult<>(UMSErrorCode.LIST_ISEMPTY);
                }
            }else {
                result = new ListResult<>(code);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public Result insertData(Relationship u) {
        Result result = new Result ();
        int code = DataChecker.checkInsertRelationship(u.getuId(), u.getFrId());
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                BasicDBObject o = new BasicDBObject();
                o.put(FIELD_IDUSER, u.getuId());
                o.put(FIELD_IDFRIEND, u.getFrId());
                friendListCollection.insert(o);
                
                result = new Result (UMSErrorCode.SUCCESSFULLY_CODE);
            }else {
                result = new Result (code);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * remove data
     * @param u
     * @return 
     */
    @Override
    public Result removeData(Relationship u) {
        Result result = new Result ();
        int code = DataChecker.checkId(u.getuId());
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                BasicDBObject o = new BasicDBObject();
                o.put(FIELD_IDUSER, u.getuId());
                DBCursor cursor = friendListCollection.find(o);
                if (cursor.hasNext()){
                    while (cursor.hasNext()){
                        friendListCollection.remove(cursor.next());
                    }
                    result = new Result (UMSErrorCode.SUCCESSFULLY_CODE);
                }else{
                    result = new Result (UMSErrorCode.USER_NOT_FOUND);
                }
            }else{
                result = new Result (code);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result removeAll() {
        Result result = new Result ();
        try {
            DBCursor cursor = friendListCollection.find();
            if (cursor.hasNext()){
                while (cursor.hasNext()){
                    friendListCollection.remove(cursor.next());
                }
                result = new Result (UMSErrorCode.SUCCESSFULLY_CODE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
}
