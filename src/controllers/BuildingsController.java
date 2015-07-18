package controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
    	b.setName(n);
    	
    	tx.begin( );
        em.persist( b );
        tx.commit();
		return null;
    	
    }
}
