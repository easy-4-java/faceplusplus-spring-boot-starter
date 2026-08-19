package com.faceplusplus.spring.boot;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
@Slf4j
/**
 * <p>Auto-configuration for FaceppTemplate.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
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

	/**
	 * <p>Ops for faceset.</p>
	 * @return the result
	 */
	public FaceppFacesetAsyncOperations opsForFaceset() {
		return facesetOps;
	}

	/**
	 * <p>Ops for face detect.</p>
	 * @return the result
	 */
	public FaceppFaceAsyncOperations opsForFaceDetect() {
		return faceDetectOps;
	}

	/** @return return the facepp properties. */
	public FaceppProperties getFaceppProperties() {
		return faceppProperties;
	}

	/** @return return the facepp ok http3 template. */
	public FaceppOkHttp3Template getFaceppOkHttp3Template() {
		return faceppOkHttp3Template;
	}


}
