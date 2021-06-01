/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.model.rest.models;

/**
 *
 * @author zihar
 */
public class ValidatingRequest {
    private String idAsociado;
    private String ineFront;
    private String ineBack;
    private String selfie;

    /**
     * @return the idAsociado
     */
    public String getIdAsociado() {
        return idAsociado;
    }

    /**
     * @param idAsociado the idAsociado to set
     */
    public void setIdAsociado(String idAsociado) {
        this.idAsociado = idAsociado;
    }

    /**
     * @return the ineFront
     */
    public String getIneFront() {
        return ineFront;
    }

    /**
     * @param ineFront the ineFront to set
     */
    public void setIneFront(String ineFront) {
        this.ineFront = ineFront;
    }

    /**
     * @return the ineBack
     */
    public String getIneBack() {
        return ineBack;
    }

    /**
     * @param ineBack the ineBack to set
     */
    public void setIneBack(String ineBack) {
        this.ineBack = ineBack;
    }

    /**
     * @return the selfie
     */
    public String getSelfie() {
        return selfie;
    }

    /**
     * @param selfie the selfie to set
     */
    public void setSelfie(String selfie) {
        this.selfie = selfie;
    }
}
