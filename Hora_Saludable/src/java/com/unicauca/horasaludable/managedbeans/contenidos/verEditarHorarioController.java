/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Horario;
import com.unicauca.horasaludable.jpacontrollers.HorarioFacade;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yuri
 */
@ManagedBean
@ViewScoped
public class verEditarHorarioController implements Serializable {

    @EJB
    HorarioFacade ejbHorario;

    Horario horario;
    long id;

    @PostConstruct
    public void init() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        id = Long.parseLong(params.get("id"));
        horario = ejbHorario.mostrarHorario(id);
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public String actualizarHorario() {

        ejbHorario.edit(horario);
        return "principal";
    }
    
    public String eliminarHorario() {
        
        ejbHorario.remove(horario);
        return "principal";
    }    
    
    public void insertarFila() {
        String cont2 = this.horario.getHorcontenido().substring(0, this.horario.getHorcontenido().length() - 8);
        cont2 = cont2 + "<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>\n" + "</table>";
        this.horario.setHorcontenido(cont2);

    }

    public void eliminarFila() {

        String[] cadenas = this.horario.getHorcontenido().split("\n");
        String cont2 = "";
        for (int i = 0; i < cadenas.length; i++) {
            if (i != cadenas.length - 2) {
                cont2 = cont2 + cadenas[i];
            }
        }

        this.horario.setHorcontenido(cont2);
    }
    
    public void limpiar()
    {
        this.horario.setHornombre(" ");
        this.horario.setHorcontenido("<br/><table align=\"center\" border=\"4\" bordercolor=\"#22419a\" cellpadding=\"10\" cellspacing=\"20\">\n"
                + "<tr style=\"color:#ffffff\" bgcolor=\"#22419a\">\n"
                + "<th width=\"80px\">HORA</th><th width=\"80px\" >LUNES</th><th width=\"80px\" >MARTES</th><th width=\"80px\" >MIERCOLES</th> <th width=\"80px\" >JUEVES</th><th width=\"80px\" >VIERNES</th>\n"
                + "</tr>\n"
                + "<tr>  <td>8:00 a.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>\n"
                + "<tr>  <td>9:00 a.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>\n"
                + "<tr>  <td>10:00 a.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>\n"
                + "<tr>  <td>11:00 a.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "<tr>  <td>12:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "<tr>  <td>1:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "<tr>  <td>2:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "<tr>  <td>3:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "<tr>  <td>4:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "<tr>  <td>5:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "<tr>  <td>6:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "<tr>  <td>7:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "<tr>  <td>8:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\n"
                + "</table>");
    }

}
