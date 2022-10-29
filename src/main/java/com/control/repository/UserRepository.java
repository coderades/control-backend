package com.control.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.User;

@Repository
//https://www.baeldung.com/spring-data-derived-queries
public interface UserRepository extends JpaRepository<User, String> {

	Boolean existsByUserIdIsNotAndUserName(String userId, String userName);

	Boolean existsByUserIdIsNotAndUserEmail(String userId, String userEmail);

	Boolean existsByUserName(String userName);

	Boolean existsByUserEmail(String userEmail);

	User findByUserName(String userName);

	Optional<User> findByUserEmail(String userEmail);

	List<?> findByUserNameIgnoreCaseContaining(String userName);

	List<?> findByUserNameIgnoreCaseContainingOrUserEmailIgnoreCaseContaining(String userName, String userEmail);

	@Query("SELECT u FROM User u WHERE (userName LIKE %?1% OR userEmail LIKE %?1%) AND (userIsEnabled = ?2 OR NULL = ?2) AND (userIsAccountNonExpired = ?3 OR NULL = ?3) AND (userIsAccountNonLocked = ?4 OR NULL = ?4) AND (userIsCredentialsNonExpired = ?5 OR NULL = ?5) ORDER BY userName ASC")
	List<?> findByUser(String userName, Boolean userIsEnabled, Boolean userIsAccountNonExpired,
			Boolean userIsAccountNonLocked, Boolean userIsCredentialsNonExpired);

	@Query("SELECT u.userPassword FROM User u WHERE u.userId =?1")
	String findByUserPassword(String userId);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.userPassword = ?2 WHERE u.userId = ?1")
	void saveUserPassword(String userId, String userPassword);

}