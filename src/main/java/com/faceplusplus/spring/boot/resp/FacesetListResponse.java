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
 * Model class for FacesetListResponse.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FacesetListResponse extends FaceppResponse {

	/**
	 * Cursor for the next request; the index of the next faceset_token after those returned.
	 * When present, more faceset_tokens remain; pass this value as start in the next call to fetch them.
	 * When this field is absent, all faceset_tokens under the API key have been returned.
	 */
	@JsonProperty("next")
	private String next;

	/**
	 * 2. FaceSet information under this API key (see fields below).
	 * Note: empty array when the API key has no FaceSet.
	 */
	@JsonProperty("facesets")
	private List<FacesetFetail> facesets;

	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class FacesetFetail {

		/**
		 * Face recognition operations.The FaceSet token (faceset_token).
		 */
		@JsonProperty("faceset_token")
		private String facesetToken;

		/**
		 * 2、User-defined FaceSet id (outer_id); empty when not set.
		 */
		@JsonProperty("outer_id")
		private String outerId;

		/**
		 * 3、FaceSet display name (up to 256 chars; ^@,&=*'" are not allowed).
		 */
		@JsonProperty("display_name")
		private String displayName;

		/**
		 * 4、Comma-separated custom tags used to group FaceSets (up to 255 chars; ^@,&=*'" are not allowed in each tag).
		 */
		@JsonProperty("tags")
		private String tags;

	}

}
