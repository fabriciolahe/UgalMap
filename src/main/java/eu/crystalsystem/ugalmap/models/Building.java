package eu.crystalsystem.ugalmap.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;


/**
 * <p>Java class for Building complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Building">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="buildingId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@javax.persistence.Entity
@Table(name = "building")
public class Building {
	@XmlElement(required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "building_id")
	protected int buildingId;


    /**
     * Gets the value of the buildingId property.
     * 
     */
    public int getBuildingId() {
        return buildingId;
    }

    /**
     * Sets the value of the buildingId property.
     * 
     */
    public void setBuildingId(int value) {
        this.buildingId = value;
    }
}
