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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Yuri
 */
@ManagedBean
@RequestScoped
public class imagenController {

    @EJB
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

    public imagenController() {
        this.imagen = this.path + "album.jpg";

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
