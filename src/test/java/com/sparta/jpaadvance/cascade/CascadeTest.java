package com.sparta.jpaadvance.cascade;

import com.sparta.jpaadvance.entity.Food;
import com.sparta.jpaadvance.entity.User;
import com.sparta.jpaadvance.repository.FoodRepository;
import com.sparta.jpaadvance.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CascadeTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;

    @Test
    @DisplayName("Robbie 음식 주문")
    void test1() {
        // 고객 Robbie 가 후라이드 치킨과 양념 치킨을 주문합니다.
        User user = new User();
        user.setName("Robbie");

        // 후라이드 치킨 주문
        Food food = new Food();
        food.setName("후라이드 치킨");
        food.setPrice(15000);

        user.addFoodList(food);

        Food food2 = new Food();
        food2.setName("양념 치킨");
        food2.setPrice(20000);

        user.addFoodList(food2);

        userRepository.save(user);
        foodRepository.save(food);
        foodRepository.save(food2);
    }


    @Test
    @DisplayName("영속성 전이 저장")
    void test2() {
        // 고객 Robbie 가 후라이드 치킨과 양념 치킨을 주문합니다.
        User user = new User();
        user.setName("Robbie");

        // 후라이드 치킨 주문
        Food food = new Food();
        food.setName("후라이드 치킨");
        food.setPrice(15000);

        user.addFoodList(food);

        Food food2 = new Food();
        food2.setName("양념 치킨");
        food2.setPrice(20000);

        user.addFoodList(food2);

        //User 클래스에서 foodList에 cascade를 달아줌으로
        //연결되어 foodList의 내용인 후라이드, 양념 치킨이 저장된다
        userRepository.save(user);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("Robbie 탈퇴")
    void test3() {
        // 고객 Robbie 를 조회합니다.
        User user = userRepository.findByName("Robbie");
        System.out.println("user.getName() = " + user.getName());

        // Robbie 가 주문한 음식 조회
        for (Food food : user.getFoodList()) {
            System.out.println("food.getName() = " + food.getName());
        }

        // 주문한 음식 데이터 삭제
        foodRepository.deleteAll(user.getFoodList());

        // Robbie 탈퇴
        userRepository.delete(user);
    }

    @Test
    @Transactional//User입장에서 OneToMany 지연로딩이기때문에 트랜잭션을 걸어줘야 필요한값에 대한 CRUD가능한듯
    @Rollback(value = false)
    @DisplayName("영속성 전이 삭제")
    void test4() {
        // 고객 Robbie 를 조회합니다.
        User user = userRepository.findByName("Robbie");
        System.out.println("user.getName() = " + user.getName());

        // Robbie 가 주문한 음식 조회
        for (Food food : user.getFoodList()) {
            System.out.println("food.getName() = " + food.getName());
        }

        // Robbie 탈퇴(음식 데이터까지 한번에 삭제)
        userRepository.delete(user);
    }

}