package app.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import logic.dto.get.IdentifableUserDto;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/osoby")
@Api(value = "/osoby", tags = {"redirect"})
public interface IRedirectApi {

    @GET
    @Produces({"application/json"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 303, message = "Redirect to /users"),
                    @ApiResponse(code = 200, message = " ", response = IdentifableUserDto.class, responseContainer = "List")
            }
    )
    Response getUsers(@DefaultValue("0") @QueryParam("offset") int offset,
                      @DefaultValue("10") @QueryParam("limit") int limit);
}
