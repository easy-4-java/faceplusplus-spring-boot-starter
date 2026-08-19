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

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.faceplusplus.spring.boot.resp.FaceppResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * OkHttp3 request template for the Face++ HTTP API.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
@Slf4j
/**
 * <p>Auto-configuration for FaceppOkHttp3Template.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class FaceppOkHttp3Template implements InitializingBean {

	public final static String APPLICATION_JSON_VALUE = "application/json";
	public final static String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
	public final static String APPLICATION_OCTET_STREAM_VALUE = "application/octet-stream";
	public final static MediaType APPLICATION_JSON = MediaType.parse(APPLICATION_JSON_VALUE);
	public final static MediaType APPLICATION_JSON_UTF8 = MediaType.parse(APPLICATION_JSON_UTF8_VALUE);
	public final static MediaType APPLICATION_OCTET_STREAM = MediaType.parse(APPLICATION_OCTET_STREAM_VALUE);

	protected OkHttpClient okhttp3Client;
	protected ObjectMapper objectMapper;
	protected FaceppProperties agoraProperties;

	public FaceppOkHttp3Template(OkHttpClient okhttp3Client, ObjectMapper objectMapper, FaceppProperties agoraProperties) {
		this.okhttp3Client = okhttp3Client;
		this.objectMapper = objectMapper;
		this.agoraProperties = agoraProperties;
	}

	@Override
	/**
	 * <p>After properties set.</p>
	 */
	public void afterPropertiesSet() throws Exception {
		// Request encoding; default UTF-8.
		if (okhttp3Client == null) {
			// 1. Create the OkHttpClient instance.
			okhttp3Client = new OkHttpClient().newBuilder().connectTimeout(5000, TimeUnit.MILLISECONDS)
					// .hostnameVerifier(okhttpHostnameVerifier)
					// .followRedirects(properties.isFollowRedirects())
					// .followSslRedirects(properties.isFollowSslRedirects())
					.pingInterval(1, TimeUnit.MILLISECONDS).readTimeout(3000, TimeUnit.MILLISECONDS)
					.retryOnConnectionFailure(true)
					// .sslSocketFactory(trustedSSLSocketFactory, trustManager)
					.writeTimeout(3, TimeUnit.SECONDS)
					// Application Interceptors、Network Interceptors :
					// https://segmentfault.com/a/1190000013164260
					// .addNetworkInterceptor(loggingInterceptor)
					// .addInterceptor(headerInterceptor)
					.build();
		}
	}

	/**
	 * <p>Post.</p>
	 * @param url
	 * @param rtClass
	 * @return the result
	 */
	public <T extends FaceppResponse> T post(String url, Class<T> rtClass) throws IOException {
		return this.doRequest(url, HttpMethod.POST, null, null, null, rtClass);
	}

	/**
	 * <p>Post.</p>
	 * @param url
	 * @param params
	 * @param rtClass
	 * @return the result
	 */
	public <T extends FaceppResponse> T post(String url, Map<String, Object> params, Class<T> rtClass) throws IOException {
		return this.doRequest(url, HttpMethod.POST, null, params, null, rtClass);
	}

	/**
	 * <p>Post.</p>
	 * @param url
	 * @param headers
	 * @param params
	 * @param rtClass
	 * @return the result
	 */
	public <T extends FaceppResponse> T post(String url, Map<String, Object> headers, Map<String, Object> params, Class<T> rtClass) throws IOException {
		return this.doRequest(url, HttpMethod.POST, headers, params, null, rtClass);
	}

	/**
	 * <p>Post.</p>
	 * @param url
	 * @param headers
	 * @param params
	 * @param bodyContent
	 * @param rtClass
	 * @return the result
	 */
	public <T extends FaceppResponse> T post(String url, Map<String, Object> headers, Map<String, Object> params, Map<String, Object> bodyContent, Class<T> rtClass) throws IOException {
		return this.doRequest(url, HttpMethod.POST, headers, params, bodyContent, rtClass);
	}

	/**
	 * <p>Get.</p>
	 * @param url
	 * @param rtClass
	 * @return the result
	 */
	public <T extends FaceppResponse> T get(String url, Class<T> rtClass) throws IOException {
		return this.doRequest(url, HttpMethod.GET, null, null, null, rtClass);
	}

	/**
	 * <p>Get.</p>
	 * @param url
	 * @param params
	 * @param rtClass
	 * @return the result
	 */
	public <T extends FaceppResponse> T get(String url, Map<String, Object> params, Class<T> rtClass) throws IOException {
		return this.doRequest(url, HttpMethod.GET, null, params, null, rtClass);
	}

	/**
	 * <p>Get.</p>
	 * @param url
	 * @param headers
	 * @param params
	 * @param rtClass
	 * @return the result
	 */
	public <T extends FaceppResponse> T get(String url, Map<String, Object> headers, Map<String, Object> params, Class<T> rtClass) throws IOException {
		return this.doRequest(url, HttpMethod.GET, headers, params, null, rtClass);
	}

	public <T extends FaceppResponse> T doRequest(
			String url,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> queryParams,
			Map<String, Object> bodyContent,
			Class<T> rtClass) throws IOException {
		long startTime = System.currentTimeMillis();
		// 1. Create the Request with the URL and HTTP method.
		HttpUrl httpUrl = this.getHttpUrl(url, queryParams);
		return this.doRequest(startTime, httpUrl, method, headers, bodyContent, rtClass);
	}

	public <T extends FaceppResponse> T doRequest(
			long startTime,
			HttpUrl httpUrl,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> bodyContent,
			Class<T> rtClass) throws IOException {
		// 2. Create a Call from the Request.
		Response response = this.doRequest(startTime, httpUrl, method, headers, bodyContent);
		T res = null;
		try {
			if (response.isSuccessful()) {
				String body = response.body().string();
				res = this.readValue(body, rtClass);
				res.setCode(response.code());
			} else {
				res = BeanUtils.instantiateClass(rtClass);
				res.setCode(response.code());
			}
		} catch (Exception e) {
			log.error("Agora >> Async Request Error : {}, use time : {}", e.getMessage(), System.currentTimeMillis() - startTime);
			res = BeanUtils.instantiateClass(rtClass);
		}
		return res;
	}

	public Response doRequest(
			String url,
			HttpMethod method) throws IOException {
		return this.doRequest(url, method, null);
	}

	public Response doRequest(
			String url,
			HttpMethod method,
			Map<String, Object> queryParams) throws IOException {
		return this.doRequest(url, method, null, queryParams);
	}

	public Response doRequest(
			String url,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> queryParams) throws IOException {
		return this.doRequest(url, method, headers, queryParams, null);
	}

	public Response doRequest(
			String url,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> queryParams,
			Map<String, Object> bodyContent) throws IOException {
		long startTime = System.currentTimeMillis();
		return this.doRequest(startTime, url, method, headers, queryParams, bodyContent);
	}

	public Response doRequest(
			long startTime,
			String url,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> queryParams,
			Map<String, Object> bodyContent) throws IOException {
		// 1. Create the Request with the URL and HTTP method.
		HttpUrl httpUrl = this.getHttpUrl(url, queryParams);
		return this.doRequest(startTime, httpUrl, method, headers, bodyContent);
	}

	public <T extends FaceppResponse> T doPartRequest(
			String httpUrl,
			Map<String, Object> params,
			Class<T> rtClass) throws IOException {

		long startTime = System.currentTimeMillis();
		// 2. Create a Call from the Request.
		Response response = this.doPartRequest(startTime, httpUrl, params);
		T res = null;
		try {
			if (response.isSuccessful()) {
				String body = response.body().string();
				res = this.readValue(body, rtClass);
				res.setCode(response.code());
			} else {
				res = BeanUtils.instantiateClass(rtClass);
				res.setCode(response.code());
			}
		} catch (Exception e) {
			log.error("Agora >> Async Request Error : {}, use time : {}", e.getMessage(), System.currentTimeMillis() - startTime);
			res = BeanUtils.instantiateClass(rtClass);
		}
		return res;
	}

	public Response doPartRequest(
			long startTime,
			String httpUrl,
			Map<String, Object> params) throws IOException {

		// 1. Create the Request.Builder.
		Request.Builder builder = new Request.Builder().url(httpUrl);
		// 2. Build the MultipartBody.
		MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
		for (Map.Entry<String, Object> entry : params.entrySet()){
			Object val = entry.getValue();
			if(val instanceof File){
				File file = (File) val;
				RequestBody fileBody = RequestBody.create(FaceppOkHttp3Template.APPLICATION_OCTET_STREAM, file);
				bodyBuilder.addFormDataPart(entry.getKey(), file.getName(), fileBody );
			} else {
				bodyBuilder.addFormDataPart(entry.getKey(), Objects.toString(entry.getValue()) );
			}
		}
		// 3. Create a Call from the Request.
		try {
			Response response = okhttp3Client.newCall(builder.post(bodyBuilder.build()).build()).execute();
			if (response.isSuccessful()) {
				log.info("Agora >> Request Success : code : {}, use time : {} ", response.code(), System.currentTimeMillis() - startTime);
			} else {
				log.error("Agora >> Request Failure : code : {}, message : {}, use time : {} ", response.code(), response.message(), System.currentTimeMillis() - startTime);
			}
			return response;
		} catch (IOException e) {
			log.error("OkHttp3 Request Error : {}, use time : {}", e.getMessage(), System.currentTimeMillis() - startTime);
		}
		return null;
	}

	public Response doRequest(
			long startTime,
			HttpUrl httpUrl,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> bodyContent) throws IOException {
		try {
			// 1. Create the Request.Builder.
			Request.Builder builder = this.createRequestBuilder(httpUrl, method, headers, bodyContent);
			// 2. Create a Call from the Request.
			Response response = okhttp3Client.newCall(builder.build()).execute();
			if (response.isSuccessful()) {
				log.info("Agora >> Request Success : code : {}, use time : {} ", response.code(), System.currentTimeMillis() - startTime);
			} else {
				log.error("Agora >> Request Failure : code : {}, message : {}, use time : {} ", response.code(), response.message(), System.currentTimeMillis() - startTime);
			}
			return response;
		} catch (IOException e) {
			log.error("OkHttp3 Request Error : {}, use time : {}", e.getMessage(), System.currentTimeMillis() - startTime);
			throw e;
		}
	}

	public <T extends FaceppResponse> void doAsyncRequest(
			String url,
			HttpMethod method,
			Consumer<T> success,
			Class<T> rtClass) throws IOException {
		this.doAsyncRequest(url, method, success, null, rtClass);
	}

	public <T extends FaceppResponse> void doAsyncRequest(
			String url,
			HttpMethod method,
			Consumer<T> success,
			BiFunction<Call, IOException, Boolean> failure,
			Class<T> rtClass) throws IOException {
		this.doAsyncRequest(url, method, null, success, failure, rtClass);
	}

	public <T extends FaceppResponse> void doAsyncRequest(
			String url,
			HttpMethod method,
			Map<String, Object> queryParams,
			Consumer<T> success,
			BiFunction<Call, IOException, Boolean> failure,
			Class<T> rtClass) throws IOException {
		this.doAsyncRequest(url, method, null, queryParams, success, failure, rtClass);
	}

	public <T extends FaceppResponse> void doAsyncRequest(
			String url,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> queryParams,
			Consumer<T> success,
			BiFunction<Call, IOException, Boolean> failure,
			Class<T> rtClass) throws IOException {
		this.doAsyncRequest(url, method, headers, queryParams, null, success, failure, rtClass);
	}

	public <T extends FaceppResponse> void doAsyncRequest(
			String url,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> queryParams,
			Map<String, Object> bodyContent,
			Consumer<T> success,
			BiFunction<Call, IOException, Boolean> failure,
			Class<T> rtClass) throws IOException {
		long startTime = System.currentTimeMillis();
		// 1. Create the Request with the URL and HTTP method.
		HttpUrl httpUrl = this.getHttpUrl(url, queryParams);
		this.doAsyncRequest(startTime, httpUrl, method, headers, bodyContent, success, failure, rtClass);
	}

	public <T extends FaceppResponse> void doAsyncRequest(
			long startTime,
			HttpUrl httpUrl,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> bodyContent,
			Consumer<T> success,
			BiFunction<Call, IOException, Boolean> failure,
			Class<T> rtClass) throws IOException {
		// 2. Create a Call from the Request.
		this.doAsyncRequest(startTime, httpUrl, method, headers, bodyContent, (call, response) -> {
			T res;
			try {
				if (response.isSuccessful()) {
					String body = response.body().string();
					res = this.readValue(body, rtClass);
					res.setCode(response.code());
				} else {
					res = BeanUtils.instantiateClass(rtClass);
					res.setCode(response.code());
				}
			} catch (Exception e) {
				log.error("Agora >> Async Request Error : {}, use time : {}", e.getMessage(), System.currentTimeMillis() - startTime);
				res = BeanUtils.instantiateClass(rtClass);
			}
			success.accept(res);
			return res;
		}, failure);
	}

	public <T extends FaceppResponse> void doAsyncRequest(
			long startTime,
			String url,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> queryParams,
			Map<String, Object> bodyContent,
			BiFunction<Call, Response, T> success,
			BiFunction<Call, IOException, Boolean> failure) throws IOException {
		// 1. Create the Request with the URL and HTTP method.
		HttpUrl httpUrl = this.getHttpUrl(url, queryParams);
		this.doAsyncRequest(startTime, httpUrl, method, headers, bodyContent, success, failure);
	}

	public <T extends FaceppResponse> void doAsyncRequest(
			long startTime,
			HttpUrl httpUrl,
			HttpMethod method,
			Map<String, Object> headers,
			Map<String, Object> bodyContent,
			BiFunction<Call, Response, T> success,
			BiFunction<Call, IOException, Boolean> failure) throws IOException {
		// 1. Create the Request.Builder.
		Request.Builder builder = this.createRequestBuilder(httpUrl, method, headers, bodyContent);
		// 2. Create a Call from the Request.
		okhttp3Client.newCall(builder.build()).enqueue(new Callback() {

			@Override
			/**
			 * <p>On failure.</p>
			 * @param call
			 * @param e
			 */
			public void onFailure(Call call, IOException e) {
				log.error("Agora >> Async Request Failure : {}, use time : {} ", e.getMessage(), System.currentTimeMillis() - startTime);
				if (Objects.nonNull(failure)) {
					failure.apply(call, e);
				}
			}

			@Override
			/**
			 * <p>On response.</p>
			 * @param call
			 * @param response
			 */
			public void onResponse(Call call, Response response) {
				if (response.isSuccessful()) {
					log.info("Agora >> Async Request Success : code : {}, use time : {} ", response.code(), System.currentTimeMillis() - startTime);
				} else {
					log.error("Agora >> Async Request Failure : code : {}, message : {}, use time : {} ", response.code(), response.message(), System.currentTimeMillis() - startTime);
				}
				if (Objects.nonNull(success)) {
					success.apply(call, response);
				}
			}

		});
	}

	/** @return return the http url. */
	public HttpUrl getHttpUrl(String httpUrl, Map<String, Object> params) {
		log.info("Agora >> Request Url : {}", httpUrl);
		HttpUrl.Builder urlBuilder = HttpUrl.parse(httpUrl).newBuilder();
		if (CollectionUtils.isEmpty(params)) {
			return urlBuilder.build();
		}
		if (!CollectionUtils.isEmpty(params)) {
			log.info("Agora >> Request Params : {}", params);
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				urlBuilder.addQueryParameter(entry.getKey(), Objects.isNull(entry.getValue()) ? "" : entry.getValue().toString());
			}
		}
		return urlBuilder.build();
	}

	public Request.Builder createRequestBuilder(HttpUrl httpUrl,
												  HttpMethod method,
												  Map<String, Object> headers,
												  Map<String, Object> bodyContent) throws IOException{
		log.info("Agora >> Request Query Url : {} , Method : {}", httpUrl.query() , method.getName());
		// 1. Create the Request.Builder.
		Request.Builder builder = new Request.Builder().url(httpUrl);
		// 2. Add request headers.
		if(Objects.nonNull(headers)) {
			log.info("Agora >> Request Headers : {}", headers);
			for (Entry<String, Object> entry : headers.entrySet()) {
				builder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
		// 3. Add the request body.
		if(Objects.nonNull(bodyContent)) {
			String bodyStr = objectMapper.writeValueAsString(bodyContent);
			log.info("Agora >> Request Body : {}", bodyStr);
			builder = method.apply(builder, bodyStr);
		} else {
			builder = method.apply(builder);
		}
		return builder;
	}

	/**
	 * <p>Read value.</p>
	 * @param json
	 * @param cls
	 * @return the result
	 */
	public <T extends FaceppResponse> T readValue(String json, Class<T> cls) {
		try {
			return JSONObject.parseObject(json, cls);
			//return objectMapper.readValue(json, cls);
		} catch (Exception e) {
			log.error(e.getMessage());
			return BeanUtils.instantiateClass(cls);
		}
	}

	/**
	 * <p>Auto-configuration for HttpMethod.</p>
	 * @author <a href="https://github.com/loong10k">Loong Wan</a>
	 * @since 1.0.0
	 */
	public static enum HttpMethod {

		/**
		 * get request.
		 */
		GET("GET", (builder, bodyStr)->{
			return builder.get();
		}),
		/**
		 * head request.
		 */
		HEAD("HEAD", (builder, bodyStr)->{
			return builder.head();
		}),
		/**
		 * post request.
		 */
		POST("POST", (builder, bodyStr)->{
			return builder.post(RequestBody.create(APPLICATION_JSON_UTF8, bodyStr));
		}),
		/**
		 * put request.
		 */
		PUT("PUT", (builder, bodyStr)->{
			return builder.put(RequestBody.create(APPLICATION_JSON_UTF8, bodyStr));
		}),
		/**
		 * patch request.
		 */
		PATCH("PATCH", (builder, bodyStr)->{
			return builder.patch(RequestBody.create(APPLICATION_JSON_UTF8, bodyStr));
		}),
		/**
		 * delete request.
		 */
		DELETE("DELETE", (builder, bodyStr)->{
			return StringUtils.hasText(bodyStr) ? builder.delete(RequestBody.create(APPLICATION_JSON_UTF8, bodyStr)) : builder.delete();
		}),
		/**
		 * options request.
		 */
		OPTIONS("OPTIONS", (builder, bodyStr)->{
			return builder;
		}),
		/**
		 * trace request.
		 */
		TRACE("TRACE", (builder, bodyStr)->{
			return builder;
		});

		private String name;
		private BiFunction<Request.Builder, String, Request.Builder> function;

		HttpMethod(String name, BiFunction<Request.Builder, String, Request.Builder> function) {
			this.name = name;
			this.function = function;
		}

		/** @return return the name. */
		public String getName() {
			return name;
		}

		public Request.Builder apply(Request.Builder builder, String bodyStr){
			return function.apply(builder, bodyStr);
		}

		public Request.Builder apply(Request.Builder builder){
			return function.apply(builder, null);
		}

		/** @return return the by name. */
		public static HttpMethod getByName(int name) {
			for (HttpMethod type : HttpMethod.values()) {
				if (type.getName().equals(name)) {
					return type;
				}
			}
			return null;
		}

	}

	/** @return return the object mapper. */
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
