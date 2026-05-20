package util;

import model.MenuItem;
import model.OrderItem;
import model.Pesanan;
import model.StatusPesanan;
import java.util.List;

/**
 * Class DisplayHelper menyediakan utilitas untuk menampilkan UI di console.
 * Berisi method-method statis untuk formatting tampilan.
 */
public class DisplayHelper {

    private static final int LEBAR_LAYAR = 55;

    // Tampilkan header kiosk
    public static void tampilkanHeaderKiosk() {
        bersihkanLayar();
        System.out.println("╔" + "═".repeat(LEBAR_LAYAR - 2) + "╗");
        System.out.println("║" + center("🍽  SELF ORDER KIOSK  🍽", LEBAR_LAYAR - 2) + "║");
        System.out.println("║" + center("Restoran Nusantara Digital", LEBAR_LAYAR - 2) + "║");
        System.out.println("╚" + "═".repeat(LEBAR_LAYAR - 2) + "╝");
    }

    // Tampilkan header dengan judul
    public static void tampilkanHeader(String judul) {
        System.out.println("\n" + "═".repeat(LEBAR_LAYAR));
        System.out.println(center("  " + judul + "  ", LEBAR_LAYAR));
        System.out.println("═".repeat(LEBAR_LAYAR));
    }

    // Tampilkan daftar menu dengan format rapi
    public static void tampilkanDaftarMenu(List<MenuItem> menuList) {
        if (menuList.isEmpty()) {
            System.out.println("  [Tidak ada menu]");
            return;
        }

        System.out.printf("  %-4s %-22s %-8s %10s%n", "ID", "Nama Menu", "Kategori", "Harga");
        System.out.println("  " + "-".repeat(LEBAR_LAYAR - 4));

        for (MenuItem item : menuList) {
            String status = item.isTersedia() ? "" : " [HABIS]";
            System.out.printf("  %-4d %-22s %-8s Rp %,.0f%s%n",
                    item.getId(), item.getNama(), item.getKategori(),
                    item.getHarga(), status);
        }
    }

    // Tampilkan menu per kategori
    public static void tampilkanMenuPerKategori(String kategori, List<MenuItem> menuList) {
        System.out.println("\n  ┌─── " + kategori.toUpperCase() + " ───");
        for (MenuItem item : menuList) {
            if (item.isTersedia()) {
                System.out.printf("  │ [%2d] %-22s Rp %,.0f%n",
                        item.getId(), item.getNama(), item.getHarga());
                System.out.printf("  │      %s%n", item.getDeskripsi());
            }
        }
        System.out.println("  └" + "─".repeat(LEBAR_LAYAR - 5));
    }

    // Tampilkan ringkasan pesanan
    public static void tampilkanRingkasanPesanan(Pesanan pesanan) {
        System.out.println("\n  📋 PESANAN ANDA:");
        System.out.println("  " + "-".repeat(LEBAR_LAYAR - 4));
        System.out.printf("  %-4s %-22s %4s %12s%n", "No", "Item", "Qty", "Subtotal");
        System.out.println("  " + "-".repeat(LEBAR_LAYAR - 4));

        List<OrderItem> items = pesanan.getItems();
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
            System.out.printf("  %-4d %-22s  x%-2d Rp %,.0f%n",
                    (i + 1), item.getMenuItem().getNama(),
                    item.getJumlah(), item.getSubtotal());
        }

        System.out.println("  " + "-".repeat(LEBAR_LAYAR - 4));
        System.out.printf("  %-30s Rp %,.0f%n", "Subtotal:", pesanan.getTotalHarga());
        System.out.printf("  %-30s Rp %,.0f%n", "Pajak (10%):", pesanan.getPajak());
        System.out.println("  " + "─".repeat(LEBAR_LAYAR - 4));
        System.out.printf("  %-30s Rp %,.0f%n", "TOTAL:", pesanan.getGrandTotal());
        System.out.println("  " + "─".repeat(LEBAR_LAYAR - 4));
    }

    // Tampilkan semua pesanan
    public static void tampilkanSemuaPesanan(List<Pesanan> pesananList) {
        if (pesananList.isEmpty()) {
            System.out.println("  [Tidak ada pesanan]");
            return;
        }

        System.out.printf("  %-6s %-15s %-6s %-25s %12s%n",
                "No", "Pelanggan", "Meja", "Status", "Total");
        System.out.println("  " + "-".repeat(LEBAR_LAYAR + 5));

        for (Pesanan p : pesananList) {
            System.out.printf("  #%-5d %-15s %-6s %-25s Rp%,.0f%n",
                    p.getNomorPesanan(),
                    truncate(p.getNamaPelanggan(), 14),
                    p.getNomorMeja(),
                    p.getStatus().getLabel(),
                    p.getGrandTotal());
        }
    }

    // Tampilkan detail pesanan
    public static void tampilkanDetailPesanan(Pesanan pesanan) {
        System.out.println("\n  ┌─ DETAIL PESANAN #" + String.format("%03d", pesanan.getNomorPesanan()) + " ─────");
        System.out.println("  │ Pelanggan : " + pesanan.getNamaPelanggan());
        System.out.println("  │ Meja      : " + pesanan.getNomorMeja());
        System.out.println("  │ Waktu     : " + pesanan.getWaktuPesanFormatted());
        System.out.println("  │ Status    : " + pesanan.getStatus());
        System.out.println("  │ Diupdate  : " + pesanan.getWaktuUpdateFormatted());
        System.out.println("  ├─ ITEM ─────────────────────────────────");

        for (OrderItem item : pesanan.getItems()) {
            System.out.printf("  │ • %-22s x%d = Rp %,.0f%n",
                    item.getMenuItem().getNama(), item.getJumlah(), item.getSubtotal());
            if (!item.getCatatan().isEmpty()) {
                System.out.println("  │    > " + item.getCatatan());
            }
        }

        System.out.println("  ├─────────────────────────────────────────");
        System.out.printf("  │ Subtotal  : Rp %,.0f%n", pesanan.getTotalHarga());
        System.out.printf("  │ Pajak 10%% : Rp %,.0f%n", pesanan.getPajak());
        System.out.printf("  │ TOTAL     : Rp %,.0f%n", pesanan.getGrandTotal());
        System.out.println("  └─────────────────────────────────────────");
    }

    // Tampilkan pilihan status
    public static void tampilkanPilihanStatus() {
        System.out.println("\n  Pilih Status Baru:");
        StatusPesanan[] statuses = StatusPesanan.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.printf("  [%d] %s%n", (i + 1), statuses[i]);
        }
    }

    // Tampilkan pesan sukses
    public static void pesanSukses(String pesan) {
        System.out.println("\n  ✅ " + pesan);
    }

    // Tampilkan pesan error
    public static void pesanError(String pesan) {
        System.out.println("\n  ❌ ERROR: " + pesan);
    }

    // Tampilkan pesan info
    public static void pesanInfo(String pesan) {
        System.out.println("\n  ℹ️  " + pesan);
    }

    // Tampilkan pesan peringatan
    public static void pesanWarning(String pesan) {
        System.out.println("\n  ⚠️  " + pesan);
    }

    // Bersihkan layar (simulasi)
    public static void bersihkanLayar() {
        System.out.println("\n\n");
    }

    // Tekan enter untuk lanjut
    public static void pressEnterToContinue() {
        System.out.println("\n  Tekan Enter untuk melanjutkan...");
    }

    // Helper: center text
    private static String center(String text, int width) {
        if (text.length() >= width) return text;
        int pad = (width - text.length()) / 2;
        return " ".repeat(pad) + text + " ".repeat(width - text.length() - pad);
    }

    // Helper: truncate text
    private static String truncate(String text, int maxLen) {
        if (text.length() <= maxLen) return text;
        return text.substring(0, maxLen - 2) + "..";
    }

    // Tampilkan garis pemisah
    public static void garisHorizontal() {
        System.out.println("  " + "─".repeat(LEBAR_LAYAR - 4));
    }

    // Tampilkan statistik pesanan untuk manager
    public static void tampilkanStatistikPesanan(
            int totalPesanan, int menunggu, int diproses, int siap, int selesai, int dibatalkan,
            double totalPendapatan) {
        System.out.println("\n  📊 STATISTIK PESANAN:");
        System.out.println("  " + "─".repeat(40));
        System.out.printf("  %-25s : %d%n", "Total Pesanan", totalPesanan);
        System.out.printf("  %-25s : %d%n", "⏳ Menunggu", menunggu);
        System.out.printf("  %-25s : %d%n", "🔄 Diproses", diproses);
        System.out.printf("  %-25s : %d%n", "✅ Siap Diambil", siap);
        System.out.printf("  %-25s : %d%n", "🎉 Selesai", selesai);
        System.out.printf("  %-25s : %d%n", "❌ Dibatalkan", dibatalkan);
        System.out.println("  " + "─".repeat(40));
        System.out.printf("  %-25s : Rp %,.0f%n", "💰 Total Pendapatan", totalPendapatan);
        System.out.println("  " + "─".repeat(40));
    }
}
