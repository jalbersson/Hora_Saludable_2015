/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.asistenciaService;

import com.unicauca.horasaludable.asistenciaDomain.Inscrito;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author seven
 */
@ManagedBean
@ApplicationScoped
public class InscritoService {

    /**
     * Creates a new instance of InscritoService
     */
    public InscritoService() {
    }
    public List<Inscrito> crearInscritos() {
        List<Inscrito> inscritos = new ArrayList<Inscrito>();
        
        Inscrito inscrito1 = new Inscrito();
        inscrito1.setPrimerNombre("LUIS");
        inscrito1.setSegundoNombre("GERARDO");
        inscrito1.setPrimerApellido("ALVEAR");
        inscrito1.setSegundoApellido("ORTEGA");
        inscrito1.setDocumentoIdentidad("4613691");
        inscrito1.setDependenciaUniversitaria("T.EDITORIAL");
        
        inscritos.add(inscrito1);
        
        Inscrito inscrito2 = new Inscrito();
        inscrito2.setPrimerNombre("CLAUDIA");
        inscrito2.setSegundoNombre("AMANDA");
        inscrito2.setPrimerApellido("AMAYA");
        inscrito2.setSegundoApellido("GOMEZ");
        inscrito2.setDocumentoIdentidad("34560607");
        inscrito2.setDependenciaUniversitaria("CONYUGE");
        
        inscritos.add(inscrito2);
        
        Inscrito inscrito3 = new Inscrito();
        inscrito3.setPrimerNombre("LUZ");
        inscrito3.setSegundoNombre("ANGELICA");
        inscrito3.setPrimerApellido("ARIAS");
        inscrito3.setSegundoApellido("SARAY");
        inscrito3.setDocumentoIdentidad("34551673");
        inscrito3.setDependenciaUniversitaria("FAC. EDUCACION");
        
        inscritos.add(inscrito3);
        
        return inscritos;
    }
}
