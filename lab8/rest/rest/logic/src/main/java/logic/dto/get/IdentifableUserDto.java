package logic.dto.get;

import io.swagger.annotations.ApiModelProperty;
import logic.dto.UserDto;

import java.util.UUID;

public class IdentifableUserDto extends UserDto {

    @ApiModelProperty(value = "User id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
