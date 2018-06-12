/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.result;

import ums.constant.errorcode.UMSErrorCode;

/**
 *
 * @author Nguyen Van Nha
 */
public class Result {
    private int code;

    public Result() {
        code = UMSErrorCode.UNKNOWN_CODE;
    }

    public Result(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }    
}
