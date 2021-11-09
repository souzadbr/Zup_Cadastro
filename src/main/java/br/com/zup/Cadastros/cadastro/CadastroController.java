package br.com.zup.Cadastros.cadastro;

import br.com.zup.Cadastros.cadastro.dto.CadastroDTO;
import br.com.zup.Cadastros.cadastro.dto.CadastroResumidoDTO;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping //cadastra somente
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastroPessoa(@RequestBody CadastroDTO cadastroDTO) {
        //metodo pega as informações da DTO e preenche a model que está nula até então.

        Cadastro cadastro = modelMapper.map(cadastroDTO, Cadastro.class);
        cadastroService.cadastrarPessoaModel(cadastro);
    }

    @GetMapping
    public List<CadastroResumidoDTO> exibirCadastrosdaLista(@RequestParam(required = false)Boolean moraSozinho,
                                                            @RequestParam(required = false)Integer idade,
                                                            @RequestParam(required = false)Boolean temPet) {
        List<CadastroResumidoDTO> cadastroResumidoDTOS = new ArrayList<>();
        for (Cadastro cadastro : cadastroService.exibirTodosOsCadastros(moraSozinho, idade, temPet)) {
            cadastroResumidoDTOS.add(new
                    CadastroResumidoDTO(cadastro.getNome(),cadastro.getSobrenome(),cadastro.getCpf()));
        }
        return cadastroResumidoDTOS;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) //significa que não existe corpo na resposta
    public void deletarCadastro(@PathVariable @Valid @CPF String cpf){ //Não tem corpo então o cpf vem do caminho URL
        // @PathVariable @CPF para validar se relamente é um CPF
        cadastroService.deletarCadastro(cpf);
    }

}




    /*
    todo  1 - crie um metodo para cadastrar uma pessoa.CHECK
      Para um cadastro todo os campos são obrigatórios EXCETO o campo dataDoCadastro que deve ser preenchido pelo proprio sistema e o client não deve saber da existencia desse campo
     todo 2 - Faça um metodo que retorna a lista inteira de cadastros ou filtrado por cadastros que moram sozinhos, que tem pet ou por idade.
     nessa lista deve ser retornado apenas os campos ID, NOME e SOBRENOME.check
     todo 3 - faça um metodo para DELETAR um cadastro por id.
     todo 4 - faça um metodo que retorna TODOS os dados de um usuario pesquisado pelo ID.
     */


