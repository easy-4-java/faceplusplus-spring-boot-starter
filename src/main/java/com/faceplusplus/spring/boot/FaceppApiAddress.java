package com.faceplusplus.spring.boot;

import java.text.MessageFormat;

/**
 * https://console.faceplusplus.com.cn/documents/268763412
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
public enum FaceppApiAddress {

	// ---------------- 人脸识别相关 ------------------

	/**
	 * 获取云端录制资源ID
	 * URL: https://api.agora.io/v1/apps/<yourappid>/cloud_recording/acquire
	 */
	ACQUIRE_RESOURCE_ID("人脸检测 API", RequestMethod.POST,"/facepp/v3/detect"),
	/**
	 * 开始云端录制
	 * URL: https://api.agora.io/v1/apps/<yourappid>/cloud_recording/resourceid/<resourceid>/mode/<mode>/start
	 */
	START_CLOUD_RECORDING("人脸分析 API", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/mode/{2}/start"),
	/**
	 * 更新云端录制
	 * URL: https://api.agora.io/v1/apps/<appid>/cloud_recording/resourceid/<resourceid>/sid/<sid>/mode/<mode>/updateLayout
	 */
	UPDATE_CLOUD_RECORDING("稠密关键点 API", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/updateLayout"),
	/**
	 * 更新合流布局
	 * URL: https://api.agora.io/v1/apps/<appid>/cloud_recording/resourceid/<resourceid>/sid/<sid>/mode/<mode>/update
	 */
	UPDATE_CLOUD_RECORDING_LAYOUT("人脸比对 API", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/update"),
	/**
	 * 查询云端录制状态
	 * URL: https://api.agora.io/v1/apps/<yourappid>/cloud_recording/resourceid/<resourceid>/sid/<sid>/mode/<mode>/query
	 */
	QUERY_CLOUD_RECORDING("查询云端录制状态", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/query"),
	/**
	 * 停止云端录制
	 * URL:  https://api.agora.io/v1/apps/<yourappid>/cloud_recording/resourceid/<resourceid>/sid/<sid>/mode/<mode>/stop
	 */
	STOP_CLOUD_RECORDING("停止云端录制", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/stop"),

	// ---------------- 项目管理 ------------------

	/**
	 * 创建项目
	 */
	PROJECT_POST("创建项目", RequestMethod.POST,"https://api.agora.io/v1/project"),
	/**
	 * 获取指定项目
	 */
	PROJECT_GET("获取指定项目", RequestMethod.POST,"https://api.agora.io/v1/project"),
	/**
	 * 获取所有项目
	 */
	PROJECTS_GET("获取所有项目", RequestMethod.POST,"https://api.agora.io/v1/projects"),
	/**
	 * 禁用或启用项目
	 */
	PROJECT_STATUS_POST("禁用或启用项目", RequestMethod.POST,"https://api.agora.io/v1/projects_status"),
	/**
	 *获取指定项目的用量数据
	 */
	PROJECT_USAGE_GET("获取指定项目的用量数据", RequestMethod.POST,"https://api.agora.io/v3/usage"),
	/**
	 * 设置录制服务器 IP
	 */
	RECORDING_CONFIG_POST("设置录制服务器 IP", RequestMethod.POST,"https://api.agora.io/v1/recording_config"),
	/**
	 * 启用或禁用主要 App 证书
	 */
	SIGNKEY_POST("启用或禁用主要 App 证书", RequestMethod.POST,"https://api.agora.io/v1/signkey"),
	/**
	 *重置主要 App 证书
	 */
	SIGNKEY_RESET_POST("重置主要 App 证书", RequestMethod.POST,"https://api.agora.io/v1/reset_signkey"),

	// ---------------- 封禁用户权限 ------------------

	/**
	 * 创建封禁用户权限规则
	 */
	KICKING_RULE_POST("创建封禁用户权限规则", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),
	/**
	 * 获取封禁用户权限规则列表
	 */
	KICKING_RULE_GET("获取封禁用户权限规则列表", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),
	/**
	 * 更新封禁用户权限规则的生效时间
	 */
	KICKING_RULE_PUT("更新封禁用户权限规则的生效时间", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),
	/**
	 * 删除封禁用户权限规则
	 */
	KICKING_RULE_DELETE("删除封禁用户权限规则", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),

	// ---------------- 人脸库管理 API 组 ------------------

	/**
	 * 创建人脸库 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888391
	 */
	FACESET_CREATE("创建人脸库", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/create"),
	/**
	 * 删除人脸库 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888393
	 */
	FACESET_DELETE("删除人脸库", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/delete"),
	/**
	 * 更新人脸库信息 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888401
	 */
	FACESET_UPDATE("更新人脸库信息", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/update"),
	/**
	 * 获取人脸库列表及信息 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888397
	 */
	FACESET_LIST("获取人脸库列表及信息", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/getfacesets"),
	/**
	 * 获取人脸库信息 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888395
	 */
	FACESET_DETAIL("获取人脸库列表及信息", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/getdetail"),

	/**
	 * 添加人脸API
	 * URL: https://console.faceplusplus.com.cn/documents/4888389
	 */
	FACE_ADD("添加人脸", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/addface"),
	/**
	 * 删除人脸 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888399
	 */
	FACE_REMOVE("删除人脸", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/removeface"),

	/**
	 * 添加人脸（异步） API
	 * URL: https://console.faceplusplus.com.cn/documents/40622166
	 */
	FACE_ADD_ASYNC("添加人脸（异步）", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/async/addface"),
	/**
	 * 删除人脸（异步） API
	 * URL: https://console.faceplusplus.com.cn/documents/40622169
	 */
	FACE_REMOVE_ASYNC("删除人脸（异步）", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/faceset/async/removeface"),
	/**
	 * 添加/删除人脸结果查询 API
	 * URL: https://console.faceplusplus.com.cn/documents/40622157
	 */
	FACE_STATUS_ASYNC("添加/删除人脸结果查询", RequestMethod.POST," https://api-cn.faceplusplus.com/facepp/v3/faceset/async/task_status"),

	// ---------------- 人脸库中的人脸管理 API 组 ------------------

	/**
	 * 自定义人脸信息 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888387
	 */
	FACE_SET_USERID("自定义人脸信息", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/face/setuserid"),

	/**
	 * 获取人脸信息  API
	 * URL: https://console.faceplusplus.com.cn/documents/4888385
	 */
	FACE_GET_DETAIL("获取人脸信息 ", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/face/getdetail"),



	// ---------------- 人脸识别 API 组 ------------------

	/**
	 * 人脸检测 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888373
	 */
	FACE_DETECT("人脸检测", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/detect"),

	/**
	 * 人脸分析 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888383
	 */
	FACE_ANALYZE("人脸分析", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/face/analyze"),

	/**
	 * 人脸比对 API
	 * URL: https://console.faceplusplus.com.cn/documents/4887586
	 */
	FACE_COMPARE("人脸比对", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/compare"),
	/**
	 * 人脸搜索 API
	 * URL: https://console.faceplusplus.com.cn/documents/4888381
	 */
	FACE_SEARCH("人脸搜索", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v3/search"),
	/**
	 * 皮肤分析-基础版 API
	 * URL: https://console.faceplusplus.com.cn/documents/119745378
	 */
	FACE_SKIN_ANALYZE("皮肤分析-基础版", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v1/skinanalyze"),
	/**
	 * 皮肤分析-高阶版 API
	 * URL: https://console.faceplusplus.com.cn/documents/140781002
	 */
	FACE_SKIN_ANALYZE_ADVANCED("皮肤分析-高阶版", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v1/skinanalyze_advanced"),
	/**
	 * 皮肤分析-专业版 API
	 * URL: https://console.faceplusplus.com.cn/documents/307316314
	 */
	FACE_SKIN_ANALYZE_PRO("皮肤分析-专业版", RequestMethod.POST,"https://api-cn.faceplusplus.com/facepp/v1/skinanalyze_pro"),
	 ;

	private String opt;

	private RequestMethod method;
	private String url;

    FaceppApiAddress(String opt, RequestMethod method, String url) {
		this.opt = opt;
		this.method = method;
		this.url = url;
	}

	public String getOpt() {
		return opt;
	}

	public RequestMethod getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}

	public String getUrl(Object ...args) {
		return MessageFormat.format(url, args);
	}

}
