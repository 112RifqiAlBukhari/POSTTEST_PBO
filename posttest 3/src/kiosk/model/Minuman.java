package kiosk.model;

public class Minuman extends menuMakanan {

    private String suhu;

    public Minuman(int id, String nama, int harga, String kategori, String suhu) {
        super(id, nama, harga, kategori); // ambil dari parent
        this.suhu = suhu;
    }

    public String getSuhu() {
        return suhu;
    }
}