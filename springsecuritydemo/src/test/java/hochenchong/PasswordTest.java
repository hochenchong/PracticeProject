package hochenchong;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    /*
        BCryptPasswordEncoder 每次加密的结果都不同
     */
    @Test
    public void encodePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
        Assert.assertTrue(encoder.matches("123456", "$2a$09$F3SwGvj5Zn.pNLsHd.f3q.q2poP0BZ7r0G5/nHUdu7AlDByDOvg2q"));
    }
}
