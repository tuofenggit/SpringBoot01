package com.example.web.util;

public class ConstantUtil {

	public static final int TIMEOUT = 100000;
	
	public static final Integer PAGESIZE_DEFAULT = 10;//获取列表时每页默认记录数
	
	public static final Integer INVALID_STATUS =0;//所有数据无效状态
	
	public static final Integer DELFLAG_UNDELETE =   1;// 正常使用
	public static final Integer DELFLAG_DELETED =   2;// 已删除
	
	public static final String PASSWORD_KEY_USER="GOLDWIND_USER_PASSWORD";//URL地址中加密 KEY
	
	public static final String COOKIE_SPLIT="##########";//COOKIE  Value的分割符
	
	public static final String LANG_CN="zh";//中文环境
	public static final String LANG_EN="en";//英文环境
	
	public static final int RESET_PASS_SUCCEED = 1;//重置密码成功
	public static final int RESET_PASS_FAILED = 2; //重置密码失败
	
	public static final String TOKEN_KEY = "goldwind_foam";//token加密关键字
	
}
