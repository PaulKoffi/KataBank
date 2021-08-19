package API;

public class Amount {

    private double value;

    public Amount(double  value) {
        this.value = value;
    }

    public static Amount amountWith(double  value) {
        return new Amount(value);
    }

    public Amount plus(Amount otherAmount) {
        return amountWith(this.value + otherAmount.value);
    }

    public boolean isGreaterThan(Amount otherAmount) {
        return this.value > otherAmount.value;
    }

    public Amount absoluteValue() {
        return amountWith(Math.abs(value));
    }

    public Amount negative() {
        return amountWith(-value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        Amount other = (Amount) obj;
        if (value != other.value)
            return false;
        return true;
    }
}
