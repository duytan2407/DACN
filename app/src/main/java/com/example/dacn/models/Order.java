package com.example.dacn.models;

public class Order {
    private String tongtien;

    private String diachi;

    private String sdt;

    private String iduser;

    private String ngaygiao;

    private String trangthai;

    private String giogiao;

    private String phuongthucthanhtoan;

    private String id;

    private String hoten;

    public Order() {
    }

    public Order(String tongtien, String diachi, String sdt, String iduser, String ngaygiao, String trangthai, String giogiao, String phuongthucthanhtoan, String id, String hoten) {
        this.tongtien = tongtien;
        this.diachi = diachi;
        this.sdt = sdt;
        this.iduser = iduser;
        this.ngaygiao = ngaygiao;
        this.trangthai = trangthai;
        this.giogiao = giogiao;
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        this.id = id;
        this.hoten = hoten;
    }


    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getDiachi ()
    {
        return diachi;
    }

    public void setDiachi (String diachi)
    {
        this.diachi = diachi;
    }

    public String getSdt ()
    {
        return sdt;
    }

    public void setSdt (String sdt)
    {
        this.sdt = sdt;
    }

    public String getIduser ()
    {
        return iduser;
    }

    public void setIduser (String iduser)
    {
        this.iduser = iduser;
    }

    public String getNgaygiao ()
    {
        return ngaygiao;
    }

    public void setNgaygiao (String ngaygiao)
    {
        this.ngaygiao = ngaygiao;
    }

    public String getTrangthai ()
    {
        return trangthai;
    }

    public void setTrangthai (String trangthai)
    {
        this.trangthai = trangthai;
    }

    public String getGiogiao ()
    {
        return giogiao;
    }

    public void setGiogiao (String giogiao)
    {
        this.giogiao = giogiao;
    }

    public String getPhuongthucthanhtoan ()
    {
        return phuongthucthanhtoan;
    }

    public void setPhuongthucthanhtoan (String phuongthucthanhtoan)
    {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getHoten ()
    {
        return hoten;
    }

    public void setHoten (String hoten)
    {
        this.hoten = hoten;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tongtien = "+tongtien+", diachi = "+diachi+", sdt = "+sdt+", iduser = "+iduser+", ngaygiao = "+ngaygiao+", trangthai = "+trangthai+", giogiao = "+giogiao+", phuongthucthanhtoan = "+phuongthucthanhtoan+", id = "+id+", hoten = "+hoten+"]";
    }
}
