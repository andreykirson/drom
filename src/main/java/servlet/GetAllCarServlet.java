package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Car;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class GetAllCarServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllCarServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("user", req.getSession().getAttribute("user"));
        LOG.debug("GetAllServlet's doGET() called");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Collection<Car> cars = store.findAllCars();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String json = new Gson().toJson(cars.toString());
        writer.println(json);
        writer.flush();
        writer.close();
        LOG.debug("GetAllServlet finished");
        LOG.debug("JSON {}", json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
