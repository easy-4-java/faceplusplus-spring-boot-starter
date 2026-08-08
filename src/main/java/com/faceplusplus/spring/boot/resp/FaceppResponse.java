package com.faceplusplus.spring.boot.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Response result.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
/**
 * Model class for FaceppResponse.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FaceppResponse {

	/**
	 * HTTP status code; 200 means success, otherwise failure.
	 */
	@JsonProperty("code")
	private int code;

	/**
	 * Total request time in milliseconds. Always returned except on 404 (API_NOT_FOUND) or 403 (AUTHORIZATION_ERROR).
	 */
	@JsonProperty("time_used")
	private int timeUsed;

	/**
	 * Unique string identifying each request. Always returned except on 404 (API_NOT_FOUND) or 403 (AUTHORIZATION_ERROR).
	 */
	@JsonProperty("request_id")
	private String requestId;

	/**
	 * Returned only when the request fails (see the error-message section). Absent otherwise.
	 */
	@JsonProperty("error_message")
	private String errorMsg;

	public boolean isSuccess() {
		return code == 200;
	}

}
