package kiosk.model;


public abstract class menuMakanan {

    // --- Access Modifier ---
    private int id;
    public String nama;
    protected int harga;
    String kategori;

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

    // --- METHOD UNTUK OVERRIDE (concrete) ---
    public void tampilMenu() {
        System.out.print(id + " | " + nama + " [" + kategori + "] | Rp" + harga);
    }

    // =====================================================
    // ABSTRACT METHOD - Wajib diimplementasikan subclass
    // =====================================================
    public abstract String getKeterangan();     // Subclass wajib jelaskan keterangan khusus
    public abstract String getKategori();       // Subclass wajib return kategorinya
}