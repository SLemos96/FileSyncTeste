/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import filesync.controle.TipoRequisicao;
import filesync.parametro.Arquivo;
import filesync.parametro.Parametro;
import filesync.persistencia.FileSystemModel;
import java.io.*;
import java.net.*;
import filesync.comunicao.Request;
import filesync.comunicao.Reply;
import filesync.controle.AutenticadorUsuario;
import filesync.controle.FileSync;
import filesync.screens.EscolhaDiretorio;
import filesync.persistencia.BDArquivo;
import filesync.persistencia.DadosLogin;
import filesync.persistencia.IBancoDados;
import filesync.persistencia.Usuario;
import filesync.screens.MainScreen;
import filesync.screens.ServerScreen;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Comente as classes e seus métodos... Podem ser comentários simplistas
 * @author Reinaldo
 */
public class Cliente {
    private String fs;
    private final String RAIZ_CLIENTE = "FileSync";
    private final String USUARIO_REMOTOS = "usuários_remotos";
    private String caminhoRaizCliente;
    private String caminhoUsuariosRemotos;
    private int buffer_size = 1048576;  // 2 megabytes
    private boolean conectadoServidor;
    private boolean clienteLogado;
    private TipoRequisicao tipoRequisicao;
    private Reply resposta;
    private Request requisicao;
    private Socket cliente;
    private MainScreen telaPrincipal;    
    private String serverName;
    private int porta;    
    private boolean recebeuFSMRemoto;      
    
    public Cliente() {
        this.fs = System.getProperty("file.separator");
        this.caminhoRaizCliente = System.getProperty("user.home") + fs + RAIZ_CLIENTE + fs;
        this.caminhoUsuariosRemotos = caminhoRaizCliente + USUARIO_REMOTOS + fs;
        new File(caminhoRaizCliente).mkdir();
        //iniciarTelaPrincipal();
    }        
    
    public Cliente(String serverName, int porta) throws IOException {
        this.serverName = serverName;
        this.porta = porta;
        this.fs = System.getProperty("file.separator");
        this.caminhoRaizCliente = System.getProperty("user.home") + fs + RAIZ_CLIENTE + fs;
        this.caminhoUsuariosRemotos = caminhoRaizCliente + USUARIO_REMOTOS + fs;
        new File(caminhoRaizCliente).mkdir();
        //new File(System.getProperty("user.home") + fs + raizCliente + fs + usuariosRemotos).mkdir();        
    }
    
    public final void iniciarTelaPrincipal() {
        telaPrincipal = new MainScreen(this);
    }
    
    /**
     * 
     * @return 1 conectado e logado, 0 conectado, mas nao logado, -1 nao conectado;
     */
    public int conectarServidor(Usuario user, String serverName, int porta) {
        int opcao = 0;
        this.serverName = serverName;
        this.porta = porta;
        System.out.println("Conectando ao " + serverName + 
                " na porta " + porta);
        
        realizarAutenticacao(user);
        
        if (conectadoServidor) {
            opcao = 0;
            if (clienteLogado)
                opcao = 1;                            
            System.out.println("Conectado a " +
                cliente.getRemoteSocketAddress());            
        } else {
            opcao = -1;
        }
        
        return opcao;        
    }
    
    public void realizarAutenticacao(Usuario user) {
        //boolean sucesso = conectadoServidor;
        requisicao = new Request(TipoRequisicao.Autenticacao, user); 
        
        enviarRequisicao();
        if (conectadoServidor == true) {
            receberResposta();                        
            clienteLogado = resposta.getSucesso();
        } 
        //return sucesso;
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
            conectadoServidor = true;
            OutputStream saidaParaServidor = cliente.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(saidaParaServidor);
            oos.writeObject(requisicao);
            oos.flush();            
        } catch (ConnectException ex) {
            conectadoServidor = false;
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            conectadoServidor = false;
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
            fis.close();
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
                
        arquivoLocal = new File(arquivoRemoto.getNomeDoDestino());        
        data = resposta.getBytes();
        
        try {
            FileOutputStream fos = new FileOutputStream(arquivoLocal);            
            fos.write(data);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println("Arquivo: \n" + arquivoLocal.getAbsolutePath() + " nao foi aberto");
            
        }                   
    }
    
    public Arquivo acessarPastaRemota() {        
        // Obter diretorios a partir do diretorio home do pc remoto
        requisicao = new Request(TipoRequisicao.ExibirDiretoriosRemotos, 
                new Arquivo(true));
        
        enviarRequisicao();
        receberResposta();
        
        Arquivo arquivoRemoto = (Arquivo) resposta.getObject();
        arquivoRemoto.criarArvoreDeDiretorioLocal(caminhoRaizCliente, fs);
        arquivoRemoto.setCaminhoDoArquivo(caminhoRaizCliente);
        
        return arquivoRemoto;
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
        telaPrincipal = new MainScreen(this);
        telaPrincipal.setVisible(true);
    }

    private void exibirArquivosParaCliente(FileSystemModel arvoreDeArquivos, boolean isAreaRemota) {        
        telaPrincipal.mostrarPainelDiretorio(arvoreDeArquivos, isAreaRemota);
    }        

    public void sincronizarArquivos() {
        File diretorioDestino = telaPrincipal.getRaizLocal();                             
        
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
    
    public void exibirArvoreDeDiretoriosRemotos(String caminhoDiretorioRemoto) {
        Arquivo diretorioRemoto;
        
        diretorioRemoto = new Arquivo(caminhoDiretorioRemoto);
                
        requisicao = new Request(TipoRequisicao.ExibirDiretoriosRemotos, 
                diretorioRemoto);
        
        enviarRequisicao();
        receberResposta();
        
        diretorioRemoto = (Arquivo) resposta.getObject();
        
        System.out.println(diretorioRemoto.listaArvoreDeDiretorios());
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
    
    
    public void criarArvoreDeArquivos(Path fileSystem) {
        System.out.println("CriadorDeArvore chamado por fileWalksTree");
        File file = fileSystem.toFile();
        Arquivo arquivo = new Arquivo(file);
        
        if (file.isFile()) {
           arquivo.setNomeDoDestino(caminhoUsuariosRemotos + fileSystem);
           realizarDownload(arquivo);
        } else {
            file.mkdir();
        }
    }
    
    public void criarArvoreDeArquivos(String nomeDoArquivoDeDownload) {
        Arquivo arquivoDeDownload = new Arquivo(new File(nomeDoArquivoDeDownload));
                       
        criarArvoreDeArquivos(caminhoUsuariosRemotos, new File(nomeDoArquivoDeDownload).listFiles()
                        , arquivoDeDownload);        
        
    }
    
    public void criarArvoreDeArquivosRemotos(String nomeDoArquivoDeDownload) {
        
    }
    
    public void criarArvoreDeArquivos(String caminhoLocal, File[] filhos, Arquivo raiz) {
        String caminhoEstendido = caminhoLocal+fs+ raiz + fs;
        new File( caminhoEstendido).mkdir();
        for (File filho : filhos) {
            if (filho.isDirectory() && filho.listFiles().length != 0) {                
               
               criarArvoreDeArquivos(caminhoEstendido, filho.listFiles(), 
                        new Arquivo(filho));
            }
            else {
                if (!filho.isHidden()) {
                    raiz.setNomeDoDestino(caminhoEstendido + raiz);                
                    raiz.addCaminhoLocal(fs + filho.getName());
                    raiz.setNomeDoArquivo(filho.getName());
                    //raiz.setNomeDoArquivo(raiz+ fs + filho.getName());
                    realizarDownload(raiz);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        File local;
        String nomeDoArquivoDeDownload;
        Arquivo arquivoDeDownload;
        String nomeDoArquivoDeUpload;
        String caminhoDoArquivo;
        
        int porta = FileSync.getPorta();                
        
        Cliente c = new Cliente("localhost", porta);
        
        Usuario user = new Usuario(new DadosLogin("admin", "admin"));        
                
        c.conectarServidor(user, "localhost", porta);
        
        /* Exibir arvore de diretorios */
        
        String nomeDoDiretorioDeDownload = "C:\\Users\\Francisco\\Documents\\TesteArquivos";
        
        //c.exibirArvoreDeDiretoriosRemotos(nomeDoDiretorioDeDownload);
               
        
        /* Realizar download */
        local = new File(c.caminhoUsuariosRemotos);
        
        nomeDoArquivoDeDownload = "C:\\Users\\Francisco\\Documents\\TesteArquivos";
               
        
        //Files.walkFileTree(Paths.get(nomeDoArquivoDeDownload), 
        //        new CriadorDeArvoreDeDiretorio(Paths.get(c.caminhoUsuariosRemotos), c));
        
        //c.criarArvoreDeArquivos(nomeDoArquivoDeDownload);
        
        /* realizar download local */
        
        arquivoDeDownload = new Arquivo(new File(nomeDoArquivoDeDownload));
        String destinoDoArquivo = FileSync.getNomePasta();
        File[] arquivos = arquivoDeDownload.getArquivo().listFiles();
        for (File file : arquivos) {
            if (file.isFile()) {
                arquivoDeDownload = new Arquivo(file);
                arquivoDeDownload.setNomeDoDestino(destinoDoArquivo + file.getName());
                c.realizarDownload(arquivoDeDownload);
            } else {
                new File(destinoDoArquivo + file.getName()).mkdir();
            }
        }                
        
        /* Realizar upload *
        nomeDoArquivoDeUpload = "C:\\Users\\Francisco\\Documents";
        
        Arquivo arquivoDeUpload = new Arquivo(new File(nomeDoArquivoDeUpload));
        arquivoDeUpload.setNomeDoDestino("C:\\Users\\Francisco\\");
        
        c.realizarUpload(arquivoDeUpload);
        
        */
    }
    
}