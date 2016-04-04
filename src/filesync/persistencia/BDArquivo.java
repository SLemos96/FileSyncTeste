/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.persistencia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
public class BDArquivo implements IBancoDados{
    private String arquivoPadrao = "dados.txt";
    private String delimitadorPadrao = ":";
    
    public BDArquivo(String bancoDeDados, String delimitadorPadrao) {
        arquivoPadrao = bancoDeDados;
        this.delimitadorPadrao = delimitadorPadrao;
    }
    
    public BDArquivo() {
        
    }
    
    @Override
    public boolean encontrarUsuario(String nomeDeUsuario) {
        String line = null;
        boolean encontrado = false;
            
        try {
            FileReader arquivoDeDados = new FileReader(arquivoPadrao);
            BufferedReader lerArqDados = new BufferedReader(arquivoDeDados);
            line = lerArqDados.readLine();
         
            String data[] = null;// guarda usuário no espaço [0] e senha no espaço [1]            
            
            while (line != null) { 
                data = line.split(delimitadorPadrao);
                if(data[0].toLowerCase().equals(nomeDeUsuario)) {
                    encontrado = true;
                    break;
                }
                line = lerArqDados.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(BDArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return encontrado;
    }
    
    @Override
    public String getSenha(String nomeDeUsuario) {
        String line = null;
        String senha = "";
        
        try {
            
            FileReader arquivoDeDados = new FileReader(arquivoPadrao);
            BufferedReader lerArqDados = new BufferedReader(arquivoDeDados);
            
            line = lerArqDados.readLine();
            String data[] = null;// guarda usuário no espaço [0] e senha no espaço [1]            
            
            while (line != null) { 
                data = line.split(delimitadorPadrao);
                if(data[0].toLowerCase().equals(nomeDeUsuario)) {
                    senha = data[1];
                    break;
                }
                line = lerArqDados.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(BDArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return senha;
    } 
}