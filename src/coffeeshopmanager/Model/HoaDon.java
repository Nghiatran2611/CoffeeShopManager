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
public class HoaDon {
    private int maHoaDon;
    private int maBan;

    public HoaDon(int maHoaDon, int maBan) {
        this.maHoaDon = maHoaDon;
        this.maBan = maBan;
    }
    /**
     * @return the maHoaDon
     */
    public int getMaHoaDon() {
        return maHoaDon;
    }

    /**
     * @param maHoaDon the maHoaDon to set
     */
    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    /**
     * @return the MaBan
     */
    public int getMaBan() {
        return maBan;
    }

    /**
     * @param MaBan the MaBan to set
     */
    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }
    
}
