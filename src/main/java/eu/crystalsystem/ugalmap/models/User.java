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
 * <p>Java class for User complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="User">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userFirstname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userLastname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userActive" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="role" type="{http://eu.crystalsystem.ugalmap.model}Role"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@javax.persistence.Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    protected int userId;

	@Column(name = "user_firstname")
    protected String userFirstname;

	@Column(name = "user_lastname")
    protected String userLastname;

	@Column(name = "user_email")
    protected String userEmail;

	@Column(name = "user_passwd")
    protected String userPasswd;

	@Column(name = "user_active")
    protected String userActive;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.DETACH })
	@JoinColumn(name = "role_role_id")
    protected Role role;

	public User(String userFirstname, String userLastname, String userEmail, String userPasswd, String userActive,
			Role role) {
		super();
		this.userFirstname = userFirstname;
		this.userLastname = userLastname;
		this.userEmail = userEmail;
		this.userPasswd = userPasswd;
		this.userActive = userActive;
		this.role = role;
	}

	public User() {
	}

	/**
	 * Gets the value of the userId property.
	 * 
	 */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
    }

    /**
     * Gets the value of the userFirstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserFirstname() {
        return userFirstname;
    }

    /**
     * Sets the value of the userFirstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserFirstname(String value) {
        this.userFirstname = value;
    }

    /**
     * Gets the value of the userLastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserLastname() {
        return userLastname;
    }

    /**
     * Sets the value of the userLastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserLastname(String value) {
        this.userLastname = value;
    }

    /**
     * Gets the value of the userEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserEmail() {
        return userEmail;
    }

	/**
	 * Sets the value of the userEmail property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserEmail(String value) {
		this.userEmail = value;
	}

    /**
	 * Sets the value of the userPasswd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserPasswd(String value) {
		this.userPasswd = value;
	}

	/**
	 * Gets the value of the userPasswd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserPasswd() {
		return userPasswd;
	}

    /**
     * Gets the value of the userActive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserActive() {
        return userActive;
    }

    /**
     * Sets the value of the userActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserActive(String value) {
        this.userActive = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link Role }
     *     
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link Role }
     *     
     */
    public void setRole(Role value) {
        this.role = value;
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstname=" + userFirstname + ", userLastname=" + userLastname
				+ ", userEmail=" + userEmail + ", userPasswd=" + userPasswd + ", userActive=" + userActive + ", role="
				+ role + "]";
	}

}
