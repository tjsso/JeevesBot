package main.java.EntityObjects;

import javax.persistence.*;

@Entity
@Table(name="Commands")
public class CommandEntity extends EntityObject {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="command")
    private String command;

    @Column(name="outputId")
    private Integer outputId;

    @Column(name="requiresMod")
    private Boolean requiresMod;

    @Column(name="isMain")
    private Boolean isMain;

    public CommandEntity(){

    }

    public CommandEntity(String command, Integer outputId, Boolean requiresMod, Boolean isMain) {
        this.command = command;
        this.outputId = outputId;
        this.requiresMod = requiresMod;
        this.isMain = isMain;
    }

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

    public void setOutputId(int outputId) {
        this.outputId = outputId;
    }

    public Boolean isRequiresMod() {
        return requiresMod;
    }

    public void setRequiresMod(boolean requiresMod) {
        this.requiresMod = requiresMod;
    }

    public Boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    @Override
    public String toString() {
        return "CommandEntity{" +
                "command='" + command + '\'' +
                ", outputId=" + outputId +
                ", requiresMod=" + requiresMod +
                ", isMain=" + isMain +
                '}';
    }
}
