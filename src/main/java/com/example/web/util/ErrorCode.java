package com.example.web.util;

/**
 * @ClassName: ErrorCode
 * @Description: RESTful API 错误码
 * @date 2017年9月1日 下午3:32:18
 *
 */
public class ErrorCode {

	public static final int OPERATION_SUCCESS = 200; // 操作成功

	public static final int TOKEN_ERROR = 401; // 用户未认证，请求失败 token 验证失败

	public static final int AUTH_FAILURE = 403; // 没有授权的，例如项目查询
	
	public static final int NO_RESOURCE = 410; // 资源不存在

	public static final int ILLEGAL_PARAM = 422; // 无效参数

	public static final int SERVER_ERROR = 500; // 服务器内部错误

}
