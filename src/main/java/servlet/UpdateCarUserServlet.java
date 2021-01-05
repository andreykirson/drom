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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateCarUserServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateCarUserServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // add delete old image in temp folder
        LOG.debug("UpdateCarUserServlet's doGET() called");
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
                if (!item.isFormField()) {
                    File file = new File(folder + File.separator + item.getName());
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
        CarsUsers carsUsers = store.findCarsUsersById(Integer.parseInt(mapFormValue.get("carUserId")));
        Car car = carsUsers.getCar();
        Model model = car.getModel();
        Brand brand = model.getBrand();
        Engine engine = car.getEngine();
        Transmission transmission = car.getTransmission();
        model.setName(mapFormValue.get("input-model"));
        brand.setName(mapFormValue.get("input-brand"));
        store.updateBrand(brand);
        model.setBrand(brand);
        store.updateModel(model);
        engine.setEngineType(mapFormValue.get("input-engine"));
        engine.setCapacity(Integer.parseInt(mapFormValue.get("input-engine-capacity")));
        engine.setPower(Integer.parseInt(mapFormValue.get("input-engine-power")));
        store.updateEngine(engine);
        transmission.setTransmissionType(mapFormValue.get("input-transmission"));
        store.updateTransmission(transmission);
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
        store.update(car);
        User user = (User) req.getSession().getAttribute("user");
        carsUsers = new CarsUsers(new Date(), Boolean.parseBoolean(mapFormValue.get("status-switch")), user, car);
        LOG.debug("UpdateCarUserServlet's doPOST() is finished");
        resp.sendRedirect(req.getContextPath() + "/usercars.jsp");
    }
}
