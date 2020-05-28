package app.controller.api;

import app.util.Avatar;
import app.util.PATCH;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import logic.dto.UserDto;
import logic.dto.get.IdentifableUserDto;
import logic.exception.InvalidIdException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Path("/users")
@Api(value = "/users", tags = {"users"})
public interface IUserApi {

    @GET
    @Produces({"application/json"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = " ", response = IdentifableUserDto.class, responseContainer = "List")
            }
    )
    Response getUsers(@DefaultValue("0") @QueryParam("offset") int offset,
                      @DefaultValue("10") @QueryParam("limit") int limit);


    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "user created", response = UserDto.class)
            }
    )
    Response createUser(UserDto userDto);

    @GET
    @Path("/{userId}")
    @Produces({"application/json"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "get user", response = IdentifableUserDto.class),
                    @ApiResponse(code = 404, message = "user not found", response = NotFoundException.class),
                    @ApiResponse(code = 400, message = "invalid id ", response = InvalidIdException.class)

            }
    )
    Response getUserById(@ApiParam(value = "user id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                         @PathParam("userId") String userId);

    @PUT
    @Path("/{userId}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "update user", response = IdentifableUserDto.class),
                    @ApiResponse(code = 404, message = "user not found", response = NotFoundException.class),
                    @ApiResponse(code = 400, message = "invalid id ", response = InvalidIdException.class)

            }
    )
    Response updateUser(@ApiParam(value = "user id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                        @PathParam("userId") String userId, UserDto userDto);

    @PATCH
    @Path("/{userId}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "patch user", response = IdentifableUserDto.class),
                    @ApiResponse(code = 404, message = "user not found", response = NotFoundException.class),
                    @ApiResponse(code = 400, message = "invalid id ", response = InvalidIdException.class)

            }
    )
    Response patchUser(@ApiParam(value = "user id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                       @PathParam("userId") String userId, Map<String, String> updates);

    @DELETE
    @Path("/{userId}")
    @Produces({"application/json"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "User removed"),
                    @ApiResponse(code = 404, message = "user not found", response = NotFoundException.class),
                    @ApiResponse(code = 400, message = "invalid id ", response = InvalidIdException.class)

            }
    )
    Response removeUser(@ApiParam(value = "user id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                        @PathParam("userId") String userId);

    @PUT
    @Path("/{userId}/avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({"application/json"})
    Response updateUserAvatar(@ApiParam(value = "user id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                              @PathParam("userId") String userId,
                              @MultipartForm Avatar avatar);

    @GET
    @Path("/{userId}/avatar")
    @Produces({"application/json", "image/png"})
    Response getUserAvatar(@ApiParam(value = "user id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                           @PathParam("userId") String userId) throws IOException;

    @DELETE
    @Path("/{userId}/avatar")
    @Produces({"application/json"})
    Response removeUserAvatar(@ApiParam(value = "user id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                              @PathParam("userId") String userId);
}
