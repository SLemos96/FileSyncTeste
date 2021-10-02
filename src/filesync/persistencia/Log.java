/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.persistencia;

import javax.swing.JTextPane;

/**
 * cria um log
 * @author janioxavier
 */
public class Log {
    private String log;
    private JTextPane telaDeLog;
    
    public Log(JTextPane telaDeLog, String logPadrao) {
        this.log = logPadrao;
        this.telaDeLog = telaDeLog;
    }
    
    public void escreverLog(String mensagem) {
        this.log += mensagem;
        this.telaDeLog.setText(log);
    }
    
    /**
     * Escreve uma mensagem e pula linha ao final
     * @param mensagem mensagem a ser escrita no log.
     */
    public void escreverLogLine(String mensagem) {
        this.log += mensagem + "\n";
        this.telaDeLog.setText(log);
    }
    
    public String getLog() {
        return log;
    }
    
    public void zerarLog() {
        this.log = "";
        this.telaDeLog.setText(log);
    }
}
