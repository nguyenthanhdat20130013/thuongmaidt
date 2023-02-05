package beans;

import model.Product;
import model.UserModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class Cart implements Serializable {
    HashMap<String, Product> data;
    UserModel customer;
    long total;
    int quantity;

    public Cart() {
        data = new HashMap<>();
        customer = new UserModel();
        total = 0;
        quantity = 0;
    }

    public Cart(UserModel customer, long total, int quantity) {
        data = new HashMap<>();
        this.customer = customer;
        this.total = total;
        this.quantity = quantity;
    }

    public void put(Product p) {
        if (data.containsKey(p.getKey())) {
            Product p1 = data.get(p.getKey());
            p1.setQuantity(p1.getQuantity() + 1);
            data.put(p.getKey(), p1);
        } else {
            data.put(p.getKey(), p);
        }
        updateTotalAndQ();
    }

    public void put(String key, int quantity) {
        if (data.containsKey(key)) {
            Product p1 = data.get(key);
            p1.setQuantity(quantity);
            data.put(key, p1);
        }
        updateTotalAndQ();
    }

    private void updateTotalAndQ() {
        total = 0;
        quantity = 0;
        for (Product p : data.values()) {
            total += p.getQuantity() * p.getPrice_sell();
            quantity += p.getQuantity();
        }
    }

    public void update(Product p) {
        if (data.containsKey(p.getKey())) {
            data.put(p.getKey(), p);
        }
        updateTotalAndQ();
    }

    public void remove(String key) {
        data.remove(key);
        updateTotalAndQ();
    }

    public Collection<Product> getListProduct() {
        return data.values();
    }

    public int getQuantity() {
        return quantity;
    }

    public long getTotal() {
        return total;
    }

    public UserModel getCustomer() {
        return customer;
    }

    public void sub(Product p) {
        if (data.containsKey(p.getKey()) && data.get(p.getKey()).getQuantity() > 0) {
            Product p1 = data.get(p.getKey());
            int num = p1.getQuantity();
            p1.setQuantity(num - 1);
            data.put(p.getKey(), p1);
        } else if (data.get(p.getKey()).getQuantity() < 1) {
            data.remove(p.getKey());
        }
        updateTotalAndQ();
    }

}
