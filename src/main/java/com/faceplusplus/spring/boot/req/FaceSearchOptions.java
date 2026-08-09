package com.faceplusplus.spring.boot.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Face-analysis options.
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
@Data
@Builder
public class FaceSearchOptions {

	/**
	 * Whether to detect and return face landmarks. Valid values:
	 * 2: detect; returns 106 face landmarks.
	 * 1: detect; returns 83 face landmarks.
	 * 0: detect.
	 * Note: defaults to 0.
	 */
	@JsonProperty("return_landmark")
    private int returnLandmark;

	/**
	 * Whether to detect and return attributes such as age, gender and emotion. Valid values:
	 * none: do not detect attributes.
	 *
	 *     gender
	 *     age
	 *     smiling
	 *     headpose
	 *     facequality
	 *     blur
	 *     eyestatus
	 *     emotion
	 *     beauty
	 *     mouthstatus
	 *     eyegaze
	 *     skinstatus
	 *     nose_occlusion
	 *     chin_occlusion
	 *     face_occlusion
	 *
	 * Attributes to detect and return.
	 *
	 * Provide attributes as a comma-separated string (order does not matter).
	 *
	 * See the "attributes" section below for attribute details.
	 *
	 * Note: the input attribute "smiling" maps to the response attribute "smile".
	 *
	 * Note: defaults to none.
	 */
	@JsonProperty("return_attributes")
	private String returnAttributes = "none";

	/**
	 * Minimum beauty-score value. Defaults to 0.
	 * Note: beauty score defaults to [0,100]; adjust with beauty_score_min/beauty_score_max.
	 */
	@JsonProperty("beauty_score_min")
	private int beautyScoreMin = 0;

	/**
	 * Maximum beauty-score value. Defaults to 100.
	 */
	@JsonProperty("beauty_score_max")
	private int beautyScoreMax = 100;

}
