/*
 * Java bean class for entity table semesters 
 * Created on 14 Jul 2015 ( Date ISO 2015-07-14 - Time 20:55:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entity bean for table "semesters"
 * 
 * @author Telosys Tools Generator
 *
 */
@Entity
@Table(name="semesters")
public class Semester implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private Integer    id           ; // Primary Key

    private String     name         ;

    /**
     * Default constructor
     */
    public Semester()
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
     * This field is mapped on the database column "Name" ( type "VARCHAR", NotNull : false ) 
     * @param name
     */
    public void setName( String name )
    {
        this.name = name;
    }
    /**
     * Get the "name" field value
     * This field is mapped on the database column "Name" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getName()
    {
        return this.name;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        
        sb.append(name);
        return sb.toString(); 
    } 


}
