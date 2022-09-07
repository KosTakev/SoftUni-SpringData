package entity;

import javax.persistence.*;

@Entity
public class Shampoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private double weigh;

    @OneToOne
    private ShampooLabel label;

    @ManyToOne
    private Batch batch;

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public ShampooLabel getLabel() {
        return label;
    }

    public void setLabel(ShampooLabel label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeigh() {
        return weigh;
    }

    public void setWeigh(double weigh) {
        this.weigh = weigh;
    }
}
