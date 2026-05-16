package model.entities.enums;

public enum MenuOption {
    EXIT(0),
    REGISTER_CUSTOMER(1),
    REGISTER_SERVICE(2),
    LIST_SERVICES(3),
    REMOVE_CUSTOMER(4),
    REMOVE_SERVICE(5),
    PROFIT(6);

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