package glue.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "chatsession")
public class MessageDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String message;
	
	@NotNull
	private String toUser;
	@NotNull
	private String fromUser;
   
	public MessageDTO(){}
	
	public MessageDTO(long id) { 
	    this.id = id;
	  }

	public MessageDTO(String message, String toUser, String fromUser) {
		super();
		this.message = message;
		this.toUser = toUser;
		this.fromUser = fromUser;
		
	}

	public MessageDTO(String message, String toUser) {
		super();
		this.message = message;
		this.toUser = toUser;
		this.fromUser = toUser;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	@Override
	public String toString() {
		return fromUser+ "Says: " + message+"\n";
	}

	

}