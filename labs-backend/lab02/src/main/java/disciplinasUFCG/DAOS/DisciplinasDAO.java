package disciplinasUFCG.DAOS;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import disciplinasUFCG.entities.Disciplina;

@Repository
public interface DisciplinasDAO<T, ID extends Serializable> extends JpaRepository<Disciplina, Long> {

}