package homework_05_25_2023;

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) throws InterruptedException {
        synchronized (this) {
            while (balance < amount) {
                wait();
            }

            balance -= amount;
            System.out.println("Withdrawn: " + amount + " Balance: " + balance);
        }
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " Balance: " + balance);
        notifyAll();
    }

    public static void balanceOperations() {
        BankAccount bankAccount = new BankAccount(100);
        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    bankAccount.withdraw(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                bankAccount.deposit(100);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        withdrawThread.start();
        depositThread.start();
    }

    public static void main(String[] args) {
        balanceOperations();
    }
}