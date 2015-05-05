/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class perfilUsuarioController implements Serializable
{
    @EJB
    private UsuarioFacade usuarioEJB;
    private String rutaFoto;
    private Usuario usuario;
    private SimpleDateFormat sdf;
    private String sexo;
    private String tipo;
    private boolean estudiante;    
    private boolean funcionario;
    private boolean familiar;
    
    
    public perfilUsuarioController() 
    {
        rutaFoto="img/fotosUploads";
        this.sdf=new SimpleDateFormat("yyyy-MM-dd");
    }
    
    @PostConstruct
    private void init()
    {
        Long idusuario=Long.parseLong("20141227");
        this.usuario=this.usuarioEJB.buscarPorIdUsuario(idusuario).get(0);
        this.definirSexo();
        this.definirTipo();
        
    }
    
    public boolean isEstudiante() 
    {
        return estudiante;
    }

    public void setEstudiante(boolean estudiante) 
    {
        this.estudiante = estudiante;
    }

    public boolean isFuncionario() 
    {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario)
    {
        this.funcionario = funcionario;
    }

    public boolean isFamiliar() 
    {
        return familiar;
    }

    public void setFamiliar(boolean familiar) 
    {
        this.familiar = familiar;
    }
    
    public String getTipo() 
    {
        return tipo;
    }

    public void setTipo(String tipo) 
    {
        this.tipo = tipo;
    }
    
    public String getSexo()
    {
        return sexo;
    }

    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }
    
    public SimpleDateFormat getSdf() 
    {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) 
    {
        this.sdf = sdf;
    }
    
    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }
    
    public String getRutaFoto() 
    {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) 
    {
        this.rutaFoto = rutaFoto;
    }
    
    private void definirSexo()
    {
        if(this.usuario.getUsugenero().equals("M"))
        {
            this.sexo="Masculino";
        }
        else
        {
            this.sexo="Femenino";
        }
    }
    
    private void definirTipo()
    {
        this.estudiante=false;
        this.familiar=false;
        this.funcionario=false;
        if(this.usuario.getUniid()!=null)
        {
            if(this.usuario.getCarid()!=null)
            {
                this.tipo="Funcionario";
                this.funcionario=true;
            }
            else
            {
                this.tipo="Estudiante";
                this.estudiante=true;
            }
        }
        else
        {
            if(this.usuario.getConyugeid()!=null)
            {
                this.tipo="Familiar";
                this.familiar=true;
            }
        }
    }
    
}

