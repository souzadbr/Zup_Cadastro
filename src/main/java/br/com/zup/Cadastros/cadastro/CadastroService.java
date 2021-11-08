package br.com.zup.Cadastros.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public void cadastrarPessoaModel(CadastroDTO cadastroDTO){

        Cadastro cadastroPessoa = new Cadastro();
        cadastroPessoa.setDataDoCadastro(LocalDate.now());
        cadastroPessoa.setBairro(cadastroDTO.getBairro());
        cadastroPessoa.setCidade(cadastroDTO.getCidade());
        cadastroPessoa.setCpf(cadastroDTO.getCpf());
        cadastroPessoa.setIdade(cadastroDTO.getIdade());
        cadastroPessoa.setNome(cadastroDTO.getNome());
        cadastroPessoa.setSobrenome(cadastroDTO.getSobrenome());
        cadastroPessoa.setMoraSozinho(cadastroDTO.isMoraSozinho());
        cadastroPessoa.setNomeDoParenteProximo(cadastroDTO.getNomeDoParenteProximo());
        cadastroPessoa.setTemPet(cadastroDTO.isTemPet());
        cadastroRepository.save(cadastroPessoa);

    }




}
