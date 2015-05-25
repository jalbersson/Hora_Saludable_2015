/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Horario;
import com.unicauca.horasaludable.jpacontrollers.HorarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Yuri
 */
@ManagedBean
@RequestScoped
public class agregarHorarioController implements Serializable {

    @EJB
    private HorarioFacade horF = new HorarioFacade();

    private Horario hor;
    private String titulo;
    private String cont;

    //<table><th style="color: "></th></table>
    
    public agregarHorarioController() {
        this.titulo = "Ingresa aqui titulo del horario";
        this.cont = "<br/><table align=\"center\" border=\"4\" bordercolor=\"#fe5050\" cellpadding=\"10\" cellspacing=\"20\">\n"
                + "<tr style=\"color:#ffffff\" bgcolor=\"#22419a\">\n"
                + "<th width=\"40px\">HORA</th><th width=\"80px\" >LUNES</th><th width=\"80px\" >MARTES</th><th width=\"80px\" >MIERCOLES</th> <th width=\"80px\" >JUEVES</th><th width=\"80px\" >VIERNES</th>\n"
                + "</tr>\n"
                + "<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>\n"
                + "<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>\n"
                + "<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>\n"
                + "<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td>Karate</td>   </tr>\n"
                + "</table>";

    }

    public void insertarFila() {
        String cont2 = this.cont.substring(0, this.cont.length() - 8);
        cont2 = cont2 + "<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>\n" + "</table>";
        this.cont = cont2;

    }
    
    public void eliminarFila() {
        
        String[] cadenas = this.cont.split("\n");
        String cont2="";
        for(int i=0 ; i<cadenas.length ; i++)
        {
            if(i != cadenas.length -2)
                cont2 = cont2 + cadenas[i];
        }
            
        this.cont = cont2;

    }

    public String crearHorario() {
        try {
            hor = new Horario();
            this.hor.setHornombre(this.titulo);
            this.hor.setHorcontenido(this.cont);
            this.horF.create(this.hor);

        } catch (Exception e) {

            return "agregarHorario";
        }
        return "principal";

    }
    
    public List<Horario> lista()
    {
        return horF.lista();
    }
    
    public String editarHorario(Horario h)
    {
        return "editarHorario?id="+h.getHorid();
    }
    
    public String verHorario(Horario h)
    {
        return "verHorario?id="+h.getHorid();
    }
    
    public String eliminarHorario(Horario h)
    {
        return "eliminarHorario?id="+h.getHorid();
    }
    
    //<p:button value="" outcome="#{agregarHorarioController.eliminarHorario(hor)}" icon="ui-icon-circle-plus"/> 

    public Horario getHor() {
        return hor;
    }

    public void setHor(Horario hor) {
        this.hor = hor;
    }

    public HorarioFacade getHorF() {
        return horF;
    }

    public void setHorF(HorarioFacade horF) {
        this.horF = horF;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

}

/*
 <p:dataScroller  var="hor" value="#{horarioController.h}" chunkSize="6" style="float:right;" >
                                        <h:panelGrid columns="1" cellpadding="6" style="text-align: center;">
                                            <p:link value="#{hor.nombreH}" outcome="#{horarioController.mostrarHorario(hor)}" style="font-weight: bold; font-size: 15px; color: #AA0000;" />
                                        </h:panelGrid>
                                    </p:dataScroller>
 */
