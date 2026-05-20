package service;

import model.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private static MenuService instance;

    private List<MenuItem> daftarMenu;

    private MenuService() {
        daftarMenu = new ArrayList<>();
        inisialisasiMenuDefault();
    }

    public static MenuService getInstance() {
        if (instance == null) {
            instance = new MenuService();
        }
        return instance;
    }

    // =========================
    // MENU DEFAULT
    // =========================
    private void inisialisasiMenuDefault() {

        tambahMenu(new MenuItem(1, "Nasi Goreng Spesial", "Makanan", 25000, "Nasi goreng dengan telur dan ayam"));
        tambahMenu(new MenuItem(2, "Mie Goreng Jumbo",    "Makanan", 22000, "Mie goreng porsi besar"));
        tambahMenu(new MenuItem(3, "Ayam Bakar Madu",     "Makanan", 35000, "Ayam bakar saus madu"));
        tambahMenu(new MenuItem(4, "Es Teh Manis",        "Minuman",  8000, "Teh manis dingin segar"));
        tambahMenu(new MenuItem(5, "Jus Alpukat",         "Minuman", 15000, "Jus alpukat segar"));
        tambahMenu(new MenuItem(6, "Kentang Goreng Crispy","Snack",  12000, "Kentang goreng crispy"));
    }

    // =========================
    // GENERATE ID OTOMATIS
    // =========================
    public int generateId() {
        return daftarMenu.stream()
                .mapToInt(MenuItem::getId)
                .max()
                .orElse(0) + 1;
    }

    // =========================
    // TAMBAH MENU
    // =========================
    public boolean tambahMenu(MenuItem item) {
        daftarMenu.add(item);
        return true;
    }

    // =========================
    // HAPUS MENU
    // =========================
    public boolean hapusMenu(int id) {
        return daftarMenu.removeIf(item -> item.getId() == id);
    }

    // =========================
    // EDIT HARGA
    // =========================
    public boolean editHarga(int id, double hargaBaru) {
        for (MenuItem item : daftarMenu) {
            if (item.getId() == id) {
                item.setHarga(hargaBaru);
                return true;
            }
        }
        return false;
    }

    // =========================
    // EDIT NAMA
    // =========================
    public boolean editNama(int id, String namaBaru) {
        for (MenuItem item : daftarMenu) {
            if (item.getId() == id) {
                item.setNama(namaBaru);
                return true;
            }
        }
        return false;
    }

    // =========================
    // CARI MENU BERDASARKAN ID
    // =========================
    public MenuItem cariMenuById(int id) {
        for (MenuItem item : daftarMenu) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    // =========================
    // CARI MENU BERDASARKAN NAMA
    // =========================
    public List<MenuItem> cariMenuByNama(String keyword) {
        List<MenuItem> hasil = new ArrayList<>();
        for (MenuItem item : daftarMenu) {
            if (item.getNama().toLowerCase().contains(keyword.toLowerCase())) {
                hasil.add(item);
            }
        }
        return hasil;
    }

    // =========================
    // MENU BERDASARKAN KATEGORI
    // =========================
    public List<MenuItem> getMenuByKategori(String kategori) {
        List<MenuItem> hasil = new ArrayList<>();
        for (MenuItem item : daftarMenu) {
            if (item.getKategori().equalsIgnoreCase(kategori) && item.isTersedia()) {
                hasil.add(item);
            }
        }
        return hasil;
    }

    // =========================
    // AMBIL SEMUA MENU
    // =========================
    public List<MenuItem> getAllMenu() {
        return daftarMenu;
    }

    // =========================
    // TOTAL MENU
    // =========================
    public int getTotalMenu() {
        return daftarMenu.size();
    }

    // =========================
    // TOGGLE TERSEDIA
    // =========================
    public boolean toggleKetersediaan(int id) {
        for (MenuItem item : daftarMenu) {
            if (item.getId() == id) {
                item.setTersedia(!item.isTersedia());
                return true;
            }
        }
        return false;
    }
}