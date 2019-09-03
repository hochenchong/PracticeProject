package hochenchong.args;

/**
 * @author HochenChong
 * @date 2019/09/01
 */
public class Args {
    private Schemas schemas;
    private Command command;

    public Args(String schema, String command) {
        this.schemas = new Schemas(schema);
        this.command = new Command(command);
    }


    public Object getValue(String key) {
        String value = command.getValue(key);
        return schemas.getValue(key, value);
    }
}
