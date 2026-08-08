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
 * Model class for FacesetBo.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FacesetBo {

	/**
	 * FaceSet display name (up to 256 chars; ^@,&=*'" are not allowed).
	 */
	@JsonProperty("display_name")
    private String displayName;

	/**
	 * Globally-unique custom FaceSet id (up to 255 chars; ^@,&=*'" are not allowed).
	 */
	@JsonProperty("outer_id")
	private String outerId;

	/**
	 * Comma-separated custom tags used to group FaceSets (up to 255 chars; ^@,&=*'" are not allowed in each tag).
	 */
	@JsonProperty("tags")
	private String tags;

	/**
	 * Comma-separated string of one or more face_tokens (up to 5).
	 */
	@JsonProperty("face_tokens")
	private String faceTokens;

	/**
	 * Custom user data (up to 16 KB; ^@,&=*'" are not allowed).
	 */
	@JsonProperty("user_data")
	private String userData;

	/**
	 * When outer_id is provided and already exists, whether to add face_tokens to the existing FaceSet.
	 * 0: do not add face_tokens to an existing FaceSet; return FACESET_EXIST.
	 * 1: add face_tokens to the existing FaceSet.
	 * Defaults to 0.
	 */
	@JsonProperty("force_merge")
	private int forceMerge;

}
