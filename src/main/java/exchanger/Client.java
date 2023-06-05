package exchanger;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

@Setter
@Getter

public class Client implements Runnable {
    private static int idCounter = 1;
    private int id;
    private double balance;
    private Exchanger<Double> exchanger;

    public Client(double balance, Exchanger<Double> exchanger) {
        this.id = idCounter++;
        this.balance = balance;
        this.exchanger = exchanger;
    }

    public void topUpBalance(double amount) {
        balance += amount;
        System.out.println("Client " + id + " has topped up balance: " + amount + ". Current balance: " + balance);
    }

    public void makePurchase(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Client " + id + " has made a purchase: " + amount + ". Current balance: " + balance);
        } else {
            System.out.println("Client " + id + " has insufficient funds to make a purchase: " + amount);
        }
    }

    @Override
    public void run() {
        try {
            double newBalance = exchanger.exchange(balance);
            if (newBalance > balance) {
                balance = newBalance;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Restaurant {
    public static void main(String[] args) {
        Exchanger<Double> exchanger = new Exchanger<>();
        List<Client> clients = new ArrayList<>();

        // Create clients
        clients.add(new Client(0.0, exchanger));
        clients.add(new Client(0.0, exchanger));
        clients.add(new Client(0.0, exchanger));
        clients.add(new Client(10.0, exchanger));

        // Display initial balance of each client
        for (Client client : clients) {
            System.out.println("Client " + client.getId() + " initial balance: " + client.getBalance());
        }

        // Start client threads
        List<Thread> threads = new ArrayList<>();
        for (Client client : clients) {
            threads.add(new Thread(client));
        }
        threads.forEach(Thread::start);

        // Simulate balance top-up and purchase operations
        clients.get(0).topUpBalance(50.0);
        clients.get(1).topUpBalance(10.0);
        clients.get(2).topUpBalance(0.5);
        clients.get(3).topUpBalance(15.0);

        clients.get(0).makePurchase(50.0);
        clients.get(1).makePurchase(20.0);
        clients.get(2).makePurchase(15.0);
        clients.get(3).makePurchase(100.0);

        // Wait for threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Display current balance of each client
        for (Client client : clients) {
            System.out.println("Client " + client.getId() + " final balance: " + client.getBalance());
        }
    }
}