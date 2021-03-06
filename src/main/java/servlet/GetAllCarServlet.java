package servlet;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.CarsUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import util.JSONBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetAllCarServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllCarServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("user", req.getSession().getAttribute("user"));
        LOG.debug("GetAllServlet's doGET() called");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        List<CarsUsers> carsUsers = store.findAllCarsUsers();
        ObjectNode record = new JSONBuilder().buildCarsUsersJSON(carsUsers);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(record);
        writer.flush();
        writer.close();
        LOG.debug("GetAllServlet finished");
        LOG.debug("JSON {}", record);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
