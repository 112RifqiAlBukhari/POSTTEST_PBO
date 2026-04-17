package kiosk.model;

// =====================================================
// INTERFACE - ITransaksi
// Diimplementasikan oleh class pesanan
// =====================================================
public interface ITransaksi {

    // Method 1: Menghitung total harga transaksi
    int hitungTotal();

    // Method 2: Menampilkan struk/ringkasan transaksi
    void tampilStruk();

    // Method 3: Mendapatkan status transaksi (apakah valid / jumlah > 0)
    boolean isTransaksiValid();
}
