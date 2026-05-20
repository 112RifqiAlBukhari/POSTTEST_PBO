package service;

import model.Pesanan;
import model.StatusPesanan;
import java.util.ArrayList;
import java.util.List;

/**
 * Class PesananService mengelola semua operasi terkait pesanan.
 * Menggunakan pola Singleton agar data pesanan bisa diakses bersama
 * oleh PelangganUI dan KitchenUI dalam satu sesi program.
 */
public class PesananService {
    // Singleton instance
    private static PesananService instance;

    private List<Pesanan> daftarPesanan;

    // Constructor private agar tidak bisa di-new dari luar
    private PesananService() {
        this.daftarPesanan = new ArrayList<>();
    }

    // Method untuk mendapatkan satu-satunya instance
    public static PesananService getInstance() {
        if (instance == null) {
            instance = new PesananService();
        }
        return instance;
    }

    // Simpan pesanan baru
    public boolean simpanPesanan(Pesanan pesanan) {
        if (pesanan == null || pesanan.isEmpty()) {
            return false;
        }
        daftarPesanan.add(pesanan);
        return true;
    }

    // Update status pesanan
    public boolean updateStatus(int nomorPesanan, StatusPesanan statusBaru) {
        for (Pesanan pesanan : daftarPesanan) {
            if (pesanan.getNomorPesanan() == nomorPesanan) {
                pesanan.updateStatus(statusBaru);
                return true;
            }
        }
        return false;
    }

    // Cari pesanan berdasarkan nomor
    public Pesanan cariPesananByNomor(int nomorPesanan) {
        for (Pesanan pesanan : daftarPesanan) {
            if (pesanan.getNomorPesanan() == nomorPesanan) {
                return pesanan;
            }
        }
        return null;
    }

    // Cari pesanan berdasarkan nama pelanggan
    public List<Pesanan> cariPesananByNamaPelanggan(String nama) {
        List<Pesanan> hasil = new ArrayList<>();
        for (Pesanan pesanan : daftarPesanan) {
            if (pesanan.getNamaPelanggan().toLowerCase().contains(nama.toLowerCase())) {
                hasil.add(pesanan);
            }
        }
        return hasil;
    }

    // Ambil semua pesanan
    public List<Pesanan> getAllPesanan() {
        return new ArrayList<>(daftarPesanan);
    }

    // Ambil pesanan berdasarkan status
    public List<Pesanan> getPesananByStatus(StatusPesanan status) {
        List<Pesanan> hasil = new ArrayList<>();
        for (Pesanan pesanan : daftarPesanan) {
            if (pesanan.getStatus() == status) {
                hasil.add(pesanan);
            }
        }
        return hasil;
    }

    // Ambil pesanan aktif (bukan selesai/dibatalkan)
    public List<Pesanan> getPesananAktif() {
        List<Pesanan> hasil = new ArrayList<>();
        for (Pesanan pesanan : daftarPesanan) {
            if (pesanan.getStatus() != StatusPesanan.SELESAI &&
                    pesanan.getStatus() != StatusPesanan.DIBATALKAN) {
                hasil.add(pesanan);
            }
        }
        return hasil;
    }

    // Ambil riwayat pesanan selesai
    public List<Pesanan> getPesananSelesai() {
        return getPesananByStatus(StatusPesanan.SELESAI);
    }

    // Hitung total pendapatan dari pesanan selesai
    public double getTotalPendapatan() {
        double total = 0;
        for (Pesanan pesanan : daftarPesanan) {
            if (pesanan.getStatus() == StatusPesanan.SELESAI) {
                total += pesanan.getGrandTotal();
            }
        }
        return total;
    }

    // Hitung total semua pesanan (termasuk yang belum selesai)
    public double getTotalSemuaPesanan() {
        double total = 0;
        for (Pesanan pesanan : daftarPesanan) {
            if (pesanan.getStatus() != StatusPesanan.DIBATALKAN) {
                total += pesanan.getGrandTotal();
            }
        }
        return total;
    }

    // Batalkan pesanan
    public boolean batalkanPesanan(int nomorPesanan) {
        return updateStatus(nomorPesanan, StatusPesanan.DIBATALKAN);
    }

    // Hitung jumlah pesanan berdasarkan status
    public int countByStatus(StatusPesanan status) {
        int count = 0;
        for (Pesanan pesanan : daftarPesanan) {
            if (pesanan.getStatus() == status) count++;
        }
        return count;
    }

    // Hapus pesanan berdasarkan nomor
    public boolean hapusPesanan(int nomorPesanan) {
        return daftarPesanan.removeIf(p -> p.getNomorPesanan() == nomorPesanan);
    }

    // Ambil total jumlah pesanan
    public int getTotalPesanan() {
        return daftarPesanan.size();
    }


}