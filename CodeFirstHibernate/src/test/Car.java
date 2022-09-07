package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends PassengerVehicle {

    private String model;

    public Car() {
    }

    public Car(int numOfPassengers, String model) {
        super("CAR", numOfPassengers);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
