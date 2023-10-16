package projectHotel.repository;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectHotel.entity.UserEntity;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Integer> {


    UserEntity findByEmail (String email);
}
