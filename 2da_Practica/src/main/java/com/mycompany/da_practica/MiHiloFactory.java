package com.mycompany.da_practica;

import javax.swing.table.DefaultTableModel;

public class MiHiloFactory implements HiloFactory{
    @Override
    public MiHilo crearHilo(String codigo, String fecha, String hora, DefaultTableModel modelo) {
        return new MiHilo(codigo, fecha, hora, modelo);
    }    
}
