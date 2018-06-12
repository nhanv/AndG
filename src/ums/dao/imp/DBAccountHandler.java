/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.dao.imp;

import ums.constant.MongoOperator;
import ums.constant.errorcode.UMSErrorCode;
import ums.dao.extend.IDBAcounnt;
import ums.entity.Relationship;
import ums.entity.User;
import ums.dao.checker.DataChecker;
import ums.result.Result;
import ums.result.extend.ListResult;
import ums.result.extend.StringResult;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;

/**
 *
 * @author Nguyen Van Nha
 */
public class DBAccountHandler implements IDBAcounnt {
    private final String DATABASE = "mydb";
    private final String COLLECTION_ACCOUNT = "account";
    private final String FIELD_ID = "_id";
    private final String FIELD_EMAIL = "email";
    private final String FIELD_PASSWORD = "password";
    private final String FIELD_NAME = "name";
    private final String FIELD_AGE = "age";
    
    private final String FIELD_TOKEN = "token";
    private final String FIELD_UID = "uid";
    
    private static DB db;
    private static DBCollection accountCollection;
    //private static DBTokenHandler tokenHandler;
    private static DBRelationshipHandler relationshipHandler;

    /**
     * open database and get collection
     * @param host
     * @param port 
     */
    public DBAccountHandler(String host, int port) {
        try {
            Mongo mongo = new Mongo (host, port);
            db = mongo.getDB(DATABASE);
            accountCollection = db.getCollection(COLLECTION_ACCOUNT);
            relationshipHandler = new DBRelationshipHandler(db);
            //tokenHandler = new DBTokenHandler(db);
            //create thread manage session
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
    
    /**
     * insert new account into collection
     * @param u
     * @return 
     */
    @Override
    public Result insertData(User u) {
        Result result = new Result ();
        int code = DataChecker.checkDataInsert(u.getEmail(), u.getPassword(), u.getName(), u.getAge());
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                BasicDBObject o = new BasicDBObject();
                o.put(FIELD_EMAIL, u.getEmail());
                DBObject findO = accountCollection.findOne(o);
                if (findO == null){
                    o.put(FIELD_PASSWORD, u.getPassword());
                    o.put(FIELD_NAME, u.getName());
                    o.put(FIELD_AGE, u.getAge());
                    
                    accountCollection.insert(o);
                    
                    result = new Result (UMSErrorCode.SUCCESSFULLY_CODE);
                }else {
                    result = new Result (UMSErrorCode.EMAIL_DUPLICATE);
                }
            }else{
                result = new Result (code);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return result;
    }

    /**
     * login with email and password registered 
     * @param u
     * @return session
     */
    @Override
    public StringResult login(User u) {
        StringResult result = new StringResult();
        int code = DataChecker.checkDataLogin(u.getEmail(), u.getPassword());
        //tokenHandler.removeAll();
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                BasicDBObject o = new BasicDBObject();
                o.put(FIELD_EMAIL, u.getEmail());
                DBObject findO = accountCollection.findOne(o);
                if (findO != null){
                    String pass = findO.get(FIELD_PASSWORD).toString();
                    if (pass.equals(u.getPassword())){
                        //get id and hashing
                        String id = findO.get(FIELD_ID).toString();
                        String token = UUID.randomUUID().toString();
                        JSONObject rs = new JSONObject();
                        rs.put(FIELD_TOKEN, token);
                        rs.put(FIELD_UID, id);

                        //return result
                        result = new StringResult(rs.toString(), UMSErrorCode.SUCCESSFULLY_CODE);
                    }else{
                        result = new StringResult(UMSErrorCode.WRONG_PASSWORD);
                    }
                }else{
                    result = new StringResult (UMSErrorCode.USER_NOT_FOUND);
                }
            }else {
                result = new StringResult (code);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * check user login
     * find friend by email
     * check in relationship
     * add friend or cancel
     * @param uId
     * @param u
     * @return 
     */
    @Override
    public Result addFriend (String uId, User u){
        Result result = new Result();
        int code = DataChecker.checkEmail(u.getEmail());
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                //get id user
                int codeId = DataChecker.checkId(uId);
                if ( codeId == UMSErrorCode.SUCCESSFULLY_CODE){
                    ObjectId oldU = new ObjectId (uId);
                    DBObject findOld = accountCollection.findOne(oldU);
                    String oldEmail = findOld.get(FIELD_EMAIL).toString();
                    //check other email
                    if (oldEmail.equals(u.getEmail())){
                        result = new Result(UMSErrorCode.EMAIL_DUPLICATE);
                    }else {
                        BasicDBObject findO = new BasicDBObject();
                        findO.put(FIELD_EMAIL, u.getEmail());
                        DBObject friend = accountCollection.findOne(findO);
                        //find friend by email
                        if (friend != null){
                            String fId = friend.get(FIELD_ID).toString();

                            ListResult idResult = relationshipHandler.getIdFriendList(uId);
                            //check in relationship
                            if (idResult.getCode() == UMSErrorCode.SUCCESSFULLY_CODE){
                                boolean check = false; // check relationship
                                String frId;
                                for (int i = 0; i < idResult.getListResult().size(); i++) {
                                    frId = idResult.getListResult().get(i).toString();
                                    if (frId.equals(fId)){
                                        check = true;
                                        break;
                                    }
                                }
                                //if in relationship
                                if (check){
                                    result = new Result (UMSErrorCode.INRELATIONSHIP);
                                }else{
                                    Relationship r = new Relationship(uId, fId);
                                    relationshipHandler.insertData(r);
                                    result = new Result (UMSErrorCode.SUCCESSFULLY_CODE);
                                }
                            }else {                        
                                Relationship r = new Relationship(uId, fId);
                                relationshipHandler.insertData(r);
                                result = new Result (UMSErrorCode.SUCCESSFULLY_CODE);
                            }
                        }else{
                            //if email want add friend not found
                            result = new Result (UMSErrorCode.USER_NOT_FOUND);
                        }
                    }
                }else{
                    result = new Result(codeId);
                }
            }else{
                result = new Result (code);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * get info account by session
     * @param uId
     * @return 
     */
    @Override
    public ListResult getInfo(String uId) {
        ListResult result = new ListResult ();
        int code = DataChecker.checkId(uId);
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                ObjectId u = new ObjectId(uId);
                DBObject user = accountCollection.findOne(u);
                if (user != null){
                    User uInfo = new User ();
                    uInfo.setEmail(user.get(FIELD_EMAIL).toString());
                    uInfo.setName(user.get(FIELD_NAME).toString());
                    uInfo.setAge(Integer.parseInt(user.get(FIELD_AGE).toString()));

                    List<User> list = new ArrayList<>();
                    list.add(uInfo);

                    result = new ListResult(list, UMSErrorCode.SUCCESSFULLY_CODE);                        
                }else{
                    result = new ListResult(UMSErrorCode.USER_NOT_FOUND);
                }
            }else{
                result = new ListResult(code);
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     *get list friend 
     * @param uId
     * @return 
     */
    @Override
    public ListResult getFriend (String uId){
        ListResult result = new ListResult();
        int code = DataChecker.checkId(uId);
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                //get friend list id
                ListResult idFrList = relationshipHandler.getIdFriendList(uId);
                if (idFrList.getCode() == UMSErrorCode.SUCCESSFULLY_CODE){
                    //get id friend list
                    List<String> idList = idFrList.getListResult(); //store friend list id

                    //get info friend list
                    List<User> frList = new ArrayList<>(); //store friend list info
                    ObjectId fr;
                    for (int i = 0; i < idList.size(); i++) {
                        fr = new ObjectId (idList.get(i).toString());
                        DBObject friend = accountCollection.findOne(fr);
                        if (friend != null){
                            User us = new User();
                            us.setEmail(friend.get(FIELD_EMAIL).toString());
                            us.setName(friend.get(FIELD_NAME).toString());
                            us.setAge(Integer.parseInt(friend.get(FIELD_AGE).toString()));

                            frList.add(us);
                        }
                    }

                    result = new ListResult(frList, UMSErrorCode.SUCCESSFULLY_CODE);
                }else{
                    result = new ListResult (idFrList.getCode());
                }
            }else{
                result = new ListResult(code);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * remove account follow email
     * @param u
     * @return 
     */
    @Override
    public Result removeData(User u) {
        Result result = new Result ();
        int code = DataChecker.checkEmail(u.getEmail());
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                BasicDBObject o = new BasicDBObject();
                o.put(FIELD_EMAIL, u.getEmail());
                DBObject findO = accountCollection.findOne(o);
                if (findO != null){
                    accountCollection.remove(findO);
                    result = new Result(UMSErrorCode.SUCCESSFULLY_CODE);
                }else{
                    result = new Result (UMSErrorCode.USER_NOT_FOUND);
                }
            }else{
                result = new Result(UMSErrorCode.INVAILABLE_EMAIL);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * get all account
     * @return 
     */
    @Override
    public StringResult getAllInfo() {
        StringResult result;
        StringBuilder str = new StringBuilder ();
        DBCursor cursor = accountCollection.find();
        if (cursor.hasNext()){
            while (cursor.hasNext()){
                str.append(cursor.next().toString());
                str.append("\n");
            }
            result = new StringResult(str.toString(), UMSErrorCode.SUCCESSFULLY_CODE);
        }else{
            result = new StringResult (str.toString(), UMSErrorCode.COLLECTION_ISEMPTY);
        }
        return result;
    }

    /**
     * remove all account
     * @return 
     */
    @Override
    public Result removeAll() {
        Result result;
        DBCursor cursor = accountCollection.find();
        if (cursor.hasNext()){
            while (cursor.hasNext()){
                accountCollection.remove(cursor.next());
            }
            result = new Result (UMSErrorCode.SUCCESSFULLY_CODE);
        }else{
            result = new Result (UMSErrorCode.COLLECTION_ISEMPTY);
        }        
        return result;
    }

    /**
     * change old password by new password not equal old password
     * @param uId
     * @param oldPass
     * @param newPass
     * @return 
     */
    @Override
    public Result changePass(String uId, String oldPass, String newPass) {
        Result result = new Result ();
        int code = DataChecker.checkChangePass(uId, oldPass, newPass);
        try {
            if (code == UMSErrorCode.SUCCESSFULLY_CODE){
                ObjectId oid = new ObjectId (uId);
                DBObject o = accountCollection.findOne(oid);
                if (o != null){
                    String pass = o.get(FIELD_PASSWORD).toString();
                    if (pass.equals(oldPass)){
                        BasicDBObject newDoc = new BasicDBObject();
                        newDoc.append(MongoOperator.SET_OPERATOR,
                                      new BasicDBObject().append(FIELD_PASSWORD,
                                                                 newPass));
                        accountCollection.update(o, newDoc);
                        result = new Result (UMSErrorCode.SUCCESSFULLY_CODE);
                    }else{
                        result = new Result (UMSErrorCode.WRONG_PASSWORD);
                    }
                }else {
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
    public StringResult getFriendInfo(String pToken, String frEmail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
