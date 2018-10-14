package DomainObject;

import Main.MainB;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class BotListener extends ListenerAdapter {



    private void createFactory(){

    }

    @Override
    public void onMessage(MessageEvent event) throws Exception {

        createFactory();

        String message = event.getMessage();

        String[] args = message.split("/s");

        String string = "";

        if(message.substring(0,1).equals("!")){

            Command command = new Command().findByCommand(MainB.factory,args[0]);

            if(command != null) {
               // string = command.getOutputString();
                string = "Working";
            }else{
                string = "Cannot find command";
            }
        }
        event.getChannel().send().message(string);
    }
}