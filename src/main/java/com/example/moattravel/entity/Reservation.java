package com.example.moattravel.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations") // ← DBテーブル名
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Integer id;

    @ManyToOne // 民宿への外部キー
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    @ManyToOne // ユーザーへの外部キー
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "checkin_date")
    private LocalDate checkinDate;

    @Column(name = "checkout_date")
    private LocalDate checkoutDate;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "amount")
    private Integer amount;

    // --- getter / setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public House getHouse() { return house; }
    public void setHouse(House house) { this.house = house; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getCheckinDate() { return checkinDate; }
    public void setCheckinDate(LocalDate checkinDate) { this.checkinDate = checkinDate; }

    public LocalDate getCheckoutDate() { return checkoutDate; }
    public void setCheckoutDate(LocalDate checkoutDate) { this.checkoutDate = checkoutDate; }

    public Integer getNumberOfPeople() { return numberOfPeople; }
    public void setNumberOfPeople(Integer numberOfPeople) { this.numberOfPeople = numberOfPeople; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
}