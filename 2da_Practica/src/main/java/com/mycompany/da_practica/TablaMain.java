package com.mycompany.da_practica;

public class TablaMain  {
    
    private String codigo;
    private String fecha;
    private String horaInicio;
    private String horaFinal;
    private final CalcularTiempo calculador;

    public TablaMain(String codigo, String fecha, String horaInicio, String horaFinal) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.calculador = new TiempoTabla(horaInicio, horaFinal);
    }
    
    public long getHMI() { return calculador.calcularHMI(); }
    
    public long getHF() { return calculador.calcularHF(); }
    
    public long getANTO() { return calculador.calcularANTO(); }
    
    public long getDES() { return calculador.calcularDES(); }
    
    public long getTF() { return calculador.calcularTF(); }
    
    public long getTC() { return calculador.calcularTC(); }
    
    public long getTCO() { return calculador.calcularTCO(); }
    
    public String getCodigo() {        return codigo;    }

    public void setCodigo(String codigo) {        this.codigo = codigo;    }

    public String getFecha() {        return fecha;    }

    public void setFecha(String fecha) {        this.fecha = fecha;    }

    public String getHoraInicio() {        return horaInicio;    }

    public void setHoraInicio(String horaInicio) {        this.horaInicio = horaInicio;    }

    public String getHoraFinal() {        return horaFinal;    }

    public void setHoraFinal(String horaFinal) {        this.horaFinal = horaFinal;    }
    
}
