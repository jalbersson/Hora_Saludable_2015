/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.noticias;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import com.unicauca.noticias.Noticias;
import java.util.Random;

/**
 *
 * @author Yamid
 */
@ManagedBean(name = "servicioNoticias")
@ApplicationScoped

public class ServicioNoticias {

    /**
     * Creates a new instance of ServicioNoticias
     */
    public ServicioNoticias() {
    }

    List<Noticias> lst;

    public List<Noticias> createNoticias(int tamaño) {

        lst = new ArrayList<Noticias>();
        for (int i = 0; i < tamaño; i++) {
            //lst.add(new Noticias("hola", "123", "otro hola"));
            lst.add(new Noticias(getCadenaAleatoria(5), getCadenaAleatoria(160), getCadenaAleatoria(10)));
            //System.out.print("Entro aqui");
        }

        return lst;

    }

    public String getCadenaAleatoria(int longitud) {
        String cadenaAleatoria = "";
        long ms = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(ms);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= 9) || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        cadenaAleatoria = "asd0";
        return cadenaAleatoria;
    }

}
