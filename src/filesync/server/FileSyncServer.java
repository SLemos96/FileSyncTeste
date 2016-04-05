/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.server;

import filesync.screens.MainScreenServer;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Reinaldo
 */
public class FileSyncServer {

    /**
     * @param args the command line arguments
     */
    
    
    Reply resposta;
    Request requisicao;
    
    
    public static void main(String[] args) throws SocketException, IOException {
        // TODO code application logic here
        
        MainScreenServer tela = new MainScreenServer();
        tela.setVisible(true);
        
        DatagramSocket server = new DatagramSocket(11000);
        
    }
    
}
