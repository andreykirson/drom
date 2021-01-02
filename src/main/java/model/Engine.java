package model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table (name = "engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String engineType;
    private int capacity;
    private int power;


    public Engine() {
    }

    public Engine(String engineType, int capacity, int power) {
        this.engineType = engineType;
        this.capacity = capacity;
        this.power = power;
    }

    public Engine(int id, String engineType, int capacity, int power) {
        this.id = id;
        this.engineType = engineType;
        this.capacity = capacity;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id
                && capacity == engine.capacity
                && power == engine.power
                && Objects.equals(engineType, engine.engineType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, engineType, capacity, power);
    }
}
