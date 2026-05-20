package ui;

import model.MenuItem;
import model.OrderItem;
import model.Pesanan;
import service.MenuService;
import service.NotaService;
import service.PesananService;
import util.DisplayHelper;
import util.InputHelper;
import java.util.List;

/**
 * PelangganUI - Antarmuka untuk Pelanggan
 * Dikerjakan oleh: [NAMA KAMU]

 * Status:
 * [v] Tampilan halaman pesan        (SELESAI - bagianmu)
 * [v] Tampilan menu pilihan pelanggan (SELESAI - bagianmu)
 * [v] Tambah Pesanan        -> SELESAI
 * [v] Lihat Pesanan Saya    -> SELESAI
 * [ ] Hapus Item Pesanan    -> dikerjakan teman
 * [ ] Cetak Nota            -> dikerjakan teman
 * [ ] Lihat Status Pesanan  -> dikerjakan teman
 */
public class PelangganUI {

    private MenuService menuService;
    private PesananService pesananService;
    private NotaService notaService;
    private Pesanan pesananAktif;
    private int pesananTerkonfirmasiNomor;

    public PelangganUI() {
        this.menuService = MenuService.getInstance();
        this.pesananService = PesananService.getInstance();
        this.notaService = new NotaService();
        this.pesananAktif = null;
        this.pesananTerkonfirmasiNomor = 0;
    }

    // Entry point untuk alur pelanggan
    public void mulai() {
        DisplayHelper.tampilkanHeader("HALAMAN PEMESANAN");

        System.out.println("  Sebelum memesan, mohon isi data berikut:\n");

        // Nama hanya boleh huruf dan spasi
        String nama = bacaNamaValid("  Nama Anda   : ");

        // Nomor meja hanya boleh angka
        String meja = bacaMejaValid("  Nomor Meja  : ");

        System.out.println("\n  Halo, " + nama + "! Anda berada di Meja #" + meja);
        System.out.println("  Silakan pilih menu di bawah ini.");
        InputHelper.tungguEnter();

        pesananAktif = new Pesanan(nama, meja);
        menuPelanggan(nama, meja);
    }

    // =========================
    // VALIDASI NAMA (huruf & spasi saja)
    // =========================
    private String bacaNamaValid(String pesan) {
        while (true) {
            String input = InputHelper.bacaString(pesan).trim();

            if (input.isEmpty()) {
                System.out.println("  Nama tidak boleh kosong!");
                continue;
            }

            // Hanya huruf (a-z, A-Z) dan spasi
            if (!input.matches("[a-zA-Z ]+")) {
                System.out.println("  Nama hanya boleh berisi huruf! Angka dan simbol tidak diperbolehkan.");
                continue;
            }

            return input;
        }
    }

    // =========================
    // VALIDASI NOMOR MEJA (angka saja)
    // =========================
    private String bacaMejaValid(String pesan) {
        while (true) {
            String input = InputHelper.bacaString(pesan).trim();

            if (input.isEmpty()) {
                System.out.println("  Nomor meja tidak boleh kosong!");
                continue;
            }

            // Hanya angka
            if (!input.matches("[0-9]+")) {
                System.out.println("  Nomor meja hanya boleh berisi angka!");
                continue;
            }

            return input;
        }
    }

    // Menu utama pelanggan
    private void menuPelanggan(String nama, String meja) {
        boolean lanjut = true;

        while (lanjut) {
            DisplayHelper.tampilkanHeader("MENU PELANGGAN");

            System.out.println("  Halo, " + nama + " | Meja #" + meja);
            System.out.println("  ----------------------------------------");
            System.out.println("  [1] Tambah Pesanan");
            System.out.println("  [2] Lihat Pesanan Saya");
            System.out.println("  [3] Hapus Item dari Pesanan");
            System.out.println("  [4] Cetak Nota dan Konfirmasi Pemesanan");
            System.out.println("  [5] Lihat Status Pesanan");
            System.out.println("  [0] Kembali ke Halaman Utama");
            System.out.println();

            int pilihan = bacaPilihanInt("  Pilihan: ");

            switch (pilihan) {
                case 1 -> tambahPesanan();
                case 2 -> lihatPesananSaya();
                case 3 -> hapusItemPesanan();
                case 4 -> cetakNotaDanKonfirmasi();
                case 5 -> lihatStatusPesanan();
                case 0 -> {
                    if (pesananAktif != null && !pesananAktif.isEmpty()) {
                        System.out.println("\n  Pesanan Anda belum disimpan!");
                        String simpan = InputHelper.bacaString("  Simpan pesanan? (y/n): ");
                        if (simpan.equalsIgnoreCase("y")) {
                            pesananService.simpanPesanan(pesananAktif);
                            System.out.println("  Pesanan #" + pesananAktif.getNomorPesanan() + " telah disimpan!");
                        }
                    }
                    lanjut = false;
                }
                default -> {
                    System.out.println("\n  Pilihan tidak tersedia, coba lagi.");
                    InputHelper.tungguEnter();
                }
            }
        }
    }

    //buat input tambah pesanan dan lihat menu
    private void tambahPesanan() {
        DisplayHelper.tampilkanHeader("TAMBAH PESANAN");

        boolean lanjutTambah = true;

        while (lanjutTambah) {
            tampilkanSemuaMenu();

            System.out.println("\n  ----------------------------------------");
            System.out.println("  [0] Kembali ke Menu Utama");
            System.out.println();

            int idMenu = bacaPilihanInt("  Masukkan ID Menu: ");

            if (idMenu == 0) {
                lanjutTambah = false;
                continue;
            }

            if (idMenu < 0) {
                System.out.println("\n  Pilihan tidak tersedia, coba lagi.");
                InputHelper.tungguEnter();
                continue;
            }

            MenuItem selectedMenu = menuService.cariMenuById(idMenu);

            if (selectedMenu == null) {
                System.out.println("\n  Pilihan tidak tersedia, coba lagi.");
                InputHelper.tungguEnter();
                continue;
            }

            if (!selectedMenu.isTersedia()) {
                System.out.println("\n  Menu '" + selectedMenu.getNama() + "' sedang tidak tersedia!");
                InputHelper.tungguEnter();
                continue;
            }

            System.out.println("\n  -------------------------------------------");
            System.out.println("  Menu: " + selectedMenu.getNama());
            System.out.println("  Kategori: " + selectedMenu.getKategori());
            System.out.println("  Harga: Rp " + String.format("%,.0f", selectedMenu.getHarga()));
            System.out.println("  " + selectedMenu.getDeskripsi());
            System.out.println("  -------------------------------------------");

            int jumlah = bacaPilihanInt("\n  Masukkan jumlah: ");

            if (jumlah <= 0) {
                System.out.println("\n  Jumlah harus lebih dari 0!");
                InputHelper.tungguEnter();
                continue;
            }

            System.out.print("  Catatan khusus (opsional, tekan Enter jika tidak ada): ");
            String catatan = InputHelper.bacaStringOpsional("");

            OrderItem orderItem;
            if (catatan != null && !catatan.trim().isEmpty()) {
                orderItem = new OrderItem(selectedMenu, jumlah, catatan);
            } else {
                orderItem = new OrderItem(selectedMenu, jumlah);
            }

            pesananAktif.tambahItem(orderItem);

            System.out.println("\n  Pesanan berhasil ditambahkan!");
            System.out.println("  " + jumlah + "x " + selectedMenu.getNama() + " masuk ke pesanan.");

            tampilkanRingkasanPesanan();

            System.out.println();
            String lanjut = InputHelper.bacaString("  Tambah pesanan lagi? (y/n): ");
            if (!lanjut.equalsIgnoreCase("y")) {
                lanjutTambah = false;
            }
        }
    }

    //buat melihatkan pesanan sudah di pesan
    private void tampilkanSemuaMenu() {
        System.out.println("\n  =================================================");
        System.out.println("  ID   Nama Menu                    Kategori   Harga");
        System.out.println("  -------------------------------------------------");

        for (MenuItem menu : menuService.getAllMenu()) {
            System.out.printf("  %-3d  %-26s %-9s Rp %,.0f\n",
                    menu.getId(),
                    truncateString(menu.getNama(), 26),
                    menu.getKategori(),
                    menu.getHarga()
            );
        }
        System.out.println("  =================================================");
    }

    private String truncateString(String str, int length) {
        if (str == null) return "";
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length - 3) + "...";
    }

    private void tampilkanRingkasanPesanan() {
        if (pesananAktif == null || pesananAktif.isEmpty()) {
            System.out.println("\n  -------------------------------------------");
            System.out.println("  Belum ada pesanan.");
            System.out.println("  -------------------------------------------");
            return;
        }

        System.out.println("\n  ------------- RINGKASAN PESANAN -------------");

        int no = 1;
        for (OrderItem item : pesananAktif.getItems()) {
            System.out.printf("  %d. %s x %d = Rp %,.0f\n",
                    no++,
                    item.getMenuItem().getNama(),
                    item.getJumlah(),
                    item.getSubtotal()
            );
            if (item.getCatatan() != null && !item.getCatatan().isEmpty()) {
                System.out.printf("      Catatan: %s\n", item.getCatatan());
            }
        }
        System.out.println("  -------------------------------------------");
        System.out.printf("  Subtotal    : Rp %,.0f\n", pesananAktif.getTotalHarga());
        System.out.printf("  Pajak (10%%) : Rp %,.0f\n", pesananAktif.getPajak());
        System.out.println("  -------------------------------------------");
        System.out.printf("  GRAND TOTAL : Rp %,.0f\n", pesananAktif.getGrandTotal());
        System.out.println("  -------------------------------------------");
    }

    //buat lihat pesanan yg sudah di pesan
    private void lihatPesananSaya() {
        DisplayHelper.tampilkanHeader("PESANAN SAYA");

        if (pesananAktif == null || pesananAktif.isEmpty()) {
            System.out.println("\n  -------------------------------------------");
            System.out.println("  Anda belum memiliki pesanan.");
            System.out.println("  Silakan tambah pesanan terlebih dahulu.");
            System.out.println("  -------------------------------------------");
        } else {
            System.out.println("\n  ------------- DETAIL PESANAN -------------");

            int no = 1;
            for (OrderItem item : pesananAktif.getItems()) {
                System.out.printf("  %d. %s x %d = Rp %,.0f\n",
                        no++,
                        item.getMenuItem().getNama(),
                        item.getJumlah(),
                        item.getSubtotal()
                );
                if (item.getCatatan() != null && !item.getCatatan().isEmpty()) {
                    System.out.printf("      Catatan: %s\n", item.getCatatan());
                }
            }

            System.out.println("  -------------------------------------------");
            System.out.printf("  Subtotal    : Rp %,.0f\n", pesananAktif.getTotalHarga());
            System.out.printf("  Pajak (10%%) : Rp %,.0f\n", pesananAktif.getPajak());
            System.out.println("  -------------------------------------------");
            System.out.printf("  GRAND TOTAL : Rp %,.0f\n", pesananAktif.getGrandTotal());
            System.out.println("  -------------------------------------------");
        }

        InputHelper.tungguEnter();
    }

    // Method untuk membaca integer dengan validasi (menangani input huruf)
    private int bacaPilihanInt(String pesan) {
        try {
            return InputHelper.bacaInt(pesan);
        } catch (Exception e) {
            return -1;
        }
    }

    private void hapusItemPesanan() {
        DisplayHelper.tampilkanHeader("HAPUS ITEM PESANAN");

        if (pesananAktif == null || pesananAktif.isEmpty()) {
            System.out.println("\n  -------------------------------------------");
            System.out.println("  Pesanan Anda masih kosong.");
            System.out.println("  Silakan tambah pesanan terlebih dahulu.");
            System.out.println("  -------------------------------------------");
            InputHelper.tungguEnter();
            return;
        }

        System.out.println("\n  Item pesanan Anda saat ini:");
        System.out.println("  -------------------------------------------");
        int no = 1;
        for (OrderItem item : pesananAktif.getItems()) {
            System.out.printf("  [%d] %-24s x%d  = Rp %,.0f%n",
                    no++,
                    item.getMenuItem().getNama(),
                    item.getJumlah(),
                    item.getSubtotal());
            if (item.getCatatan() != null && !item.getCatatan().isEmpty()) {
                System.out.printf("      Catatan: %s%n", item.getCatatan());
            }
        }
        System.out.println("  [0] Batal (kembali)");
        System.out.println("  -------------------------------------------");

        int pilihan = bacaPilihanInt("  Pilih nomor item yang ingin dihapus: ");

        if (pilihan == 0) {
            System.out.println("\n  Penghapusan dibatalkan.");
            InputHelper.tungguEnter();
            return;
        }

        if (pilihan < 1 || pilihan > pesananAktif.getItems().size()) {
            System.out.println("\n  Nomor item tidak valid!");
            InputHelper.tungguEnter();
            return;
        }

        int index = pilihan - 1;
        String namaItem = pesananAktif.getItems().get(index).getMenuItem().getNama();

        System.out.println("\n  Item yang akan dihapus: " + namaItem);
        if (InputHelper.bacaKonfirmasi("  Yakin ingin menghapus item ini?")) {
            boolean berhasil = pesananAktif.hapusItem(index);
            if (berhasil) {
                System.out.println("\n  Item \"" + namaItem + "\" berhasil dihapus dari pesanan.");
                if (!pesananAktif.isEmpty()) {
                    tampilkanRingkasanPesanan();
                } else {
                    System.out.println("\n  Pesanan Anda sekarang kosong.");
                }
            } else {
                System.out.println("\n  Gagal menghapus item. Silakan coba lagi.");
            }
        } else {
            System.out.println("\n  Penghapusan dibatalkan.");
        }

        InputHelper.tungguEnter();
    }

    // [4] Cetak nota dan konfirmasi pemesanan
    private void cetakNotaDanKonfirmasi() {
        DisplayHelper.tampilkanHeader("CETAK NOTA & KONFIRMASI PEMESANAN");

        if (pesananAktif == null || pesananAktif.isEmpty()) {
            System.out.println("\n  -------------------------------------------");
            System.out.println("  Pesanan Anda masih kosong.");
            System.out.println("  Silakan tambah pesanan terlebih dahulu.");
            System.out.println("  -------------------------------------------");
            InputHelper.tungguEnter();
            return;
        }

        System.out.println("\n  Berikut ringkasan pesanan Anda sebelum dikonfirmasi:");
        tampilkanRingkasanPesanan();

        System.out.println();
        System.out.println("  Apakah ada catatan khusus untuk pesanan ini?");
        String catatan = InputHelper.bacaStringOpsional("  Catatan (kosongkan jika tidak ada): ");
        if (catatan != null && !catatan.trim().isEmpty()) {
            pesananAktif.setCatatanKhusus(catatan.trim());
        }

        System.out.println();
        if (InputHelper.bacaKonfirmasi("  Konfirmasi dan kirim pesanan ini?")) {
            boolean berhasil = pesananService.simpanPesanan(pesananAktif);

            if (berhasil) {
                System.out.println("\n  Pesanan #" + String.format("%03d", pesananAktif.getNomorPesanan())
                        + " berhasil dikonfirmasi!");
                System.out.println("  Pesanan Anda sedang diproses oleh dapur.\n");

                notaService.cetakNota(pesananAktif);

                pesananTerkonfirmasiNomor = pesananAktif.getNomorPesanan();
                pesananAktif = new Pesanan(pesananAktif.getNamaPelanggan(), pesananAktif.getNomorMeja());

                System.out.println("  Nota di atas adalah bukti pesanan Anda.");
                System.out.println("  Silakan tunjukkan nota ini kepada kasir jika diperlukan.");
            } else {
                System.out.println("\n  Gagal menyimpan pesanan. Silakan coba lagi.");
            }
        } else {
            System.out.println("\n  Konfirmasi dibatalkan. Pesanan belum dikirim.");
        }

        InputHelper.tungguEnter();
    }

    // [5] Lihat status pesanan
    private void lihatStatusPesanan() {
        DisplayHelper.tampilkanHeader("CEK STATUS PESANAN");

        List<Pesanan> semuaPesanan = pesananService.getAllPesanan();

        if (semuaPesanan.isEmpty()) {
            System.out.println("\n  Belum ada pesanan yang masuk ke sistem.");
            System.out.println("  Silakan buat pesanan terlebih dahulu.");
            InputHelper.tungguEnter();
            return;
        }

        System.out.println("\n  Daftar semua pesanan:");
        System.out.println("  ─────────────────────────────────────────────────");
        System.out.printf("  %-6s %-15s %-6s %-20s%n", "No", "Nama", "Meja", "Status");
        System.out.println("  ─────────────────────────────────────────────────");
        for (Pesanan p : semuaPesanan) {
            System.out.printf("  #%-5d %-15s %-6s %s%n",
                    p.getNomorPesanan(),
                    p.getNamaPelanggan().length() > 14
                            ? p.getNamaPelanggan().substring(0, 13) + "."
                            : p.getNamaPelanggan(),
                    p.getNomorMeja(),
                    p.getStatus().toString());
        }
        System.out.println("  ─────────────────────────────────────────────────");

        System.out.println("\n  Masukkan nomor pesanan untuk melihat detail status.");
        System.out.println("  [0] Kembali");
        System.out.println();

        int nomorInput = bacaPilihanInt("  Nomor Pesanan: ");
        if (nomorInput <= 0) return;

        Pesanan pesanan = pesananService.cariPesananByNomor(nomorInput);

        if (pesanan == null) {
            System.out.println("\n  Pesanan #" + String.format("%03d", nomorInput) + " tidak ditemukan.");
            InputHelper.tungguEnter();
            return;
        }

        System.out.println("\n  ┌─── STATUS PESANAN ─────────────────────────");
        System.out.println("  │ No. Pesanan : #" + String.format("%03d", pesanan.getNomorPesanan()));
        System.out.println("  │ Pelanggan   : " + pesanan.getNamaPelanggan());
        System.out.println("  │ No. Meja    : " + pesanan.getNomorMeja());
        System.out.println("  │ Waktu Pesan : " + pesanan.getWaktuPesanFormatted());
        System.out.println("  │");
        System.out.println("  │ STATUS SAAT INI:");
        System.out.println("  │   " + pesanan.getStatus().toString());
        System.out.println("  │");

        switch (pesanan.getStatus()) {
            case MENUNGGU -> {
                System.out.println("  │ Pesanan sudah masuk dan sedang");
                System.out.println("  │ menunggu konfirmasi dari dapur.");
            }
            case DIPROSES -> {
                System.out.println("  │ Pesanan sedang dimasak oleh dapur.");
                System.out.println("  │ Mohon bersabar, sebentar lagi selesai!");
            }
            case SIAP -> {
                System.out.println("  │ Pesanan sudah siap!");
                System.out.println("  │ Silakan ambil pesanan Anda sekarang.");
            }
            case SELESAI -> {
                System.out.println("  │ Pesanan telah selesai & diambil.");
                System.out.println("  │ Terima kasih, selamat menikmati!");
            }
            case DIBATALKAN -> {
                System.out.println("  │ Maaf, pesanan dibatalkan oleh dapur.");
                System.out.println("  │ Silakan hubungi staff untuk informasi lebih lanjut.");
            }
        }

        System.out.println("  │");
        System.out.println("  │ Terakhir diupdate : " + pesanan.getWaktuUpdateFormatted());
        System.out.println("  └────────────────────────────────────────────");

        InputHelper.tungguEnter();
    }
}