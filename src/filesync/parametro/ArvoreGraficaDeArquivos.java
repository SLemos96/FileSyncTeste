/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.parametro;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.tree.*;

public class ArvoreGraficaDeArquivos extends JFrame {
    private File raiz;    
    private JTree arvore;
    
    public ArvoreGraficaDeArquivos(Container container, File arquivoRaiz){        
        super();
	//this.arvoreDeArquivos = new ArvoreDeArquivos(arquivoRaiz);
        
        DefaultMutableTreeNode raiz = montarArvore(new DefaultMutableTreeNode(arquivoRaiz.getName())
                , arquivoRaiz);
        
        arvore = new JTree(raiz);         
                
        container.add(arvore);
        JScrollPane scrollPane = new JScrollPane(arvore);
        container.add(scrollPane);    	                
    }	  
    
    public JTree getArvore() {
        return arvore;
    }
    
    public DefaultMutableTreeNode montarArvore(DefaultMutableTreeNode folha, File file) {
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(file.getName());
        File[] listaDeArquivos;
        
        listaDeArquivos = file.listFiles();
        
        if (listaDeArquivos != null) {
            adicionarFilhos(raiz, listaDeArquivos);
            
            for (File arquivo : listaDeArquivos) {
                if (arquivo.isDirectory()) {
                    raiz.add(montarArvore(new DefaultMutableTreeNode(arquivo.getName()), arquivo));
                }
            }
        }
        return raiz;
    }
        
    public DefaultMutableTreeNode adicionarFilhos(DefaultMutableTreeNode raiz, File[] filhos) {
        
        for (File arquivo : filhos) {
            if (arquivo.isFile())
                raiz.add(new DefaultMutableTreeNode(arquivo.getName()));
        }
        return raiz;
    }
    
    public static void main(String args[]){
        File raiz = new File("C:\\Users\\Francisco");
        
    }
}
