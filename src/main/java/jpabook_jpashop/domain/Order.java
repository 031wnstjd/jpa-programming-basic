package jpabook_jpashop.domain;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "ORDERS") // ORDER BY에서 ORDER가 테이블명으로 안 되는 DB도 존재하므로, 테이블명으로 ORDERS를 많이 씀
public class Order extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL) // Order를 persist를 통해 저장하면, Delivery도 알아서 저장됨 (Deleivery의 로직이 너무 복잡하거나 라이프사이클이 따로 관리되어야 하는 경우 cascade를 빼고 따로 persist)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 연관관계 편의 메소드 (양방향이기 때문)
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this); // 연관관계 주인이 연관관계 매핑을 함
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
