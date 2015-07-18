/*
 * Java bean class for entity table time_slots 
 * Created on 14 Jul 2015 ( Date ISO 2015-07-14 - Time 20:55:48 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Entity bean for table "time_slots"
 * 
 * @author Telosys Tools Generator
 *
 */
@Entity
@Table(name="time_slots")
public class TimeSlot implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer    id           ; // Primary Key

    private Float      starttime    ;
    private Float      length       ;
    private Byte       day          ;
    private Integer    roomid       ;
    private Integer    groupid      ;

    @ManyToOne
    @JoinColumn(name="RoomId")
    private Room room;
    
    @OneToOne
    @JoinColumn(name="GroupId")
    private Group group;
    
    /**
     * Default constructor
     */
    public TimeSlot()
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
    //--- DATABASE MAPPING : StartTime ( FLOAT ) 
    /**
     * Set the "starttime" field value
     * This field is mapped on the database column "StartTime" ( type "FLOAT", NotNull : true ) 
     * @param starttime
     */
    public void setStarttime( Float starttime )
    {
        this.starttime = starttime;
    }
    /**
     * Get the "starttime" field value
     * This field is mapped on the database column "StartTime" ( type "FLOAT", NotNull : true ) 
     * @return the field value
     */
    public Float getStarttime()
    {
        return this.starttime;
    }

    //--- DATABASE MAPPING : Length ( FLOAT ) 
    /**
     * Set the "length" field value
     * This field is mapped on the database column "Length" ( type "FLOAT", NotNull : true ) 
     * @param length
     */
    public void setLength( Float length )
    {
        this.length = length;
    }
    /**
     * Get the "length" field value
     * This field is mapped on the database column "Length" ( type "FLOAT", NotNull : true ) 
     * @return the field value
     */
    public Float getLength()
    {
        return this.length;
    }

    //--- DATABASE MAPPING : Day ( TINYINT ) 
    /**
     * Set the "day" field value
     * This field is mapped on the database column "Day" ( type "TINYINT", NotNull : true ) 
     * @param day
     */
    public void setDay( Byte day )
    {
        this.day = day;
    }
    /**
     * Get the "day" field value
     * This field is mapped on the database column "Day" ( type "TINYINT", NotNull : true ) 
     * @return the field value
     */
    public Byte getDay()
    {
        return this.day;
    }

    //--- DATABASE MAPPING : RoomId ( INT ) 
    /**
     * Set the "roomid" field value
     * This field is mapped on the database column "RoomId" ( type "INT", NotNull : true ) 
     * @param roomid
     */
    public void setRoomid( Integer roomid )
    {
        this.roomid = roomid;
    }
    /**
     * Get the "roomid" field value
     * This field is mapped on the database column "RoomId" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getRoomid()
    {
        return this.roomid;
    }

    //--- DATABASE MAPPING : GroupId ( INT ) 
    /**
     * Set the "groupid" field value
     * This field is mapped on the database column "GroupId" ( type "INT", NotNull : true ) 
     * @param groupid
     */
    public void setGroupid( Integer groupid )
    {
        this.groupid = groupid;
    }
    /**
     * Get the "groupid" field value
     * This field is mapped on the database column "GroupId" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getGroupid()
    {
        return this.groupid;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(starttime);
        sb.append("|");
        sb.append(length);
        sb.append("|");
        sb.append(day);
        sb.append("|");
        sb.append(roomid);
        sb.append("|");
        sb.append(groupid);
        return sb.toString(); 
    }

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	} 


}
