package com.faceplusplus.spring.boot.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaceSetFetail {

    /**
     * The FaceSet token (faceset_token).
     */
    @JsonProperty("faceset_token")
    private String facesetToken;

    /**
     * Comma-separated custom tags used to group FaceSets (up to 255 chars; ^@,&=*'" are not allowed in each tag).
     */
    @JsonProperty("tags")
    private String tags;

    /**
     * User-defined FaceSet id (outer_id); empty when not set.
     */
    @JsonProperty("outer_id")
    private String outerId;

}
