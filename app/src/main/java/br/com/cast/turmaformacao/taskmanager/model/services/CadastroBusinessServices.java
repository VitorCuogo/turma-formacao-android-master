package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.List;
import br.com.cast.turmaformacao.taskmanager.model.entities.Cadastro;
import br.com.cast.turmaformacao.taskmanager.model.persistence.CadastroRepository;


public class CadastroBusinessServices {

    public CadastroBusinessServices() {
        super();
    }

    public static List<Cadastro> findAll() {
        return CadastroRepository.getAll();
    }

    public static void save(Cadastro cadastro) {
        CadastroRepository.save(cadastro);
    }

    public static void delete(Cadastro cadastro){
        CadastroRepository.delete(cadastro.getId());
    }
}
