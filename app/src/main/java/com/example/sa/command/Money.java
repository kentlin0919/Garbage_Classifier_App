package com.example.sa.command;

public class Money {
    int changeMoney;
    int Money;
    double bankMoney,money,alterMoney;
    public Money(int money, double bankMoney){
        this.Money=money;
        this.bankMoney=bankMoney;
        changemoney();
    }


    public int changemoney(){
        changeMoney = Money;
        money = bankMoney;
        alterMoney = money - changeMoney;
        return (int)alterMoney;


    }

    public int Undomoney(){
        return (int)money;
    }


}
