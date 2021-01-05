package store;

import model.*;

import java.util.List;

public interface Store {
    void addCar(Car car);
    void addBrand(Brand brand);
    void updateBrand(Brand brand);
    void addModel(Model model);
    void updateModel(Model model);
    void addTransmission(Transmission transmission);
    void updateTransmission(Transmission transmission);
    void addEngine(Engine engine);
    void updateEngine(Engine engine);
    void delete(Car car);
    void update(Car car);
    User findUserByEmail(String email);
    void createUser(User user);
    List<Model> findAllModels();
    List<Brand> findAllBrands();
    List<Car> findAllCars();
    void updateUser(User user);
    List<CarsUsers> findAllCarsUsers();
    void deleteCarUser(CarsUsers carsUsers);
    void addCarsUsers(CarsUsers carsUsers);
    void updateCarsUsers(CarsUsers carsUsers);
    List<CarsUsers> findCarsByUserId(int id);
    CarsUsers findCarsUsersById(int id);
}
