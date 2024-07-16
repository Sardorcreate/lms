package com.company.service;

import com.company.dto.TestDto;
import com.company.entity.Test;
import com.company.enums.Status;
import com.company.repository.TestRepository;
import com.company.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public String createTest(TestDto testDto) {

        Test test = new Test();
        test.setQuestion(testDto.getQuestion());
        test.setFirstOption(testDto.getFirstOption());
        test.setSecondOption(testDto.getSecondOption());
        test.setThirdOption(testDto.getThirdOption());
        test.setFourthOption(testDto.getFourthOption());
        test.setStatus(Status.ACTIVE);

        testRepository.save(test);

        return "Test has been successfully created!!!";
    }

    public List<TestDto> startTest() {

        List<Test> all = testRepository.findAll();

        List<Integer> randomNumbers = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            randomNumbers.add(RandomUtil.getRandomNumbers(all.size()));
        }

        List<TestDto> testDtos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < all.size(); j++) {

                if (all.get(j).getId() == randomNumbers.get(i)) {

                    Test test = all.get(j);

                    TestDto testDto = new TestDto();
                    testDto.setId(test.getId());
                    testDto.setQuestion(test.getQuestion());
                    testDto.setFirstOption(test.getFirstOption());
                    testDto.setSecondOption(test.getSecondOption());
                    testDto.setThirdOption(test.getThirdOption());
                    testDto.setFourthOption(test.getFourthOption());

                    testDtos.add(testDto);

                    break;
                }

            }
        }

        return testDtos;
    }
}
