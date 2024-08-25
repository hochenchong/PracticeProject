package hochenchong.chapter.chapter16;

/**
 * @author hochenchong
 * @date 2024/08/24
 */
public class Session {
    private String userId;

    private String username;

    public Session() {
    }

    public Session(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return userId + ":" + username;
    }
}
