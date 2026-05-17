package model.enums;

public enum VehicleOption {
    CAR(1),
    MOTORCYCLE(2);

    private final int code;

    VehicleOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static VehicleOption searchByCode(int code) {
        for (VehicleOption option : VehicleOption.values()) {
            if (option.getCode() == code) {
                return option;
            }
        }
        return null;
    }
}
