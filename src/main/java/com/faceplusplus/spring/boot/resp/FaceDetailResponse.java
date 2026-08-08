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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude( JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
/**
 * Model class for FaceDetailResponse.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FaceDetailResponse extends FaceppResponse {

	/**
	 * System id of the image the face_token belongs to.
	 */
	@JsonProperty("image_id")
	private String imageId;

	/**
	 * 2. The face_token.
	 */
	@JsonProperty("face_token")
	private String faceToken;

	/**
	 * 3、User-defined identifier information.
	 */
	@JsonProperty("user_id")
	private String userId;

	/**
	 * 3. Face rectangle; integer pixel coordinates.
	 * top: y-coordinate of the rectangle's top-left corner.
	 * left: x-coordinate of the rectangle's top-left corner.
	 * width: rectangle width.
	 * height: rectangle height.
	 */
	@JsonProperty("face_rectangle")
	private FaceRectangle faceRectangle;

	/**
	 * 5. FaceSets that contain this face_token.
	 * faceset_token：The FaceSet token (faceset_token).
	 * outer_id: user-defined FaceSet id; empty when not set.
	 * tags: user-defined FaceSet tags; empty when not set.
	 */
	@JsonProperty("facesets")
	private List<FaceSetFetail> facesets;


}
