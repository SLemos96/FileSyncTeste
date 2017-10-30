/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import filesync.controle.TipoRequisicao;
import java.io.*;

/**
 *
 * @author Reinaldo
 */
public class Reply implements Serializable {
    private byte[] bytes;
    private TipoRequisicao resposta;
    private Object objeto;
    private boolean sucesso;
    
    public Reply(byte[] bytes, TipoRequisicao resposta) {
        this.bytes = bytes;
        this.resposta = resposta;
    }
    
    public Reply(byte[] bytes) {
        this.bytes = bytes;
    }
    
    public Reply(Object objeto) {
        this.objeto = objeto;
    }
    
    public Reply(Object objeto, TipoRequisicao resposta) {
        this.objeto = objeto;
        this.resposta = resposta;
    }
    
    public Reply(Object objeto, byte[] data) {
        this.objeto = objeto;
        this.bytes = data;
    }
    
    public Reply(boolean sucesso, TipoRequisicao resposta) {
        this.sucesso = sucesso;
        this.resposta = resposta;
    }
    
    public Reply(boolean sucesso) {
        this.sucesso = sucesso;
    }   
    
    public Object getObject() {
        return objeto;
    }
    
    public boolean getSucesso() {
        return sucesso;
    }
        
    public byte[] getBytes() {
        return bytes;
    }

    public TipoRequisicao getResposta() {
        return resposta;
    }    

    public boolean isSucesso() {
        return sucesso;
    }        
} 
