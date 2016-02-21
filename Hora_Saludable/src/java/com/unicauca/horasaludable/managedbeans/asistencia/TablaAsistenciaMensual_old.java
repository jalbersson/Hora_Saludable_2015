package com.unicauca.horasaludable.managedbeans.asistencia;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Named(value = "tablaAsistenciaMensual")
@ManagedBean
@ViewScoped
public class TablaAsistenciaMensual_old implements Serializable
{
    private int num;
    private String nombre;
    private char sexo;
    private char masculino;
    private char femenino;
    private long codigo;
    private String programa;
    private String estamento;
    private String[] dias;
    private int asisTotal;
    
    public TablaAsistenciaMensual_old()
    {
        num = 0;
        nombre = "-";
        codigo = 0;
        programa = "-";
        estamento = "-";  
        asisTotal = 0;        
        dias = new String[31];
        for(int i=0; i<31; i++)
            dias[i] = "";
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

    public String getDias(int i) {
        return dias[i];
    }

    public void setDias(int i, String valor) {
        dias[i] = valor;
    }

    public int getAsisTotal() {
        return asisTotal;
    }

    public void setAsisTotal(int asisTotal) {
        this.asisTotal = asisTotal;
    }  

    public char getMasculino() {
        return masculino;
    }

    public void setMasculino(char masculino) {
        this.masculino = masculino;
    }

    public char getFemenino() {
        return femenino;
    }

    public void setFemenino(char femenino) {
        this.femenino = femenino;
    }
    
    
}