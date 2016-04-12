/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.controle;

import filesync.comunicao.Conexao;
import filesync.comunicao.Reply;
import filesync.comunicao.Request;
import filesync.comunicao.ServidorTCP;
import filesync.parametro.Arquivo;
import filesync.persistencia.ArvoreDeArquivos;
import filesync.persistencia.FileSystemModel;
import filesync.persistencia.Log;
import filesync.persistencia.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
public class GeradorDeResposta {
    
    private Conexao conexao;
    private Log logDoServidor;
    private AutenticadorUsuario autenticador;
    private Reply resposta;
    
    public GeradorDeResposta(Conexao conexao, Log logDoServidor, AutenticadorUsuario autenticador) {
        this.conexao = conexao;
        this.logDoServidor = logDoServidor;
        this.autenticador = autenticador;
    }        
    
    public void analisarRequisicao(Request requisicao) throws FileNotFoundException, IOException {        
        TipoRequisicao tipo;
        
        tipo = requisicao.getRequisicao();
        
        logDoServidor.escreverLogLine("o cliente requer " + tipo.getDescricao());
        
        if (TipoRequisicao.Download == tipo) {
            realizarUpload((Arquivo) requisicao.getParametro());
        } else if (TipoRequisicao.ExibirArquivos == tipo) {
            enviarDiretorioCliente((Usuario) requisicao.getParametro());
        } else if (TipoRequisicao.ObterListaArquivo == tipo) {
            enviarListaDeArquivos(tipo);
        } else if (TipoRequisicao.Upload == tipo) {
            realizarDownload((Arquivo) requisicao.getParametro());
        } else if (TipoRequisicao.Autenticacao == tipo) {
            verificarAutenticacao((Usuario) requisicao.getParametro());            
        } else if (TipoRequisicao.ObterEscolhaRemotaDiretorio == tipo) {
            enviarEscolhaDeDiretorioRemota((Arquivo) requisicao.getParametro());
        } else if (TipoRequisicao.ExibirArquivosRemotos == tipo) {
            enviarArvoreDeArquivosRemotos((Arquivo)requisicao.getParametro());
        } else if (TipoRequisicao.ExibirDiretoriosRemotos == tipo) {
            exibirDiretoriosRemotos((Arquivo) requisicao.getParametro());
        } else if (TipoRequisicao.BuscarArquivoRemoto == tipo) {
            enviarArquivo((Arquivo) requisicao.getParametro());
        }
    }
    
    public void enviarArquivo(Arquivo arquivo) {
        //Reconstrução de caminho
        String caminho = System.getProperty("user.home") +
                 arquivo.getCaminhoDeDestino();
        
        File arquivoAux = new File(caminho);
        
        if (arquivoAux == null)
            logDoServidor.escreverLogLine("arquivo: " + caminho + " não encontrado!");
        
        Arquivo arquivoLocal = new Arquivo(arquivoAux);
                
        arquivoLocal.preencherFilhos();
        
        resposta = new Reply(arquivoLocal);
        conexao.enviarResposta(resposta);
    }
    
    public void enviarArvoreDeArquivosRemotos(Arquivo arquivoName) {
        Reply resposta;
        ArvoreDeArquivos arvore;
        
        FileSystemModel fsm = new FileSystemModel(new File("\\"));
        
        resposta = new Reply(fsm, TipoRequisicao.ObterEscolhaRemotaDiretorio);
    }
    
    public void enviarEscolhaDeDiretorioRemota(Arquivo arquivo) {
        String diretorioDeDestino = arquivo.getCaminhoDeDestino();
        
        File arquivoLocal = new File(diretorioDeDestino);
                
        Arquivo diretorio = new Arquivo(arquivoLocal);
        
        diretorio.inicializarArvoreDeDiretorios(arquivoLocal);
        diretorio.criarArvoreDeDiretorioLocal(diretorioDeDestino, System.getProperty("file.separator"));
        
        resposta = new Reply(arquivoLocal);
        
        conexao.enviarResposta(resposta);        
    }
    
    public void enviarDiretorioCliente(Usuario usuario) {        
        /*
        Reply resposta;
        
        String usuarioNome = usuario.getDadosLogin().getNomeDeUsuario();
        
        ArvoreDeArquivos arvore = diretorioDosUsuarios.get(usuarioNome);
        resposta = new Reply(arvore, TipoRequisicao.ExibirArquivos);
        enviarResposta(resposta);*/
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
        
        resposta = new Reply(sucesso);
        conexao.enviarResposta(resposta);
    }
    
    public void criarPastaDoUsuario(String nomeUsuario) {        /*
        File raizUsuario = new File(pastaRaiz + File.separator + nomeUsuario);
        logDoServidor.escreverLogLine("criando diretorio de " + raizUsuario.getAbsolutePath());
        raizUsuario.mkdir();
        diretorioDosUsuarios.put(nomeUsuario, new ArvoreDeArquivos(raizUsuario));*/
    }             
    
    private void exibirDiretoriosRemotos(Arquivo arquivo) {
        File diretorioLocal = new File(arquivo.getNomeDoDestino());
        
        if (!diretorioLocal.exists()) {
            logDoServidor.escreverLogLine("diretorio " + 
                    diretorioLocal.getAbsolutePath() + " não encontrado.");
        }
        
        arquivo = new Arquivo(diretorioLocal);
        arquivo.criarArvoreDeDiretorio(diretorioLocal);
        
        logDoServidor.escreverLogLine("arvore de diretorios local: \n" + arquivo.listaArvoreDeDiretorios());
        
        resposta = new Reply(arquivo);
        
        conexao.enviarResposta(resposta);
        logDoServidor.escreverLogLine("Enviando arvore de diretorios do caminho local: " + arquivo);
    }    

    private void enviarListaDeArquivos(TipoRequisicao tipo) {
        File file = new File(System.getProperty("file.separator"));        
        FileOutputStream fos;
        ObjectOutputStream sai;
        
        byte b[] = new byte[4096];
        
        try {
            sai = new ObjectOutputStream(new FileOutputStream(file));
            sai.write(b);            
                                    
            Reply resposta = new Reply(b, tipo);
        
            conexao.enviarResposta(resposta);
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void realizarDownload(Arquivo arquivo) throws FileNotFoundException, IOException {        
        File novoArquivo;
        byte[] data;
        FileOutputStream fos;
        
        String caminhoDoArquivo = arquivo.getNomeDoDestino() + System.getProperty("file.separator") +
                arquivo;
       
        novoArquivo = new File(caminhoDoArquivo);        
                
        if (!novoArquivo.exists()) {
            novoArquivo.createNewFile();
        }                        
        
        fos = new FileOutputStream(novoArquivo);        
        
        data = arquivo.getData();        
        logDoServidor.escreverLogLine("realizando download do arquivo: " + arquivo.getNomeDoArquivo());
        try {
            
            fos.write(data);
            fos.flush();
            fos.close();
            resposta = new Reply(true);
            conexao.enviarResposta(resposta);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {            
            logDoServidor.escreverLogLine("Erro na abertura do arquivo");
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void realizarUpload(Arquivo arquivoRequerido) {
        //Reconstrução de caminho
        String caminho = System.getProperty("user.home") +
                 arquivoRequerido.getCaminhoDeDestino();
        
        int size;
        FileInputStream fis;
        File arquivoLocal;
        byte[] data;      
        
        arquivoLocal = new File(caminho);
                
        try {
            logDoServidor.escreverLogLine("realizando upload do arquivo: '" + arquivoRequerido.getCaminhoLocal()+"'");
            fis = new FileInputStream(arquivoLocal);
            size = fis.available();
            data = new byte[size];
            
            if (size > 0)
                while(fis.read(data) != -1);
            fis.close();
            resposta = new Reply(data);
            
            conexao.enviarResposta(resposta);
            
        } catch (IOException ex) {
            logDoServidor.escreverLogLine("Erro na abertura ou fechamento do arquivo '" + arquivoRequerido.getNomeDoDestino()+"'");
        } 
    }        
}
