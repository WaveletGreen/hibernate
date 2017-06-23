package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 * @author Administrator
 *
 */
public class DateUtil {
	private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 将一个字符串解析为Date对象
	 * @param strDate
	 * @return
	 */
	public static Date parse(String strDate){
		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 按指定的字符串格式格式化Date对象
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		return format.format(date);
	}
}
