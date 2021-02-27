package com.mazing.logic.command;

public class TimeCommand extends MainCommand {

    @Override
    public void execute() {
        setResponse(getGame().checkTime());
    }

}
