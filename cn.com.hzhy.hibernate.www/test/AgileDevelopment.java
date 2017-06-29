package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

import entity.ch04.City;
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
		// buildEntityClass("User", "id/int", "userName/String");
		// String[] impClass={"Serializable"};
		buildEntityClassFromVoid("Users", null, null, null, "id/int", "userName/String", "password/String");

	}

	public static void buildEntityClass(String fileName, String... attributes) throws IOException {
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
				newAttrs[i] = oldAttrs[i]
						.replaceFirst(oldAttrs[i].substring(0, 1), oldAttrs[i].substring(0, 1).toUpperCase())
						.split("/")[0];
				System.out.println(newAttrs[i]);
			}
		}
		while (reader.ready()) {
			String buffer = reader.readLine();
			if (newAttrs != null) {
				for (int j = 0; j < newAttrs.length; j++) {
					if (types.length > 0) {
						buffer = buffer.replace("_Type", types[j]);
					}
					buffer = buffer.replace("_Attr", newAttrs[j]);
				}

			}
			buffer = buffer.replace("_Agile", fileName);
			writer.write(buffer);
			writer.newLine();
		}
		writer.flush();
		writer.close();
		reader.close();
	}

	/**
	 * 凭空生成一个实体类java文件 ，没错，就是这么牛逼，不依靠模板
	 * 
	 * @param fileName
	 *            <br/>
	 *            &emsp;&emsp;需要生成的实体类java文件的名称
	 * @param packageName
	 *            <br/>
	 *            &emsp;&emsp;需要放置的包名，如果此项为null，则默认放在entity包下
	 * @param extendsClass
	 *            <br/>
	 *            &emsp;&emsp;需要继承的类名，如果此项为null则默认不写，默认继承Object类
	 * @param implementsInf
	 *            <br/>
	 *            &emsp;&emsp;需要实现的接口，可使用String[]来传入多个需要实现的接口
	 * @param attributes
	 *            <br/>
	 *            &emsp;&emsp;该实体类中包含的属性，是可选的，可以传入String[]或者多个字符串，请用<strong>[属性名/类型]</strong>的编写方式传入，如:<br/>
	 *            &emsp;&emsp;
	 *            <p style="padding-left:150px">
	 * 			[属性名/类型]<br/>
	 * 			&emsp;id&emsp;/&emsp;int
	 *            </p>
	 * @throws IOException
	 */

	public static void buildEntityClassFromVoid(String fileName, String packageName, String extendsClass,
			String[] implementsInf, String... attributes) throws IOException {
		/**
		 * 生成文件路径和包
		 */
		// 目标文件夹的上层路径
		String path = "cn.com.hzhy.hibernate.www/";
		// 存放实体类的文件夹
		File file = null;

		// 包名和文件夹名一致
		if (packageName == null) {
			file = new File(path + "entity/");
		} else {
			file = new File(path + packageName + "/");
		}
		// 如果文件夹不存在的新建
		if (!file.exists()) {
			file.mkdirs();
		}
		// 创建java文件
		File createdFile = new File(file.getPath() + "/" + fileName + ".java");
		// writer用于写入文件
		BufferedWriter writer = new BufferedWriter(new FileWriter(createdFile));

		// 写入包名
		if (packageName == null)
			writer.write("package entity;");
		else {
			writer.write("package " + packageName + ";");
		}
		// 新生成两行空行
		writer.newLine();
		writer.newLine();

		// 写入类声明
		writer.write("public class " + fileName + " ");
		// 写入需要继承的类
		if (extendsClass != null) {
			writer.write("extends " + extendsClass + " ");
		}
		// 写入需要实现的接口
		if (implementsInf != null) {
			writer.write("implements ");
			for (int i = 0; i < implementsInf.length; i++) {
				if (i == implementsInf.length - 1) {
					writer.write(implementsInf[i] + " ");
					break;
				}
				writer.write(implementsInf[i] + ", ");
			}
		}
		// 写入类声明的第一个大括号
		writer.write("{\n\n");
		/**
		 * 下面是类的属性和构造方法的编写方式
		 */

		// 根据属性生成相应的属性代码和Setter和Getter
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
			// 截取属性的类型和属性名
			for (int i = 0; i < types.length; i++) {
				types[i] = types[i].split("/")[1];
				oldAttrs[i] = oldAttrs[i].split("/")[0];
				System.out.println(oldAttrs[i]);
			}
			// 截取属性名并未Getter和Setter做准备
			for (int i = 0; i < newAttrs.length; i++) {
				newAttrs[i] = oldAttrs[i].replaceFirst(oldAttrs[i].substring(0, 1),
						oldAttrs[i].substring(0, 1).toUpperCase());
				System.out.println(newAttrs[i]);
			}
			// 写入属性声明
			for (int i = 0; i < newAttrs.length; i++) {
				writer.write("\tprivate " + types[i] + " " + oldAttrs[i] + ";\n");
			}
		}
		// 生成无参的构造函数
		writer.write("\n");
		writer.write("\tpublic " + fileName + "() {\n\t}\n\n");
		// 生成带参的构造方法
		if (attributes != null) {
			StringBuilder withParaments = new StringBuilder();
			withParaments.append("\tpublic " + fileName + "(");
			for (int i = 0; i < newAttrs.length; i++) {
				if (i == newAttrs.length - 1) {
					withParaments.append(types[i] + " " + oldAttrs[i]);
					break;
				}
				withParaments.append(types[i] + " " + oldAttrs[i] + ", ");
			}
			withParaments.append(") {\n\t}\n\n");
			writer.write(withParaments.toString());
		}
		/**
		 * 生成Getter和Setter
		 */
		if (attributes != null) {
			for (int i = 0; i < newAttrs.length; i++) {
				// 生成Getter
				writer.write("\tpublic " + types[i] + " get" + newAttrs[i] + "() {\n\t\treturn this." + oldAttrs[i]
						+ ";\n\t}\n\n");
				// 生成Setter
				writer.write("\tpublic void set" + newAttrs[i] + "(" + types[i] + " " + oldAttrs[i] + ") {\n\t\tthis."
						+ oldAttrs[i] + " = " + oldAttrs[i] + ";\n\t}\n\n");
			}
		}
		// 写入最后一个大括号
		writer.write("}");

		/**
		 * 关闭各种reader和writer
		 */
		// 刷新缓存

		writer.flush();
		writer.close();
	}
}
