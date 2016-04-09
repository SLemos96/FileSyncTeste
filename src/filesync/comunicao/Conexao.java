/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import filesync.controle.AutenticadorUsuario;
import filesync.screens.EscolhaDiretorio;
import filesync.persistencia.BDArquivo;
import filesync.persistencia.DadosLogin;
import filesync.persistencia.Log;
import filesync.persistencia.Usuario;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janioxavier
 */
public class Conexao extends Thread{
    
    private String pastaRaiz;
    private AutenticadorUsuario autenticador;
    private HashMap<String, ArvoreDeArquivos> diretorioDosUsuarios;
    private Log logDoServidor;
    private ObjectInputStream entra;
    private ObjectOutputStream sai;
    Socket cliente;
    
    public Conexao(Socket socket, Log logDoServidor,
            HashMap<String,ArvoreDeArquivos> arvoreArquivos, String pastaRaiz) {
        this.pastaRaiz = pastaRaiz;
        this.autenticador = new AutenticadorUsuario(new BDArquivo());
        this.cliente = socket;
        this.logDoServidor = logDoServidor;
        this.diretorioDosUsuarios = arvoreArquivos;
        
        try {
            entra = new ObjectInputStream(cliente.getInputStream());
            sai = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    public void run() {
        receberRequisicao();
        try {
            encerrar();
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void receberRequisicao() {
        try {                        
            logDoServidor.escreverLogLine("Recebendo requisicao...");
            
            Request requisicao = (Request) entra.readObject();            
            analisarRequisicao(requisicao);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            
        }
    }
    
    public void encerrar() throws IOException {
        entra.close();
        sai.close();
    }
    
    public void analisarRequisicao(Request requisicao) {        
        TipoRequisicao tipo;
        
        tipo = requisicao.getRequisicao();
        
        logDoServidor.escreverLogLine("o cliente requer " + tipo.getDescricao());
        
        if (TipoRequisicao.Download == tipo) {
            System.out.println("d");
        } else if (TipoRequisicao.ExibirArquivos == tipo) {
            enviarDiretorioCliente((Usuario) requisicao.getParametro());
        } else if (TipoRequisicao.ObterListaArquivo == tipo) {
            System.out.println("o");
        } else if (TipoRequisicao.Upload == tipo) {
            System.out.println("u");
        } else if (TipoRequisicao.Autenticacao == tipo) {
            verificarAutenticacao((Usuario) requisicao.getParametro());            
        } else if (TipoRequisicao.ObterEscolhaRemotaDiretorio == tipo) {
            enviarEscolhaDeDiretorioRemota(tipo);
        } else if (TipoRequisicao.ExibirArquivosRemotos == tipo) {
            enviarArvoreDeArquivosRemotos((NomeDoArquivo)requisicao.getParametro());
        }
    }
    
    public void enviarArvoreDeArquivosRemotos(NomeDoArquivo arquivoName) {
        Reply resposta;
        ArvoreDeArquivos arvore;
        
        FileSystemModel fsm = new FileSystemModel(new File(System.getProperty("file.separator")));
        
        resposta = new Reply(fsm, TipoRequisicao.ObterEscolhaRemotaDiretorio);
    }
    
    public void enviarEscolhaDeDiretorioRemota(TipoRequisicao tipo) {
        Reply resposta;
        
        FileSystemModel fsm = new FileSystemModel(new File(System.getProperty("file.separator")));
        resposta = new Reply(fsm, tipo);
        
        enviarResposta(resposta);        
    }
    
    public void enviarDiretorioCliente(Usuario usuario) {        
        
        Reply resposta;
        
        String usuarioNome = usuario.getDadosLogin().getNomeDeUsuario();
        
        ArvoreDeArquivos arvore = diretorioDosUsuarios.get(usuarioNome);
        resposta = new Reply(arvore, TipoRequisicao.ExibirArquivos);
        enviarResposta(resposta);
    }    
    
    public void verificarAutenticacao(Usuario usuario) {
        boolean sucesso;
        String senha;
        String nomeUsuario;
        
        nomeUsuario = usuario.getDadosLogin().getNomeDeUsuario();        
        senha = usuario.getDadosLogin().getSenha();
        
        logDoServidor.escreverLogLine("autenticando usuario: " + nomeUsuario);
        
        if (sucesso = autenticador.autenticarUsuario(nomeUsuario, senha)) {
            logDoServidor.escreverLogLine(nomeUsuario + " esta conectado");
            //criarPastaDoUsuario(nomeUsuario);
        }
        else
            logDoServidor.escreverLogLine(nomeUsuario + " não está conectado");
        
        Reply resposta = new Reply(sucesso, TipoRequisicao.Autenticacao);
        enviarResposta(resposta);
    }
    
    public void criarPastaDoUsuario(String nomeUsuario) {        
        File raizUsuario = new File(pastaRaiz + File.separator + nomeUsuario);
        logDoServidor.escreverLogLine("criando diretorio de " + raizUsuario.getAbsolutePath());
        raizUsuario.mkdir();
        diretorioDosUsuarios.put(nomeUsuario, new ArvoreDeArquivos(raizUsuario));
    }
    
    public void enviarResposta(Reply resposta) {
        try {
            logDoServidor.escreverLogLine("servidor enviando resposta para " 
                    + resposta.getResposta().getDescricao());            
            sai.writeObject(resposta);
            sai.flush();
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
