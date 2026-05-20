package ui;

import model.Kitchen;
import model.OrderItem;
import model.Pesanan;
import model.StatusPesanan;
import service.NotaService;
import service.PesananService;
import util.DisplayHelper;
import util.InputHelper;

import java.util.List;

/**
 * KitchenUI - Antarmuka untuk Staff Dapur
 *
 * Alur status pesanan:
 *   MENUNGGU -> kitchen terima  -> DIPROSES
 *   MENUNGGU -> kitchen tolak   -> DIBATALKAN
 *   DIPROSES -> selesai masak   -> SIAP
 *   SIAP     -> pelanggan ambil -> SELESAI
 */
public class KitchenUI {

    private Kitchen kitchen;
    private PesananService pesananService;
    private NotaService notaService;

    public KitchenUI(Kitchen kitchen) {
        this.kitchen = kitchen;
        this.pesananService = PesananService.getInstance();
        this.notaService = new NotaService();
    }

    public void tampilkanDashboard() {
        DisplayHelper.tampilkanHeader("KITCHEN DASHBOARD");
        System.out.println("  Selamat datang, " + kitchen.getNamaLengkap() + "!");
        System.out.println("  Role: " + kitchen.getRole());
        InputHelper.tungguEnter();
        menuKitchen();
    }

    private void menuKitchen() {
        boolean lanjut = true;

        while (lanjut) {
            DisplayHelper.tampilkanHeader("MENU KITCHEN STAFF");

            int jumlahMenunggu = pesananService.countByStatus(StatusPesanan.MENUNGGU);
            int jumlahDiproses = pesananService.countByStatus(StatusPesanan.DIPROSES);
            int jumlahSiap     = pesananService.countByStatus(StatusPesanan.SIAP);

            System.out.println("  Staff  : " + kitchen.getNamaLengkap());
            System.out.println("  ----------------------------------------");
            if (jumlahMenunggu > 0) {
                System.out.println("  ⚠ Ada " + jumlahMenunggu + " pesanan menunggu konfirmasi!");
            }
            System.out.println("  ----------------------------------------");
            System.out.println("  [1] Pesanan Masuk (Terima / Tolak)   [" + jumlahMenunggu + " menunggu]");
            System.out.println("  [2] Pesanan Sedang Diproses          [" + jumlahDiproses + " diproses]");
            System.out.println("  [3] Pesanan Siap (Tandai Selesai)    [" + jumlahSiap + " siap]");
            System.out.println("  [4] Lihat Semua Pesanan Aktif");
            System.out.println("  [5] Riwayat Pesanan Selesai");
            System.out.println("  [0] Logout");
            System.out.println();

            int pilihan = InputHelper.bacaInt("  Pilihan: ");

            switch (pilihan) {
                case 1 -> pesananMasuk();
                case 2 -> pesananDiproses();
                case 3 -> pesananSiap();
                case 4 -> lihatSemuaPesananAktif();
                case 5 -> riwayatPesananSelesai();
                case 0 -> {
                    System.out.println("\n  Logout berhasil. Sampai jumpa, " + kitchen.getNamaLengkap() + "!");
                    InputHelper.tungguEnter();
                    lanjut = false;
                }
                default -> {
                    System.out.println("\n  Pilihan tidak tersedia, coba lagi.");
                    InputHelper.tungguEnter();
                }
            }
        }
    }

    // [1] Kitchen terima atau tolak pesanan yang MENUNGGU
    private void pesananMasuk() {
        DisplayHelper.tampilkanHeader("PESANAN MASUK");

        List<Pesanan> daftarMenunggu = pesananService.getPesananByStatus(StatusPesanan.MENUNGGU);

        if (daftarMenunggu.isEmpty()) {
            System.out.println("\n  Tidak ada pesanan yang menunggu konfirmasi.");
            InputHelper.tungguEnter();
            return;
        }

        System.out.println("\n  Daftar pesanan menunggu konfirmasi:");
        tampilkanDaftarRingkas(daftarMenunggu);

        System.out.println("\n  [0] Kembali");
        int nomorPesanan = InputHelper.bacaInt("  Masukkan nomor pesanan: ");

        if (nomorPesanan == 0) return;

        Pesanan pesanan = pesananService.cariPesananByNomor(nomorPesanan);

        if (pesanan == null || pesanan.getStatus() != StatusPesanan.MENUNGGU) {
            System.out.println("\n  Pesanan tidak ditemukan atau bukan status MENUNGGU.");
            InputHelper.tungguEnter();
            return;
        }

        tampilkanDetailPesanan(pesanan);

        System.out.println("\n  Pilih tindakan:");
        System.out.println("  [1] Terima  -> Status berubah ke: SEDANG DIPROSES");
        System.out.println("  [2] Tolak   -> Status berubah ke: DIBATALKAN");
        System.out.println("  [0] Batal");
        System.out.println();

        int tindakan = InputHelper.bacaInt("  Pilihan: ");

        switch (tindakan) {
            case 1 -> {
                pesananService.updateStatus(nomorPesanan, StatusPesanan.DIPROSES);
                System.out.println("\n  ✅ Pesanan #" + String.format("%03d", nomorPesanan)
                        + " diterima dan mulai DIPROSES.");
                notaService.cetakStrukKitchen(pesanan);
            }
            case 2 -> {
                if (InputHelper.bacaKonfirmasi("  Yakin ingin MEMBATALKAN pesanan ini?")) {
                    pesananService.updateStatus(nomorPesanan, StatusPesanan.DIBATALKAN);
                    System.out.println("\n  ❌ Pesanan #" + String.format("%03d", nomorPesanan)
                            + " telah DIBATALKAN.");
                } else {
                    System.out.println("\n  Pembatalan dibatalkan.");
                }
            }
            case 0 -> { /* kembali */ }
            default -> System.out.println("\n  Pilihan tidak valid.");
        }

        InputHelper.tungguEnter();
    }

    // [2] Tandai pesanan DIPROSES -> SIAP
    private void pesananDiproses() {
        DisplayHelper.tampilkanHeader("PESANAN SEDANG DIPROSES");

        List<Pesanan> daftarDiproses = pesananService.getPesananByStatus(StatusPesanan.DIPROSES);

        if (daftarDiproses.isEmpty()) {
            System.out.println("\n  Tidak ada pesanan yang sedang diproses.");
            InputHelper.tungguEnter();
            return;
        }

        System.out.println("\n  Daftar pesanan sedang dimasak:");
        tampilkanDaftarRingkas(daftarDiproses);

        System.out.println("\n  [0] Kembali");
        int nomorPesanan = InputHelper.bacaInt("  Masukkan nomor pesanan yang sudah selesai dimasak: ");

        if (nomorPesanan == 0) return;

        Pesanan pesanan = pesananService.cariPesananByNomor(nomorPesanan);

        if (pesanan == null || pesanan.getStatus() != StatusPesanan.DIPROSES) {
            System.out.println("\n  Pesanan tidak ditemukan atau bukan status DIPROSES.");
            InputHelper.tungguEnter();
            return;
        }

        pesananService.updateStatus(nomorPesanan, StatusPesanan.SIAP);
        System.out.println("\n  ✅ Pesanan #" + String.format("%03d", nomorPesanan)
                + " selesai dimasak -> Status: PESANAN SIAP.");
        System.out.println("  Pelanggan sudah bisa mengambil pesanannya.");

        InputHelper.tungguEnter();
    }

    // [3] Tandai pesanan SIAP -> SELESAI (sudah diambil pelanggan)
    private void pesananSiap() {
        DisplayHelper.tampilkanHeader("PESANAN SIAP DIAMBIL");

        List<Pesanan> daftarSiap = pesananService.getPesananByStatus(StatusPesanan.SIAP);

        if (daftarSiap.isEmpty()) {
            System.out.println("\n  Tidak ada pesanan yang siap diambil.");
            InputHelper.tungguEnter();
            return;
        }

        System.out.println("\n  Daftar pesanan siap diambil:");
        tampilkanDaftarRingkas(daftarSiap);

        System.out.println("\n  [0] Kembali");
        int nomorPesanan = InputHelper.bacaInt("  Masukkan nomor pesanan yang sudah diambil pelanggan: ");

        if (nomorPesanan == 0) return;

        Pesanan pesanan = pesananService.cariPesananByNomor(nomorPesanan);

        if (pesanan == null || pesanan.getStatus() != StatusPesanan.SIAP) {
            System.out.println("\n  Pesanan tidak ditemukan atau bukan status SIAP.");
            InputHelper.tungguEnter();
            return;
        }

        pesananService.updateStatus(nomorPesanan, StatusPesanan.SELESAI);
        System.out.println("\n  🎉 Pesanan #" + String.format("%03d", nomorPesanan)
                + " sudah diambil -> Status: SELESAI.");
        System.out.println("  Terima kasih, selamat menikmati!");

        InputHelper.tungguEnter();
    }

    // [4] Lihat semua pesanan aktif
    private void lihatSemuaPesananAktif() {
        DisplayHelper.tampilkanHeader("SEMUA PESANAN AKTIF");

        List<Pesanan> aktif = pesananService.getPesananAktif();

        if (aktif.isEmpty()) {
            System.out.println("\n  Tidak ada pesanan aktif saat ini.");
        } else {
            for (Pesanan p : aktif) {
                tampilkanDetailPesanan(p);
            }
        }

        InputHelper.tungguEnter();
    }

    // [5] Riwayat pesanan selesai dan dibatalkan
    private void riwayatPesananSelesai() {
        DisplayHelper.tampilkanHeader("RIWAYAT PESANAN");

        List<Pesanan> selesai    = pesananService.getPesananByStatus(StatusPesanan.SELESAI);
        List<Pesanan> dibatalkan = pesananService.getPesananByStatus(StatusPesanan.DIBATALKAN);

        if (selesai.isEmpty() && dibatalkan.isEmpty()) {
            System.out.println("\n  Belum ada riwayat pesanan.");
            InputHelper.tungguEnter();
            return;
        }

        if (!selesai.isEmpty()) {
            System.out.println("\n  ── PESANAN SELESAI ──────────────────────────");
            tampilkanDaftarRingkas(selesai);
        }

        if (!dibatalkan.isEmpty()) {
            System.out.println("\n  ── PESANAN DIBATALKAN ───────────────────────");
            tampilkanDaftarRingkas(dibatalkan);
        }

        System.out.println("\n  ─────────────────────────────────────────────");
        System.out.printf("  Total pendapatan (pesanan selesai) : Rp %,.0f%n",
                pesananService.getTotalPendapatan());

        InputHelper.tungguEnter();
    }

    // Helper: tampilkan daftar ringkas
    private void tampilkanDaftarRingkas(List<Pesanan> list) {
        System.out.println("  ─────────────────────────────────────────────");
        System.out.printf("  %-6s %-15s %-6s %-22s %10s%n",
                "No", "Pelanggan", "Meja", "Status", "Total");
        System.out.println("  ─────────────────────────────────────────────");
        for (Pesanan p : list) {
            System.out.printf("  #%-5d %-15s %-6s %-22s Rp%,.0f%n",
                    p.getNomorPesanan(),
                    potong(p.getNamaPelanggan(), 14),
                    p.getNomorMeja(),
                    p.getStatus().getLabel(),
                    p.getGrandTotal());
        }
        System.out.println("  ─────────────────────────────────────────────");
    }

    // Helper: tampilkan detail satu pesanan
    private void tampilkanDetailPesanan(Pesanan p) {
        System.out.println("\n  ┌─ PESANAN #" + String.format("%03d", p.getNomorPesanan()) + " ──────────────────────");
        System.out.println("  │ Pelanggan : " + p.getNamaPelanggan());
        System.out.println("  │ Meja      : " + p.getNomorMeja());
        System.out.println("  │ Waktu     : " + p.getWaktuPesanFormatted());
        System.out.println("  │ Status    : " + p.getStatus());
        System.out.println("  ├── ITEM ──────────────────────────────────");
        for (OrderItem item : p.getItems()) {
            System.out.printf("  │  [%dx] %s%n",
                    item.getJumlah(), item.getMenuItem().getNama());
            if (!item.getCatatan().isEmpty()) {
                System.out.println("  │       -> " + item.getCatatan());
            }
        }
        if (!p.getCatatanKhusus().isEmpty()) {
            System.out.println("  │  Catatan Khusus: " + p.getCatatanKhusus());
        }
        System.out.printf("  │ Total     : Rp %,.0f%n", p.getGrandTotal());
        System.out.println("  └──────────────────────────────────────────");
    }

    // Helper: potong string
    private String potong(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max - 2) + "..";
    }
}