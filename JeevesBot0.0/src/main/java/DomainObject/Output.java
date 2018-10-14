package DomainObject;

import EntityObject.OutputEntity;
import Main.MainB;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Output extends OutputEntity {

    private static SessionFactory factory;

    public Output(){
        try {
            factory = new Configuration().configure(MainB.HIBERNATE_CONFIG).addAnnotatedClass(OutputEntity.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Output - Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static String findById(Integer id){
        List<Output> outputs = constructQuery("id", id.toString());

        if(!outputs.isEmpty())
            return outputs.get(0).getOutput();
        return null;
    }

    private static List<Output> constructQuery(String column, String criteria){

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            // Create CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // Create CriteriaQuery
            CriteriaQuery<Output> query = builder.createQuery(Output.class);
            Root<Output> root = query.from(Output.class);
            query.select(root).where(builder.equal(root.get(column), criteria));
            Query<Output> q = session.createQuery(query);

            ArrayList<Output> list = new ArrayList<>(q.getResultList());
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
