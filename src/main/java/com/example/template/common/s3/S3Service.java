package com.example.template.common.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.repository.CollegeRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final CollegeRepository collegeRepository;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Transactional
    public void readObject() throws IOException, CsvValidationException {
        // S3에서 지정된 파일 가져오기

        log.info("파일을 가져옵니다");
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket, "Data.csv"));
        log.info("파일 가져오기 성공");

        // try-with-resources로 자원 자동 관리
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(s3Object.getObjectContent(), "UTF-8"));
             CSVReader csvReader = new CSVReader(bufferedReader)) {

            String[] row;
            csvReader.readNext();

            log.info("데이터를 읽습니다.");

            while((row = csvReader.readNext()) != null) {
                List<String> values = Arrays.stream(row).toList();

                College college = College.builder()
                        .name(values.get(1))
                        .headmaster(values.get(2))
                        .contactNo(values.get(3))
                        .address(values.get(4))
                        .coordinate(Coordinate.builder()
                                .dwn(Double.parseDouble(values.get(5)))
                                .acr(Double.parseDouble(values.get(6)))
                                .build())
                        .build();

                collegeRepository.save(college);
            }

        } catch (IOException | CsvValidationException e) {
            log.error("Failed to process CSV file from S3: {}", e.getMessage(), e);
            throw e; // 예외를 다시 던져 호출자에게 알림
        }
    }
}
