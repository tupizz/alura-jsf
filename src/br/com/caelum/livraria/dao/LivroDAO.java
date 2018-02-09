package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.livraria.modelo.Livro;

public class LivroDAO {
	
	public Livro retornaLivroComAutor(Livro livro) {		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
											 
		Query qry = em.createQuery("SELECT DISTINCT l FROM Livro l JOIN FETCH l.autores WHERE l = :livro", Livro.class);
		livro = (Livro) qry.setParameter("livro",livro).getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return livro;
	}

}
