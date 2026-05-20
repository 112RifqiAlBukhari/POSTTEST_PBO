import service.AuthService;
import ui.LoginUI;
import ui.PelangganUI;
import util.DisplayHelper;
import util.InputHelper;

/**
 * KioskApp - Main Entry Point
 * Sistem Self Order Kiosk
 * Project Akhir - Pemrograman Berorientasi Objek
 *
 * Dikerjakan oleh: [NAMA KAMU]
 */
public class KioskApp {

    public static void main(String[] args) {
        AuthService authService = new AuthService();

        boolean running = true;
        while (running) {
            tampilkanHalamanUtama();

            int pilihan = InputHelper.bacaInt("  Pilihan Anda: ");

            switch (pilihan) {
                case 1 -> {
                    PelangganUI pelangganUI = new PelangganUI();
                    pelangganUI.mulai();
                }
                case 2 -> {
                    LoginUI loginUI = new LoginUI(authService);
                    loginUI.tampilkan();
                }
                case 0 -> {
                    if (InputHelper.bacaKonfirmasi("\n  Yakin ingin keluar?")) {
                        running = false;
                    }
                }
                default -> {
                    System.out.println("\n  Pilihan tidak tersedia, coba lagi.");
                    InputHelper.tungguEnter();
                }
            }
        }

        System.out.println("\n  Terima kasih telah menggunakan Self Order Kiosk!");
        System.out.println("  Sampai jumpa!\n");
        InputHelper.tutupScanner();
    }


    // Tampilan halaman utama kiosk
    private static void tampilkanHalamanUtama() {
        DisplayHelper.tampilkanHeaderKiosk();
        System.out.println();
        System.out.println("  +==========================================+");
        System.out.println("  |           SELAMAT DATANG!                |");
        System.out.println("  |  Silakan pilih salah satu opsi berikut   |");
        System.out.println("  +==========================================+");
        System.out.println("  |                                          |");
        System.out.println("  |   [1]  PESAN SEKARANG                    |");
        System.out.println("  |        Lihat menu & buat pesanan         |");
        System.out.println("  |                                          |");
        System.out.println("  |   [2]  LOGIN STAFF                       |");
        System.out.println("  |        Kitchen / Manager                 |");
        System.out.println("  |                                          |");
        System.out.println("  |   [0]  KELUAR                            |");
        System.out.println("  |                                          |");
        System.out.println("  +==========================================+");
        System.out.println();
    }


}
