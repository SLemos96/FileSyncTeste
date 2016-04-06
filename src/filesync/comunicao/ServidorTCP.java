/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;
import filesync.comunicao.Request;
import filesync.comunicao.Reply;
import filesync.screens.MainScreen;
import filesync.screens.ServerScreen;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aguarda uma conexão, recebe objetos e envia objetos.
 * @author Francisco
 */
public class ServidorTCP extends Thread{
    private String mensagemServidor;
    private ServerSocket serverSocket;
    private Socket server;
    private Request requisicao;
    private ArvoreDeArquivos arvoreDeArquivos;
    private ServerScreen telaServidor;
    
    public ServidorTCP(int porta){
        try {
            serverSocket = new ServerSocket(porta);       
        } catch (BindException e) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    public ServidorTCP(ServerScreen telaServidor, String pastaRaiz)  {
        try {
            this.arvoreDeArquivos = new ArvoreDeArquivos(new File(pastaRaiz));
            this.telaServidor = telaServidor;
            this.telaServidor.setTitle("Log do servidor");
            this.telaServidor.setVisible(true);
            serverSocket = new ServerSocket(0);
            mensagemServidor = "Servidor: " + serverSocket.getInetAddress().getLocalHost().getHostAddress() + "\n";
            escreverLog();
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escreverLog() {
        telaServidor.setLogTextPane(mensagemServidor);
    }
    public void run() {
        aguardarConexao();
        while(true) {
            receberRequisicao();
        }
        
    }

    public String getMensagemServidor() {
        return mensagemServidor;
    }

    public void setMensagemServidor(String mensagemServidor) {
        this.mensagemServidor = mensagemServidor;
    }    
    
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    public int getPorta() {
        return serverSocket.getLocalPort();
    }
    
    public void aguardarConexao() {
        mensagemServidor += "Esperando cliente na porta: " +
                serverSocket.getLocalPort() + "\n";
        escreverLog();
        try {
            server = serverSocket.accept();
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        mensagemServidor += "Conexão estabelecida com " +
                server.getRemoteSocketAddress() + "\n";
        escreverLog();
    }
    
    public void receberRequisicao() {
        try {
            mensagemServidor += "Recebendo requisicao...\n";
            escreverLog();
            DataInputStream dis = new DataInputStream(server.getInputStream());
            ObjectInputStream oos = new ObjectInputStream(dis);
            Request requisicao = (Request) oos.readObject();
            analisarRequisicao(requisicao);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            
        }
    }
    
    public void analisarRequisicao(Request requisicao) {
        mensagemServidor += "analisando requisicao...\n";
        TipoRequisicao tipo = requisicao.getRequisicao();
        if (TipoRequisicao.Download == tipo) {
            System.out.println("d");
        } else if (TipoRequisicao.ExibirArquivos == tipo) {
            exibirArquivosParaCliente();
        } else if (TipoRequisicao.ObterListaArquivo == tipo) {
            System.out.println("o");
        } else if (TipoRequisicao.Upload == tipo) {
            System.out.println("u");
        }        
    }
    
    public void exibirArquivosParaCliente() {
        mensagemServidor += "cliente requer lista de arquivos no servidor\n";
        escreverLog();
        
        Reply resposta = new Reply(arvoreDeArquivos, TipoRequisicao.ObterListaArquivo);
        proverResposta(resposta);
    }
    
    public byte[] serializarObjeto(Object requisicao) {
        try {
           ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream ous;
            ous = new ObjectOutputStream(bao);
            ous.writeObject(requisicao);
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void proverResposta(Reply resposta) {
        try {
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(resposta);            
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setArvoreDeArquivos(ArvoreDeArquivos arvoreDeArquivos) {
        this.arvoreDeArquivos = arvoreDeArquivos;
    }
}
