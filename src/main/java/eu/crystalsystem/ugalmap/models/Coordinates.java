package eu.crystalsystem.ugalmap.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * <p>Java class for Coordinates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Coordinates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coordinatesId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="coordinatesLatitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="coordinatesLongitute" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@javax.persistence.Entity
@Table(name = "coordinates")
public class Coordinates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coordinates_id")
    protected int coordinatesId;

	@Column(name = "coordinates_latitude")
    protected double coordinatesLatitude;

	@Column(name = "coordinates_longitute")
    protected double coordinatesLongitute;

    /**
     * Gets the value of the coordinatesId property.
     * 
     */
    public int getCoordinatesId() {
        return coordinatesId;
    }

    /**
     * Sets the value of the coordinatesId property.
     * 
     */
    public void setCoordinatesId(int value) {
        this.coordinatesId = value;
    }

    /**
     * Gets the value of the coordinatesLatitude property.
     * 
     */
    public double getCoordinatesLatitude() {
        return coordinatesLatitude;
    }

    /**
     * Sets the value of the coordinatesLatitude property.
     * 
     */
    public void setCoordinatesLatitude(double value) {
        this.coordinatesLatitude = value;
    }

    /**
     * Gets the value of the coordinatesLongitute property.
     * 
     */
    public double getCoordinatesLongitute() {
        return coordinatesLongitute;
    }

    /**
     * Sets the value of the coordinatesLongitute property.
     * 
     */
    public void setCoordinatesLongitute(double value) {
        this.coordinatesLongitute = value;
    }

}
