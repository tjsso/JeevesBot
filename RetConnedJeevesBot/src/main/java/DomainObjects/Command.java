package main.java.DomainObjects;

import main.java.DomainObjects.DAO.CommandDAO;
import main.java.EntityObjects.CommandEntity;

public class Command extends CommandEntity{

    private CommandDAO dao;

    public Command(){
        dao = new CommandDAO();
    }

    public Command findByCommand(String command){
       Command c = dao.findByCommand(command);
       return c;
    }

    public void close(){
        dao.closeSession();
    }

    public void save(CommandEntity c){
        dao.save(c);
    }

}
