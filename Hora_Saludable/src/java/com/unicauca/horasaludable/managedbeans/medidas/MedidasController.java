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
import com.lowagie.text.pdf.BaseFont;
import static com.lowagie.text.pdf.BidiOrder.R;
import java.io.ByteArrayOutputStream;

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

            context.addMessage("msggestion", new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", "Text creado con Exito"));
            ExternalContext extcontext = context.getExternalContext();
            extcontext.getFlash().setKeepMessages(true);
            extcontext.redirect("GestionTest.xhtml");
        } catch (Exception e) {
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio algun error al intentar  efectuar la operacion"));
        }

    }
    
    public void eliminarTest()
        {

        FacesContext context = FacesContext.getCurrentInstance();
        try {

            ejbMedida.remove(medicionactual);

            context.addMessage("msggestion", new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", "Test Eliminado"));
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




    public void generarPDF(Usuario user) throws com.lowagie.text.DocumentException {
        try {

            
            File file = File.createTempFile(user.getUsunombres()+user.getUsuapellidos(), ".pdf");
            pdfFileName = file.getName();      

            Document document = new Document();

            PdfWriter pdfwriter = PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();
      

            Font bold = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD | Font.UNDERLINE);
       

             Paragraph tituloPoL = new Paragraph("Composicion Corporal y Test Deportivos",bold); 
             tituloPoL.setAlignment(Element.ALIGN_CENTER);  
            document.add(tituloPoL);
      
        Font negrilla = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD);
          Font boldSize = new Font(Font.FontFamily.HELVETICA, 10f, Font.BOLD);
                       
             document.add(new Paragraph("____________________________________________________________________________"));
             
                  
            document.add((Element) new Chunk("Nombre: ",negrilla));document.add((Element) new Chunk(user.getUsunombres() + " "+user.getUsuapellidos()));            
            document.add((Element) new Chunk("                               Fecha Impresión:"+fecha));
            
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
             if(getMedicionactual().getMeddeporte()==null )
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

            PdfPCell cell = new PdfPCell(new Phrase("\nANTROPOMETRIA:\n", bold));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);                   

        PdfPCell cell02 = new PdfPCell(new Paragraph(""));
         cell02.setBorder(Rectangle.NO_BORDER); 

       PdfPCell cell31 = new PdfPCell(new Paragraph(""));
            cell31.setBorder(Rectangle.NO_BORDER);
            
            PdfPCell cell41 = new PdfPCell(new Paragraph(""));
            cell41.setBorder(Rectangle.NO_BORDER);
            
            PdfPCell cell5 = new PdfPCell(new Paragraph("\nPLIEGUES CUTANEOS:\n",bold));
            cell5.setBorder(Rectangle.NO_BORDER);
           cell5.setColspan(3);
           
            PdfPCell cel6 = new PdfPCell(new Phrase("\nPERIMETRO BRAZO:",boldSize));
        cel6.setBorder(Rectangle.NO_BORDER);      
       cel6.setColspan(2);
       
       PdfPCell cel9 = new PdfPCell(new Phrase("\nTRICEPS:",boldSize));
        cel9.setBorder(Rectangle.NO_BORDER);
        cel9.setColspan(2);
         
           PdfPCell cel11 = new PdfPCell(new Phrase("PERIMETRO ANTEBRAZO:",boldSize));
        cel11.setBorder(Rectangle.NO_BORDER);      
       cel11.setColspan(2);
       
        PdfPCell cell101 = new PdfPCell(new Paragraph("\n"+getMedicionactual().getMedperimetrobrazo()+""));
            cell101.setBorder(Rectangle.NO_BORDER);
            
            PdfPCell cell102 = new PdfPCell(new Paragraph(""));
            cell102.setBorder(Rectangle.NO_BORDER); 
            
            PdfPCell cell103 = new PdfPCell(new Paragraph("\n"+getMedicionactual().getMedtriceps()+""));
            cell103.setBorder(Rectangle.NO_BORDER); 
            
             PdfPCell cell104 = new PdfPCell(new Paragraph(getMedicionactual().getMeddiametroantebrazo()+""));
            cell104.setBorder(Rectangle.NO_BORDER); 
            
            PdfPCell cell105 = new PdfPCell(new Paragraph(""));
            cell105.setBorder(Rectangle.NO_BORDER);
            
        table.addCell(cell);
        table.addCell(cell02);
        table.addCell(cell31);
        table.addCell(cell5);
        table.addCell(cel6);
        table.addCell(cell101);  
        table.addCell(cell102);  
        table.addCell(cel9);
        table.addCell(cell103);
        table.addCell(cel11);
        table.addCell(cell104);
        table.addCell(cell105);  
        
         PdfPCell cel14 = new PdfPCell(new Phrase("ABDOMINAL:",boldSize));
        cel14.setBorder(Rectangle.NO_BORDER); 
         cel14.setColspan(2);
         
        table.addCell(cel14);
        
        PdfPCell cell106 = new PdfPCell(new Paragraph(getMedicionactual().getMedabdominal()+""));
            cell106.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell106);
         PdfPCell cel16 = new PdfPCell(new Phrase("PERIMETRO DE CAJA TORAXICA:",boldSize));
        cel16.setBorder(Rectangle.NO_BORDER); 
         cel16.setColspan(2);
         
        table.addCell(cel16);
        PdfPCell cell107 = new PdfPCell(new Paragraph(getMedicionactual().getMedperimetrocajatoraxica()+""));
            cell107.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell107);
        
        PdfPCell cell108 = new PdfPCell(new Paragraph(""));
            cell108.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell108);  
        
        PdfPCell cel19 = new PdfPCell(new Phrase("MUSLO:",boldSize));
        cel19.setBorder(Rectangle.NO_BORDER); 
         cel19.setColspan(2);
        
        table.addCell(cel19);
        
          PdfPCell cell109 = new PdfPCell(new Paragraph(getMedicionactual().getMedmuslo()+""));
            cell109.setBorder(Rectangle.NO_BORDER);
            
        table.addCell(cell109);
        
        PdfPCell cel21 = new PdfPCell(new Phrase("PERIMETRO DE GATRONEMIO:",boldSize));
        cel21.setBorder(Rectangle.NO_BORDER); 
         cel21.setColspan(2);
         
        table.addCell(cel21);
        
        PdfPCell cell110 = new PdfPCell(new Paragraph(" "));
            cell110.setBorder(Rectangle.NO_BORDER);
            
        table.addCell(cell110);
        
        
                PdfPCell cell111 = new PdfPCell(new Paragraph(""));
            cell111.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell111); 
        
        PdfPCell cel24 = new PdfPCell(new Phrase("SUBESCAPULAR:",boldSize));
        cel24.setBorder(Rectangle.NO_BORDER); 
        cel24.setColspan(2);
        
        table.addCell(cel24);
        
        PdfPCell cell112 = new PdfPCell(new Paragraph(getMedicionactual().getMedsubescapular()+""));
            cell112.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell112);
        
        
        
        PdfPCell cel26 = new PdfPCell(new Phrase("PERIMETRO DE MUSLO:",boldSize));
        cel26.setBorder(Rectangle.NO_BORDER); 
        cel26.setColspan(2);
         
        table.addCell(cel26);
        
       PdfPCell cell113 = new PdfPCell(new Paragraph(getMedicionactual().getMedperimetromuslo()+""));
            cell113.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell113);
        
               PdfPCell cell114 = new PdfPCell(new Paragraph(""));
            cell114.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell114);  
        
        PdfPCell cel29 = new PdfPCell(new Phrase("SUPRAILIACO:",boldSize));
        cel29.setBorder(Rectangle.NO_BORDER); 
        cel29.setColspan(2);
        
        
        table.addCell(cel29);
        
        PdfPCell cell115 = new PdfPCell(new Paragraph(getMedicionactual().getMedsuprailiaco()+""));
            cell115.setBorder(Rectangle.NO_BORDER);
        
        
        table.addCell(cell115);
        
        PdfPCell cel31 = new PdfPCell(new Phrase("PERIMETRO DE MUÑECA:",boldSize));
        cel31.setBorder(Rectangle.NO_BORDER); 
        cel31.setColspan(2);
         
        table.addCell(cel31);
        
        PdfPCell cell116 = new PdfPCell(new Paragraph(getMedicionactual().getMedperimetromuneca()+""));
            cell116.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell116);
        
        PdfPCell cell117 = new PdfPCell(new Paragraph(""));
            cell117.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell117);  
        
        PdfPCell cel34 = new PdfPCell(new Phrase("PANTORRILA:",boldSize));
        cel34.setBorder(Rectangle.NO_BORDER); 
         cel34.setColspan(2);
         
         
        table.addCell(cel34);
        
        PdfPCell cell118 = new PdfPCell(new Paragraph(getMedicionactual().getMedpantorilla()+""));
            cell118.setBorder(Rectangle.NO_BORDER);
            
        table.addCell(cell118);
        
        PdfPCell cel36 = new PdfPCell(new Phrase(""));
        cel36.setBorder(Rectangle.NO_BORDER); 
        cel36.setColspan(2);
         
        table.addCell(cel36);
 
           PdfPCell cel1900 = new PdfPCell(new Phrase(""));
        cel1900.setBorder(Rectangle.NO_BORDER); 
        table.addCell(cel1900);  
        
        PdfPCell cel190 = new PdfPCell(new Phrase(""));
        cel190.setBorder(Rectangle.NO_BORDER); 
        table.addCell(cel190);  
        
        PdfPCell cel39 = new PdfPCell(new Phrase("(+) PLIEGUES:",boldSize));
        cel39.setBorder(Rectangle.NO_BORDER); 
        cel39.setColspan(2);
        table.addCell(cel39);
        
        PdfPCell cel191 = new PdfPCell(new Phrase(getMedicionactual().sumatoriadepliegues()+""));
        cel191.setBorder(Rectangle.NO_BORDER); 
        table.addCell(cel191);   
        
        document.add(table); 
        document.add(new Paragraph("____________________________________________________________________________"));
            document.add(new Paragraph(""));

            PdfPTable tabla = new PdfPTable(13);
            tabla.setWidthPercentage(100);
            //  tabla.setSpacingAfter(5);

            PdfPCell celda = new PdfPCell(new Phrase("\nCOMPOSICION CORPORAL:", bold));
            celda.setBorder(Rectangle.NO_BORDER);
            celda.setColspan(7);

            PdfPCell celda2 = new PdfPCell(new Phrase(""));
            celda2.setBorder(Rectangle.NO_BORDER);

            PdfPCell celda3 = new PdfPCell(new Phrase(""));
            celda3.setBorder(Rectangle.NO_BORDER);
            PdfPCell celda4 = new PdfPCell(new Phrase(""));
            celda4.setBorder(Rectangle.NO_BORDER);
            PdfPCell celda5 = new PdfPCell(new Phrase(""));
            celda5.setBorder(Rectangle.NO_BORDER);
            PdfPCell celda6 = new PdfPCell(new Phrase(""));
            celda6.setBorder(Rectangle.NO_BORDER);
            PdfPCell celda7 = new PdfPCell(new Phrase(""));
            celda7.setBorder(Rectangle.NO_BORDER);
            
            PdfPCell celda110 = new PdfPCell(new Phrase("\n"));
            celda110.setBorder(Rectangle.NO_BORDER);
            celda110.setColspan(13);

            PdfPCell celda8 = new PdfPCell(new Phrase("PORCENTAJE DE GRASA:", boldSize));
            celda8.setBorder(Rectangle.NO_BORDER);
            celda8.setColspan(4);

            PdfPCell celda9 = new PdfPCell(new Phrase(getMedicionactual().porcentajeGrasa()+"   " + getMedicionactual().getEstadoPorcentajeGrasaIdeal()));
            celda9.setBorder(Rectangle.NO_BORDER);
            celda9.setColspan(3);

            PdfPCell celda11 = new PdfPCell(new Phrase(""));
            celda11.setBorder(Rectangle.NO_BORDER);

            PdfPCell celda12 = new PdfPCell(new Phrase("PESO OPTIMO:", boldSize));
            celda12.setBorder(Rectangle.NO_BORDER);
            celda12.setColspan(4);

            PdfPCell celda13 = new PdfPCell(new Phrase(getMedicionactual().pesooptimo()+""));
            celda13.setBorder(Rectangle.NO_BORDER);

            PdfPCell celda14 = new PdfPCell(new Phrase("PESO GRASO:", boldSize));
            celda14.setBorder(Rectangle.NO_BORDER);
            celda14.setColspan(4);

            PdfPCell celda15 = new PdfPCell(new Phrase(getMedicionactual().pesoGraso()+""));
            celda15.setBorder(Rectangle.NO_BORDER);
 celda15.setColspan(3);

            PdfPCell celda17 = new PdfPCell(new Phrase(""));
            celda17.setBorder(Rectangle.NO_BORDER);

            PdfPCell celda18 = new PdfPCell(new Phrase("PESO LIBRE DE GRASA:", boldSize));
            celda18.setBorder(Rectangle.NO_BORDER);
            celda18.setColspan(4);

            PdfPCell celda19 = new PdfPCell(new Phrase( getMedicionactual().pesolibregrasa()+""));
            celda19.setBorder(Rectangle.NO_BORDER);

            PdfPCell celda20 = new PdfPCell(new Phrase("MASA TOTAL OSEA:", boldSize));
            celda20.setBorder(Rectangle.NO_BORDER);
            celda20.setColspan(4);

            PdfPCell celda21 = new PdfPCell(new Phrase(getMedicionactual().masatotalosea()+""));
            celda21.setBorder(Rectangle.NO_BORDER);
            celda21.setColspan(3);

            PdfPCell celda23 = new PdfPCell(new Phrase(""));
            celda23.setBorder(Rectangle.NO_BORDER);

            PdfPCell celda24 = new PdfPCell(new Phrase("TASA METABOLICA BASAL: ", boldSize));
            celda24.setBorder(Rectangle.NO_BORDER);
            celda24.setColspan(4);

            PdfPCell celda25 = new PdfPCell(new Phrase(getMedicionactual().tasametabolicabasal()+"",boldSize));
            celda25.setBorder(Rectangle.NO_BORDER);

            PdfPCell celda26 = new PdfPCell(new Phrase("EXCESO DE PESO:", boldSize));
            celda26.setBorder(Rectangle.NO_BORDER);
            celda26.setColspan(4);

            PdfPCell celda27 = new PdfPCell(new Phrase( getMedicionactual().excesodepeso()+""));
            celda27.setBorder(Rectangle.NO_BORDER);
             celda27.setColspan(3);

            PdfPCell celda29 = new PdfPCell(new Phrase(""));
            celda29.setBorder(Rectangle.NO_BORDER);

            PdfPCell celda30 = new PdfPCell(new Phrase("MASA MUSCULAR:", boldSize));
            celda30.setBorder(Rectangle.NO_BORDER);
            celda30.setColspan(4);

            PdfPCell celda31 = new PdfPCell(new Phrase( getMedicionactual().masamuscular()+""));
            celda31.setBorder(Rectangle.NO_BORDER);

            PdfPCell celda32 = new PdfPCell(new Phrase("IND. MASA CORPORAL:", boldSize));
            celda32.setBorder(Rectangle.NO_BORDER);
            celda32.setColspan(4);

            PdfPCell celda33 = new PdfPCell(new Phrase(getMedicionactual().indicemasacorporal()+"   "+getMedicionactual().getEstadoIMC()+""));
            celda33.setBorder(Rectangle.NO_BORDER);
             celda33.setColspan(3);

            PdfPCell celda35 = new PdfPCell(new Phrase(""));
            celda35.setBorder(Rectangle.NO_BORDER);
            celda35.setColspan(4);

            tabla.addCell(celda);

            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            tabla.addCell(celda6);
            tabla.addCell(celda7);            
            tabla.addCell(celda110);
            tabla.addCell(celda8);
            tabla.addCell(celda9);
            tabla.addCell(celda11);
            tabla.addCell(celda12);
            tabla.addCell(celda13);
            tabla.addCell(celda14);
            tabla.addCell(celda15);
            tabla.addCell(celda17);
            tabla.addCell(celda18);
            tabla.addCell(celda19);
            tabla.addCell(celda20);
            tabla.addCell(celda21);
            tabla.addCell(celda23);
            tabla.addCell(celda24);
            tabla.addCell(celda25);
            tabla.addCell(celda26);
            tabla.addCell(celda27);
            tabla.addCell(celda29);
            tabla.addCell(celda30);
            tabla.addCell(celda31);
            tabla.addCell(celda32);
            tabla.addCell(celda33);
            tabla.addCell(celda35);           
            document.add(tabla);  
         
         
          document.add(new Paragraph("____________________________________________________________________________"));
        document.add(new Paragraph("\n"));
        
        PdfPTable tablass1 = new PdfPTable(20);       
           tablass1.setWidthPercentage(100);
            tablass1.setSpacingAfter(5);
    
            
        PdfPCell celdda = new PdfPCell(new Phrase("",bold));        
        celdda.setColspan(6);
        
         PdfPCell celdda1 = new PdfPCell(new Phrase(""));         
        celdda1.setBorder(Rectangle.NO_BORDER);   
           
        PdfPCell celdda2 = new PdfPCell(new Phrase(""));
        celdda2.setColspan(6);   
            
             PdfPCell celdda3 = new PdfPCell(new Phrase(""));
        celdda3.setBorder(Rectangle.NO_BORDER); 
            
              PdfPCell celdda4 = new PdfPCell(new Phrase(""));              
             celdda4.setColspan(6);   
              
             
             PdfPTable tablass2 = new PdfPTable(3);
             tablass2.setWidthPercentage(100);
            tablass2.setSpacingAfter(5);
           
             
             PdfPCell celdda901 = new PdfPCell(new Phrase("        TEST DE SARGENT",boldSize));
             celdda901.setBorder(Rectangle.NO_BORDER);
             celdda901.setColspan(3); 
             
              PdfPCell celdda909 = new PdfPCell(new Phrase("\n"));
             celdda909.setBorder(Rectangle.NO_BORDER);
             celdda909.setColspan(3);
             
             PdfPCell celdda902 = new PdfPCell(new Phrase("Embergadura:",boldSize));
             celdda902.setBorder(Rectangle.NO_BORDER);
             celdda902.setColspan(2);
             
             PdfPCell celdda9022 = new PdfPCell(new Phrase(getMedicionactual().getMedembergadura()+""));
             celdda9022.setBorder(Rectangle.NO_BORDER); 
             
             
             PdfPCell celdda903 = new PdfPCell(new Phrase("Salto Máximo",boldSize));
             celdda903.setBorder(Rectangle.NO_BORDER);
               celdda903.setColspan(2);
             
             PdfPCell celdda9032 = new PdfPCell(new Phrase(getMedicionactual().getMedsaltomaximo()+""));
             celdda9032.setBorder(Rectangle.NO_BORDER);                           
             
             PdfPCell celdda904 = new PdfPCell(new Phrase("Salto Real",boldSize));
             celdda904.setBorder(Rectangle.NO_BORDER);
             celdda904.setColspan(2);
             
             PdfPCell celdda905 = new PdfPCell(new Phrase(getMedicionactual().getMedsaltoreal()+""));
             celdda905.setBorder(Rectangle.NO_BORDER);   
             
             PdfPCell celdda906 = new PdfPCell(new Phrase("\n \n\n"));
             celdda906.setBorder(Rectangle.NO_BORDER);
             celdda906.setColspan(4);
             
             PdfPCell celdda907 = new PdfPCell(new Phrase("\n"));
             celdda907.setBorder(Rectangle.NO_BORDER); 
             celdda907.setColspan(4);
             
             
             tablass2.addCell(celdda901);
             tablass2.addCell(celdda909); 
             tablass2.addCell(celdda902);
             tablass2.addCell(celdda9022);
             tablass2.addCell(celdda903);             
             tablass2.addCell(celdda9032);
             tablass2.addCell(celdda904);
             tablass2.addCell(celdda905);            
             tablass2.addCell(celdda906);
             tablass2.addCell(celdda907);
             
            celdda.addElement(tablass2);
                 
             
           PdfPTable tablass3 = new PdfPTable(3);
           tablass3.setWidthPercentage(100);
           tablass3.setSpacingAfter(5);
            
            PdfPCell tablaPOL = new PdfPCell(new Phrase("         TEST DE RUFIER",boldSize));
             tablaPOL.setBorder(Rectangle.NO_BORDER); 
             tablaPOL.setColspan(4);
            
            
             
              PdfPCell tablaPOL1 = new PdfPCell(new Phrase("\n"));
             tablaPOL1.setBorder(Rectangle.NO_BORDER);
             tablaPOL1.setColspan(3);
             
             PdfPCell tablaPOL2 = new PdfPCell(new Phrase("Pulso 1:",boldSize));
             tablaPOL2.setBorder(Rectangle.NO_BORDER);
             tablaPOL2.setColspan(2);
             
             PdfPCell tablaPOL3 = new PdfPCell(new Phrase(getMedicionactual().getMedpulso0()+""));
             tablaPOL3.setBorder(Rectangle.NO_BORDER); 
             
             PdfPCell tablaPOL4 = new PdfPCell(new Phrase("Pulso 2:",boldSize));
             tablaPOL4.setBorder(Rectangle.NO_BORDER);
             tablaPOL4.setColspan(2);
             
             PdfPCell tablaPOL5 = new PdfPCell(new Phrase(getMedicionactual().getMedpulso1()+""));
             tablaPOL5.setBorder(Rectangle.NO_BORDER);
             
             
             PdfPCell tablaPOL6 = new PdfPCell(new Phrase("Pulso 3:",boldSize));
             tablaPOL6.setBorder(Rectangle.NO_BORDER);
             tablaPOL6.setColspan(2);
             
             PdfPCell tablaPOL7 = new PdfPCell(new Phrase(getMedicionactual().getMedpulso2()+""));
             tablaPOL7.setBorder(Rectangle.NO_BORDER);
             
             
                   PdfPCell tablaPOL8 = new PdfPCell(new Phrase("Resultado:",boldSize));
             tablaPOL8.setBorder(Rectangle.NO_BORDER);
             tablaPOL8.setColspan(2);
             
             PdfPCell tablaPOL9 = new PdfPCell(new Phrase(getMedicionactual().getTestRufier()+""));
             tablaPOL9.setBorder(Rectangle.NO_BORDER);
             
               PdfPCell tablaPOL11 = new PdfPCell(new Phrase("\n \n\n\n\n"));
             tablaPOL11.setBorder(Rectangle.NO_BORDER);
             tablaPOL11.setColspan(3);
             
                    
             
              PdfPCell tablaPOL10 = new PdfPCell(new Phrase("                    "+getMedicionactual().getEstadoRufier()+""));
              tablaPOL10.setColspan(6);
              
              
            tablass3.addCell(tablaPOL); 
            tablass3.addCell(tablaPOL1); 
            tablass3.addCell(tablaPOL2); 
            tablass3.addCell(tablaPOL3); 
            tablass3.addCell(tablaPOL4); 
            tablass3.addCell(tablaPOL5); 
            tablass3.addCell(tablaPOL6); 
            tablass3.addCell(tablaPOL7); 
            tablass3.addCell(tablaPOL8); 
            tablass3.addCell(tablaPOL9); 
            tablass3.addCell(tablaPOL11); 
            tablass3.addCell(tablaPOL10); 
            celdda2.addElement(tablass3);
            //
                    
            PdfPTable tablass4 = new PdfPTable(3);
           tablass4.setWidthPercentage(100);
           tablass4.setSpacingAfter(5);
            
            PdfPCell tablePOL100 = new PdfPCell(new Phrase("         TEST DE LEGER:",boldSize));
             tablePOL100.setBorder(Rectangle.NO_BORDER); 
             tablePOL100.setColspan(4);
             
             
             PdfPCell tablePOL101 = new PdfPCell(new Phrase("    \n       CONSUMO MAX. \n             OXIGENO:\n\n"));
             tablePOL101.setBorder(Rectangle.NO_BORDER); 
              tablePOL101.setColspan(4);
              
              
              PdfPCell tablePOL102 = new PdfPCell(new Phrase("                    0.0"));
              tablePOL102.setBorder(Rectangle.NO_BORDER); 
              tablePOL102.setColspan(4);
             
              PdfPCell tablePOL103 = new PdfPCell(new Phrase("-------------------------------------"));
              tablePOL103.setBorder(Rectangle.NO_BORDER); 
              tablePOL103.setColspan(4);
              
              PdfPCell tablePOL104 = new PdfPCell(new Phrase("      TEST DE WELLS:\n",boldSize));
              tablePOL104.setBorder(Rectangle.NO_BORDER); 
              tablePOL104.setColspan(4);
              
              PdfPCell tablePOL105 = new PdfPCell(new Phrase("Flexibilidad",boldSize));
              tablePOL105.setBorder(Rectangle.NO_BORDER); 
              tablePOL105.setColspan(2);
              
               PdfPCell tablePOL106 = new PdfPCell(new Phrase(getMedicionactual().getMedflexibilidad()+""));
              tablePOL106.setBorder(Rectangle.NO_BORDER);
              
                PdfPCell tablePOL108 = new PdfPCell(new Phrase("\n "));
             tablePOL108.setBorder(Rectangle.NO_BORDER);
             tablePOL108.setColspan(3); 
             
              PdfPCell tablaPOL107 = new PdfPCell(new Phrase("                  "+getMedicionactual().getEstadoTestWells()+""));
              tablaPOL107.setColspan(6);
              
             tablass4.addCell(tablePOL100);
             tablass4.addCell(tablePOL101);
             tablass4.addCell(tablePOL102);
            tablass4.addCell(tablePOL103);
            tablass4.addCell(tablePOL104);
            tablass4.addCell(tablePOL105);
            tablass4.addCell(tablePOL106);  
             tablass4.addCell(tablePOL108); 
            tablass4.addCell(tablaPOL107);
            celdda4.addElement(tablass4);
            
                    
        tablass1.addCell(celdda);        
        tablass1.addCell(celdda1);
        tablass1.addCell(celdda2);
        tablass1.addCell(celdda3);
        tablass1.addCell(celdda4);
        
            document.add(tablass1);
        
        
          PdfPTable tablassPoL = new PdfPTable(4);
           tablassPoL.setWidthPercentage(100);
           tablassPoL.setSpacingAfter(5);           
                     
             PdfPCell tablePOLTa1 = new PdfPCell(new Phrase(" COMENTARIOS \n ",negrilla));
             tablePOLTa1.setBorder(Rectangle.NO_BORDER); 
             tablePOLTa1.setColspan(4);
             
             PdfPCell tablePOLTa2 = new PdfPCell(new Phrase("  \n \n\n \n\n \n",boldSize));
             tablePOLTa2.setColspan(4);
       
                        

      tablassPoL.addCell(tablePOLTa1);
      tablassPoL.addCell(tablePOLTa2);
      
     URL url = FacesContext.getCurrentInstance().getExternalContext().getResource("/resources/img/antorcha.png");
            Image imgLogoUnicauca = Image.getInstance(url); 


    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, 
    BaseFont.WINANSI, BaseFont.EMBEDDED);

  imgLogoUnicauca.setAbsolutePosition(140, 110);
  
  document.add(imgLogoUnicauca);
      
      
      
      
      
      
           
           document.add(tablassPoL);
            
            document.close();
            RequestContext.getCurrentInstance().update("frmVerRutina");
        } catch (IOException ex) {
            Logger.getLogger(MedidasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(MedidasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
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
}