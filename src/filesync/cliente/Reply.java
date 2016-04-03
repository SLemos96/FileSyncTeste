/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.cliente;

import java.io.File;

/**
 *
 * @author Reinaldo
 */
public class Reply {

    byte [] resposta = new byte[52428800];
    File path;
    
    public Reply(byte[] arquivo, File caminho) {
        this.resposta = arquivo;
        this.path = caminho;
        
    }
       
}
