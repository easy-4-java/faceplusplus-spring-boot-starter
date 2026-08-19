package com.faceplusplus.spring.boot;

import java.text.MessageFormat;

/**
 * https://console.faceplusplus.com.cn/documents/268763412
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
public enum FaceppApiAddress {

	// ---------------- Face recognition ------------------

	/**
	 * Acquire a cloud-recording resource id.
	 * URL: https://api.agora.io/v1/apps/<yourappid>/cloud_recording/acquire
	 */
	ACQUIRE_RESOURCE_ID("Face detection (Detect). API", RequestMethod.POST,"/facepp/v3/detect"),
	/**
	 * Start cloud recording.
	 * URL: https://api.agora.io/v1/apps/<yourappid>/cloud_recording/resourceid/<resourceid>/mode/<mode>/start
	 */
	START_CLOUD_RECORDING("Face analysis (Analyze). API", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/mode/{2}/start"),
	/**
	 * Update cloud recording.
	 * URL: https://api.agora.io/v1/apps/<appid>/cloud_recording/resourceid/<resourceid>/sid/<sid>/mode/<mode>/updateLayout
	 */
	UPDATE_CLOUD_RECORDING("Dense landmarks API", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/updateLayout"),
	/**
	 * Update the merge layout.
	 * URL: https://api.agora.io/v1/apps/<appid>/cloud_recording/resourceid/<resourceid>/sid/<sid>/mode/<mode>/update
	 */
	UPDATE_CLOUD_RECORDING_LAYOUT("Face comparison (Compare). API", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/update"),
	/**
	 * Query cloud-recording status.
	 * URL: https://api.agora.io/v1/apps/<yourappid>/cloud_recording/resourceid/<resourceid>/sid/<sid>/mode/<mode>/query
	 */
	QUERY_CLOUD_RECORDING("Query cloud-recording status.", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/query"),
	/**
	 * Stop cloud recording.
	 * URL:  https://api.agora.io/v1/apps/<yourappid>/cloud_recording/resourceid/<resourceid>/sid/<sid>/mode/<mode>/stop
	 */
	STOP_CLOUD_RECORDING("Stop cloud recording.", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/stop"),

	// ---------------- Project management ------------------

	/**
	 * Create a project.
	 */
	PROJECT_POST("Create a project.", RequestMethod.POST,"https://api.agora.io/v1/project"),
	/**
	 * Get a specific project.
	 */
	PROJECT_GET("Get a specific project.", RequestMethod.POST,"https://api.agora.io/v1/project"),
	/**
	 * Get all projects.
	 */
	PROJECTS_GET("Get all projects.", RequestMethod.POST,"https://api.agora.io/v1/projects"),
	/**
	 * Disable or enable a project.
	 */
	PROJECT_STATUS_POST("Disable or enable a project.", RequestMethod.POST,"https://api.agora.io/v1/projects_status"),
	/**
	 *Get the usage data of a specific project.
	 */
	PROJECT_USAGE_GET("Get the usage data of a specific project", RequestMethod.POST,"https://api.agora.io/v3/usage"),
	/**
	 * Set the recording server IP.
	 */
	RECORDING_CONFIG_POST("Set the recording server IP.", RequestMethod.POST,"https://api.agora.io/v1/recording_config"),
	/**
	 * Enable or disable the primary App certificate.
	 */
	SIGNKEY_POST("Enable or disable the primary App certificate.", RequestMethod.POST,"https://api.agora.io/v1/signkey"),
	/**
	 *Reset the primary App certificate.
	 */
	SIGNKEY_RESET_POST("Reset the primary App certificate.", RequestMethod.POST,"https://api.agora.io/v1/reset_signkey"),

	// ---------------- Kicking (ban) rules ------------------

	/**
	 * Create a kicking (ban) rule.
	 */
	KICKING_RULE_POST("Create a kicking (ban) rule.", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),
	/**
	 * List kicking (ban) rules.
	 */
	KICKING_RULE_GET("List kicking (ban) rules.", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),
	/**
	 * Update the effective time of a kicking (ban) rule.
	 */
	KICKING_RULE_PUT("Update the effective time of a kicking (ban) rule.", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),
	/**
	 * Delete a kicking (ban) rule.
	 */
	KICKING_RULE_DELETE("Delete a kicking (ban) rule.", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),

	// ---------------- FaceSet management API group ------------------

	/**
	 * Create a FaceSet. API
	 * URL: https://console.faceplusplus.com.cn/documents/4888391
	 */
	FACESET_CREATE("Create a FaceSet.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/create"),
	/**
	 * Delete a FaceSet. API
	 * URL: https://console.faceplusplus.com.cn/documents/4888393
	 */
	FACESET_DELETE("Delete a FaceSet.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/delete"),
	/**
	 * Update FaceSet information. API
	 * URL: https://console.faceplusplus.com.cn/documents/4888401
	 */
	FACESET_UPDATE("Update FaceSet information.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/update"),
	/**
	 * Get the FaceSet list and information. API
	 * URL: https://console.faceplusplus.com.cn/documents/4888397
	 */
	FACESET_LIST("Get the FaceSet list and information.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/getfacesets"),
	/**
	 * Get FaceSet detail API.
	 * URL: https://console.faceplusplus.com.cn/documents/4888395
	 */
	FACESET_DETAIL("Get the FaceSet list and information.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/getdetail"),

	/**
	 * Add faces.API
	 * URL: https://console.faceplusplus.com.cn/documents/4888389
	 */
	FACE_ADD("Add faces.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/addface"),
	/**
	 * Remove faces. API
	 * URL: https://console.faceplusplus.com.cn/documents/4888399
	 */
	FACE_REMOVE("Remove faces.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/removeface"),

	/**
	 * Add face (async) API.
	 * URL: https://console.faceplusplus.com.cn/documents/40622166
	 */
	FACE_ADD_ASYNC("Add faces (async)", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/async/addface"),
	/**
	 * Remove face (async) API.
	 * URL: https://console.faceplusplus.com.cn/documents/40622169
	 */
	FACE_REMOVE_ASYNC("Remove faces (async)", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/async/removeface"),
	/**
	 * Async add/remove face result query API.
	 * URL: https://console.faceplusplus.com.cn/documents/40622157
	 */
	FACE_STATUS_ASYNC("Async add/remove face result query", RequestMethod.POST," https://api-cn.faceplusplus.com/facepp/v3/faceset/async/task_status"),

	// ---------------- Face management API group ------------------

	/**
	 * Customise face information (set user_id). API
	 * URL: https://console.faceplusplus.com.cn/documents/4888387
	 */
	FACE_SET_USERID("Customise face information (set user_id).", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/face/setuserid"),

	/**
	 * Get face information. API
	 * URL: https://console.faceplusplus.com.cn/documents/4888385
	 */
	FACE_GET_DETAIL("Get face information.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/face/getdetail"),



	// ---------------- Face recognition API group ------------------

	/**
	 * Face detection (Detect). API
	 * URL: https://console.faceplusplus.com.cn/documents/4888373
	 */
	FACE_DETECT("Face detection (Detect).", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/detect"),

	/**
	 * Face analysis (Analyze). API
	 * URL: https://console.faceplusplus.com.cn/documents/4888383
	 */
	FACE_ANALYZE("Face analysis (Analyze).", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/face/analyze"),

	/**
	 * Face comparison (Compare). API
	 * URL: https://console.faceplusplus.com.cn/documents/4887586
	 */
	FACE_COMPARE("Face comparison (Compare).", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/compare"),
	/**
	 * Face search (Search). API
	 * URL: https://console.faceplusplus.com.cn/documents/4888381
	 */
	FACE_SEARCH("Face search (Search).", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/search"),
	/**
	 * Skin analysis - basic. API
	 * URL: https://console.faceplusplus.com.cn/documents/119745378
	 */
	FACE_SKIN_ANALYZE("Skin analysis - basic.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v1/skinanalyze"),
	/**
	 * Skin analysis - advanced. API
	 * URL: https://console.faceplusplus.com.cn/documents/140781002
	 */
	FACE_SKIN_ANALYZE_ADVANCED("Skin analysis - advanced.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v1/skinanalyze_advanced"),
	/**
	 * Skin analysis - professional. API
	 * URL: https://console.faceplusplus.com.cn/documents/307316314
	 */
	FACE_SKIN_ANALYZE_PRO("Skin analysis - professional.", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v1/skinanalyze_pro"),
	 ;

	private String opt;

	private RequestMethod method;
	private String url;

    FaceppApiAddress(String opt, RequestMethod method, String url) {
		this.opt = opt;
		this.method = method;
		this.url = url;
	}

	/** @return return the opt. */
	public String getOpt() {
		return opt;
	}

	/** @return return the method. */
	public RequestMethod getMethod() {
		return method;
	}

	/** @return return the url. */
	public String getUrl() {
		return url;
	}

	/** @return return the url. */
	public String getUrl(Object ...args) {
		return MessageFormat.format(url, args);
	}

}
