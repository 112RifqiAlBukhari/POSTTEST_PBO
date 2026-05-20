package model;

/**
 * Class Pelanggan merepresentasikan pelanggan yang melakukan pemesanan.
 * Berbeda dengan User, Pelanggan tidak memerlukan login.
 */
public class Pelanggan {
    private static int idCounter = 1;

    private int id;
    private String nama;
    private String nomorMeja;
    private Pesanan pesananAktif;

    // Constructor
    public Pelanggan(String nama, String nomorMeja) {
        this.id = idCounter++;
        this.nama = nama;
        this.nomorMeja = nomorMeja;
        this.pesananAktif = new Pesanan(nama, nomorMeja);
    }

    // Getters
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNomorMeja() { return nomorMeja; }
    public Pesanan getPesananAktif() { return pesananAktif; }

    // Setters
    public void setNama(String nama) { this.nama = nama; }
    public void setNomorMeja(String nomorMeja) { this.nomorMeja = nomorMeja; }

    // Buat pesanan baru (reset pesanan aktif)
    public void resetPesanan() {
        this.pesananAktif = new Pesanan(nama, nomorMeja);
    }

    @Override
    public String toString() {
        return String.format("Pelanggan: %s | Meja: %s", nama, nomorMeja);
    }
}
