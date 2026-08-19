package com.faceplusplus.spring.boot.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Face-recognition options.
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
@Data
@Builder
/**
 * <p>Auto-configuration for FaceDetectOptions.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class FaceDetectOptions {

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
	 * Whether to return landmarks/attributes for all faces. When disabled only the 5 largest faces are analysed. Valid values:
	 * 1: yes.
	 * 0: no.
	 * Note: defaults to 0.
	 */
	@JsonProperty("calculate_all")
	private int calculateAll;

	/**
	 * Whether to detect faces within a specified rectangle.
	 * When this parameter is empty or omitted the API detects all faces in the whole image.
	 * When a production API key provides a valid face rectangle string, the API detects faces only within that rectangle and returns matching landmarks/attributes; the returned rectangle equals the input. No detection is performed outside the rectangle.
	 * Format: four comma-separated positive integers - top, left, width, height (e.g. 70,80,100,100).
	 */
	@JsonProperty("face_rectangle")
	private String faceRectangle;

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
