package com.example.web.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileUtil {

	private static  final Logger logger=Logger.getLogger(FileUtil.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Object value=(long)123;
		
		Class clazz=value.getClass();
		String clazzName=clazz.getName();
		System.out.println("-->>>"+(clazzName.substring(clazzName.lastIndexOf(".")+1)));
		clazz=null;
		value=null;
	}
	
	
	 /**
	  * @Title: getExtNameByFile   
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param file
	  * @return String 返回类型 
	  **/
	public static String getExtNameByFile(File file){
		String extName="";
		if(file != null){
			String filePathName=file.getName();
			int dot = filePathName.lastIndexOf('.');   
			if ((dot >-1) && (dot < (filePathName.length() - 1))) {   
				extName = filePathName.substring(dot + 1);   
            }
			filePathName = null;
		}
		return extName;
	}
	

	/**
	 * 根据文件路径获取文件扩展名
	 * */
	public static String getExtNameByPathName(String filePathName){
		String extName="";
		if(filePathName != null && !"".equals(filePathName)){
			int dot = filePathName.lastIndexOf('.'); 
			if ((dot >-1) && (dot < (filePathName.length() - 1))) {   
				extName = filePathName.substring(dot + 1);   
            }   
		}
		return extName;
	}
	
	/**
	 * 
	* 方法名称:copyFile
	* 方法简介:复制文件到另一个文件中
	* 备注: 
	* 1.FileInputStream 用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用 FileReader
	* 2.目标文件不存在可新建(目录文件所在文件夹须存在)
	* @param srcFilePath  源文件
	* @param desFilePath  目标文件
	*/
	public static boolean copyFile(String srcFilePath, String desFilePath){
		boolean flag = true;
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(srcFilePath);
			//目标文件已存在？
			File desFile = new File(desFilePath);
			//如果desFile不存在就会被创建出来
			//new FileOutputStream(desFile, true);    为true时，从文件末尾开始写入，反之才能够文件开头写入
			out = new FileOutputStream(desFile);
			BufferedOutputStream bfOut = new BufferedOutputStream(out);
			byte[] bytearray = new byte[1024];
			do {
				in.read(bytearray, 0, 1024);
				bfOut.write(bytearray);
			} while (in.available() > 0);
			bfOut.close();
		} catch (FileNotFoundException e) {
			logger.error("复制文件错误"+e);
			flag = false;
		} catch (IOException e) {
			logger.error("复制文件错误"+e);
			flag = false;
		} finally{
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					System.err.println("文件流关闭出错："+e);
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					System.err.println("文件流关闭出错："+e);
				}
			}
		}
		return flag;
	}
	
	public static void createDirs(String dir) {
		try {
			File file = new File(dir);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	
}
