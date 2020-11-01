package eu.crystalsystem.ugalmap.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * <p>Java class for Label complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Label">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="labelId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="labelName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@javax.persistence.Entity
@Table(name = "label")
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "label_id")
    protected int labelId;

	@Column(name = "label_name")
	protected String labelName;

	/**
	 * Gets the value of the labelId property.
	 * 
	 */
    public int getLabelId() {
        return labelId;
    }

    /**
     * Sets the value of the labelId property.
     * 
     */
    public void setLabelId(int value) {
        this.labelId = value;
    }

    /**
     * Gets the value of the labelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * Sets the value of the labelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelName(String value) {
        this.labelName = value;
    }

}
