package kiosk.model;

public class pesanan implements ITransaksi {
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

    // --- TAMPIL PESANAN (existing) ---
    public void tampilPesanan() {
        System.out.println("Pelanggan : " + namaPelanggan);
        System.out.print("Item      : ");
        item.tampilMenu();
        System.out.println("Keterangan: " + item.getKeterangan());   // pakai abstract method
        System.out.println("Kategori  : " + item.getKategori());     // pakai abstract method
        System.out.println("Jumlah    : " + jumlah);
        System.out.println("Total     : Rp" + hitungTotal());        // pakai method interface
    }


    // Method 1: Hitung total harga
    @Override
    public int hitungTotal() {
        return item.getHarga() * jumlah;
    }

    // Method 2: Tampil struk ringkas
    @Override
    public void tampilStruk() {
        System.out.println("========== STRUK PEMBELIAN ==========");
        System.out.println("Pelanggan : " + namaPelanggan);
        System.out.println("Menu      : " + item.nama);
        System.out.println("Kategori  : " + item.getKategori());
        System.out.println("Keterangan: " + item.getKeterangan());
        System.out.println("Harga     : Rp" + item.getHarga());
        System.out.println("Jumlah    : " + jumlah + "x");
        System.out.println("-------------------------------------");
        System.out.println("TOTAL     : Rp" + hitungTotal());
        System.out.println("=====================================");
    }

    // Method 3: Cek validitas transaksi
    @Override
    public boolean isTransaksiValid() {
        return jumlah > 0 && item != null;
    }
}