package cn.lastwhisper.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author lastwhisper
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {

        return DbContextHolder.get();
    }

}
