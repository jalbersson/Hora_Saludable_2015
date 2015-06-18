package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.entities.Asistencia;
import com.unicauca.horasaludable.entities.Detalleasistencia;
import com.unicauca.horasaludable.entities.DetalleasistenciaPK;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.AsistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.DetalleinscripcionFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class AsistenciaView {

    @EJB
    private AsistenciaFacade ejbFacadeAsi;
    @EJB
    private DetalleinscripcionFacade ejbFacadeDetins;
    @EJB
    private DetalleasistenciaFacade ejbFacadeDetasi;
    @EJB
    private UsuarioFacade ejbFacadeUsu;

    private Asistencia asi;
    
    private List<Usuario> usuarios;
    private List<Usuario> selectedUsus;
    private String accion;
    
    public AsistenciaView() {
        selectedUsus = new ArrayList();
        usuarios = new ArrayList();
        accion = "Registrar asistencia";
        asi = new Asistencia();
        asi.setAsifecha(new Date());
        asi.setAsiobservaciones(new String());
    }

    public void crearAsistencia() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (accion.equals("Registrar asistencia")) {
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuarios registrados con éxito.", "Si lo desea, puede continuar registrando la asistencia de otros usuarios que asistieron en esta fecha"));
            ejbFacadeAsi.create(asi);
        }
        if (accion.equals("Actualizar asistencia")) {
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Asistencia actualizada con éxito.", "Si lo desea, puede continuar registrando la asistencia de otros usuarios que asistieron en esta fecha"));
            ejbFacadeAsi.edit(asi);
        }

        for (int i = 0; i < usuarios.size(); i++) {
            crearDetalleasistencia(usuarios.get(i));
        }
    }

    public void crearDetalleasistencia(Usuario usu) {
        Detalleasistencia detasi = new Detalleasistencia();
        detasi.setDetalleasistenciaPK(new DetalleasistenciaPK());

        if (selectedUsus.contains(usu)) { // usuario selecionado para registrar asistencia
            detasi.setDetasistio(true); // true:asistio
        } else {
            detasi.setDetasistio(false); //false:no asistio
        }
        detasi.setAsistencia(asi);
        detasi.setUsuario(usu);
        detasi.getDetalleasistenciaPK().setAsiid(detasi.getAsistencia().getAsiid());
        detasi.getDetalleasistenciaPK().setUsuid(detasi.getUsuario().getUsuid());

        if (accion.equals("Registrar asistencia")) {
            accion = "Actualizar asistencia";
            ejbFacadeDetasi.create(detasi);
        }
        if (accion.equals("Actualizar asistencia")) {
            ejbFacadeDetasi.edit(detasi);
        }
    }

    public void buscarAsistencia() {
        FacesContext context = FacesContext.getCurrentInstance();
        try
        {
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(asi.getAsifecha());
            usuarios = ejbFacadeDetins.usariosActivos(true, String.valueOf(cal.get(Calendar.MONTH)+1), cal.get(Calendar.YEAR), "");
            
            if(usuarios.size() > 0) {
                selectedUsus = new ArrayList();
                asi = new Asistencia();
                asi.setAsifecha(cal.getTime());

                List<Asistencia> lstAsis = ejbFacadeAsi.findByYearMonthDay(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));

                if(lstAsis.size() > 0) {
                    asi = lstAsis.get(0);
                    accion = "Actualizar asistencia";
                    List<Detalleasistencia> detasis = ejbFacadeDetasi.obtenerPorAsiid(asi.getAsiid(), true);
                    for (int j = 0; j < detasis.size(); j++) {
                        selectedUsus.add(detasis.get(j).getUsuario());
                    }
                    context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Búsqueda completa", "Los usuarios que ya estan marcados son los que ya se les registró su asistencia."));
                    context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Paso 1", "Marque los usuarios nuevos que desee registrar su asistencia o deseleccione los usuarios antiguos si les desea quitar su asistencia en dicha fecha."));
                    context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Paso 2", "Presione el botón: 'Actualizar asistencia' para guardar los cambios."));
                    
                    return;
                }
                asi.setAsiobservaciones(new String());
                accion = "Registrar asistencia";
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Búsqueda completa", "Marque los usuarios que asistieron en esta fecha y luego presione el botón 'Registrar asistencia' para guardar dicha asistencia."));
            }
            else
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Búsqueda Completa", "No hay usuarios inscritos en dicha fecha. Por favor, dirigase a la opcion: 'Inscripción mensual de usuarios' e inscribalos."));
        }
        catch(Exception ex)
        {
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Formato de fecha no válido.", "Seleccione una fecha correcta."));
        }
    }

    public Asistencia getAsi() {
        return asi;
    }

    public void setAsi(Asistencia asi) {
        this.asi = asi;
    }

    public AsistenciaFacade getEjbFacadeAsi() {
        return ejbFacadeAsi;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
        context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Para tomar la asistencia de usuarios, primero seleccione la fecha: (dia, mes, año) y luego presione: 'Buscar'."));
    }
}