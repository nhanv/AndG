/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.datarequestchecker;

import ums.constant.errorcode.UMSErrorCode;


/**
 *
 * @author Nguyen Van Nha
 */
public class DataRequestChecker {
    private static final int STCODE = 1;
    private static final int ENDCODE = 9;
    /**
     * check request code begin handler request
     * @param code
     * @return 
     */
    public static int checkCodeRequest (int code){
        int result;
        if (code < STCODE || code > ENDCODE)
            result = UMSErrorCode.UNKNOWN_CODE;
        else
            result = UMSErrorCode.SUCCESSFULLY_CODE;
        return result;
    }
}
