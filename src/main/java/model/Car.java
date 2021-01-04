package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int vin;
    private String color;
    private int year;
    private int mileage;
    private String description;
    private int price;
    private String imagepath;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToMany(mappedBy = "car")
    private List<CarsUsers> carsUser = new ArrayList<>();

    public Car() {

    }

    public Car(int vin, String color, int year, int mileage, String description, Engine engine,
               Transmission transmission, Model model, int price, String imagepath) {
        this.vin = vin;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
        this.description = description;
        this.engine = engine;
        this.transmission = transmission;
        this.model = model;
        this.price = price;
        this.imagepath = imagepath;
    }

    public Car(long id, int vin, String color, int year, int mileage, String description, Engine engine,
               Transmission transmission, Model model, int price) {
        this.id = id;
        this.vin = vin;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
        this.description = description;
        this.engine = engine;
        this.transmission = transmission;
        this.model = model;
        this.price = price;
    }

    public List<CarsUsers> getCarsUser() {
        return carsUser;
    }

    public void setCarsUser(List<CarsUsers> carsUser) {
        this.carsUser = carsUser;
    }

    public Long getId() {
        return id;
    }

    public String getImagePath() {
        return imagepath;
    }

    public void setImagePath(String imagePath) {
        this.imagepath = imagePath;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && vin == car.vin
                && year == car.year
                && mileage == car.mileage
                && price == car.price
                && Objects.equals(color, car.color)
                && Objects.equals(description, car.description)
                && Objects.equals(imagepath, car.imagepath)
                && Objects.equals(engine, car.engine)
                && Objects.equals(transmission, car.transmission)
                && Objects.equals(model, car.model)
                && Objects.equals(carsUser, car.carsUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vin, color, year, mileage, description, price, imagepath, engine, transmission, model, carsUser);
    }

}
