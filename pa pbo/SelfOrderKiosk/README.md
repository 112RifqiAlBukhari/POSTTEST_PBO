# SISTEM SELF ORDER KIOSK
**Project Akhir - Pemrograman Berorientasi Objek (OOP)**

---

## DESKRIPSI
Sistem Self Order Kiosk adalah aplikasi berbasis console (CLI) yang mensimulasikan sistem pemesanan mandiri di restoran. Program ini dibuat menggunakan bahasa Java dengan menerapkan konsep-konsep Pemrograman Berorientasi Objek (OOP).

---

## KONSEP OOP YANG DITERAPKAN

| Konsep OOP       | Implementasi |
|------------------|-------------|
| **Encapsulation** | Semua class menggunakan `private` untuk atribut dan `public` getter/setter |
| **Inheritance**   | `Kitchen` dan `Manager` mewarisi `User` (Abstract Class) |
| **Abstraction**   | `User` adalah abstract class dengan method abstract `getWelcomeMessage()` dan `tampilkanMenu()` |
| **Polymorphism**  | Method `tampilkanMenu()` di-override berbeda oleh `Kitchen` dan `Manager` |
| **Composition**   | `Pesanan` terdiri dari `OrderItem`, `OrderItem` terdiri dari `MenuItem` |
| **Enum**          | `StatusPesanan` menggunakan Enum untuk type safety |

---

## STRUKTUR PROJECT

```
SelfOrderKiosk/
├── src/
│   ├── KioskApp.java              ← Main class (Entry Point)
│   │
│   ├── model/                     ← Layer Model (Entity)
│   │   ├── User.java              ← Abstract Base Class
│   │   ├── Kitchen.java           ← Subclass dari User
│   │   ├── Manager.java           ← Subclass dari User
│   │   ├── Pelanggan.java         ← Entity Pelanggan
│   │   ├── MenuItem.java          ← Entity Item Menu
│   │   ├── OrderItem.java         ← Entity Item Pesanan
│   │   ├── Pesanan.java           ← Entity Pesanan
│   │   └── StatusPesanan.java     ← Enum Status
│   │
│   ├── service/                   ← Layer Bisnis Logic
│   │   ├── AuthService.java       ← Autentikasi User
│   │   ├── MenuService.java       ← CRUD Menu
│   │   ├── PesananService.java    ← Manajemen Pesanan
│   │   └── NotaService.java       ← Cetak Nota/Struk
│   │
│   ├── ui/                        ← Layer Antarmuka
│   │   ├── PelangganUI.java       ← UI untuk Pelanggan
│   │   ├── KitchenUI.java         ← UI untuk Kitchen
│   │   ├── ManagerUI.java         ← UI untuk Manager
│   │   └── LoginUI.java           ← UI Login
│   │
│   └── util/                      ← Utilitas
│       ├── DisplayHelper.java     ← Helper Tampilan
│       └── InputHelper.java       ← Helper Input
│
├── bin/                           ← File .class (hasil kompilasi)
├── SelfOrderKiosk.jar             ← Executable JAR
├── compile.sh                     ← Script kompilasi (Linux/Mac)
├── run.sh                         ← Script menjalankan (Linux/Mac)
└── README.md
```

---

## FITUR-FITUR

### 1. PELANGGAN (Tanpa Login)
- Masukkan nama dan nomor meja
- Lihat daftar menu berdasarkan kategori
- Tambah item ke pesanan (dengan catatan opsional)
- Lihat ringkasan pesanan aktif
- Hapus item dari pesanan
- Konfirmasi dan cetak nota pesanan
- Lihat status pesanan yang sudah dikonfirmasi

### 2. KITCHEN STAFF (Login Required)
- Akun demo: `dapur1` / `kitchen123`
- Lihat semua pesanan
- Update status pesanan:
  - ⏳ Menunggu Konfirmasi
  - 🔄 Sedang Diproses
  - ✅ Pesanan Siap
  - 🎉 Selesai
  - ❌ Dibatalkan
- Lihat pesanan aktif (per status)
- Riwayat pesanan selesai
- Notifikasi pesanan baru

### 3. MANAGER (Login Required)
- Akun demo: `manager` / `manager123`
- Lihat semua menu
- Tambah menu baru
- Edit menu (nama, harga, ketersediaan)
- Hapus menu
- Laporan semua pesanan (dengan filter)
- Laporan pendapatan dan statistik
- Top 5 menu terlaris

---

## CARA MENJALANKAN

### Menggunakan JAR (Paling Mudah):
```bash
java -jar SelfOrderKiosk.jar
```

### Kompilasi Manual & Jalankan:
```bash
# Kompilasi
javac -d bin -sourcepath src src/KioskApp.java src/model/*.java src/service/*.java src/ui/*.java src/util/*.java

# Jalankan
java -cp bin KioskApp
```

### Menggunakan Script (Linux/Mac):
```bash
bash compile.sh
bash run.sh
```

---

## AKUN DEMO

| Role    | Username | Password    |
|---------|----------|-------------|
| Kitchen | dapur1   | kitchen123  |
| Kitchen | dapur2   | kitchen456  |
| Manager | manager  | manager123  |
| Manager | admin    | admin123    |

---

## ALUR PENGGUNAAN

```
[Mulai]
    ↓
[Halaman Utama]
    ├── [1] PESAN → Masukkan nama & meja → Lihat menu → Tambah pesanan → Konfirmasi → Cetak Nota
    └── [2] LOGIN → [1] Kitchen atau [2] Manager
                         ↓                    ↓
                  [Kitchen Dashboard]  [Manager Dashboard]
                  - Update Status      - CRUD Menu
                  - Lihat Pesanan      - Laporan
```

---

## PERSYARATAN SISTEM
- Java JDK 11 atau lebih baru
- Terminal/Command Prompt

---

*Dibuat untuk Project Akhir Mata Kuliah Pemrograman Berorientasi Objek*
