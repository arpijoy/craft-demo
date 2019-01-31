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
@Table(name = "BrownBagSessions")
public class BrownBagSessionDTO {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	 
	  @NotNull
	  private String bbsname;
	  
	  @NotNull
	  private String hostname;
	  
	  @NotNull
	  private String email;
	  
	  @NotNull
	  private String schedule;

	  public BrownBagSessionDTO() { }

	  public BrownBagSessionDTO(long id) { 
	    this.id = id;
	  }

	  public BrownBagSessionDTO(String bbsname, String hostname, String email, String schedule) {
		super();
		this.bbsname = bbsname;
		this.hostname = hostname;
		this.email = email;
		this.schedule = schedule;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBbsname() {
		return bbsname;
	}

	public void setBbsname(String bbsname) {
		this.bbsname = bbsname;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "BrownBagSessionDTO [id=" + id + ", bbsname=" + bbsname + ", hostname=" + hostname + ", email=" + email
				+ ", schedule=" + schedule + "]";
	}


}
