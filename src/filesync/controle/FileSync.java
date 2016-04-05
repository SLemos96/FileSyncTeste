/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.controle;

import filesync.screens.*;
import filesync.comunicao.Cliente;
import filesync.comunicao.ServidorTCP;
import filesync.persistencia.BDArquivo;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Inicializa as telas principais, o servidor e controla a comunicação entre
 * a interface grafica e o cliente.
 * @author Francisco
 */
public class FileSync {
    private String serverName;
    private final int portaPadrao = 2689;
    private ServidorTCP servidor;
    private Cliente cliente;
    private AutenticadorUsuario autenticador;
    
    public FileSync() {
        this.autenticador = new AutenticadorUsuario(new BDArquivo());
    }

    public int getPortaPadrao() {
        return portaPadrao;
    }
        
    public void iniciar() {
        new LoginScreen().setVisible(true);
    }
    
    public void iniciarServidor() {
        servidor = new ServidorTCP(portaPadrao);
        servidor.aguardarConexao();
    }
    
    public void iniciarServidor(int porta) {
        servidor = new ServidorTCP(porta);
    }
    
    public void iniciarCliente() {
        cliente = new Cliente();
    }
    
    public boolean autenticarServidor(String serverName, int porta) {
        return cliente.conectarServidor(serverName, porta);
    }
    
    public boolean autenticarUsuario(String user, String senha) {
        return autenticador.autenticarUsuario(user, senha);
    }            
    
    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FileSync().iniciar();
            }
        });

    }
}
