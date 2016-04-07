/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.persistencia;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Francisco
 */
public class Usuario implements Serializable{
    private DadosLogin dadosLogin;
    private File filePath;
    
}
