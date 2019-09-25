package disciplinasUFCG.DAOS;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import disciplinasUFCG.entities.Usuario;

@Repository
public interface UsuariosDAO<T, ID extends Serializable> extends JpaRepository<Usuario, String> {

}
