package br.com.zup.Cadastros.cadastro;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;
import java.util.List;

public interface CadastroRepository extends CrudRepository<Cadastro, String> {
    List<Cadastro>findAllByMoraSozinho(boolean moraSozinho);
    List<Cadastro>findAllByIdade(int idade);
    List<Cadastro>findAllByTemPet(boolean temPet);
}
