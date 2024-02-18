package com.tiagobarbosa.springbatchtests.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

public class Client {
    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(?: [a-zA-ZÀ-ÿ]+)*$", message = "Name must be alphabetical")
    private String name;
    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(?: [a-zA-ZÀ-ÿ]+)*$", message = "Name must be alphabetical")
    private String surname;
    @NotNull
    @Range(min = 18, max = 150)
    private int age;
    @NotNull
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email")
    private String email;
    private List<Transaction> transactions = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String surname, int age, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                (transactions.isEmpty() ? "" : ", transactions: '" + transactions) + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}