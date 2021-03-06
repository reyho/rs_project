package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Building;

public class BuildingsController {
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	private EntityManager em = emfactory.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public static void main(String[] args) {
    	BuildingsController bc = new BuildingsController();
		 bc.createBuilding("TT_build_02");
	}
    
    public Building createBuilding(String n) {
    	Building b = new Building();
    	// id is automatically set
    	b.setName(n);
    	
    	tx.begin( );
        em.persist( b );
        tx.commit();
		return b;
    }
    
    public Building findBuildingByName(String bName) {
    	TypedQuery<Building> query =
      		  em.createQuery("SELECT e FROM buildings e WHERE e.Name = :name", Building.class);
    	query.setParameter("name", bName); 
    	Building b = query.getSingleResult();
    	return b;
    }
    
    public ObservableList<Building> findBuildingAll() {
    	TypedQuery<Building> query =
      		  em.createQuery("SELECT e FROM Building e", Building.class);
    	List<Building> bList = query.getResultList();
    	ObservableList<Building> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    // find building by id, and delete if found - boolean indicates success of the operation
    public boolean deleteBuilding(int id) {
    	Building b = em.find(Building.class, id);
    	if( b != null ) {
    		tx.begin();
    		em.remove(b);
    		tx.commit();
    		return true;
		}
    	return false;
    }
    
}
