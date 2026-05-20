package model;

/**
 * Enum StatusPesanan mendefinisikan status-status yang bisa dimiliki sebuah pesanan.
 * Menggunakan Enum untuk type safety dalam OOP.
 */
public enum StatusPesanan {
    MENUNGGU("Menunggu Konfirmasi", "⏳"),
    DIPROSES("Sedang Diproses", "🔄"),
    SIAP("Pesanan Siap", "✅"),
    SELESAI("Selesai / Diambil", "🎉"),
    DIBATALKAN("Dibatalkan", "❌");

    private final String label;
    private final String icon;

    StatusPesanan(String label, String icon) {
        this.label = label;
        this.icon = icon;
    }

    public String getLabel() { return label; }
    public String getIcon() { return icon; }

    @Override
    public String toString() {
        return icon + " " + label;
    }
}
