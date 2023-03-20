package ru.kata.spring.boot_security.demo.dao;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query("from User u join fetch u.roles where u.username=:userName")
    User findByUsername(@Param("userName") String userName);
}
