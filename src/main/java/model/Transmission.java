package model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transmissions")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String transmissionType;

    public Transmission() {

    }

    public Transmission(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Transmission(int id, String transmissionType) {
        this.id = id;
        this.transmissionType = transmissionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transmission that = (Transmission) o;
        return id == that.id
                && Objects.equals(transmissionType, that.transmissionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transmissionType);
    }
}
