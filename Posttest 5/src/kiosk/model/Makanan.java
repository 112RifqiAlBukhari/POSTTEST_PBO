package kiosk.model;

public class Makanan extends menuMakanan {
    private int levelPedas;

    public Makanan(int id, String nama, int harga, int levelPedas) {
        super(id, nama, harga, "Makanan");
        this.levelPedas = levelPedas;
    }

    // --- ENCAPSULATION ---
    public int getLevelPedas() {
        return levelPedas;
    }

    // --- POLYMORPHISM: OVERRIDE tampilMenu ---
    @Override
    public void tampilMenu() {
        super.tampilMenu();
        System.out.println(" | Pedas: Lvl " + levelPedas);
    }


    @Override
    public String getKeterangan() {
        return "Level Pedas: " + levelPedas + "/10";
    }

    @Override
    public String getKategori() {
        return "Makanan";
    }
}