package service;

import model.OrderItem;
import model.Pesanan;

/**
 * Class NotaService bertanggung jawab untuk mencetak nota/struk pesanan.
 * Mengimplementasikan prinsip Single Responsibility.
 */
public class NotaService {
    private static final String NAMA_RESTORAN = "SELF ORDER KIOSK";
    private static final String ALAMAT = "Jl. Kuliner No. 88, Kota Makanan";
    private static final String TELEPON = "(0541) 123-4567";
    private static final int LEBAR_NOTA = 45;

    // Cetak nota ke console
    public void cetakNota(Pesanan pesanan) {
        System.out.println();
        cetakGarisDua();
        cetakTengah(NAMA_RESTORAN);
        cetakTengah(ALAMAT);
        cetakTengah(TELEPON);
        cetakGarisDua();

        System.out.println(padRight("No. Pesanan: #" + String.format("%03d", pesanan.getNomorPesanan()), LEBAR_NOTA));
        System.out.println(padRight("Pelanggan  : " + pesanan.getNamaPelanggan(), LEBAR_NOTA));
        System.out.println(padRight("No. Meja   : " + pesanan.getNomorMeja(), LEBAR_NOTA));
        System.out.println(padRight("Waktu      : " + pesanan.getWaktuPesanFormatted(), LEBAR_NOTA));

        if (!pesanan.getCatatanKhusus().isEmpty()) {
            System.out.println(padRight("Catatan    : " + pesanan.getCatatanKhusus(), LEBAR_NOTA));
        }

        cetakGarisSatu();
        System.out.printf("%-22s %4s  %10s%n", "Item", "Qty", "Subtotal");
        cetakGarisSatu();

        for (OrderItem item : pesanan.getItems()) {
            String namaItem = item.getMenuItem().getNama();
            // Potong nama jika terlalu panjang
            if (namaItem.length() > 22) {
                namaItem = namaItem.substring(0, 19) + "...";
            }
            System.out.printf("%-22s  x%-2d  Rp%,8.0f%n",
                    namaItem, item.getJumlah(), item.getSubtotal());

            if (!item.getCatatan().isEmpty()) {
                System.out.println("  * " + item.getCatatan());
            }
        }

        cetakGarisSatu();

        // Hitung dan tampilkan total
        double subtotal = pesanan.getTotalHarga();
        double pajak = pesanan.getPajak();
        double grandTotal = pesanan.getGrandTotal();

        System.out.printf("%-30s Rp%,8.0f%n", "Subtotal:", subtotal);
        System.out.printf("%-30s Rp%,8.0f%n", "Pajak (10%):", pajak);
        cetakGarisSatu();
        System.out.printf("%-30s Rp%,8.0f%n", "TOTAL BAYAR:", grandTotal);
        cetakGarisDua();

        cetakTengah("Status: " + pesanan.getStatus().toString());
        cetakGarisDua();
        cetakTengah("Terima Kasih Atas Kunjungan Anda!");
        cetakTengah("Selamat Menikmati! :)");
        cetakGarisDua();
        System.out.println();
    }

    // Cetak struk ringkas (untuk kitchen)
    public void cetakStrukKitchen(Pesanan pesanan) {
        System.out.println();
        cetakGarisSatu();
        cetakTengah("[ KITCHEN ORDER ]");
        cetakGarisSatu();
        System.out.println("Pesanan #" + String.format("%03d", pesanan.getNomorPesanan()));
        System.out.println("Meja    : " + pesanan.getNomorMeja());
        System.out.println("Waktu   : " + pesanan.getWaktuPesanFormatted());
        cetakGarisSatu();

        for (OrderItem item : pesanan.getItems()) {
            System.out.printf("[%dx] %s%n", item.getJumlah(), item.getMenuItem().getNama());
            if (!item.getCatatan().isEmpty()) {
                System.out.println("  -> " + item.getCatatan());
            }
        }

        if (!pesanan.getCatatanKhusus().isEmpty()) {
            cetakGarisSatu();
            System.out.println("CATATAN KHUSUS: " + pesanan.getCatatanKhusus());
        }

        cetakGarisSatu();
        System.out.println();
    }

    // Helper methods
    private void cetakGarisDua() {
        System.out.println("=".repeat(LEBAR_NOTA));
    }

    private void cetakGarisSatu() {
        System.out.println("-".repeat(LEBAR_NOTA));
    }

    private void cetakTengah(String text) {
        if (text.length() >= LEBAR_NOTA) {
            System.out.println(text);
            return;
        }
        int padding = (LEBAR_NOTA - text.length()) / 2;
        System.out.println(" ".repeat(padding) + text);
    }

    private String padRight(String text, int length) {
        if (text.length() >= length) return text;
        return text + " ".repeat(length - text.length());
    }

    // Generate string nota (untuk disimpan)
    public String generateNotaString(Pesanan pesanan) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("=".repeat(LEBAR_NOTA)).append("\n");
        sb.append(centerText(NAMA_RESTORAN)).append("\n");
        sb.append(centerText(ALAMAT)).append("\n");
        sb.append("=".repeat(LEBAR_NOTA)).append("\n");
        sb.append(String.format("No. Pesanan: #%03d%n", pesanan.getNomorPesanan()));
        sb.append("Pelanggan  : ").append(pesanan.getNamaPelanggan()).append("\n");
        sb.append("No. Meja   : ").append(pesanan.getNomorMeja()).append("\n");
        sb.append("Waktu      : ").append(pesanan.getWaktuPesanFormatted()).append("\n");
        sb.append("-".repeat(LEBAR_NOTA)).append("\n");

        for (OrderItem item : pesanan.getItems()) {
            sb.append(String.format("%-22s x%-2d Rp%,8.0f%n",
                    item.getMenuItem().getNama(), item.getJumlah(), item.getSubtotal()));
        }

        sb.append("-".repeat(LEBAR_NOTA)).append("\n");
        sb.append(String.format("%-30s Rp%,8.0f%n", "TOTAL BAYAR:", pesanan.getGrandTotal()));
        sb.append("=".repeat(LEBAR_NOTA)).append("\n");
        return sb.toString();
    }

    private String centerText(String text) {
        if (text.length() >= LEBAR_NOTA) return text;
        int padding = (LEBAR_NOTA - text.length()) / 2;
        return " ".repeat(padding) + text;
    }
}
