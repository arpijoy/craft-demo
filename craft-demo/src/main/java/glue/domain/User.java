package glue.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author arpitachattopadhyay
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  
	  @NotNull
	  private String email;
	  
	  @NotNull
	  private String name;
	  
	  @NotNull
	  private String status;

	 
	  public User() { }

	  public User(long id) { 
	    this.id = id;
	  }

	  public User(String email, String name, String status) {
	    this.email = email;
	    this.name = name;
	    this.status = status;
	  }

	  public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	  
	  @Override
	   public String toString() {
	        return "UserDTO{" +
	                "name='" + name + '\'' +
	                ", email=" + email +
	                '}';
	    }
}

