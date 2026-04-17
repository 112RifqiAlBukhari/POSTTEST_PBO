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
        // Polymorphism: Subclass masuk ke list Abstract Superclass
        listMenu.add(new Makanan(1, "Fire Chicken", 35000, 5));
        listMenu.add(new Minuman(2, "Pink Lava", 15000, "Dingin"));
        listMenu.add(new Makanan(3, "Flying Chicken", 30000, 2));

        int pilihan;
        do {
            System.out.println("\n--- KIOSK RICHEESE (POSTTEST 4) ---");
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Lihat Semua Pesanan");
            System.out.println("3. Cari Menu");
            System.out.println("4. Cetak Struk Pesanan");   // Fitur baru pakai ITransaksi
            System.out.println("5. Keluar");
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
                        // Menampilkan keterangan dari abstract method
                        System.out.println("   -> Keterangan: " + m.getKeterangan());
                    }

                    System.out.print("Pilih ID Menu: ");
                    int idMenu = input.nextInt();
                    System.out.print("Jumlah: ");
                    int qty = input.nextInt();

                    boolean ditemukan = false;
                    for (menuMakanan m : listMenu) {
                        if (m.cariMenu(idMenu)) {
                            pesanan p = new pesanan(nama, m, qty);
                            // Validasi transaksi sebelum menambah (pakai interface ITransaksi)
                            if (p.isTransaksiValid()) {
                                daftarPesanan.add(p);
                                System.out.println("Pesanan ditambahkan! Total: Rp" + p.hitungTotal());
                            } else {
                                System.out.println("Transaksi tidak valid! Periksa jumlah pesanan.");
                            }
                            ditemukan = true;
                        }
                    }
                    if (!ditemukan) System.out.println("Menu dengan ID tersebut tidak ditemukan.");
                    break;

                case 2:
                    System.out.println("\n=== DATA PESANAN ===");
                    if (daftarPesanan.isEmpty()) {
                        System.out.println("Belum ada pesanan.");
                    }
                    for (pesanan p : daftarPesanan) {
                        p.tampilPesanan();
                        System.out.println("--------------------");
                    }
                    break;

                case 3:
                    System.out.print("Masukkan Nama Menu yang dicari: ");
                    String search = input.nextLine();
                    System.out.println("Hasil Pencarian:");
                    boolean ada = false;
                    for (menuMakanan m : listMenu) {
                        if (m.cariMenu(search)) {
                            m.tampilMenu();
                            System.out.println("   Kategori  : " + m.getKategori());
                            System.out.println("   Keterangan: " + m.getKeterangan());
                            ada = true;
                        }
                    }
                    if (!ada) System.out.println("Menu tidak ditemukan.");
                    break;

                case 4:
                    // Cetak struk menggunakan method dari Interface ITransaksi
                    System.out.println("\n=== CETAK STRUK PESANAN ===");
                    if (daftarPesanan.isEmpty()) {
                        System.out.println("Belum ada pesanan.");
                    } else {
                        for (pesanan p : daftarPesanan) {
                            p.tampilStruk();   // method dari ITransaksi
                            System.out.println();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Terima kasih! Sampai jumpa.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);

        input.close();
    }
}