package com.faceplusplus.spring.boot.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Skin-analysis options.
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
@Data
@Builder
public class SkinAnalyzeOptions {


	/**
	 * Controls whether face quality is enforced on the input image (0 or 1):
	 *
	 *     1: perform face quality control; returns INVALID_FACE_SIZE or INVALID_FACE_QUALITY when quality fails.
	 *     0: no face quality control; return skin-analysis results as long as a face is detected.
	 *
	 * Defaults to 0.
	 * Note: without quality control accuracy is not guaranteed; provide high-quality face images (see "image requirements" above).
	 */
	@JsonProperty("face_quality_control")
	private int faceQualityControl;

	/**
	 * Controls whether region confidence for acne, closed comedones, dark spots and moles is returned (0 or 1):
	 *
	 *     1: return region confidence.
	 *     0: do not return region confidence.
	 *
	 * Defaults to 0.
	 */
	@JsonProperty("return_rect_confidence")
	private int returnRectConfidence;

	/**
	 * Comma-separated string of skin-analysis map types to return. Supported elements:
	 *
	 * -------Element--------------------Description---------------------------Returned image--------------
	 * | red_area | red-area map showing sensitivity/inflammation | white-background red-area image, darker red means more sensitivity
	 * | brown_area | brown-area map showing facial pigmentation | white-background brown-area image, darker brown means more pigmentation
	 * | texture_enhanced_pores | enlarged-pore map | transparent PNG marking enlarged pores, same size as the original, overlayable
	 * | texture_enhanced_blackheads | blackhead map | transparent PNG marking blackheads, same size as the original, overlayable
	 * | texture_enhanced_oily_area | oily-area map | transparent PNG marking oily areas, same size as the original, overlayable
	 * | texture_enhanced_lines | facial texture map showing deep/shallow wrinkles | transparent PNG marking wrinkles, same size as the original, overlayable
	 *
	 * Fields may be extended in the future.
	 * Example: "red_area, brown_area".
	 * For each requested element the API returns an original-size image you can overlay on the source image.
	 * Defaults to an empty string.
	 */
	@JsonProperty("return_maps")
	private String returnMaps;

}
