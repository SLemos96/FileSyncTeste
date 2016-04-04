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
        servidor = new ServidorTCP(portaPadrao);
        cliente = new Cliente();
    }

    public int getPortaPadrao() {
        return portaPadrao;
    }
        
    public void iniciar() {
        new LoginScreen().setVisible(true);
        servidor.aguardarConexao();        
    }
    
    public boolean autenticarServidor(String serverName, int porta) {
        return cliente.conectarServidor(serverName, porta);
    }
    
    public boolean autenticarUsuario(String user, String senha) {
        return autenticador.autenticarUsuario(user, senha);
    }            
    
    public static void main(String[] args) {
        FileSync gui = new FileSync();
        gui.iniciar();
    }
}
