import EntityObject.CommandEntity;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.pircbotx.PircBotX;

import java.io.File;

public class Main {

	public static final String BOTNAME = "Jeeves";
	public static final String OAUTH = "oauth:33firxhhfz6gp9hihmdg4ymnzxzk4t";
	public static final String CHANNEL = "phoenyxzypher";

	public static PircBotX bot;
	private static SessionFactory factory;

	public static void main(String[] args) throws Exception {

		//URL url = Main.class.getResource("java/hibernate.cfg.xml");
		File file = new File("src/hibernate.cfg.xml");
		System.out.println(file.getAbsolutePath() + " " + file.length());

		try {
			factory = new Configuration().configure(file).addAnnotatedClass(CommandEntity.class).buildSessionFactory();
					//addPackage("com.xyz") //add package if used.
							//addAnnotatedClass(CommandEntity.class).
							//buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
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