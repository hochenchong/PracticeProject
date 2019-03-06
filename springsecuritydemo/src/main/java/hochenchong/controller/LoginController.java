package hochenchong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login/fail")
    public String loginFail(Model model) {
        model.addAttribute("msg", "账号或密码不正确！");
        return "login";
    }
}
