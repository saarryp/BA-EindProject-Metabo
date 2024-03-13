package nl.bitsentools.eindprojectbackendmetabo.repositories;


import nl.bitsentools.eindprojectbackendmetabo.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserModel, String> {
}
