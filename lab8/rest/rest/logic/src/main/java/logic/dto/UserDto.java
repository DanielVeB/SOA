package logic.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        value = "User"
)
public class UserDto {


    @ApiModelProperty(value = "User name", example = "James")
    private String name;

    @ApiModelProperty(value = "User age", example = "22")
    private int age;



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
