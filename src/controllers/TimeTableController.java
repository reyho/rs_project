package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
}
