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
public class FaceAddResponse extends FaceppResponse {

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
	 * 3. Number of face_tokens successfully added to the FaceSet.
	 */
	@JsonProperty("face_added")
	private Integer faceAdded;

	/**
	 * 4. Total number of face_tokens in the FaceSet after the operation.
	 */
	@JsonProperty("face_count")
	private Integer faceCount;

	/**
	 * 5. face_tokens that could not be added, with reasons.
	 * face_token：Face identifier (face_token).
	 * reason：Reason the face could not be added: INVALID_FACE_TOKEN (face does not exist) or QUOTA_EXCEEDED (FaceSet full).
	 */
	@JsonProperty("failure_detail")
	private List<FailureFetail> detail;

	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class FailureFetail {

		/**
		 * Face identifier (face_token).
		 */
		@JsonProperty("face_token")
		private String token;

		/**
		 * Reason the face could not be added: INVALID_FACE_TOKEN (face does not exist) or QUOTA_EXCEEDED (FaceSet full).
		 */
		@JsonProperty("reason")
		private String reason;

	}

}
