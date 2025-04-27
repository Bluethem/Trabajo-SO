package com.mycompany.da_practica;

import javax.swing.table.DefaultTableModel;

public class MiHilo extends Thread implements PrototipoHilo, RegistroIngreso, RegistroSalida {    
    private final String codigo;
    private final String fecha;
    private final String hora;
    private boolean esperandoSalida = true;
    private final Object lock = new Object();
    private final DefaultTableModel modelo;

    public MiHilo(String codigo, String fecha, String hora, DefaultTableModel modelo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.modelo = modelo;
    }

    @Override
    public void run() {
        registrarIngreso();
        synchronized (lock) {
            try {
                while (esperandoSalida) {
                    lock.wait(); // El hilo se duerme esperando
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        registrarSalida();
    }
    
    @Override
    public void registrarIngreso() {
        Object[] fila = {codigo, fecha, hora, "INGRESO"};
        modelo.addRow(fila);
    }
    
    @Override
    public void salidaHilo() {
        synchronized (lock) {
            esperandoSalida = false;
            lock.notify(); // Despertar el hilo
        }
    }
    
    @Override
    public void registrarSalida() {
        Object[] fila = {codigo, fecha, hora, "SALIDA"};
        modelo.addRow(fila);
    }

    @Override
    public MiHilo clone() {
        try {
            return (MiHilo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
