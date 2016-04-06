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
public class Request implements Serializable{
    
    private Request requisicao;
    private Parametro parametro;
    private File path;
    
    public Request(Request requisicao) {
        this.requisicao = requisicao;
    } 
           
}
