package app.controller;

import app.controller.api.IUserApi;
import app.util.Avatar;
import logic.dto.UserDto;
import logic.dto.get.IdentifableUserDto;
import logic.service.IUserService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class UsersController implements IUserApi {


    @Inject
    private IUserService usersService;

    @Inject
    private Logger logger;


    @Override
    public Response getUsers(int offset, int limit) {
        logger.info("Get users, offset: {}, limit {}", offset, limit);
        List<IdentifableUserDto> userDtos = usersService.getUsers(offset, limit);
        return Response.ok().entity(userDtos).build();
    }

    @Override
    public Response createUser(UserDto userDto) {
        logger.info("Create new user");
        UserDto createdUser = usersService.createUser(userDto);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @Override
    public Response getUserById(String userId) {
        logger.info("Get user by id {}", userId);
        UserDto userDto = usersService.getUser(userId);
        return Response.ok(userDto).build();

    }


    @Override
    public Response updateUser(String userId, UserDto userDto) {
        logger.info("Update user with id {}", userId);
        IdentifableUserDto updated = usersService.updateUser(userId, userDto);
        return Response.ok(updated).build();
    }

    @Override
    public Response patchUser(String userId, Map<String, String> updates) {
        logger.info("Patch user with id {}", userId);
        IdentifableUserDto updated = usersService.patchUser(userId, updates);
        return Response.ok(updated).build();
    }


    @Override
    public Response removeUser(String userId) {
        logger.info("remove user with id {}", userId);
        usersService.removeUser(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

//    =================================================================


    @Override
    public Response updateUserAvatar(String userId, Avatar avatar) {
        usersService.updateUserAvatar(userId, avatar.getData());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Override
    public Response getUserAvatar(String userId) throws IOException {
        byte[] b_avatar = usersService.getUserAvatar(userId);
        File avatar = prepareFile(b_avatar);
        return Response.status(200).entity(avatar).header(
                "Content-Disposition", "attachment; filename=\"avatar.png\""
        ).build();
    }


    @Override
    public Response removeUserAvatar(String userId) {
        logger.info("Remove avatar for user with id {}", userId);
        usersService.removeUserAvatar(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private File prepareFile(byte[] data) throws IOException {
        File avatar = new File("avatar.png");
        FileOutputStream fos = new FileOutputStream(avatar);
        fos.write(data);
        fos.flush();
        fos.close();
        return avatar;
    }
}
