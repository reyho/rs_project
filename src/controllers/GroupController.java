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
import models.Group;
import models.Department;
import models.FacultyPersonnel;
import models.Role;
import models.Semester;
import models.Student;

public class GroupController {
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	private EntityManager em = emfactory.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public static void main(String[] args) {
    	GroupController gc = new GroupController();
		//bc.createGroup("TT_group_03", 35, bc.em.find(Building.class, 101));
    	//System.out.println(bc.findGroupByName("TT_build_02"));
    	//bc.deleteGroup(bc.findGroupByName("TT_build_02").getId());
    	//System.out.println(bc.getAllBuildings().get(0));
	}
    
    
    
    public Group createGroup(String type, String name, FacultyPersonnel instructor, Course course) {
    	Group r = new Group();
    	// id is automatically set
    	r.setType(type);;
    	r.setName(name);
    	r.setInstructor(instructor);
    	r.setCourse(course);
    	
    	
    	
    	
    	tx.begin( );
        em.persist( r );
        tx.commit();
		return r;
    }
    
    public void setStudent(Group group,List<Student> student){
    	group.setStudents(student);
    	
    	tx.begin( );
        em.persist( group );
        tx.commit();
    }
    
    public Group findGroupByName(String name) {
    	TypedQuery<Group> query =
      		  em.createQuery("SELECT e FROM Group e WHERE e.name = :name", Group.class);
    	query.setParameter("name", name); 
    	Group b = query.getSingleResult();
    	return b;
    }
    
    public ObservableList<Group> findGroupAll() {
    	TypedQuery<Group> query =
      		  em.createQuery("SELECT e FROM Group e", Group.class);
    	List<Group> bList = query.getResultList();
    	ObservableList<Group> e = FXCollections.observableArrayList(bList);
    	return e;
    }
    
    public ObservableList<Student> findGroupStudents(Group group) {
    	TypedQuery<Student> query =
        		  em.createQuery("SELECT distinct e FROM Student e JOIN e.groups c WHERE c = :group", Student.class);
      	query.setParameter("group", group); 
    	List<Student> bList = query.getResultList();
      	ObservableList<Student> b = FXCollections.observableArrayList(bList);
      	return b;
    }
    
    // find group by id, and delete if found - boolean indicates success of the operation
    public boolean deleteGroup(int id) {
    	Group b = em.find(Group.class, id);
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
    
    public ObservableList<Course> getAllCourses() {
    	TypedQuery<Course> query =
      		  em.createQuery("SELECT e FROM Course e", Course.class);
    	List<Course> bList = query.getResultList();
    	ObservableList<Course> b = FXCollections.observableArrayList(bList);
    	return b;
    }
    
    public ObservableList<Student> getAllStudents() {
    	TypedQuery<Student> query =
      		  em.createQuery("SELECT e FROM Student e", Student.class);
    	List<Student> bList = query.getResultList();
    	ObservableList<Student> b = FXCollections.observableArrayList(bList);
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
