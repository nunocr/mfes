package FINAL;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SocialNetwork {
  public static SocialNetwork socialNetwork = new SocialNetwork();
  public VDMSet users = SetUtil.set();
  private User db_user1 = new User("Vasco", FINAL.quotes.MaleQuote.getInstance());
  private User db_user2 = new User("Anao", FINAL.quotes.FemaleQuote.getInstance());
  private User db_user3 = new User("Cuco", FINAL.quotes.OtherQuote.getInstance());

  public void cg_init_SocialNetwork_1() {

    return;
  }

  public SocialNetwork() {

    cg_init_SocialNetwork_1();
  }

  public static SocialNetwork getInstance() {

    return SocialNetwork.socialNetwork;
  }

  public static SocialNetwork clearInstance() {

    socialNetwork = new SocialNetwork();
    return getInstance();
  }

  public void addUser(final User user) {

    users = SetUtil.union(Utils.copy(users), SetUtil.set(user));
    IO.println("User successfully added!");
  }

  public void addFriend(final User user1, final User user2) {

    User user1_temp = user1;
    User user2_temp = user2;
    VDMSet atomicTmp_1 = SetUtil.union(user1.friendsList, SetUtil.set(user2));
    VDMSet atomicTmp_2 = SetUtil.union(user2.friendsList, SetUtil.set(user1));
    {
        /* Start of atomic statement */
      user1_temp.friendsList = Utils.copy(atomicTmp_1);
      user2_temp.friendsList = Utils.copy(atomicTmp_2);
    } /* End of atomic statement */
  }

  public VDMSet getUsersNames() {

    VDMSet names = SetUtil.set();
    for (Iterator iterator_1 = users.iterator(); iterator_1.hasNext(); ) {
      User user = (User) iterator_1.next();
      names = SetUtil.union(Utils.copy(names), SetUtil.set(user.name));
    }
    return Utils.copy(names);
  }

  public void makePost(final Post newPost) {

    newPost.getAuthor().addPost(newPost);
    for (Iterator iterator_2 = newPost.getViewers().iterator(); iterator_2.hasNext(); ) {
      User currUser = (User) iterator_2.next();
      currUser.addPost(newPost);
    }
  }

  public void sendMessage(final Message newMessage) {

    newMessage.target.addMessage(newMessage);
  }

  public String toString() {

    return "SocialNetwork{"
        + "socialNetwork := "
        + Utils.toString(socialNetwork)
        + ", users := "
        + Utils.toString(users)
        + ", db_user1 := "
        + Utils.toString(db_user1)
        + ", db_user2 := "
        + Utils.toString(db_user2)
        + ", db_user3 := "
        + Utils.toString(db_user3)
        + "}";
  }
}
