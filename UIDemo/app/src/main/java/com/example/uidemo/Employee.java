package com.example.uidemo;

import android.net.Uri;

public class Employee {
    private int maSo;
    private String hoTen;
    private String gioiTinh;
    private String donVi;
    private Uri imageId;

    public int getMaSo() {
        return maSo;
    }

    public void setMaSo(int maSo) {
        this.maSo = maSo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public Uri getImageId() {
        return imageId;
    }

    public void setImageId(Uri imageId) {
        this.imageId = imageId;
    }

    /**
     *
     * @param maSo
     * @param hoTen
     * @param gioiTinh
     * @param donVi
     * @param imageId
     */

    public Employee(int maSo, String hoTen, String gioiTinh, String donVi, Uri imageId) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.donVi = donVi;
        this.imageId = imageId;
    }

    /**
     *
     * @param maSo
     * @param hoTen
     * @param gioiTinh
     * @param donVi
     */
    public Employee(int maSo, String hoTen, String gioiTinh, String donVi) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.donVi = donVi;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "NhanVien{" + maSo +
                ", " + hoTen +
                ", " + gioiTinh +
                ", " + donVi +
                '}';
    }


}
