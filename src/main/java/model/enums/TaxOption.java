package model.enums;

public enum TaxOption {
    STANDARD(1),
    BLACK_FRIDAY(2),
    URGENCY(3);

    private final int code;

    TaxOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TaxOption searchByCode(int code) {
        for (TaxOption option : values()) {
            if (option.getCode() == code) {
                return option;
            }
        }
        return STANDARD;
    }
}
