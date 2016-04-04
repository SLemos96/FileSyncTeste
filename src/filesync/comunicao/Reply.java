/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import java.io.*;

/**
 *
 * @author Reinaldo
 */
public abstract class Reply implements Serializable{
    private Reply reply;
    private byte [] resposta = new byte[52428800];
    
    public Reply(byte[] arquivo, File caminho) {
        this.resposta = arquivo;
        
    }
       
}
