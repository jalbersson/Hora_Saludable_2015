/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.utilidades;

import com.unicauca.horasaludable.managedbeans.usuarios.MostrarUsuariosController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class Utilidades {

    /**
     * Devuelve la imagen por defecto para las fotos: vacio.jpg
     *
     * @param tipo foto ó noticia
     * @return un flujo que se carga en el componente p:graphicsImage
     */
    public static StreamedContent getImagenPorDefecto(String tipo) {
        try {
            String OS = System.getProperty("os.name").toLowerCase();
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String absoluteDiskPath = (String) servletContext.getRealPath("/");
            if (OS.contains("nux")) {
                if (tipo.equals("foto")) {
                    absoluteDiskPath = absoluteDiskPath.replace("build/", "") + "/resources/img/fotoDefecto.jpg";
                }
                if (tipo.equals("noticia")) {
                    absoluteDiskPath = absoluteDiskPath.replace("build/", "") + "/resources/img/noticiaDefecto.jpg";
                }
            } else {
                if (tipo.equals("foto")) {
                    absoluteDiskPath = absoluteDiskPath.replace("build\\", "") + "resources\\img\\fotoDefecto.jpg";
                }
                if (tipo.equals("noticia")) {
                    absoluteDiskPath = absoluteDiskPath.replace("build\\", "") + "resources\\img\\noticiaDefecto.jpg";
                }

            }

            File file = new File(absoluteDiskPath);
            return new DefaultStreamedContent(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MostrarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Convierte una imagen de StreamedContent a byte[]
     * @param img imagen en byte[]
     * @return 
     */
    public static byte[] StreameadContentToByte(StreamedContent img ) {
        try {
            InputStream is = img.getStream();
            BufferedImage bufferImagen = ImageIO.read(is);
            return RedimensionadorImagenes.bufferedImageToByte(bufferImagen);
        } catch (IOException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void redireccionar(String pagina) {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extcontext = context.getExternalContext();
        extcontext.getFlash().setKeepMessages(true);
        try {
            extcontext.redirect(pagina);
        } catch (IOException ex) {
            Logger.getLogger("Error al redireccionar a " + pagina);
        }

    }
}
