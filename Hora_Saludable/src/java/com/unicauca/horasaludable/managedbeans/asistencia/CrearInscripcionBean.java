package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.entities.Detalleinscripcion;
import com.unicauca.horasaludable.entities.DetalleinscripcionPK;
import com.unicauca.horasaludable.entities.Inscripcion;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.DetalleinscripcionFacade;
import com.unicauca.horasaludable.jpacontrollers.InscripcionFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class CrearInscripcionBean {

    @EJB
    private InscripcionFacade ejbFacadeIns;
    @EJB
    private UsuarioFacade ejbFacadeUsu;
    @EJB
    private DetalleinscripcionFacade ejbFacadeDetins;

    private Inscripcion ins;
    
    private List<Usuario> filteredUsus;
    private List<Usuario> usuarios;
    private List<Usuario> selectedUsus;
    private String accion;
    
    public CrearInscripcionBean() {
        ins = new Inscripcion();
        AuxiliarInscripcion ai = new AuxiliarInscripcion();
        ins.setInsmes(ai.getMes(0)); // con 0 se obtiene el mes actual
        ins.setInsanio(ai.getAnio());
        usuarios = new ArrayList();
        selectedUsus = new ArrayList();
        accion = "Inscribir usuarios";
    }

public void crearIncripcion() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (!selectedUsus.isEmpty()) {
            AuxiliarInscripcion ai = new AuxiliarInscripcion();
            int mes = ai.getMM(ins.getInsmes());

            if (accion.equals("Inscribir usuarios")) {
                ins.setInsmes(String.valueOf(mes));
                ejbFacadeIns.create(ins);
            } else {
                ins = ejbFacadeIns.existeInscripcion(String.valueOf(mes), ins.getInsanio());
            }            

            for (int i = 0; i < usuarios.size(); i++) {
                crearDetalleinscripcion(usuarios.get(i));
            }

            if (ejbFacadeIns.find(ins.getInsid()) != null) {
                if (accion.equals("Inscribir usuarios")) {
                    accion = "Actualizar inscripción";
                    context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inscripción creada con éxito.", "Ahora sí dirijase a la opción: 'Registrar asistencia' y registre la asistencia los usuarios que acaba de inscribir en dicho mes y año."));
                }
                else {
                    context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inscripción actualizada con éxito.", "Ahora sí dirijase a la opción: 'Registrar asistencia' y registre la asistencia los usuarios antiguos y nuevos que acaba de inscribir en dicho mes y año."));
                }                
            } else {
                accion = "Inscribir usuarios";
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha inscrito ningún usuario.", "Marque los usuarios que desee inscribir en esta fecha y luego presione el botón 'Inscribir usuarios' para guardar dicha inscripción."));
            }
            ins.setInsmes(ai.getMes(mes));
        }
        else {
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha inscrito ningún usuario.", "Marque los usuarios que desea inscribir en dicha fecha y luego presione el botón: 'Inscribir o Actualizar usuarios' para guardar o actualizar dicha inscripción."));
        }
    }

    public void buscarInscritos() {
        FacesContext context = FacesContext.getCurrentInstance();
        AuxiliarInscripcion ai = new AuxiliarInscripcion();
        int mes = ai.getMM(ins.getInsmes());
        selectedUsus = ejbFacadeDetins.usariosActivos(true, String.valueOf(mes), ins.getInsanio(),"");
        usuarios = ejbFacadeUsu.findAll();
        if(!usuarios.isEmpty()) {
            if (selectedUsus.isEmpty()) {
                accion = "Inscribir usuarios";
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Búsqueda completa", "Marque los usuarios que desee inscribir en esta fecha y luego presione el botón 'Inscribir usuarios' para guardar dicha inscripción."));
            } else {
                accion = "Actualizar inscripción";
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Búsqueda completa", "Los usuarios que ya estan marcados son los que ya estan inscritos en esta fecha."));
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Paso 1", "Marque los usuarios nuevos que desee inscribir en esta fecha o des-marque los usuarios antiguos si desea borrar su inscripción."));
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Paso 2", "Presione el botón: 'Actualizar inscripción' para guardar los cambios."));
            }
        }
        else {
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "No hay usuarios registrados en este programa."));
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Solución", "Dirijase a la opcion: 'Gestión de Usuarios' y registre usuarios a este programa."));
        }

    }

    public void crearDetalleinscripcion(Usuario usu) {
        Detalleinscripcion detins = new Detalleinscripcion();
        detins.setDetalleinscripcionPK(new DetalleinscripcionPK());
        if (selectedUsus.contains(usu)) { // usuario selecionado para inscribirlo
            detins.setDetactivo(true); // true:activo
        } else {
            detins.setDetactivo(false); //false:inactivo
        }
        detins.setInscripcion(ins);
        detins.setUsuario(usu);
        detins.getDetalleinscripcionPK().setInsid(detins.getInscripcion().getInsid());
        detins.getDetalleinscripcionPK().setUsuid(detins.getUsuario().getUsuid());

        if (accion.equals("Inscribir usuarios")) {
            ejbFacadeDetins.create(detins);
        }
        if (accion.equals("Actualizar inscripción")) {
            ejbFacadeDetins.edit(detins);
        }
    }

    public Inscripcion getIns() {
        return ins;
    }

    public void setIns(Inscripcion ins) {
        this.ins = ins;
    }

    public InscripcionFacade getEjbFacadeIns() {
        return ejbFacadeIns;
    }

    public List<String> getMeses() {
        AuxiliarInscripcion ai = new AuxiliarInscripcion();
        return ai.getMeses();
    }

    public int getAnio() {
        AuxiliarInscripcion ai = new AuxiliarInscripcion();
        return ai.getAnio();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Usuario> getSelectedUsus() {
        return selectedUsus;
    }

    public void setSelectedUsus(List<Usuario> selectedUsus) {
        this.selectedUsus = selectedUsus;
    }
    
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Usuario> getFilteredUsus() {
        return filteredUsus;
    }

    public void setFilteredUsus(List<Usuario> filteredUsus) {
        this.filteredUsus = filteredUsus;
    }
    
    public boolean filtrarPorNombre(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
        if(value == null) {
            return false;
        }
        List<Usuario> lstUsu2 = ejbFacadeUsu.buscarPorContenidoNombres(filterText);
        
        for(int i=0; i<lstUsu2.size(); i++)
        {
            if(value.toString().trim().equals(lstUsu2.get(i).getUsunombres().trim()))
                return true;
        }
        return false;
    }
    
    public boolean filtrarPorApellido(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
        if(value == null) {
            return false;
        }
        List<Usuario> lstUsu2 = ejbFacadeUsu.buscarPorContenidoApellidos(filterText);
        
        for(int i=0; i<lstUsu2.size(); i++)
        {
            if(value.toString().trim().equals(lstUsu2.get(i).getUsuapellidos().trim()))
                return true;
        }
        return false;
    }
    
    public void mensajeInicial ()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Para inscribir usuarios, primero seleccione el mes y año donde desea inscribirlos y luego presione: 'Buscar'."));
    }
}