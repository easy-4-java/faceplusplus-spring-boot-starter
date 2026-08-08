package com.faceplusplus.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Face++ configuration properties.
 * Thread-pool configuration used by the Face++ HTTP client.
 */
@ConfigurationProperties(prefix = FaceppProperties.PREFIX)
@Data
/**
 * Configuration properties for the Face++ integration.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FaceppProperties {

	/**
	 * The prefix of the property of {@link FaceppProperties}.
	 */
	public static final String PREFIX = "faceplusplus.facepp";

	/** App id. */
	private String host = "https://api-cn.faceplusplus.com";
	/** App id. */
	private String appId;
	/** App certificate. */
	private String appCertificate;
	/** Token expiration time in seconds. */
	private int expirationTimeInSeconds = 3600;
	/** RESTful login key (required). */
	private String loginKey;
	/** RESTful login secret (required). */
	private String loginSecret;

	/** Recording region: 7=Hong Kong, 10=Singapore. */
	private Integer ossRegion;

	/** Video width. */
	private Integer viewWidth;

	/** Video height. */
	private Integer viewHeight;

}
