package com.example.sa.command;

import java.util.ArrayList;
import java.util.List;

public class invorker {
    private List<Command> doCommandList = new ArrayList<Command>();
    private List<Command> undoCommandList = new ArrayList<Command>();

    public void addCommend(Command Command) {
        doCommandList.add(Command);
    }

    public void execute() {
        for(Command command : doCommandList) {
            command.execute();
        }
    }
    public void unexecute() {
        for (Command command : undoCommandList) {
            command.unexecute();
        }
    }
}
