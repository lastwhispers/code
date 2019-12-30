package book.chapter05;

import org.apache.zookeeper.data.Stat;

/**
 *
 * @author <a href="mailto:nileader@gmail.com">银时</a>
 * 
 */
public class StatUtils {

    public static String printStat(Stat stat) {
        if (null == stat) {
            return "";
        }
        return "Stat[czxid=" + stat.getCzxid() + ", mzxid=" + stat.getMzxid() + ", version=" + stat.getVersion() + "]";
    }
}
