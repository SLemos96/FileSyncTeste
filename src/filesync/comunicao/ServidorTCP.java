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

/**
 * Aguarda uma conexão, recebe objetos e envia objetos.
 * @author Francisco
 */
public class ServidorTCP extends Thread{
    private Log logDoServidor;
    private ServerSocket serverSocket;
    private Socket cliente;
    private Request requisicao;
    private ArvoreDeArquivos arvoreDeArquivos;
    
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
            this.arvoreDeArquivos = new ArvoreDeArquivos(new File(pastaRaiz));            
            serverSocket = new ServerSocket(0);
            logDoServidor = new Log(logTextPane,"Servidor: " + InetAddress.getLocalHost().getHostAddress() + "\n"
                    + "criando diretório em: " + pastaRaiz + "\n");
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
            new Conexao(cliente, logDoServidor, arvoreDeArquivos).start();
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    

    public void setArvoreDeArquivos(ArvoreDeArquivos arvoreDeArquivos) {
        this.arvoreDeArquivos = arvoreDeArquivos;
    }
}
