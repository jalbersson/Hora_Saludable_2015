package com.unicauca.horasaludable.managedbeans.medidas;

/*
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;*/

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unicauca.horasaludable.entities.Medida;
import com.unicauca.horasaludable.entities.Usuario;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;


/**
 *
 * @author pcblanco
 */
@ManagedBean
@ViewScoped
public class MedidasController 
{     
    @EJB
    private com.unicauca.horasaludable.jpacontrollers.MedidaFacade ejbMedida;
    private Medida medicionactual;  
    private ServicioCalculoMedidas servmed;
    private List<Medida> listaTest;
    private int idusu;
    private Date fechaNuevoTest;
    private String calificacion;


        
    public MedidasController() {
      
    }
    
    @PostConstruct
    public void init() 
    {
         //para probar
        int idmed = 5; //para probar 
        FacesContext context = FacesContext.getCurrentInstance();
        MostrarUsuarioTestController s =  (MostrarUsuarioTestController)context.getApplication().evaluateExpressionGet(context, "#{mostrarUsuarioTestController}", MostrarUsuarioTestController.class);
        idusu = s.getUsuario().getUsuid().intValue();
        listaTest= ejbMedida.buscarporUsuid(idusu);
        medicionactual = ejbMedida.buscarporMedId(idmed).get(0);
        calificacion="pendiente";
        medicionactual =  s.getMedidaactual(); //ejbMedida.buscarporMedId(s.getMedidaactual()).get(0);
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
    public void agregarMedidas()
    {
        
        
        FacesContext context = FacesContext.getCurrentInstance();      
       try
           {
             
             Medida nueva=new Medida();
             nueva.setUsuid(new Usuario(Long.valueOf(idusu+"")));
             nueva.setMedfecha(fechaNuevoTest);
             ejbMedida.create(nueva);  
               
             context.addMessage("msggestion", new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", "Operacion realizada con exito"));
              ExternalContext extcontext = context.getExternalContext();
              extcontext.getFlash().setKeepMessages(true); 
              extcontext.redirect("GestionTest.xhtml");
           }
        catch (Exception e) {
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio algun error al intentar  efectuar la operacion"));
            }
        
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
      /* public void imprimir() {
   
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
  //  }
     

 private String pdfFileName = "Formato-A.pdf";

    

    public String getPdfFileName() {
        return pdfFileName;
    }

    public void setPdfFileName(String pdfFileName) {
        this.pdfFileName = pdfFileName;
    }

    public void generarPDF() {
        try {
          
           
            
            File file = File.createTempFile("Formato-A", ".pdf");

            pdfFileName = file.getName();

            Document document = new Document();

            PdfWriter pdfwriter = PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            Font bold = new Font(Font.HELVETICA, 12f, Font.BOLD);

//            URL url = FacesContext.getCurrentInstance().getExternalContext().getResource("/resources/images/escudo-unicauca.jpg");
    
         //   Image imgLogoUnicauca = Image.getInstance(url);
          //  imgLogoUnicauca.scaleAbsolute(118f, 131f);

            PdfPTable tableEncabezado = new PdfPTable(2);

            tableEncabezado.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableEncabezado.setWidthPercentage(100);
            tableEncabezado.setSpacingAfter(5);

           // PdfPCell cell1 = new PdfPCell(imgLogoUnicauca);
            //cell1.setBorder(Rectangle.NO_BORDER);

            PdfPCell cell2 = new PdfPCell(new Paragraph("popUniversidad del Cauca\nFacultad de Ingeniería Electronica y\nTelecomunicaciones\nCI-FIET"));
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cell3 = new PdfPCell(new Paragraph("FORMATO A: PRESENTACIÓN DE LA PROPUESTA DE TRABAJO DE GRADO AL\nDEPARTAMENTO", bold));
            cell3.setBorder(Rectangle.NO_BORDER);

            PdfPCell cell4 = new PdfPCell(new Paragraph("", bold));
            cell4.setBorder(Rectangle.NO_BORDER);
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);

//            tableEncabezado.addCell(cell1);
            tableEncabezado.addCell(cell2);
            tableEncabezado.addCell(cell3);
            tableEncabezado.addCell(cell4);

            document.add(tableEncabezado);

            document.close();
            RequestContext.getCurrentInstance().update("frmVerRutina");
        } catch (DocumentException ex) {
            Logger.getLogger(MedidasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MedidasController.class.getName()).log(Level.SEVERE, null, ex);
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
    public void calcularSaltoReal()
    {
        medicionactual.setMedsaltoreal(medicionactual.getMedsaltomaximo()-medicionactual.getMedembergadura());
    }
    public List<Medida> getListaTest() 
    {
        return listaTest;
    }
    public void setListaTest(List<Medida> listaTest) 
    {
        this.listaTest = listaTest;
    }
    public Date getFechaNuevoTest() 
    {
        return fechaNuevoTest;
    }
    public void setFechaNuevoTest(Date fechaNuevoTest) 
    {
        this.fechaNuevoTest = fechaNuevoTest;
    }
    public void testRufier()
    {
        float i=0;
        i=(medicionactual.getMedpulso0()+medicionactual.getMedpulso1()+medicionactual.getMedpulso2()-200)/10;
        if(i==0)
        {
            calificacion="Excelente";
        }
        if(i>=0.1 && i<=5)
        {
            calificacion="Bueno";
        }
        if(i>=5.1 && i<=10)
        {
            calificacion="Medio";
        }
        if(i>=10.1 && i<=15)
        {
            calificacion="Insuficiente";
        }
        if(i>=15.1 && i<=20)
        {
            calificacion="Malo, requiere evaluación médica";
        }
        System.out.println("así quedo la calificacion: "+calificacion);
    }
    public String getCalificacion() 
    {
        return calificacion;
    }
    public void setCalificacion(String calificacion) 
    {
        this.calificacion = calificacion;
    }
}
