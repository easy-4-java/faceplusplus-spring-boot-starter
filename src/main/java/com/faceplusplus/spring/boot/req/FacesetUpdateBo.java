package com.faceplusplus.spring.boot.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * FaceSet collection.
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
@Data
/**
 * Model class for FacesetUpdateBo.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FacesetUpdateBo {


	/**
	 * The FaceSet token (faceset_token).
	 */
	@JsonProperty("faceset_token")
	private String faceToken;

	/**
	 * Globally-unique custom FaceSet id (up to 255 chars; ^@,&=*'" are not allowed).
	 */
	@JsonProperty("outer_id")
	private String outerId;

	/**
	 * Globally-unique custom FaceSet id (up to 255 chars; ^@,&=*'" are not allowed).
	 */
	@JsonProperty("new_outer_id")
	private String newOuterId;

	/**
	 * FaceSet display name (up to 256 chars; ^@,&=*'" are not allowed).
	 */
	@JsonProperty("display_name")
	private String displayName;

	/**
	 * Comma-separated custom tags used to group FaceSets (up to 255 chars; ^@,&=*'" are not allowed in each tag).
	 */
	@JsonProperty("tags")
	private String tags;

	/**
	 * Custom user data (up to 16 KB; ^@,&=*'" are not allowed).
	 */
	@JsonProperty("user_data")
	private String userData;

}
