package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.entities.Detalleasistencia;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.AsistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;


@Named(value = "asistencia_usuarios_con_session")
@ManagedBean
@ViewScoped
public class asistencia_usuarios_con_session implements Serializable 
{
    @EJB
    private AsistenciaFacade ebjUsuarioFacade;
    @EJB
    private DetalleasistenciaFacade ebjDetalleasistenciaFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    private ScheduleModel eventModel;
    private ScheduleEvent event;
    private BarChartModel meses;
    private String anioElegido;
    private boolean mostrarEstadisticas;

    
    public String getAnioElegido() {
        return anioElegido;
    }

    public void setAnioElegido(String anioElegido) {
        this.anioElegido = anioElegido;
    }

    public asistencia_usuarios_con_session() {

    }

    @PostConstruct
    public void init() {
        this.cargarSchedule();
        meses = new BarChartModel();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public CartesianChartModel getMeses() {
        return meses;
    }

    public void setMeses(BarChartModel meses) {
        this.meses = meses;
    }
    public boolean isMostrarEstadisticas() 
    {
        return mostrarEstadisticas;
    }

    public void setMostrarEstadisticas(boolean mostrarEstadisticas) 
    {
        this.mostrarEstadisticas = mostrarEstadisticas;
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public List<Usuario> obtener_usuario_usuid() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() != null) 
        {            
            List<Usuario> lst = ebjUsuarioFacade.retornarBuscarPorNombreUsuario(req.getUserPrincipal().getName());
            return lst;
        }
        return null;
    }

    public List<Detalleasistencia> obtener_asistencia_usuid() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() != null) 
        {
           
            List<Detalleasistencia> lst = ebjDetalleasistenciaFacade.obtenerAsisUsuid(ebjUsuarioFacade.retornarBuscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuid());
            return lst;
        }
        return null;
        
    }

    private void cargarSchedule() {
        List<Detalleasistencia> listaAsistencias = this.obtener_asistencia_usuid();
        eventModel = new DefaultScheduleModel();
        int numAsis = 1;
        for (Detalleasistencia asistencias : listaAsistencias) {
            eventModel.addEvent(new DefaultScheduleEvent("Asistencia #"+numAsis,
                    asistencias.getAsistencia().getAsifecha(), asistencias.getAsistencia().getAsifecha(), numAsis));
            numAsis++;
        }
    }

    private Map<Integer, Integer> obtenerNumAsisMes() {
        List<Detalleasistencia> lst = this.obtener_asistencia_usuid();
        Map<Integer, Integer> meses = new HashMap();

        String formato = "MM";
        String formato1 = "YYYY";

        String aux;
        String aux1;
        String aux2;
        String aux3;

        ArrayList<Integer> numeros = new ArrayList();

        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat(formato1);

        int cont;

        for (int j = 1; j <= 12; j++) {
            cont = 1;
            aux2 = Integer.toString(j);
            if (j < 10) {
                aux3 = "0" + aux2;
            } else {
                aux3 = aux2;
            }
            numeros.clear();

            for (Detalleasistencia l : lst) {
                aux = dateFormat.format(l.getAsistencia().getAsifecha());
                aux1 = dateFormat1.format(l.getAsistencia().getAsifecha());
                if (aux1.equals(this.anioElegido) && aux.equals(aux3)) {
                    meses.put(j, cont++);
                }

            }
        }
        return meses;
    }

    private Map<Integer, Integer> llenarmap() {
        Map<Integer, Integer> meses2 = new HashMap();
        meses2 = this.obtenerNumAsisMes();
        for (int i = 1; i <= 12; i++) {
            if (!meses2.containsKey(i)) {
                meses2.put(i, 0);
            }
        }
        return meses2;
    }

    public BarChartModel estadisticas() {
        Map<Integer, Integer> meses1 = new HashMap();
        meses1 = this.llenarmap();

        final ChartSeries contendido = new ChartSeries("Asistencia Anual");
        contendido.set("Ene", meses1.get(1));
        contendido.set("Feb", meses1.get(2));
        contendido.set("Mar", meses1.get(3));
        contendido.set("Abr", meses1.get(4));
        contendido.set("May", meses1.get(5));
        contendido.set("Jun", meses1.get(6));
        contendido.set("Jul", meses1.get(7));
        contendido.set("Ago", meses1.get(8));
        contendido.set("Sep", meses1.get(9));
        contendido.set("Oct", meses1.get(10));
        contendido.set("Nov", meses1.get(11));
        contendido.set("Dic", meses1.get(12));

        meses.clear();
        meses.addSeries(contendido);
        
        meses.setTitle("ASISTENCIA PERSONAL - AÑO: "+anioElegido);
        meses.setLegendPosition("ne");
        meses.setShowPointLabels(true);
        meses.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = meses.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        
        Axis yAxis = meses.getAxis(AxisType.Y);
        yAxis.setLabel("Total de días");
        yAxis.setMin(0);
        yAxis.setMax(102);
        
        return meses;
    }
    
    public List<String> obtenerAniosAsistencia()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() != null) 
        {            
            List<Integer> lst = this.ebjDetalleasistenciaFacade.obtenerAniosAsistencia(req.getUserPrincipal().getName());
            List<String> anios=new ArrayList();
            int contador=0;
            for(Integer objeto:lst)
            {
                anios.add(objeto+"");
                contador++;
            }
            if(contador>0)
            {
                this.anioElegido=anios.get(contador-1);
                this.mostrarEstadisticas=true;
                return anios;
                
            }
            this.mostrarEstadisticas=false;
            return null;
        }
        return null;
    }
}