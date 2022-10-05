package ge.ufc.webapps;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/hello")
public class HelloService {

    @GET
    @Path("/sayHello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(@DefaultValue("World") @QueryParam("to") String to) {
        String responseData = "Hello " + to;
        Response respnose = Response.status(Status.OK).entity(responseData).build();
        return respnose;
    }

    @GET
    @Path("/addTwoNumbers/{first}/{second}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addTwoNumbers(@PathParam("first") int first, @PathParam("second") int second) {
        int result = first + second;
        Response respnose = Response.status(Status.OK).entity(result).build();
        return respnose;
    }

    @GET
    @Path("/whoAmI/{firstName}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response whoAmI(@PathParam("firstName") String firstName, @QueryParam("lastName") String lastName,
                           @Context HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Your name: ");
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        sb.append(" Your IP: ");
        sb.append(request.getRemoteAddr());
        Response respnose = Response.status(Status.OK).entity(sb.toString()).build();
        return respnose;
    }

    @GET
    @Path("/guessNumber/{username}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response guessNumber(@PathParam("username") String username, @PathParam("password") String password,
                                @QueryParam("number") int number) {

        if (username == null || password == null) {
            return Response.status(Status.UNAUTHORIZED).entity("Username or password was not passed").build();
        }

        if (!"admin".equals(username) || !"123".equals(password)) {
            return Response.status(Status.FORBIDDEN).entity("Incorrect username or password").build();
        }

        if (number < 0 || number > 10) {
            return Response.status(Status.BAD_REQUEST).entity("Incorrect number range").build();
        }

        if (number == 7) {
            return Response.status(Status.OK).entity("Guessed!").build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("not guessed").build();
        }
    }

    @GET
    @Path("/calculateBMI")
    @Produces(MediaType.TEXT_PLAIN)
    public Response calculateBMI(@HeaderParam("height") double height, @HeaderParam("weight") double weight) {
        double bmi = weight / (height * height);
        return Response.status(Status.OK).header("BMI", bmi).entity("").build();
    }

}
