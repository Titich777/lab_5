package storedClasses.forms;

import exceptions.InvalidFormException;
public abstract class Form <T>{
    public abstract T build() throws InvalidFormException;
}
