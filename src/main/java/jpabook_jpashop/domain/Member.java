package jpabook_jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @Embedded
    private Address address;

//    설계상 Member에 orders가 있는 건 좋지 못한 설계 => 적절히 연관관계를 끊어줘야함 (양방향 연관관계가 꼭 필요한지 따지기)
//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
