package FINAL;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  public String name;
  public Object sex;
  public Date birthDate;
  public VDMSet friendsList = SetUtil.set();
  public VDMSet groupsList = SetUtil.set();
  public VDMSeq feed = SeqUtil.seq();
  public VDMSeq messages = SeqUtil.seq();
  public static Number userID = 0L;
  public Number ID = User.userID;

  public void cg_init_User_4(
      final String userName,
      final Number userDay,
      final Number userMonth,
      final Number userYear,
      final Object userSex) {

    name = userName;
    birthDate.day = userDay;
    birthDate.month = userMonth;
    birthDate.year = userYear;
    sex = userSex;
    friendsList = SetUtil.set();
    ID = User.userID;
    userID = User.userID.longValue() + 1L;
    return;
  }

  public void cg_init_User_3(final String userName, final Object userSex) {

    name = userName;
    sex = userSex;
    friendsList = SetUtil.set();
    ID = User.userID;
    userID = User.userID.longValue() + 1L;
    return;
  }

  public void cg_init_User_2(
      final String userName, final Number userDay, final Number userMonth, final Number userYear) {

    name = userName;
    birthDate.day = userDay;
    birthDate.month = userMonth;
    birthDate.year = userYear;
    friendsList = SetUtil.set();
    ID = User.userID;
    userID = User.userID.longValue() + 1L;
    return;
  }

  public void cg_init_User_1(final String userName) {

    name = userName;
    friendsList = SetUtil.set();
    ID = User.userID;
    userID = User.userID.longValue() + 1L;
    return;
  }

  public User(final String userName) {

    cg_init_User_1(userName);
  }

  public User(
      final String userName, final Number userDay, final Number userMonth, final Number userYear) {

    cg_init_User_2(userName, userDay, userMonth, userYear);
  }

  public User(final String userName, final Object userSex) {

    cg_init_User_3(userName, userSex);
  }

  public User(
      final String userName,
      final Number userDay,
      final Number userMonth,
      final Number userYear,
      final Object userSex) {

    cg_init_User_4(userName, userDay, userMonth, userYear, userSex);
  }

  public String getName() {

    return name;
  }

  public Object getSex() {

    return sex;
  }

  public Date getBirthDate() {

    return Utils.copy(birthDate);
  }

  public VDMSet getFriendsList() {

    return Utils.copy(friendsList);
  }

  public Number getUserID() {

    return ID;
  }

  public VDMSeq getFeed() {

    return Utils.copy(feed);
  }

  public VDMSeq getMessages() {

    return Utils.copy(messages);
  }

  public void SetName(final String newName) {

    name = newName;
  }

  public void addFriend(final User friend) {

    friendsList = SetUtil.union(Utils.copy(friendsList), SetUtil.set(friend));
  }

  public void RemoveFriend(final User friend) {

    friendsList = SetUtil.diff(Utils.copy(friendsList), SetUtil.set(friend));
  }

  public void addPost(final Post newPost) {

    feed = SeqUtil.conc(Utils.copy(feed), SeqUtil.seq(newPost));
  }

  public void addMessage(final Message message) {

    messages = SeqUtil.conc(Utils.copy(messages), SeqUtil.seq(message));
  }

  public User() {}

  public String toString() {

    return "User{"
        + "name := "
        + Utils.toString(name)
        + ", sex := "
        + Utils.toString(sex)
        + ", birthDate := "
        + Utils.toString(birthDate)
        + ", friendsList := "
        + Utils.toString(friendsList)
        + ", groupsList := "
        + Utils.toString(groupsList)
        + ", feed := "
        + Utils.toString(feed)
        + ", messages := "
        + Utils.toString(messages)
        + ", userID := "
        + Utils.toString(userID)
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

      return "mk_User`Date" + Utils.formatFields(day, month, year);
    }
  }
}
