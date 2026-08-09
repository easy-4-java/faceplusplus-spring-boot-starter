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

import com.faceplusplus.spring.boot.req.*;
import com.faceplusplus.spring.boot.resp.*;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Face recognition operations.
 *
 * @author Loong Wan (https://github.com/loong10k)
 * @since 1.0.0
 */public class FaceppFaceOperations extends FaceppOperations {

	public FaceppFaceOperations(FaceppTemplate faceppTemplate) {
		super(faceppTemplate);
	}

	/**
	 * Face recognition > Face Detect API.
	 * Detects faces and analyses the given image.
	 * Detects all faces in the image and returns a unique face_token for each, usable for later analysis/comparison. Production API keys support detecting faces within a specified region.
	 * The API can also analyse detected faces, returning landmarks and attributes. Trial API keys analyse at most the 5 largest faces; other detected faces can be analysed via the Face Analyze API. Production API keys analyse all detected faces.
	 * API：https://console.faceplusplus.com.cn/documents/4888373
	 * @param imageUrl Image URL. Downloading may be slow due to network latency, so image_file or image_base64 is recommended.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceDetectResponse detectUrl(String imageUrl, FaceDetectOptions options) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_DETECT.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_url", imageUrl)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceDetectResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params, FaceDetectResponse.class);
		return resp;
	}

	/**
	 * 2. Face recognition > Face Detect API.
	 * Detects faces and analyses the given image.
	 * Detects all faces in the image and returns a unique face_token for each, usable for later analysis/comparison. Production API keys support detecting faces within a specified region.
	 * The API can also analyse detected faces, returning landmarks and attributes. Trial API keys analyse at most the 5 largest faces; other detected faces can be analysed via the Face Analyze API. Production API keys analyse all detected faces.
	 * API：https://console.faceplusplus.com.cn/documents/4888373
	 * @param imageBase64 Image URL. Downloading may be slow due to network latency, so image_file or image_base64 is recommended.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceDetectResponse detectBase64(String imageBase64, FaceDetectOptions options) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_DETECT.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_base64", imageBase64)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceDetectResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params, FaceDetectResponse.class);
		return resp;
	}

	/**
	 * 3. Face recognition > Face Detect API.
	 * Detects faces and analyses the given image.
	 * Detects all faces in the image and returns a unique face_token for each, usable for later analysis/comparison. Production API keys support detecting faces within a specified region.
	 * The API can also analyse detected faces, returning landmarks and attributes. Trial API keys analyse at most the 5 largest faces; other detected faces can be analysed via the Face Analyze API. Production API keys analyse all detected faces.
	 * API：https://console.faceplusplus.com.cn/documents/4888373
	 * @param imageFile Image URL. Downloading may be slow due to network latency, so image_file or image_base64 is recommended.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceDetectResponse detectFile(File imageFile, FaceDetectOptions options) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_DETECT.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_file", imageFile)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceDetectResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceDetectResponse.class);
		return resp;
	}

	/**
	 * 4. Face recognition > Face Analyze API.
	 * Analyses the landmarks and attributes of faces identified by face_token from the Detect API. Up to 5 faces per call.
	 * API：https://console.faceplusplus.com.cn/documents/4888383
	 * @param faceTokens Comma-separated string of one or more face_tokens (up to 5).
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceAnalyzeResponse analyze(String[] faceTokens, FaceAnalyzeOptions options) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_ANALYZE.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("face_tokens", faceTokens)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceAnalyzeResponse resp = getFaceppOkHttp3Template().post(reqUrl, null, params, FaceAnalyzeResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Face Compare API.
	 * Compares two faces to decide whether they are the same person, returning a confidence value and thresholds at different false-accept rates.
	 * Accepts images or face_tokens. When an image is provided the largest detected face is used.
	 * API：https://console.faceplusplus.com.cn/documents/4887586
	 * @param imageUrl1 URL of the first image.
	 * @param imageUrl2 URL of the second image.
	 * @return the operation result
	 */
	public FaceCompareResponse compareUrl(String imageUrl1, String imageUrl2) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_COMPARE.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_url1", imageUrl1)
				.put("image_url2", imageUrl2)
				.build();
		FaceCompareResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceCompareResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Face Compare API.
	 * Compares two faces to decide whether they are the same person, returning a confidence value and thresholds at different false-accept rates.
	 * Accepts images or face_tokens. When an image is provided the largest detected face is used.
	 * API：https://console.faceplusplus.com.cn/documents/4887586
	 * @param faceToken1 First face_token to compare; takes priority over image parameters.
	 * @param faceToken2 Second face_token to compare; takes priority over image parameters.
	 * @return the operation result
	 */
	public FaceCompareResponse compareToken(String faceToken1, String faceToken2) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_COMPARE.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("face_token1", faceToken1)
				.put("face_token2", faceToken2)
				.build();
		FaceCompareResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceCompareResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Face Compare API.
	 * Compares two faces to decide whether they are the same person, returning a confidence value and thresholds at different false-accept rates.
	 * Accepts images or face_tokens. When an image is provided the largest detected face is used.
	 * API：https://console.faceplusplus.com.cn/documents/4887586
	 * @param imageBase64_1 Base64-encoded image data. When image_url1/image_file1/image_base64_1 are all provided, image_file1 takes priority and image_url1 the lowest.
	 * @param imageBase64_2 Base64-encoded image data. When image_url2/image_file2/image_base64_2 are all provided, image_file2 takes priority and image_url2 the lowest.
	 * @return the operation result
	 */
	public FaceCompareResponse compareBase64(String imageBase64_1, String imageBase64_2) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_DETECT.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_base64_1", imageBase64_1)
				.put("image_base64_2", imageBase64_2)
				.build();
		FaceCompareResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceCompareResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Face Compare API.
	 * Compares two faces to decide whether they are the same person, returning a confidence value and thresholds at different false-accept rates.
	 * Accepts images or face_tokens. When an image is provided the largest detected face is used.
	 * API：https://console.faceplusplus.com.cn/documents/4887586
	 * @param imageFile1 First binary image file; uploaded as multipart/form-data.
	 * @param imageFile2 Second binary image file; uploaded as multipart/form-data.
	 * @return the operation result
	 */
	public FaceCompareResponse compareFile(File imageFile1, File imageFile2) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_COMPARE.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_file1", imageFile1)
				.put("image_file2", imageFile2)
				.build();
		FaceCompareResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceCompareResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Face Search API.
	 * Finds the most similar face(s) to the target face within an existing FaceSet, returning confidence and thresholds at different false-accept rates.
	 * Accepts images or face_tokens. When an image is provided the largest detected face is used.
	 * API：https://console.faceplusplus.com.cn/documents/4888381
	 * @param imageUrl URL of the image containing the target face.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceSearchResponse searchUrl(String imageUrl, FaceSearchOptions options) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_COMPARE.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_url", imageUrl)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceSearchResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceSearchResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Face Search API.
	 * Finds the most similar face(s) to the target face within an existing FaceSet, returning confidence and thresholds at different false-accept rates.
	 * Accepts images or face_tokens. When an image is provided the largest detected face is used.
	 * API：https://console.faceplusplus.com.cn/documents/4888381
	 * @param faceToken Target face_token to search; takes priority over image parameters.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceSearchResponse searchToken(String faceToken, FaceSearchOptions options) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_COMPARE.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("face_token", faceToken)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceSearchResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceSearchResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Face Search API.
	 * Finds the most similar face(s) to the target face within an existing FaceSet, returning confidence and thresholds at different false-accept rates.
	 * Accepts images or face_tokens. When an image is provided the largest detected face is used.
	 * API：https://console.faceplusplus.com.cn/documents/4888381
	 * @param imageBase64 Base64-encoded image data. When image_url/image_file/image_base64 are all provided, image_file takes priority and image_url the lowest.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceSearchResponse searchBase64(String imageBase64, FaceSearchOptions options) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_DETECT.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_base64", imageBase64)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceSearchResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceSearchResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Face Search API.
	 * Finds the most similar face(s) to the target face within an existing FaceSet, returning confidence and thresholds at different false-accept rates.
	 * Accepts images or face_tokens. When an image is provided the largest detected face is used.
	 * API：https://console.faceplusplus.com.cn/documents/4888381
	 * @param imageFile Binary image file containing the target face; uploaded as multipart/form-data.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceSearchResponse searchFile(File imageFile, FaceSearchOptions options) throws IOException {
		String reqUrl = FaceppApiAddress.FACE_SEARCH.getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_file", imageFile)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceSearchResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceSearchResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Skin analysis (basic) API.
	 * Detects and analyses the facial skin condition of the given image.
	 * API：https://console.faceplusplus.com.cn/documents/119745378
	 * @param imageUrl URL of the image containing the target face.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceSkinAnalyzeResponse skinAnalyzeUrl(String imageUrl, SkinAnalyzeType type, SkinAnalyzeOptions options) throws IOException {
		String reqUrl = type.getApiAddress().getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_url", imageUrl)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceSkinAnalyzeResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceSkinAnalyzeResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Skin analysis (basic) API.
	 * Detects and analyses the facial skin condition of the given image.
	 * API：https://console.faceplusplus.com.cn/documents/119745378
	 * @param imageBase64 Base64-encoded image data. When image_url/image_file/image_base64 are all provided, image_file takes priority and image_url the lowest.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceSkinAnalyzeResponse skinAnalyzeBase64(String imageBase64, SkinAnalyzeType type, SkinAnalyzeOptions options) throws IOException {
		String reqUrl = type.getApiAddress().getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_base64", imageBase64)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceSkinAnalyzeResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceSkinAnalyzeResponse.class);
		return resp;
	}

	/**
	 * 5. Face recognition > Skin analysis (basic) API.
	 * Detects and analyses the facial skin condition of the given image.
	 * API：https://console.faceplusplus.com.cn/documents/119745378
	 * @param imageFile Binary image file containing the target face; uploaded as multipart/form-data.
	 * @param options optional parameters
	 * @return the operation result
	 */
	public FaceSkinAnalyzeResponse skinAnalyzeFile(File imageFile, SkinAnalyzeType type, SkinAnalyzeOptions options) throws IOException {
		String reqUrl = type.getApiAddress().getUrl();
		Map params = new ImmutableMap.Builder<String, Object>()
				.put("api_key", getFaceppProperties().getAppId())
				.put("api_secret", getFaceppProperties().getAppCertificate())
				.put("image_file", imageFile)
				.putAll(getObjectMapper().readValue(getObjectMapper().writeValueAsString(options), Map.class))
				.build();
		FaceSkinAnalyzeResponse resp = getFaceppOkHttp3Template().doPartRequest(reqUrl, params, FaceSkinAnalyzeResponse.class);
		return resp;
	}

}
