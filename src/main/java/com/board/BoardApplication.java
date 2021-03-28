package com.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
@EnableAutoConfiguration
스프링 부트는 개발에 필요한 몇 가지 필수적인 설정들의 처리가 되어있음
이걸 자동으로 완료

@ComponentScan
기존의 XML 설정 방식의 스프링은 빈(Bean)의 등록 및 스캔을 위해
수동으로 ComponentScan을 여러번 선언해서 사용해왔음.
스프링 부트는 해당 애너테이션에 의해 자동으로 검색하고 IoC Container에 빈(Bean)으로 등록함.
 = > 의존성 주입의 간편화

@SpringBootConfiguration
스프링 부트의 설정을 나타내는 어노테이션
테스트를 사용할 때 계속 이 어노테이션을 찾기 때문에 스프링 부트에서는 필수 어노테이션
 */

public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
