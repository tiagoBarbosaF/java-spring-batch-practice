package com.tiagobarbosa.springbatchtests.domain;

//public record Client(String name, String surname, int age, String email) {
//}

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String surname;
    private int age;
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