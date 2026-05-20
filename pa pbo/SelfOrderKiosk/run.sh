#!/bin/bash
# Script untuk menjalankan Self Order Kiosk

echo "==========================="
echo " SELF ORDER KIOSK"
echo "==========================="

# Cek apakah Java sudah terinstall
if ! command -v java &> /dev/null; then
    echo "ERROR: Java tidak ditemukan! Pastikan Java sudah terinstall."
    exit 1
fi

# Cek apakah file JAR ada
if [ -f "SelfOrderKiosk.jar" ]; then
    java -jar SelfOrderKiosk.jar
elif [ -d "bin" ]; then
    java -cp bin KioskApp
else
    echo "File tidak ditemukan. Kompilasi terlebih dahulu dengan compile.sh"
    exit 1
fi
