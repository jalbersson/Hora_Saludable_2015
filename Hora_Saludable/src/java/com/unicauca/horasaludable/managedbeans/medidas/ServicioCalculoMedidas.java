/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

import com.unicauca.horasaludable.entities.Medida;

/**
 *
 * @author pcblanco
 */
public class ServicioCalculoMedidas {
    
    private Medida med;
    private int sexo;  // 0 M -- 1 F
    private double talla;  //en cm
    private int edad; //en años

    public ServicioCalculoMedidas(Medida med,int sexo,double talla,int edad) {
        this.med = med;
        this.sexo = sexo;
        this.talla = talla;
        this.edad = edad;
    }
    
    
    public double sumatoriadepliegues()
      {
          return med.getMedtriceps() +  med.getMedsubescapular() + med.getMedsuprailiaco() + med.getMedmuslo() + med.getMedpantorilla() + med.getMedabdominal();
      }      
    
   public double porcentajeGrasa()
      {
          double r;
          if(sexo==0)
                {
                r = sumatoriadepliegues()*0.097 +3.64;  
                }
          else 
               {
                r = sumatoriadepliegues()*0.1429 + 4.56;  
                }
          return r;
      }  
    
   public double pesoGraso()
      {
          return (med.getMedpeso() * porcentajeGrasa())/100; 
      }  
   
   public double pesolibregrasa()
      {
          return med.getMedpeso() - pesoGraso();
      }
   
   public double masamuscular()
      {
          return 0;
      } 
   
   public double pesoideal()
      {
          return 0.75*(talla - 150) + 50;
      }  
   public double indicemasacorporal()
      {
          return med.getMedpeso() / 2.89;
      } 
   
   public double complexion()
      {
          return talla / med.getMedperimetromuneca();
      }  
   
   public double tasametabolicabasal()
      {
          double r;
          if(sexo==0)
                {
                r = 66 + (13.8 * med.getMedpeso()) + (5 * talla) - (6.8 * edad);
                }
          else 
               {
                r = 655 + (9.6 * med.getMedpeso()) + (1.7 * talla) - (4.7 * edad);  
                }
          return r;
      }
   
   public double excesodepeso()
      {
          return med.getMedpeso()-pesooptimo();
      }  
   
   public double pesooptimo()
      {
          return pesolibregrasa() / 0.9;
      }  
   
   public double masaesqcuerpo()
      {
         return 0;
      }  
   
   public double masatotalosea()
      {
          return 0;
      }  
   
   public double porcentajegrasaideal()
      {
         return 0;
      }  
   
}
