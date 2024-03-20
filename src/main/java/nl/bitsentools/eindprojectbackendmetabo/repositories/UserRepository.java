package nl.bitsentools.eindprojectbackendmetabo.repositories;


import nl.bitsentools.eindprojectbackendmetabo.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <UserModel, Long> {
    Optional<UserModel> findUserModelByUsername(String username);
    boolean existsUserModelByUsername(String username);
    void deleteUserModelByUsername(String username);
}
