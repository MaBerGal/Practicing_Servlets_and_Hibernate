
package dao;

import model.Usuario;
import java.util.ArrayList;
import java.util.List;
        
public interface UsuarioDao {
    
    public ArrayList<Usuario> getUsuarios();
    public void insertarUsuario(Usuario usuario);
    public void editarUsuario(Usuario usuario);
    public void borrarUsuario(Usuario usuario);
    Usuario getUsuarioPorCredenciales(String identificador, String password);
    
}
