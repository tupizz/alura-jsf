package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	private Usuario usuario = new Usuario();
	
	public String efetuaLogin(){
		FacesContext context = FacesContext.getCurrentInstance();

		if(new UsuarioDAO().usuarioExiste(usuario)){
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}else{
			/*CONFIGURA SETTANDO QUE AS MENSAGENS DE VALIDAÇÃO SERÃO MANTIDAS POR 2 REQUISIÇÕES (FLASH) */
			context.getExternalContext().getFlash().setKeepMessages(true);			
			context.addMessage(null, new FacesMessage("Usuário não encontrado"));
			
			return "login?faces-redirect=true";
		}
	}
	
	public RedirectView deslogar(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return new RedirectView("login");
	}

	
	/* ---------------- GETTERS AND SETTERS ----------------  */
	
	public Usuario getUsuario() {
		return usuario;
	}

}
