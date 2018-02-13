package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext cxt = event.getFacesContext();
		
		String nomeDaPagina = cxt.getViewRoot().getViewId();
		
		System.out.println(nomeDaPagina);
		
		if("/login.xhtml".equals(nomeDaPagina)){
			return;
		}
		
		Usuario userLogado  = (Usuario) cxt.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(userLogado != null){
			return;
		}
		
		// FAZER UM REDIRECT PROGRAMATICAMENTE 
		NavigationHandler handler = cxt.getApplication().getNavigationHandler();
		handler.handleNavigation(cxt, null, "/login?faces-redirect=true");
		cxt.renderResponse();
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
