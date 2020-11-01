package eu.crystalsystem.ugalmap.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * <p>Java class for Value complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Value">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valueId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="valueContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valueType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@javax.persistence.Entity
@Table(name = "value")
public class Value extends LabelValueType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
    protected int valueId;

	@Column(name = "value_content")
    protected String valueContent;

	@Column(name = "value_type")
    protected String valueType;

	public Value(String valueContent, String valueType) {
		this.valueContent = valueContent;
		this.valueType = valueType;
	}

	public Value() {

	}

	/**
	 * Gets the value of the valueId property.
	 * 
	 */
    public int getValueId() {
        return valueId;
    }

    /**
     * Sets the value of the valueId property.
     * 
     */
    public void setValueId(int value) {
        this.valueId = value;
    }

    /**
     * Gets the value of the valueContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueContent() {
        return valueContent;
    }

    /**
     * Sets the value of the valueContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueContent(String value) {
        this.valueContent = value;
    }

    /**
     * Gets the value of the valueType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * Sets the value of the valueType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueType(String value) {
        this.valueType = value;
    }

}
