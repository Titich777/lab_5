package command.commands;

import exceptions.ExitObligedException;

/**
 * Команда 'exit'
 * завершить программу (без сохранения в файл)
 */
public class Exit extends Command{
    public Exit(){
        super("exit", ": завершить программу (без сохранения в файл)");
    }

    /**
     * Исполнить команду
     * @param args аргументы команды
     * @throws ExitObligedException нужен выход из программы
     */
    @Override
    public void execute(String args) throws ExitObligedException{
        throw new ExitObligedException();
    }
}