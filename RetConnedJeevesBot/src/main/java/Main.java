package main.java;

import main.java.EntityObjects.CommandEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

public class Main {

	public static final String BOTNAME = "Jeeves";
	public static final String OAUTH = "oauth:33firxhhfz6gp9hihmdg4ymnzxzk4t";
	public static final String CHANNEL = "phoenyxzypher";

	public static PircBotX bot;

	public static void main(String[] args) throws Exception {

		configureSessionFactory();

		Session session = null;
		Transaction tx=null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// Creating Contact entity that will be save to the sqlite database
			CommandEntity myContact = new CommandEntity();
			// Saving to the database
			//session.save(myContact);
			//session.save(yourContact);

			// Committing the change in the database.
			session.flush();
			tx.commit();

			// Fetching saved data
			List<CommandEntity> contactList = session.createQuery("from Commands").list();

			for (CommandEntity contact : contactList) {
				System.out.println(contact);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

			// Rolling back the changes to make the data consistent in case of any failure
			// in between multiple database write operations.
			tx.rollback();
		} finally{
			if(session != null) {
				session.close();
			}
		}


		/*Configuration config = new Configuration.Builder()
				.setName(BOTNAME)
				.setServer("irc.chat.twitch.tv", 6667)
				.setServerPassword(OAUTH)
				.addListener(new Bot())
				.addAutoJoinChannel("#" + CHANNEL)
				.buildConfiguration();

		bot = new PircBotX(config);
		bot.startBot();*/


	}
}