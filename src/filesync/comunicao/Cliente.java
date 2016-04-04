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
public class Cliente {

    /**
     * @param args the command line arguments
     */
    private Reply resposta;
    private Request requisicao;
    private Socket cliente;
    
    
    public Cliente() {

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
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        MainScreen tela = new MainScreen();
        tela.setVisible(true);        
    }
}
