package com.example.jteam.friender;

/**
 * Created by Jeong on 2016-08-08.
 */
public class Bulletin {
    private String city = null;
    private String destination = null;
    private String route1 = null;
    private String route2 = null;
    private String letter = null;
    private String p_date = null;
    private int[] character = new int[3];/////////////
    private int totalnum = 0;/////////////
    private int joinednum = 0;//////////////

    public int getCharacter(int num) {
        return character;
    }

    public void setCharacter(int[] character) {
        this.character = character;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getJoinednum() {
        return joinednum;
    }

    public void setJoinednum(int joinednum) {
        this.joinednum = joinednum;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getP_date() {
        return p_date;
    }

    public void setP_date(String p_date) {
        this.p_date = p_date;
    }

    public String getRoute1() {
        return route1;
    }

    public void setRoute1(String route1) {
        this.route1 = route1;
    }

    public String getRoute2() {
        return route2;
    }

    public void setRoute2(String route2) {
        this.route2 = route2;
    }

    public int getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(int totalnum) {
        this.totalnum = totalnum;
    }
}
