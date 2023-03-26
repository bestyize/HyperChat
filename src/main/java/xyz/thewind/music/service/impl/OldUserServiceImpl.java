package xyz.thewind.music.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.thewind.music.dto.DTOConverter;
import xyz.thewind.music.dto.UserDTO;
import xyz.thewind.music.model.OldUser;
import xyz.thewind.music.repository.OldUserRepository;
import xyz.thewind.music.service.OldUserService;

import java.util.List;


@Service
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class OldUserServiceImpl implements OldUserService {
    @Resource
    private OldUserRepository oldUserRepository;

    @Override
    public boolean exists(String userId) {
        return oldUserRepository.findTopByUserId(userId) != null;
    }

    @Override
    public boolean existsEmail(String email) {
        return oldUserRepository.findTopByEmail(email) != null;
    }

    @Override
    public OldUser findUserByUserIdAndPassword(String userId, String password) {
        return oldUserRepository.findTopByUserIdAndPassword(userId, password);
    }

    @Override
    public void updateUserStateById(String userId, int state) {
        int status = oldUserRepository.updateStatusByUserId(state, userId);
    }


    @Override
    public OldUser addUser(UserDTO userDTO) {
        OldUser user = DTOConverter.convertUserDtoToUser(userDTO);
        return oldUserRepository.save(user);
    }

    @Override
    public List<OldUser> findAllUser() {
        return oldUserRepository.findAll();
    }
}
