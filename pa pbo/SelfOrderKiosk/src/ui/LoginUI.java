package ui;

import model.Kitchen;
import model.Manager;
import model.User;
import service.AuthService;
import util.DisplayHelper;
import util.InputHelper;

public class LoginUI {

    private AuthService authService;

    public LoginUI(AuthService authService) {
        this.authService = authService;
    }

    // Tampilkan halaman login langsung tanpa pilihan role
    public void tampilkan() {
        DisplayHelper.tampilkanHeader("LOGIN STAFF");

        System.out.println("  Akun demo:");
        System.out.println("  Kitchen  -> username: dapur1   | password: kitchen123");
        System.out.println("  Manager  -> username: manager  | password: manager123");
        System.out.println("  ----------------------------------------\n");

        int percobaan    = 0;
        int maxPercobaan = 3;

        while (percobaan < maxPercobaan) {
            String username = InputHelper.bacaString("  Username : ");
            String password = InputHelper.bacaString("  Password : ");

            User user = authService.login(username, password);

            if (user != null) {
                System.out.println("\n  Login berhasil! Selamat datang, "
                        + user.getNamaLengkap() + "!");
                System.out.println("  Role: " + user.getRole());
                InputHelper.tungguEnter();

                // Arahkan ke dashboard sesuai role yang terdeteksi otomatis
                if (user instanceof Kitchen) {
                    KitchenUI kitchenUI = new KitchenUI((Kitchen) user);
                    kitchenUI.tampilkanDashboard();
                } else if (user instanceof Manager) {
                    ManagerUI managerUI = new ManagerUI((Manager) user);
                    managerUI.tampilkanDashboard();
                }

                authService.logout();
                return;

            } else {
                percobaan++;
                int sisa = maxPercobaan - percobaan;
                System.out.println("\n  Username atau password salah!");
                if (sisa > 0) {
                    System.out.println("  Sisa percobaan: " + sisa);
                    System.out.println();
                } else {
                    System.out.println("  Terlalu banyak percobaan. Akses ditolak.");
                }
            }
        }

        InputHelper.tungguEnter();
    }
}