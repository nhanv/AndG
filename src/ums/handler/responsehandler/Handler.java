/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler.responsehandler;

import ums.constant.connectcode.ClientRequestCode;
import ums.handler.responsehandler.impl.AddFriendHandler;
import ums.handler.responsehandler.impl.ChangePasswordHandler;
import ums.handler.responsehandler.impl.LoginHandler;
import ums.handler.responsehandler.impl.RegisterHandler;
import ums.handler.responsehandler.impl.ViewAllAccount;
import ums.handler.responsehandler.impl.ViewInfoHandler;
import ums.handler.responsehandler.impl.ViewFriendListHandler;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nguyen Van Nha
 */
public class Handler {
    private static Map<Integer, IHandler> handler = new HashMap<>();
    
    public Handler (){
        init();
    }
    
    public static final void init (){
        handler.put(ClientRequestCode.REGISTER_CODE, new RegisterHandler());
        handler.put(ClientRequestCode.LOGIN_CODE, new LoginHandler());
        handler.put(ClientRequestCode.ADDFRIEND_CODE, new AddFriendHandler());
        handler.put(ClientRequestCode.GETINFO_CODE, new ViewInfoHandler());
        handler.put(ClientRequestCode.CHANGEPASSWORD_CODE, new ChangePasswordHandler());
        handler.put(ClientRequestCode.GETFRIENDLIST_CODE, new ViewFriendListHandler());
        
        handler.put(ClientRequestCode.VIEWALLACCOUNT, new ViewAllAccount());
    }

    /**
     * handler request from client and response
     * @param code
     * @return 
     */
    public IHandler getHandler (int code) {
        IHandler result;
        result = handler.get(code);
        //handler.get(ClientRequestCode.VIEWALLACCOUNT).execute();
        return result;
    }
}
