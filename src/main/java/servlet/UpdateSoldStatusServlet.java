package servlet;

import model.CarsUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UpdateSoldStatusServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateSoldStatusServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("UpdateSoldStatusServlet's doPOST() called");
        req.setCharacterEncoding("UTF-8");
        try (BufferedReader reader = req.getReader()) {
            StringBuilder fullLine = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fullLine.append(line);
            }
            System.out.println(fullLine.toString());
            String[] str = fullLine.toString().split("\\s+");
            boolean status = Boolean.parseBoolean(str[1]);
            int id = Integer.parseInt(String.valueOf(str[0].split(":")[1]));
            CarsUsers carsUsers = store.findCarsUsersById(id);
            carsUsers.setSoldStatus(status);
            store.updateCarsUsers(carsUsers);
            LOG.debug("The status of ad is updated");
        } catch (IOException e) {
            LOG.error("Something goes wrong", e);
        }
    }
}
