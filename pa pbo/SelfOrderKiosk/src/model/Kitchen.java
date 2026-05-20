package model;

/**
 * Class Kitchen merepresentasikan staff dapur.
 * Mewarisi class User (Inheritance).
 */
public class Kitchen extends User {

    public Kitchen(String username, String password, String namaLengkap) {
        super(username, password, namaLengkap, "KITCHEN");
    }

    @Override
    public String getWelcomeMessage() {
        return "Selamat datang di Kitchen Dashboard, " + namaLengkap + "!";
    }

    @Override
    public void tampilkanMenu() {
        System.out.println("+---------------------------------------+");
        System.out.println("|         MENU KITCHEN STAFF            |");
        System.out.println("+---------------------------------------+");
        System.out.println("| [1] Lihat Semua Pesanan               |");
        System.out.println("| [2] Update Status Pesanan             |");
        System.out.println("| [3] Riwayat Pesanan Selesai           |");
        System.out.println("| [0] Logout                            |");
        System.out.println("+---------------------------------------+");
    }
}
