package command;

import exceptions.CommandRuntimeException;
import exceptions.ExitObligedException;
import exceptions.IllegalArgumentsException;

public interface Executable {
    void execute(String args) throws IllegalArgumentsException, ExitObligedException, CommandRuntimeException;
}
