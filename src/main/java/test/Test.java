package test;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.Person;
import model.Role;
import model.User;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sesion1-jpa");
		EntityManager manager = factory.createEntityManager();
		
		try {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			
			User newUser = new User();
			//newUser.setIduser(0);
			newUser.setEmail("444 myEmail@dot.com");
			newUser.setPassword("44 Mi nuevo usuario");
			newUser.setAge(25);
			newUser.setName("Mi name 444");
			//newUser.setLastLogin(new java.sql.Date((new Date()).getTime()) );
			newUser.setLastlogin(new Date());
			newUser.setCreated(new Date());
			newUser.setFirstlogin(new Date());
			
			Person person = new Person();
			person.setIdperson(1);
			
			Role role = new Role();
			role.setIdrole(1);
			
			newUser.setPerson(person);
			newUser.setRole(role);
			
			Person np = new Person();
			np.setFistname("Persona 2");
			manager.persist(np);
			
			newUser.setPerson(np);
			manager.persist(newUser);
			System.out.println(newUser);
			
			
//			User u = manager.find(User.class, 3);
//			System.out.println(u.getName());
//			System.out.println(u.getPassword());
//			
//			u.setEmail("miEmail@nuevo.com");
//			manager.merge(u);
			

//			manager.remove(u);
			
			Query q = manager.createQuery(
				      "DELETE FROM User u WHERE u.iduser = :param");
			
			Query qRemove = manager.createNamedQuery("User.remove");
			
			int deletedCount = qRemove.setParameter("param", 6).executeUpdate();
			System.out.println("Elementos eliminados = " + deletedCount);
			
			
			
			transaction.commit();
			int id = newUser.getIduser();
			System.out.println("New ID=" + id);
			//manager.close();
			//manager.flush();
			Query query = manager.createNamedQuery("User.findAll");
			//query.setParameter(1, "Bob");
			//query.setParameter(2, "estrella");
			List<User> result = query.getResultList();
			for (User user : result) {
				System.out.println(user.getName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
