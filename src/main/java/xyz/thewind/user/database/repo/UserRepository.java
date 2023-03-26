package xyz.thewind.user.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import xyz.thewind.user.database.bean.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

    User findTopByUid(long uid);

    User findTopByUserName(String userName);

    User findTopByOpenId(String openId);

    User findTopByUidAndToken(long uid, String token);

    User findTopByUserNameAndToken(String userName, String token);

    @Modifying
    @Query("update User set level = :level where uid = :uid")
    int updateLevelByUid(long uid, int level);

    @Modifying
    @Query("update User set nickName = :nickName where uid = :uid and token = :token")
    int updateNickNameByUid(long uid, String nickName, String token);

    @Modifying
    @Query("update User set token = :token where openId = :openId")
    int updateTokenByOpenId(String openId, String token);

    @Modifying
    @Query("update User set headerUrl = :headerUrl where uid = :uid and token = :token")
    int updateHeaderUrlByUid(long uid, String headerUrl, String token);

    @Modifying
    @Query("update User set isVip = :isVip, vipStartTime = :vipStartTime, vipEndTime = :vipEndTime where uid = :uid and token = :token")
    int updateVipInfoByUid(long uid, boolean isVip, long vipStartTime, long vipEndTime, String token);


}
