package com.faceplusplus.spring.boot;

import lombok.extern.slf4j.Slf4j;

/**
 * @author [@Loong Wan](https://github.com/loong10k)
 */
@Slf4j
/**
 * Core template for invoking the Face++ APIs.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class FaceppTemplate {

	private FaceppOkHttp3Template faceppOkHttp3Template;
	private FaceppProperties faceppProperties;

	private final FaceppFacesetAsyncOperations facesetOps = new FaceppFacesetAsyncOperations(this);
	private final FaceppFaceAsyncOperations faceDetectOps = new FaceppFaceAsyncOperations(this);


	public FaceppTemplate(FaceppOkHttp3Template faceppOkHttp3Template, FaceppProperties faceppProperties) {
		this.faceppOkHttp3Template = faceppOkHttp3Template;
		this.faceppProperties = faceppProperties;
	}

	public FaceppFacesetAsyncOperations opsForFaceset() {
		return facesetOps;
	}

	public FaceppFaceAsyncOperations opsForFaceDetect() {
		return faceDetectOps;
	}

	public FaceppProperties getFaceppProperties() {
		return faceppProperties;
	}

	public FaceppOkHttp3Template getFaceppOkHttp3Template() {
		return faceppOkHttp3Template;
	}


}
