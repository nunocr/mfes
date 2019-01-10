package FINAL;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Group {
  public String name;
  public VDMSet usersList = SetUtil.set();

  public void cg_init_Group_2(final String groupName, final VDMSet users) {

    name = groupName;
    usersList = Utils.copy(users);
    return;
  }

  public void cg_init_Group_1(final String groupName) {

    name = groupName;
    usersList = SetUtil.set();
    return;
  }

  public Group(final String groupName) {

    cg_init_Group_1(groupName);
  }

  public Group(final String groupName, final VDMSet users) {

    cg_init_Group_2(groupName, Utils.copy(users));
  }

  public void addUser(final User user) {

    usersList = SetUtil.union(Utils.copy(usersList), SetUtil.set(user));
  }

  public void removeUser(final User user) {

    usersList = SetUtil.diff(Utils.copy(usersList), SetUtil.set(user));
  }

  public Group() {}

  public String toString() {

    return "Group{"
        + "name := "
        + Utils.toString(name)
        + ", usersList := "
        + Utils.toString(usersList)
        + "}";
  }
}
