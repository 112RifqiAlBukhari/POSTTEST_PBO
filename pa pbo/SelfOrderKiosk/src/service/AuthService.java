package service;

import model.Kitchen;
import model.Manager;
import model.User;
import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private List<User> daftarUser;
    private User userAktif;

    public AuthService() {
        this.daftarUser = new ArrayList<>();
        this.userAktif = null;
        inisialisasiUserDefault();
    }

    private void inisialisasiUserDefault() {
        // Kitchen Staff
        daftarUser.add(new Kitchen("dapur1", "kitchen123", "Budi Santoso"));
        daftarUser.add(new Kitchen("dapur2", "kitchen456", "Sari Dewi"));

        // Manager
        daftarUser.add(new Manager("manager", "manager123", "Ahmad Fauzi"));
        daftarUser.add(new Manager("admin", "admin123", "Rina Permata"));
    }

    // Login otomatis deteksi role dari username & password
    public User login(String username, String password) {
        for (User user : daftarUser) {
            if (user.getUsername().equals(username)
                    && user.verifikasiPassword(password)) {
                userAktif = user;
                return user;
            }
        }
        return null;
    }

    // Logout
    public void logout() {
        userAktif = null;
    }

    // Cek apakah ada user aktif
    public boolean isLoggedIn() {
        return userAktif != null;
    }

    // Ambil user aktif
    public User getUserAktif() {
        return userAktif;
    }

    // Cek role user aktif
    public boolean isKitchen() {
        return userAktif != null && userAktif.getRole().equals("KITCHEN");
    }

    public boolean isManager() {
        return userAktif != null && userAktif.getRole().equals("MANAGER");
    }

    // Ambil semua user (untuk manager)
    public List<User> getAllUser() {
        return new ArrayList<>(daftarUser);
    }

    // Tambah user baru (oleh manager)
    public boolean tambahUser(User user) {
        for (User existing : daftarUser) {
            if (existing.getUsername().equals(user.getUsername())) {
                return false;
            }
        }
        daftarUser.add(user);
        return true;
    }
}