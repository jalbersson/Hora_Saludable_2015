/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

import com.unicauca.horasaludable.entities.Cargo;
import com.unicauca.horasaludable.entities.Medida;
import com.unicauca.horasaludable.entities.Unidadacademica;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import com.unicauca.horasaludable.utilidades.Utilidades;

/**
 *
 * @author Jhonny Taborda
 */
@ManagedBean
@SessionScoped
public class MostrarUsuarioTestController {

    @EJB
    private UsuarioFacade usuarioEJB;

    private Usuario usuario;
    private Medida Medidaactual;

    private String rutaFoto;
    private String rutaAbsolutaFotos;
    private UploadedFile foto;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private SimpleDateFormat sdf;
    private float peso;
    private int edad;
    private String tipoUsuario;
    private List<Cargo> listaCargo;
    private List<Unidadacademica> listaUnidadAcademica;
    private Long idCargo;
    private Long idUnidadAcademica;
    private Usuario funcionarioFamiliar;
    private List<Usuario> listaFuncionarios;
    private String nombreOApellidos;
    private boolean campoFoto;
    private String sexo;

    public MostrarUsuarioTestController() {
        this.rutaFoto = "img/fotosUploads";
        this.rutaAbsolutaFotos = "/home/geovanny/Documentos/Asae/Hora_Saludable_2015/Hora_Saludable/web/resources/img/fotosUploads/";
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    @PostConstruct
    private void init() {
        this.buscarUsuario();
        this.definirSexo();
        this.calcularEdad();
        this.campoFoto = true;
    }

    /**
     * Recupera de la bd la imagen
     *
     * @return el flujo de bytes de la imagen
     */
    public StreamedContent getImagenFlujo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            String id = context.getExternalContext().getRequestParameterMap().get("idUsu");
            Usuario usu = usuarioEJB.buscarPorIdUsuario(Long.valueOf(id)).get(0);
            if (usuario.getUsuFotoBD() == null) {
                return Utilidades.getImagenPorDefecto("foto");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(usu.getUsuFotoBD()));
            }
        }
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isCampoFoto() {
        return campoFoto;
    }

    public void setCampoFoto(boolean campoFoto) {
        this.campoFoto = campoFoto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombreOApellidos() {
        return nombreOApellidos;
    }

    public void setNombreOApellidos(String nombreOApellidos) {
        this.nombreOApellidos = nombreOApellidos;
    }

    public List<Usuario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Usuario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public Usuario getFuncionarioFamiliar() {
        return funcionarioFamiliar;
    }

    public void setFuncionarioFamiliar(Usuario funcionarioFamiliar) {
        this.funcionarioFamiliar = funcionarioFamiliar;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public Long getIdUnidadAcademica() {
        return idUnidadAcademica;
    }

    public void setIdUnidadAcademica(Long idUnidadAcademica) {
        this.idUnidadAcademica = idUnidadAcademica;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public List<Cargo> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<Cargo> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public List<Unidadacademica> getListaUnidadAcademica() {
        return listaUnidadAcademica;
    }

    public void setUnidadAcademica(List<Unidadacademica> listaUnidadAcademica) {
        this.listaUnidadAcademica = listaUnidadAcademica;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    public String getRutaAbsolutaFotos() {
        return rutaAbsolutaFotos;
    }

    public void setRutaAbsolutaFotos(String rutaAbsolutaFotos) {
        this.rutaAbsolutaFotos = rutaAbsolutaFotos;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Medida getMedidaactual() {
        return Medidaactual;
    }

    public void setMedidaactual(Medida Medidaactual) {
        this.Medidaactual = Medidaactual;
    }

    public void estudianteSeleccionado(Usuario estudiante) throws IOException {

        this.usuario = estudiante;
        this.campoFoto = true;

        FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/administrador/medidas/GestionTest.xhtml");

    }

    public void estudianteSeleccionadoUsu(String login) throws IOException {

        this.usuario = usuarioEJB.buscarUsuarioPorNombreUsuario(login).get(0);
        this.calcularEdad();
        this.definirSexo();
        this.campoFoto = true;

        FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/usuario/medidas/GestionTest.xhtml");

    }

    public void medicionSeleccionada(Medida medida) throws IOException {

        this.Medidaactual = medida;

        FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/administrador/medidas/Test.xhtml");

    }

    public void medicionSeleccionadaUsu(Medida medida) throws IOException {

        this.Medidaactual = medida;

        FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/usuario/medidas/Test.xhtml");

    }

    public void calcularEdad() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_nac = formato.format(this.usuario.getUsufechanacimiento());
        Date fechaActual = new Date();
        String hoy = formato.format(fechaActual);
        String[] dat1 = fecha_nac.split("/");
        String[] dat2 = hoy.split("/");
        this.edad = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
        int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
        if (mes < 0) {
            this.edad = this.edad - 1;
        } else if (mes == 0) {
            int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
            if (dia > 0) {
                this.edad = this.edad - 1;
            }
        }
    }

    private void definirSexo() {
        if (this.usuario.getUsugenero().equals('M')) {
            this.sexo = "Masculino";
        } else {
            this.sexo = "Femenino";
        }
    }

    private void buscarUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() != null) {
            this.usuario = this.usuarioEJB.retornarBuscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0);
        }
    }

    public Usuario getUsuarioPorId(int id) {
        return null;
    }
}
