package glue.controllers;

import glue.Notification;
import glue.models.MessageDTO;
import glue.models.MessageDao;
import glue.service.NotificationService;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Main Controller class
 * @author arpitachattopadhyay
 *
 */

@Controller
public class MainController {
	
	

	@Autowired
	private NotificationService notificationService;

	/**
	 * GET / -> show the index page.
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * GET /notifications -> show the notifications page.
	 */
	@RequestMapping("/notifications")
	public String notifications() {
		return "notifications";
	}

	/**
	 * GET /rest -> show the notifications page.
	 */
	@RequestMapping("/rest")
	public String restapis() {
		return "rest";
	}

	/**
	 * GET /bbsession -> show the bbSession.
	 */
	@RequestMapping("/bbsession")
	public String bbsessions() {
		return "bbsession";
	}

	/**
	 * GET /chatter -> show the chat session.
	 */
	@RequestMapping("/chatter")
	public String chatter() {
		return "chatter";
	}

	/**
	 * POST /some-action -> do an action.
	 * 
	 * After the action is performed will be notified UserA.
	 */
	@RequestMapping(value = "/some-action", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> someAction() {

		StringBuffer upcomingSession = new StringBuffer("<div class=\"alert alert-info\">");
		List<MessageDTO> mSessions = messageDao.getAll();

		if ((mSessions == null) || (mSessions.isEmpty())) {
			upcomingSession.append("No Messages</div>");

			notificationService.notify(new Notification(upcomingSession.toString()), // notification
																						// object
					"JackFrost" // username
			);
			notificationService.notify(new Notification(upcomingSession.toString()), // notification
																						// object
					"Dori" // username
			);
		}
		
		Iterator<MessageDTO> iterator = mSessions.iterator();
		while (iterator.hasNext()) {
			MessageDTO mDto = (MessageDTO) iterator.next();
			upcomingSession = new StringBuffer("").append(mDto.toString()).append("\n");
			if ("All".equalsIgnoreCase(mDto.getToUser())) {
				notificationService.notify(new Notification(upcomingSession.toString()), // notification
																							// object
						"JackFrost" // username
				);
				notificationService.notify(new Notification(upcomingSession.toString()), // notification
						// object
						"Dori" // username
				);
			} else {
				notificationService.notify(new Notification(upcomingSession.toString()), // notification
						// object
						mDto.getToUser() // username
				);
			}
		} // while end

		// Return an http 200 status code
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * POST /send-message -> do an action.
	 * 
	 * After the action is performed will be notified user.
	 */
	@RequestMapping(value = "/send-message", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> sendMessage( @RequestParam String username,
			@RequestParam String message) {

		System.out.println("username:" + username + "message:" + message);
		
		String fromFontBegin = "<font color=\"FF00CC\">";
		String fontEnd = "</font><br/>";

		
		MessageDTO messageDto = new MessageDTO(message, username);
		messageDto.setFromUser("JackFrost");

		if ("JackFrost".equalsIgnoreCase(messageDto.getToUser())) {
			messageDto.setFromUser("Dori");
		}
		messageDao.create(messageDto);
		StringBuffer smartmessage = new StringBuffer(messageDto.getFromUser()).append(": ").append(message).append(fontEnd);
		
		//Update from Chat box
		notificationService.notify(new Notification(fromFontBegin+smartmessage.toString()), // notification
				messageDto.getFromUser() // username
		);
		//Update To Chat Box
		notificationService.notify(new Notification(smartmessage.toString()), // notification
				messageDto.getToUser() // username
		);

		// Return an http 200 status code
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Autowired
	private MessageDao messageDao;
} // class MainController
