
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.cifrado.Cifrar;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import com.unicauca.horasaludable.validadores.ValidarEdicionUsuarios;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import com.unicauca.horasaludable.utilidades.RedimensionadorImagenes;
import com.unicauca.horasaludable.utilidades.Utilidades;

/**
 *
 * @author Geovanny
 * Maneja los eventos de la vista de administrador/usuarios/perfilAdministrador.xhtml y de /usuario/usuarios/perfilUsuario.xhtml
 */
@ManagedBean
@SessionScoped
public class perfilUsuarioController implements Serializable {

    @EJB
    private UsuarioFacade usuarioEJB;
    //private String rutaFoto;
    private Usuario usuario;
    private String tipo;
    private UploadedFile uploadedFileFoto;
    private boolean estudiante;
    private boolean funcionario;
    private boolean familiar;
    private boolean mostrarFoto;
    private boolean mostrarFecNac;
    private boolean mostrarTelefono;
    private boolean mostrarNombres;
    private boolean mostrarApellidos;
    private boolean mostrarIdentificacion;
    private boolean mostrarEmail;
    private boolean mostrarSexo;
    private boolean mostrarContrasena;
    
    private String nombres;
    private String apellidos;
    private Character sexo;
    private String email;
    private Long identificacion;
    
    private String telefono;
    private Date fechaNacimiento;
    private SimpleDateFormat sdf;
    private String contrasena;
    private String confirmarContrasena;
    

    private ValidarEdicionUsuarios validarEdicionUsuario;

    public perfilUsuarioController() {
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    @PostConstruct
    private void init() {
        this.buscarUsuario();
        this.definirSexo();
        this.definirTipo();
        this.inicializarCampos();

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    private void buscarUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() != null) {
            this.usuario = this.usuarioEJB.retornarBuscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0);
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }

    public void setConfirmarContrasena(String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }

    public boolean isMostrarContrasena() {
        return mostrarContrasena;
    }

    public void setMostrarContrasena(boolean mostrarContrasena) {
        this.mostrarContrasena = mostrarContrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isMostrarFecNac() {
        return mostrarFecNac;
    }

    public void setMostrarFecNac(boolean mostrarFecNac) {
        this.mostrarFecNac = mostrarFecNac;
    }

    public boolean isMostrarTelefono() {
        return mostrarTelefono;
    }

    public void setMostrarTelefono(boolean mostrarTelefono) {
        this.mostrarTelefono = mostrarTelefono;
    }

    public UploadedFile getFoto() {
        return uploadedFileFoto;
    }

    public void setFoto(UploadedFile foto) {
        this.uploadedFileFoto = foto;
    }

    public boolean isMostrarFoto() {
        return mostrarFoto;
    }

    public void setMostrarFoto(boolean mostrarFoto) {
        this.mostrarFoto = mostrarFoto;
    }

    public boolean isEstudiante() {
        return estudiante;
    }

    public void setEstudiante(boolean estudiante) {
        this.estudiante = estudiante;
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isFamiliar() {
        return familiar;
    }

    public void setFamiliar(boolean familiar) {
        this.familiar = familiar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



    private void definirSexo() {
        if (this.usuario.getUsugenero().equals('M')) {
            this.sexo = 'M';
        } else {
            this.sexo = 'F';
        }
    }

    public boolean isMostrarNombres() {
        return mostrarNombres;
    }

    public void setMostrarNombres(boolean mostrarNombres) {
        this.mostrarNombres = mostrarNombres;
    }

    public boolean isMostrarApellidos() {
        return mostrarApellidos;
    }

    public void setMostrarApellidos(boolean mostrarApellidos) {
        this.mostrarApellidos = mostrarApellidos;
    }

    public boolean isMostrarIdentificacion() {
        return mostrarIdentificacion;
    }

    public void setMostrarIdentificacion(boolean mostrarIdentificacion) {
        this.mostrarIdentificacion = mostrarIdentificacion;
    }

    public boolean isMostrarEmail() {
        return mostrarEmail;
    }

    public void setMostrarEmail(boolean mostrarEmail) {
        this.mostrarEmail = mostrarEmail;
    }

    public boolean isMostrarSexo() {
        return mostrarSexo;
    }

    public void setMostrarSexo(boolean mostrarSexo) {
        this.mostrarSexo = mostrarSexo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    private void definirTipo() {
        this.estudiante = false;
        this.familiar = false;
        this.funcionario = false;
        if (this.usuario.getUniid() != null) {
            if (this.usuario.getCarid() != null) {
                this.tipo = "Funcionario";
                this.funcionario = true;
            } else {
                this.tipo = "Estudiante";
                this.estudiante = true;
            }
        } else {
            if (this.usuario.getConyugeid() != null) {
                this.tipo = "Familiar";
                this.familiar = true;
            }
        }
    }

    private void inicializarCampos() {
        this.mostrarFoto = true;
        this.mostrarNombres = true; 
        this.mostrarApellidos = true; 
        this.mostrarEmail = true; 
        this.mostrarSexo = true; 
        this.mostrarIdentificacion = true;
        this.mostrarTelefono = true;
        this.mostrarContrasena = true;
        this.mostrarFecNac = true;
    }

    public void cargarFoto(FileUploadEvent event) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.uploadedFileFoto = event.getFile();
        requestContext.update("formularioPerfilFotoUsuario");

    }

    public void mostraSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarFoto = false;
        requestContext.update("formularioPerfilFotoUsuario");
        requestContext.update("formularioEditarFoto");
    }

    public void actualizarFoto() throws InterruptedException {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.uploadedFileFoto != null) {
            this.mostrarFoto = true;

            try {
                InputStream fi = uploadedFileFoto.getInputstream();
                byte[] buffer = RedimensionadorImagenes.redimensionar(fi, 200);
                usuario.setUsuFotoBD(buffer);
                this.usuarioEJB.edit(usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto Actualizada exitosamente. Fresione F5 para refrescarla", "Foto Actualizada."));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Thread.sleep(2000);
            this.uploadedFileFoto = null;

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha cargado una foto.", "No se ha cargado una foto"));
        }
        requestContext.update("formularioPerfilFotoUsuario");
        requestContext.update("formularioEditarFoto");
    }

    public void cancelarSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarFoto = true;
        this.uploadedFileFoto = null;
        requestContext.update("formularioPerfilFotoUsuario");
        requestContext.update("formularioEditarFoto");

    }
    public void mostrarModificarIdentificacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarIdentificacion = false;
        this.identificacion = this.usuario.getUsuidentificacion();
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void mostrarModificarNombres() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarNombres = false;
        if (this.usuario.getUsunombres()!= null) {
            this.nombres = this.usuario.getUsunombres()+ "";
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void mostrarModificarApellidos() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarApellidos = false;
        if (this.usuario.getUsuapellidos()!= null) {
            this.apellidos = this.usuario.getUsuapellidos()+ "";
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void mostrarModificarEmail() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarEmail = false;
        if (this.usuario.getUsuemail()!= null) {
            this.email = this.usuario.getUsuemail()+ "";
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void mostrarModificarTelefono() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarTelefono = false;
        if (this.usuario.getUsutelefono() != null) {
            this.telefono = this.usuario.getUsutelefono() + "";
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void mostrarModificarFecNac() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarFecNac = false;
        this.fechaNacimiento = new Date(this.usuario.getUsufechanacimiento().getYear(), this.usuario.getUsufechanacimiento().getMonth(), this.usuario.getUsufechanacimiento().getDate());
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void mostrarModificarSexo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarSexo = false;
        if (this.usuario.getUsugenero()!= null) {
            this.sexo = this.usuario.getUsugenero();
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    
    public void cancelarActualizarIdentificacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarIdentificacion = true;
        this.identificacion = 0L;
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void cancelarActualizarNombres() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarNombres = true;
        this.nombres = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void cancelarActualizarApellidos() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarApellidos = true;
        this.apellidos = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void cancelarActualizarEmail() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarEmail = true;
        this.email = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void cancelarActualizarTelefono() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarTelefono = true;
        this.telefono = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void cancelarActualizarFecNac() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarFecNac = true;
        this.fechaNacimiento = new Date();
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void actualizarIdentificacion() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarNumeroIdentificacion(this.identificacion+"", this.usuarioEJB)) {
            this.mostrarIdentificacion = true;
            this.usuario.setUsuidentificacion(this.identificacion);
            this.usuarioEJB.edit(this.usuario);
            this.identificacion = 0L;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Número de identificación actualizado con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void cancelarActualizarSexo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarSexo = true;
        this.sexo = 'M';
        requestContext.update("formularioPerfilDatosPersonales");
    }
    
    public void actualizarFecNac() {
        
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarFechaNacimiento(this.fechaNacimiento)) {
            this.mostrarFecNac = true;
            this.usuario.setUsufechanacimiento(this.fechaNacimiento);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fecha nacimiento actualizada con éxito", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
      
        
    }
    public void actualizarNombres() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarNombres(this.nombres)) {
            this.mostrarNombres = true;
            this.usuario.setUsunombres(this.nombres);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nombres actualizados con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void actualizarApellidos() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarApellidos(this.apellidos)) {
            this.mostrarApellidos = true;
            this.usuario.setUsuapellidos(this.apellidos);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Apellidos actualizados con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void actualizarEmail() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarCorreo(this.email, this.usuarioEJB)) {
            this.mostrarEmail = true;
            this.usuario.setUsuemail(this.email);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correo electrónico actualizado con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    
    public void actualizarTelefono() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarTelefono(this.telefono)) {
            this.mostrarTelefono = true;
            if (!this.telefono.isEmpty()) {
                BigInteger bi = new BigInteger(this.telefono);
                this.usuario.setUsutelefono(bi);
            } else {
                this.usuario.setUsutelefono(null);
            }
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo teléfono actualizado.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void actualizarSexo() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarGenero(this.sexo)) {
            this.mostrarSexo = true;
            this.usuario.setUsugenero(this.sexo);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sexo actualizado con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void mostrarModificarContrasena() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarContrasena = false;
        requestContext.update("formularioPerfilDatosCuenta");
    }

    public void cancelarActualizarContrasena() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarContrasena = true;
        this.contrasena = "";
        this.confirmarContrasena = "";
        requestContext.update("formularioPerfilDatosCuenta");
    }

    public void cambiarContrasena() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarContrasenaConConfirmacion(this.contrasena, this.confirmarContrasena)) {
            this.usuario.setUsucontrasena(Cifrar.sha256(this.contrasena));
            this.usuarioEJB.edit(this.usuario);
            this.mostrarContrasena = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Se cambio la contraseña correctamente.", ""));

        }
        requestContext.update("formularioPerfilDatosCuenta");

    }
}
