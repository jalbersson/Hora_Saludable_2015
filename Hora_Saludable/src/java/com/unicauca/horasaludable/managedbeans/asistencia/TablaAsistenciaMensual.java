package com.unicauca.horasaludable.managedbeans.asistencia;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Named(value = "tablaAsistenciaMensual")
@ManagedBean
@ViewScoped
public class TablaAsistenciaMensual implements Serializable
{
    private int num;
    private String nombre;
    //private char sexo;
    private char masculino;
    private char femenino;
    private long codigo;
    private String programa;
    //private String estamento;
    //Los cuatro atributos siguientes correponde al estamento
    private String estudiante;
    private String docente;
    private String funcionario;            
    private String familiar;
    private String[] dias;
    private int asisTotal;
    
    public TablaAsistenciaMensual()
    {
        num = 0;
        nombre = "-";
        codigo = 0;
        programa = "-";
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
    //Estamento

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }
    
    
}