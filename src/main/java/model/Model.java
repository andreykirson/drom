package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Model() {

    }

    public Model(String name) {
        this.name = name;
    }

    public Model(String name, Brand brand) {
        this.name = name;
        this.brand = brand;
    }

    public Model(int id, String name, Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return id == model.id
                && Objects.equals(name, model.name)
                && Objects.equals(brand, model.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand);
    }

    @Override
    public String toString() {
        return "Model{" + "name='" + name + '\'' + ", brand=" + brand + '}';
    }
}