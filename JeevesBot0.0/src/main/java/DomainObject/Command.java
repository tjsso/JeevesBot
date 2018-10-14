package DomainObject;

import EntityObject.CommandEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Command extends CommandEntity {

    public Command(){

    }

    private static SessionFactory sFactory;

    public static Command findByCommand( SessionFactory factory,String command){

        sFactory = factory;

        List<Command> list = constructQuery("command", command);

        if(!list.isEmpty())
            return list.get(0);
        return null;
    }

    public String getOutputString() {

        String output = new Output().findById(this.getOutputId());

        if(output != null)
            return output;
        return "Error - Could not find Output";
    }

    private static List<Command> constructQuery(String column, String criteria){

        Session session = sFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            // Create CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // Create CriteriaQuery
            CriteriaQuery<Command> query = builder.createQuery(Command.class);
            Root<Command> root = query.from(Command.class);
            query.select(root).where(builder.equal(root.get(column), criteria));
            Query<Command> q = session.createQuery(query);

            ArrayList<Command> list = new ArrayList<>(q.getResultList());
            tx.commit();

            return list;

        }catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
        session.close();

        return new ArrayList<>();
    }

}
