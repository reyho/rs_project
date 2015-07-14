/*
 * Java bean class for entity table rooms 
 * Created on 14 Jul 2015 ( Date ISO 2015-07-14 - Time 20:55:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entity bean for table "rooms"
 * 
 * @author Telosys Tools Generator
 *
 */
@Entity
@Table
public class Rooms implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer    id           ; // Primary Key

    private Integer    name         ;
    private Integer    capacity     ;
    private Integer    buildingid   ;

    /**
     * Default constructor
     */
    public Rooms()
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
    //--- DATABASE MAPPING : Name ( INT ) 
    /**
     * Set the "name" field value
     * This field is mapped on the database column "Name" ( type "INT", NotNull : true ) 
     * @param name
     */
    public void setName( Integer name )
    {
        this.name = name;
    }
    /**
     * Get the "name" field value
     * This field is mapped on the database column "Name" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getName()
    {
        return this.name;
    }

    //--- DATABASE MAPPING : Capacity ( INT ) 
    /**
     * Set the "capacity" field value
     * This field is mapped on the database column "Capacity" ( type "INT", NotNull : true ) 
     * @param capacity
     */
    public void setCapacity( Integer capacity )
    {
        this.capacity = capacity;
    }
    /**
     * Get the "capacity" field value
     * This field is mapped on the database column "Capacity" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getCapacity()
    {
        return this.capacity;
    }

    //--- DATABASE MAPPING : BuildingId ( INT ) 
    /**
     * Set the "buildingid" field value
     * This field is mapped on the database column "BuildingId" ( type "INT", NotNull : true ) 
     * @param buildingid
     */
    public void setBuildingid( Integer buildingid )
    {
        this.buildingid = buildingid;
    }
    /**
     * Get the "buildingid" field value
     * This field is mapped on the database column "BuildingId" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getBuildingid()
    {
        return this.buildingid;
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
        sb.append(capacity);
        sb.append("|");
        sb.append(buildingid);
        return sb.toString(); 
    } 


}
