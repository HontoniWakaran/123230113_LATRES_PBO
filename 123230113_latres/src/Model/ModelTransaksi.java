
package Model;

public class ModelTransaksi {
    
    private int id_pelanggan;
    private String nama_pelanggan;
    private String nama_obat;
    private int harga_satuaan_obat;
    private int jumlah_obat;

    public ModelTransaksi(int id_pelanggan, String nama_pelanggan, String nama_obaat, int harga_satuaan_obat, int jumlah_obat) {
        this.id_pelanggan = id_pelanggan;
        this.nama_pelanggan = nama_pelanggan;
        this.nama_obat = nama_obaat;
        this.harga_satuaan_obat = harga_satuaan_obat;
        this.jumlah_obat = jumlah_obat;
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public int getHarga_satuaan_obat() {
        return harga_satuaan_obat;
    }

    public int getJumlah_obat() {
        return jumlah_obat;
    }
}
