package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	
	private Integer autorId;
	
	public void carregarAutorPelaId(){
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public RedirectView excluir(Autor autor){
		new DAO<Autor>(Autor.class).remove(autor);
		return new RedirectView("autor");
	}
	
	public void carregarFormulario(Autor autor){
		this.autor = autor;
	}

	public RedirectView gravar() {
		
		if(this.autor.getId() != null){
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}else{
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		}
		
		this.autor = new Autor();
		
		return new RedirectView("livro");
	}
	
	/* --------------- GGAS --------------- */
	
	public Autor getAutor() {
		return autor;
	}
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
}
