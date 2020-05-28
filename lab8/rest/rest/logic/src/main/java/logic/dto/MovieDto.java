package logic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
       value = "Movie"
)
public class MovieDto {


    @ApiModelProperty(value = "Movie title", example = "The Shawshank redemption")
    private String title;
    @ApiModelProperty(value = "url to movie on IMDb", example = "https://www.imdb.com/title/tt0111161/?ref_=nv_sr_srsg_0")
    private String url;

    public MovieDto() {
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
