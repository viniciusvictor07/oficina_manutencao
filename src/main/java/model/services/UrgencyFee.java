package model.services;

public class UrgencyFee implements TaxManager {
    @Override
    public double applyAdjustment(double currentAmount) {
        return currentAmount + 50.0;
    }
}
