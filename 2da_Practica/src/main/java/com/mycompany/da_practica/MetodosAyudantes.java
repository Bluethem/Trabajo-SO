package com.mycompany.da_practica;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class MetodosAyudantes {
    
    private final HiloFactory hiloFactory;
    private final DefaultTableModel modelo;
    private final DefaultTableModel tabla;
    private final Map<String, MiHilo> hilosActivos;
    private final Map<String, String> horasIngreso;

    public MetodosAyudantes(HiloFactory hiloFactory, DefaultTableModel modelo, DefaultTableModel tabla,
                          Map<String, MiHilo> hilosActivos, Map<String, String> horasIngreso) {
        this.hiloFactory = hiloFactory;
        this.modelo = modelo;
        this.tabla = tabla;
        this.hilosActivos = hilosActivos;
        this.horasIngreso = horasIngreso;
    }

    public void registrarIngreso(String codigo) {
        String fecha = LocalDate.now().toString();
        String hora = LocalTime.now().toString().substring(0, 8);

        if (codigo.isEmpty()) {
            throw new IllegalArgumentException("Ingrese un código válido.");
        }
        if (hilosActivos.containsKey(codigo)) {
            throw new IllegalStateException("Esta persona ya ingresó. Solo puede registrar salida.");
        } else {
            MiHilo hilo = hiloFactory.crearHilo(codigo, fecha, hora, modelo);
            hilosActivos.put(codigo, hilo);
            hilo.start();
            horasIngreso.put(codigo, hora);
        }
    }

    public void registrarSalida(String codigo) {
        if (!hilosActivos.containsKey(codigo)) {
            throw new IllegalStateException("Esta persona no tiene un ingreso registrado.");
        }
        MiHilo hilo = hilosActivos.get(codigo);
        hilo.salidaHilo();
        hilosActivos.remove(codigo);

        String fecha = LocalDate.now().toString();
        String horaInicio = horasIngreso.get(codigo);
        String horaFinal = LocalTime.now().toString().substring(0, 8);

        TablaMain registro = new TablaMain(codigo, fecha, horaInicio, horaFinal);
        tabla.addRow(new Object[]{
            registro.getCodigo(),
            registro.getFecha(),
            registro.getHMI(),
            registro.getHF(),
            registro.getANTO(),
            registro.getDES(),
            registro.getTF(),
            registro.getTC(),
            registro.getTCO()
        });

        horasIngreso.remove(codigo);
    }
}