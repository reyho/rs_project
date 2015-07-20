package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import models.FacultyPersonnel;
import models.Room;
import models.Semester;
import models.TimeSlot;

public class TimeTableController {
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	private EntityManager em = emfactory.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public static void main(String[] args) {
    	//CourseController cc = new CourseController();
		//bc.createCourse("TT_course_03", 35, bc.em.find(Building.class, 101));
    	//System.out.println(bc.findCourseByName("TT_build_02"));
    	//bc.deleteCourse(bc.findCourseByName("TT_build_02").getId());
    	//System.out.println(bc.getAllBuildings().get(0));
	}
	public ObservableList<TimeSlot> getAllDepartments() {
    	TypedQuery<TimeSlot> query =
      		  em.createQuery("SELECT e FROM TimeSlot e", TimeSlot.class);
    	List<TimeSlot> bList = query.getResultList();
    	ObservableList<TimeSlot> b = FXCollections.observableArrayList(bList);
    	return b;
    }
	
	public ObservableList<Room> getAllRooms(){
		TypedQuery<Room> query =
	      		  em.createQuery("SELECT e FROM Room e", Room.class);
	    	List<Room> bList = query.getResultList();
	    	ObservableList<Room> b = FXCollections.observableArrayList(bList);
	    	return b;
	}
	
	public ObservableList<Semester> getAllSemesters(){
		TypedQuery<Semester> query =
	      		  em.createQuery("SELECT e FROM Semester e", Semester.class);
	    	List<Semester> bList = query.getResultList();
	    	ObservableList<Semester> b = FXCollections.observableArrayList(bList);
	    	return b;
	}
	
	public ObservableList<FacultyPersonnel> getAllInstructors(){
		TypedQuery<FacultyPersonnel> query =
	      		  em.createQuery("SELECT e FROM FacultyPersonnel e", FacultyPersonnel.class);
	    	List<FacultyPersonnel> bList = query.getResultList();
	    	ObservableList<FacultyPersonnel> b = FXCollections.observableArrayList(bList);
	    	return b;
	}
	public ObservableList<TimeSlot> filterTimeSlots(Room r, Semester s, FacultyPersonnel p) {
		
		if(r == null && s == null && p == null) {
			System.out.println("filter empty");
			return null;
		} else {
			boolean needsAnd = false;
			String queryBuild = "SELECT e FROM TimeSlot e ";
			if(r != null) { queryBuild += "JOIN e.room cr "; }
			if(s != null) { queryBuild += "JOIN e.group.course.semester sem "; }
			if(p != null) { queryBuild += "JOIN e.group.instructor ins "; }
			
			queryBuild += "WHERE ";
			if(r != null) { 
				queryBuild += "cr = :r "; 
				needsAnd = true; 
			}
			
			if(s != null) { 
				queryBuild += (needsAnd) ? "AND " : "";
				queryBuild += "sem = :s ";
				needsAnd = true; 
			}
			
			if(p != null) { 
				queryBuild += (needsAnd) ? "AND " : "";
				queryBuild += "ins = :p";
			}
			
			TypedQuery<TimeSlot> query =
		      		  em.createQuery(queryBuild, TimeSlot.class);
			if(r != null) {query.setParameter("r", r); }
			if(s != null) {query.setParameter("s", s);}
			if(p != null) {query.setParameter("p", p);}
			List<TimeSlot> bList = query.getResultList();
			ObservableList<TimeSlot> b = FXCollections.observableArrayList(bList);
			return b;
		}
		
	}
}
