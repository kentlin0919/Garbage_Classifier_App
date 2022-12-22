package com.example.sa.command;

public class Concrete_Commands implements Command{
    private Money money;
    public Concrete_Commands(Money money){
        this.money = money;
    }

    @Override
    public int execute() {
//        receiver = new receiver();
        return money.changemoney();

    }

    @Override
    public int unexecute() {
        return money.Undomoney();
    }
}
