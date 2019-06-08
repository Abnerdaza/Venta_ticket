package com.example.venta;

public class Stock {

    private String stock_periodo;
    private int stoc_becados;

    public Stock() {
    }

    public Stock(String stock_periodo, int   stoc_becados) {
        this.stock_periodo = stock_periodo;
        this.stoc_becados = stoc_becados;
    }

    public String getStock_periodo() {
        return stock_periodo;
    }

    public void setStock_periodo(String stock_periodo) {
        this.stock_periodo = stock_periodo;
    }

    public int getStoc_becados() {
        return stoc_becados;
    }

    public void setStoc_becados(int stoc_becados) {
        this.stoc_becados = stoc_becados;
    }


    @Override
    public String toString() {
        return stock_periodo+ " " +stoc_becados;
    }
}
