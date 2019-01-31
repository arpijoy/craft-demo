package glue;

/**
 * Notification for message content
 * @author arpita chattopadhyay
 *
 */

public class Notification {

  private String message;

  public Notification (String content) {
    this.message = content;
  }

  public String getContent() {
    return message;
  }

}
