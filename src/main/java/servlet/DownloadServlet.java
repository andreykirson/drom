package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DownloadServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("DownloadServlet's doGET() called");
        String name = req.getParameter("name");
        resp.setContentType("name=" + name);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        File file = new File("images" + File.separator + name);
        try (
                FileInputStream in = new FileInputStream(file)) {
            resp.getOutputStream().write(in.readAllBytes());
        }
    }
}
