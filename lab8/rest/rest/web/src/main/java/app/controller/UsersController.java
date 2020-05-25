package app.controller;

import app.util.Avatar;
import io.swagger.annotations.Api;
import logic.dto.UserDto;
import logic.service.UsersService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Path("/users")
@Api(value = "/users", tags = {"user"})
public class UsersController {


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


    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response createUser(UserDto userDto) {
        logger.info("Create new user");
        UserDto createdUser = usersService.createUser(userDto);
        return Response.status(201).entity(createdUser).build();
    }

    @GET
    @Path("/{userId}/info")
    @Produces({"application/json"})
    public Response getUserById(@PathParam("userId") String userId) {
        logger.info("Get user by id {}", userId);
        UserDto userDto = usersService.getUser(userId);
        return Response.ok(userDto).build();
    }

    @PUT
    @Path("/{userId}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response updateUser(@PathParam("userId") String userId, UserDto userDto) {
        logger.info("Update user with id {}", userId);
        return Response.ok(userDto).build();
    }

    @DELETE
    @Path("/{userId}")
    @Produces({"application/json"})
    public Response removeUser(@PathParam("userId") String userId) {
        logger.info("remove user with id {}", userId);
        usersService.removeUser(userId);
        return Response.status(204).build();
    }

//    =================================================================


    @POST
    @Path("/{userId}/avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateUserAvatar(@PathParam("userId") String userId,
                                     @MultipartForm Avatar avatar) {

        usersService.updateUserAvatar(userId, avatar.getData());
        return Response.status(204).build();
    }

    @GET
    @Path("/{userId}/avatar")
    @Produces({"image/png", "image/jpg"})
    public Response getUserAvatar(@PathParam("userId") String userId) throws IOException {
        byte[] b_avatar = usersService.getUserAvatar(userId);
        File avatar = new File("avatar.png");
        FileOutputStream fos = new FileOutputStream(avatar);
        fos.write(b_avatar);
        fos.flush();
        fos.close();
        return Response.status(200).entity(avatar).header(
                "Content-Disposition", "attachment; filename=\"avatar.png\""
        ).build();
    }

    @DELETE
    @Path("/{userId}/avatar")
    @Produces({"application/json"})
    public Response removeUserAvatar(@PathParam("userId") String userId) {
        logger.info("Remove avatar for user with id {}", userId);
        return Response.status(204).build();
    }
//===========================================

    @GET
    @Path("/{userId}/movies")
    @Produces({"application/json"})
    public Response getUserMovies(@PathParam("userId") String userId) {
        logger.info("Get" + userId);
//        throw new UserNotFoundException("xdd");
//        UserDto userDto = usersService.getUserById(UUID.randomUUID());
//        return Response.ok(userDto).build();
        return null;
    }
}
