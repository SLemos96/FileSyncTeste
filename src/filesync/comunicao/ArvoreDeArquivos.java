/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import filesync.persistencia.Tree;
import java.awt.List;
import java.util.TreeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Armazena uma arvore de arquivos
 * @author Francisco
 */
public class ArvoreDeArquivos implements Serializable, Parametro, Cloneable{
    private Tree<File> arvoreDeArquivos;
    private File raiz;
    
    public ArvoreDeArquivos(File raiz, Tree<File> arvoreDeArquivos) {
        this.raiz = raiz;
        this.arvoreDeArquivos = arvoreDeArquivos;
    }
    /**
     *
     * @param raiz
     */
    public ArvoreDeArquivos(File raiz) {
        /*
        arvoreDeArquivo = new TreeMap<>(new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
                }
            }
        );*/
        arvoreDeArquivos = new Tree(raiz);
        this.raiz = raiz;
        criarArvoreDeArquivo();
    }

    public File getRaiz() {
        return raiz;
    }

    public void setRaiz(File raiz) {
        this.raiz = raiz;
    }
    
    public void criarArvoreDeArquivo() {
        if (raiz != null) {
            preencherFilhos(raiz);
        }
    }
    
    public void preencherFilhos(File raiz) {
        File[] arquivos = raiz.listFiles();
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    arvoreDeArquivos.addLeaf(raiz, arquivo);                    
                } else {
                    preencherFilhos(arquivo);
                }                
            }
        } else {
            arvoreDeArquivos.addLeaf(raiz.getParentFile(), raiz);
        }
    }        
        
    public boolean isDiretorio(File diretorio) {
        return arvoreDeArquivos.containsElement(diretorio);
    }
    
    public boolean contemArquivo(File arquivo) {
        return arvoreDeArquivos.containsElement(arquivo);
    }
    
    public ArrayList<File> getFilhos(File arquivo) {
        return new ArrayList<>(arvoreDeArquivos.getSuccessors(arquivo));
    }
    
    
    public File getArquivo(File arquivo) {
        return arvoreDeArquivos.getTree(arquivo).getHead();
    }
    
    public void limparArvore() {
        
    }
    
    public String listaDeArquivos() {
        
        return arvoreDeArquivos.toString();
    }
    
    public static void main(String[] args) {
        File raiz;
        raiz = new File("C:\\Users\\Francisco\\Documents\\TesteArquivos");
        ArvoreDeArquivos arvoreDeArquivos = 
                new ArvoreDeArquivos(raiz);
        System.out.println(arvoreDeArquivos.listaDeArquivos());
    }
    
    
    //Método pouco ineficiente
    public ArvoreDeArquivos subArvoreDeArquivos(File arquivoFilho) {
        return new ArvoreDeArquivos(arquivoFilho, arvoreDeArquivos.getTree(arquivoFilho));
    }

    @Override
    public String tipoParametro() {
        return "arvore de arquivos";
    }
    
    @Override
    public ArvoreDeArquivos clone() throws CloneNotSupportedException {        
        return (ArvoreDeArquivos) super.clone();
        
    }
}
