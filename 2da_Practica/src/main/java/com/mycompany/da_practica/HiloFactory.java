package com.mycompany.da_practica;

import javax.swing.table.DefaultTableModel;

public interface HiloFactory {
    MiHilo crearHilo(String codigo, String fecha, String hora, DefaultTableModel modelo);
}
