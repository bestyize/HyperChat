package xyz.thewind.user.database.service;

import xyz.thewind.user.database.bean.User;

public interface UserService {

    User createUser(User user);

    boolean isExits(long uid);

    User findUserById(long uid);

    User findUserByName(String userName);

    User findUserByOpenId(String openId);

    User loginByIdAndToken(long uid, String token);

    User findUserByIdAndToken(long uid, String token);

    User loginByUserNameAndToken(String userName, String token);

    int updateTokenByOpenId(String openId, String token);

    int updateLevelById(long uid, int level);

    int updateUserNameById(long uid, String userName, String token);

    int updateTokenById(long uid, String token);

    int updateHeaderUrlById(long uid, String headerUrl, String token);

    int updateUserDescById(long uid, String desc, String token);

    int updateVipInfoById(long uid, boolean isVip, long vipStartTime, long vipEndTime, String token);

    int getUserLevelById(long uid);

    boolean isVip(long uid);


}
