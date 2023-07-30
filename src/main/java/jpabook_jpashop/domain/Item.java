package jpabook_jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 싱글테이블 전략
@DiscriminatorColumn // DTYPE (웬만하면 넣는 게 좋음. 어떤 타입이 들어왔는지 확인할 수 있기 때문. 특히 싱글 테이블 전략에서는 꼭 들어가야 함)
public abstract class Item extends BaseEntity { // Item을 상속 받아서 Album, Book, Movie가 만들어지므로 추상클래스로 만들어야함

    @Id @GeneratedValue // @GeneratedValue는 기본값이 AUTO
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
