package model;

/**
 * Class OrderItem merepresentasikan satu item dalam pesanan.
 * Menggunakan Composition dengan MenuItem.
 */
public class OrderItem {
    private MenuItem menuItem;
    private int jumlah;
    private String catatan;

    // Constructor
    public OrderItem(MenuItem menuItem, int jumlah) {
        this.menuItem = menuItem;
        this.jumlah = jumlah;
        this.catatan = "";
    }

    public OrderItem(MenuItem menuItem, int jumlah, String catatan) {
        this.menuItem = menuItem;
        this.jumlah = jumlah;
        this.catatan = catatan;
    }

    // Getters
    public MenuItem getMenuItem() { return menuItem; }
    public int getJumlah() { return jumlah; }
    public String getCatatan() { return catatan; }

    // Setters
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public void setCatatan(String catatan) { this.catatan = catatan; }

    // Hitung subtotal item ini
    public double getSubtotal() {
        return menuItem.getHarga() * jumlah;
    }

    @Override
    public String toString() {
        String result = String.format("  %-22s x%d  = Rp %,.0f",
                menuItem.getNama(), jumlah, getSubtotal());
        if (!catatan.isEmpty()) {
            result += "\n    Catatan: " + catatan;
        }
        return result;
    }
}
