package com.copote.utils;

import java.util.UUID;

/**
 * 
 * @author Qingzhen Li
 * 
 * 生成UUID并做格式化处理
 */
public class UUIDTool {
	
	public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
   }
}
