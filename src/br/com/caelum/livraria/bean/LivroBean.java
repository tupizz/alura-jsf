package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dao.LivroDAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	
	private Integer autorId;
	
	public List<Autor> getAutoresDoLivro(){
		return livro.getAutores();
	}
	
	public List<Livro> getLivros(){
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();	
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		this.livro.adicionaAutor(autor);
	}
	
	
	
	public RedirectView gravar() {
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um autor!"));
            return null;
        }
		
		if(this.livro.getId() != null) {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		} else {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		}
		
		this.livro = new Livro();
		
		return new RedirectView("livro");
	}
	
	public RedirectView excluir(Livro livro) {
		new DAO<Livro>(Livro.class).remove(livro);
		return new RedirectView("livro");
	}
	
	public void excluirAutorDoLivro(Autor autor) {
		this.livro.excluirAutorDaListaDeAutores(autor);
	}
	
	public void carregarFormulario(Livro livro) {
		this.livro = new LivroDAO().retornaLivroComAutor(livro);
	}
	
	public RedirectView formAutor() {
		return  new RedirectView("autor");
	}
	
	
	/* 
	 * Essa função recebe um contexto que permite obter informações da view processada no momento (FACESCONTEXT)
	 * 
	 * 	UIForm --> UIInput + UICommand (cria árvores de componente parecida com árvore DOM)
	 * 
	 * o componente da view que estão sendo validado 
	 * e um objeto que representa o valor digitado pelo usuário 
	 * 
	 * */
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
		}
	}

}
