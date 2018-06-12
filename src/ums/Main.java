/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ums;

import ums.handler.UMSHandler;
import org.eclipse.jetty.server.Server;

/**
 *
 * @author Nguyen Van Nha
 */
public class Main {
    private static final int SERVER_PORT = 8083;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            Server server = new Server (SERVER_PORT);
            server.setHandler(new UMSHandler());
            server.start();
            server.join();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
