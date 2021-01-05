package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.CarsUsers;

import java.util.List;

public class JSONBuilder {

    private ObjectMapper mapper = new ObjectMapper();
    private ObjectNode record = mapper.createObjectNode();

   public  ObjectNode buildCarsUsersJSON(List<CarsUsers> carsUsers) {
        for (CarsUsers cu : carsUsers) {
            record.put("car" + cu.getCar().getId() + " ", mapper.createObjectNode()
                    .put("id", cu.getId())
                    .put("date", String.valueOf(cu.getCreatedTime()))
                    .put("brand", cu.getCar().getModel().getBrand().getName())
                    .put("model", cu.getCar().getModel().getName())
                    .put("year", cu.getCar().getYear())
                    .put("price", cu.getCar().getPrice())
                    .put("imagePath", cu.getCar().getImagePath())
                    .put("status", cu.getSoldStatus())
                    .set("user", mapper.createObjectNode()
                            .put("name", cu.getUser().getUsername())
                            .put("phone", cu.getUser().getPhone()))
            );
        }
       return record;
    }

}
