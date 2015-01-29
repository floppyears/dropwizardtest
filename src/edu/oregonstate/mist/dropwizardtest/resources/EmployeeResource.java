package edu.oregonstate.mist.dropwizardtest.resources;

import io.dropwizard.auth.Auth;
import edu.oregonstate.mist.dropwizardtest.auth.AuthenticatedUser;
import edu.oregonstate.mist.dropwizardtest.core.Employee;
import edu.oregonstate.mist.dropwizardtest.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @GET
    @Timed
    @Path("{id: \\d+}")
    public Employee getById(@PathParam("id") Integer id) {
        final Employee employee = DropwizardTestApplication.database.getEmployeeById(id);

        if (employee == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        } else {
            return employee;
        }
    }

    @POST
    @Metered
    @Path("{id: \\d+}")
    public Response setById(@PathParam("id") Integer id, String requestBody) {
        return Response.ok(requestBody).build();
    }

    @GET
    @Path("{id: \\d+}/OnidLoginId")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOnidLoginIdById(@PathParam("id") Integer id, @Auth AuthenticatedUser user) {
        final Employee employee = DropwizardTestApplication.database.getEmployeeById(id);

        if (employee == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        } else {
            return employee.getOnidLoginId();
        }
    }
}
