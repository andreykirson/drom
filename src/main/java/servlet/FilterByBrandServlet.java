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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FilterByBrandServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(FilterByBrandServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("FilterByBrandServlet's doGet() called");
        req.setCharacterEncoding("UTF-8");
        try (BufferedReader reader = req.getReader()) {
            StringBuilder fullLine = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fullLine.append(line);
            }
            List<CarsUsers> carsUsers;
            if (fullLine.toString().equals("All")) {
                carsUsers = store.findAllCarsUsers();
            } else {
                carsUsers = store.getAllByBrand(fullLine.toString());
            }
            ObjectNode record = new JSONBuilder().buildCarsUsersJSON(carsUsers);
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.println(record);
            writer.flush();
            writer.close();
            LOG.debug("FilterByBrandServlet finished");
            LOG.debug("The selected brand is {}", fullLine.toString());
            LOG.debug("JSON {}", record);
        }
    }

}
