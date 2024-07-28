// PaymentStrategy.java
interface PaymentStrategy {
    void pay(int amount);
}

// CreditCardPayment.java
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid" + amount + " using credit card number.");
    }
}

// GPayPayment.java
class GPayPayment implements PaymentStrategy {
    private String email;

    public GPayPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid" + amount + " using GPay.");
    }
}

// ShoppingCart.java
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

// Payment.java
public class Payment {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "Rindhia Sree"));
        cart.checkout(100);

        cart.setPaymentStrategy(new GPayPayment("rindhia.sree@example.com"));
        cart.checkout(200);
    }
}