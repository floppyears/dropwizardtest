package edu.oregonstate.mist.dropwizardtest.resources;

import edu.oregonstate.mist.dropwizardtest.core.Employee;
import edu.oregonstate.mist.dropwizardtest.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

@Path("/api/v1/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    public EmployeeResource() {
    }

    @GET
    @Path("{id: \\d+}")
    public Employee getById(@PathParam("id") Integer id) {
        final Employee employee = DropwizardTestApplication.database.getEmployeeById(id);

        if (employee == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        } else {
            return employee;
        }
    }
}
