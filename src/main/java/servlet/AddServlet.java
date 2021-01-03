package servlet;

import com.google.common.io.ByteStreams;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
        Brand brand = new Brand(req.getParameter("input-brand"));
        store.addBrand(brand);
        model.setBrand(brand);
        store.addModel(model);
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
        car.setVin(Integer.parseInt(req.getParameter("input-vin")));
        car.setYear(Integer.parseInt(req.getParameter("input-year")));
        car.setColor(req.getParameter("color-picker"));
        car.setMileage(Integer.parseInt(req.getParameter("input-mileage")));
        byte[] bytes = ByteStreams.toByteArray(fileContent);

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
                    car.setImagePath(file.getName());
                    System.out.println("Path :  " + file.getName());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        car.setImage(bytes);
        store.addCar(car);
        User user = (User) req.getSession().getAttribute("user");
        CarsUsers carsUsers = new CarsUsers(new Date(), false, user, car);
        store.addCarsUsers(carsUsers);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

}
