package store;

import model.*;

import java.util.List;

public interface Store {
    void addCar(Car car);
    void addBrand(Brand brand);
    void addModel(Model model);
    void addTransmission(Transmission transmission);
    void addEngine(Engine engine);
    void delete(Car car);
    void update(Car car);
    User findUserByEmail(String email);
    void createUser(User user);
    List<Model> findAllModels();
    List<Brand> findAllBrands();
    List<Car> findAllCars();
    void updateUser(User user);
    List<CarsUsers> findAllCarsUsers();
    void addCarsUsers(CarsUsers carsUsers);
}
