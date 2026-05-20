package model;

/**
 * Abstract class User sebagai base class untuk semua pengguna sistem.
 * Mengimplementasikan prinsip Inheritance dan Abstraction dalam OOP.
 */
public abstract class User {
    protected String username;
    protected String password;
    protected String namaLengkap;
    protected String role;

    // Constructor
    public User(String username, String password, String namaLengkap, String role) {
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.role = role;
    }

    // Abstract method yang harus diimplementasikan oleh subclass
    public abstract String getWelcomeMessage();
    public abstract void tampilkanMenu();

    // Method untuk verifikasi password
    public boolean verifikasiPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // Getters
    public String getUsername() { return username; }
    public String getNamaLengkap() { return namaLengkap; }
    public String getRole() { return role; }

    // Setters
    public void setPassword(String password) { this.password = password; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", role, namaLengkap, username);
    }
}
