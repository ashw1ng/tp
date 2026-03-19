package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts all persons in the address book with starred contacts first, then alphabetically by name.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all persons (starred first, then by name)";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book with starred contacts first, then alphabetically by name.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersons();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
