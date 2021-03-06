/*
 * Java bean class for entity table roles 
 * Created on 14 Jul 2015 ( Date ISO 2015-07-14 - Time 20:55:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entity bean for table "roles"
 * 
 * @author Telosys Tools Generator
 *
 */
@Entity
@Table(name="roles")
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private Integer    id           ; // Primary Key

    private String     name         ;
    private Integer    privilege    ;

    /**
     * Default constructor
     */
    public Role()
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

    //--- DATABASE MAPPING : Privilege ( INT ) 
    /**
     * Set the "privilege" field value
     * This field is mapped on the database column "Privilege" ( type "INT", NotNull : true ) 
     * @param privilege
     */
    public void setPrivilege( Integer privilege )
    {
        this.privilege = privilege;
    }
    /**
     * Get the "privilege" field value
     * This field is mapped on the database column "Privilege" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getPrivilege()
    {
        return this.privilege;
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
