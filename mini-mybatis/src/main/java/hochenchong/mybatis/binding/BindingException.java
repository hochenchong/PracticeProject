package hochenchong.mybatis.binding;

/**
 * 实际上，是继承于 PersistenceException
 *
 * @author hochenchong
 * @date 2024/07/10
 */
public class BindingException extends RuntimeException {
    public BindingException() {
    }

    public BindingException(String message) {
        super(message);
    }

    public BindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindingException(Throwable cause) {
        super(cause);
    }
}
