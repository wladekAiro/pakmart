package com.wladek.pakmart.repository;

import com.wladek.pakmart.domain.User;
import com.wladek.pakmart.domain.enumeration.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wladek
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    User findByLoginId(String loginId);

    User findByEmail(String email);

    List<User> findByUserRole(UserRole userRole);
}
