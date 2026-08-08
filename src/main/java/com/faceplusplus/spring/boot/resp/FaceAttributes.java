package com.faceplusplus.spring.boot.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * Model class for FaceAttributes.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FaceAttributes {

    /**
     * Gender analysis result. Values:
     * Male.
     * Female.
     */
    @JsonProperty("gender")
    private FaceAttrValue gender;

    /**
     * Age analysis result (a non-negative integer).
     */
    @JsonProperty("age")
    private FaceAttrValue age;

    /**
     * Smile analysis result. Contains:
     *     value: float in [0,100] (3 decimals); higher means a bigger smile.
     *     threshold: smile threshold above which a smile is detected.
     */
    @JsonProperty("smile")
    private FaceAttrSmile smile;

    /**
     * Head-pose analysis result. Each attribute is a float in [-180,180] (6 decimals) in degrees.
     * pitch_angle: pitch (looking up/down).
     * roll_angle: roll (in-plane rotation).
     * yaw_angle: yaw (turning left/right).
     */
    @JsonProperty("headpose")
    private FaceAttrHeadpose headpose;

    /**
     * Face blur analysis result. Contains:
     *     blurness: face blur analysis result.
     * Each attribute contains the following fields:
     *     value: float in [0,100] (3 decimals); higher means more blurred.
     *     threshold: blur threshold above which recognition is affected.
     */
    @JsonProperty("blur")
    private FaceAttrBlur blur;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FaceAttrValue {

        @JsonProperty("value")
        private String value;

    }


    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FaceAttrSmile {

        /**
         * Float in [0,100] (3 decimals); higher means a bigger smile.
         */
        @JsonProperty("value")
        private Float value;

        /**
         * Smile threshold above which a smile is detected.
         */
        @JsonProperty("threshold")
        private Float threshold;

    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FaceAttrHeadpose {

        /**
         * Yaw angle (turning left/right).
         */
        @JsonProperty("yaw_angle")
        private Float yaw;

        /**
         * Pitch angle (looking up/down).
         */
        @JsonProperty("pitch_angle")
        private Float pitch;

        /**
         * Roll angle (in-plane rotation).
         */
        @JsonProperty("roll_angle")
        private Float roll;

    }


    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FaceAttrBlur {

        /**
         * Yaw angle (turning left/right).
         */
        @JsonProperty("yaw_angle")
        private Float yaw;

        /**
         * Pitch angle (looking up/down).
         */
        @JsonProperty("pitch_angle")
        private Float pitch;

        /**
         * Roll angle (in-plane rotation).
         */
        @JsonProperty("roll_angle")
        private Float roll;

    }

}
