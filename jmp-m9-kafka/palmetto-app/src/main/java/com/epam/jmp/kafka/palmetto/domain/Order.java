package com.epam.jmp.kafka.palmetto.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Order
 * Date: 03/24/2023
 *
 * @author Yauheni Antsipenka
 */
public class Order implements Serializable {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String pizzaName;
    @JsonProperty
    private Integer number;
    @JsonProperty
    private StatusEnum status;

    public Order() {
    }

    public Order(String pizzaName, Integer number) {
        this.pizzaName = pizzaName;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && pizzaName.equals(order.pizzaName) && number.equals(order.number) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pizzaName, number, status);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":").append(id);
        sb.append(", \"pizzaName\":\"").append(pizzaName).append('\"');
        sb.append(", \"number\":").append(number);
        sb.append(", \"status\":\"").append(status).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
