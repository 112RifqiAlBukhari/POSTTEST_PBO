package util;

import java.util.Scanner;

/**
 * Class InputHelper menyediakan utilitas untuk membaca input dari user.
 * Menggunakan pola Singleton untuk Scanner.
 */
public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);

    // Baca input integer dengan validasi
    public static int bacaInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠️  Input tidak valid! Masukkan angka.");
            }
        }
    }

    // Baca input integer dengan range validasi
    public static int bacaInt(String prompt, int min, int max) {
        while (true) {
            int nilai = bacaInt(prompt);
            if (nilai >= min && nilai <= max) {
                return nilai;
            }
            System.out.printf("  ⚠️  Masukkan angka antara %d - %d!%n", min, max);
        }
    }

    // Baca input double
    public static double bacaDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                double nilai = Double.parseDouble(input);
                if (nilai < 0) {
                    System.out.println("  ⚠️  Nilai tidak boleh negatif!");
                    continue;
                }
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("  ⚠️  Input tidak valid! Masukkan angka.");
            }
        }
    }

    // Baca input string (tidak boleh kosong)
    public static String bacaString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("  ⚠️  Input tidak boleh kosong!");
        }
    }

    // Baca input string (boleh kosong)
    public static String bacaStringOpsional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Baca konfirmasi Y/N
    public static boolean bacaKonfirmasi(String prompt) {
        while (true) {
            if (!prompt.isEmpty()) {
                System.out.print(prompt + " (Y/N): ");
            } else {
                System.out.print("  Konfirmasi (Y/N): ");
            }
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("YES")) return true;
            if (input.equals("N") || input.equals("NO")) return false;
            System.out.println("  Masukkan Y atau N!");
        }
    }

    // Tunggu enter
    public static void tungguEnter() {
        System.out.print("\n  Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }

    // Tutup scanner
    public static void tutupScanner() {
        scanner.close();
    }
}
