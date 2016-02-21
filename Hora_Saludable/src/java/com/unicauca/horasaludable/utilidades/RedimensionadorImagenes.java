
package com.unicauca.horasaludable.utilidades;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class RedimensionadorImagenes {
    /**
     * Redimensiona la imagen
     * @param is flujo de entrada que representa la imagen
     * @param maxSize tamaño en pixel de la nueva imagen
     * @return flujo de bytes redimensionado que se graba en la base de datos
     */
    public static byte[] redimensionar(InputStream is, int maxSize) {
        try {
            int maxWidth = maxSize;
            int maxHeight = maxSize;
            BufferedImage nuevaImagen = ImageIO.read(is);
            if (nuevaImagen.getHeight() > nuevaImagen.getWidth()) {
                int heigt = (nuevaImagen.getHeight() * maxWidth) / nuevaImagen.getWidth();
                nuevaImagen = resize(nuevaImagen, maxWidth, heigt);
                int width = (nuevaImagen.getWidth() * maxHeight) / nuevaImagen.getHeight();
                nuevaImagen = resize(nuevaImagen, width, maxHeight);
            } else {
                int width = (nuevaImagen.getWidth() * maxHeight) / nuevaImagen.getHeight();
                nuevaImagen = resize(nuevaImagen, width, maxHeight);
                int heigt = (nuevaImagen.getHeight() * maxWidth) / nuevaImagen.getWidth();
                nuevaImagen = resize(nuevaImagen, maxWidth, heigt);
            }
            //guardarImagen(nuevaImagen, "/tmp/peque.jpg");
            return bufferedImageToByte(nuevaImagen);
        } catch (IOException ex) {
            Logger.getLogger("Error en el redimensionador de imagenes");
        }
        return null;
    }
    
    public static byte[] bufferedImageToByte(BufferedImage imagen) {
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imagen, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException ex) {
            Logger.getLogger("Error en el redimensionador de imagenes");
        }
        return null;
    }

    private static void guardarImagen(BufferedImage bufferedImage, String pathName) {
        try {
            String format = (pathName.endsWith(".png")) ? "png" : "jpg";
            File file = new File(pathName);
            file.getParentFile().mkdirs();
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage imagenRedimensionada = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = imagenRedimensionada.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return imagenRedimensionada;
    }
    
}
