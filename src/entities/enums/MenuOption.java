package entities.enums;

public enum MenuOption {
    EXIT(0),
    REGISTER_CUSTOMER(1),
    REGISTER_SERVICE(2),
    EXPENSIVE(3),
    LIST(4),
    REMOVE_CUSTOMER(5),
    REMOVE_SERVICE(6),
    PROFIT(7);

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