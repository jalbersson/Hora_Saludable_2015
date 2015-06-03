/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Yuri
 */
@ManagedBean
@RequestScoped
public class imagenSubirController {

    private String imagen;
    private String path = "D:\\";

    private UploadedFile file;

    public imagenSubirController() {

        this.imagen = "default";
        File f = new File("."); // Creamos un objeto file

        String OS = System.getProperty("os.name").toLowerCase();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        if(OS.contains("nux") || OS.contains("debian"))
        {
            this.path = realPath + "/resources/img/imagenSalud/";
           
        }
        else
        {
            this.path = realPath + "\\resources\\img\\imagenSalud\\";
        }

    }

    public void upload() {
        try {
            if (file != null) {
                //guardarImagen();
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

            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("album.xhtml");

        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Error, no hay imagen seleccionada", " No ha podido cargar la imagen.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
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
        this.file = file;
    }

}
