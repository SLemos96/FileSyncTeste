/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.persistencia;

import java.io.File;

/**
 * Classe para identificar o destino de um arquivo
 * @author Francisco
 */
public class ArquivoDestino {
    private final boolean toServer;
    private final File file;
    
    public ArquivoDestino(File file, boolean toServer) {
        this.file = file;
        this.toServer = toServer;
    }

    public boolean isToServer() {
        return toServer;
    }

    public File getFile() {
        return file;
    }
}
