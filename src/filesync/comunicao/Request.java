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
public abstract class Request implements Serializable{
    
    private Request requisicao;
    private boolean type; // tipo da requisição
    private File path;
    
    public Request(Request requisicao) {
        this.requisicao = requisicao;
    } 
    
    
    
}
