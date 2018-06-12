package ums.result.extend;


import ums.result.Result;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nguyen Van Nha
 */
public class StringResult extends Result{
    private String result;

    public StringResult() {
        super ();
        result = null;
    }

    public StringResult(int code) {
        super(code);
        result = null;
    }

    public StringResult(String list) {
        super ();
        this.result = list;
    }

    public StringResult(String list, int code) {
        super(code);
        this.result = list;
    }

    public String getResult() {
        return result;
    }    
}
