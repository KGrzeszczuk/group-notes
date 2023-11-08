package pl.kamil.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.kamil.backend.entity.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{
    // AppUser findByUsername(String username);
    Optional<AppUser> findOneByUsername(String username);

}
