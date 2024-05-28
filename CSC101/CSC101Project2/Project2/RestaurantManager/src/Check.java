public class Check {
    private double saleAmount = 0.0;
    private double tipAmount = 0.0;
    private double totalAmount = 0.0;

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(double tipAmount) {
        this.tipAmount = tipAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void correctCheckErrors() {
        // We can assume that saleAmount is correct, it's the customer's writing that we have to worry about
        if (this.tipAmount == 0) { // If customer forgot to write the tip
            this.tipAmount = this.totalAmount - this.saleAmount;
            System.out.printf("Customer forgot to write a tip! Calculated Tip: $%.2f\n", this.tipAmount);
        } else if (this.totalAmount == 0) { // If customer forgot to write the total
            this.totalAmount = this.saleAmount + this.tipAmount;
            System.out.printf("Customer forgot to write the total! Calculated total: $%.2f\n", this.totalAmount);
        } else if (this.tipAmount != this.totalAmount - this.saleAmount) { // If tip is incorrect
            this.tipAmount = this.totalAmount - this.saleAmount;
            System.out.printf("Customer miscalculated the tip! Correct tip: $%.2f\n", this.tipAmount);
        }
    }

    public Check(double saleAmount, double tipAmount, double totalAmount) {
        saleAmount = saleAmount;
        tipAmount = tipAmount;
        totalAmount = totalAmount;
    }

    public Check() {

    }
}
