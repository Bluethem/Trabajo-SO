package com.mycompany.da_practica;

import java.time.LocalTime;
import java.time.Duration;

public class TiempoTabla implements CalcularTiempo{

    private final LocalTime horaInicio;
    private final LocalTime horaFinal;
    private static final LocalTime HORA_ENTRADA = LocalTime.of(7, 0);
    private static final LocalTime HORA_SALIDA = LocalTime.of(18, 0);

    public TiempoTabla(String horaInicioStr, String horaFinalStr) {
        this.horaInicio = LocalTime.parse(horaInicioStr);
        this.horaFinal = LocalTime.parse(horaFinalStr);
    }

    @Override
    public long calcularHMI() {
        if (horaInicio.isBefore(HORA_ENTRADA)) {
            return Duration.between(horaInicio, HORA_ENTRADA).toMinutes();
        }
        return 0;
    }

    @Override
    public long calcularHF() {
        if (horaInicio.isAfter(HORA_ENTRADA)) {
            return Duration.between(HORA_ENTRADA, horaInicio).toMinutes();
        }
        return 0;
    }

    @Override
    public long calcularANTO() {
        if (horaFinal.isBefore(HORA_SALIDA)) {
            return Duration.between(horaFinal, HORA_SALIDA).toMinutes();
        }
        return 0;
    }

    @Override
    public long calcularDES() {
        if (horaFinal.isAfter(HORA_SALIDA)) {
            return Duration.between(HORA_SALIDA, horaFinal).toMinutes();
        }
        return 0;
    }

    @Override
    public long calcularTF() {
        return calcularHMI() + calcularDES();
    }

    @Override
    public long calcularTC() {
        return calcularHF() + calcularANTO();
    }

    @Override
    public long calcularTCO() {
        return calcularTF() - calcularTC();
    } 
    
}
