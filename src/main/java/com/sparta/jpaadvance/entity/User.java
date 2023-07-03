/*
//단방향
*/
/*
package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}*//*


//양방향
package com.sparta.jpaadvance.entity;

import com.sparta.jpaadvance.entity.Food;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "user")
    private Food food;

    public void addFood(Food food) {
        this.food = food;
        food.setUser(this);
    }
}*/

/*package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Food> foodList = new ArrayList<>();

    public void addFoodList(Food food) {
        this.foodList.add(food);
        food.setUser(this); // 외래 키(연관 관계) 설정
    }

}*/

/*package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}*/
/*
package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "userList")//mappedBy 는 반대편 변수명
    private List<Food> foodList = new ArrayList<>();

    public void addFoodList(Food food) {
        this.foodList.add(food);
        food.getUserList().add(this); // 외래 키(연관 관계) 설정
    }
}*/

package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    //OneToMany는 기본이 Lazy
    //필요할때 정보를 가져온다
    //뒤에가 Many이기때문에 다수의 정보를 가져오기 무리가 있을 수 있어 필요할때 가져온다
    //LAZY = 지연로딩 => 지연로딩은 정보를 필요할때 가져온다
    //1. 이때 영속성이 열려있어야 가져 올 수 있기 때문에
    //2. 지연로딩에서 값을 뒤늦게 가져오려면 가져오는 함수내에 @Transactional 이 달려 있어야 함!!

    //cascade = CascadeType.PERSIST
    //이렇게 하면 user을 저장할때 foodList도 같이 저장이 된다
    //+CascadeType.REMOVE의 기능도 가지고 있어 user을 삭제하면 연관되어 있는 user에 포함되었던 음식 디비도 삭제된다

    //orphanRemoval = true
    //user에서 foodlist의 연관관계를 제거(user.gettFoodList().remove(chicken);)를 통해 Food디비에서 제거 가능

    //Food클래스에서 지정한 변수명
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST/*, CascadeType.REMOVE*/}, orphanRemoval = true)
    private List<Food> foodList = new ArrayList<>();

    public void addFoodList(Food food) {
        foodList.add(food);
        food.setUser(this);
    }
}