/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.controle;

import filesync.screens.*;
import filesync.comunicao.*;
import filesync.persistencia.BDArquivo;
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
    private MainScreen telaPrincipal;
    
    public FileSync() {
        telaPrincipal = new MainScreen();
        this.autenticador = new AutenticadorUsuario(new BDArquivo());
        chooseDiretorio = new EscolhaDiretorio();
        servidor = new ServidorTCP(telaPrincipal);
        cliente = new Cliente();        
    }

    public int getPortaPadrao() {
        return portaPadrao;
    }
        
    public void iniciar() {
        servidor.start();
        new LoginScreen(this).setVisible(true);
        
    }
    
    public int getPorta() {
        return servidor.getPorta();
    }
        
    
    public boolean autenticarServidor(String serverName, int porta) {
        boolean sucesso = cliente.conectarServidor(serverName, porta);
        if (sucesso) {            
            Request requisicao;
            requisicao = new Request(TipoRequisicao.ExibirArquivos, arvoreDeArquivos);
            cliente.enviarRequisicao(requisicao);
            servidor.receberRequisicao();
        } 
        return sucesso;
    }
    
    public boolean autenticarUsuario(String user, String senha) {
        boolean sucesso = autenticador.autenticarUsuario(user, senha);
        if (sucesso) {
            telaPrincipal.setPastaLocalField(arvoreDeArquivos.getRaiz().toString());
            telaPrincipal.setLocalTextArea(arvoreDeArquivos.listaDeArquivos());
            telaPrincipal.setVisible(true);
        } else {
            sucesso = false;
        }
        return sucesso;
    }            
    
    
    public String escolherDiretorio() {
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
