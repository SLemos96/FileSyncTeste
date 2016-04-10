/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import java.nio.file.*;
import filesync.persistencia.Tree;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class Arquivo implements Parametro{
    private File arquivo;
    private String caminhoLocal;
    private String caminhoDeDestino;
    private String nomeDoArquivo;
    private long tamanho;
    private long ultimaAlteracao;
    private Tree<String> arvoreDeDiretorios;
    private byte[] data;
    private boolean isDiretorio;
    
    public Arquivo(File arquivo) {
        this.arquivo = arquivo;
        this.nomeDoArquivo = arquivo.getName();
        this.caminhoLocal = arquivo.getAbsolutePath();
        this.tamanho = arquivo.length();
        this.ultimaAlteracao = arquivo.lastModified();
        this.isDiretorio = arquivo.isDirectory();
        this.caminhoDeDestino = "";        
    }

    public String getCaminhoDeDestino() {
        return caminhoDeDestino;
    }

    public void setCaminhoDoArquivo(String caminhoDoArquivo) {
        this.caminhoLocal = caminhoDoArquivo;
    }

    
    public boolean isIsDiretorio() {
        return isDiretorio;
    }

    public Tree<String> getArvoreDeDiretorios() {
        return arvoreDeDiretorios;
    }
    
    
    
    public Arquivo(String nomeDoDestino) {
        this.caminhoDeDestino = nomeDoDestino;
    }

    public String getNomeDoArquivo() {
        return nomeDoArquivo;
    }

    public void setNomeDoArquivo(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }
    

    public byte[] getData() {
        return data;
    }

    public String getNomeDoDestino() {
        return caminhoDeDestino;
    }

    public void setNomeDoDestino(String nomeDoDestino) {
        this.caminhoDeDestino = nomeDoDestino;
    }
            
    public File getArquivo() {
        return arquivo;
    }
    
    public void setData(byte[] data) {
        this.data = data;
    }

    public String getCaminhoLocal() {
        return caminhoLocal;
    }

    public long getTamanho() {
        return tamanho;
    }

    public long getUltimaAlteracao() {
        return ultimaAlteracao;
    }
    
    public void addCaminhoLocal(String caminho) {
        caminhoLocal += caminho;
    }    
    
    public void addCaminhoDeDestino(String caminho) {
        caminhoDeDestino += caminho;        
    }
    
    public void addNome(String caminho) {
        nomeDoArquivo += caminho;
    }
    
    public boolean criarArvoreDeDiretorio(File raiz) {
        if (raiz.isDirectory()) {
            arvoreDeDiretorios = new Tree<String >(raiz.getName());        
            inicializarArvoreDeDiretorios(raiz);
            return true;
        } else {
            return false;
        }
    }
    
    public void inicializarArvoreDeDiretorios(File raiz) {
        LinkedList<File> diretorios = new LinkedList<>();
        diretorios.add(raiz);
        
        while(!diretorios.isEmpty()) {
            raiz = diretorios.pollLast();
            File[] arquivos = raiz.listFiles();
            inicializarArvoreDeDiretorios(raiz.getName(), diretorios, arquivos);            
        }
    }
    
    private void inicializarArvoreDeDiretorios(String raiz, LinkedList<File> diretorios, File[] arquivos) {
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (!arquivo.isHidden() && arquivo.isDirectory()) {
                   arvoreDeDiretorios.addLeaf(raiz, arquivo.getName());
                   diretorios.push(arquivo);
                }
            }
        }
    }
    
    /*
    public void inicializarArvoreDeDiretorios(File raiz) {
        File[] arquivos = raiz.listFiles();
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (!arquivo.isHidden() && arquivo.isDirectory()) {
                    arvoreDeDiretorios.addLeaf(raiz.getName(), arquivo.getName());                                        
                    inicializarArvoreDeDiretorios(arquivo);
                }
            }
                            
        }
    }*/
    
    /**
     * Cria uma arvore de diretorio no caminho especificado
     * @param caminhoDestino caminho especificado para criar uma arvore de
     * diretorio
     * @param fs o separar de arquivos do sistema
     */
    public void criarArvoreDeDiretorioLocal(String caminhoDestino, String fs) {
        File arquivo;
        String caminhoDeCriacao;
        
        caminhoDeCriacao = caminhoDestino + fs + nomeDoArquivo;
        if (new File(caminhoDeCriacao).mkdir())
            System.out.println("Diretorio Criado");
        else
            System.out.println("Diretorio: " + caminhoDeCriacao + "nao criado");
        
        ArrayList<String> filhos = new ArrayList<>(arvoreDeDiretorios.getSuccessors(nomeDoArquivo));
        
        for (String filho : filhos) {
            new File(caminhoDeCriacao +fs + filho).mkdir();            
        }                
    }
    
    

    public static void main(String[] args) throws IOException {
        String nomeDoDiretorioDeDownload = "C:\\Users\\Francisco\\Documents\\";
        
        File diretorio = new File(nomeDoDiretorioDeDownload);
        
        Arquivo arquivo = new Arquivo(diretorio);
        
        arquivo.criarArvoreDeDiretorio(diretorio);
        arquivo.criarArvoreDeDiretorioLocal(
                "C:\\Users\\Francisco\\TesteArquivos" , System.getProperty("file.separator"));
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/test.bin"));
        oos.writeObject(arquivo.getArvoreDeDiretorios());
        oos.close();
        //arquivo.criarArvoreDeDiretorioLocal(System.getProperty("user.dir"),
        //        System.getProperty("file.separator"));
        
        System.out.println(arquivo.listaArvoreDeDiretorios());    
    }
    
    public String listaArvoreDeDiretorios() {
        return arvoreDeDiretorios.toString();
    }                       
    
    @Override
    public String toString() {
        return arquivo.getName();
    }
    
    @Override
    public String tipoParametro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
