package com.unicauca.horasaludable.managedbeans.medidas;

/*
 import com.lowagie.text.Document;
 import com.lowagie.text.Paragraph;
 import com.lowagie.text.pdf.PdfWriter;*/
import com.itextpdf.text.Image;
import com.unicauca.horasaludable.entities.Medida;
import com.unicauca.horasaludable.entities.Usuario;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import com.itextpdf.text.Font;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;

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
import org.primefaces.context.RequestContext;

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
    private List<Medida> listaTest;
    private int idusu;
    private Date fechaNuevoTest;
    private String calificacion;

    public MedidasController() {

    }

    @PostConstruct
    public void init() {
        //para probar
        int idmed = 5; //para probar 
        FacesContext context = FacesContext.getCurrentInstance();
        MostrarUsuarioTestController s = (MostrarUsuarioTestController) context.getApplication().evaluateExpressionGet(context, "#{mostrarUsuarioTestController}", MostrarUsuarioTestController.class);
        idusu = s.getUsuario().getUsuid().intValue();
        listaTest = ejbMedida.buscarporUsuid(idusu);
        medicionactual = ejbMedida.buscarporMedId(idmed).get(0);
        calificacion = "pendiente";
        medicionactual = s.getMedidaactual(); //ejbMedida.buscarporMedId(s.getMedidaactual()).get(0);
       
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

    public void setListaMedi(List<Medida> listaMedi) {
        this.listaMedi = listaMedi;
    }

    public void agregarMedidas() {

        FacesContext context = FacesContext.getCurrentInstance();
        try {

            Medida nueva = new Medida();
            nueva.setUsuid(new Usuario(Long.valueOf(idusu + "")));
            nueva.setMedfecha(fechaNuevoTest);
            ejbMedida.create(nueva);

            context.addMessage("msggestion", new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", "Operacion realizada con exito"));
            ExternalContext extcontext = context.getExternalContext();
            extcontext.getFlash().setKeepMessages(true);
            extcontext.redirect("GestionTest.xhtml");
        } catch (Exception e) {
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio algun error al intentar  efectuar la operacion"));
        }

    }

    public void redireccionar(Medida test) throws IOException {

        this.medicionactual = test;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/administrador/medidas/VistaImprimirMedida.xhtml");
    }

    SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
    Date fechaDate = new Date();
    String fecha = formateador.format(fechaDate);

   

    private String pdfFileName = "Formato-A.pdf";

    public String getPdfFileName() {
        return pdfFileName;
    }

    public void setPdfFileName(String pdfFileName) {
        this.pdfFileName = pdfFileName;
    }




    public void generarPDF(Usuario user) {
        try {

            
            
            
            
            File file = File.createTempFile(user.getUsunombres()+user.getUsuapellidos(), ".pdf");
            pdfFileName = file.getName();
      

            Document document = new Document();

            PdfWriter pdfwriter = PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();
      

            Font bold = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD | Font.UNDERLINE);
           

            URL url = FacesContext.getCurrentInstance().getExternalContext().getResource("/resources/img/logo-unicauca-negro.png");

            Image imgLogoUnicauca = Image.getInstance(url);
            imgLogoUnicauca.scaleAbsolute(118f, 131f);

            PdfPTable tableEncabezado = new PdfPTable(2);

            tableEncabezado.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableEncabezado.setWidthPercentage(100);
            tableEncabezado.setSpacingAfter(5);

            PdfPCell cell1 = new PdfPCell(imgLogoUnicauca);
            cell1.setBorder(Rectangle.NO_BORDER);
            
             PdfPCell cell2 = new PdfPCell(new Paragraph("Universidad del Cauca\n División de Recreación y Deporte\n         Hora saludable"));
             
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            document.add(new Paragraph(""));
                       
            tableEncabezado.addCell(cell1);
            tableEncabezado.addCell(cell2);         
        
            document.add(tableEncabezado);
  

             Paragraph tituloPoL = new Paragraph("Composicion Corporal y Test Deportivos",bold); 
             tituloPoL.setAlignment(Element.ALIGN_CENTER);  
            document.add(tituloPoL);
            
             //     URL url2 = FacesContext.getCurrentInstance().getExternalContext().getResource("/resources/img/antorcha.png");

            //   Image imgFondo = Image.getInstance(url2);
           //     imgFondo.setAbsolutePosition(100f, 150f);
        Font negrilla = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD);
          Font boldSize = new Font(Font.FontFamily.HELVETICA, 10f, Font.BOLD);
                       
             document.add(new Paragraph("____________________________________________________________________________"));
             
                  
            document.add((Element) new Chunk("Nombre: ",negrilla));document.add((Element) new Chunk(user.getUsunombres() + " "+user.getUsuapellidos()));            
            document.add((Element) new Chunk("                                      Fecha:"+fecha));
            
            document.add(new Paragraph(""));
            document.add((Element) new Chunk("Identificacion: ",negrilla));  document.add((Element) new Chunk(user.getUsuidentificacion()+""));
            document.add((Element) new Chunk("                           Genero: ",negrilla));
            document.add((Element) new Chunk(getMedicionactual().getUsuid().getUsugenero()+" ( M=Masculino / F =Femenino )"));  
            
            document.add(new Paragraph(""));
            
            document.add((Element) new Chunk("Complexion: ",negrilla)); 
             document.add((Element) new Chunk(getMedicionactual().complexion()+"                                   Medidas:: ")); 
             document.add((Element) new Chunk("     Talla ",negrilla));
             document.add((Element) new Chunk(getMedicionactual().getMedtalla()+""));
             
             document.add((Element) new Chunk("         Peso:",negrilla));             
             document.add((Element) new Chunk(getMedicionactual().getMedpeso()+" Kgs"));
             
             document.add(new Paragraph(""));
             document.add((Element) new Chunk("Deporte: ",negrilla));
             if(getMedicionactual().getMeddeporte()==null)
             {
             document.add((Element) new Chunk("No tiene Asignado"));  
             }
             else
             {document.add((Element) new Chunk(getMedicionactual().getMeddeporte()));  }    
           
            
            document.add(new Paragraph("____________________________________________________________________________"));
               document.add(new Paragraph(""));
            
  PdfPTable table = new PdfPTable(7);
table.setWidthPercentage(100);
        table.setSpacingAfter(5);
        
            
        PdfPCell cell = new PdfPCell(new Phrase("ANTROPOMETRIA:",bold));
        cell.setBorder(Rectangle.NO_BORDER);      
       cell.setColspan(3);                    

        PdfPCell cell02 = new PdfPCell(new Paragraph(""));
         cell02.setBorder(Rectangle.NO_BORDER); 

       PdfPCell cell31 = new PdfPCell(new Paragraph(""));
            cell31.setBorder(Rectangle.NO_BORDER);
            
            PdfPCell cell41 = new PdfPCell(new Paragraph(""));
            cell41.setBorder(Rectangle.NO_BORDER);
            
            PdfPCell cell5 = new PdfPCell(new Paragraph("PLIEGUES CUTANEOS:",bold));
            cell5.setBorder(Rectangle.NO_BORDER);
           cell5.setColspan(3);
           
            PdfPCell cel6 = new PdfPCell(new Phrase("PERIMETRO BRAZO:",boldSize));
        cel6.setBorder(Rectangle.NO_BORDER);      
       cel6.setColspan(2);
       
       PdfPCell cel9 = new PdfPCell(new Phrase("TRICEPS:",boldSize));
        cel9.setBorder(Rectangle.NO_BORDER);
        cel9.setColspan(2);
         
           PdfPCell cel11 = new PdfPCell(new Phrase("PERIMETRO ANTEBRAZO:",boldSize));
        cel11.setBorder(Rectangle.NO_BORDER);      
       cel11.setColspan(2);
       
        table.addCell(cell);
        table.addCell(cell02);
        table.addCell(cell31);
        table.addCell(cell5);
        table.addCell(cel6);
        table.addCell("rtrt");  
        table.addCell("");  
        table.addCell(cel9);
        table.addCell("rrt");
        table.addCell(cel11);
        table.addCell("rt");
        table.addCell("");  
        
         PdfPCell cel14 = new PdfPCell(new Phrase("ABDOMINAL:",boldSize));
        cel14.setBorder(Rectangle.NO_BORDER); 
         cel14.setColspan(2);
         
        table.addCell(cel14);
        table.addCell("rrt2P");
        
        
        
         PdfPCell cel16 = new PdfPCell(new Phrase("PERIMETRO DE CAJA TORAXICA:",boldSize));
        cel16.setBorder(Rectangle.NO_BORDER); 
         cel16.setColspan(2);
         
        table.addCell(cel16);
        table.addCell("rt2");
        table.addCell("");  
        
        PdfPCell cel19 = new PdfPCell(new Phrase("MUSLO:",boldSize));
        cel19.setBorder(Rectangle.NO_BORDER); 
         cel19.setColspan(2);
        
        table.addCell(cel19);
        table.addCell("rrt2");
        
        PdfPCell cel21 = new PdfPCell(new Phrase("PERIMETRO DE GATRONEMIO:",boldSize));
        cel21.setBorder(Rectangle.NO_BORDER); 
         cel21.setColspan(2);
         
        table.addCell(cel21);
        table.addCell("rt2"); 
        table.addCell("");  
        PdfPCell cel24 = new PdfPCell(new Phrase("SUBESCAPULAR:",boldSize));
        cel24.setBorder(Rectangle.NO_BORDER); 
        cel24.setColspan(2);
        
        table.addCell(cel24);
        table.addCell("rrt2");
        
        
        PdfPCell cel26 = new PdfPCell(new Phrase("PERIMETRO DE MUSLO:",boldSize));
        cel26.setBorder(Rectangle.NO_BORDER); 
        cel26.setColspan(2);
         
        table.addCell(cel26);
        table.addCell("rt2");
        table.addCell("");  
        
        PdfPCell cel29 = new PdfPCell(new Phrase("SUPRAILIACO:",boldSize));
        cel29.setBorder(Rectangle.NO_BORDER); 
        cel29.setColspan(2);
        
        
        table.addCell(cel29);
        table.addCell("rrt2");
        
        PdfPCell cel31 = new PdfPCell(new Phrase("PERIMETRO DE MUÑECA:",boldSize));
        cel31.setBorder(Rectangle.NO_BORDER); 
        cel31.setColspan(2);
         
        table.addCell(cel31);
        table.addCell("rt2");
        table.addCell("");  
        
        PdfPCell cel34 = new PdfPCell(new Phrase("PANTORRILA:",boldSize));
        cel34.setBorder(Rectangle.NO_BORDER); 
         cel34.setColspan(2);
         
         
        table.addCell(cel34);
        table.addCell("rrt2");
        
        PdfPCell cel36 = new PdfPCell(new Phrase(""));
        cel36.setBorder(Rectangle.NO_BORDER); 
        cel36.setColspan(2);
         
        table.addCell(cel36);
 
        table.addCell("");  
        
        PdfPCell cel39 = new PdfPCell(new Phrase("(+) PLIEGUES:",boldSize));
        cel39.setBorder(Rectangle.NO_BORDER); 
        table.addCell(cel39);
        table.addCell("rrt2");        
        
        
           
           document.add(table);   
            
            
            
            document.close();
            RequestContext.getCurrentInstance().update("frmVerRutina");
        } catch (IOException ex) {
            Logger.getLogger(MedidasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(MedidasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void guardarmed() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            ejbMedida.edit(medicionactual);
            context.addMessage("msggestion", new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", "Operacion realizada con exito"));
            ExternalContext extcontext = context.getExternalContext();
            extcontext.getFlash().setKeepMessages(true);
            extcontext.redirect("GestionTest.xhtml");
        } catch (Exception e) {
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio algun error al intentar  efectuar la operacion"));
        }
    }

    public void calcularSaltoReal() {
        medicionactual.setMedsaltoreal(medicionactual.getMedsaltomaximo() - medicionactual.getMedembergadura());
    }

    public List<Medida> getListaTest() {
        return listaTest;
    }

    public void setListaTest(List<Medida> listaTest) {
        this.listaTest = listaTest;
    }

    public Date getFechaNuevoTest() {
        return fechaNuevoTest;
    }

    public void setFechaNuevoTest(Date fechaNuevoTest) {
        this.fechaNuevoTest = fechaNuevoTest;
    }

    public void testRufier() {
        float i = 0;
        i = (medicionactual.getMedpulso0() + medicionactual.getMedpulso1() + medicionactual.getMedpulso2() - 200) / 10;
        if (i == 0) {
            calificacion = "Excelente";
        }
        if (i >= 0.1 && i <= 5) {
            calificacion = "Bueno";
        }
        if (i >= 5.1 && i <= 10) {
            calificacion = "Medio";
        }
        if (i >= 10.1 && i <= 15) {
            calificacion = "Insuficiente";
        }
        if (i >= 15.1 && i <= 20) {
            calificacion = "Malo, requiere evaluación médica";
        }
        System.out.println("así quedo la calificacion: " + calificacion);
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}
