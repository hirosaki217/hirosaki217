package com.example.uidemo;

import android.net.Uri;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

public class Employee implements Serializable {
    private int maSo;
    private String hoTen;
    private String gioiTinh;
    private String donVi;
    private String imageId;

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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
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

    public Employee(int maSo, String hoTen, String gioiTinh, String donVi, String imageId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getMaSo() == employee.getMaSo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaSo());
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
