/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import com.unicauca.horasaludable.cifrado.Cifrar;
import com.unicauca.horasaludable.entities.Recuperarcontrasena;
import com.unicauca.horasaludable.jpacontrollers.RecuperarcontrasenaFacade;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author GORDA
 */
@ManagedBean
@RequestScoped
public class RecuperarContraseniaController {
    
    @EJB
    private UsuarioFacade ejbUsuario;
    @EJB
    private RecuperarcontrasenaFacade ejbRecuperarcontrasena;
    
    private String correo;
    private Usuario usuario;
    private Recuperarcontrasena recuperarContrasena;
    private String to;
    private String from;
    private String message;
    private String subject;
    private String smtpServ;
    private Cifrar cifrado;
    
        
    public RecuperarContraseniaController() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    ////
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSmtpServ() {
        return smtpServ;
    }

    public void setSmtpServ(String smtpServ) {
        this.smtpServ = smtpServ;
    }
    
    ///
    
    public void recuperar(){
        try{
            usuario = ejbUsuario.buscarUsuarioPorEmail(correo);
            if(usuario != null){
                sendMail();
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/usuario/recuperarcontrasenia/validar.xhtml");
            }else{
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se pudo encontrar el correo",""));
            }
        }catch(Exception e){
        }
    }
    
    public void sendMail(){
        HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();      
        
        to = correo;
        from = "horasaludable@gmail.com";
        
        subject = "Recuperar contraseña Hora Saludable";
        smtpServ = "smtp.gmail.com";
        String pass = "hora.saludable";
        String idcifrado = cifrado.sha256(usuario.getUsuid().toString());
        //String idcifrado = usuario.getUsuid().toString();
        recuperarContrasena = new Recuperarcontrasena();
        recuperarContrasena.setReid(usuario.getUsuid());
        recuperarContrasena.setReidcifrado(idcifrado);        
        if(ejbRecuperarcontrasena.buscarRecuperarContrasenaCifrado(idcifrado)==null)
        {
            ejbRecuperarcontrasena.create(recuperarContrasena);
        }                
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/faces/usuario/recuperarcontrasenia/cambiarContrasenia.xhtml?ifo="+ idcifrado;
        message = "<h1> Hola "+usuario.getUsunombres()+" Hemos recibido tu solicitud de cambio de contraseña, para hacer el"
                + " cambio haz clic <a href="+url+">Aqui</a></h1>";
         
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpServ);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);

        Session session = Session.getDefaultInstance(props);//autentificar el correo

        try {
            MimeMessage message = new MimeMessage(session);//se inicia una session
            message.setFrom(new InternetAddress(this.from));
            message.addRecipient(Message.RecipientType.TO,
                           new InternetAddress(to));
            message.setSubject(this.subject);
            message.setText(this.message, "ISO-8859-1", "html");

            Transport transport = session.getTransport("smtp");
            transport.connect(smtpServ, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("A+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }       
    }
}
