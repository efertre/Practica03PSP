package model;

public enum TipoComision {
    Administracion(0.015),
    Aepertura(0.02),
    Estudio(0.010),
    Transferencias(0.05),
    Descubierto(0.03),
    Cambio_Divisas(0.025),
    Cancelacion(0.018),
    Tarjeta_Regal(0.012);

    private final double interes;

    // Constructor para asociar el interés a cada tipo de comisión
    TipoComision(double interes) {
        this.interes = interes;
    }

    // Método para obtener el interés
    public double getInteres() {
        return interes;
    }
}