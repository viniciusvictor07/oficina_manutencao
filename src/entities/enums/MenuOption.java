package entities.enums;

public enum MenuOption {
    EXIT(0),
    REGISTER(1),
    EXPENSIVE(2),
    LIST(3),
    REMOVE(4),
    PROFIT(5);

    private final int code;

    MenuOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MenuOption searchByCode(int code) {
        for (MenuOption option : MenuOption.values()) {
            if (option.getCode() == code) {
                return option;
            }
        }
        return null;
    }
}