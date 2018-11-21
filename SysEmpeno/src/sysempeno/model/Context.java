/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.model;

import entities.Contrato;

/**
 *
 * @author erick
 */
public class Context {
    private final static Context instance = new Context();
    private Contrato contrato;
    
    public static Context getInstance() {
        return instance;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
}
