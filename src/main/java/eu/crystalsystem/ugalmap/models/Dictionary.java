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


/**
 * <p>Java class for Dictionary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dictionary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dictionaryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dictionaryTranslatedValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dictionaryLabelValueType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="language" type="{http://eu.crystalsystem.ugalmap.model}Language"/>
 *         &lt;element name="valueLabel" type="{http://eu.crystalsystem.ugalmap.model}Label"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@javax.persistence.Entity
@Table(name = "dictionary")
public class Dictionary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dictionary_id")
    protected int dictionaryId;

	@Column(name = "dictionary_translated_value")
    protected String dictionaryTranslatedValue;

	@Column(name = "dictionary_label_value_type ")
    protected String dictionaryLabelValueType;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	@JoinColumn(name = "language_language_id")
    protected Language language;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	@JoinColumn(name = "label_id")
	protected Label label;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	@JoinColumn(name = "value_id ")
	protected Value value;

	public Dictionary(String dictionaryTranslatedValue, String dictionaryLabelValueType, Language language, Label label,
			Value value) {
		super();
		this.dictionaryTranslatedValue = dictionaryTranslatedValue;
		this.dictionaryLabelValueType = dictionaryLabelValueType;
		this.language = language;
		this.label = label;
		this.value = value;
	}

	public Dictionary() {
	}

	/**
	 * Gets the value of the dictionaryId property.
	 * 
	 */
    public int getDictionaryId() {
        return dictionaryId;
    }

    /**
     * Sets the value of the dictionaryId property.
     * 
     */
    public void setDictionaryId(int value) {
        this.dictionaryId = value;
    }

    /**
     * Gets the value of the dictionaryTranslatedValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDictionaryTranslatedValue() {
        return dictionaryTranslatedValue;
    }

    /**
     * Sets the value of the dictionaryTranslatedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDictionaryTranslatedValue(String value) {
        this.dictionaryTranslatedValue = value;
    }

    /**
     * Gets the value of the dictionaryLabelValueType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDictionaryLabelValueType() {
        return dictionaryLabelValueType;
    }

    /**
     * Sets the value of the dictionaryLabelValueType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDictionaryLabelValueType(String value) {
        this.dictionaryLabelValueType = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setLanguage(Language value) {
        this.language = value;
    }

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}


}
