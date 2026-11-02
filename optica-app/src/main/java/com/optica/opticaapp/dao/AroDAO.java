package com.optica.opticaapp.dao;

import com.optica.opticaapp.modelo.Aro;
import java.util.List;

public interface AroDAO {
    void insertar(Aro aro);
    List<Aro> listarTodos();
    Aro buscarPorId(int id);
    void actualizar(Aro aro); // <--- LÍNEA NUEVA
    void actualizarStock(int id, int nuevoStock);
    void eliminar(int id);
    List<Aro> listarStockBajo(int limite);
}