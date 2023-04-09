package xyz.thewind.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.thewind.global.util.NumberUtil;
import xyz.thewind.global.util.StringCheckUtil;
import xyz.thewind.global.util.UrlUtil;
import xyz.thewind.global.util.encrypt.rsa.RsaEncryptUtil;
import xyz.thewind.global.util.json.JsonUtilsKt;
import xyz.thewind.music.util.StringUtils;
import xyz.thewind.user.bean.CommonResponse;
import xyz.thewind.user.bean.UpdateUserInfoResponse;
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
    public ResponseEntity<CommonResponse> login(@RequestBody String requestBody) {
        CommonResponse response = new CommonResponse();
        response.setCode(-1);
        String clearText = RsaEncryptUtil.decrypt(requestBody);
        Map<String, String> map = UrlUtil.urlToKv(clearText);
        if (map.isEmpty()) {
            response.setMessage("请求信息校验失败");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        String userName = map.get("userName");
        String token = map.get("token");
        if (userName == null || token == null) {
            response.setMessage("帐号或密码错误，如忘记请去公众号重置");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        String data = JsonUtilsKt.toJson(userService.loginByUserNameAndToken(userName, token));
        if (data.isEmpty()) {
            response.setMessage("帐号或密码错误，如忘记请去公众号重置");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setCode(0);
        response.setMessage(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping("/user/api/register")
    public ResponseEntity<CommonResponse> register(String userName, String token, String verifyCode) {
        CommonResponse commonResponse = new CommonResponse();
        boolean userNameInvalid = !StringCheckUtil.INSTANCE.isValidUserName(userName);

        if (userNameInvalid) {
            commonResponse.setCode(-1);
            commonResponse.setMessage("注册信息不合法,用户名必须由数字、字母、下划线、汉字中的一种或多种组成, 长度大于1");
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        boolean passwordInvalid = !StringCheckUtil.INSTANCE.isValidPassword(token);
        if (passwordInvalid) {
            commonResponse.setCode(-1);
            commonResponse.setMessage("注册信息不合法,密码必须由数字、字母、下划线、@符号 组成，至少包含其中的两种，长度大于5");
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }

        if (verifyCode == null) {
            commonResponse.setCode(-1);
            commonResponse.setMessage("注册信息不合法,验证码错误");
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        if (userService.findUserByName(userName) != null) {
            commonResponse.setCode(-1);
            commonResponse.setMessage("已有该用户名，请换一个");
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        String openId = redisTemplate.opsForValue().get(verifyCode);
        if (openId != null && openId.length() > 0) {
            if (userService.findUserByOpenId(openId) != null) {
                commonResponse.setCode(-1);
                commonResponse.setMessage("该微信号已经注册过，若您忘记密码，请到公众号回复:找回帐号");
                return new ResponseEntity<>(commonResponse, HttpStatus.OK);
            }
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

    @RequestMapping("/user/api/update/name")
    public ResponseEntity<UpdateUserInfoResponse> updateUserName(HttpServletRequest request) {
        UpdateUserInfoResponse resp = new UpdateUserInfoResponse();
        String name = request.getParameter("userName");
        boolean isValidName = StringCheckUtil.INSTANCE.isValidUserName(name);
        if (!isValidName) {
            resp.setMsg("新用户名格式校验错误");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        if(StringUtils.isSensitiveWord(name)) {
            resp.setMsg("此为违禁词，请换一个试试");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }

        long uid = NumberUtil.getOrDefault(request.getHeader("uid"), -1);
        if (userService.findUserByName(name) != null) {
            resp.setMsg("用户名已存在");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        String token = request.getHeader("token");
        User user = userService.findUserByUidAndToken(uid, token);
        if (user == null) {
            resp.setMsg("用户登录信息无效");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        int code = userService.updateUserNameByUid(uid, name, token);
        if (code == 0) {
            resp.setMsg("更新失败，请稍后重试");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        resp.setCode(0);
        resp.setMsg("更新成功");
        user.setUserName(name);
        resp.setData(user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @RequestMapping("/user/api/update/token")
    public ResponseEntity<UpdateUserInfoResponse> updateToken(HttpServletRequest request) {
        UpdateUserInfoResponse resp = new UpdateUserInfoResponse();
        String newToken = request.getParameter("token");
        boolean isValid = StringCheckUtil.INSTANCE.isValidPassword(newToken);
        if (!isValid) {
            resp.setMsg("密码不合法");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }

        long uid = NumberUtil.getOrDefault(request.getHeader("uid"), -1);
        String token = request.getHeader("token");
        User user = userService.findUserByUidAndToken(uid, token);
        if (user == null) {
            resp.setMsg("用户登录信息无效");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        int code = userService.updateTokenByUid(uid, newToken);
        if (code == 0) {
            resp.setMsg("更新失败，请稍后重试");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        resp.setCode(0);
        resp.setMsg("更新成功");
        user.setToken(token);
        resp.setData(user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @RequestMapping("/user/api/update/header")
    public ResponseEntity<UpdateUserInfoResponse> updateHeader(HttpServletRequest request) {
        UpdateUserInfoResponse resp = new UpdateUserInfoResponse();
        String header = request.getParameter("header");
        if (header == null || !header.startsWith("https")) {
            resp.setMsg("头像链接无效");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }

        long uid = NumberUtil.getOrDefault(request.getHeader("uid"), -1);
        String token = request.getHeader("token");
        User user = userService.findUserByUidAndToken(uid, token);
        if (user == null) {
            resp.setMsg("用户登录信息无效");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        int code = userService.updateHeaderUrlByUid(uid, header, token);
        if (code == 0) {
            resp.setMsg("更新失败，请稍后重试");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        resp.setCode(0);
        resp.setMsg("更新成功");
        user.setHeaderUrl(header);
        resp.setData(user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



}
