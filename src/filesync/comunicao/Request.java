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
    
    private TipoRequisicao requisicao;
    private Parametro parametro;
    
    public Request(TipoRequisicao requisicao, Parametro parametro1) {
        this.requisicao = requisicao;
        this.parametro = parametro1;
    }

    public TipoRequisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(TipoRequisicao requisicao) {
        this.requisicao = requisicao;
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }                   
}
