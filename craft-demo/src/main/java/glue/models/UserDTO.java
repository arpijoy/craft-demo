package glue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an UserDTO for this web application.
 */
@Entity
@Table(name = "users")
public class UserDTO implements java.io.Serializable{

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 8878592942951140294L;

@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @NotNull
  private String email;
  
  @NotNull
  private String name;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public UserDTO() { }

  public UserDTO(long id) { 
    this.id = id;
  }

  public UserDTO(String email, String name) {
    this.email = email;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }

  public String getEmail() {
    return email;
  }
  
  public void setEmail(String value) {
    this.email = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }
  
} // class UserDTO
