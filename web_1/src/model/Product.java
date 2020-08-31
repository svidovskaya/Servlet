package model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


public class Product {

    private int id;

    private String name;
    private String kode;
    private String shtrih;
    private String manuf;
    private float price;
    private float price_stom;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getShtrih() {
        return shtrih;
    }

    public void setShtrih(String shtrih) {
        this.shtrih = shtrih;
    }


    public String getManuf() {
        return manuf;
    }

    public void setManuf(String manuf) {
        this.manuf = manuf;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice_stom() {
        return price_stom;
    }

    public void setPrice_stom(float price_stom) {
        this.price_stom = price_stom;
    }

    public Product(int id, String name, String kode, String shtrih, String manuf, float price, float price_stom) {
        this.id = id;
        this.name = name;
        this.kode = kode;
        this.shtrih = shtrih;
        this.manuf = manuf;
        this.price = price;
        this.price_stom = price_stom;
    }
}
