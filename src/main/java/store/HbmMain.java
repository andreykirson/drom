package store;

import com.google.gson.Gson;
import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HbmMain {

    public static void main(String[] args) {
        final Store store = HbmStore.getInstance();
//        Brand brandOne = new Brand("Mercedes-Benz");
//        Brand brandTwo = new Brand("Oppel");
//        store.addBrand(brandOne);
//        store.addBrand(brandTwo);
//        Model modelOne = new Model("GL");
//        Model modelTwo = new Model("ZT");
//        modelOne.setBrand(brandOne);
//        modelTwo.setBrand(brandTwo);
//        store.addModel(modelOne);
//        store.addModel(modelTwo);
//        Transmission transmission = new Transmission("manual");
//        store.addTransmission(transmission);
//        System.out.println("transmission id " + transmission.getId());
//        Engine engine = new Engine("disel", 60, 1200);
//        store.addEngine(engine);
//        System.out.println("Engine id " + engine.getId());
//        User userOne = new User("Anri", "Anri@gmail.com", "password", "+7 900 821 45 98");
//        List<User> users = new ArrayList<User>();
//        users.add(userOne);
//        Car carOne = new Car(1, "black", 2010, 62000, "good car", engine, transmission, modelOne, 10000);
//        Car carTwo = new Car(2, "white", 2005, 60000, "bad car", engine, transmission, modelTwo, 5000);
//        store.addCar(carOne);
//        System.out.println("carOne id " + carOne.getId());
//        store.addCar(carTwo);
//        System.out.println("carTwo id " + carTwo.getId());
        List<Car> cars = new ArrayList<Car>();
//        cars.add(carOne);
//        cars.add(carTwo);
//        userOne.setCars(cars);
//        store.createUser(userOne);
//        System.out.println("userOne id " + userOne.getId());
        cars = store.findAllCars();

//        JSONObject rsl = new JSONObject();
////
////        for (Car c:cars) {
////            rsl.put("model", c.getModel().getName());
////            rsl.put("brand", c.getModel().getBrand());
////            rsl.put("price", c.getPrice());
////            rsl.put("user name", c.getUsers());
////        }

        String json = new Gson().toJson(cars);

        System.out.println("JSON Object :" + json);
   }

}
