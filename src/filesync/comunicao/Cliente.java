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
import filesync.screens.EscolhaDiretorio;
import filesync.persistencia.BDArquivo;
import filesync.persistencia.DadosLogin;
import filesync.persistencia.IBancoDados;
import filesync.persistencia.Usuario;
import filesync.screens.MainScreen;
import filesync.screens.ServerScreen;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Comente as classes e seus métodos... Podem ser comentários simplistas
 * @author Reinaldo
 */
public class Cliente {
    private final String fs = System.getProperty("file.separator");
    private final String raizCliente = "FileSync";
    private final String usuariosRemotos = "usuários_remotos";
    private final String caminhoRaizCliente = System.getProperty("user.home") + fs + raizCliente;
    private final String caminhoUsuariosRemotos = caminhoRaizCliente + fs + usuariosRemotos + fs;
    private int buffer_size = 1048576;  // 2 megabytes
    private boolean conectadoServidor;
    private TipoRequisicao tipoRequisicao;
    private Reply resposta;
    private Request requisicao;
    private Socket cliente;
    private MainScreen telaPrincipal;
    private ArvoreDeArquivos arvoreDeArquivosRemota;
    private String serverName;
    private int porta;
    private static Cliente instance;
    private boolean recebeuFSMRemoto;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    private Cliente() {                    
    }
    
    public static Cliente getInstance() {
        if (instance == null) {
            inicializaInstancia();
        }
        return instance;
    }
    
    private static synchronized void inicializaInstancia() {
        if (instance == null) {
            instance = new Cliente();
        }
    }
    
    public Cliente(String serverName, int porta) throws IOException {
        this.serverName = serverName;
        this.porta = porta;                    
        new File(System.getProperty("user.home") + fs + raizCliente).mkdir();
        new File(System.getProperty("user.home") + fs + raizCliente + fs + usuariosRemotos).mkdir();
    }                
    
    /**
     * 
     * @return true se a comunicação foi realizada e o usuario foi conectado, false caso contrario
     */
    public boolean conectarServidor(Usuario user, String serverName, int porta) {
        
        this.serverName = serverName;
        this.porta = porta;
        System.out.println("Conectando ao " + serverName + 
                " na porta " + porta);
        
        realizarAutenticacao(user);
        
        System.out.println("Conectado a " +
            cliente.getRemoteSocketAddress());
        
        return conectadoServidor;        
    }
    
    public boolean realizarAutenticacao(Usuario user) {
        requisicao = new Request(TipoRequisicao.Autenticacao, user); 
        
        enviarRequisicao();
        receberResposta();                
        
        return resposta.getSucesso();
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
    
    public void enviarRequisicao() {        
        try {
            cliente = new Socket(serverName, porta);
            OutputStream saidaParaServidor = cliente.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(saidaParaServidor);
            oos.writeObject(requisicao);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }                    
    }
    
    public boolean realizarUpload(Arquivo arquivoLocal) {        
        int size;
        byte[] data;
        FileInputStream fis;                                
        
        try {
            fis = new FileInputStream(arquivoLocal.getArquivo());
            size = fis.available();            
            data = new byte[fis.available()];
            
            if (size > 0)
                while(fis.read(data) != -1);
            
            arquivoLocal.setData(data);
            
            requisicao = new Request(TipoRequisicao.Upload, arquivoLocal);        
            enviarRequisicao();                                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {                                
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }                
        receberResposta();                
        
        return resposta.getSucesso();
    }
    
    public void realizarDownload(Arquivo arquivoRemoto) {
        File arquivoLocal;
        byte[] data;                
         
        requisicao = new Request(TipoRequisicao.Download, arquivoRemoto);        
        enviarRequisicao();
        receberResposta();
                
        arquivoLocal = new File(arquivoRemoto.getNomeDoDestino() + fs + arquivoRemoto);
        
        data = resposta.getBytes();
        
        try (FileOutputStream fos = new FileOutputStream(arquivoLocal)) {            
            fos.write(data);
            fos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }                        
    }
    
    public boolean acessarPastaRemota() {        
        requisicao = new Request(TipoRequisicao.ObterEscolhaRemotaDiretorio, null);
        
        enviarRequisicao();
        receberResposta();
        
        return recebeuFSMRemoto;
    }
    
    public void obterListaDeArquivos() {
        requisicao = new Request(TipoRequisicao.ObterListaArquivo, null);
        
        enviarRequisicao();
        receberResposta();
    }
    
    public void receberResposta() {
        try {
            InputStream respostaServidor = cliente.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(respostaServidor);
            resposta = (Reply) ois.readObject();
            
            //analisarResposta(resposta);
            //encerrarConexao();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    
    
    public void analisarResposta(Reply resposta) {
        
        TipoRequisicao tipo = resposta.getResposta();
        
        if (TipoRequisicao.Download == tipo) {
            System.out.println("d");
        } else if (TipoRequisicao.ExibirArquivosRemotos == tipo) {
            exibirArvoreDeArquivosRemoto((FileSystemModel) resposta.getObject());
        } else if (TipoRequisicao.ObterListaArquivo == tipo) {
            receberArquivosRemotos(resposta.getBytes());
        } else if (TipoRequisicao.Upload == tipo) {
            System.out.println("u");
        } else if (TipoRequisicao.Autenticacao == tipo) {
            conectadoServidor =  resposta.isSucesso();
        } else if(TipoRequisicao.ObterEscolhaRemotaDiretorio == tipo) {
            exibirArvoreDeArquivosRemoto((FileSystemModel) resposta.getObject());
        }
    }
    
    
    public void exibirArvoreDeArquivosRemoto(FileSystemModel arvoreDeArquivosRemoto) {
        if (arvoreDeArquivosRemoto != null) {
            telaPrincipal.setFSMRemoto(arvoreDeArquivosRemoto);
            recebeuFSMRemoto = true;
        } else {
            recebeuFSMRemoto = false;
        }
    }
    
    public void escolherDiretorioRemoto(EscolhaDiretorio diretorioRemoto) {
        /*String raizDiretorioRemoto;
        
        raizDiretorioRemoto = diretorioRemoto.escolherDiretorio().getPath();
        telaPrincipal.setPastaRemotaField(raizDiretorioRemoto);*/
    }
    
    public void exibirArquivosRemotos(Usuario usuario) {

        requisicao = new Request(TipoRequisicao.ExibirArquivos, usuario);
        enviarRequisicao();
         
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

    private void exibirArquivosParaCliente(FileSystemModel arvoreDeArquivos, boolean isAreaRemota) {        
        telaPrincipal.mostrarPainelDiretorio(arvoreDeArquivos, isAreaRemota);
    }        

    public void sincronizarArquivos() {
        File diretorioDestino = telaPrincipal.getRaizLocal();
        
        LinkedList<File> arquivos = telaPrincipal.arquivosFilhos(arvoreDeArquivosRemota);
        
        
        while(!arquivos.isEmpty()) {
            File arquivo = arquivos.pollLast();
            
            File novoArquivo = new File(diretorioDestino, arquivo.getName());
            
            /*
            if(!diretorioDestino.exists()) {
                diretorioDestino.mkdir();
            }
            arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));*/
            /* CRIAR ARQUIVO COM ESTE NOME*/
            System.out.println(novoArquivo);
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    
    public Object desserializarObjeto(byte[] objeto) {
        ByteArrayInputStream in;        
        ObjectInputStream is;
        
        try {
            in = new ByteArrayInputStream(objeto);
            is = new ObjectInputStream(in);
            return is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }
    
    private ObjectInputStream ObjectInputStream(InputStream respostaServidor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void receberArquivosRemotos(byte[] arquivo) {
        
    }

    public String getCaminhoRaizCliente() {
        return caminhoRaizCliente;
    }

    public String getCaminhoUsuariosRemotos() {
        return caminhoUsuariosRemotos;
    }
    
    public static void main(String[] args) throws IOException {
        File local;
        String nomeDoArquivoDeDownload;
        Arquivo arquivoDeDownload;
        String nomeDoArquivoDeUpload;
        int porta = ServerScreen.porta;                
        
        Cliente c = new Cliente("localhost", porta);
        
        Usuario user = new Usuario(new DadosLogin("admin", "admin"));        
                
        c.conectarServidor(user, "localhost", porta);
        
        /* Realizar download */
        local = new File(c.caminhoUsuariosRemotos);
        
        nomeDoArquivoDeDownload = "C:\\Users\\Francisco\\Documents\\Nova Pasta\\";
        
        arquivoDeDownload = new Arquivo(new File(nomeDoArquivoDeDownload));
        
        File[] arquivos = arquivoDeDownload.getArquivo().listFiles();
        for (File file : arquivos) {
            if (file.isFile()) {
                arquivoDeDownload = new Arquivo(file);
                arquivoDeDownload.setNomeDoDestino(c.caminhoUsuariosRemotos);
                c.realizarDownload(arquivoDeDownload);
            }
        }
        
        /* Realizar upload */
        nomeDoArquivoDeUpload = "C:\\Users\\Francisco\\Documents\\Servidor\\doServidor.odt";
        
        Arquivo arquivoDeUpload = new Arquivo(new File(nomeDoArquivoDeUpload));
        arquivoDeUpload.setNomeDoDestino("C:\\Users\\Francisco\\");
        
        c.realizarUpload(arquivoDeUpload);
        
        
    }
    
}