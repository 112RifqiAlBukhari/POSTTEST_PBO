package kiosk.app;

import kiosk.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<pesanan> daftarPesanan = new ArrayList<>();
        ArrayList<menuMakanan> listMenu = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        // --- Inisialisasi Data Master Menu ---
        // Menggunakan Polymorphism: Subclass masuk ke list Superclass
        listMenu.add(new Makanan(1, "Fire Chicken", 35000, 5));
        listMenu.add(new Minuman(2, "Pink Lava", 15000, "Dingin"));
        listMenu.add(new Makanan(3, "Flying Chicken", 30000, 2));

        int pilihan;
        do {
            System.out.println("\n--- KIOSK RICHEESE (POSTTEST 3) ---");
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Lihat Semua Pesanan ");
            System.out.println("3. Cari Menu ");
            System.out.println("4. Keluar");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Nama Pelanggan: ");
                    String nama = input.nextLine();

                    System.out.println("\n=== DAFTAR MENU ===");
                    for (menuMakanan m : listMenu) {
                        m.tampilMenu();
                    }

                    System.out.print("Pilih ID Menu: ");
                    int idMenu = input.nextInt();
                    System.out.print("Jumlah: ");
                    int qty = input.nextInt();


                    for (menuMakanan m : listMenu) {
                        if (m.cariMenu(idMenu)) {
                            daftarPesanan.add(new pesanan(nama, m, qty));
                            System.out.println("✅ Pesanan ditambahkan!");
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n=== DATA PESANAN ===");
                    if (daftarPesanan.isEmpty()) System.out.println("Belum ada pesanan.");
                    for (pesanan p : daftarPesanan) {
                        p.tampilPesanan();
                        System.out.println("--------------------");
                    }
                    break;

                case 3:
                    System.out.print("Masukkan Nama Menu yang dicari: ");
                    String search = input.nextLine();
                    System.out.println("Hasil Pencarian:");
                    for (menuMakanan m : listMenu) {
                        if (m.cariMenu(search)) {
                            m.tampilMenu();
                        }
                    }
                    break;
            }
        } while (pilihan != 4);
    }
}