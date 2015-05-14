package com.unicauca.horasaludable.managedbeans.asistencia;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@Named(value = "tablaAsistenciaAnual")
@ManagedBean
@ViewScoped
public class TablaAsistenciaAnual implements Serializable
{
    private int num;
    private String nombre;
    private char sexo;
    private long codigo;
    private String programa;
    private String estamento;
    private int[] meses;
    private int asisTotal;

    public TablaAsistenciaAnual() {
        num = 0;
        nombre = "-";
        codigo = 0;
        programa = "-";
        estamento = "-";     
        meses = new int[12];
        for(int i=0; i<12; i++)
            meses[i] = 0;
        asisTotal = 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getEstamento() {
        return estamento;
    }

    public void setEstamento(String estamento) {
        this.estamento = estamento;
    }

    public int getMeses(int i) {
        return meses[i];
    }

    public void setMeses(int i, int valor) {
        meses[i] = valor;
    }

    public int getAsisTotal() {
        return asisTotal;
    }
    
    public void setAsisTotal(int asisTotal) {
        this.asisTotal = asisTotal;
    }
}