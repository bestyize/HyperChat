package xyz.thewind.user.database.service;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.thewind.user.database.bean.User;
import xyz.thewind.user.database.repo.UserRepository;

@Service
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public User createUser(User user) {
        return repo.save(user);
    }

    @Override
    public boolean isExits(long id) {
        return findUserById(id) != null;
    }

    @Override
    @Nullable
    public User findUserById(long id) {
        User user = repo.findTopById(id);
        if (user != null) {
            user.setUid(user.getId());
        }
        return user;
    }

    @Override
    public User findUserByName(String userName) {
        User user = repo.findTopByUserName(userName);
        if (user != null) {
            user.setUid(user.getId());
        }
        return user;
    }

    @Override
    public User findUserByOpenId(String openId) {
        User user = repo.findTopByOpenId(openId);
        if (user != null) {
            user.setUid(user.getId());
        }
        return user;
    }

    @Override
    public User loginByIdAndToken(long id, String token) {
        if (id < 0 || token == null || token.isEmpty()) {
            return null;
        }
        User user = repo.findTopByIdAndToken(id, token);
        if (user != null) {
            user.setUid(user.getId());
        }
        return user;
    }

    @Override
    public User findUserByIdAndToken(long id, String token) {
        return loginByIdAndToken(id, token);
    }

    @Override
    public User loginByUserNameAndToken(String userName, String token) {
        User user = repo.findTopByUserNameAndToken(userName, token);
        if (user != null) {
            user.setUid(user.getId());
        }
        return user;
    }

    @Override
    public int updateTokenByOpenId(String openId, String token) {
        return repo.updateTokenByOpenId(openId, token);
    }

    @Override
    public int updateLevelById(long id, int level) {
        return repo.updateLevelById(id, level);
    }

    @Override
    public int updateUserNameById(long id, String userName, String token) {
        return repo.updateUserNameById(id, userName, token);
    }

    @Override
    public int updateTokenById(long id, String token) {
        return repo.updateTokenById(id, token);
    }

    @Override
    public int updateHeaderUrlById(long id, String headerUrl, String token) {
        return repo.updateHeaderUrlById(id, headerUrl, token);
    }

    @Override
    public int updateUserDescById(long id, String userDesc, String token) {
        return repo.updateUserDescById(id, userDesc, token);
    }

    @Override
    public int updateVipInfoById(long id, boolean isVip, long vipStartTime, long vipEndTime, String token) {
        return repo.updateVipInfoById(id, isVip, vipStartTime,vipEndTime, token);
    }

    @Override
    public int getUserLevelById(long id) {
        User u = findUserById(id);
        if (u == null) return -1;
        return u.getLevel();
    }

    @Override
    public boolean isVip(long id) {
        User u = findUserById(id);
        if (u == null) return false;
        return u.isVip();
    }
}
