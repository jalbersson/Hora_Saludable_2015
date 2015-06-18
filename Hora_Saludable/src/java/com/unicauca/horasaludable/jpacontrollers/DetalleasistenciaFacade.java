/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Detalleasistencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author seven
 */
@Stateless
public class DetalleasistenciaFacade extends AbstractFacade<Detalleasistencia> {

    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleasistenciaFacade() {
        super(Detalleasistencia.class);
    }

    public List<Detalleasistencia> obtenerAsisUsuid(Long usuid) {
        try {
            String queryString = "SELECT t FROM Detalleasistencia t "
                    + "where t.usuario.usuid=" + usuid;
            Query query = getEntityManager().createQuery(queryString);
            //System.out.println("ERR"+Long.valueOf(usuid.intValue()+""));
            //query.set("usuid", Long.valueOf(usuid.intValue()+""));
            return query.getResultList();
        } finally {
            // em.close();
        }
    }

    public long asistenciaPorMesGrafica2(int mes, int anio) {
        String sqlString = ""
                + "select count(*) "
                + "from "
                + "( "
                + "select DA.USUID, count(DA.ASIId) "
                + "from INSCRIPCION I NATURAL JOIN DETALLEINSCRIPCION DI "
                + "NATURAL JOIN DETALLEASISTENCIA DA NATURAL JOIN ASISTENCIA A "
                + "WHERE DI.DETACTIVO = 1 "
                + "AND I.INSMES = " + mes + " "
                + "AND I.INSANIO = " + anio + " "
                + "AND month(A.ASIFECHA) = " + mes + " "
                + "AND year(A.ASIFECHA) = " + anio + " "
                + "AND DA.DETASISTIO = 1 "
                + "GROUP BY DA.USUId "
                + "HAVING count(DA.ASIID) > 0"
                + ") ASI";
        Query query = em.createNativeQuery(sqlString);

//        int asistentes = -1;
        long asistentes = (long) query.getSingleResult();

//        System.out.println("*1*2*3*4*5*6*7*8*9*****"+asistentes);
        return asistentes;
    }
    
    public long asistenciaPorMesGrafica1(int mes, int anio) {
        String sqlString = ""
                + "select count(*) "
                + "from INSCRIPCION I NATURAL JOIN DETALLEINSCRIPCION DI "
                + "NATURAL JOIN DETALLEASISTENCIA DA NATURAL JOIN ASISTENCIA A "
                + "WHERE DI.DETACTIVO = 1 "
                + "AND I.INSMES = " + mes + " "
                + "AND I.INSANIO = " + anio + " "
                + "AND month(A.ASIFECHA) = " + mes + " "
                + "AND year(A.ASIFECHA) = " + anio + " "
                + "AND DA.DETASISTIO = 1 ";
        
        Query query = em.createNativeQuery(sqlString);

//        int asistentes = -1;
        long asistentes = (long) query.getSingleResult();

//        System.out.println("*1*2*3*4*5*6*7*8*9*****"+asistentes);
        return asistentes;
    }
    
    public long asistenciaDependenciaPorAño(int anio, long uniid) {
        String sqlString = ""
                + "select count(*) "
                + "from "
                + "( "
                + "select DA.USUID, count(DA.ASIId) "
                + "from USUARIO U NATURAL JOIN INSCRIPCION I NATURAL JOIN DETALLEINSCRIPCION DI "
                + "NATURAL JOIN DETALLEASISTENCIA DA NATURAL JOIN ASISTENCIA A "
                + "WHERE U.UNIID = " +uniid+ " "
                + "AND DI.DETACTIVO = 1 "
                + "AND I.INSANIO = " + anio + " "
                + "AND year(A.ASIFECHA) = " + anio + " "
                + "AND DA.DETASISTIO = 1 "
                + "GROUP BY DA.USUId "
                + "HAVING count(DA.ASIID) > 0"
                + ") ASI";
        Query query = em.createNativeQuery(sqlString);

        long asistentes = (long) query.getSingleResult();

        return asistentes;
    }
    
    public long asistenciaDependenciaPorMes(int mes, int anio, long uniid) {
        String sqlString = ""
                + "select count(*) "
                + "from "
                + "( "
                + "select DA.USUID, count(DA.ASIId) "
                + "from USUARIO U NATURAL JOIN INSCRIPCION I NATURAL JOIN DETALLEINSCRIPCION DI "
                + "NATURAL JOIN DETALLEASISTENCIA DA NATURAL JOIN ASISTENCIA A "
                + "WHERE U.UNIID = " +uniid+ " "
                + "AND DI.DETACTIVO = 1 "
                + "AND I.INSMES = " + mes + " "
                + "AND I.INSANIO = " + anio + " "
                + "AND month(A.ASIFECHA) = " + mes + " "
                + "AND year(A.ASIFECHA) = " + anio + " "
                + "AND DA.DETASISTIO = 1 "
                + "GROUP BY DA.USUId "
                + "HAVING count(DA.ASIID) > 0"
                + ") ASI";
        Query query = em.createNativeQuery(sqlString);

        long asistentes = (long) query.getSingleResult();

        return asistentes;
    }
    
    public long asistenciaDependenciaPorSemestre(String fechaI, String fechaF, long uniid) {
        String sqlString = ""
                + "select count(*) "
                + "from "
                + "( "
                + "select DA.USUID, count(DA.ASIId) "
                + "from USUARIO U NATURAL JOIN INSCRIPCION I NATURAL JOIN DETALLEINSCRIPCION DI "
                + "NATURAL JOIN DETALLEASISTENCIA DA NATURAL JOIN ASISTENCIA A "
                + "WHERE U.UNIID = " +uniid+ " "
                + "AND DI.DETACTIVO = 1 "
                + "AND (A.ASIFECHA BETWEEN '" + fechaI + "' "
                + "AND '" + fechaF + "') "
                + "AND DA.DETASISTIO = 1 "
                + "GROUP BY DA.USUId "
                + "HAVING count(DA.ASIID) > 0"
                + ") ASI";
        Query query = em.createNativeQuery(sqlString);

        long asistentes = (long) query.getSingleResult();

        return asistentes;
    }

    public List<Object[]> detalle_dependencia(Date fecha1, Date fecha2) {

        Query query = getEntityManager().createNamedQuery("Detalleasistencia.usuario");
        query.setParameter("date1", fecha1);
        query.setParameter("DATE2", fecha2);
        List<Object[]> resultList = query.getResultList();
        return resultList;

    }

    public List<Detalleasistencia> obtenerDetalleAsistenciaPorUsuIDAsiID(Long asiid, Long usuid, Boolean detasistio) {
        List<Detalleasistencia> resultList;
        try {
            Query query = getEntityManager().createNamedQuery("Detalleasistencia.findByAsiidUsuid");
            query.setParameter("asiid", asiid);
            query.setParameter("usuid", usuid);
            query.setParameter("detasistio", detasistio);
            resultList = query.getResultList();
        } catch (Exception e) {
            resultList = new ArrayList();
        }
        return resultList;
    }
    
    public List<Integer> obtenerAniosAsistencia(String usunombreusuario) {

        Query query = getEntityManager().createNamedQuery("Detalleasistencia.obtenerAniosAsistencia");
        query.setParameter("usunombreusuario", usunombreusuario);        
        List<Integer> resultList = query.getResultList();
        return resultList;

    }
    
    public List<Detalleasistencia> obtenerPorAsiid(Long asiid, Boolean detasistio) {
        List<Detalleasistencia> resultList;
        try {
            Query query = getEntityManager().createNamedQuery("Detalleasistencia.findByAsiidAsistio");
            query.setParameter("asiid", asiid);
            query.setParameter("detasistio", detasistio);
            resultList = query.getResultList();
        } catch (Exception e) {
            resultList = new ArrayList();
        }
        return resultList;
    }
}
