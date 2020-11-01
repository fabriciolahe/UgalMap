package eu.crystalsystem.ugalmap.models;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * <p>Java class for Schedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Schedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="scheduleId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="scheduleTimeStart" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="scheduleTimeEnd" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="entity" type="{http://eu.crystalsystem.ugalmap.model}Entity"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@javax.persistence.Entity
@Table(name = "schedule")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
    protected int scheduleId;

	@Column(name = "schedule_time_start")
	protected Time scheduleTimeStart;

	@Column(name = "schedule_time_end")
	protected Time scheduleTimeEnd;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "entity_entity_id")
    protected Entity entity;

	public Schedule(Time scheduleTimeStart, Time scheduleTimeEnd, Entity entity) {

		this.scheduleTimeStart = scheduleTimeStart;
		this.scheduleTimeEnd = scheduleTimeEnd;
		this.entity = entity;
	}

	public Schedule() {
	}

	/**
	 * Gets the value of the scheduleId property.
	 * 
	 */
    public int getScheduleId() {
        return scheduleId;
    }

    /**
     * Sets the value of the scheduleId property.
     * 
     */
    public void setScheduleId(int value) {
        this.scheduleId = value;
    }

    /**
	 * Gets the value of the scheduleTimeStart property.
	 * 
	 * @return possible object is {@link Time }
	 * 
	 */
	public Time getScheduleTimeStart() {
        return scheduleTimeStart;
    }

    /**
	 * Sets the value of the scheduleTimeStart property.
	 * 
	 * @param value
	 *            allowed object is {@link Time }
	 * 
	 */
	public void setScheduleTimeStart(Time value) {
        this.scheduleTimeStart = value;
    }

    /**
	 * Gets the value of the scheduleTimeEnd property.
	 * 
	 * @return possible object is {@link Time }
	 * 
	 */
	public Time getScheduleTimeEnd() {
        return scheduleTimeEnd;
    }

    /**
	 * Sets the value of the scheduleTimeEnd property.
	 * 
	 * @param value
	 *            allowed object is {@link Time }
	 * 
	 */
	public void setScheduleTimeEnd(Time value) {
        this.scheduleTimeEnd = value;
    }

    /**
     * Gets the value of the entity property.
     * 
     * @return
     *     possible object is
     *     {@link Entity }
     *     
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Sets the value of the entity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entity }
     *     
     */
    public void setEntity(Entity value) {
        this.entity = value;
    }

}
