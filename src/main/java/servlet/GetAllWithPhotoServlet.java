package servlet;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.CarsUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import util.JSONBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetAllWithPhotoServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllWithPhotoServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("GetAllWithPhotoServlet's doGet() called");
        resp.setContentType("application/json");
            List<CarsUsers> carsUsers;
            carsUsers = store.getAllWithPhoto();
            ObjectNode record = new JSONBuilder().buildCarsUsersJSON(carsUsers);
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.println(record);
            writer.flush();
            writer.close();
            LOG.debug("GetAllWithPhotoServlet finished");
            LOG.debug("JSON {}", record);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
