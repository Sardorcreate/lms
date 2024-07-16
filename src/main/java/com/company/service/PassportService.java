package com.company.service;

import com.company.dto.PassportDto;
import com.company.entity.Passport;
import com.company.exception.AllException;
import com.company.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassportService {

    @Autowired
    private PassportRepository passportRepository;

    public Passport getPassport(Integer id) {

        Passport passport = passportRepository.findById(id).get();

        return passport;
    }

    public Passport createPassport(PassportDto passportDto) {

        Optional<Passport> byPassportNumber = passportRepository.findByPassportNumber(passportDto.getPassportNumber());

        if (byPassportNumber.isPresent()) {
            throw new AllException("This passport is already registered!!!");
        }

        Passport passport = dtoToEntity(passportDto);

        passportRepository.save(passport);

        return passport;
    }

    public Passport dtoToEntity(PassportDto passportDto) {

        Passport passport = new Passport();
        passport.setPassportNumber(passportDto.getPassportNumber());
        passport.setPassportSerie(passportDto.getPassportSerie());
        passport.setJshshir(passportDto.getJshshir());

        return passport;
    }

    public PassportDto entityToDto(Passport passport) {

        PassportDto passportDto = new PassportDto();
        passportDto.setId(passport.getId());
        passportDto.setPassportSerie(passport.getPassportSerie());
        passportDto.setPassportNumber(passport.getPassportNumber());
        passportDto.setJshshir(passport.getJshshir());

        return passportDto;
    }
}
