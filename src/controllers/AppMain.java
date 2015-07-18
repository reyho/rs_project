package controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import models.Building;

public class AppMain {
	public static void main(String[] args) {
		/*EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("RS_Project");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		

		Building b = new Building();
		b.setName("TT_build_06");
		//b.setId(2);

		tx.begin();
		em.persist(b);
		tx.commit();
		 
		 BuildingsController bc = new BuildingsController();
		 bc.createBuilding("TT_build_07");*/
	}
}
