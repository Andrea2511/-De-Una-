package co.edu.escuelaing.cvds.project.service;

import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.PromocionRepository;
import co.edu.escuelaing.cvds.project.repository.TarjetaRepository;
import co.edu.escuelaing.cvds.project.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Tarjeta obtenerTarjetaPorUser(User titular) {
        return tarjetaRepository.findByUsuario(titular);
    }

    public List<Transaccion> obtenerTransacciones(Tarjeta tarjeta) {
        return transaccionRepository.findByTarjeta(tarjeta);
    }

    public List<Tarjeta> obtenerTodasLasTarjetas() {
        return tarjetaRepository.findAll();
    }

    public Tarjeta crearTarjeta(User titular) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setSaldoT(0);
        tarjeta.setPassword("0000");
        tarjeta.setUsuario(titular);
        tarjeta.setPuntosRedimibles(5000);

        List<Transaccion> transacciones = new ArrayList<>();
        tarjeta.setTransacciones(transacciones);

        return tarjetaRepository.save(tarjeta);
    }

    public Tarjeta changePassword(String Password, Tarjeta tarjeta) {
        tarjeta.setPassword(Password);

        return tarjetaRepository.save(tarjeta);
    }

    public void recarga(double monto, LocalDateTime fechaPago, Tarjeta tarjeta, String descripcion) {

        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(TipoTransaccion.RECARGA);
        transaccion.setMonto(monto);
        transaccion.setFechaPago(fechaPago);
        transaccion.setTarjeta(tarjeta);
        transaccion.setDescripcion(descripcion);

        tarjeta.getTransacciones().add(transaccion);
        tarjeta.setSaldoT(tarjeta.getSaldoT() + monto);

        transaccionRepository.save(transaccion);
        tarjetaRepository.save(tarjeta);

    }

    public Transaccion paga(double monto, LocalDateTime fechaPago, Tarjeta tarjeta, String descripcion) {

        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(TipoTransaccion.PAGO);
        transaccion.setMonto(monto);
        transaccion.setFechaPago(fechaPago);
        transaccion.setTarjeta(tarjeta);
        transaccion.setDescripcion(descripcion);

        tarjeta.getTransacciones().add(transaccion);

        double montoNuevo = tarjeta.getSaldoT() - monto;
        tarjeta.setSaldoT(montoNuevo);

        double montoPuntosRedimibles = monto * 0.02;
        tarjeta.setPuntosRedimibles((int) montoPuntosRedimibles);

        tarjetaRepository.save(tarjeta);
        transaccionRepository.save(transaccion);

        return transaccion;

    }

    public void modificarPuntos(Tarjeta tarjeta) {
        tarjeta.setPuntosRedimibles(0);
        tarjetaRepository.save(tarjeta);
    }

    public void actualizarTarjeta(Tarjeta tarjeta) {
        tarjetaRepository.save(tarjeta);
    }
}

