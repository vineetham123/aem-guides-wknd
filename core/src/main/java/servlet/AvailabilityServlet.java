package servlet;

import com.drew.lang.annotations.NotNull;
import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
// * idempotent. For write operations use the {@link /SlingAllMethodsServlet}.
 */
@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes = {"/wknd/components/page"},
        methods = HttpConstants.METHOD_GET,
        selectors = {"availability"},
        extensions = "json")
@ServiceDescription("Samthosh Vegetables - Availability Servlet")
public class AvailabilityServlet extends SlingSafeMethodsServlet {

    transient Logger LOG = LoggerFactory.getLogger(AvailabilityServlet.class);

    @Override
    protected void doGet(@NotNull final SlingHttpServletRequest req,
                         @NotNull final SlingHttpServletResponse resp) throws IOException {

        String[] oneSiteIDs = req.getParameterMap().get("vegId");
        if (oneSiteIDs == null || oneSiteIDs.length == 0) {
            resp.getWriter().print("vegId  parameter is required");
            resp.setStatus(HttpStatus.SC_BAD_REQUEST);
            return;
        }

        LOG.info("units=>"+"units hard coded");
        LOG.info("Units as JSON="+new Gson().toJson("myunits"));
        resp.setContentType("application/json");
        resp.getWriter().print("myunits");
        resp.setStatus(HttpStatus.SC_OK);
    }
}
