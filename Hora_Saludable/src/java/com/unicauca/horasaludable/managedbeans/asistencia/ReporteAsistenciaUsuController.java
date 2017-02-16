package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.entities.Asistencia;
import com.unicauca.horasaludable.entities.Detalleasistencia;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.AsistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import com.unicauca.horasaludable.jpacontrollers.DetalleinscripcionFacade;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

@Named(value = "reporteAsistenciaUsuController")
@ManagedBean
@ViewScoped
public class ReporteAsistenciaUsuController implements Serializable {

    @EJB
    private UsuarioFacade usuarioEJB;
    @EJB
    private AsistenciaFacade asistenciaEJB;
    @EJB
    private DetalleasistenciaFacade detalleAsistenciaEJB;
    @EJB
    private DetalleinscripcionFacade detalleInsEJB;

    private int mes;
    private int año;
    private List<TablaAsistenciaAnual> tablaAnual;
    private List<TablaAsistenciaMensual> tablaMensual;
    private int[] totalHorasDia;
    private int totalHorasMes;
    private int[] totalHorasCadaMes;
    private int totalHorasAño;
    private boolean verReporteAnual;
    private boolean verAño;
    private boolean verReporteMensual;
    private boolean verMes;
    private boolean verReporte;
    private boolean verTablaAnual;
    private boolean verTablaMensual;
    private String nombreUsuario;
    private List<String> anios;

    public ReporteAsistenciaUsuController() {
        nombreUsuario = "";
        mes = 0;
        año = 0;
        totalHorasDia = new int[31];
        totalHorasMes = 0;
        totalHorasCadaMes = new int[12];
        totalHorasAño = 0;
        verReporteAnual = false;
        verReporteMensual = false;
        verAño = false;
        verMes = false;
        verReporte = false;
        verTablaAnual = false;
        verTablaMensual = false;
    }

    public String getNombreDelMes() {
        String nombreMes = "";
        switch (mes) {
            case 1:
                nombreMes = "Enero";
                break;
            case 2:
                nombreMes = "Febrero";
                break;
            case 3:
                nombreMes = "Marzo";
                break;
            case 4:
                nombreMes = "Abril";
                break;
            case 5:
                nombreMes = "Mayo";
                break;
            case 6:
                nombreMes = "Junio";
                break;
            case 7:
                nombreMes = "Julio";
                break;
            case 8:
                nombreMes = "Agosto";
                break;
            case 9:
                nombreMes = "Septiembre";
                break;
            case 10:
                nombreMes = "Octubre";
                break;
            case 11:
                nombreMes = "Noviembre";
                break;
            case 12:
                nombreMes = "Diciembre";
                break;
        }

        return nombreMes;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public List<TablaAsistenciaAnual> getTablaAnual() {
        return tablaAnual;
    }

    public void setTablaAnual(List<TablaAsistenciaAnual> tablaAnual) {
        this.tablaAnual = tablaAnual;
    }

    public List<TablaAsistenciaMensual> getTablaMensual() {
        return tablaMensual;
    }

    public void setTablaMensual(List<TablaAsistenciaMensual> tablaMensual) {
        this.tablaMensual = tablaMensual;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Procesa los datos de la asistencia anual, llena el objeto tablaAnual
     *
     */
    public void asistenciaAnual() {
        if (verReporteAnual == true && verReporteMensual == false) {
            tablaAnual = new ArrayList();
            TablaAsistenciaAnual obj;
            int cont = 1;
            List<Usuario> lstUsuarios = detalleInsEJB.usariosActivosPorAño(true, año, nombreUsuario);

            for (Usuario u : lstUsuarios) //Se recorren todos los usuarios
            {
                obj = new TablaAsistenciaAnual();
                for (int i = 1; i <= 12; i++) {
                    if (detalleInsEJB.usuarioActivo(u.getUsuid(), true, String.valueOf(i), año)) {
                        List<Asistencia> lstAsistencia = asistenciaEJB.findByYearMonth(año, i);
                        for (Asistencia a : lstAsistencia) {
                            List<Detalleasistencia> lstDetalleAsistencia = detalleAsistenciaEJB.obtenerDetalleAsistenciaPorUsuIDAsiID(a.getAsiid(), u.getUsuid(), true);

                            if (lstDetalleAsistencia.size() > 0) {
                                obj.setMeses((i - 1), obj.getMeses(i - 1) + 1);
                            }
                        }
                    }
                }
                obj.setNum(cont);
                obj.setNombre(u.getUsuapellidos() + " " + u.getUsunombres());
                obj.setSexo(u.getUsugenero());
                obj.setCodigo(u.getUsuidentificacion());//Se cambió la cedula en lugar del codigo de usuairo, a petición de Nairo
                if (u.getUniid() != null) {
                    obj.setPrograma(u.getUniid().getUninombre());
                }
                if (u.getCarid() != null) {
                    obj.setEstamento(u.getCarid().getCarnombre());
                }
                obj.setAsisTotal(obj.getMeses(0) + obj.getMeses(1) + obj.getMeses(2) + obj.getMeses(3) + obj.getMeses(4) + obj.getMeses(5) + obj.getMeses(6) + obj.getMeses(7) + obj.getMeses(8) + obj.getMeses(9) + obj.getMeses(10) + obj.getMeses(11));
                tablaAnual.add(obj);
                cont++;
            }
        }
    }

    /**
     * Procesa los datos del reporte de la asistencia mensual, llena el objeto
     * tablaMensual
     */
    public void asistenciaMensual() {
        if (verReporteMensual) {
            tablaMensual = new ArrayList();
            TablaAsistenciaMensual obj;
            Calendar c;
            int cont = 1, aux;

            List<Usuario> lstUsuarios = detalleInsEJB.usariosActivos(true, String.valueOf(mes), año, nombreUsuario);

            List<Asistencia> lstAsistencia = asistenciaEJB.findByYearMonth(año, mes);

            for (Usuario u : lstUsuarios) //Se recorren todos los usuarios
            {
                obj = new TablaAsistenciaMensual();
                aux = 0;
                if (detalleInsEJB.usuarioActivo(u.getUsuid(), true, String.valueOf(mes), año)) {
                    for (Asistencia a : lstAsistencia) {
                        List<Detalleasistencia> lstDetalleAsistencia = detalleAsistenciaEJB.obtenerDetalleAsistenciaPorUsuIDAsiID(a.getAsiid(), u.getUsuid(), true);

                        if (lstDetalleAsistencia.size() > 0) {
                            c = GregorianCalendar.getInstance();
                            c.setTime(a.getAsifecha());
                            int dia = c.get(Calendar.DAY_OF_MONTH);
                            obj.setDias(dia - 1, "X");
                            aux++;
                        }
                    }
                }
                obj.setNum(cont);
                obj.setNombre(u.getUsuapellidos() + " " + u.getUsunombres());
                //obj.setSexo(u.getUsugenero());
                if (u.getUsugenero() == 'M') {
                    obj.setMasculino('X');
                    obj.setFemenino(' ');
                } else {
                    obj.setMasculino(' ');
                    obj.setFemenino('X');
                }

                obj.setCodigo(u.getUsuidentificacion());//Se cambió la cedula en lugar del codigo de usuairo, a petición de Nairo
                if (u.getUniid() != null) {
                    obj.setPrograma(u.getUniid().getUninombre());
                }
                if (u.getCarid() != null) {
                    if (u.getCarid().getCarnombre().equals("Docente")) {
                        obj.setDocente("X");
                    }
                    if (u.getCarid().getCarnombre().equals("Administrativo")) {
                        obj.setFuncionario("X");
                    }
                    //obj.setEstamento(u.getCarid().getCarnombre());
                } else {
                    //Es nulo, toca descartar si es familiar o estudiante
                    if (u.getConyugeid() == null && u.getCarid() == null) {
                        obj.setEstudiante("X");
                    } else {
                        obj.setFamiliar("X");
                    }
                }
                obj.setAsisTotal(aux);
                tablaMensual.add(obj);
                cont++;
            }
        }
    }

    /**
     * Da formato al reporte de asistencia anual en formato excel
     *
     * @param document
     */
    public void postProcessXLSAnual(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        wb.setSheetName(0, "CONSOLIDADO " + año);
        HSSFSheet sheet = wb.getSheetAt(0);

        sheet.shiftRows(0, sheet.getLastRowNum(), 6);

        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 18));

        HSSFFont font1 = wb.createFont();
        font1.setFontName(HSSFFont.FONT_ARIAL);
        font1.setFontHeightInPoints((short) 14);
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font1.setColor(HSSFColor.BLUE.index);
        HSSFCellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle1.setFont(font1);

        HSSFRow header = sheet.createRow(0);
        HSSFCell fcell1 = header.createCell(0);
        fcell1.setCellValue("UNIVERSIDAD DEL CAUCA");
        fcell1.setCellStyle(cellStyle1);

        HSSFFont font2 = wb.createFont();
        font2.setFontName(HSSFFont.FONT_ARIAL);
        font2.setFontHeightInPoints((short) 11);
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font2.setColor(HSSFColor.BLUE.index);
        HSSFCellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle2.setFont(font2);

        sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 18));

        HSSFRow header1 = sheet.createRow(2);
        HSSFCell fcell2 = header1.createCell(0);
        fcell2.setCellValue("Formato Registro Asistencia a Programas");
        fcell2.setCellStyle(cellStyle2);

        HSSFFont font3 = wb.createFont();
        font3.setFontName(HSSFFont.FONT_ARIAL);
        font3.setFontHeightInPoints((short) 9);
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle3.setFont(font3);

        sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 2));

        HSSFRow header2 = sheet.createRow(4);
        HSSFCell fcell3 = header2.createCell(0);
        fcell3.setCellValue("INSTRUCTOR: NAIRO BURBANO SANCHEZ");
        fcell3.setCellStyle(cellStyle3);

        sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 5));

        HSSFCell fcell4 = header2.createCell(3);
        fcell4.setCellValue("PROGRAMA: HORA SALUDABLE");
        fcell4.setCellStyle(cellStyle3);

        sheet.addMergedRegion(new CellRangeAddress(4, 4, 6, 18));

        DateFormat df = DateFormat.getDateInstance();
        String s = df.format(new Date());
        HSSFCell fcell5 = header2.createCell(6);
        fcell5.setCellValue("FECHA: " + s);
        fcell5.setCellStyle(cellStyle3);

        HSSFRow titulosTabla = sheet.getRow(6);

        HSSFCellStyle cellStyle4 = wb.createCellStyle();
        cellStyle4.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle4.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle4.setFont(font3);
        cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle4.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle4.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

        for (int i = 0; i < titulosTabla.getPhysicalNumberOfCells(); i++) {

            HSSFCell cell = titulosTabla.getCell(i);
            cell.setCellStyle(cellStyle4);
            sheet.autoSizeColumn(i);
        }

        HSSFFont font4 = wb.createFont();
        font4.setFontName(HSSFFont.FONT_ARIAL);
        font4.setFontHeightInPoints((short) 8);
        HSSFCellStyle cellStyle5 = wb.createCellStyle();
        cellStyle5.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle5.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle5.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle5.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle5.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle5.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle5.setFont(font4);

        HSSFCellStyle cellStyle6 = wb.createCellStyle();
        cellStyle6.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyle6.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle6.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle6.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle6.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle6.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle6.setFont(font4);

        long[] totales = new long[13];
        for (int i = 0; i < 13; i++) {
            totales[i] = 0;
        }

        for (int i = 7; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int cn = row.getFirstCellNum(); cn < row.getLastCellNum(); cn++) {
                    long aux;
                    Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
                    c.setCellStyle(cellStyle5);
                    if (cn == 1) {
                        c.setCellStyle(cellStyle6);
                    } else if (cn == 0 || cn == 3 || cn >= 6) {
                        aux = Long.parseLong(c.getStringCellValue());
                        row.removeCell(c);
                        Cell cell = row.createCell(cn);
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(aux);
                        cell.setCellStyle(cellStyle5);
                        if (cn > 5) {
                            totales[cn - 6] += aux;
                        }
                    }
                    sheet.autoSizeColumn(cn);
                }
            }
        }

        int lastRow = sheet.getLastRowNum() + 1;

        HSSFCellStyle cellStyle7 = wb.createCellStyle();
        cellStyle7.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyle7.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle7.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle7.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle7.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle7.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle7.setFont(font3);

        sheet.addMergedRegion(new CellRangeAddress(lastRow, lastRow, 0, 5));
        HSSFRow totalRow = sheet.createRow(lastRow);
        for (int i = 0; i < 6; i++) {
            HSSFCell fcell6 = totalRow.createCell(i);
            if (i == 0) {
                fcell6.setCellValue("TOTAL (Horas):  ");
            }
            fcell6.setCellStyle(cellStyle7);
        }

        HSSFFont font5 = wb.createFont();
        font5.setFontName(HSSFFont.FONT_ARIAL);
        font5.setFontHeightInPoints((short) 9);
        font5.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle cellStyle9 = wb.createCellStyle();
        cellStyle9.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle9.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle9.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle9.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle9.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle9.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle9.setFont(font5);

        for (int i = 0; i < 13; i++) {
            HSSFCell fcell7 = totalRow.createCell(i + 6);
            fcell7.setCellType(Cell.CELL_TYPE_NUMERIC);
            fcell7.setCellValue(totales[i]);
            fcell7.setCellStyle(cellStyle9);
        }

        int obsRow = lastRow + 2;
        HSSFCellStyle cellStyle8 = wb.createCellStyle();
        cellStyle8.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyle8.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        cellStyle8.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle8.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle8.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle8.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle8.setFont(font3);
        cellStyle8.setWrapText(true);

        sheet.addMergedRegion(new CellRangeAddress(obsRow, obsRow + 2, 0, 18));

        for (int j = obsRow; j < obsRow + 3; j++) {
            HSSFRow observacionesRow = sheet.createRow(j);
            for (int i = 0; i < 19; i++) {
                HSSFCell fcell7 = observacionesRow.createCell(i);
                if (i == 0) {
                    fcell7.setCellValue("OBSERVACIONES E IMPREVISTOS:");
                }
                fcell7.setCellStyle(cellStyle8);
            }
        }

        int diasNoLabRow = obsRow + 4;

        sheet.addMergedRegion(new CellRangeAddress(diasNoLabRow, diasNoLabRow, 0, 18));
        sheet.addMergedRegion(new CellRangeAddress(diasNoLabRow + 1, diasNoLabRow + 1, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(diasNoLabRow + 1, diasNoLabRow + 1, 2, 18));
        sheet.addMergedRegion(new CellRangeAddress(diasNoLabRow + 2, diasNoLabRow + 2, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(diasNoLabRow + 2, diasNoLabRow + 2, 2, 18));
        sheet.addMergedRegion(new CellRangeAddress(diasNoLabRow + 3, diasNoLabRow + 3, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(diasNoLabRow + 3, diasNoLabRow + 3, 2, 18));

        HSSFRow diasNoLaboralRow = sheet.createRow(diasNoLabRow);
        for (int i = 0; i < 19; i++) {
            HSSFCell fcell8 = diasNoLaboralRow.createCell(i);
            if (i == 0) {
                fcell8.setCellValue("DIAS NO LABORADOS: (Detalle la fecha y el motivo de su ausencia al sitio de trabajo)");
            }
            fcell8.setCellStyle(cellStyle9);
        }

        for (int i = diasNoLabRow + 1; i < diasNoLabRow + 4; i++) {
            HSSFRow fechaMotivoRow = sheet.createRow(i);
            for (int j = 0; j < 2; j++) {
                HSSFCell fcell9 = fechaMotivoRow.createCell(j);
                if (j == 0) {
                    fcell9.setCellValue("FECHA:");
                }
                fcell9.setCellStyle(cellStyle8);
            }
            for (int j = 2; j < 19; j++) {
                HSSFCell fcell10 = fechaMotivoRow.createCell(j);
                if (j == 2) {
                    fcell10.setCellValue("MOTIVO:");
                }
                fcell10.setCellStyle(cellStyle8);
            }
        }

        HSSFFont font6 = wb.createFont();
        font6.setFontName(HSSFFont.FONT_ARIAL);
        font6.setColor(HSSFColor.ROYAL_BLUE.index);
        font6.setFontHeightInPoints((short) 9);
        font6.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle cellStyle10 = wb.createCellStyle();
        cellStyle10.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle10.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle10.setFont(font6);

        sheet.addMergedRegion(new CellRangeAddress(diasNoLabRow + 4, diasNoLabRow + 4, 0, 18));
        HSSFRow pieRow = sheet.createRow(diasNoLabRow + 4);
        HSSFCell footer = pieRow.createCell(0);
        footer.setCellValue("Universidad del Cauca – Oficina de Planeación");
        footer.setCellStyle(cellStyle10);
    }

    /**
     * Da formato al reporte de asistencia mensual en formato excel, el cual ya
     * fue generado por el control jsf <p:dataExporter type="xls">
     *
     * @param document, referencia al archivo de hoja excel
     */
    public void postProcessXLSMensual(Object document) {
        //Obtiene una instancia workbook (libro excel) para poderlo editar y dar formato
        HSSFWorkbook wb = (HSSFWorkbook) document;
        //Fija el nombre
        wb.setSheetName(0, getNombreDelMes() + " " + año);
        //Obtiene la primer hora del workbook
        HSSFSheet sheet = wb.getSheetAt(0);

        //Desplaza (inserta) filas entre startRow y endRow, n number of rows. Para poder crear el encabezado
        sheet.shiftRows(0, sheet.getLastRowNum(), 8);

        // Header 
        //mezcla varias filas en una
        sheet.addMergedRegion(new CellRangeAddress(0, 4, 0, 1));//primera fila, ultima fila, primera columna, ultima columna
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 5));
        sheet.addMergedRegion(new CellRangeAddress(2, 4, 2, 5));
        sheet.addMergedRegion(new CellRangeAddress(0, 4, 6, 46));

        HSSFFont font1 = wb.createFont();
        font1.setFontName(HSSFFont.FONT_ARIAL);
        font1.setFontHeightInPoints((short) 12);
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle1.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle1.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle1.setFont(font1);
        //Inserta la informacion del encabezado
        for (int i = 0; i < 5; i++) { //Se insertan 5 filas
            HSSFRow header = sheet.createRow(i);
            for (int j = 0; j < 47; j++) { //Se insertan 47 columanas que es el ancho de todo el reporte
                HSSFCell fcell = header.createCell(j);
                fcell.setCellStyle(cellStyle1);

                if (j == 2 && i == 0) {
                    fcell.setCellValue("Vicerrectoría de Cultura y Bienestar");
                } else if (j == 6) {
                    fcell.setCellValue("Formato Registro Asistencia a Programas");
                } else if (i == 2 && j == 2) {
                    fcell.setCellValue("División de Deporte y Recreación");
                }
            }
        }

        HSSFRow header2 = sheet.createRow(6);

        HSSFFont font2 = wb.createFont();
        font2.setFontName(HSSFFont.FONT_ARIAL);
        font2.setFontHeightInPoints((short) 9);
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle2.setFont(font2);

        sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));

        HSSFCell fcell3 = header2.createCell(0);
        fcell3.setCellValue("INSTRUCTOR: NAIRO BURBANO SANCHEZ");
        fcell3.setCellStyle(cellStyle2);

        sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 5));

        HSSFCell fcell4 = header2.createCell(2);
        fcell4.setCellValue("PROGRAMA: HORA SALUDABLE");
        fcell4.setCellStyle(cellStyle2);

        sheet.addMergedRegion(new CellRangeAddress(6, 6, 6, 46));

        Date now = new Date();
        DateFormat df = DateFormat.getDateInstance();
        String s = df.format(now);

        HSSFCell fcell5 = header2.createCell(6);
        fcell5.setCellValue("FECHA: " + s);
        fcell5.setCellStyle(cellStyle2);
        //HSSFRow representa una fila de la hoja
        //La fila 8 representa los encabezados de la tabla:No, Nombres, Apellidos, etc.
        HSSFRow titulosTabla = sheet.getRow(8);

        HSSFFont font3 = wb.createFont();
        font3.setFontName(HSSFFont.FONT_ARIAL);
        font3.setFontHeightInPoints((short) 9);
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle3.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle3.setFont(font3);
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

        //Da formato a las celdas de los encabezados de la tabla
        for (int i = 0; i < titulosTabla.getPhysicalNumberOfCells(); i++) {

            HSSFCell cell = titulosTabla.getCell(i);
            cell.setCellStyle(cellStyle3);
            if (i > 14 && i < 46) {//Son los dias: 1,2..,30,31, que estan desde la columna 15 hasta la 45
                long aux = Long.parseLong(cell.getStringCellValue());
                titulosTabla.removeCell(cell);
                Cell cellActual = titulosTabla.createCell(i);
                cellActual.setCellType(Cell.CELL_TYPE_NUMERIC);
                cellActual.setCellValue(aux);
                cellActual.setCellStyle(cellStyle3);
            }
            sheet.autoSizeColumn(i);
        }

        HSSFFont font4 = wb.createFont();
        font4.setFontName(HSSFFont.FONT_ARIAL);
        font4.setFontHeightInPoints((short) 8);
        HSSFCellStyle cellStyle4 = wb.createCellStyle();
        cellStyle4.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle4.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle4.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle4.setFont(font4);

        HSSFCellStyle cellStyle5 = wb.createCellStyle();
        cellStyle5.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyle5.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle5.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle5.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle5.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle5.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle5.setFont(font4);

        //Inicializa el un vector para los totales, de 0 a 31 dias, se necesitan 32 casillas
        int[] totales = new int[32];
        int[] otrosTotales = new int[7];
        for (int i = 0; i < 32; i++) {//31 dias
            totales[i] = 0;
        }
        for (int i = 0; i < 7; i++) {//para sumar las columnas: M,F,HS,E,D,F,Fliar

            otrosTotales[i] = 0;
        }
        //Da formato a los datos, despues de los encabezados
        //En la fila 9 empieza los datos del primer usuario
        //Tambien calcula los totales
        for (int i = 9; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                //Manipula las columnas
                for (int cn = row.getFirstCellNum(); cn < row.getLastCellNum(); cn++) {
                    Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
                    if (cn == 1) {
                        c.setCellStyle(cellStyle5);
                    } else {
                        c.setCellStyle(cellStyle4);
                        if (cn > 14 && cn < 46) {//De la columna 15 hasta la 45, son las asistencias de los dias (las x)
                            if (c.getStringCellValue().equals("X")) {
                                totales[cn - 15] += 1;//Contador de asistencias
                            }
                        }
                        //0:No, 4:Identificacion, 37: Total
                        if (cn == 0 || cn == 4 || cn == 46) {
                            long aux = Long.parseLong(c.getStringCellValue());
                            row.removeCell(c);
                            Cell cell = row.createCell(cn);
                            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(aux);
                            cell.setCellStyle(cellStyle4);
                            //37:Total
                            if (cn == 46) {
                                totales[cn - 15] += aux;
                            }
                        }
                        if (cn == 2) {//Columna Masculino
                            if (c.getStringCellValue().equals("X")) {
                                otrosTotales[0] += 1;//Contador de Masculinos
                            } else {
                                otrosTotales[1] += 1;//Contador de Femeninos
                            }
                        }
                        if (cn == 11) {//Columna Estudiantes
                            if (c.getStringCellValue().equals("X")) {
                                otrosTotales[2] += 1;//Contador de Estudiantes
                            }
                        }
                        if (cn == 12) {//Columna Docentes
                            if (c.getStringCellValue().equals("X")) {
                                otrosTotales[3] += 1;//Contador de Docentes
                            }
                        }
                        if (cn == 13) {//Columna Funcionarios
                            if (c.getStringCellValue().equals("X")) {
                                otrosTotales[4] += 1;//Contador de Funcionarios
                            }
                        }
                        if (cn == 14) {//Columna familiares
                            if (c.getStringCellValue().equals("X")) {
                                otrosTotales[5] += 1;//Contador de Familiares
                            }
                        }

                    }
                    sheet.autoSizeColumn(cn);
                }
            }
        }

        int lastRow = sheet.getLastRowNum() + 1;

        HSSFCellStyle cellStyle6 = wb.createCellStyle();
        cellStyle6.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyle6.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle6.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle6.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle6.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle6.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle6.setFont(font2);

        sheet.addMergedRegion(new CellRangeAddress(lastRow, lastRow, 0, 1));//mezcla columnas de los totales, columna 0 y 1
        sheet.addMergedRegion(new CellRangeAddress(lastRow, lastRow, 4, 5));//mezcla columnas de los totales, columna 4 y 5

        //Crea una nueva fila al final para poner los totales de horas de cada dia
        HSSFRow totalRow = sheet.createRow(lastRow);
        for (int i = 0; i < 15; i++) {
            HSSFCell fcell7 = totalRow.createCell(i);
            //if (i == 0) {
            //    fcell7.setCellValue("TOTAL (Horas):  ");
            //}
            fcell7.setCellStyle(cellStyle6);
        }

        HSSFFont font5 = wb.createFont();
        font5.setFontName(HSSFFont.FONT_ARIAL);
        font5.setFontHeightInPoints((short) 9);
        font5.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle cellStyle7 = wb.createCellStyle();
        cellStyle7.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle7.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle7.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle7.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle7.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle7.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle7.setFont(font5);

        //Poner los valores de los totales
        for (int i = 0; i < 32; i++) {//los dias del mes
            HSSFCell fcell8 = totalRow.createCell(i + 15);//Desplaza 15, porque ahi empieza el dia 1
            fcell8.setCellType(Cell.CELL_TYPE_NUMERIC);
            fcell8.setCellValue(totales[i]);
            fcell8.setCellStyle(cellStyle7);
        }
        //Poner otros totales
        //Total masculinos
        HSSFCell fcell9 = totalRow.createCell(2);//2: Columna masculinos
        fcell9.setCellType(Cell.CELL_TYPE_NUMERIC);
        fcell9.setCellValue(otrosTotales[0]);//En la pos 0 está los masculinos
        fcell9.setCellStyle(cellStyle7);
        //Total femeninos
        fcell9 = totalRow.createCell(3);//3: Columna masculinos
        fcell9.setCellType(Cell.CELL_TYPE_NUMERIC);
        fcell9.setCellValue(otrosTotales[1]);//En la pos 0 está los masculinos
        fcell9.setCellStyle(cellStyle7);
        //Total personas HS
        fcell9 = totalRow.createCell(9);//9: HS
        fcell9.setCellType(Cell.CELL_TYPE_NUMERIC);
        fcell9.setCellValue(otrosTotales[0] + otrosTotales[1]);//Suma hombres + Mujeres
        fcell9.setCellStyle(cellStyle7);

        //Total Estudiantes
        fcell9 = totalRow.createCell(11);//11: Estudiantes
        fcell9.setCellType(Cell.CELL_TYPE_NUMERIC);
        fcell9.setCellValue(otrosTotales[2]);//Estudiantes
        fcell9.setCellStyle(cellStyle7);
        //Total Docentes
        fcell9 = totalRow.createCell(12);//12: Docentes
        fcell9.setCellType(Cell.CELL_TYPE_NUMERIC);
        fcell9.setCellValue(otrosTotales[3]);//Docentes
        fcell9.setCellStyle(cellStyle7);
        //Total Funcionarios
        fcell9 = totalRow.createCell(13);//13: Funcionarios
        fcell9.setCellType(Cell.CELL_TYPE_NUMERIC);
        fcell9.setCellValue(otrosTotales[4]);
        fcell9.setCellStyle(cellStyle7);
        //Total Familiares
        fcell9 = totalRow.createCell(14);//14: Familiares
        fcell9.setCellType(Cell.CELL_TYPE_NUMERIC);
        fcell9.setCellValue(otrosTotales[5]);//Docentes
        fcell9.setCellStyle(cellStyle7);

        //Crea las observaciones
        int obsRow = lastRow + 2;
        sheet.addMergedRegion(new CellRangeAddress(obsRow, obsRow, 0, 46));
        sheet.addMergedRegion(new CellRangeAddress(obsRow + 1, obsRow + 1, 0, 46));
        sheet.addMergedRegion(new CellRangeAddress(obsRow + 2, obsRow + 2, 0, 46));
        sheet.addMergedRegion(new CellRangeAddress(obsRow + 3, obsRow + 3, 0, 46));
        sheet.addMergedRegion(new CellRangeAddress(obsRow + 4, obsRow + 4, 0, 46));
        sheet.addMergedRegion(new CellRangeAddress(obsRow + 5, obsRow + 5, 0, 46));

        HSSFCellStyle cellStyle8 = wb.createCellStyle();
        cellStyle8.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyle8.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        cellStyle8.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle8.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle8.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle8.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle8.setFont(font2);

        for (int j = obsRow; j < obsRow + 6; j++) {
            HSSFRow observacionesRow = sheet.createRow(j);
            for (int i = 0; i < 47; i++) {
                HSSFCell fcell7 = observacionesRow.createCell(i);
                if (i == 0 && j == obsRow) {
                    fcell7.setCellValue("OBSERVACIONES E IMPREVISTOS:");
                }
                fcell7.setCellStyle(cellStyle8);
            }
        }

        HSSFFont font6 = wb.createFont();
        font6.setFontName(HSSFFont.FONT_ARIAL);
        font6.setColor(HSSFColor.ROYAL_BLUE.index);
        font6.setFontHeightInPoints((short) 9);
        font6.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle cellStyle9 = wb.createCellStyle();
        cellStyle9.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle9.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle9.setFont(font6);

        //Crea el footer final
        sheet.addMergedRegion(new CellRangeAddress(obsRow + 6, obsRow + 6, 0, 46));
        HSSFRow fooRow = sheet.createRow(obsRow + 6);
        HSSFCell footer = fooRow.createCell(0);
        footer.setCellValue("Universidad del Cauca – Oficina de Planeación -Formato VCB-DDR-FOR-003");
        footer.setCellStyle(cellStyle9);

        //Poner la fila complementaria a los encabezados de fila        
        //Desplaza (inserta) filas entre startRow y endRow, n number of rows.
        sheet.shiftRows(7, sheet.getLastRowNum(), 1);
        //Crea la nueva fila
        HSSFRow header = sheet.createRow(8);
        for (int j = 0; j < 47; j++) { //Se insertan 46 columanas
            HSSFCell fcell = header.createCell(j);
            fcell.setCellStyle(cellStyle3);
        }
        //Mezcla la Columna No.
        sheet.addMergedRegion(new CellRangeAddress(8, 9, 0, 0));//primera fila, ultima fila, primera columna, ultima columna
        header = sheet.getRow(8);
        HSSFCell fcell = header.getCell(0);
        fcell.setCellValue("No");
        //Mezcla la Columna Nombres y apellidos.
        sheet.addMergedRegion(new CellRangeAddress(8, 9, 1, 1));//primera fila, ultima fila, primera columna, ultima columna
        header = sheet.getRow(8);
        fcell = header.getCell(1);
        fcell.setCellValue("NOMBRES Y APELLIDOS");
        //Mezcla Sexo
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 2, 3));//primera fila, ultima fila, primera columna, ultima columna
        header = sheet.getRow(8);
        fcell = header.getCell(2);
        fcell.setCellValue("Sexo");
        //Mezcla el codigo
        sheet.addMergedRegion(new CellRangeAddress(8, 9, 4, 4));//primera fila, ultima fila, primera columna, ultima columna
        header = sheet.getRow(8);
        fcell = header.getCell(4);
        fcell.setCellValue("CÓDIGO");
        //Mezcla el programa academino
        sheet.addMergedRegion(new CellRangeAddress(8, 9, 5, 5));//primera fila, ultima fila, primera columna, ultima columna
        header = sheet.getRow(8);
        fcell = header.getCell(5);
        fcell.setCellValue("PROGRAMA ACADEMICO");
        //Mezcla el programa
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 6, 10));//primera fila, ultima fila, primera columna, ultima columna
        header = sheet.getRow(8);
        fcell = header.getCell(6);
        fcell.setCellValue("Programa");        
        //Mezcla el estamento
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 11, 14));//primera fila, ultima fila, primera columna, ultima columna
        header = sheet.getRow(8);
        fcell = header.getCell(11);
        fcell.setCellValue("Estamento");           
        //Mezcla el mes
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 15, 45));//primera fila, ultima fila, primera columna, ultima columna
        header = sheet.getRow(8);
        fcell = header.getCell(15);
        fcell.setCellValue("Mes:" + getNombreDelMes());
        /*
         int my_picture_id;
         //Convertir imagen a bytes
         try ( //Leer la imagen
         InputStream image = getClass().getResourceAsStream("/logo.png")) {
         //Convertir imagen a bytes
         byte[] bytes = IOUtils.toByteArray(image);
         //Adicionar la imagen al wb y obtener el index de la imagen
         my_picture_id = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
         //Cierro la imagen
         }
        
         HSSFPatriarch drawing = sheet.createDrawingPatriarch();
         ClientAnchor my_anchor = new HSSFClientAnchor();
         my_anchor.setCol1(2);
         my_anchor.setRow1(1);           
        
         HSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
         my_picture.resize();
        
         //Escribir los cambios del xls
         //FileOutputStream out = new FileOutputStream(new File("C:\\excel_insert_image_example.xls"));
         //wb.write(out);
         //out.close();
         */
    }

    public int[] getTotalHorasDia() {
        for (int i = 0; i < 31; i++) {
            totalHorasDia[i] = 0;
            for (int j = 0; j < tablaMensual.size(); j++) {
                if (tablaMensual.get(j).getDias(i).equals("X")) {
                    totalHorasDia[i] += 1;
                }
            }
        }
        return totalHorasDia;
    }

    public void setTotalHorasDia(int[] totalHorasDia) {
        this.totalHorasDia = totalHorasDia;
    }

    public int getTotalHorasMes() {
        totalHorasMes = 0;
        for (int i = 0; i < tablaMensual.size(); i++) {
            totalHorasMes += tablaMensual.get(i).getAsisTotal();
        }
        return totalHorasMes;
    }

    public void setTotalHorasMes(int totalHorasMes) {
        this.totalHorasMes = totalHorasMes;
    }

    public int[] getTotalHorasCadaMes() {
        for (int i = 0; i < 12; i++) {
            totalHorasCadaMes[i] = 0;
            for (int j = 0; j < tablaAnual.size(); j++) {
                totalHorasCadaMes[i] += tablaAnual.get(j).getMeses(i);
            }
        }
        return totalHorasCadaMes;
    }

    public void setTotalHorasCadaMes(int[] totalHorasCadaMes) {
        this.totalHorasCadaMes = totalHorasCadaMes;
    }

    public int getTotalHorasAño() {
        totalHorasAño = 0;
        for (int i = 0; i < tablaAnual.size(); i++) {
            totalHorasAño += tablaAnual.get(i).getAsisTotal();
        }
        return totalHorasAño;
    }

    public void setTotalHorasAño(int totalHorasAño) {
        this.totalHorasAño = totalHorasAño;
    }

    //Para habilitar/deshabilitar componentes:
    public boolean isVerReporteAnual() {
        return verReporteAnual;
    }

    public void setVerReporteAnual(boolean verReporteAnual) {
        this.verReporteAnual = verReporteAnual;
    }

    public boolean isVerAño() {
        return verAño;
    }

    public void setVerAño(boolean verAño) {
        this.verAño = verAño;
    }

    public boolean isVerReporteMensual() {
        return verReporteMensual;
    }

    public void setVerReporteMensual(boolean verReporteMensual) {
        this.verReporteMensual = verReporteMensual;
    }

    public List<String> getAnios() {
        
        generarAnios();
        
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

    
    
    public boolean isVerMes() {
        return verMes;
    }

    public void setVerMes(boolean verMes) {
        this.verMes = verMes;
    }

    public boolean isVerReporte() {
        return verReporte;
    }

    public void setVerReporte(boolean verReporte) {
        this.verReporte = verReporte;
    }

    public boolean isVerTablaAnual() {
        if (verReporte == true && verReporteAnual == true && verAño == true && verReporteMensual == false) {
            return true;
        } else {
            return false;
        }
    }

    public void setVerTablaAnual(boolean verTablaAnual) {
        this.verTablaAnual = verTablaAnual;
    }

    public boolean isVerTablaMensual() {
        if (verReporte == true && verReporteMensual == true && verAño == true && verMes == true) {
            return true;
        } else {
            return false;
        }
    }

    public void setVerTablaMensual(boolean verTablaMensual) {
        this.verTablaMensual = verTablaMensual;
    }

    public void cambiarTipo(ValueChangeEvent e) {
        String tipo = e.getNewValue().toString();
        año = 0;
        mes = 0;
        nombreUsuario = "";
        if (tipo.equals("Anual")) {
            this.verReporteAnual = true;
            this.verReporteMensual = false;
        }
        if (tipo.equals("Mensual")) {
            this.verReporteAnual = true;
            this.verReporteMensual = true;
        }
        if (tipo.equals("NingunReporte")) {
            this.verReporteAnual = false;
            this.verReporteMensual = false;
        }
        this.verReporte = false;
    }

    public void cambiarAño(ValueChangeEvent e) {
        tablaAnual = new ArrayList();

        String tipo = e.getNewValue().toString();

        if (!tipo.equals("0")) {
            verAño = true;
            if (verReporteMensual == false || verMes == true) {
                verReporte = true;
            } else {
                verReporte = false;
            }
        } else {
            verReporte = false;
            verAño = false;
        }
    }

    public void cambiarMes(ValueChangeEvent e) {
        tablaMensual = new ArrayList();

        String tipo = e.getNewValue().toString();
        if (!tipo.equals("0")) {
            verMes = true;
            if (verReporteMensual == true && verAño == true) {
                verReporte = true;
            } else {
                verReporte = false;
            }
        } else {
            verReporte = false;
            verMes = false;
        }
    }
    
    private void generarAnios()
    {
        anios = new ArrayList();
        Date date = new Date();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);        
        while(year> 2014)
        {
            String anio = year + "";
            anios.add(anio);
            year --;
        }
        String anio = year + "";
        anios.add(anio);
    }
}
