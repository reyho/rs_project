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
import models.Course;
import models.Department;
import models.FacultyPersonnel;
import models.Role;
import models.Semester;

public class CourseController {
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	private EntityManager em = emfactory.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public static void main(String[] args) {
    	CourseController cc = new CourseController();
		//bc.createCourse("TT_course_03", 35, bc.em.find(Building.class, 101));
    	//System.out.println(bc.findCourseByName("TT_build_02"));
    	//bc.deleteCourse(bc.findCourseByName("TT_build_02").getId());
    	//System.out.println(bc.getAllBuildings().get(0));
	}
    
    
    
    public Course createCourse(String name, Byte required, Byte nolectures, Byte noaudit, Byte nolab, Semester semester, Department department) {
    	Course r = new Course();
    	// id is automatically set
    	r.setName(name);
    	r.setRequired(required);
    	r.setNolectures(nolectures);
    	r.setNoaudit(noaudit);
    	r.setNolab(nolab);
    	r.setSemester(semester);
    	r.setDepartment(department);
    	
    	
    	tx.begin( );
        em.persist( r );
        tx.commit();
		return r;
    }
    
    public void setInstructor(Course course,List<FacultyPersonnel> instructors){
    	course.setInstructors(instructors);
    	
    	tx.begin( );
        em.persist( course );
        tx.commit();
    }
    
    public Course findCourseByName(String name) {
    	TypedQuery<Course> query =
      		  em.createQuery("SELECT e FROM Course e WHERE e.name = :name", Course.class);
    	query.setParameter("name", name); 
    	Course b = query.getSingleResult();
    	return b;
    }
    
    public ObservableList<Course> findCourseAll() {
    	TypedQuery<Course> query =
      		  em.createQuery("SELECT e FROM Course e", Course.class);
    	List<Course> bList = query.getResultList();
    	ObservableList<Course> e = FXCollections.observableArrayList(bList);
    	return e;
    }
    
    public ObservableList<FacultyPersonnel> findCourseInstructors(Course course) {
    	TypedQuery<FacultyPersonnel> query =
        		  em.createQuery("SELECT distinct e FROM FacultyPersonnel e JOIN e.courses c WHERE c = :course", FacultyPersonnel.class);
      	query.setParameter("course", course); 
    	List<FacultyPersonnel> bList = query.getResultList();
      	ObservableList<FacultyPersonnel> b = FXCollections.observableArrayList(bList);
      	return b;
    }
    
    // find course by id, and delete if found - boolean indicates success of the operation
    public boolean deleteCourse(int id) {
    	Course b = em.find(Course.class, id);
    	if( b != null ) {
    		tx.begin();
    		em.remove(b);
    		tx.commit();
    		return true;
		}
    	return false;
    }
    
    public ObservableList<FacultyPersonnel> getAllInstructors() {
    	TypedQuery<FacultyPersonnel> query =
      		  em.createQuery("SELECT e FROM FacultyPersonnel e", FacultyPersonnel.class);
    	List<FacultyPersonnel> bList = query.getResultList();
    	ObservableList<FacultyPersonnel> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    public ObservableList<Semester> getAllSemesters() {
    	TypedQuery<Semester> query =
      		  em.createQuery("SELECT e FROM Semester e", Semester.class);
    	List<Semester> bList = query.getResultList();
    	ObservableList<Semester> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    public ObservableList<Department> getAllDepartments() {
    	TypedQuery<Department> query =
      		  em.createQuery("SELECT e FROM Department e", Department.class);
    	List<Department> bList = query.getResultList();
    	ObservableList<Department> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
}
