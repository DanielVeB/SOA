package app.controller;

import io.swagger.annotations.Api;
import logic.dto.UserDto;
import logic.service.UsersService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/movies")
@Api(value = "/movies", tags = {"movies"})
public class MovieController {

    @Inject
    private UsersService usersService;

    @Inject
    private Logger logger;

    @GET
    @Produces({"application/json"})
    public Response getUsers(@DefaultValue("0") @QueryParam("offset") int offset,
                             @DefaultValue("10") @QueryParam("limit") int limit) {
        logger.info("Get users");
        List<UserDto> userDtos = usersService.getUsers(offset, limit);
        return Response.ok().entity(userDtos).build();
    }
}
