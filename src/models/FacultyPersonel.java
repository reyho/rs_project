/*
 * Java bean class for entity table faculty_personel 
 * Created on 14 Jul 2015 ( Date ISO 2015-07-14 - Time 20:55:45 )
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
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Entity bean for table "faculty_personel"
 * 
 * @author Telosys Tools Generator
 *
 */
@Entity
@Table(name="faculty_personel")
public class FacultyPersonel implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer    id           ; // Primary Key

    private String     username     ;
    private String     password     ;
    private String     name         ;
    private String     lastname     ;
    private String     title        ;
    private Integer    departmentid ;
    private Integer    roleid       ;

    @ManyToOne
    @JoinColumn(name="RoleId")
    private Role role;
    
    @OneToMany(mappedBy="instructor")
    private List<Group> groups;
    
    @OneToMany(mappedBy="reservedBy")
    private List<Reservation> reservations;
    
    @ManyToMany
    @JoinTable(name="course_instructors",
    		joinColumns= @JoinColumn(name="InstructorId"),
    		inverseJoinColumns = @JoinColumn(name="CourseId")
    )
    private List<Course> courses;
    
    @ManyToOne
    @JoinColumn(name="DepartmentId")
    private Department departmnet;
    
    
    /**
     * Default constructor
     */
    public FacultyPersonel()
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
    //--- DATABASE MAPPING : Username ( VARCHAR ) 
    /**
     * Set the "username" field value
     * This field is mapped on the database column "Username" ( type "VARCHAR", NotNull : true ) 
     * @param username
     */
    public void setUsername( String username )
    {
        this.username = username;
    }
    /**
     * Get the "username" field value
     * This field is mapped on the database column "Username" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getUsername()
    {
        return this.username;
    }

    //--- DATABASE MAPPING : Password ( VARCHAR ) 
    /**
     * Set the "password" field value
     * This field is mapped on the database column "Password" ( type "VARCHAR", NotNull : true ) 
     * @param password
     */
    public void setPassword( String password )
    {
        this.password = password;
    }
    /**
     * Get the "password" field value
     * This field is mapped on the database column "Password" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getPassword()
    {
        return this.password;
    }

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

    //--- DATABASE MAPPING : Title ( VARCHAR ) 
    /**
     * Set the "title" field value
     * This field is mapped on the database column "Title" ( type "VARCHAR", NotNull : false ) 
     * @param title
     */
    public void setTitle( String title )
    {
        this.title = title;
    }
    /**
     * Get the "title" field value
     * This field is mapped on the database column "Title" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getTitle()
    {
        return this.title;
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

    //--- DATABASE MAPPING : RoleId ( INT ) 
    /**
     * Set the "roleid" field value
     * This field is mapped on the database column "RoleId" ( type "INT", NotNull : true ) 
     * @param roleid
     */
    public void setRoleid( Integer roleid )
    {
        this.roleid = roleid;
    }
    /**
     * Get the "roleid" field value
     * This field is mapped on the database column "RoleId" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getRoleid()
    {
        return this.roleid;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(username);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(lastname);
        sb.append("|");
        sb.append(title);
        sb.append("|");
        sb.append(departmentid);
        sb.append("|");
        sb.append(roleid);
        return sb.toString(); 
    }

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Department getDepartmnet() {
		return departmnet;
	}

	public void setDepartmnet(Department departmnet) {
		this.departmnet = departmnet;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	} 


}
