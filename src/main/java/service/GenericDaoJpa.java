package service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public class GenericDaoJpa<T> implements GenericDao<T> {
    //private static final String PU_NAME = "bierWinkels";
	//private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU_NAME);
	//protected static final EntityManager em = emf.createEntityManager();
	
    private final Class<T> type;
    protected EntityManager em;
    
    public GenericDaoJpa(Class<T> type) {
    	super();
        this.type = type;
    }
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
    	this.em = em;
    }
    
    @Transactional(readOnly = true)
    public T get(Long id) {
    	T entity = this.em.find(this.type, id);
    	return entity;
    }
    
    @Transactional(readOnly = true)
    public List<T> findAll() {
    	return this.em.createQuery(
    			"select entity from " + this.type.getName() + " entity").getResultList();
    }
    
    @Override
    public void insert(T object) {
    	em.persist(object);
    }
    
    @Override
    public void delete(T object) {
    	em.remove(em.merge(object));
    }
    
    @Transactional(readOnly = true)
    @Override
    public boolean exists(Long id) {
    	T entity = this.em.find(this.type, id);
    	return entity != null;
    }
    
    @Override
    public T update(T object) {
    	return em.merge(object);
    }


    
//    public static void closePersistency() {
//        em.close();
//        emf.close();
//    }
//    public static void startTransaction() {
//        em.getTransaction().begin();
//    }
//    public static void commitTransaction() {
//        em.getTransaction().commit();
//    }
//    public static void rollbackTransaction() {
//        em.getTransaction().rollback();
//    }

//    @Override
//    public List<T> findAll() {
//        //return em.createNamedQuery(type.getName()+".findAll", type).getResultList();
//        return em.createQuery("select entity from " + type.getName() + " entity", type).getResultList();
//    }
//
//    @Override
//    public <U> T get(U id) {
//        T entity = em.find(type, id);
//        return entity;
//    }
//
//    @Override
//    public T update(T object) {
//        return em.merge(object);
//    }
//
//    @Override
//    public void delete(T object) {
//        em.remove(em.merge(object));
//    }
//
//    @Override
//    public void insert(T object) {
//        em.persist(object);
//    }
//
//    @Override
//    public <U> boolean exists(U id) {
//        T entity = em.find(type, id);
//        return entity != null;
//    } 
    
}