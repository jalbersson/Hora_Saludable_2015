/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Recuperarcontrasena;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import static java.lang.ProcessBuilder.Redirect.to;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Yamid
 */
@ManagedBean
@RequestScoped
public class envioCorreo {

    /**
     * Creates a new instance of envioCorreo
     */
    @EJB
    private UsuarioFacade ejbUsuario;
    private String from = "horasaludable@gmail.com";
    private String subject;
    private String smtpServ = "smtp.gmail.com";
    private String pass = "hora.saludable";
    private String url = "http://localhost:8080/Hora_Saludable/faces/usuario/recuperarcontrasenia/cambiarContrasenia.xhtml?ifo=" + "id_cifrado_prueba";
    private String message_ = "";

    List<String> listaCorreos;

    public envioCorreo() {
        listaCorreos = new ArrayList();

    }

    public void cargarCorreos() {
        List<Usuario> todosUsuarios = ejbUsuario.buscarTodos();
        int indexEmails = 0;
        String dirEmail = "";
        /*
         for (int i = 0; i < todosUsuarios.size(); i++) {
         dirEmail = todosUsuarios.get(i).getUsuemail();
         if (validateEmail(todosUsuarios.get(i).getUsuemail())) {
         listaCorreos.set(indexEmails, dirEmail);
         message_ = message_  + dirEmail;
         indexEmails++;
         }
         }*/

        for (int i = 0; i < todosUsuarios.size(); i++) {
            dirEmail = todosUsuarios.get(i).getUsuemail();
            if (validateEmail(dirEmail)) {
                listaCorreos.add(dirEmail);
            }
        }

    }

    public void sendMail() {
        
        
        cargarCorreos();
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.smtpServ);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", this.from);
        props.put("mail.smtp.password", this.pass);

        Session session = Session.getDefaultInstance(props);//autentificar el correo

        try {
            MimeMessage message = new MimeMessage(session);//se inicia una session
            message.setFrom(new InternetAddress(this.from));

            for (int i = 0; i < listaCorreos.size(); i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.listaCorreos.get(i)));
            }

            message.setSubject(this.subject);

            message.setText(this.message_, "ISO-8859-1", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect(this.smtpServ, this.from, this.pass);
            transport.sendMessage(message, message.getAllRecipients());
            //FacesMessage messages = new FacesMessage(FacesMessage.SEVERITY_INFO, "Envio de mensaje masivo", "Mensaje enviado con exito.");
            //RequestContext.getCurrentInstance().showMessageInDialog(messages);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El correo se ha enviado!"));

            transport.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } finally {

            this.message_ = "";
            this.subject = "";
        }
    }


    /**
     * VALIDACION DE DIRECCIONES DE CORREO*
     */
    /**
     * Codigo tomado de:
     * https://amatellanes.wordpress.com/2013/05/29/java-validar-direccion-de-correo-electronico-en-java/
     * *
     */
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate given email with regular expression.
     *
     * @param email email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validateEmail(String email) {

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
        //return true;
    }

    public List<String> getListaCorreos() {
        return listaCorreos;
    }

    public void setListaCorreos(List<String> listaCorreos) {
        this.listaCorreos = listaCorreos;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage_() {
        return message_;
    }

    public void setMessage_(String message_) {
        this.message_ = message_;
    }

}
