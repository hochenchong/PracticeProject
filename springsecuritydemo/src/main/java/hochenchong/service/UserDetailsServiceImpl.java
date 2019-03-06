package hochenchong.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取密文,这里可以从数据库获取
        String password = new BCryptPasswordEncoder().encode("123456");

        // 用户授权，可以创建多个角色
        List<GrantedAuthority> grantlist = new ArrayList<GrantedAuthority>();
        grantlist.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        grantlist.add(new SimpleGrantedAuthority("ROLE_USER"));

        // 第一个参数是用户名，方法直接传入了。
        // 第二个参数是数据库查询出的密码，这里我们直接模拟了。
        // 第三个参数是用户角色授权信息
        User user = new User(username, password, grantlist);
        return user;
    }
}
