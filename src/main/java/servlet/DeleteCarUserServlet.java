package servlet;

import model.CarsUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class DeleteCarUserServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteCarUserServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("DeleteCarUserServlet's doPOST() called");
        req.setCharacterEncoding("UTF-8");
        try (BufferedReader reader = req.getReader()) {
            StringBuilder fullLine = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fullLine.append(line);
            }
            int id = Integer.parseInt(fullLine.toString());
            CarsUsers carsUsers = store.findCarsUsersById(id);
            store.deleteCarUser(carsUsers);
            LOG.debug("The add is deleted");
        } catch (IOException e) {
            LOG.error("Something goes wrong", e);
        }
    }
}
