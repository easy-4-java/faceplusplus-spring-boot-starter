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
 * Model class for FaceSearchResponse.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FaceSearchResponse extends FaceppResponse {

	/**
	 * Array of search-result objects.
	 * Note: not returned when the input image contains no detected face (search).
	 */
	@JsonProperty("results")
	private JSONArray results;

	/**
	 * 2. Reference confidence thresholds (three fields); each is a float in [0,100] (3 decimals).
	 *     1e-3: confidence threshold at 0.1% false-accept rate.
	 *     1e-4: confidence threshold at 0.01% false-accept rate.
	 *     1e-5: confidence threshold at 0.001% false-accept rate.
	 * Confidence below the 0.1% threshold suggests different people; above the 0.001% threshold strongly suggests the same person.
	 * Note: thresholds are not static and may differ between calls; do not persist them or compare a confidence against a previously returned threshold.
	 * Note: not returned when the input image contains no detected face (compare).
	 */
	@JsonProperty("thresholds")
	private JSONObject thresholds;

	/**
	 * 3. System id of the image provided via image_url/image_file/image_base64.
	 * Note: not returned when no image is provided.
	 */
	@JsonProperty("image_id")
	private String imageId;

	/**
	 * 4. Faces detected in the input image; the first one is used for the search.
	 * Note: not returned when no image is provided; empty array when no face is detected.
	 */
	@JsonProperty("faces")
	private JSONArray faces;

}
