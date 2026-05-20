#!/bin/bash
# Script untuk mengkompilasi Self Order Kiosk

echo "Mengkompilasi program..."
mkdir -p bin

javac -d bin -sourcepath src \
    src/KioskApp.java \
    src/model/*.java \
    src/service/*.java \
    src/ui/*.java \
    src/util/*.java

if [ $? -eq 0 ]; then
    echo "Kompilasi berhasil!"
    jar --create --file SelfOrderKiosk.jar --main-class KioskApp -C bin .
    echo "JAR file dibuat: SelfOrderKiosk.jar"
    echo ""
    echo "Jalankan dengan: java -jar SelfOrderKiosk.jar"
    echo "atau           : bash run.sh"
else
    echo "ERROR: Kompilasi gagal!"
fi
