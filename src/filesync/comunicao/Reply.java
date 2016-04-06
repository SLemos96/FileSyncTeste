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
public class Reply implements Serializable {
    private byte[] bytes;
    private TipoRequisicao resposta;
    private Object objeto;
    
    public Reply(byte[] bytes, TipoRequisicao resposta) {
        this.bytes = bytes;
        this.resposta = resposta;
    }
    
    public Reply(Object objeto, TipoRequisicao resposta) {
        this.objeto = objeto;
        this.resposta = resposta;
    }
    
    public Object getObject() {
        return objeto;
    }
            
    public byte[] getBytes() {
        return bytes;
    }

    public TipoRequisicao getResposta() {
        return resposta;
    }    
} 
