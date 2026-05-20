package ui;

import model.Manager;
import model.MenuItem;
import service.MenuService;
import util.DisplayHelper;
import util.InputHelper;

import java.util.List;

public class ManagerUI {

    private Manager manager;
    private MenuService menuService;

    public ManagerUI(Manager manager) {
        this.manager = manager;
        this.menuService = MenuService.getInstance();
    }

    // =========================
    // DASHBOARD MANAGER
    // =========================
    public void tampilkanDashboard() {
        DisplayHelper.tampilkanHeader("MANAGER DASHBOARD");
        System.out.println("Selamat datang, " + manager.getNamaLengkap());
        InputHelper.tungguEnter();
        menuManager();
    }

    // =========================
    // MENU MANAGER
    // =========================
    private void menuManager() {
        boolean lanjut = true;

        while (lanjut) {
            DisplayHelper.tampilkanHeader("MENU MANAGER");

            System.out.println("[1] Lihat Semua Menu");
            System.out.println("[2] Tambah Menu Baru");
            System.out.println("[3] Edit Harga Menu");
            System.out.println("[4] Hapus Menu");
            System.out.println("[0] Logout");

            int pilihan = InputHelper.bacaInt("Pilihan : ");

            switch (pilihan) {
                case 1 -> lihatSemuaMenu();
                case 2 -> tambahMenuBaru();
                case 3 -> editHargaMenu();
                case 4 -> hapusMenu();
                case 0 -> {
                    System.out.println("Logout berhasil!");
                    lanjut = false;
                }
                default -> {
                    System.out.println("Pilihan tidak valid!");
                    InputHelper.tungguEnter();
                }
            }
        }
    }

    // =========================
    // LIHAT MENU
    // =========================
    private void lihatSemuaMenu() {
        DisplayHelper.tampilkanHeader("DAFTAR MENU");

        List<MenuItem> daftarMenu = menuService.getAllMenu();

        if (daftarMenu.isEmpty()) {
            System.out.println("Belum ada menu.");
        } else {
            for (int i = 0; i < daftarMenu.size(); i++) {
                MenuItem menu = daftarMenu.get(i);
                System.out.println(
                        (i + 1) + ". "
                                + menu.getNama()
                                + " [" + menu.getKategori() + "]"
                                + " - Rp " + String.format("%,.0f", menu.getHarga())
                                + (menu.isTersedia() ? "" : " [TIDAK TERSEDIA]")
                );
            }
        }

        InputHelper.tungguEnter();
    }

    // =========================
    // TAMBAH MENU
    // =========================
    private void tambahMenuBaru() {
        DisplayHelper.tampilkanHeader("TAMBAH MENU BARU");

        String nama      = InputHelper.bacaString("Nama Menu  : ");
        String kategori  = InputHelper.bacaString("Kategori   : ");
        double harga     = InputHelper.bacaDouble("Harga      : Rp ");
        String deskripsi = InputHelper.bacaString("Deskripsi  : ");

        int idBaru = menuService.generateId();

        MenuItem menuBaru = new MenuItem(idBaru, nama, kategori, harga, deskripsi);
        menuService.tambahMenu(menuBaru);

        System.out.println("\nMenu \"" + nama + "\" berhasil ditambahkan!");
        System.out.println("(Menu ini sekarang tersedia untuk dipesan pelanggan)");
        InputHelper.tungguEnter();
    }

    // =========================
    // EDIT HARGA MENU
    // =========================
    private void editHargaMenu() {
        DisplayHelper.tampilkanHeader("EDIT HARGA MENU");

        List<MenuItem> daftarMenu = menuService.getAllMenu();

        if (daftarMenu.isEmpty()) {
            System.out.println("Belum ada menu yang tersedia.");
            InputHelper.tungguEnter();
            return;
        }

        for (int i = 0; i < daftarMenu.size(); i++) {
            MenuItem menu = daftarMenu.get(i);
            System.out.println(
                    (i + 1) + ". "
                            + menu.getNama()
                            + " - Rp " + String.format("%,.0f", menu.getHarga())
            );
        }

        int nomor = InputHelper.bacaInt("Pilih nomor menu yang ingin diedit (0 = batal) : ");

        if (nomor == 0) {
            System.out.println("Edit dibatalkan.");
            InputHelper.tungguEnter();
            return;
        }

        if (nomor < 1 || nomor > daftarMenu.size()) {
            System.out.println("Nomor menu tidak valid!");
            InputHelper.tungguEnter();
            return;
        }

        MenuItem dipilih = daftarMenu.get(nomor - 1);

        System.out.println("Menu dipilih   : " + dipilih.getNama());
        System.out.println("Harga saat ini : Rp " + String.format("%,.0f", dipilih.getHarga()));

        double hargaBaru = InputHelper.bacaDouble("Harga baru     : Rp ");

        if (hargaBaru <= 0) {
            System.out.println("Harga tidak valid! Harga harus lebih dari 0.");
            InputHelper.tungguEnter();
            return;
        }

        menuService.editHarga(dipilih.getId(), hargaBaru);

        System.out.println("Harga \"" + dipilih.getNama()
                + "\" berhasil diubah menjadi Rp "
                + String.format("%,.0f", hargaBaru) + "!");
        InputHelper.tungguEnter();
    }

    // =========================
    // HAPUS MENU
    // =========================
    private void hapusMenu() {
        DisplayHelper.tampilkanHeader("HAPUS MENU");

        List<MenuItem> daftarMenu = menuService.getAllMenu();

        if (daftarMenu.isEmpty()) {
            System.out.println("Belum ada menu yang tersedia.");
            InputHelper.tungguEnter();
            return;
        }

        for (int i = 0; i < daftarMenu.size(); i++) {
            MenuItem menu = daftarMenu.get(i);
            System.out.println(
                    (i + 1) + ". "
                            + menu.getNama()
                            + " - Rp " + String.format("%,.0f", menu.getHarga())
            );
        }

        int nomor = InputHelper.bacaInt("Pilih nomor menu yang ingin dihapus (0 = batal) : ");

        if (nomor == 0) {
            System.out.println("Hapus dibatalkan.");
            InputHelper.tungguEnter();
            return;
        }

        if (nomor < 1 || nomor > daftarMenu.size()) {
            System.out.println("Nomor menu tidak valid!");
            InputHelper.tungguEnter();
            return;
        }

        MenuItem dipilih = daftarMenu.get(nomor - 1);

        String konfirmasi = InputHelper.bacaString(
                "Yakin ingin menghapus \""
                        + dipilih.getNama() + "\"? (y/n) : "
        ).trim().toLowerCase();

        if (konfirmasi.equals("y")) {
            menuService.hapusMenu(dipilih.getId());
            System.out.println("Menu \"" + dipilih.getNama() + "\" berhasil dihapus!");
        } else {
            System.out.println("Hapus dibatalkan.");
        }

        InputHelper.tungguEnter();
    }
}