
package dao;

import model.Compra;
import java.util.ArrayList;
import java.util.List;

public interface CompraDao {
    
    public Compra getCompraPorId(int id);
    public ArrayList<Compra> getCompras();
    public ArrayList<Compra> getComprasPorUsuario(String id);
    public void insertarCompra(Compra compra);
    public void editarCompra(Compra compra);
    public void borrarCompra(Compra compra);
    public int getSiguienteID();
    public ArrayList<Compra> getComprasOrdenadas(String idUsuario, String criterioOrden);
    
}
