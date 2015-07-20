package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Department;
public class DepartmentController {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	EntityManager em = emfactory.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    public ObservableList<Department> getAllDepartments() {
    	TypedQuery<Department> query =
      		  em.createQuery("SELECT e FROM Department e", Department.class);
    	List<Department> bList = query.getResultList();
    	ObservableList<Department> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    public Department createDepartment(String name, String departmentchiefid) {
    	Department e = new Department();
    	// id is automatically set
    	e.setName(name);
    	e.setDepartmentchiefid(departmentchiefid);
    	    	
    	tx.begin( );
        em.persist( e );
        tx.commit();
		return e;
    }
    
    public boolean deleteDepartment(int id) {
    	Department b = em.find(Department.class, id);
    	if( b != null ) {
    		tx.begin();
    		em.remove(b);
    		tx.commit();
    		return true;
		}
    	System.out.println("Error: no entity with this id found.");
    	return false;
    }

}
