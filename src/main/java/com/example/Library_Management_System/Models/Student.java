package com.example.Library_Management_System.Models;


import javax.persistence.*;

@Entity
@Table(name = "student_info")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String country;
    private int age;

    @Column(unique = true)
    private String email;
    private String mobNo;

    @OneToOne(mappedBy = "student_name",cascade = CascadeType.ALL)
    @JoinColumn
    private Card card;

    public Student() {
    }

    public Student(int id, String name, String country, int age, String email, String mobNo, Card card) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.age = age;
        this.email = email;
        this.mobNo = mobNo;
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
