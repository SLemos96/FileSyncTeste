/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import java.io.*;
import java.net.*;
import filesync.comunicao.Request;
import filesync.comunicao.Reply;
import filesync.controle.AutenticadorUsuario;
import filesync.persistencia.BDArquivo;
import filesync.persistencia.IBancoDados;
import filesync.screens.MainScreen;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Comente as classes e seus métodos... Podem ser comentários simplistas
 * @author Reinaldo
 */
public class Cliente extends Thread{

    /**
     * @param args the command line arguments
     */
    private TipoRequisicao tipoRequisicao;
    private Reply resposta;
    private Request requisicao;
    private Socket cliente;
    private MainScreen telaPrincipal;
    
    public Cliente() {
        telaPrincipal = new MainScreen();
    }
    
    public void run() {
    }
    
    /**
     * 
     * @return true se a comunicação foi realizada, false caso contrario
     */
    public boolean conectarServidor(String serverName, int porta) {
        boolean sucesso = false;
        System.out.println("Conectando ao " + serverName + 
                " na porta " + porta);
        try {
            cliente = new Socket(serverName, porta);
            System.out.println("Conectado a " +
                cliente.getRemoteSocketAddress());
            sucesso = true;
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            sucesso = false;
        } finally {
            return sucesso;
        }
    }
    
    public boolean encerrarConexao() {
        boolean sucesso = false;
        try {
            cliente.close();
            sucesso = true;
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return sucesso;
        }
    }
    
    public void finalizarConexao() {
        try {
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarRequisicao(Request requisicao) {
        try {
            OutputStream saidaParaServidor = cliente.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(saidaParaServidor);
            oos.writeObject(requisicao);
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void receberResposta() {
        try {
            InputStream respostaServidor = cliente.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(respostaServidor);
            Reply resposta = (Reply) ois.readObject();
            
            ArvoreDeArquivos arvoreDeArquivos = (ArvoreDeArquivos) resposta.getObject();
            telaPrincipal.setRemoteTextArea(arvoreDeArquivos.listaDeArquivos());
            telaPrincipal.setPastaRemotaField(arvoreDeArquivos.getRaiz().toString());
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void analisarRequisicao(Request requisicao, Parametro parametro) {
        
    }
    
    public void proverResposta(Reply resposta) {
        
    }
    
    public byte[] serializarObjeto(Request requisicao) {
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
    
    public void mostrarTelaPrincipal() {
        telaPrincipal.setVisible(true);
    }
}