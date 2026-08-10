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
package com.faceplusplus.spring.boot;

import com.faceplusplus.spring.boot.req.FacesetBo;
import com.faceplusplus.spring.boot.req.FacesetDeleteBo;
import com.faceplusplus.spring.boot.req.FacesetUpdateBo;
import com.faceplusplus.spring.boot.resp.*;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FaceSet management operations.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */public class FaceppFacesetOperations extends FaceppOperations {

	public FaceppFacesetOperations(FaceppTemplate faceppTemplate) {
		super(faceppTemplate);
	}

	/**
	 * FaceSet management operations. > Create a FaceSet. API
	 * Creates a FaceSet to hold face_tokens. A FaceSet can hold up to 10000 face_tokens.
	 * Trial API keys can create up to 1000 FaceSets; production API keys up to 10000.
	 * API：https://console.faceplusplus.com.cn/documents/4888391
	 * @param faceset FaceSet information.
	 * @return the operation result
	 */
	public FacesetCreateResponse createFaceset(FacesetBo faceset) throws IOException {
		String reqUrl = FaceppApiAddress.FACESET_CREATE.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(faceset), Map.class))
				.build();
		FacesetCreateResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params, FacesetCreateResponse.class);
		return resp;
	}

	/**
	 * 2. FaceSet management > Delete FaceSet API.
	 * Deletes a FaceSet.
	 * API：https://console.faceplusplus.com.cn/documents/4888391
	 * @param faceset FaceSet information.
	 * @return the operation result
	 */
	public FacesetCreateResponse deleteFaceset(FacesetDeleteBo faceset) throws IOException {
		String reqUrl = FaceppApiAddress.FACESET_DELETE.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(faceset), Map.class))
				.build();
		FacesetCreateResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FacesetCreateResponse.class);
		return resp;
	}

	/**
	 * 3. FaceSet management > Update FaceSet API.
	 * Updates the attributes of a FaceSet.
	 * API：https://console.faceplusplus.com.cn/documents/4888391
	 * @param faceset FaceSet information.
	 * @return the operation result
	 */
	public FacesetCreateResponse updateFaceset(FacesetUpdateBo faceset) throws IOException {
		String reqUrl = FaceppApiAddress.FACESET_UPDATE.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(faceset), Map.class))
				.build();
		FacesetCreateResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FacesetCreateResponse.class);
		return resp;
	}

	/**
	 * 2. FaceSet management > Get FaceSet list API.
	 * Returns the list of FaceSets for an API key with their faceset_token, outer_id, display_name and tags.
	 * Note: since 16 Aug 2017 this API no longer returns all FaceSets at once; each call returns at most 100. Use the start/next parameters to paginate.
	 * API：https://console.faceplusplus.com.cn/documents/4888397
	 * @param lastSequence A number n indicating the start offset of faceset_tokens for the API key.
	 * Controls the start offset (n-th faceset_token). Results are sorted by creation time; up to 1000 per call. Defaults to 1.
	 * Note: since 16 Aug 2017 the per-call limit dropped from 1000 to 100; pass the next value from the previous call to fetch the next 100 faceset_tokens.
	 * @param tags Comma-separated string of FaceSet tags to query.
	 * @return the operation result
	 */
	public FacesetListResponse getFacesetList(int lastSequence, String... tags) throws IOException {
		String reqUrl = FaceppApiAddress.FACESET_LIST.getUrl();
		ImmutableMap.Builder builder = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("start", lastSequence);
		if(tags != null && tags.length > 0){
			if(tags.length == 1){
				builder.put("tags", tags[0]);
			} else {
				builder.put("tags", Stream.of(tags).collect(Collectors.joining(", ")));
			}
		}
		FacesetListResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, builder.build(),  FacesetListResponse.class);
		return resp;
	}

	/**
	 * 2. FaceSet management > Get FaceSet detail API.
	 * Returns full information of a FaceSet: its faceset_token, outer_id, display_name and the count/list of stored face_tokens.
	 * Note: since 16 Aug 2017 this API no longer returns the full face_token list at once; each call returns at most 100. Use the start/next parameters to paginate.
	 * API：https://console.faceplusplus.com.cn/documents/4888395
	 * @param facesetToken The FaceSet token (faceset_token).
	 * @param lastSequence A number n indicating the start offset of faceset_tokens for the API key.
	 * Controls the start offset (n-th faceset_token). Results are sorted by creation time; up to 1000 per call. Defaults to 1.
	 * Note: since 16 Aug 2017 the per-call limit dropped from 1000 to 100; pass the next value from the previous call to fetch the next 100 faceset_tokens.
	 * @return the operation result
	 */
	public FacesetDetailResponse getFacesetByToken(String facesetToken, int lastSequence) throws IOException {
		String reqUrl = FaceppApiAddress.FACESET_LIST.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("faceset_token", facesetToken)
				.put("start", lastSequence)
				.build();
		FacesetDetailResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FacesetDetailResponse.class);
		return resp;
	}

	/**
	 * 2. FaceSet management > Get FaceSet detail API.
	 * Returns full information of a FaceSet: its faceset_token, outer_id, display_name and the count/list of stored face_tokens.
	 * Note: since 16 Aug 2017 this API no longer returns the full face_token list at once; each call returns at most 100. Use the start/next parameters to paginate.
	 * API：https://console.faceplusplus.com.cn/documents/4888395
	 * @param outerId User-supplied FaceSet identifier (outer_id).
	 * @param lastSequence A number n indicating the start offset of faceset_tokens for the API key.
	 * Controls the start offset (n-th faceset_token). Results are sorted by creation time; up to 1000 per call. Defaults to 1.
	 * Note: since 16 Aug 2017 the per-call limit dropped from 1000 to 100; pass the next value from the previous call to fetch the next 100 faceset_tokens.
	 * @return the operation result
	 */
	public FacesetDetailResponse getFacesetByOuterId(String outerId, int lastSequence) throws IOException {
		String reqUrl = FaceppApiAddress.FACESET_DETAIL.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("outer_id", outerId)
				.put("start", lastSequence)
				.build();
		FacesetDetailResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FacesetDetailResponse.class);
		return resp;
	}

	/**
	 * 3. FaceSet management > Add face API.
	 * Adds face_tokens to an existing FaceSet (up to 1000 face_tokens per FaceSet).
	 * Note: since 16 Aug 2017 a FaceSet can hold up to 10000 face_tokens.
	 * API：https://console.faceplusplus.com.cn/documents/4888389
	 * @param facesetToken The FaceSet token (faceset_token).
	 * @param faceTokens Comma-separated string of one or more face_tokens (up to 5).
	 * @return the operation result
	 */
	public FaceAddResponse addFaceWithToken(String facesetToken, String ... faceTokens) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_ADD.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("faceset_token", facesetToken)
				.put("face_tokens", faceTokens)
				.build();
		FaceAddResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FaceAddResponse.class);
		return resp;
	}

	/**
	 * 3. FaceSet management > Add face API.
	 * Adds face_tokens to an existing FaceSet (up to 1000 face_tokens per FaceSet).
	 * Note: since 16 Aug 2017 a FaceSet can hold up to 10000 face_tokens.
	 * API：https://console.faceplusplus.com.cn/documents/4888389
	 * @param outerId User-supplied FaceSet identifier (outer_id).
	 * @param faceTokens Comma-separated string of one or more face_tokens (up to 5).
	 * @return the operation result
	 */
	public FaceAddResponse addFaceWithOuterId(String outerId, String ... faceTokens) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_ADD.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("outer_id", outerId)
				.put("face_tokens", faceTokens)
				.build();
		FaceAddResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FaceAddResponse.class);
		return resp;
	}

	/**
	 * 3. FaceSet management > Remove face API.
	 * Removes some or all face_tokens from a FaceSet.
	 * API：https://console.faceplusplus.com.cn/documents/4888399
	 * @param facesetToken The FaceSet token (faceset_token).
	 * @param faceTokens Comma-separated string of one or more face_tokens to remove (up to 1000).
	 * Pass "RemoveAllFaceTokens" to remove all face_tokens in the FaceSet.
	 * @return the operation result
	 */
	public FaceRemoveResponse removeFaceByToken(String facesetToken, String ... faceTokens) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_REMOVE.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("faceset_token", facesetToken)
				.put("face_tokens", faceTokens)
				.build();
		FaceRemoveResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FaceRemoveResponse.class);
		return resp;
	}

	/**
	 * 3. FaceSet management > Remove face API.
	 * Removes some or all face_tokens from a FaceSet.
	 * API：https://console.faceplusplus.com.cn/documents/4888399
	 * @param outerId User-supplied FaceSet identifier (outer_id).
	 * @param faceTokens Comma-separated string of one or more face_tokens to remove (up to 1000).
	 * Pass "RemoveAllFaceTokens" to remove all face_tokens in the FaceSet.
	 * @return the operation result
	 */
	public FaceRemoveResponse removeFaceByOuterId(String outerId, String ... faceTokens) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_REMOVE.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("outer_id", outerId)
				.put("face_tokens", faceTokens)
				.build();
		FaceRemoveResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FaceRemoveResponse.class);
		return resp;
	}

	/**
	 * Face management > Set user_id API.
	 * Sets user_id information for a detected face; the value is returned by the Search API to identify the user.
	 * API：https://console.faceplusplus.com.cn/documents/4888387
	 * @param faceToken The face_token.
	 * @param userId User-defined user_id (up to 255 chars; ^@,&=*'" are not allowed). The same user_id is recommended for all face_tokens of one person.
	 * @return the operation result
	 */
	public FaceSetUserIdResponse createFace(String faceToken, String userId) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_SET_USERID.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("user_id", userId)
				.put("face_token", faceToken)
				.build();
		FaceSetUserIdResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params, FaceSetUserIdResponse.class);
		return resp;
	}

	/**
	 * 2. Face management > Get face detail API.
	 * Returns the information associated with a face_token from the Detect API, including the source image id and the FaceSets it belongs to.
	 * API：https://console.faceplusplus.com.cn/documents/4888385
	 * @param faceToken The face_token.
	 * @return the operation result
	 */
	public FaceDetailResponse getFaceDetail(String faceToken) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_GET_DETAIL.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("face_token", faceToken)
				.build();
		FaceDetailResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FaceDetailResponse.class);
		return resp;
	}


}
