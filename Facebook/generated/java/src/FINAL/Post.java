package FINAL;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Post {
  public String text;
  public User author;
  public VDMSet viewers = SetUtil.set();
  public static Number postID = 0L;
  public Number ID = Post.postID;

  public void cg_init_Post_2(final String postText) {

    text = postText;
    ID = Post.postID;
    postID = Post.postID.longValue() + 1L;
    return;
  }

  public void cg_init_Post_1(final String postText, final User postAuthor) {

    text = postText;
    author = postAuthor;
    ID = Post.postID;
    postID = Post.postID.longValue() + 1L;
    return;
  }

  public Post(final String postText, final User postAuthor) {

    cg_init_Post_1(postText, postAuthor);
  }

  public Post(final String postText) {

    cg_init_Post_2(postText);
  }

  public String getText() {

    return text;
  }

  public VDMSet getViewers() {

    return Utils.copy(viewers);
  }

  public User getAuthor() {

    return author;
  }

  public Number getPostID() {

    return ID;
  }

  public void addUser(final User user) {

    viewers = SetUtil.union(Utils.copy(viewers), SetUtil.set(user));
  }

  public void removeUser(final User user) {

    viewers = SetUtil.diff(Utils.copy(viewers), SetUtil.set(user));
  }

  public void setAuthor(final User user) {

    author = user;
  }

  public Post() {}

  public String toString() {

    return "Post{"
        + "text := "
        + Utils.toString(text)
        + ", author := "
        + Utils.toString(author)
        + ", viewers := "
        + Utils.toString(viewers)
        + ", postID := "
        + Utils.toString(postID)
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

      return "mk_Post`Date" + Utils.formatFields(day, month, year);
    }
  }
}
