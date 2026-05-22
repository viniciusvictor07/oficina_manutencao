package model.services;

public class BlackFridayDiscount implements TaxManager {
    @Override
    public double applyAdjustment(double currentAmount) {
        return currentAmount * 0.90;
    }
}