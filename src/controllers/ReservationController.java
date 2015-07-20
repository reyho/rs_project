package controllers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Department;
import models.FacultyPersonnel;
import models.Reservation;
import models.Room;
import models.Student;

public class ReservationController {
	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	EntityManager em = emfactory.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    public ObservableList<FacultyPersonnel> getAllPersonnel() {
    	TypedQuery<FacultyPersonnel> query =
      		  em.createQuery("SELECT e FROM FacultyPersonnel e", FacultyPersonnel.class);
    	List<FacultyPersonnel> bList = query.getResultList();
    	ObservableList<FacultyPersonnel> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    public ObservableList<Room> getAllRooms() {
    	TypedQuery<Room> query =
      		  em.createQuery("SELECT e FROM Room e", Room.class);
    	List<Room> bList = query.getResultList();
    	ObservableList<Room> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    public ObservableList<Reservation> findAllReservations() {
    	TypedQuery<Reservation> query =
      		  em.createQuery("SELECT e FROM Reservation e", Reservation.class);
    	List<Reservation> bList = query.getResultList();
    	ObservableList<Reservation> b = FXCollections.observableArrayList(bList);
    	return b;
    }

	public Reservation createReservation(String type, Float starttime, Float endtime, Date begindatetime, 
			FacultyPersonnel reservedBy, Room room) {
		
		Reservation e = new Reservation();
    	// id is automatically set
    	e.setType(type);
    	e.setStarttime(starttime);
    	e.setEndtime(endtime);
    	e.setBegindatetime(begindatetime);
    	e.setReservedBy(reservedBy);
    	e.setRoom(room);
    	
    	
    	tx.begin( );
        em.persist( e );
        tx.commit();
		return e;
	}

	public boolean deleteReservation(Integer id) {
		
		Reservation b = em.find(Reservation.class, id);
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
