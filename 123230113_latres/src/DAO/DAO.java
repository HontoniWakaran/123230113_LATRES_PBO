package DAO;

import Model.ModelDB;
import Model.ModelTransaksi;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    public boolean inputdata(ModelTransaksi i){
        var connect = ModelDB.getConnection();
        try (var statement = connect.prepareStatement("INSERT INTO transaksi "
                + "(nama_pelanggan, nama_obat, harga_satuan_obat, jumlah_beli)"
                + " VALUES (?, ?, ?, ?)");){
            statement.setString(1, i.getNama_pelanggan());
            statement.setString(2, i.getNama_obat());
            statement.setInt(3, i.getHarga_satuaan_obat());
            statement.setInt(4, i.getJumlah_obat());
            statement.execute();
    }catch(Exception e){
        e.printStackTrace();
    }
        return true;
    }
    
    public boolean editdata(ModelTransaksi i){
        var connect = ModelDB.getConnection();
        try (var statement = connect.prepareStatement("UPDATE transaksi SET "
                + "nama_pelanggan=?, nama_obat=?,"
                + " harga_satuan_obat=?, jumlah_beli=? "
                + "WHERE id_pelanggan=?");){
            statement.setString(1, i.getNama_pelanggan());
            statement.setString(2, i.getNama_obat());
            statement.setInt(3, i.getHarga_satuaan_obat());
            statement.setInt(4, i.getJumlah_obat());
            statement.setInt(5, i.getId_pelanggan());
            statement.execute();
    }catch(Exception e){
        e.printStackTrace();
    }
        return true;
    }
    
    public boolean hapusdata(int id){
        var connect = ModelDB.getConnection();
        try (var statement = connect.prepareStatement("DELETE FROM transaksi WHERE id_pelanggan=?");){
            statement.setInt(1, id);
            statement.execute();
        }catch(Exception e){
             e.printStackTrace();
        }
         return true;
    }
    
    public List<ModelTransaksi> getAllTransaksi(){
         var connect = ModelDB.getConnection();
         var result = new ArrayList<ModelTransaksi>();
        try (var statement = connect.prepareStatement("SELECT * FROM transaksi");){
             statement.execute();
            var rs = statement.getResultSet();
            while(rs.next()){
                ModelTransaksi transaksi = new ModelTransaksi(
                rs.getInt("id_pelanggan"),
                rs.getString("nama_pelanggan"),
                rs.getString("nama_obat"),
                rs.getInt("harga_satuan_obat"),
                rs.getInt("jumlah_beli")
            );
            result.add(transaksi);
            }
        }catch(Exception e){
             e.printStackTrace();
        }
        return result;
    }
    
    public boolean isDuplicate(String namaPelanggan, String namaObat) {
    var connect = ModelDB.getConnection();
    try (var statement = connect.prepareStatement("SELECT COUNT(*) FROM transaksi WHERE nama_pelanggan = ? AND nama_obat = ?")) {
        statement.setString(1, namaPelanggan);
        statement.setString(2, namaObat);
        var rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
}