import EntityObject.CommandEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.pircbotx.PircBotX;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.List;

public class Main {

	public static final String BOTNAME = "Jeeves";
	public static final String OAUTH = "oauth:33firxhhfz6gp9hihmdg4ymnzxzk4t";
	public static final String CHANNEL = "phoenyxzypher";

	public static PircBotX bot;
	private static SessionFactory factory;

	public static void main(String[] args) throws Exception {

		//URL url = Main.class.getResource("java/hibernate.cfg.xml");
		File file = new File("src/hibernate.cfg.xml");
		//System.out.println(file.getAbsolutePath() + " " + file.length());

		try {
			factory = new Configuration().configure(file).addAnnotatedClass(CommandEntity.class).buildSessionFactory();
					//addPackage("com.xyz") //add package if used.
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		Session session = factory.openSession();

		try {
			session.beginTransaction();

			// Create CriteriaBuilder
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// Create CriteriaQuery
			CriteriaQuery<CommandEntity> query = builder.createQuery(CommandEntity.class);
			Root<CommandEntity> root = query.from(CommandEntity.class);
			query.select(root).where(builder.equal(root.get("command"), "!mod"));;
			Query<CommandEntity> q = session.createQuery(query);
			CommandEntity result = q.getResultList().get(0);

			System.out.println("Lets Hope this Works! + " + result.getCommand());
			session.getTransaction().commit();

		}catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		factory.close();
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