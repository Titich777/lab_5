package storedClasses;

import java.util.Objects;

public interface Validator {
    boolean validate();

    boolean equals(Objects o);
}
