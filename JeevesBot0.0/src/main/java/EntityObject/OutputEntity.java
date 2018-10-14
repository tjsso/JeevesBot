package EntityObject;

import javax.persistence.*;

@Entity
@Table(name = "Output")
public class OutputEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String output;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
