package kiosk.model;

public class menuMakanan {
    // Penggunaan Access Modifier yang berbeda (Sesuai tugas sebelumnya)
    private int id;         // Hanya bisa diakses di class ini
    public String nama;      // Bisa diakses di mana saja
    protected int harga;    // Bisa diakses oleh subclass
    String kategori;        // Default (Package-private)

    public menuMakanan(int id, String nama, int harga, String kategori) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // --- ENCAPSULATION (Getter & Setter) ---
    public int getId() { return id; }

    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }

    // --- POLYMORPHISM: OVERLOADING 1 (Berdasarkan Nama) ---
    public boolean cariMenu(String keyword) {
        return this.nama.equalsIgnoreCase(keyword);
    }

    // --- POLYMORPHISM: OVERLOADING 2 (Berdasarkan ID) ---
    public boolean cariMenu(int idCari) {
        return this.id == idCari;
    }

    // --- METHOD UNTUK OVERRIDE ---
    public void tampilMenu() {
        System.out.print(id + " | " + nama + " [" + kategori + "] | Rp" + harga);
    }
}