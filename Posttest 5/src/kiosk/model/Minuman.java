package kiosk.model;

public class Minuman extends menuMakanan {
    private String suhu;

    public Minuman(int id, String nama, int harga, String suhu) {
        super(id, nama, harga, "Minuman");
        this.suhu = suhu;
    }

    // --- ENCAPSULATION ---
    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    // --- POLYMORPHISM: OVERRIDE tampilMenu ---
    @Override
    public void tampilMenu() {
        super.tampilMenu();
        System.out.println(" | Suhu: " + suhu);
    }


    @Override
    public String getKeterangan() {
        return "Suhu Minuman: " + suhu;
    }

    @Override
    public String getKategori() {
        return "Minuman";
    }
}