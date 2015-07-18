package controllers;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import models.Student;

import models.Group;


public class StudentController {
	
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RS_Project" );
		EntityManager em = emfactory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
		/*Group rl = em.find(Group.class, 1);
	      List<Student> es = rl.getStudents();
	      for(Iterator<Student> it = es.iterator(); it.hasNext();) {
	          System.out.println(it.next());
	      }*/
	      
	      Student s = em.find(Student.class, 3);
	      List<Group> gr = s.getGroups();
	      for(Iterator<Group> it = gr.iterator(); it.hasNext();) {
	          System.out.println(it.next());
	      }

	}

}
