package xyz.thewind.music.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.thewind.music.model.OldUser;

import java.util.List;

@Repository
public interface OldUserRepository extends JpaRepository<OldUser, Long>, JpaSpecificationExecutor<OldUser> {
    OldUser findTopByUserId(String userId);

    OldUser findTopByEmail(String email);

    OldUser findTopByUserIdAndPassword(String userId, String password);

    @Modifying
    @Query("update OldUser set status= :status where userId = :userId")
    int updateStatusByUserId(@Param("status") int status, @Param("userId") String userId);

    List<OldUser> findAll();
}
