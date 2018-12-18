/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Model;

/**
 *
 * @author Tinh
 */
public class ChiTietHoaDon {
    private int maChiTietHoaDon;
    private int maBan;
    private int maMon;
    private int soLuong;
    
    public ChiTietHoaDon(int maChiTietHoaDon, int maBan, int maMon, int soLuong){
        this.maChiTietHoaDon = maChiTietHoaDon;
        this.maBan = maBan;
        this.maMon = maMon;
        this.soLuong = soLuong;
    }

    /**
     * @return the maChiTietHoaDon
     */
    public int getMaChiTietHoaDon() {
        return maChiTietHoaDon;
    }

    /**
     * @param maChiTietHoaDon the maChiTietHoaDon to set
     */
    public void setMaChiTietHoaDon(int maChiTietHoaDon) {
        this.maChiTietHoaDon = maChiTietHoaDon;
    }

    /**
     * @return the maBan
     */
    public int getMaBan() {
        return maBan;
    }

    /**
     * @param maBan the maBan to set
     */
    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    /**
     * @return the maMon
     */
    public int getMaMon() {
        return maMon;
    }

    /**
     * @param maMon the maMon to set
     */
    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

}
