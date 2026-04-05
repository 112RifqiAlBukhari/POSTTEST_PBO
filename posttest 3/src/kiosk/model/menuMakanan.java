package kiosk.model;

public class menuMakanan {
    // 1. PRIVATE
    private int id;
    // 2. PUBLIC
    public String nama;
    // 3. PROTECTED
    protected int harga;
    // 4. DEFAULT
    String kategori;

    public menuMakanan(int id, String nama, int harga, String kategori) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
    // --- SETTER & GETTER (Encapsulation) ---
    public int getId() { return id; }

    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }

    public void tampilMenu() {
        System.out.println(id + " | " + nama + " [" + kategori + "] | Rp" + harga);
    }
}