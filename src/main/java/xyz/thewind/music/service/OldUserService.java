package xyz.thewind.music.service;

import xyz.thewind.music.dto.UserDTO;
import xyz.thewind.music.model.OldUser;

import java.util.List;

public interface OldUserService {
    boolean exists(String userId);

    boolean existsEmail(String email);

    OldUser findUserByUserIdAndPassword(String userId, String password);

    void updateUserStateById(String userId, int state);

    OldUser addUser(UserDTO userDTO);

    List<OldUser> findAllUser();
}
