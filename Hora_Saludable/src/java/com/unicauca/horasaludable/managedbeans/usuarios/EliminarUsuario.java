/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.entities.Usuariogrupo;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuariogrupoFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class EliminarUsuario implements Serializable
{
    private Usuario usuarioSeleccionado;
    private MostrarUsuariosController managebUsuarios;
    @EJB
    private UsuarioFacade usuarioEJB; 
    @EJB
    private UsuariogrupoFacade usuarioGrupoEJB;
    
    public EliminarUsuario() 
    {
    }
    
    public void ventanaEliminarUsuario(Usuario usuario, MostrarUsuariosController mgb)
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.usuarioSeleccionado=usuario;
        this.managebUsuarios=mgb;
        requestContext.execute("PF('eliminarUsuario').show()");
    }
    
    public void eliminarUsuario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.usuarioSeleccionado!=null)
        {
            if(this.usuarioSeleccionado.getMedidaList().isEmpty() && this.usuarioSeleccionado.getDetalleasistenciaList().isEmpty() && this.usuarioSeleccionado.getDetalleinscripcionList().isEmpty())
            {
                Usuariogrupo usuarioGrupo=this.usuarioGrupoEJB.buscarPorNombreUsuario(this.usuarioSeleccionado.getUsunombreusuario()).get(0);
                this.usuarioGrupoEJB.remove(usuarioGrupo);
                this.usuarioEJB.remove(this.usuarioSeleccionado);
                this.managebUsuarios.getListaUsuarios().remove(this.usuarioSeleccionado);
                requestContext.update("tablasUsuarios");
                requestContext.execute("PF('eliminarUsuario').hide()");  
                requestContext.execute("PF('eliminacionCorrecta').show()");
            }
            else
            {
                requestContext.execute("PF('eliminarUsuario').hide()");
                requestContext.execute("PF('noSePuedeEliminar').show()");
            }
        }
    }
    
    
}
