package FINAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		
		User sessionUser = null; // changes to the ID of the logged user
		SocialNetwork sessionSocialNetwork = null;
		
		welcomeMenu(sessionUser, sessionSocialNetwork);
	}




// option picker
public static int insertOption(){
	
	System.out.print("Enter option: ");
	
	Scanner sc = new Scanner(System.in); // object for scanner
	int option = sc.nextInt();
	
	return option;
}

//------------------------------------------------------------------
//---------------------------start menu---------------------------
//------------------------------------------------------------------

public static void welcomeMenu(User sessionUser, SocialNetwork sessionSocialNetwork)
{

	System.out.println("************************************");
	System.out.println("************************************");
	System.out.println("*******                      *******");
	System.out.println("*******    --------------    *******");
	System.out.println("*******    Social Network    *******");
	System.out.println("*******    --------------    *******");
	System.out.println("*******                      *******");
	System.out.println("************************************");
	System.out.println("************************************\n");

	System.out.println("\n#Options");
	System.out.println("  1. Create New Instance");
	System.out.println("  2. Load Demo Instance");

	int option = insertOption();

	switch (option) {
	case 1:
		SocialNetwork newSocialNetwork = new SocialNetwork();
		sessionSocialNetwork = newSocialNetwork;
		logMenu(sessionUser, sessionSocialNetwork);
		break;

	case 2:
		sessionSocialNetwork = povoar();
		logMenu(sessionUser, povoar());
		break;

	default:
		System.err.println("Invalid option! Exiting!");
		break;
	}	
	
}

//------------------------------------------------------------------
//---------------------------log menu---------------------------
//------------------------------------------------------------------


public static void logMenu(User sessionUser, SocialNetwork sessionSocialNetwork)
{
	
System.out.println("************************************");
System.out.println("************************************");
System.out.println("*******                      *******");
System.out.println("*******    --------------    *******");
System.out.println("******* Log in or Create new *******");
System.out.println("*******    --------------    *******");
System.out.println("*******                      *******");
System.out.println("************************************");
System.out.println("************************************\n");

System.out.println("\n#Options");
System.out.println("  1. Log in");
System.out.println("  2. Create new account");

int option = insertOption();

switch (option) {
case 1:
	sessionUser = logIn(sessionSocialNetwork);
	mainMenu(sessionUser, sessionSocialNetwork);
	break;

case 2:
	sessionUser = CreateUserAndAdd(sessionSocialNetwork);
	mainMenu(sessionUser, sessionSocialNetwork);
	break;

default:
	System.err.println("Invalid option! Exiting!");
	break;
}	

}


//------------------------------------------------------------------
//---------------------------main menu---------------------------
//------------------------------------------------------------------





public static void mainMenu(User sessionUser, SocialNetwork sessionSocialNetwork)
{

	System.out.println("************************************");
	System.out.println("************************************");
	System.out.println("*******                      *******");
	System.out.println("*******    --------------    *******");
	System.out.println("******* 	  Main Menu      *******");
	System.out.println("*******    --------------    *******");
	System.out.println("*******                      *******");
	System.out.println("************************************");
	System.out.println("************************************\n");

	System.out.println("\n#Options");
	System.out.println("  1. Posts and Feed");
	System.out.println("  2. Messenger");
	System.out.println("  3. Friends");
	System.out.println("  4. Groups");
	System.out.println("  5. Log Off");

	int option = insertOption();

	switch (option) {
	case 1:
		postsMenu(sessionUser, sessionSocialNetwork);
		break;

	case 2:
		messengerMenu(sessionUser, sessionSocialNetwork);
		break;

	case 3:
		friendsMenu(sessionUser, sessionSocialNetwork);
		break;

	case 4:
		groupsMenu(sessionUser, sessionSocialNetwork);
		break;

	case 5:
		logOff(sessionUser, sessionSocialNetwork);
		break;
		
	default:
		System.err.println("Invalid option! Exiting!");
		break;
	}	
	
}






//POSTS MENU


public static void postsMenu(User sessionUser, SocialNetwork sessionSocialNetwork)
{

	System.out.println("************************************");
	System.out.println("************************************");
	System.out.println("*******                      *******");
	System.out.println("*******    --------------    *******");
	System.out.println("*******    Posts and Feed    *******");
	System.out.println("*******    --------------    *******");
	System.out.println("*******                      *******");
	System.out.println("************************************");
	System.out.println("************************************\n");

	System.out.println("\n#Options");
	System.out.println("  1. Make Post");
	System.out.println("  2. View Feed");
	System.out.println("  3. Go Back");

	int option = insertOption();

	switch (option) {
	case 1:
		
		//---------- make post ----------
		
		boolean more = false;
		
		System.out.println("\n what friend should see this? (name 1 by 1 or type all) \n");
		Scanner sc1 = new Scanner(System.in); // object for scanner
		String name = sc1.nextLine();
		if(name.equals("all"))
		{
			
			System.out.println("\n Post Text: \n");
			Scanner sc = new Scanner(System.in); // object for scanner
			String text = sc.nextLine();
			
			Post newPost = new Post(text, sessionUser);
			sessionSocialNetwork.makePost(newPost);
			sessionUser.addPost(newPost);
			newPost.addUser(sessionUser);
			sessionSocialNetwork.makePost(newPost);
			
			
			
			Iterator<User> iterator = sessionUser.getFriendsList().iterator();
			while(iterator.hasNext()){
				User setElement = iterator.next();
				setElement.addPost(newPost);
				}
			postsMenu(sessionUser, sessionSocialNetwork);
			System.out.println("\n Done! \n");
			}
		else
		{
			System.out.println("\n Post Text: \n");
			Scanner sc = new Scanner(System.in); // object for scanner
			String text = sc.nextLine();
			
			Post newPost = new Post(text, sessionUser);
			sessionSocialNetwork.makePost(newPost);
			sessionUser.addPost(newPost);
			Iterator<User> iterator = sessionUser.getFriendsList().iterator();
			while(iterator.hasNext()){
				User setElement = iterator.next();
				if(setElement.getName().equals(name))
				setElement.addPost(newPost);
				}
			
			System.out.println("\n Add more users? (yes/no) \n");
			Scanner sc2 = new Scanner(System.in); // object for scanner
			String flag = sc2.nextLine();
			if(flag.equals("yes"))
			{
				System.out.println("whats the name of the next friend? \n");
				Scanner sc4 = new Scanner(System.in); // object for scanner
				String name2 = sc4.nextLine();
				
				Iterator<User> iterator3 = sessionUser.getFriendsList().iterator();
				while(iterator3.hasNext()){
					User setElement = iterator3.next();
					if(setElement.getName().equals(name))
					setElement.addPost(newPost);
					}
				postsMenu(sessionUser, sessionSocialNetwork);


			}
			else
			{
				postsMenu(sessionUser, sessionSocialNetwork);

			}
				
			
			postsMenu(sessionUser, sessionSocialNetwork);

			System.out.println("\n Done! \n");
			}
			
			
			
		break;		
			


		

		
		
		
		
		
		//---------- view feed ----------
	case 2:
		
		Iterator<Post> iterator2 = sessionUser.getFeed().iterator();
		while(iterator2.hasNext()){
			Post setElement = iterator2.next();
			System.out.println(setElement.getText());
			System.out.println("\n");
			
			postsMenu(sessionUser, sessionSocialNetwork);
		}
		break;
		

		
		
		
		


	case 3:
		mainMenu(sessionUser, sessionSocialNetwork);
		break;

	default:
		System.err.println("Invalid option! Exiting!");
		break;
	}	
	
}


















//------------------------------------------------------------------
//---------------------------friends menu---------------------------
//------------------------------------------------------------------


public static void friendsMenu(User sessionUser, SocialNetwork sessionSocialNetwork)
{
	
System.out.println("************************************");
System.out.println("************************************");
System.out.println("*******                      *******");
System.out.println("*******    --------------    *******");
System.out.println("*******        Friends	     *******");
System.out.println("*******    --------------    *******");
System.out.println("*******                      *******");
System.out.println("************************************");
System.out.println("************************************\n");

System.out.println("\n#Options");
System.out.println("  1. Add Friend");
System.out.println("  2. Remove Friend");
System.out.println("  3. List Friends");
System.out.println("  4. Go Back");

int option = insertOption();

switch (option) {
case 1:
	//---------- add friend ----------
	System.out.println("\n Whats the name of the user you want to add as friend? \n");
	Scanner sc = new Scanner(System.in); // object for scanner
	String name = sc.nextLine();
	
	Iterator<User> iterator = sessionSocialNetwork.users.iterator();
	while(iterator.hasNext()){
		User setElement = iterator.next();
		if(setElement.name.equals(name)) { //if user with that name exists
			
			sessionSocialNetwork.addFriend(sessionUser, setElement);
			System.out.println("\n User added! \n");
			friendsMenu(sessionUser, sessionSocialNetwork);
		}
	}
	System.out.println("User does not exist");
	friendsMenu(sessionUser, sessionSocialNetwork);;
	
	

	break;

case 2:
	
	//---------- remove friend ----------
	
	
	
	
		System.out.println("\n Whats the name of the user you want to REMOVE as friend? \n");
		Scanner sc2 = new Scanner(System.in); // object for scanner
		String name2 = sc2.nextLine();
		
		Iterator<User> iterator2 = sessionUser.friendsList.iterator();
		while(iterator2.hasNext()){
			User setElement = iterator2.next();
			if(setElement.name.equals(name2)) { //if user with that name exists
				
				sessionUser.RemoveFriend(setElement);
				System.out.println("\n User Removed! \n");
				friendsMenu(sessionUser, sessionSocialNetwork);;
			}
		}
		System.out.println("User does not exist");
		friendsMenu(sessionUser, sessionSocialNetwork);;	
	
	
	
	break;

	
case 3:
	
	//-----list friends ----
	Iterator<User> iterator3 = sessionUser.friendsList.iterator();
	while(iterator3.hasNext()){
		User setElement = iterator3.next();
		System.out.println(setElement.getName()); 
		System.out.println("\n");			
		}
	friendsMenu(sessionUser, sessionSocialNetwork);;
	
	System.out.println("User does not exist");
	friendsMenu(sessionUser, sessionSocialNetwork);;	
break;



case 4:
	mainMenu(sessionUser, sessionSocialNetwork);;
	break;
	
default:
	System.err.println("Invalid option! Exiting!");
	break;
	
}	
}











//------------------------------------------------------------------
//---------------------------Groups Menu---------------------------
//------------------------------------------------------------------





public static void groupsMenu(User sessionUser, SocialNetwork sessionSocialNetwork)
{

	System.out.println("************************************");
	System.out.println("************************************");
	System.out.println("*******                      *******");
	System.out.println("*******    --------------    *******");
	System.out.println("******* 	    Groups       *******");
	System.out.println("*******    --------------    *******");
	System.out.println("*******                      *******");
	System.out.println("************************************");
	System.out.println("************************************\n");

	System.out.println("\n#Options");
	System.out.println("  1. Create Group");
	System.out.println("  2. Add user to group");
	System.out.println("  3. Remove user to group");
	System.out.println("  4. Go Back");

	int option = insertOption();

	switch (option) {
	case 1:
		
		//---------- new group ----------
		
		System.out.println("\n Whats the name of the group to create \n");
		Scanner sc1 = new Scanner(System.in); // object for scanner
		String name = sc1.nextLine();
		
		Group group = new Group(name);
		System.out.println("\n Group Created! \n");
		groupsMenu(sessionUser, sessionSocialNetwork);
		break;
		
		
		//---------- Add user to group ----------
		

		
		case 2:	
		
		
		System.out.println("\n Whats the name of the user you want to add to the group? \n");
		Scanner sc2 = new Scanner(System.in); // object for scanner
		String name2 = sc2.nextLine();
		
		Iterator<User> iterator2 = sessionSocialNetwork.users.iterator();
		while(iterator2.hasNext()){
			User setElement = iterator2.next();
			if(setElement.name.equals(name2)) { //if user with that name exists
				
				sessionUser.RemoveFriend(setElement);
				System.out.println("\n User added! \n");
				friendsMenu(sessionUser, sessionSocialNetwork);;
			}
		}
		System.out.println("User does not exist");
		friendsMenu(sessionUser, sessionSocialNetwork);;	
		
		
		
		
		
		
		
		
		
		
		
		

		
		break;
		
		//---------- Remove user to group ----------
		


	case 3:
		mainMenu(sessionUser, sessionSocialNetwork);
		break;

	default:
		System.err.println("Invalid option! Exiting!");
		break;
	}	
	
}










public static void messengerMenu(User sessionUser, SocialNetwork sessionSocialNetwork)
{

	System.out.println("************************************");
	System.out.println("************************************");
	System.out.println("*******                      *******");
	System.out.println("*******    --------------    *******");
	System.out.println("******* 	  Messenger      *******");
	System.out.println("*******    --------------    *******");
	System.out.println("*******                      *******");
	System.out.println("************************************");
	System.out.println("************************************\n");

	System.out.println("\n#Options");
	System.out.println("  1. Send Message");
	System.out.println("  2. View Mailbox");
	System.out.println("  3. Go Back");

	int option = insertOption();

	switch (option) {
	case 1:
		
		//---------- send message ----------
		
		System.out.println("\n Whats the name of the user your sending this to? \n");
		Scanner sc = new Scanner(System.in); // object for scanner
		String name = sc.nextLine();
		
		System.out.println("\n Input Message Text: \n");
		Scanner sc2 = new Scanner(System.in); // object for scanner
		String text = sc.nextLine();
		
		Iterator<User> iterator = sessionSocialNetwork.users.iterator();
		while(iterator.hasNext()){
			User setElement = iterator.next();
			if(setElement.name.equals(name)) { //if user with that name exists
				
				Message newMessage = new Message(text,sessionUser, setElement);
				sessionSocialNetwork.sendMessage(newMessage);
				System.out.println("\n Message Sent");
				messengerMenu(sessionUser, sessionSocialNetwork);;
			}
		}
		System.out.println("User does not exist");
		messengerMenu(sessionUser, sessionSocialNetwork);;
		
		
		
		
		
		
		
		//---------- send message ----------
		

		
		//---------- view mailbox ----------
	case 2:
		
		Iterator<Message> iterator2 = sessionUser.messages.iterator();
		while(iterator2.hasNext()){
			Message setElement = iterator2.next();
			System.out.println(setElement.getText());
			System.out.println("\n");
			
			messengerMenu(sessionUser, sessionSocialNetwork);
		}
		break;
		
		//---------- view mailbox ----------
		


	case 3:
		mainMenu(sessionUser, sessionSocialNetwork);
		break;

	default:
		System.err.println("Invalid option! Exiting!");
		break;
	}	
	
}






//*********************************************
//              HELPER FUNCTION
//********************************************



public static User CreateUserAndAdd(SocialNetwork socialNetwork)
{
	System.out.println("\n What is your name? \n");
	Scanner sc = new Scanner(System.in); // object for scanner
	String name = sc.nextLine();
	User user = new User(name); 
	socialNetwork.addUser(user);
	return user;
}

public static User logIn(SocialNetwork socialNetwork)
{
//if user in socinet then sessinuser become him
	System.out.println("\n What is your name? \n");
	Scanner sc = new Scanner(System.in); // object for scanner
	String name = sc.nextLine();
	
	Iterator<User> iterator = socialNetwork.users.iterator();
	while(iterator.hasNext()){
		User setElement = iterator.next();
		if(setElement.name.equals(name)) {
			return setElement;
		}
	}
	System.out.println("Invalid option! Exiting to welcome menu!");
	welcomeMenu(null, null);
	return null;
}



public static void logOff(User sessionUser, SocialNetwork sessionSocialNetwork)
{
	sessionUser = null;
	sessionSocialNetwork = null;
	welcomeMenu(sessionUser, sessionSocialNetwork);
}
public static SocialNetwork povoar()
{
	SocialNetwork socialNetworkDemo = new SocialNetwork();
	User demo = new User ("Demo");
	User demo2 = new User ("Demo2");
	User demo3 = new User ("Demo3");
	
	socialNetworkDemo.addUser(demo);
	socialNetworkDemo.addUser(demo2);
	socialNetworkDemo.addUser(demo3);
	socialNetworkDemo.addFriend(demo, demo2);
	
	Post post1 = new Post("A primeira de muitas ;)", demo);
	socialNetworkDemo.makePost(post1);
	post1.addUser(demo2);
	demo2.addPost(post1);

	
	//add users and posts and messages and groups
	/*
	System.out.println("oi");
	User user1 = new User("joao");
	new SocialNetwork();
	SocialNetwork soci = new SocialNetwork();
	
	soci.addUser(user1);
	
	System.out.println(soci.getUsersNames());
	*/
	
	
	//^tem de haver um user chamado demo
	System.out.println("Please log in with user name: Demo \n)");
	return socialNetworkDemo;
}
}

