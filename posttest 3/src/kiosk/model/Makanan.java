package kiosk.model;

public class Makanan extends menuMakanan {

    private int levelPedas;

    public Makanan(int id, String nama, int harga, String kategori, int levelPedas) {
        super(id, nama, harga, kategori); // ambil dari parent
        this.levelPedas = levelPedas;
    }

    public int getLevelPedas() {
        return levelPedas;
    }
}