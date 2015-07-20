package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Group;
import models.Room;
import models.TimeSlot;

public class TimeSlotController {
	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	EntityManager em = emfactory.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    public ObservableList<Room> getAllRooms() {
    	TypedQuery<Room> query =
      		  em.createQuery("SELECT e FROM Room e", Room.class);
    	List<Room> bList = query.getResultList();
    	ObservableList<Room> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    public ObservableList<Group> getAllGroups() {
    	TypedQuery<Group> query =
      		  em.createQuery("SELECT e FROM Group e", Group.class);
    	List<Group> bList = query.getResultList();
    	ObservableList<Group> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    public ObservableList<TimeSlot> findTimeSlotsAll() {
    	TypedQuery<TimeSlot> query =
      		  em.createQuery("SELECT e FROM TimeSlot e", TimeSlot.class);
    	List<TimeSlot> bList = query.getResultList();
    	ObservableList<TimeSlot> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    public TimeSlot createTimeSlot(Float starttime, Float length, Byte day, Room room, Group group){
    	
    	TimeSlot e = new TimeSlot();
    	// id is automatically set
    	e.setStartTimeNice(starttime);
    	e.setLength(length);
    	e.setDay(day);
    	e.setRoom(room);
    	e.setGroup(group);
    	
    	tx.begin( );
        em.persist( e );
        tx.commit();
		return e;
    	
    }
    
    public boolean deleteTimeSlot(int id) {
    	TimeSlot b = em.find(TimeSlot.class, id);
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
