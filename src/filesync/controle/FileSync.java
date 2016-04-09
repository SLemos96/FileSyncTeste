/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.controle;

import filesync.screens.*;
import filesync.comunicao.*;
import filesync.persistencia.BDArquivo;
import filesync.persistencia.Usuario;
import java.io.File;

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
    private EscolhaDiretorio chooseDiretorio;
    private ArvoreDeArquivos arvoreDeArquivos;
    private ServerScreen telaServidor;
    
    public FileSync() {                
        this.autenticador = new AutenticadorUsuario(new BDArquivo());
        chooseDiretorio = new EscolhaDiretorio();        
        cliente = Cliente.getInstance();        
    }

    public int getPortaPadrao() {
        return portaPadrao;
    }
        
    public void iniciar() {
        new LoginScreen(this).setVisible(true);        
    }
    
    public void iniciarServidor(String serverName) {
        this.serverName = serverName;
        new ServerScreen(serverName);
    }
    
    public int getPorta() {
        return servidor.getPorta();
    }
            
    public boolean autenticarServidor(Usuario usuario, String serverName, int porta) {
        boolean sucesso = cliente.conectarServidor(usuario, serverName, porta);
        if (sucesso) {
            cliente.mostrarTelaPrincipal();
            cliente.exibirArquivosRemotos(usuario);
        } 
        return sucesso;
    }
        
    
    public boolean autenticarUsuario(String user, String senha) {
        boolean sucesso = autenticador.autenticarUsuario(user, senha);        
        return sucesso;
    }
    
    public void exibirDiretorio() {
        
    }
    
    
    public String escolherDiretorio() {
        return "";
    }
    
    public String escolherDiretorioServidor() {
        File raiz = chooseDiretorio.escolherDiretorio();
        arvoreDeArquivos = new ArvoreDeArquivos(raiz);
        return raiz.toString();
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
