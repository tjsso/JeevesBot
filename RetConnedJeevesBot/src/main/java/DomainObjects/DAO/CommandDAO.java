package main.java.DomainObjects.DAO;

import main.java.DomainObjects.Command;
import main.java.EntityObjects.CommandEntity;
import main.java.EntityObjects.EntityObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import main.java.EntityObjects.Repository.sessionFactory;
import org.hibernate.criterion.Restrictions;


public class CommandDAO extends genericDAO implements sessionFactory {

    private SessionFactory factory;
    private Session session;

    public CommandDAO(){
        factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml") //file name string defaults to hibernate.cfg.xml
                .addAnnotatedClass(CommandEntity.class)
                .buildSessionFactory();

        session = factory.getCurrentSession();
    }

    public Command findByCommand(String command){
        //Command c = null;
        Object obj = null;
        Session sess = factory.openSession();
        try{
            sess.beginTransaction();
            obj = sess.createCriteria(CommandEntity.class)
                                        .add(Restrictions.eq("command", command))
                                        .uniqueResult();
            sess.getTransaction().commit();
        } catch(Exception e){
            if(sess.getTransaction() != null)
                sess.getTransaction().rollback();

            e.printStackTrace();
        }
        return (Command) obj;
    }

    public void closeSession(){
        factory.close();
    }

    public void save(EntityObject entityObject){

        CommandEntity commandEntity = (CommandEntity) entityObject;

        try {
            //save receive java objects...
            //System.out.println("Create");
            //CommandEntity c = new CommandEntity("!fire",0,false,false);

            System.out.println("begin tran");
            session.beginTransaction();

            System.out.println("saving entity");
            if (commandEntity.getCommand() != null)
                session.save(commandEntity);

            System.out.println("commit tran");
            session.getTransaction().commit();

            System.out.println("successful addition");
        }catch(Exception e){
                System.out.println("ERROR - Cannot save CommandEntity!");
            	e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
