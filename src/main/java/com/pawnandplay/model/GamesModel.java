package com.pawnandplay.model;

public class GamesModel {
    private int gameID;
    private String gamename;
    private String level;
    private String genre;
    private int age;
    private double price;
    private int stock;
    private String brand;

    // Constructor
    public GamesModel(int gameID, String gamename, String level, String genre, int age, double price, int stock, String brand) {
        this.gameID = gameID;
        this.gamename = gamename;
        this.level = level;
        this.genre = genre;
        this.age = age;
        this.price = price;
        this.stock = stock;
        this.brand = brand;
    }

    public GamesModel() {
    }

    // Getters and Setters
    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
