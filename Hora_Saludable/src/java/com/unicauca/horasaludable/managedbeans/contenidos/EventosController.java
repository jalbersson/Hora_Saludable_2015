/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;


import com.unicauca.horasaludable.entities.Evento;
import com.unicauca.horasaludable.jpacontrollers.EventoFacade;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Angela & Yuri
 */
@ManagedBean
@SessionScoped
public class eventoController {

     @EJB
    EventoFacade ejbEvento;
    
    Evento evento;
    Evento detallesEvento;
    private List<Evento> eventos = new ArrayList();
    private List<Evento> ultimos = new ArrayList();
    String evefpublicacion;
    String eveTitulo;
    Long idE=null;
    private String imagen;
    private String path = "D:\\";
    private UploadedFile file;
    java.util.Date evefevento;
    
    
    public eventoController() {
        
        evento = new Evento() ;
       
        this.imagen = "default";
        File f = new File("."); // Creamos un objeto file

        String OS = System.getProperty("os.name").toLowerCase();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/");
        if (OS.contains("nux") || OS.contains("debian")) {
            this.path = realPath + "/resources/img/imagenesEventos/";
        } else {
            this.path = realPath + "\\resources\\img\\imagenesEventos\\";

        }
    }
    
    public Date convertToJavaDate(java.util.Date date)
    {
      java.sql.Date sqlDate = null;
        
      try
      {
        sqlDate = new java.sql.Date(date.getTime()); 
      }
      catch(Exception e)
      {   
      }    
      return sqlDate;
    }

    public String guardarEvento(){

        try
        {
            java.util.Date  fechaPublicado = new java.util.Date();
            this.evento.setEvefechapublicacion(convertToJavaDate(fechaPublicado));
            this.evento.setEvefechaevento(convertToJavaDate(evefevento));
            this.evento.setEveimagen(this.imagen + ".jpg");
            this.ejbEvento.create(this.evento);
            
        }
        catch(Exception e)
        {
            return "listarEventos";
        }

        return "principal";
    }
    
        public String eliminarEvento(Evento e)
    {
        try
        {
            this.ejbEvento.remove(e);
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Error: El evento no fue agregado!"));
            return "listarEventos";
        }

        return "principal";  
    }
    
    public void upload() {
        try {
            if (file != null) {
            //guardarNoticia();
                subirImagen();
            } else {
                this.imagen = "default";
            }
        } catch (Exception e) {
             this.imagen = "default";
        }
        
    }
    
    public void subirImagen() {

        File f = null;
        InputStream in = null;
        String ubicacionImagen = "";
        try {

            InputStream xx = file.getInputstream();
            InputStream fis = xx;
            BufferedImage image = ImageIO.read(fis); //reading the image file

            int rows = 1; //You should decide the values for rows and cols variables
            int cols = 1;
            int chunks = rows * cols;

            int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
            int chunkHeight = image.getHeight() / rows;
            int count = 0;

            /**
             * *******************
             */
            this.imagen = getNombreImagen();
            /**
             * *******************
             */

            BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    //Initialize the image array with image chunks
                    imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                    // draws the image chunk
                    Graphics2D gr = imgs[count++].createGraphics();
                    gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                    gr.dispose();
                }
            }

            //writing mini images into image files
            for (int i = 0; i < imgs.length; i++) {
                ImageIO.write(imgs[i], "jpg", new File(this.path + this.imagen + ".jpg"));
            }

            /*
             ubicacionImagen = this.path;
             FacesMessage msg = new FacesMessage("Success! ", " is uploaded.");
             FacesContext.getCurrentInstance().addMessage(null, msg);
             */
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Error", " No ha podido cargar la imagen.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        // Do what you want with the file        
    }

    String getNombreImagen() {
        int longitud = 15;
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }
    
    public String detalleEventoPrincipal(Long id)
    {
        idE=id;
        return "detalleEventoPrincipal";
    }
    
        public String detalleEventoEventos(Long id)
    {
        idE=id;
        return "detalleEventoEventos";
    }
    
    public String aleatorioArchivos() {

        int tam = 7;
        Random rn = new Random();
        return "imagen (" +(11+ rn.nextInt(tam)) + ").jpg";
    }

    /*public void asignacionImagenesAleatorias() {
        int tam = this.ultimos.size();
        for (int i = 0; i < tam; i++) {
            this.ultimos.get(i).setEveimagen(aleatorioArchivos());
        }
    }*/

    
    public Evento eventoDetallado()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        //eveTitulo=params.get("eventoId");
	idE = Long.parseLong(params.get("eventoId"));
        
        
        
        detallesEvento = new Evento();
        //long numero= (long)idE;
        for(int i=0;i<ultimos.size();i++)
        {
            if(ultimos.get(i).getEveid().equals(idE))
            {
                detallesEvento=ultimos.get(i);
            }
        }
            //detallesEvento=ultimos.get(numero);
            return detallesEvento;
    }
    
        public Evento eventoDetalladoTodos()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        //eveTitulo=params.get("eventoId");
	idE = Long.parseLong(params.get("eventoId"));
        
        this.eventos = this.ejbEvento.buscarEventos();
        
        detallesEvento = new Evento();
        //long numero= (long)idE;
        for(int i=0;i<eventos.size();i++)
        {
            if(eventos.get(i).getEveid().equals(idE))
            {
                detallesEvento=eventos.get(i);
            }
        }
            //detallesEvento=ultimos.get(numero);
            return detallesEvento;
    }
        
    public String cortarTitulo(String s)
    {
        if(s.length()>20)
        {
            return s.substring(0,20)+"...";
        }
        return s;
    }
    
        public String cortarLugar(String s)
    {
        if(s.length()>15)
        {
            return s.substring(0,15)+"...";
        }
        return s;
    }
        
    public String mostrarEventoEditar(Evento e)
    {
        
        return "editarEventoEspecifico?id="+e.getEveid();
    }
    
    public String mostrarEvento(Evento e) {

        return "detalleEventoPrincipal?id=" + e.getEveid();
    }
    
    public String getEveTitulo() {
       
        return eveTitulo;
    }

    public void setEveTitulo(String eveTitulo) {
        this.eveTitulo = eveTitulo;
    }
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getEvefpublicacion() {
        return evefpublicacion;
    }

    public void setEvefpublicacion(String evefpublicacion) {
        this.evefpublicacion = evefpublicacion;
    }

    public java.util.Date getEvefevento() {
        return evefevento;
    }

    public void setEvefevento(java.util.Date evefevento) {
        this.evefevento = evefevento;
    }
    
    public List<Evento> getEventos() {
        this.eventos = this.ejbEvento.buscarEventos();
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    public List<Evento> getUltimos() {
        try
        {
            this.ultimos = this.ejbEvento.ultimosEventos();
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "msgs", "Error: No se puede conectar con la base de datos !!!"));
        }
        return ultimos;
    }

    public void setUltimos(List<Evento> ultimos) {
        this.ultimos = ultimos;
    }
    
    
    public Evento getDetallesEvento() {
        return detallesEvento;
    }

    public void setDetallesEvento(Evento detallesEvento) {
        this.detallesEvento = detallesEvento;
    }
    
    public Long getIdE() {
        return idE;
    }

    public void setIdE(Long idE) {
        this.idE = idE;
    }
    
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UploadedFile getFile() {

        return file;
    }

    public void setFile(UploadedFile file) {
        /*FacesMessage msg = new FacesMessage("Imagen Cargada! ", this.path);
         FacesContext.getCurrentInstance().addMessage(null, msg);
         */
        this.file = file;
    }
    

    
}
