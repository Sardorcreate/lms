package com.company.repository;

import com.company.entity.Passport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Integer> {

    Optional<Passport> findByPassportNumber(Double number);

}
