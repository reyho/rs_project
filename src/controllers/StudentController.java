package controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Student;
import models.Department;
import models.Semester;


public class StudentController {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
	EntityManager em = emfactory.createEntityManager();
    EntityTransaction tx = em.getTransaction();
	
    
    /*public static void main(String[] args) {
		
		/*Group rl = em.find(Group.class, 1);
	      List<Student> es = rl.getStudents();
	      for(Iterator<Student> it = es.iterator(); it.hasNext();) {
	          System.out.println(it.next());
	      }
	      
	      Student s = em.find(Student.class, 3);
	      List<Group> gr = s.getGroups();
	      for(Iterator<Group> it = gr.iterator(); it.hasNext();) {
	          System.out.println(it.next());
	      }*/

	
	public ObservableList<Department> getAllDepartments() {
    	TypedQuery<Department> query =
      		  em.createQuery("SELECT e FROM Department e", Department.class);
    	List<Department> bList = query.getResultList();
    	ObservableList<Department> b = FXCollections.observableArrayList(bList);
    	return b;
    }
	
	public ObservableList<Semester> getAllSemesters() {
    	TypedQuery<Semester> query =
      		  em.createQuery("SELECT e FROM Semester e", Semester.class);
    	List<Semester> bList = query.getResultList();
    	ObservableList<Semester> b = FXCollections.observableArrayList(bList);
    	return b;
    }
	
	public ObservableList<Student> getAllStudents() {
    	TypedQuery<Student> query =
      		  em.createQuery("SELECT e FROM Student e", Student.class);
    	List<Student> bList = query.getResultList();
    	ObservableList<Student> b = FXCollections.observableArrayList(bList);
    	return b;
    }
	
	public boolean deleteStudent(int id) {
    	Student b = em.find(Student.class, id);
    	if( b != null ) {
    		tx.begin();
    		em.remove(b);
    		tx.commit();
    		return true;
		}
    	System.out.println("Error: no entity with this id found.");
    	return false;
    }
	
	public Student createStudent(String name, String lastname, String studentid, Semester semester, Department department) {
		Student e = new Student();
    	// id is automatically set
    	e.setName(name);
    	e.setLastname(lastname);
    	e.setStudentid(studentid);
    	e.setSemester(semester);
    	e.setDepartment(department);
    	
    	
    	tx.begin( );
        em.persist( e );
        tx.commit();
		return e;
    }

}
