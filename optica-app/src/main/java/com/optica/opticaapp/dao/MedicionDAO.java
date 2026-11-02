package com.optica.opticaapp.dao;
import com.optica.opticaapp.modelo.Medicion;
import java.util.List;

public interface MedicionDAO {
    List<Medicion> listarPorCliente(int clienteId);
}