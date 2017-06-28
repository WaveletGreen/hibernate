package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javassist.expr.NewArray;

/**
 * 敏捷开发测试
 * 
 * @author Administrator
 * 
 */
public class AgileDevelopment {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		buildEntityClass("User", "id/int", "userName/String");

	}

	public static void buildEntityClass(String fileName, String... attributes)
			throws IOException {
		// 目标文件夹的上层路径
		String path = "cn.com.hzhy.hibernate.www/";
		// 存放实体类的路径
		File file = new File(path + "entity/");
		// 如果路径不存在的新建
		if (!file.exists()) {
			file.mkdirs();
		}
		// 根据Model.txt创建的java文件
		File createdFile = new File(file.getPath() + "/" + fileName + ".java");
		// Model.txt的路径
		File modelFile = new File(path + "/entityModel/EntityModel.txt");
		// 使用buffered读取和写文件
		BufferedReader reader = new BufferedReader(new FileReader(modelFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(createdFile));
		String[] oldAttrs = null;
		String[] types = null;
		String[] newAttrs = null;
		if (attributes.length > 0) {
			oldAttrs = new String[attributes.length];
			types = new String[attributes.length];
			newAttrs = new String[attributes.length];
			for (int i = 0; i < types.length; i++) {
				oldAttrs[i] = attributes[i];
				types[i] = attributes[i];
			}
			// 截取属性的类型
			for (int i = 0; i < types.length; i++) {
				types[i] = types[i].split("/")[1];
			}
			for (int i = 0; i < newAttrs.length; i++) {
				newAttrs[i] = oldAttrs[i].replaceFirst(
						oldAttrs[i].substring(0, 1),
						oldAttrs[i].substring(0, 1).toUpperCase()).split("/")[0];
				System.out.println(newAttrs[i]);
			}
		}
		int i = 0;
		while (reader.ready()) {
			String buffer = reader.readLine();
			if (newAttrs.length > 0) {
				if (types.length > 0) {
					buffer.replace("_Type", types[i]);
				}
				buffer.replace("_Attr", newAttrs[i]);
			}
			buffer.replace("_Agile", fileName);
			writer.write(buffer);
			writer.newLine();
		}
		writer.flush();
		writer.close();
		reader.close();

	}
}
