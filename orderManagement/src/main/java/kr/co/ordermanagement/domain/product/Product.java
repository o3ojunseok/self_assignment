package kr.co.ordermanagement.domain.product;

import kr.co.ordermanagement.domain.exception.NotEnoughAmountException;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public Product(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public void checkEnoughAmount(Integer orderedAmount) {
        if (this.amount < orderedAmount) {
            throw new NotEnoughAmountException(this.id + "번 상품 수량 부족");
        }
    }

    public void decreaseAmount(Integer orderedAMount) {
        this.amount = this.amount - orderedAMount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }
}
