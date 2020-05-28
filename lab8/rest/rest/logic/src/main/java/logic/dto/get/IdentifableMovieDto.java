package logic.dto.get;

import io.swagger.annotations.ApiModelProperty;
import logic.dto.MovieDto;

import java.util.UUID;

public class IdentifableMovieDto extends MovieDto {

    @ApiModelProperty(value = "id of movie")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
