package kiosk.app;

import kiosk.model.pesanan;
import kiosk.model.menuMakanan;
import kiosk.model.Makanan;
import kiosk.model.Minuman;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<pesanan> daftarPesanan = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int pilihan;

        // Inisialisasi Menu menggunakan Class menuMakanan (Poin Plus)
        Makanan m1 = new Makanan(1, "Combo 1 Fire Chicken", 35000, "Makanan", 0);
        Minuman d1 = new Minuman(5, "Pink Lava", 15000, "Minuman", "Normal");

        do {
            System.out.println("\n--- KIOSK SELF-SERVICE ---");
            System.out.println("1. Tambah Pesanan\n2. Lihat Pesanan\n3. Edit Pesanan\n4. Hapus Pesanan\n5. Keluar");
            System.out.print("Pilih Menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("\nNama Pelanggan: ");
                    String nama = input.nextLine();

                    // --- LOGIKA MAKANAN ---
                    System.out.println("\n--- PILIH MAKANAN ---");
                    System.out.println("1. " + m1.nama + "\n2. Fire Flying Chicken\n3. Fire Burger");
                    System.out.print("Pilihan: ");
                    int pilMakan = input.nextInt();
                    String makanFix = (pilMakan == 1) ? m1.nama : (pilMakan == 2) ? "Fire Flying Chicken" : "Fire Burger";

                    System.out.print("Level Pedas [0-5]: ");
                    int level = input.nextInt();

                    Makanan makananDipilih = new Makanan(1, makanFix, 0, "Makanan", level);
                    String detMakan = "Level " + makananDipilih.getLevelPedas();
                    System.out.print("Jumlah Makanan: ");
                    int qtyMakan = input.nextInt();

                    daftarPesanan.add(new pesanan(nama, makanFix, qtyMakan, detMakan));

                    // --- LOGIKA MINUMAN (KEMBALI DITAMBAHKAN) ---
                    System.out.print("\nTambah minuman? (1. Ya / 2. Tidak): ");
                    if (input.nextInt() == 1) {
                        System.out.println("\n--- PILIH MINUMAN ---");
                        System.out.println("1. " + d1.nama + "\n2. Fruitarian Tea\n3. Amo Soda");
                        System.out.print("Pilihan: ");
                        int pilMinum = input.nextInt();
                        String minumFix = (pilMinum == 1) ? d1.nama : (pilMinum == 2) ? "Fruitarian Tea" : "Amo Soda";

                        System.out.print("Pake Es? (1.Ya / 2.Tidak): ");
                        String suhu = (input.nextInt() == 1) ? "Dingin" : "Normal";

                        Minuman minumanDipilih = new Minuman(5, minumFix, 0, "Minuman", suhu);
                        String detMinum = minumanDipilih.getSuhu();
                        System.out.print("Jumlah Minuman: ");
                        int qtyMinum = input.nextInt();

                        daftarPesanan.add(new pesanan(nama, minumFix, qtyMinum, detMinum));
                    }
                    System.out.println("✅ Pesanan berhasil dicatat!");
                    break;

                case 2:
                    System.out.println("\n=== DAFTAR PESANAN ===");
                    if (daftarPesanan.isEmpty()) System.out.println("Kosong.");
                    else {
                        for (int i = 0; i < daftarPesanan.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            daftarPesanan.get(i).tampilPesanan();
                            System.out.println("---------------------------");
                        }
                    }
                    break;

                case 3: // Update yang lebih aman
                    System.out.print("Nama Pelanggan yang ingin diubah jumlahnya: ");
                    String cari = input.next();
                    boolean found = false;
                    for (pesanan p : daftarPesanan) {
                        if (p.getNamaPelanggan().equalsIgnoreCase(cari)) {
                            System.out.println("Mengubah pesanan: " + p.getMenu());
                            System.out.print("Masukkan Jumlah Baru: ");
                            p.setJumlah(input.nextInt());
                            found = true;
                        }
                    }
                    if(!found) System.out.println("❌ Data tidak ditemukan.");
                    break;

                case 4:
                    System.out.print("Nomor urut hapus: ");
                    int idx = input.nextInt();
                    if (idx > 0 && idx <= daftarPesanan.size()) {
                        daftarPesanan.remove(idx - 1);
                        System.out.println("🗑️ Dihapus.");
                    }
                    break;
            }
        } while (pilihan != 5);
    }
}