package model.exceptions;

public class EFechaNoValida extends Exception {        
    public EFechaNoValida() {}
    public EFechaNoValida(String mensaje) {
        super(mensaje);
    };    
}
