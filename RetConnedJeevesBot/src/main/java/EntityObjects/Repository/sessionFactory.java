package main.java.EntityObjects.Repository;

import main.java.EntityObjects.EntityObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface sessionFactory {

    public void closeSession();

    public void save(EntityObject entityObject);
}
