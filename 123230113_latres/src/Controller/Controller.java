package Controller;

import DAO.DAO;
import Model.ModelTransaksi;
import View.ViewTransaksi;
import java.util.List;

public class Controller {
    private ViewTransaksi View;
    private DAO DAO_Transaksi = new DAO();
    private List<ModelTransaksi> data;
    private ModelTransaksi model;
    
    public Controller(ViewTransaksi View){
        this.View = View;
        refresh();
    }
    
    private void refresh(){
        data = DAO_Transaksi.getAllTransaksi();
        View.setTable(data);
        updateSelected(-1);
    }
    
    public void insertTransaksi(String namaPelanggan, String namaObat, int hargaSatuan, int jumlahBeli) {
        var transaksi = new ModelTransaksi(0, namaPelanggan, namaObat, hargaSatuan, jumlahBeli);
        if (DAO_Transaksi.isDuplicate(namaPelanggan, namaObat)) {
            View.showError("Data duplikat, sudah pernah dimasukkan!");
        return;
        }
        if(DAO_Transaksi.inputdata(transaksi)) {
            refresh();
        } else {
            View.showError("Data Gagal Ditambahkan");
        }
    }
    
    public void updateSelected(int index) {
        if (index == -1) {
            model = null;
        } else {
            model = data.get(index);
        }
        View.setSelected(model);
    }
    
    public void edit(String namaPelanggan, String namaObat, int hargaSatuan, int jumlahBeli) {
        if(model == null){
            View.showError("Belum ada Yang Dipilih");
            return;
        }
        var transaksi = new ModelTransaksi(model.getId_pelanggan(), namaPelanggan, namaObat, hargaSatuan, jumlahBeli);
        if (DAO_Transaksi.editdata(transaksi)){           
            refresh();
        } else {
            View.showError("User Gagal Ditambahkan");
        }
    }
    
    public void delete() {
        if(model == null){
            View.showError("Belum ada Yang Dipilih");
            return;
        }
        if (DAO_Transaksi.hapusdata(model.getId_pelanggan())){           
            refresh();
        } else {
            View.showError("User Gagal Ditambahkan");
        }
    }
    
    public void showTotalBayar(int index) {
        if (index < 0 || index >= data.size()) {
            View.showError("Index transaksi tidak valid!");
            return;
        }

        ModelTransaksi transaksi = data.get(index);
        int total = transaksi.getHarga_satuaan_obat()* transaksi.getJumlah_obat();
        int diskon = (transaksi.getJumlah_obat()> 5) ? total / 10 : 0;
        int totalBayar = total - diskon;

        String message = "Harga satuan: Rp " + transaksi.getHarga_satuaan_obat()
                   + "\nJumlah beli: " + transaksi.getJumlah_obat()
                   + "\nTotal: Rp " + total
                   + (diskon > 0 ? "\nDiskon 10%: Rp " + diskon : "")
                   + "\nTotal Bayar: Rp " + totalBayar;

    View.showMessage(message);
    }
}
