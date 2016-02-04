package com.chen.adapterview;

import java.io.Serializable;
import java.util.List;

public class QQGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 分组名称
	 */
	public String groupName;
	
	/**
	 * 组员
	 */
	public List<QQChild> childList;

}
