package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Evento;
import com.unicauca.horasaludable.entities.Noticia;
import com.unicauca.horasaludable.jpacontrollers.EventoFacade;
import com.unicauca.horasaludable.utilidades.RedimensionadorImagenes;
import com.unicauca.horasaludable.utilidades.Utilidades;
import static com.unicauca.horasaludable.utilidades.Utilidades.redireccionar;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Angela & Yuri
 */
@ManagedBean
@SessionScoped
public class EventosController {

    @EJB
    private EventoFacade ejbEvento;

    private Evento selected;

    private UploadedFile file;
    Long idE = null;
    
    private String titulo;

    public Long getIdE() {
        return idE;
    }

    public void setIdE(Long idE) {
        this.idE = idE;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Evento eventoDetallado() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        //eveTitulo=params.get("eventoId");
        idE = Long.parseLong(params.get("eventoId"));
    
        Evento eve = this.ejbEvento.buscarEventoPorId(idE);
        return eve;
    }

    public EventosController() {
        selected = new Evento();
        file = null;
    }
    
    public void verEvento(Evento evt){
        
    }

    public void seleccionarEvento() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        long id = Long.parseLong(params.get("eventoId"));

        this.selected = this.ejbEvento.buscarEventoPorId(id);

    }

    public void nuevoEvento() {
        selected = new Evento();
        redireccionar("agregarEvento.xhtml");
    }

    public void prepareLeerMas(Evento evento) {
        selected = evento;
    }

    /**
     * Recupera de la bd la imagen del evento
     *
     * @return el flujo de bytes de la imagen
     */
    public StreamedContent getImagenFlujo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            String id = context.getExternalContext().getRequestParameterMap().get("idEve");
            Evento evento = ejbEvento.buscarEventoPorId(Long.valueOf(id));
            if (evento.getEveImagen() == null) {
                return Utilidades.getImagenPorDefecto("noticia");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(evento.getEveImagen()));
            }
        }
    }

    public Evento getSelected() {
        return selected;
    }

    public Date convertToJavaDate(java.util.Date date) {
        java.sql.Date sqlDate = null;

        try {
            sqlDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
        }
        return sqlDate;
    }

    public void eliminarEvento(Evento e) {
        try {
            this.ejbEvento.remove(e);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Error: El evento no fue eliminado!"));
        }

        Utilidades.redireccionar("listarEventos.xhtml");
    }

    public String detalleEventoPrincipal(Long id) {
        idE = id;
        return "detalleEventoPrincipal";
    }

    public String detalleEventoEventos(Long id) {
        idE = id;
        return "detalleEventoEventos";
    }

    public Evento getEventoSeleccionado() {
        //if (selected == null) {
            seleccionarEvento();
        //}
        return selected;
    }

    public String cortarTitulo(String s) {
        if (s.length() > 20) {
            return s.substring(0, 20) + "...";
        }
        return s;
    }

    public String cortarLugar(String s) {
        if (s.length() > 15) {
            return s.substring(0, 15) + "...";
        }
        return s;
    }

    public void editarEvento(Evento e) {
        selected = e;
        Utilidades.redireccionar("editarEvento.xhtml");
    }

    public String mostrarEvento(Evento e) {

        return "detalleEventoPrincipal?id=" + e.getEveid();
    }

    public Evento getEvento() {
        return selected;
    }

    public void setEvento(Evento evento) {
        this.selected = evento;
    }

    public List<Evento> getEventos() {
        return this.ejbEvento.buscarEventos();
    }

    public List<Evento> getUltimos() {
        try {
            return this.ejbEvento.ultimosEventos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "msgs", "Error: No se puede conectar con la base de datos !!!"));
            return null;
        }
    }

    public UploadedFile getFile() {

        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void actualizarEvento() {
        try {
            if (!this.file.getFileName().equals("")) {

                InputStream fi = file.getInputstream();
                byte[] buffer = RedimensionadorImagenes.redimensionar(fi, 200);
                selected.setEveImagen(buffer);

            }
            this.ejbEvento.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento editado con éxito", "Edición exitosa"));
            this.file = null;
            redireccionar("listarEventos.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al grabar evento", "Error"));
        }

    }

    public void agregarEvento() {
        if (selected==null)
            selected = new Evento();
        try {
            if (this.file.getFileName().equals("")) {

                StreamedContent imagen = Utilidades.getImagenPorDefecto("noticia");
                selected.setEveImagen(Utilidades.StreameadContentToByte(imagen));
            } else {

                InputStream fi = file.getInputstream();
                byte[] buffer = RedimensionadorImagenes.redimensionar(fi, 200);
                selected.setEveImagen(buffer);

            }
            this.ejbEvento.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento agregado con éxito", "Edición exitosa"));
            this.file = null;
            Utilidades.redireccionar("listarEventos.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al agregar el evento", "Error"));
        }

    }

}
