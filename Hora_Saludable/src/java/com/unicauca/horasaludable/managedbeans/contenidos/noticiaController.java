/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Noticia;
import com.unicauca.horasaludable.jpacontrollers.NoticiaFacade;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.swing.JOptionPane;
import javax.xml.crypto.Data;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Yamid
 */
@ManagedBean
@RequestScoped
public class noticiaController {

    @EJB
    private NoticiaFacade ebjNoticiaFacade = new NoticiaFacade();
    Boolean visible = true;
    Noticia noticia;

    private String titulo;
    private Date fechapublicacion;
    private Date fechaedicion;
    private String contenido;
    private String imagen;
    private String path = "D:\\";

    private UploadedFile file;

    public String otro() {
        try {
            System.out.println("Esto esta funcionando");
            return "agregarEvento";
        } catch (Exception e) {
            System.out.println("Esto no esta funcionando");
            return "principal";
        }

    }

    public noticiaController() {
        this.visible = true;
        this.titulo = "Aqui va el titulo";
        this.fechapublicacion = new Date();
        this.fechaedicion = new Date();
        this.contenido = "Aqui va el contenido";

        //this.path = "..\\..\\img\\imgNoticias\\";
        //this.path = "D:\\imagenesNoticias\\";
        this.imagen = this.path + "noticia.jpg";

        File f = new File("."); // Creamos un objeto file
        //System.out.println(f.getAbsolutePath()); // Llamamos al método que devuelve la ruta absoluta

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        this.path = realPath + "\\resources\\img\\imagenesNoticias\\";
        FacesMessage msg = new FacesMessage("Ubicacion del momento", this.path);
         //msg = new FacesMessage("Ubicacion del momento", );

        FacesContext.getCurrentInstance().addMessage(null, msg);
        //FacesMessage msg = new FacesMessage("Ubicacion del momento", System.getProperty("user.dir"));
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        //this.path = new File("").getAbsolutePath()+"\\imagenesNoticias\\";

    }

    public String guardarNoticia() {

        try {
            //imagen = "../../resources/imagenNoticia.jpg";
            noticia = new Noticia();
            Date fecha = new java.util.Date();
            this.noticia.setNottitulo(this.titulo);
            this.noticia.setNotfechapublicacion(convertStringToDate(fecha));
            this.noticia.setNotfechaedicion(convertStringToDate(fecha));
            this.noticia.setNotvisible(this.visible);
            this.noticia.setNotcontenido(this.contenido);
            this.noticia.setNotimagen(this.imagen + ".jpg");
            this.ebjNoticiaFacade.create(this.noticia);
        } catch (Exception e) {
            return "principal";
        }

        return "listarNoticias";
    }

    public static Date convertStringToDate(java.util.Date date) {
        java.sql.Date sqlDate = null;

        try {
            sqlDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
        }
        return sqlDate;
    }

    public void upload() {
        if (file != null) {
            //guardarNoticia();
            subirImagen();
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

    public NoticiaFacade getEbjNoticiaFacade() {
        return ebjNoticiaFacade;
    }

    public void setEbjNoticiaFacade(NoticiaFacade ebjNoticiaFacade) {
        this.ebjNoticiaFacade = ebjNoticiaFacade;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Date fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public Date getFechaedicion() {
        return fechaedicion;
    }

    public void setFechaedicion(Date fechaedicion) {
        this.fechaedicion = fechaedicion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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
