package br.com.zup.Cadastros.cadastro;

import br.com.zup.Cadastros.cadastro.exception.CadastroDuplicadoException;
import br.com.zup.Cadastros.cadastro.exception.CadastroNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public Cadastro cadastrarPessoaModel(Cadastro cadastro) {

        if (cadastroRepository.existsById(cadastro.getCpf())) {
            throw new CadastroDuplicadoException();
        }
        cadastro.setDataDoCadastro(LocalDate.now());
        return cadastroRepository.save(cadastro);

    }

    public List<Cadastro> exibirTodosOsCadastros(Boolean moraSozinho, Integer idade, Boolean temPet) {
        //Esse (List<Cadastro>) está covnertendo o cadastroRepository para uma lista.

        if (moraSozinho != null) {
            return cadastroRepository.findAllByMoraSozinho(moraSozinho);
        } else if (idade != null) {
            return cadastroRepository.findAllByIdade(idade);
        } else if (temPet != null) {
            return cadastroRepository.findAllByTemPet(temPet);
        }
        return (List<Cadastro>) cadastroRepository.findAll();
    }

    public void deletarCadastro(String cpf) {
        if (cadastroRepository.existsById(cpf)) {
            cadastroRepository.deleteById(cpf);
        } else {
            throw new CadastroNaoExisteException();
        }

    }
    //Optinal é comos e fosse uma caixinha e pode ou não ter o objeto dentro dela
    //força a verificar dentro da caixinha se há ou não o objeto dentro, se não estiver o objeto
    // retorna que não foi encontrado
    public Cadastro retornarCadastroPorId(String cpf) {
        Optional<Cadastro> cadastroOptional = cadastroRepository.findById(cpf);
        if (cadastroOptional.isPresent()) {
            return cadastroOptional.get();
        }

        throw new CadastroNaoExisteException();
    }
}
