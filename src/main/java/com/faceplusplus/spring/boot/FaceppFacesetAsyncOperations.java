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

import com.faceplusplus.spring.boot.resp.FaceAddResponse;
import com.faceplusplus.spring.boot.resp.FaceRemoveResponse;
import com.faceplusplus.spring.boot.resp.FaceStatusResponse;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Asynchronous FaceSet operations (extends FaceppFacesetOperations).
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FaceppFacesetAsyncOperations extends FaceppFacesetOperations {

	public FaceppFacesetAsyncOperations(FaceppTemplate faceppTemplate) {
		super(faceppTemplate);
	}

	/**
	 * FaceSet management operations. > Add face (async) API.
	 * Adds face_tokens to an existing FaceSet (up to 1000 face_tokens per FaceSet).
	 * Note: since 16 Aug 2017 a FaceSet can hold up to 10000 face_tokens.
	 * API：https://console.faceplusplus.com.cn/documents/40622166
	 * @param facesetToken The FaceSet token (faceset_token).
	 * @param faceTokens Comma-separated string of one or more face_tokens (up to 5).
	 * @return the operation result
	 */
	public void asyncAddFaceWithToken(String facesetToken, String[] faceTokens, Consumer<FaceAddResponse> consumer) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_ADD_ASYNC.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("faceset_token", facesetToken)
				.put("face_tokens", faceTokens)
				.build();
		getFaceppOkHttp3Template().doAsyncRequest(reqUrl, FaceppOkHttp3Template.HttpMethod.POST, consumer, FaceAddResponse.class);
	}

	/**
	 * 2. FaceSet management > Add face (async) API.
	 * Adds face_tokens to an existing FaceSet (up to 1000 face_tokens per FaceSet).
	 * Note: since 16 Aug 2017 a FaceSet can hold up to 10000 face_tokens.
	 * API：https://console.faceplusplus.com.cn/documents/40622166
	 * @param outerId User-supplied FaceSet identifier (outer_id).
	 * @param faceTokens Comma-separated string of one or more face_tokens (up to 5).
	 * @return the operation result
	 */
	public void asyncAddFaceWithOuterId(String outerId, String[] faceTokens, Consumer<FaceAddResponse> consumer) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_ADD_ASYNC.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("outer_id", outerId)
				.put("face_tokens", faceTokens)
				.build();
		getFaceppOkHttp3Template().doAsyncRequest(reqUrl, FaceppOkHttp3Template.HttpMethod.POST, consumer, FaceAddResponse.class);
	}

	/**
	 * 3. FaceSet management > Remove face (async) API.
	 * Removes some or all face_tokens from a FaceSet.
	 * API：https://console.faceplusplus.com.cn/documents/4888399
	 * @param facesetToken The FaceSet token (faceset_token).
	 * @param faceTokens Comma-separated string of one or more face_tokens to remove (up to 1000).
	 * Pass "RemoveAllFaceTokens" to remove all face_tokens in the FaceSet.
	 * @return the operation result
	 */
	public void asyncRemoveFaceByToken(String facesetToken, String[] faceTokens, Consumer<FaceRemoveResponse> consumer) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_REMOVE_ASYNC.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("faceset_token", facesetToken)
				.put("face_tokens", faceTokens)
				.build();
		getFaceppOkHttp3Template().doAsyncRequest(reqUrl, FaceppOkHttp3Template.HttpMethod.POST, consumer, FaceRemoveResponse.class);
	}

	/**
	 * 4. FaceSet management > Remove face (async) API.
	 * Removes some or all face_tokens from a FaceSet.
	 * API：https://console.faceplusplus.com.cn/documents/40622169
	 * @param outerId User-supplied FaceSet identifier (outer_id).
	 * @param faceTokens Comma-separated string of one or more face_tokens to remove (up to 1000).
	 * Pass "RemoveAllFaceTokens" to remove all face_tokens in the FaceSet.
	 * @return the operation result
	 */
	public void asyncRemoveFaceByOuterId(String outerId, String[] faceTokens, Consumer<FaceRemoveResponse> consumer) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_REMOVE_ASYNC.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("outer_id", outerId)
				.put("face_tokens", faceTokens)
				.build();
		getFaceppOkHttp3Template().doAsyncRequest(reqUrl, FaceppOkHttp3Template.HttpMethod.POST, consumer, FaceRemoveResponse.class);
	}

	/**
	 * 5. FaceSet management > Async add/remove result query API.
	 * Queries the current status of a previously submitted asynchronous add/remove faces task.
	 * Note: since 16 Aug 2017 a FaceSet can hold up to 10000 face_tokens.
	 * API：https://console.faceplusplus.com.cn/documents/40622157
	 * @param taskId Unique id of the asynchronous task.
	 * @return the operation result
	 */
	public FaceStatusResponse getFaceStatusByTaskId(String taskId) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_STATUS_ASYNC.getUrl();
		Map<String, Object> params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("task_id", taskId)
				.build();
		FaceStatusResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params,  FaceStatusResponse.class);
		return resp;
	}

}
