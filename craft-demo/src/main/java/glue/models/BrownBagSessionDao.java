package glue.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the BrownBagSessionDTO entity.
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
public class BrownBagSessionDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the BrownBagSession in the database.
   */
  public void create(BrownBagSessionDTO bbsession) {
    entityManager.persist(bbsession);
    return;
  }
  
  /**
   * Delete the BrownBagSession from the database.
   */
  public void delete(BrownBagSessionDTO bbsession) {
    if (entityManager.contains(bbsession))
      entityManager.remove(bbsession);
    else
      entityManager.remove(entityManager.merge(bbsession));
    return;
  }
  
  /**
   * Return all the BrownBagSession stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<BrownBagSessionDTO> getAll() {
    return entityManager.createQuery("from BrownBagSessionDTO").getResultList();
  }
  
  

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;

  
} // class end
