package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.entities.Asistencia;
import com.unicauca.horasaludable.entities.Detalleasistencia;
import com.unicauca.horasaludable.entities.DetalleasistenciaPK;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.AsistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.DetalleinscripcionFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.unicauca.horasaludable.utilidades.Utilidades;

@ManagedBean
@SessionScoped
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

    /**
     * Recupera la foto de la bd y la devuelve como un StreamedContent
     *
     * @return flujo de la imagen
     */
    public StreamedContent getImagenFlujo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Usuario usu = ejbFacadeUsu.buscarPorIdUsuario(Long.valueOf(id)).get(0);
            if (usu.getUsuFotoBD() == null) {
                return Utilidades.getImagenPorDefecto("foto");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(usu.getUsuFotoBD()));
            }
        }
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
        try {

            Calendar cal = Calendar.getInstance();
            cal.setTime(asi.getAsifecha());
            //Buscar los usuarios activos en inscripcion y detalleinscripcion
            usuarios = ejbFacadeDetins.usariosActivos(true, String.valueOf(cal.get(Calendar.MONTH) + 1), cal.get(Calendar.YEAR), "");

            if (usuarios.size() > 0) {
                selectedUsus = new ArrayList();
                //Crea una nueva asistencia tiene fecha y observaciones
                asi = new Asistencia();
                asi.setAsifecha(cal.getTime());
                //Busca el registro de la asistencia en ese año y mes
                List<Asistencia> lstAsis = ejbFacadeAsi.findByYearMonthDay(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

                if (lstAsis.size() > 0) {
                    //Devuelve la asistencia
                    asi = lstAsis.get(0);
                    accion = "Actualizar asistencia";
                    //Consulta los detalles de la asistencia, con los usuario que asistieron
                    List<Detalleasistencia> detasis = ejbFacadeDetasi.obtenerPorAsiid(asi.getAsiid(), true);
                    //Se recorre cada detalle y extrae los usuario y los copia en 
                    //el ArrayList<Usuario>selectedUsus el cual representa los que asistieron 
                    //(apareceran con check en la DataTable)
                    for (int j = 0; j < detasis.size(); j++) {
                        selectedUsus.add(detasis.get(j).getUsuario());
                    }
                    return;
                }
                asi.setAsiobservaciones(new String());
                accion = "Registrar asistencia";
            } else {
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Búsqueda Completa", "No hay usuarios inscritos en dicha fecha. Por favor, dirigase a la opcion: 'Inscripción mensual de usuarios' e inscribalos."));
            }
        } catch (Exception ex) {
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
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        if (value == null) {
            return false;
        }
        List<Usuario> lstUsu2 = ejbFacadeUsu.buscarPorContenidoNombres(filterText);

        for (int i = 0; i < lstUsu2.size(); i++) {
            if (value.toString().trim().equals(lstUsu2.get(i).getUsunombres().trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean filtrarPorApellido(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        if (value == null) {
            return false;
        }
        List<Usuario> lstUsu2 = ejbFacadeUsu.buscarPorContenidoApellidos(filterText);

        for (int i = 0; i < lstUsu2.size(); i++) {
            if (value.toString().trim().equals(lstUsu2.get(i).getUsuapellidos().trim())) {
                return true;
            }
        }
        return false;
    }

}
