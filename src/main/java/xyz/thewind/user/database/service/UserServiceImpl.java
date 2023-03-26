package xyz.thewind.user.database.service;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.thewind.user.database.bean.User;
import xyz.thewind.user.database.repo.UserRepository;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public boolean isExits(long uid) {
        return findUserByUid(uid) != null;
    }

    @Override
    @Nullable
    public User findUserByUid(long uid) {
        return repo.findFirstByUid(uid);
    }

    @Override
    public int updateLevelByUid(long uid, int level) {
        return repo.updateLevelByUid(uid, level);
    }

    @Override
    public int updateNickNameByUid(long uid, String nickName, String token) {
        return repo.updateNickNameByUid(uid, nickName, token);
    }

    @Override
    public int updateHeaderUrlByUid(long uid, String headerUrl, String token) {
        return repo.updateHeaderUrlByUid(uid, headerUrl, token);
    }

    @Override
    public int updateVipInfoByUid(long uid, boolean isVip, long vipStartTime, long vipEndTime, String token) {
        return repo.updateVipInfoByUid(uid, isVip, vipStartTime,vipEndTime, token);
    }

    @Override
    public int getUserLevelByUid(long uid) {
        User u = findUserByUid(uid);
        if (u == null) return -1;
        return u.getLevel();
    }

    @Override
    public boolean isVip(long uid) {
        User u = findUserByUid(uid);
        if (u == null) return false;
        return u.isVip();
    }
}
