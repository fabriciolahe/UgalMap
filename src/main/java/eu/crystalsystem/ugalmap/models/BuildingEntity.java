package eu.crystalsystem.ugalmap.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;


/**
 * <p>Java class for BuildingEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BuildingEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="buildingEntityId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="building" type="{http://eu.crystalsystem.ugalmap.model}Building"/>
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
@Table(name = "building_entity")
public class BuildingEntity {

	@XmlElement(required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "building_entity_id")
    protected int buildingEntityId;

    @XmlElement(required = true)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.DETACH })
	@JoinColumn(name = "building_building_id")
    protected Building building;

    @XmlElement(required = true)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "entity_entity_id")
    protected Entity entity;

    public BuildingEntity() {
		super();
	}

	public BuildingEntity(Building building, Entity entity) {
		super();
		this.building = building;
		this.entity = entity;
	}

	/**
     * Gets the value of the buildingEntityId property.
     * 
     */
    public int getBuildingEntityId() {
        return buildingEntityId;
    }

    /**
     * Sets the value of the buildingEntityId property.
     * 
     */
    public void setBuildingEntityId(int value) {
        this.buildingEntityId = value;
    }

    /**
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link Building }
     *     
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link Building }
     *     
     */
    public void setBuilding(Building value) {
        this.building = value;
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
