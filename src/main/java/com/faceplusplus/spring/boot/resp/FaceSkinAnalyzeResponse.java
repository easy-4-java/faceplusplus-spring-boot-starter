/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.faceplusplus.spring.boot.resp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude( JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
/**
 * Model class for FaceSkinAnalyzeResponse.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FaceSkinAnalyzeResponse extends FaceppResponse {

	/**
	 * Face rectangle; integer pixel coordinates.
	 * top: y-coordinate of the rectangle's top-left corner.
	 * left: x-coordinate of the rectangle's top-left corner.
	 * width: rectangle width.
	 * height: rectangle height.
	 */
	@JsonProperty("face_rectangle")
	private FaceRectangle faceRectangle;

	/**
	 * 2. Skin-analysis results (see the table below for fields).
	 * https://console.faceplusplus.com.cn/documents/307316314
	 */
	@JsonProperty("result")
	private JSONObject result;

	/**
	 * 3. Factors that may affect the result.
	 * Possible influencing factors:
	 *     improper_headpose: head pose out of range (roll/yaw/pitch outside [-45,45]).
	 * Returned when an influencing factor exists: ["improper_headpose"].
	 * Returned when there are no influencing factors: [].
	 */
	@JsonProperty("warning")
	private JSONArray warning;

}
