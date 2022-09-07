package entity;

import javax.persistence.*;

@Entity
public class ShampooLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    @OneToOne(mappedBy = "label")
    private Shampoo shampoo;

    public Shampoo getShampoo() {
        return shampoo;
    }

    public void setShampoo(Shampoo shampoo) {
        this.shampoo = shampoo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
