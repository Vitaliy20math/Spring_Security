package ru.kata.spring.boot_security.demo.Dao;

import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = :userName")
    User findByUsername(@Param("userName") String userName);



}
