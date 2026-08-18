package model.services;

public class NoAdjustment implements TaxManager {
    @Override
    public double applyAdjustment(double currentAmount) {
        return currentAmount;
    }
}
