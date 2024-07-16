package com.company.repository;

import com.company.dto.UserFilterDto;
import com.company.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFilterRepository {

    @Autowired
    private EntityManager entityManager;

    public List<User> getUserList(UserFilterDto dto) {

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM userr  where status = 'ACTIVE'");


        if (dto.getName() != null) {
            builder.append("and name = '" + dto.getName() + "'");
        }

        if (dto.getSurname() != null) {
            builder.append(" and surname = '" + dto.getSurname() + "'");
        }

        if (dto.getPhone() != null) {
            builder.append(" and phone = '" + dto.getPhone() + "'");
        }

        return entityManager.createNativeQuery(builder.toString(), User.class).getResultList();

    }
}
