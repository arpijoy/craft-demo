package glue.controllers;



import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import glue.models.BrownBagSessionDTO;
import glue.models.BrownBagSessionDao;
import glue.models.MessageDTO;
import glue.models.MessageDao;
import glue.models.UserDTO;
import glue.models.UserDao;

/**
 * Class UserController
 */
@RestController
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

	
  /**
   * Create a new user
   */
  @RequestMapping(value="/create", method = RequestMethod.POST)
  @ResponseBody
  public String create(@RequestParam String name, @RequestParam String email){
    try {
    	System.out.println("create() :"+name+":"+email);
      UserDTO user = new UserDTO(email, name);
      userDao.create(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "UserDTO succesfully created!";
  }
  
  
 

  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      UserDTO user = new UserDTO(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "UserDTO succesfully deleted!";
  }
  
  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    try {
      UserDTO user = userDao.getByEmail(email);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "UserDTO not found: " + ex.toString();
    }
    return "The user id is: " + userId;
  }
  
  /**
   * Update the email and the name for the user indentified by the passed id.
   */
  @RequestMapping(value="/update")
  @ResponseBody
  public String updateName(long id, String email, String name) {
    try {
      UserDTO user = userDao.getById(id);
      user.setEmail(email);
      user.setName(name);
      userDao.update(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "UserDTO succesfully updated!";
  } 
  
  
  /**
   * Create a new user
   */
  @RequestMapping(value="/create-bb-session", method = RequestMethod.POST)
  @ResponseBody
  public String createBbSession(@RequestParam String bb_session, @RequestParam String host_name,
		                        @RequestParam String email, @RequestParam String sched, @RequestParam String week){
    try {
    	System.out.println("createBbSession() Email:"+email);
    	StringBuffer schedule = new StringBuffer(sched).append(" week ").append(week);
  
        BrownBagSessionDTO bbSession = new BrownBagSessionDTO(bb_session,host_name,email,schedule.toString());
        bbSessionDao.create(bbSession);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "bbSessionDao succesfully created!";
  }
  
  /**
   * Create a new user
   */

  @RequestMapping(value="/get-bb-session", method = { RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public String getAllSessions(){
	  StringBuffer message = new StringBuffer("");
    try {
    
        List<BrownBagSessionDTO> bbSessionList = bbSessionDao.getAll();
       
        if ((bbSessionList==null) || (bbSessionList.isEmpty())){
        	return "No Sessions scheduled at this time!!";
        }
        
        Iterator<BrownBagSessionDTO> iterator = bbSessionList.iterator();
        BrownBagSessionDTO bsession = null;
     
        while (iterator.hasNext()){
        	bsession = (BrownBagSessionDTO) iterator.next();
        	message.append("\n").append(bsession.toString());
        	
        }
        
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return message.toString();
  }

  /**
   * Create a new user
   */
  @RequestMapping(value="/create-chat-session", method = RequestMethod.POST)
  @ResponseBody
  public String createChatSession(@RequestParam String message, @RequestParam String toUser, String fromUser){
    try {
    	System.out.println("createChatSession() :"+message);
        
        MessageDTO messageDto = new MessageDTO(message,toUser);
        messageDto.setFromUser("JackFrost");
		
		if ("JackFrost".equalsIgnoreCase(messageDto.getToUser())){
			messageDto.setFromUser("Dori");
		} 
        messageDao.create(messageDto);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "MessageDTO succesfully created!";
  }
  
  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the UserDao used inside this controller.
  @Autowired
  private UserDao userDao;
  
  @Autowired
  private BrownBagSessionDao bbSessionDao;
  
//Wire the UserDao used inside this controller.
 @Autowired
 private MessageDao messageDao;
} // class UserController
