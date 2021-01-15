package servlet;

import model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
public class AddServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("AddCarServlet's doGET() called");
        req.setCharacterEncoding("UTF-8");
        String imagePath = null;
        Map<String, String> mapFormValue = new HashMap<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField() && !item.getName().equals("")) {
                    LOG.debug("Item is : {}", item.getString());
                    File file = new File(folder + File.separator + item.getName());
                    LOG.debug("Folder of images is : {}", folder.getAbsolutePath());
                        imagePath = file.getName();
                        try (FileOutputStream out = new FileOutputStream(file)) {
                            out.write(item.getInputStream().readAllBytes());
                        }
                } else {
                   mapFormValue.put(item.getFieldName(), item.getString());
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        Model model = new Model(mapFormValue.get("input-model"));
        Brand brand = new Brand(mapFormValue.get("input-brand"));
        store.addBrand(brand);
        model.setBrand(brand);
        store.addModel(model);
        Engine engine = new Engine();
        engine.setEngineType(mapFormValue.get("input-engine"));
        engine.setCapacity(Integer.parseInt(mapFormValue.get("input-engine-capacity")));
        engine.setPower(Integer.parseInt(mapFormValue.get("input-engine-power")));
        store.addEngine(engine);
        Transmission transmission = new Transmission();
        transmission.setTransmissionType(mapFormValue.get("input-transmission"));
        store.addTransmission(transmission);
        Car car = new Car();
        car.setEngine(engine);
        car.setTransmission(transmission);
        car.setModel(model);
        car.setDescription(mapFormValue.get("car_desc"));
        car.setPrice(Integer.parseInt(mapFormValue.get("input-price")));
        car.setVin(Integer.parseInt(mapFormValue.get("input-vin")));
        car.setYear(Integer.parseInt(mapFormValue.get("input-year")));
        car.setColor(mapFormValue.get("color-picker"));
        car.setMileage(Integer.parseInt(mapFormValue.get("input-mileage")));
        car.setImagePath(imagePath);
        store.addCar(car);
        User user = (User) req.getSession().getAttribute("user");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        try {
            dt = sf.parse(sf.format(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CarsUsers carsUsers = new CarsUsers(dt, true, user, car);
        store.addCarsUsers(carsUsers);
        LOG.debug("AddCarServlet's doGET() is finished");
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

}
