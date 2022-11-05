package com.zyf.reggie.Controller;

import com.aliyuncs.utils.StringUtils;
import com.zyf.reggie.Service.UserService;
import com.zyf.reggie.common.R;
import com.zyf.reggie.pojo.User;
import com.zyf.reggie.tool.SMSUtils;
import com.zyf.reggie.tool.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        String phone = user.getPhone();
        if (!StringUtils.isEmpty(phone)){
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info(code);
            //SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);
            session.setAttribute(phone,code);
              return R.success("短信发送成功");
        }



        return R.success("短信发送失败");
    }
      @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession session){
        String phone =(String) map.get("phone");
        String code = (String) map.get("code");
        User user = userService.userByPhone(phone);
          Object attributeCode = session.getAttribute(phone);
          if (attributeCode!=null&&attributeCode.equals(code)){
           if (user==null){
               user = new User();
               user.setId(new Random().nextLong());
               user.setPhone(phone);
               userService.insertOne(user);

           }
           session.setAttribute("user",user.getId());
           return R.success(user);
       }
        return R.error("登录失败");
    }
    @PostMapping("/loginout")
    public R<String> loginOut(HttpSession session){
        session.removeAttribute("user");
        return R.success("退出成功");
    }
}
