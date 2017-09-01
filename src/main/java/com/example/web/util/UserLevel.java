package com.example.web.util;

/**
 * 
 * @ClassName: UserLevel 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @date 2017年9月1日 下午1:28:35 
 */
public enum UserLevel {

	LOWER(1,"普通工程师"),MIDDLE(2,"中级工程师"),SENIOR(3,"高级工程师");
	
	private int status;

	private String statusString;
	
	UserLevel(int statu,String statuString){
		this.status = statu;
		this.statusString = statuString;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusString() {
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	
	public static int getStatu(String statuString){
		
		statuString = statuString.toLowerCase();
		UserLevel[] callTypes = UserLevel.values();
		for (int i = 0; i < callTypes.length; i++) {
			UserLevel ct = callTypes[i];
			
			if(ct.getStatusString().equals(statuString)){
				return ct.getStatus();
			}
		}
		return LOWER.getStatus();
	}

	
}
