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
import java.io.Serializable;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Yuri
 */
@ManagedBean
@ViewScoped
public class eventoVerEditarController implements Serializable {

    @EJB
    EventoFacade ejbEvento;

    Evento evento;
    long id;
    private String imagen;
    private String path = "D:\\";
    private UploadedFile file;
    
    String dir2;
    File f2;
    
    @PostConstruct
    public void init() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        id = Long.parseLong(params.get("id"));
        evento = ejbEvento.mostrarEvento(id);
    }

    public void limpiar()
    {
        evento.setEvetitulo(" ");
        evento.setEvelugar(" ");
        evento.setEvefechaevento(null);
        evento.setEvecontenido(" ");
        
    }
    
    public eventoVerEditarController() {
        
        this.imagen = "default";
        File f = new File("."); // Creamos un objeto file
        
        String OS = System.getProperty("os.name").toLowerCase();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        if(OS.contains("nux") || OS.contains("debian"))
        {
            this.path = realPath + "/resources/img/imagenesEventos/";
           
        }
        else
        {
            this.path = realPath + "\\resources\\img\\imagenesEventos\\";
        }       
        
        FacesMessage msg = new FacesMessage("Ubicacion del momento", this.path);

        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    

    public String actualizarEvento() {
        
        if(file != null)
        {
            String borrar = this.evento.getEveimagen();
            if(eliminarImagen(borrar))
            {
                this.evento.setEveimagen(this.imagen + ".jpg");
            }
        }
        
        ejbEvento.edit(evento);

        return "listarEventos";
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
        } catch (Exception ex) {
            
        }   
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
    
    public boolean eliminarImagen(String nombre) {
        
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        dir2 = realPath + "\\resources\\img\\imagenesEventos\\" +nombre;
 
        f2 = new File(dir2);
        if (f2.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
