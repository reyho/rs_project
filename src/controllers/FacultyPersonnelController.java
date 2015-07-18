package controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.Role;
import models.Department;
import models.FacultyPersonnel;

public class FacultyPersonnelController {
	
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	private EntityManager em = emfactory.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public static void main(String[] args) {
    	//FacultyPersonnelController fc = new FacultyPersonnelController();
    	//FacultyPersonnel fm = fc.createFacultyMember("TT_prof", "123456", "TT_Prof_name", "TT_Prof_Lastname", "red.prof.", 2, 2);
    	//FacultyPersonnel fm = fc.findFacultyMemberByUsername("dean");
    	//System.out.println(fm);
    	//fc.deleteFacultyMember(2);
		 
	}
    
    public FacultyPersonnel createFacultyMember(String username, String pass, String firstName, String lastName, String title, int rId, int dId) {
    	FacultyPersonnel e = new FacultyPersonnel();
    	// id is automatically set
    	e.setUsername(username);
    	e.setPassword(pass);
    	e.setName(firstName);
    	e.setLastname(lastName);
    	e.setTitle(title);
    	if(rId != 1) {
    		//e.setRoleid(rId);
    		e.setRole(em.find(Role.class, rId));
    	} else {
    		//e.setRoleid(2);
    		e.setRole(em.find(Role.class, 2));
    	}
    	e.setDepartmnet(em.find(Department.class, dId));
    	
    	tx.begin( );
        em.persist( e );
        tx.commit();
		return e;
    }
    
    
    public FacultyPersonnel findFacultyMemberByUsername(String uName) {
    	TypedQuery<FacultyPersonnel> query =
      		  em.createQuery("SELECT e FROM FacultyPersonnel e WHERE e.username = :name", FacultyPersonnel.class);
    	query.setParameter("name", uName); 
    	try {
    		FacultyPersonnel fp = query.getSingleResult();
    		return fp;
    	}
    	catch (NoResultException nr) {
    		return null;
    	}
    	
    }
    
    
    // find building by id, and delete if found - boolean indicates success of the operation
    public boolean deleteFacultyMember(int id) {
    	FacultyPersonnel b = em.find(FacultyPersonnel.class, id);
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
