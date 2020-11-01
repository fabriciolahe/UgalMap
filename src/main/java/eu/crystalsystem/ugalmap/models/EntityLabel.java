package eu.crystalsystem.ugalmap.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;


/**
 * <p>Java class for EntityLabel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityLabel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityLabelId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="entityLabelType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="label" type="{http://eu.crystalsystem.ugalmap.model}Label"/>
 *         &lt;element name="value" type="{http://eu.crystalsystem.ugalmap.model}Value"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@javax.persistence.Entity
@Table(name = "entity_label")
public class EntityLabel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_label_id")
    protected int entityLabelId;

	/*
	 * @XmlElement(required = true)
	 * 
	 * @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
	 * 
	 * @JoinColumn(name = "entity_building_schedule_id") protected GenericEntityType
	 * genericEntityType;
	 */

	@XmlElement(required = true)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "building_id", nullable = true)
	protected Building building;

	@XmlElement(required = true)
	@ManyToOne(cascade =  CascadeType.MERGE)
	@JoinColumn(name = "entity_id")
	protected Entity entity;

	@XmlElement(required = true)
	@ManyToOne(cascade =  CascadeType.MERGE)
	@JoinColumn(name = "schedule_id")
	protected Schedule schedule;

	@Column(name = "entity_label_type")
    protected String entityLabelType;

	@ManyToOne(cascade =  CascadeType.MERGE)
	@JoinColumn(name = "label_label_id")
    protected Label label;

	@ManyToOne(cascade =  CascadeType.MERGE)
	@JoinColumn(name = "value_value_id")
    protected Value value;



	// public GenericEntityType getGenericEntityType() {
	// return genericEntityType;
	// }
	//
	// public void setGenericEntityType(GenericEntityType genericEntityType) {
	// this.genericEntityType = genericEntityType;
	// }

	/**
	 * Gets the value of the entityLabelId property.
	 * 
	 */
    public int getEntityLabelId() {
        return entityLabelId;
    }
    
    

    public EntityLabel() {
		super();
	}

	public EntityLabel(Building building, Entity entity, Schedule schedule, String entityLabelType, Label label,
			Value value) {
		super();
		this.building = building;
		this.entity = entity;
		this.schedule = schedule;
		this.entityLabelType = entityLabelType;
		this.label = label;
		this.value = value;
	}

	/**
     * Sets the value of the entityLabelId property.
     * 
     */
    public void setEntityLabelId(int value) {
        this.entityLabelId = value;
    }

    /**
     * Gets the value of the entityLabelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityLabelType() {
        return entityLabelType;
    }

    /**
     * Sets the value of the entityLabelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityLabelType(String value) {
        this.entityLabelType = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link Label }
     *     
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link Label }
     *     
     */
    public void setLabel(Label value) {
        this.label = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link Value }
     *     
     */
    public Value getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link Value }
     *     
     */
    public void setValue(Value value) {
        this.value = value;
    }

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

}
