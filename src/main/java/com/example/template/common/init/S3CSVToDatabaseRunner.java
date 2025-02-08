package com.example.template.common.init;

import com.example.template.common.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class S3CSVToDatabaseRunner implements CommandLineRunner {

    private final S3Service s3Service;

    @Override
    public void run(String... args) throws Exception {

        s3Service.readObject();

        System.out.println("S3에서 파일을 읽어 데이터베이스에 저장하였습니다.");
    }
}
