/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums.handler;

import ums.entity.ClientResponse;
import ums.handler.responsehandler.Handler;
import ums.constant.errorcode.UMSErrorCode;
import ums.handler.requesthandler.request.impl.CodeRequest;
import ums.handler.datarequestchecker.DataRequestChecker;
import ums.handler.responsehandler.IHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * receive request from client
 * create response send back client
 * @author Nguyen Van Nha
 */
public class UMSHandler extends AbstractHandler{
    public static final String FIELD_RESPONSECODE = "responsecode";
    public  static final String FIELD_RESPONSE = "response";
    private static Handler handler = new Handler ();
    
    @Override
    public void handle(String string, Request rqst, HttpServletRequest hsr,
                       HttpServletResponse hsr1) throws IOException {
        //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet.");
        JSONObject result = new JSONObject();
        try{
            
            String request = getRequest(string, rqst);
            System.out.println("Request:" + request);
            
            if(request == null){
                result.put(FIELD_RESPONSECODE, UMSErrorCode.CONNECT_ERROR);
            }else{
                CodeRequest c = new CodeRequest();
                int code = ((CodeRequest)c.createRequest(request)).getCode();
                int checkCode = DataRequestChecker.checkCodeRequest(code);
                if(checkCode == UMSErrorCode.SUCCESSFULLY_CODE){
                    IHandler dispatcher = handler.getHandler(code);
                    ClientResponse clientRespond = dispatcher.execute(request);
                    if(clientRespond == null)
                        result.put(FIELD_RESPONSECODE, UMSErrorCode.CONNECT_ERROR);
                    else
                        result = clientRespond.getClientResponse();
                }else{
                    result.put(FIELD_RESPONSECODE, UMSErrorCode.CONNECT_ERROR);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            result.put(FIELD_RESPONSECODE, UMSErrorCode.CONNECT_ERROR);
        }
        
        hsr1.setContentType("text/html;charset=UTF-8");
        try{
            sendDataBack(result.toJSONString(), hsr1);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
 
    private static void sendDataBack(String content, HttpServletResponse respond) throws Exception{
        OutputStream out = respond.getOutputStream();
        out.write(content.getBytes());
        out.flush();
        out.close();        
    }
    
    /**
     * get request from client
     * @param string
     * @param request
     * @return
     * @throws Exception 
     */
    private static String getRequest(String string, Request request) throws Exception{
        String result = null;
        
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        JSONParser jsp = new JSONParser();
        JSONObject jso = (JSONObject) jsp.parse(br);
        
        String postString = jso.toString();
        String getString  = string.substring(1);
        if(!getString.isEmpty() || postString != null){
            if(!getString.isEmpty() && postString != null){
                return null;
            }else if(!getString.isEmpty()){
                result = getString;
            }else if(postString != null){
                result = postString;
            }else {
                return null;
            }
        }
        return result;
    }
}
