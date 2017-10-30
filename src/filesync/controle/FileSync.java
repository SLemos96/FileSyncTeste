/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.controle;

import filesync.persistencia.ArvoreDeArquivos;
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
    private static final String nomePasta = System.getProperty("user.home") +
            System.getProperty("file.separator") + "FileSync" + System.getProperty("file.separator");
    private String serverName;
    private static int porta = 5800;
    private ServidorTCP servidor;
    private Cliente cliente;
    private AutenticadorUsuario autenticador;
    private EscolhaDiretorio chooseDiretorio;
    private ArvoreDeArquivos arvoreDeArquivos;
    private ServerScreen telaServidor;
    
    public FileSync() {                
        this.autenticador = new AutenticadorUsuario(new BDArquivo());
        chooseDiretorio = new EscolhaDiretorio();
    }
        
    public void iniciar() {
        new LoginScreen(this, porta).setVisible(true);        
    }        
    
    public void iniciarCliente() {        
        cliente  = new Cliente();        
    }
    
    public void iniciarTelaPrincipal() {
        cliente.mostrarTelaPrincipal();
    }
    
    public static String getNomePasta() {
        return nomePasta;
    }
    
    public static int getPorta() {
        return porta;
    }
    
    public void iniciarServidor(String serverName, String diretorioServidor) {
        this.serverName = serverName;
        new ServerScreen(serverName, porta, diretorioServidor);
    }    
            
    public int autenticarServidor(Usuario usuario, String serverName, int porta) {
        int opcao = cliente.conectarServidor(usuario, serverName, porta);/*
        if (opcao == 1) {
            cliente.mostrarTelaPrincipal();
            new File(nomePasta+System.getProperty("file.separator")+ 
                    "local" + System.getProperty("file.separator")).mkdir();
            //cliente.exibirArquivosRemotos(usuario);
        } */
        return opcao;
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
