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
public class TableInfo {
    private int maBan;
    private String sucChua;
    private String viTri;
    private String tinhTrang;

    public TableInfo(int maBan, String sucChua, String viTri, String tinhTrang){
        this.maBan = maBan;
        this.sucChua = sucChua;
        this.viTri = viTri;
        this.tinhTrang = tinhTrang;
    }
    
    public TableInfo(String sucChua, String viTri, String tinhTrang){
        this.sucChua = sucChua;
        this.viTri = viTri;
        this.tinhTrang = tinhTrang;
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
     * @return the sucChua
     */
    public String getSucChua() {
        return sucChua;
    }

    /**
     * @param sucChua the sucChua to set
     */
    public void setSucChua(String sucChua) {
        this.sucChua = sucChua;
    }

    /**
     * @return the viTri
     */
    public String getViTri() {
        return viTri;
    }

    /**
     * @param viTri the viTri to set
     */
    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    /**
     * @return the tinhTrang
     */
    public String getTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
