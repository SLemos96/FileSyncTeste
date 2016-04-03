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
public class Request {

    
    private boolean type; // tipo da requisição
    private File path;
    
    public Request(boolean req_type, File caminho) {
        this.type = req_type;
        this.path = caminho;
    }
    
    
    
}
