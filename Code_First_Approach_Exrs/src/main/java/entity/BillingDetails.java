package entity;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetails extends BaseEntity {

    private String number;
    private BankUser owner;

    public BillingDetails() {
    }

    @Column(nullable = false, unique = true)
    public String getNumber() {
        return number;
    }

    @ManyToOne
    public BankUser getOwner() {
        return owner;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOwner(BankUser owner) {
        this.owner = owner;
    }
}
