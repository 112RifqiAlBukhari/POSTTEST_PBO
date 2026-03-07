package kiosk;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<pesanan> daftarPesanan = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n--- KIOSK SELF-SERVICE ---");
            System.out.println("1. Tambah Pesanan (Create)");
            System.out.println("2. Lihat Semua Pesanan (Read)");
            System.out.println("3. Edit Jumlah Pesanan (Update)");
            System.out.println("4. Batalkan Pesanan (Delete)");
            System.out.println("5. Keluar");
            System.out.print("Pilih Menu [1-5]: ");
            pilihan = input.nextInt();
            input.nextLine();// mrnghilangkan buffer

            switch (pilihan) {
                case 1: // CREATE
                    System.out.print("\nNama Pelanggan: ");
                    String nama = input.nextLine();

                    // --- BAGIAN MAKANAN ---
                    System.out.println("\n--- PILIH MAKANAN ---");
                    System.out.println("1. Combo 1 Fire Chicken\n2. Fire Flying Chicken\n3. Fire Burger\n4. Richicken");
                    System.out.print("Pilih Makanan [1-4]: ");
                    int pilMakan = input.nextInt();

                    String makanFix = (pilMakan == 1) ? "Combo 1 Fire Chicken" : (pilMakan == 2) ? "Fire Flying Chicken" : (pilMakan == 3) ? "Fire Burger" : "Richicken";
                    String detMakan = "";
                    if (pilMakan <= 3) {
                        System.out.print("Level Pedas [0-5]: ");
                        detMakan = "Level " + input.nextInt();
                    } else {
                        detMakan = "Original";
                    }

                    System.out.print("Jumlah Makanan: ");
                    int qtyMakan = input.nextInt();
                    daftarPesanan.add(new pesanan(nama, makanFix, qtyMakan, detMakan));

                    // --- BAGIAN MINUMAN  ---
                    System.out.print("\nApakah ingin menambah minuman? (1. Ya / 2. Tidak): ");
                    int mauMinum = input.nextInt();

                    if (mauMinum == 1) {
                        System.out.println("\n--- PILIH MINUMAN ---");
                        System.out.println("1. Pink Lava\n2. Fruitarian Tea\n3. Amo Soda");
                        System.out.print("Pilih Minuman [1-3]: ");
                        int pilMinum = input.nextInt();
                        String minumFix = (pilMinum == 1) ? "Pink Lava" : (pilMinum == 2) ? "Fruitarian Tea" : "Amo Soda";

                        System.out.print("Pake Es? (1.Ya / 2.Tidak): ");
                        String detMinum = (input.nextInt() == 1) ? "Dingin" : "Normal";

                        System.out.print("Jumlah Minuman: ");
                        int qtyMinum = input.nextInt();
                        daftarPesanan.add(new pesanan(nama, minumFix, qtyMinum, detMinum));
                    }

                    input.nextLine();
                    System.out.println("\n✅ Pesanan atas nama " + nama + " berhasil disimpan!");
                    break;
                case 2: // READ
                    System.out.println("\n=== DAFTAR PESANAN SAAT INI ===");
                    if (daftarPesanan.isEmpty()) {
                        System.out.println("Belum ada pesanan.");
                    } else {
                        for (int i = 0; i < daftarPesanan.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            daftarPesanan.get(i).tampilPesanan();
                            System.out.println("---------------------------");
                        }
                    }
                    break;
                case 3: // UPDATE
                    System.out.print("Masukkan Nama Pelanggan yang ingin diedit: ");
                    String cariNama = input.nextLine();

                    ArrayList<pesanan> milikPelanggan = new ArrayList<>();
                    for (pesanan p : daftarPesanan) {
                        if (p.getNamaPelanggan().equalsIgnoreCase(cariNama)) {
                            milikPelanggan.add(p);
                        }
                    }

                    if (milikPelanggan.isEmpty()) {
                        System.out.println("Maaf, pesanan atas nama '" + cariNama + "' tidak ditemukan.");
                    } else {
                        System.out.println("\nData ditemukan! Apa yang ingin Anda edit?");
                        System.out.println("1. Edit Makanan");
                        System.out.println("2. Edit Minuman (Jika ada)");
                        System.out.print("Pilih [1-2]: ");
                        int pilihanEdit = input.nextInt();
                        input.nextLine();

                        boolean berhasilEdit = false;
                        for (pesanan p : milikPelanggan) {
                            if (pilihanEdit == 1 && (p.getMenu().contains("Chicken") || p.getMenu().contains("Burger") || p.getMenu().contains("Richicken"))) {
                                System.out.println("\n--- DAFTAR MAKANAN BARU ---");
                                System.out.println("1. Combo 1 Fire Chicken\n2. Fire Flying Chicken\n3. Fire Burger\n4. Richicken");
                                System.out.print("Pilih Makanan Baru [1-4]: ");
                                int pilBaru = input.nextInt();

                                String mBaru = (pilBaru == 1) ? "Combo 1 Fire Chicken" : (pilBaru == 2) ? "Fire Flying Chicken" : (pilBaru == 3) ? "Fire Burger" : "Richicken";
                                p.setMenu(mBaru);

                                if (pilBaru <= 3) {
                                    System.out.print("Level Pedas Baru [0-5]: ");
                                    p.setPilihanTambahan("Level " + input.nextInt());
                                } else {
                                    p.setPilihanTambahan("Original");
                                }

                                System.out.print("Jumlah Baru: ");
                                p.setJumlah(input.nextInt());
                                berhasilEdit = true;
                            }
                            else if (pilihanEdit == 2 && (p.getMenu().contains("Pink") || p.getMenu().contains("Tea") || p.getMenu().contains("Soda"))) {
                                System.out.println("\n--- DAFTAR MINUMAN BARU ---");
                                System.out.println("1. Pink Lava\n2. Fruitarian Tea\n3. Amo Soda");
                                System.out.print("Pilih Minuman Baru [1-3]: ");
                                int pilMinum = input.nextInt();

                                String miBaru = (pilMinum == 1) ? "Pink Lava" : (pilMinum == 2) ? "Fruitarian Tea" : "Amo Soda";
                                p.setMenu(miBaru);

                                System.out.print("Pake Es? (1.Ya / 2.Tidak): ");
                                p.setPilihanTambahan((input.nextInt() == 1) ? "Dingin" : "Normal");

                                System.out.print("Jumlah Baru: ");
                                p.setJumlah(input.nextInt());
                                berhasilEdit = true;
                            }
                        }

                        if (berhasilEdit) {
                            input.nextLine();
                            System.out.println("✅ Data berhasil diperbarui!");
                        } else {
                            System.out.println("❌ Gagal. Pelanggan ini tidak memiliki kategori tersebut di pesanannya.");
                        }
                    }
                    break;

                case 4:
                    System.out.print("Nomor urut pesanan yang ingin dihapus: ");
                    int index = input.nextInt();
                    if (index > 0 && index <= daftarPesanan.size()) {
                        daftarPesanan.remove(index - 1);
                        System.out.println("Pesanan berhasil dihapus.");
                    } else {
                        System.out.println("Nomor tidak valid.");
                    }
                    break;
            }
        } while (pilihan != 5);

        System.out.println("Program Selesai. Sampai jumpa!");
    }
}