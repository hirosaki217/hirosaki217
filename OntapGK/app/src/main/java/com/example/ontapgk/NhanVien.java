package com.example.ontapgk;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String diaChi;
    private String ngaySinh;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public NhanVien(String maNV, String tenNV, String diaChi, String ngaySinh) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
    }

    public NhanVien() {
    }

    @Override
    public String toString() {
        return
                "maNV:'" + maNV + '\'' +
                ", tenNV: '" + tenNV + '\'' +
                ", diaChi: '" + diaChi + '\'' +
                ", ngaySinh:'" + ngaySinh + '\''
                ;
    }
}
