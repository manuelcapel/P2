package modelo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BDUsuario {
private static final String PERSISTENCE_UNIT_NAME = "usuario";
private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	//Insertar un usuario en la Base de Datos; si se intenta introducir una dirección de correo que 
	//ya existe en la base de datos, no tiene efecto.
	public static void insertar(Usuario usuario) {
		if (!existeEmail(usuario.getEmail())) {
			EntityManager em = factoria.createEntityManager();
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
		}
	}
	//Actualizar los datos de un usuario en la base de datos
	public static void actualizar(Usuario usuario) {
		if (existeEmail(usuario.getEmail())) {
			EntityManager em = factoria.createEntityManager();
			Query q = em.createQuery(
					"SELECT u from Usuario u WHERE u.email = :emailUsuario");
			q.setParameter("emailUsuario", usuario.getEmail());
			Usuario anterior = (Usuario) q.getSingleResult();
			em.getTransaction().begin();
			anterior.setNombre(usuario.getNombre());
			anterior.setApellido(usuario.getApellido());
			em.getTransaction().commit();
			em.close();
		}
	}
	//Eliminar un usuario de la base de datos
	public static void eliminar(Usuario usuario) {
		if (existeEmail(usuario.getEmail())) {
			EntityManager em = factoria.createEntityManager();
			em.getTransaction().begin();
			Query q = em.createQuery(
					"SELECT u from Usuario u WHERE u.email = :emailUsuario");
			q.setParameter("emailUsuario", usuario.getEmail());
			Usuario eliminando = (Usuario) q.getSingleResult();
			em.remove(eliminando);
			em.getTransaction().commit();
			em.close();
		}
	}
	//Recuperar un usuario desde la base de datos 
	public static Usuario seleccionarUsuario(String email) {
		if (existeEmail(email)) {
			EntityManager em = factoria.createEntityManager();
			Query q = em.createQuery(
					"SELECT u from Usuario u WHERE u.email = :emailUsuario");
			q.setParameter("emailUsuario", email);
			Usuario usuario = (Usuario) q.getSingleResult();
			em.close();
			return usuario;
		}
		return null;
	}
	//Comprobar que existe el usuario cuyo email pasamos como argumento
	public static boolean existeEmail(String email) {
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery(
				"SELECT u from Usuario u WHERE u.email = :emailUsuario");
		q.setParameter("emailUsuario", email);
		try {
			q.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		} finally {
			em.close();
		}
	}
	//Listar los usuarios de la base de datos
	public static List<Usuario> listarUsuarios() {
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT u from Usuario u");
		@SuppressWarnings("unchecked")
		List<Usuario> lista = q.getResultList();
		em.close();
		return lista;
	}
}
