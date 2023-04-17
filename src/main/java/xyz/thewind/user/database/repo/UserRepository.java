package xyz.thewind.user.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import xyz.thewind.user.database.bean.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

    User findTopById(long uid);

    User findTopByUserName(String userName);

    User findTopByOpenId(String openId);

    User findTopByIdAndToken(long id, String token);

    User findTopByUserNameAndToken(String userName, String token);

    @Modifying
    @Query("update User set level = :level where id = :id")
    int updateLevelById(long id, int level);

    @Modifying
    @Query("update User set userName = :userName where id = :id and token = :token")
    int updateUserNameById(long id, String userName, String token);

    @Modifying
    @Query("update User set token = :token where openId = :openId")
    int updateTokenByOpenId(String openId, String token);

    @Modifying
    @Query("update User set token = :token where id = :id")
    int updateTokenById(long id, String token);


    @Modifying
    @Query("update User set headerUrl = :headerUrl where id = :id and token = :token")
    int updateHeaderUrlById(long id, String headerUrl, String token);

    @Modifying
    @Query("update User set userDesc = :userDesc where id = :id and token = :token")
    int updateUserDescById(long id, String userDesc, String token);

    @Modifying
    @Query("update User set isVip = :isVip, vipStartTime = :vipStartTime, vipEndTime = :vipEndTime where id = :id and token = :token")
    int updateVipInfoById(long id, boolean isVip, long vipStartTime, long vipEndTime, String token);


}
