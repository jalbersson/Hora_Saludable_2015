/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.contenidos.java;

/**
 *
 * @author Yuri
 */
public class Horario 
{
    private String id;
    private String hora;
    private String lunes;
    private String martes;
    private String miercoles;
    private String jueves;
    private String viernes;

    
    Horario(String h,String l,String m,String mi,String j,String v, String i)
    {
        this.hora = h;
        this.lunes = l;
        this.martes = m;
        this.miercoles = mi;
        this.jueves = j;
        this.viernes = v;
        this.id = i;
    }
    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the lunes
     */
    public String getLunes() {
        return lunes;
    }

    /**
     * @param lunes the lunes to set
     */
    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    /**
     * @return the martes
     */
    public String getMartes() {
        return martes;
    }

    /**
     * @param martes the martes to set
     */
    public void setMartes(String martes) {
        this.martes = martes;
    }

    /**
     * @return the miercoles
     */
    public String getMiercoles() {
        return miercoles;
    }

    /**
     * @param miercoles the miercoles to set
     */
    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    /**
     * @return the jueves
     */
    public String getJueves() {
        return jueves;
    }

    /**
     * @param jueves the jueves to set
     */
    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    /**
     * @return the viernes
     */
    public String getViernes() {
        return viernes;
    }

    /**
     * @param viernes the viernes to set
     */
    public void setViernes(String viernes) {
        this.viernes = viernes;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
