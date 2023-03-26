package xyz.thewind.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.thewind.global.util.NumberUtil;
import xyz.thewind.global.util.UrlUtil;
import xyz.thewind.global.util.encrypt.rsa.RsaEncryptUtil;
import xyz.thewind.global.util.json.JsonUtilsKt;
import xyz.thewind.user.bean.CommonResponse;
import xyz.thewind.user.database.bean.User;
import xyz.thewind.user.database.service.UserService;

import java.util.Map;

@RestController
public class UserFunctionController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @PostMapping("/user/api/login")
    public String login(@RequestBody String requestBody) {
        String clearText = RsaEncryptUtil.decrypt(requestBody);
        Map<String, String> map = UrlUtil.urlToKv(clearText);
        if (map.isEmpty()) return "";
        String uid = map.get("uid");
        String token = map.get("token");
        if (uid == null || token == null) return "";
        return JsonUtilsKt.toJson(userService.loginByUidAndToken(NumberUtil.getOrDefault(uid, -1), token));
    }

    @RequestMapping("/user/api/register")
    public ResponseEntity<CommonResponse> register(String userName, String token, String verifyCode) {
        CommonResponse commonResponse = new CommonResponse();
        if (userName == null || token == null || verifyCode == null) {
            commonResponse.setCode(-1);
            commonResponse.setMessage("注册信息不合法");
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        if (userService.findUserByName(userName) != null) {
            commonResponse.setCode(-1);
            commonResponse.setMessage("已有该用户名，请换一个");
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        String openId = redisTemplate.opsForValue().get(verifyCode);
        if (openId != null && openId.length() > 0) {
            User user = new User();
            user.setUserName(userName);
            user.setToken(token);
            user.setRegisterTime(System.currentTimeMillis());
            user.setLevel(1);
            user.setNickName("注册用户" + userName);
            user.setOpenId(openId);
            User u = userService.createUser(user);
            if (u == null) {
                commonResponse.setCode(-1);
                commonResponse.setMessage("创建用户失败");
            } else  {
                commonResponse.setCode(0);
                commonResponse.setMessage("注册成功，请登陆");
            }
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        commonResponse.setCode(-1);
        commonResponse.setMessage("验证码已失效，请重新获取");
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }


}
