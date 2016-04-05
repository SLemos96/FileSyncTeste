/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Reinaldo
 */
public class Reply implements Serializable{

    public Reply(File caminho, InetAddress ip, int port) {

        try {
            int size = (int)caminho.length();
            byte[] resposta = new byte[size];
            
            Socket sock = new Socket(ip, port);
            
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());

        } catch (FileNotFoundException ex) {
            System.out.println("arquivo não encontrado");
        } catch (IOException ex3) {
            System.out.println("falha ao enviar arquivo");
        }
        

    }

}
