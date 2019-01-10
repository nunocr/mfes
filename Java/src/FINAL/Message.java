package FINAL;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Message {
  public String text;
  public User author;
  public User target;
  public static Number messageID = 0L;
  public Number ID = Message.messageID;

  public void cg_init_Message_1(
      final String messageText, final User messageAuthor, final User messageTarget) {

    text = messageText;
    author = messageAuthor;
    target = messageTarget;
    ID = Message.messageID;
    messageID = Message.messageID.longValue() + 1L;
    return;
  }

  public Message(final String messageText, final User messageAuthor, final User messageTarget) {

    cg_init_Message_1(messageText, messageAuthor, messageTarget);
  }

  public String getText() {

    return text;
  }

  public User getTarget() {

    return target;
  }

  public User getAuthor() {

    return author;
  }

  public Number getPostID() {

    return ID;
  }

  public Message() {}

  public String toString() {

    return "Message{"
        + "text := "
        + Utils.toString(text)
        + ", author := "
        + Utils.toString(author)
        + ", target := "
        + Utils.toString(target)
        + ", messageID := "
        + Utils.toString(messageID)
        + ", ID := "
        + Utils.toString(ID)
        + "}";
  }

  public static class Date implements Record {
    public Number day;
    public Number month;
    public Number year;

    public Date(final Number _day, final Number _month, final Number _year) {

      day = _day;
      month = _month;
      year = _year;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(day, other.day))
          && (Utils.equals(month, other.month))
          && (Utils.equals(year, other.year));
    }

    public int hashCode() {

      return Utils.hashCode(day, month, year);
    }

    public Date copy() {

      return new Date(day, month, year);
    }

    public String toString() {

      return "mk_Message`Date" + Utils.formatFields(day, month, year);
    }
  }
}
