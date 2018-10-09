package EntityObject;

import javax.persistence.*;

@Entity
@Table(name = "Commands")
public class CommandEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String command;

    @Column
    private Integer outputId;

    @Column
    private Boolean requiresMod;

    @Column
    private Boolean isMain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getOutputId() {
        return outputId;
    }

    public void setOutputId(Integer outputId) {
        this.outputId = outputId;
    }

    public Boolean getRequiresMod() {
        return requiresMod;
    }

    public void setRequiresMod(Boolean requiresMod) {
        this.requiresMod = requiresMod;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    @Override
    public String toString(){
        return "Command " + command + " requires " + requiresMod + " access to use and returns output of " + outputId;
    }
}
