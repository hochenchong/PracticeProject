package hochenchong.mybatis.mapping;

import javax.sql.DataSource;

/**
 *
 * org.apache.ibatis.mapping.Environment
 *
 * @author hochenchong
 * @date 2024/07/10
 */
public class Environment {
    private final String id;
    private final DataSource dataSource;

    public Environment(String id, DataSource dataSource) {
        this.id = id;
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }
}
