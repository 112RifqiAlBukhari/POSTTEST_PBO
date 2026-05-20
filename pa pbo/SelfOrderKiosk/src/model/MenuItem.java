package model;

public class MenuItem {

    private int id;
    private String nama;
    private String kategori;
    private double harga;
    private String deskripsi;
    private boolean tersedia;

    // Constructor
    public MenuItem(int id,
                    String nama,
                    String kategori,
                    double harga,
                    String deskripsi) {

        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.tersedia = true;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public double getHarga() {
        return harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    // Setter
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    @Override
    public String toString() {

        return id + ". "
                + nama
                + " | "
                + kategori
                + " | Rp "
                + harga;
    }
}