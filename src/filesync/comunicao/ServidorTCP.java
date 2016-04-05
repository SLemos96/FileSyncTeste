/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;
import filesync.comunicao.Request;
import filesync.comunicao.Reply;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aguarda uma conexão, recebe objetos e envia objetos.
 * @author Francisco
 */
public class ServidorTCP {
    private ServerSocket serverSocket;
    private Socket server;
    
    public ServidorTCP(int porta){
        try {
            serverSocket = new ServerSocket(porta);       
        } catch (BindException e) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public ServidorTCP()  {
        try {
            serverSocket = new ServerSocket();
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    public void aguardarConexao() {
        System.out.println("Esperando cliente na porta:" +
                serverSocket.getLocalPort());
        try {
            server = serverSocket.accept();
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conexão estabelecida com " +
                server.getRemoteSocketAddress());
    }
    
    public void analisarRequisicao(Request requisicao) {
        
        
    }
    
    public void proverResposta(Reply resposta) {
        
    }
    
    public static void main(String[] args) {
        ServidorTCP server = new ServidorTCP(2689);
        server.aguardarConexao();
    }
}
