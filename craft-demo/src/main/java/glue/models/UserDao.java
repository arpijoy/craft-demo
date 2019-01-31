package glue.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the UserDTO entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class UserDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the user in the database.
   */
  public void create(UserDTO user) {
    entityManager.persist(user);
    return;
  }
  
  /**
   * Delete the user from the database.
   */
  public void delete(UserDTO user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<UserDTO> getAll() {
    return entityManager.createQuery("from UserDTO").getResultList();
  }
  
  /**
   * Return the user having the passed email.
   */
  public UserDTO getByEmail(String email) {
    return (UserDTO) entityManager.createQuery(
        "from UserDTO where email = :email")
        .setParameter("email", email)
        .getSingleResult();
  }

  /**
   * Return the user having the passed id.
   */
  public UserDTO getById(long id) {
    return entityManager.find(UserDTO.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(UserDTO user) {
    entityManager.merge(user);
    return;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;

  
} // class UserDao
