package cn.lastwhisper.elasticsearch.operation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author lastwhisper
 * @date 2019/12/1
 */
public class DateUtils {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String dateFormat(Date date) {
        return sdf.format(date);
    }

}
