package model;

/**
 * Class Manager merepresentasikan manajer restoran.
 * Mewarisi class User (Inheritance).
 * Memiliki akses lebih luas dibanding Kitchen.
 */
public class Manager extends User {

    public Manager(String username, String password, String namaLengkap) {
        super(username, password, namaLengkap, "MANAGER");
    }

    @Override
    public String getWelcomeMessage() {
        return "Selamat datang di Manager Dashboard, " + namaLengkap + "!";
    }

    @Override
    public void tampilkanMenu() {
        System.out.println("+---------------------------------------+");
        System.out.println("|         MENU MANAGER                  |");
        System.out.println("+---------------------------------------+");
        System.out.println("| [1] Lihat Semua Menu                  |");
        System.out.println("| [2] Tambah Menu Baru                  |");
        System.out.println("| [3] Edit Harga Menu                   |");
        System.out.println("| [4] Hapus Menu                        |");
        System.out.println("| [0] Logout                            |");
        System.out.println("+---------------------------------------+");
    }
}
