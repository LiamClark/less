package nl.tudelft;

import io.vavr.collection.List;
import java.util.ArrayList;


public class Invoice {
    private String customer;
    private double value;

    public Invoice(String customer, double value) {
        this.customer = customer;
        this.value = value;
    }

    public String getCustomer() {
        return customer;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (Double.compare(invoice.value, value) != 0) return false;
        return customer != null ? customer.equals(invoice.customer) : invoice.customer == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = customer != null ? customer.hashCode() : 0;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static ArrayList<Invoice> filter(ArrayList<Invoice> allInvoices) {
        ArrayList<Invoice> filtered = new ArrayList<>();

        for(Invoice inv : allInvoices) {
            if(inv.getValue() < 100.0)
                filtered.add(inv);
        }

        return filtered;
    }

    public static List<Invoice> filter2(List<Invoice> allInvoices) {
        return allInvoices.filter((Invoice invoice) -> invoice.getValue() < 100.0);
    }
}