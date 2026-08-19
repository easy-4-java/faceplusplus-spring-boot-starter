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
 * <p>Auto-configuration for FacesetDeleteBo.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class FacesetDeleteBo {

	/**
	 * Globally-unique custom FaceSet id (up to 255 chars; ^@,&=*'" are not allowed).
	 */
	@JsonProperty("outer_id")
	private String outerId;

	/**
	 * Comma-separated string of one or more face_tokens (up to 5).
	 */
	@JsonProperty("face_tokens")
	private String faceTokens;

	/**
	 * Whether to check the face_token exists before removal; defaults to 1.
	 * 0: do not check.
	 * 1: check.
	 * When set to 1, removal fails if the face_token exists in the FaceSet.
	 */
	@JsonProperty("check_empty")
	private int checkEmpty = 1;

}
