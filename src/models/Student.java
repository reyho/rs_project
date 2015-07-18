/*
 * Java bean class for entity table students 
 * Created on 14 Jul 2015 ( Date ISO 2015-07-14 - Time 20:55:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Entity bean for table "students"
 * 
 * @author Telosys Tools Generator
 *
 */
@Entity
@Table(name="students")
public class Student implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer    id           ; // Primary Key

    private String     name         ;
    private String     lastname     ;
    private String     studentid    ;
    private Integer    departmentid ;
    private Integer    semesterid   ;

    @ManyToOne
    @JoinColumn(name="SemesterId")
    private Semester semester;
    
    @ManyToOne
    @JoinColumn(name="DepartmentId")
    private Department department;
    
    @ManyToMany
    @JoinTable(name="students_groups",
    		joinColumns= @JoinColumn(name="StudentId"),
    		inverseJoinColumns = @JoinColumn(name="GroupId")
    )
    
    
    private List<Group> groups;
    /**
     * Default constructor
     */
    public Student()
    {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "id" field value
     * This field is mapped on the database column "Id" ( type "INT", NotNull : true ) 
     * @param id
     */
	public void setId( Integer id )
    {
        this.id = id ;
    }
    /**
     * Get the "id" field value
     * This field is mapped on the database column "Id" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getId()
    {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : Name ( VARCHAR ) 
    /**
     * Set the "name" field value
     * This field is mapped on the database column "Name" ( type "VARCHAR", NotNull : true ) 
     * @param name
     */
    public void setName( String name )
    {
        this.name = name;
    }
    /**
     * Get the "name" field value
     * This field is mapped on the database column "Name" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getName()
    {
        return this.name;
    }

    //--- DATABASE MAPPING : Lastname ( VARCHAR ) 
    /**
     * Set the "lastname" field value
     * This field is mapped on the database column "Lastname" ( type "VARCHAR", NotNull : true ) 
     * @param lastname
     */
    public void setLastname( String lastname )
    {
        this.lastname = lastname;
    }
    /**
     * Get the "lastname" field value
     * This field is mapped on the database column "Lastname" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getLastname()
    {
        return this.lastname;
    }

    //--- DATABASE MAPPING : StudentId ( VARCHAR ) 
    /**
     * Set the "studentid" field value
     * This field is mapped on the database column "StudentId" ( type "VARCHAR", NotNull : true ) 
     * @param studentid
     */
    public void setStudentid( String studentid )
    {
        this.studentid = studentid;
    }
    /**
     * Get the "studentid" field value
     * This field is mapped on the database column "StudentId" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getStudentid()
    {
        return this.studentid;
    }

    //--- DATABASE MAPPING : DepartmentId ( INT ) 
    /**
     * Set the "departmentid" field value
     * This field is mapped on the database column "DepartmentId" ( type "INT", NotNull : true ) 
     * @param departmentid
     */
    public void setDepartmentid( Integer departmentid )
    {
        this.departmentid = departmentid;
    }
    /**
     * Get the "departmentid" field value
     * This field is mapped on the database column "DepartmentId" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getDepartmentid()
    {
        return this.departmentid;
    }

    //--- DATABASE MAPPING : SemesterId ( INT ) 
    /**
     * Set the "semesterid" field value
     * This field is mapped on the database column "SemesterId" ( type "INT", NotNull : true ) 
     * @param semesterid
     */
    public void setSemesterid( Integer semesterid )
    {
        this.semesterid = semesterid;
    }
    /**
     * Get the "semesterid" field value
     * This field is mapped on the database column "SemesterId" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getSemesterid()
    {
        return this.semesterid;
    }

    public List<Group> getGroups()
    {
        return this.groups;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(lastname);
        sb.append("|");
        sb.append(studentid);
        sb.append("|");
        sb.append(departmentid);
        sb.append("|");
        sb.append(semesterid);
        return sb.toString(); 
    }

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	} 


}
