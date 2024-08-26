package hochenchong.chapter.chapter16;

import lombok.Data;

/**
 * @author hochenchong
 * @date 2024/08/24
 */
@Data
public class Session {
    private String userId;

    private String username;

    public Session() {
    }

    public Session(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    @Override
    public String toString() {
        return userId + ":" + username;
    }
}
