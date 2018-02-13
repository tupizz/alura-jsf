package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDAO {
	
	public boolean usuarioExiste(Usuario usuario){
		EntityManager em = new JPAUtil().getEntityManager();
		
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u "
									+ "WHERE u.email = :pEmail AND u.senha = :pSenha", Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try{
			Usuario user = query.getSingleResult();
		}catch (NoResultException e) {
			return false;
		}
		
		em.close();
		
		
		return true;
		
	}
}
