package logic.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

@ApiModel(
        value = "User"
)
public class UserDto {

    @ApiModelProperty(value = "User id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = false)
    private UUID id;

    @ApiModelProperty(value = "User name", example = "James", required = true)
    private String name;

    @ApiModelProperty(value = "User age", example = "22")
    private int age;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
