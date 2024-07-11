package hochenchong.mybatis.mapping;

/**
 * @author hochenchong
 * @date 2024/07/11
 */
public class MappedStatement {
    private String id;

    private String sql;

    private Class<?> resultType;

    private String methodName;

    private SqlCommandType sqlCommandType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }
}
