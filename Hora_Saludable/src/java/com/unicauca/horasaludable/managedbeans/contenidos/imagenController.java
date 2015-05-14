/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Yuri
 */
@ManagedBean
@ViewScoped
public class imagenController {

    //atributos
    @EJB
    private String ruta;
    private UploadedFile fotoAlbum;

    //metodos
    public imagenController() {
        this.ruta = "D:\\imagenes\\";
    }

    public void cargarFoto(FileUploadEvent event) {

        this.fotoAlbum = event.getFile();

    }

    public void actualizarFoto() throws InterruptedException {

        if (this.fotoAlbum != null) {

            try {

                this.GuardarFoto(this.fotoAlbum.getFileName(), this.fotoAlbum.getInputstream());//este es el metodo que realmentesube la foto al servidor

            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
            this.fotoAlbum = null;

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha cargado una foto.", "No se ha cargado una foto"));
        }

    }

    private void GuardarFoto(String filename, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(this.ruta + filename));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
