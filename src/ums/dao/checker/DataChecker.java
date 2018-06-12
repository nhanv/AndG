/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.dao.checker;

import ums.constant.errorcode.UMSErrorCode;

/**
 *
 * @author Nguyen Van Nha
 */
public class DataChecker {
    public static int checkDataLogin (String email, String pass){
        int result;
        if (email == null || email.isEmpty())
            result = UMSErrorCode.INVAILABLE_EMAIL;
        else if (pass == null || pass.isEmpty())
            result = UMSErrorCode.INVAILABLE_PASSWORD;
        else
            result = UMSErrorCode.SUCCESSFULLY_CODE;
        return result;
    }
    
    /**
     * check date begin insert
     * @param email
     * @param pass
     * @param name
     * @param age
     * @return 
     */
    public static int checkDataInsert (String email, String pass, String name, int age){
        int result;
        if (email == null || email.isEmpty())
            result = UMSErrorCode.INVAILABLE_EMAIL;
        else if (pass == null || pass.isEmpty())
            result = UMSErrorCode.INVAILABLE_PASSWORD;
        else if (name == null || name.isEmpty())
            result = UMSErrorCode.INVAILABLE_USERNAME;
        else if (age < 0 || age > 150)
            result = UMSErrorCode.INVAILABLE_AGE;
        else
            result = UMSErrorCode.SUCCESSFULLY_CODE;
        return result;
    }
    
    /**
     * check email
     * @param email
     * @return 
     */
    public static int checkEmail (String email){
        int result;
        if (email == null || email.isEmpty())
            result = UMSErrorCode.INVAILABLE_EMAIL;
        else
            result = UMSErrorCode.SUCCESSFULLY_CODE;
        
        return result;
    }
    
    /**
     * check token
     * @param id
     * @param token
     * @return 
     */
    public static int checkInsertToken (String id, String token){
        int result;
        if (id == null || id.isEmpty())
            result = UMSErrorCode.INVAILABLE_ID;
        else if (token == null || token.isEmpty())
            result = UMSErrorCode.TOKEN_ERROR;
        else
            result = UMSErrorCode.SUCCESSFULLY_CODE;
        return result;
    }
    
    /**
     * check data begin change password
     * @param uId
     * @param oldPass
     * @param newPass
     * @return 
     */
    public static int checkChangePass (String uId, String oldPass, String newPass){
        int result;
        if (uId == null|| uId.isEmpty())
            result = UMSErrorCode.TOKENNOTEXIST;
        else if (oldPass == null || oldPass.isEmpty())
            result = UMSErrorCode.INVAILABLE_PASSWORD;
        else if (newPass == null || newPass.isEmpty())
            result = UMSErrorCode.INVAILABLE_PASSWORD;
        else if (newPass.equals(oldPass))
            result = UMSErrorCode.PASSWORD_DUPLICATE;
        else
            result = UMSErrorCode.SUCCESSFULLY_CODE;
        
        return result;
    }
    
    public static int checkId (String id){
        int result;
        if (id == null || id.isEmpty())
            result = UMSErrorCode.TOKENNOTEXIST;
        else
            result = UMSErrorCode.SUCCESSFULLY_CODE;
        return result;
    }
    
    public static int checkInsertRelationship (String uId, String fId){
        int result;
        if (uId == null || uId.isEmpty())
            result = UMSErrorCode.INVAILABLE_ID;
        else if (fId == null || fId.isEmpty())
            result = UMSErrorCode.INVAILABLE_ID;
        else
            result = UMSErrorCode.SUCCESSFULLY_CODE;
        return result;
    }
}
