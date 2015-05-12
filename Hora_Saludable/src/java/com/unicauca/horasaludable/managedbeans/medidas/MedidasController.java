/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

/*
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;*/
import com.unicauca.horasaludable.entities.Medida;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author pcblanco
 */
@ManagedBean
@ViewScoped
public class MedidasController {

      
    @EJB
    private com.unicauca.horasaludable.jpacontrollers.MedidaFacade ejbMedida;
    private Medida medicionactual;  
    private ServicioCalculoMedidas servmed;
        
    public MedidasController() {
      
    }
    
    @PostConstruct
    public void init()
        {
      int idusu = 20141105 ; //para probar
      int idmed = 5 ; //para probar 
      
    //  FacesContext context = FacesContext.getCurrentInstance();
    //  MostrarUsuarioTestController s =  (MostrarUsuarioTestController)context.getApplication().evaluateExpressionGet(context, "#{mostrarUsuarioTestController}", MostrarUsuarioTestController.class);
      
      medicionactual =  ejbMedida.buscarporMedId(idmed).get(0);
        }

    public Medida getMedicionactual() {
        return medicionactual;
    }

    public void setMedicionactual(Medida medicionactual) {
        this.medicionactual = medicionactual;
    }

    private List<Medida> listaMedi;

    public List<Medida> getListaMedi() {
        return listaMedi;
    }

    public void setListaMedi(List<Medida> listaMedi) 
    {
        this.listaMedi = listaMedi;
    }


    
   public void redireccionar(Medida test) throws IOException 
   {
     
       this.medicionactual=test;
       
     //    RequestContext requestContext = RequestContext.getCurrentInstance();
 
       
       FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/administrador/medidas/VistaImprimirMedida.xhtml");
   }
   
   SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
   Date fechaDate = new Date();
   String fecha = formateador.format(fechaDate);
  
String name;
String nombreRutaFile;
   public void imprimir() {
       /*
        try {
            //Generamos el archivo PDF
            String directorioArchivos;
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
           // directorioArchivos = ctx.getRealPath("/") + "reports";
            directorioArchivos="Hora_Saludable\\build\\web\\reports";
       
            
             name = directorioArchivos +"\\"+getMedicionactual().getUsuid().getUsuidentificacion()+"test-pdf";
            Document document = new Document();
            
            
            PdfWriter.getInstance(document, new FileOutputStream(name));
            document.open();
             
            document.add(new Paragraph(""));
            document.add(new Paragraph("                                   Composicion Corporal y Test Deportivos"));
            document.add(new Paragraph("____________________________________________________________________________"));
            document.add(new Paragraph("Nombre: " + getMedicionactual().getUsuid().getUsunombres() + " "+getMedicionactual().getUsuid().getUsuapellidos()+ "                                                  Fecha:"+fecha));
            document.add(new Paragraph("Identificacion: " + getMedicionactual().getUsuid().getUsuidentificacion() + "                               Genero: "+ getMedicionactual().getUsuid().getUsugenero()));

            document.add(new Paragraph("Complexion: " + getMedicionactual().complexion()+"                                         Peso:"+getMedicionactual().getMedpeso()+"                   Talla :"+getMedicionactual().getMedtalla()));
            document.add(new Paragraph("Nombre: " + getMedicionactual().getMedpeso()));
            document.add(new Paragraph("Nombre: " + getMedicionactual().getMedpeso()));
            document.add(new Paragraph("Nombre: " + getMedicionactual().getMedpeso()));
            cargarArchivo();
            

            document.close();
            //----------------------------
            //Abrimos el archivo PDF
            System.out.println("Nombreeeeeeeeee "+name);
            System.out.println("slkdmklsmflkemglksemkgte"+directorioArchivos);
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition","inline=filename=" + name);
            
            try {
                response.getOutputStream().write(Util.getBytesFromFile(new File(name)));  
                response.getOutputStream().flush();
                response.getOutputStream().close();
                context.responseComplete();
            } catch (IOException e) 
            {
                  System.out.println("Error 11");
                e.printStackTrace();
            }
        } catch (Exception e) 
        {
              System.out.println("Erro2");
            e.printStackTrace();
        }*/
    }
     
 public void cargarArchivo() 
 {
       abrir();
   }
 

  private void abrir() {
  //ruta del archivo en el pc
      
       System.out.println("sdeeeeeeee "+nombreRutaFile);
        
      
      
      
  String file = new String("\\reports\\"+nombreRutaFile); 
   
 try{ 
   //definiendo la ruta en la propiedad file
   Runtime.getRuntime().exec("cmd /c start "+file);
     
   }catch(IOException e){
      e.printStackTrace();
   } 
  }
    
      public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
        
    public void guardarmed()
     {
       FacesContext context = FacesContext.getCurrentInstance();      
       try
           {
             ejbMedida.edit(medicionactual);
             context.addMessage("msggestion", new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", "Operacion realizada con exito"));
              ExternalContext extcontext = context.getExternalContext();
              extcontext.getFlash().setKeepMessages(true); 
              extcontext.redirect("GestionTest.xhtml");
           }
        catch (Exception e) {
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio algun error al intentar  efectuar la operacion"));
            }
    }       
    
}
