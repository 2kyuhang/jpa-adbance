package com.sparta.jpaadvance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing//@EntityListeners(AuditingEntityListener.class) 이걸 사용하려면 최상단에 @EnableJpaAuditing을 달아줘야 함
@SpringBootApplication
public class JpaAdbanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaAdbanceApplication.class, args);
    }

}
