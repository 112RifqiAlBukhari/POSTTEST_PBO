package kiosk.model;

public class pesanan {
    private String namaPelanggan;
    private menuMakanan item;
    private int jumlah;

    public pesanan(String namaPelanggan, menuMakanan item, int jumlah) {
        this.namaPelanggan = namaPelanggan;
        this.item = item;
        this.jumlah = jumlah;
    }

    // --- GETTER ---
    public String getNamaPelanggan() { return namaPelanggan; }

    public String getMenu() { return item.nama; }

    public int getJumlah() { return jumlah; }

    // --- SETTER ---
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public void setItem(menuMakanan item) { this.item = item; }

    // --- TAMPIL PESANAN ---
    public void tampilPesanan() {
        System.out.println("Pelanggan : " + namaPelanggan);
        System.out.print("Item      : ");

        item.tampilMenu();

        System.out.println("Jumlah    : " + jumlah);
        System.out.println("Total     : Rp" + (item.getHarga() * jumlah));
    }
}