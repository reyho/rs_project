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
import models.Room;

public class RoomController {
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	private EntityManager em = emfactory.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public Room createRoom(String name, int capacity, Building building) {
    	Room r = new Room();
    	// id is automatically set
    	r.setName(name);
    	r.setCapacity(capacity);
    	r.setBuilding(building);
    	
    	tx.begin( );
        em.persist( r );
        tx.commit();
		return r;
    }
    
    public Room findRoomByName(String name) {
    	TypedQuery<Room> query =
      		  em.createQuery("SELECT e FROM Room e WHERE e.name = :name", Room.class);
    	query.setParameter("name", name); 
    	Room b = query.getSingleResult();
    	return b;
    }
    
    public ObservableList<Room> findRoomAll() {
    	TypedQuery<Room> query =
      		  em.createQuery("SELECT e FROM Room e", Room.class);
    	List<Room> bList = query.getResultList();
    	ObservableList<Room> e = FXCollections.observableArrayList(bList);
    	return e;
    }
    
    // find room by id, and delete if found - boolean indicates success of the operation
    public boolean deleteRoom(int id) {
    	Room b = em.find(Room.class, id);
    	if( b != null ) {
    		tx.begin();
    		em.remove(b);
    		tx.commit();
    		return true;
		}
    	return false;
    }
    
    public ObservableList<Building> getAllBuildings() {
    	TypedQuery<Building> query =
      		  em.createQuery("SELECT e FROM Building e", Building.class);
    	List<Building> bList = query.getResultList();
    	ObservableList<Building> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
}
