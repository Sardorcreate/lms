package com.company.repository;

import com.company.entity.User;
import com.company.enums.Role;
import com.company.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    @Modifying
    @Transactional
    @Query("update User set role =:r, status =:s, visible =:v where login =:l")
    Integer updateRSV(@Param("r") Role role,
                             @Param("s") Status status,
                             @Param("v") Boolean visible,
                             @Param("l") String login);

    @Modifying
    @Transactional
    @Query("update User set name =:n, surname =:s, password =:p where login =:l")
    Integer updateNSP(@Param("n") String name,
                      @Param("s") String surname,
                      @Param("p") String password,
                      @Param("l") String login);

    List<User> findAll();

    @Modifying
    @Transactional
    @Query("update User set imagePath =:i where login =:l")
    Integer updateI(@Param("i") String imagePath,
                    @Param("l") String login);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM userr LIMIT :limit OFFSET :offset",nativeQuery = true)
    List<User> getUsers(@Param("limit") Integer size,
                        @Param("offset") Integer offset);

    @Modifying
    @Transactional
    @Query(value ="SELECT * FROM userr :sql" ,nativeQuery = true)
    List<User> getUsers1(@Param("sql") String sql);
}
