package kiosk.model;

public class pesanan {
    private String namaPelanggan;
    private String menu;
    private int jumlah;
    private String pilihanTambahan;

    public pesanan(String namaPelanggan, String menu, int jumlah, String pilihanTambahan) {
        this.namaPelanggan = namaPelanggan;
        this.menu = menu;
        this.jumlah = jumlah;
        this.pilihanTambahan = pilihanTambahan;
    }

    // --- GETTER (Untuk mengambil nilai) ---
    public String getNamaPelanggan() { return namaPelanggan; }

    public String getMenu() { return menu; } // Tambahkan ini agar tidak error!

    public String getPilihanTambahan() { return pilihanTambahan; }

    // --- SETTER (Untuk mengubah nilai) ---
    public void setMenu(String menu) { this.menu = menu; }

    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public void setPilihanTambahan(String pilihan) { this.pilihanTambahan = pilihan; }

    public void tampilPesanan() {
        System.out.println("Pelanggan : " + namaPelanggan);
        System.out.println("Menu      : " + menu + " (" + pilihanTambahan + ")");
        System.out.println("Jumlah    : " + jumlah);
    }
}