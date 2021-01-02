package model;


import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
    private byte[] image;;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "history_user",
            joinColumns = { @JoinColumn(name = "car_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> users = new ArrayList<User>();


    public Car() {
    }

    public Car(int vin, String color, int year, int mileage, String description, Engine engine,
               Transmission transmission, Model model, int price) {
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

    public Car(int vin, String color, int year, int mileage, String description, Engine engine,
               Transmission transmission, Model model, int price, byte[] image) {
        this.vin = vin;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
        this.description = description;
        this.engine = engine;
        this.transmission = transmission;
        this.model = model;
        this.price = price;
        this.image = image;
    }



    public Car(long id, int vin, String color, int year, int mileage, String description, Engine engine,
               Transmission transmission, Model model, List<User> users, int price, byte[] image) {
        this.id = id;
        this.vin = vin;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
        this.description = description;
        this.engine = engine;
        this.transmission = transmission;
        this.model = model;
        this.users = users;
        this.price = price;
        this.image = image;
    }

    public long getId() {
        return id;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
                && Arrays.equals(image, car.image)
                && Objects.equals(engine, car.engine)
                && Objects.equals(transmission, car.transmission)
                && Objects.equals(model, car.model)
                && Objects.equals(users, car.users);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, vin, color, year, mileage, description, price, engine, transmission, model, users);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", vin=" + vin +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", engine=" + engine +
                ", transmission=" + transmission +
                ", model=" + model +
                ", model=" + users + '}';
    }
}
