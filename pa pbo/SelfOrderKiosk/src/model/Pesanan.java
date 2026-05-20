package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Pesanan merepresentasikan satu pesanan lengkap dari pelanggan.
 * Menggunakan Composition dengan OrderItem dan StatusPesanan.
 */
public class Pesanan {
    private static int nomorCounter = 1;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private int nomorPesanan;
    private String namaPelanggan;
    private String nomorMeja;
    private List<OrderItem> items;
    private StatusPesanan status;
    private LocalDateTime waktuPesan;
    private LocalDateTime waktuUpdate;
    private String catatanKhusus;

    // Constructor
    public Pesanan(String namaPelanggan, String nomorMeja) {
        this.nomorPesanan = nomorCounter++;
        this.namaPelanggan = namaPelanggan;
        this.nomorMeja = nomorMeja;
        this.items = new ArrayList<>();
        this.status = StatusPesanan.MENUNGGU;
        this.waktuPesan = LocalDateTime.now();
        this.waktuUpdate = LocalDateTime.now();
        this.catatanKhusus = "";
    }

    // Tambah item ke pesanan
    public void tambahItem(OrderItem item) {
        // Cek apakah item sudah ada, jika ya tambah jumlahnya
        for (OrderItem existing : items) {
            if (existing.getMenuItem().getId() == item.getMenuItem().getId()) {
                existing.setJumlah(existing.getJumlah() + item.getJumlah());
                return;
            }
        }
        items.add(item);
    }

    // Hapus item dari pesanan berdasarkan index
    public boolean hapusItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    // Update status pesanan
    public void updateStatus(StatusPesanan statusBaru) {
        this.status = statusBaru;
        this.waktuUpdate = LocalDateTime.now();
    }

    // Hitung total harga
    public double getTotalHarga() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    // Hitung pajak (10%)
    public double getPajak() {
        return getTotalHarga() * 0.10;
    }

    // Hitung grand total
    public double getGrandTotal() {
        return getTotalHarga() + getPajak();
    }

    // Getters
    public int getNomorPesanan() { return nomorPesanan; }
    public String getNamaPelanggan() { return namaPelanggan; }
    public String getNomorMeja() { return nomorMeja; }
    public List<OrderItem> getItems() { return items; }
    public StatusPesanan getStatus() { return status; }
    public LocalDateTime getWaktuPesan() { return waktuPesan; }
    public LocalDateTime getWaktuUpdate() { return waktuUpdate; }
    public String getCatatanKhusus() { return catatanKhusus; }
    public boolean isEmpty() { return items.isEmpty(); }

    // Setters
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }
    public void setNomorMeja(String nomorMeja) { this.nomorMeja = nomorMeja; }
    public void setCatatanKhusus(String catatanKhusus) { this.catatanKhusus = catatanKhusus; }

    // Format waktu
    public String getWaktuPesanFormatted() {
        return waktuPesan.format(FORMATTER);
    }

    public String getWaktuUpdateFormatted() {
        return waktuUpdate.format(FORMATTER);
    }

    public static void resetCounter() { nomorCounter = 1; }

    @Override
    public String toString() {
        return String.format("Pesanan #%03d | %s | Meja %s | %s | Rp %,.0f",
                nomorPesanan, namaPelanggan, nomorMeja, status, getGrandTotal());
    }
}
