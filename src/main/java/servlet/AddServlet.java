package servlet;

import com.google.common.io.ByteStreams;
import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
public class AddServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        Part filePart = req.getPart("add-photo"); // Retrieves <input type="file" name="file">
        InputStream fileContent = filePart.getInputStream();
        Model model = new Model(req.getParameter("input-model"));
        System.out.println(req.getParameter("input-model"));
        Brand brand = new Brand(req.getParameter("input-brand"));
        System.out.println(req.getParameter("input-brand"));
        store.addModel(model);
        store.addBrand(brand);
        model.setBrand(brand);
        Engine engine = new Engine();
        engine.setEngineType(req.getParameter("input-engine"));
        engine.setCapacity(Integer.parseInt(req.getParameter("input-engine-capacity")));
        engine.setPower(Integer.parseInt(req.getParameter("input-engine-power")));
        store.addEngine(engine);
        Transmission transmission = new Transmission();
        transmission.setTransmissionType(req.getParameter("input-transmission"));
        store.addTransmission(transmission);
        Car car = new Car();
        car.setEngine(engine);
        car.setTransmission(transmission);
        car.setModel(model);
        car.setDescription(req.getParameter("car_desc"));
        car.setPrice(Integer.parseInt(req.getParameter("input-price")));
        car.setYear(Integer.parseInt(req.getParameter("input-year")));
        car.setColor(req.getParameter("color-picker"));
        car.setMileage(Integer.parseInt(req.getParameter("input-mileage")));
        byte[] bytes = ByteStreams.toByteArray(fileContent);
        car.setImage(bytes);
        store.addCar(car);
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        User user = (User) req.getSession().getAttribute("user");
        user.setCars(cars);
        store.updateUser(user);
        resp.sendRedirect(req.getContextPath() + "/index.do");
    }

}
