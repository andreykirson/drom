package store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.internal.filter.ValueNodes;
import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


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
//        User userOne = new User("Anri", "Anri@gmail.com", "password1", "+7 900 821 45 98");
//        User userTwo = new User("Oleg", "Oleg@gmail.com", "password2", "+7 900 821 45 98");
//
//        store.createUser(userOne);
//        store.createUser(userTwo);
//
//
//        Car carOne = new Car(1, "black", 2010, 62000, "good car", engine, transmission, modelOne, 10000);
//        Car carTwo = new Car(2, "white", 2005, 60000, "bad car", engine, transmission, modelTwo, 5000);
//        Car carThree = new Car(3, "yellow", 2015, 6000, "super", engine, transmission, modelTwo, 1000);
//        Car carFour = new Car(4, "green", 2000, 41000, "fastest car", engine, transmission, modelTwo, 9000);
//
//        store.addCar(carOne);
//        System.out.println("carOne id " + carOne.getId());
//        store.addCar(carTwo);
//        store.addCar(carThree);
//        store.addCar(carFour);
//
//        CarsUsers carsUsersOne = new CarsUsers(new Date(), false, userOne, carOne);
//        CarsUsers carsUsersTwo = new CarsUsers(new Date(), false, userOne, carTwo);
//        CarsUsers carsUsersThree = new CarsUsers(new Date(), false, userTwo, carThree);
//        CarsUsers carsUsersFour = new CarsUsers(new Date(), false, userTwo, carFour);
//
//        store.addCarsUsers(carsUsersOne);
//        store.addCarsUsers(carsUsersTwo);
//        store.addCarsUsers(carsUsersThree);
//        store.addCarsUsers(carsUsersFour);

        List<CarsUsers> carsUsers = store.findAllCarsUsers();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode record = mapper.createObjectNode();

        for (CarsUsers cu:carsUsers) {
            record.put("car" + cu.getCar().getId() + " ", mapper.createObjectNode()
                    .put("brand", cu.getCar().getModel().getBrand().getName())
                    .put("model", cu.getCar().getModel().getName())
                    .put("year", cu.getCar().getYear())
                    .put("price", cu.getCar().getPrice())
                        .set("user",  mapper.createObjectNode()
                                        .put("name", cu.getUser().getUsername())
                                        .put("phone", cu.getUser().getPhone()))
            );
        }

        System.out.println(record);

//        cars.add(carOne);
//        cars.add(carTwo);
//        userOne.setCars(cars);
//        store.createUser(userOne);
//        System.out.println("userOne id " + userOne.getId());
//        cars = store.findAllCars();
//
//        for (Car c:cars) {
//            System.out.println(c.getUsers());
//        }
//
//        JSONObject rsl = new JSONObject();
//
//        for (Car c:cars) {
//            rsl.put("model", c.getModel().getName());
//            rsl.put("brand", c.getModel().getBrand());
//            rsl.put("price", c.getPrice());
//            rsl.put("user name", c.getUsers());
//            rsl.put("user phone", c);
//        }

//        JSONArray jo = new JSONArray(cars);
//
//        String json = new Gson().toJson(cars);
//        System.out.println("JSON Object :" + json);
   }

}
