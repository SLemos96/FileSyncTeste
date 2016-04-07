/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;
import filesync.comunicao.Request;
import filesync.comunicao.Reply;
import filesync.persistencia.Log;
import filesync.screens.MainScreen;
import filesync.screens.ServerScreen;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import java.util.HashMap;
/**
 * Aguarda uma conexão, recebe objetos e envia objetos, armazena arvore de diretorios
 * @author Francisco
 */
public class ServidorTCP extends Thread{
    private String diretorioRaiz;
    private Log logDoServidor;
    private ServerSocket serverSocket;
    private Socket cliente;
    private Request requisicao;
    private HashMap<String, ArvoreDeArquivos> diretorioDosUsuarios;
    
    public ServidorTCP(int porta){
        try {
            serverSocket = new ServerSocket(porta);       
        } catch (BindException e) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    public ServidorTCP(JTextPane logTextPane, String pastaRaiz)  {
        try {
            this.diretorioRaiz = pastaRaiz;
            this.diretorioDosUsuarios = new HashMap<String, ArvoreDeArquivos>();            
            serverSocket = new ServerSocket(0);
            logDoServidor = new Log(logTextPane,"Servidor: " + InetAddress.getLocalHost().getHostAddress() + "\n"
                    + "criando diretório em: " + pastaRaiz + "\n");
            new File(pastaRaiz).mkdir();
            logTextPane.setText(logDoServidor.getLog());
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escreverLog(String mensagem) {
        logDoServidor.escreverLogLine(mensagem);        
    }
    
    public void run() {
        while (true) {
            aguardarConexao();
        }                
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    public int getPorta() {
        return serverSocket.getLocalPort();
    }
    
    public void aguardarConexao() {        
        escreverLog("Esperando cliente na porta: " +
                serverSocket.getLocalPort());
        try {
            cliente = serverSocket.accept();
            escreverLog("Conexão estabelecida com " +
                cliente.getRemoteSocketAddress());
            Thread conexao = new Conexao(cliente, logDoServidor, diretorioDosUsuarios, diretorioRaiz);
            conexao.start();
            conexao.join();                                    
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }    
}
