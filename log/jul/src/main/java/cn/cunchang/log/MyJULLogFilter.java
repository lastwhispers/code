package cn.cunchang.log;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * @author cunchang
 */
public class MyJULLogFilter implements Filter {

    /**
     *
     * @param record
     * @return
     */
    @Override
    public boolean isLoggable(LogRecord record) {
        if (record.getLoggerName().equals("cn.cunchang.log")) {
            return false;
        }
        return true;
    }

}
