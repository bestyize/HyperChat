package xyz.thewind.user.database.service;

import xyz.thewind.user.database.bean.User;

public interface UserService {

    User createUser(User user);

    boolean isExits(long uid);

    User findUserByUid(long uid);

    User findUserByName(String userName);

    User findUserByOpenId(String openId);

    User loginByUidAndToken(long uid, String token);

    User loginByUserNameAndToken(String userName, String token);

    int updateTokenByOpenId(String openId, String token);

    int updateLevelByUid(long uid, int level);

    int updateNickNameByUid(long uid, String nickName, String token);

    int updateHeaderUrlByUid(long uid, String headerUrl, String token);

    int updateVipInfoByUid(long uid, boolean isVip, long vipStartTime, long vipEndTime, String token);

    int getUserLevelByUid(long uid);

    boolean isVip(long uid);


}
