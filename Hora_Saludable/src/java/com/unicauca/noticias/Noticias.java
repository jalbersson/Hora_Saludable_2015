/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.noticias;

/**
 *
 * @author Yamid
 */
class Noticias {
    private String titulo;
    private String contenido;
    private String fecha;
    private Noticias selectedNoticia;
    public Noticias(String titulo, String contenido, String fecha) {
        this.titulo = titulo;
        this.contenido =contenido;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Noticias getSelectedNoticia() {
        return selectedNoticia;
    }

    public void setSelectedNoticia(Noticias selectedNoticia) {
        this.selectedNoticia = selectedNoticia;
    }
    
    
}
