/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import filesync.controle.AutenticadorUsuario;
import filesync.persistencia.BDArquivo;
import filesync.persistencia.DadosLogin;
import filesync.persistencia.Log;
import filesync.persistencia.Usuario;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janioxavier
 */
public class Conexao extends Thread{
    
    private AutenticadorUsuario autenticador;
    private ArvoreDeArquivos arvoreArquivos;
    private Log logDoServidor;
    private ObjectInputStream entra;
    private ObjectOutputStream sai;
    Socket cliente;
    
    public Conexao(Socket socket, Log logDoServidor, ArvoreDeArquivos arvoreArquivos) {
        this.autenticador = new AutenticadorUsuario(new BDArquivo());
        this.cliente = socket;
        this.logDoServidor = logDoServidor;
        this.arvoreArquivos = arvoreArquivos;
        
        try {
            entra = new ObjectInputStream(cliente.getInputStream());
            sai = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    public void run() {
        receberRequisicao();
    }
    
    public void receberRequisicao() {
        try {                        
            Request requisicao = (Request) entra.readObject();
            analisarRequisicao(requisicao);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            
        }
    }
    
    public void analisarRequisicao(Request requisicao) {        
        
        TipoRequisicao tipo = requisicao.getRequisicao();
        if (TipoRequisicao.Download == tipo) {
            System.out.println("d");
        } else if (TipoRequisicao.ExibirArquivos == tipo) {
            enviarArvoreDeArquivosParaCliente();
        } else if (TipoRequisicao.ObterListaArquivo == tipo) {
            System.out.println("o");
        } else if (TipoRequisicao.Upload == tipo) {
            System.out.println("u");
        } else if (TipoRequisicao.Autenticacao == tipo) {
            verificarAutenticacao((Usuario) requisicao.getParametro());
            
        }
    }
    
    public void enviarArvoreDeArquivosParaCliente() {        
        logDoServidor.escreverLog("cliente requer lista de arquivos no servidor\n");
        
        Reply resposta = new Reply(arvoreArquivos, TipoRequisicao.ExibirArquivos);
        enviarResposta(resposta);
    }    
    
    public void verificarAutenticacao(Usuario usuario) {
        boolean sucesso;
        String senha;
        String nomeUsuario;
        
        nomeUsuario = usuario.getDadosLogin().getSenha();        
        senha = usuario.getDadosLogin().getSenha();
        
        logDoServidor.escreverLogLine("autenticando usuario: " + nomeUsuario);
        
        if (sucesso = autenticador.autenticarUsuario(nomeUsuario, senha))
            logDoServidor.escreverLogLine(nomeUsuario + " esta conectado");
        else
            logDoServidor.escreverLogLine(nomeUsuario + " não está conectado");
        
        Reply resposta = new Reply(sucesso, TipoRequisicao.Autenticacao);
        enviarResposta(resposta);
    }
    
    
    public void enviarResposta(Reply resposta) {
        try {
            sai.writeObject(resposta);
 
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
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
}
