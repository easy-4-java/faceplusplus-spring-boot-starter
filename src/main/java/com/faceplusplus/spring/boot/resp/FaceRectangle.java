package com.faceplusplus.spring.boot.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaceRectangle {

    /**
     * y-coordinate of the rectangle's top-left corner.
     */
    @JsonProperty("top")
    private Integer top;

    /**
     * x-coordinate of the rectangle's top-left corner.
     */
    @JsonProperty("left")
    private Integer left;

    /**
     * Rectangle width.
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Rectangle height.
     */
    @JsonProperty("height")
    private Integer height;

}
