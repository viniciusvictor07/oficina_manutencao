package model.enums;

import model.entities.CarService;
import model.entities.Customer;
import model.entities.MotoService;
import model.entities.ServiceOrder;
import model.services.TaxManager;

public enum VehicleOption {
    CAR(1) {
        @Override
        public ServiceOrder createInstance(String model, double value, Customer customer, TaxManager tax) {
            return new CarService(model, value, customer, tax);
        }
    },
    MOTORCYCLE(2) {
        @Override
        public ServiceOrder createInstance(String model, double value, Customer customer, TaxManager tax) {
            return new MotoService(model, value, customer, tax);
        }
    };

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

    public abstract ServiceOrder createInstance(String model, double value, Customer customer, TaxManager tax);
}
