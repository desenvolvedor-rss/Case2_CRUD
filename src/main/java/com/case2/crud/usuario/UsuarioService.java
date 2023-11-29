package com.case2.crud.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired private UsuarioRepository repo;
    public List<Usuario> listAll(){

        return (List<Usuario>) repo.findAll();
    }

    public void save(Usuario usuario) {
        repo.save(usuario);
    }

    public Usuario get(Integer id) throws UserNotFoundException {
        Optional<Usuario> resultado = repo.findById(id);
        if (resultado.isPresent()) {
            return resultado.get();
        }
        throw new UserNotFoundException("Usuário com o ID " + id + " não encontrado!");

    }
    public void deletar(Integer id) throws UserNotFoundException {
        Long contador = repo.countById(id);
        if (contador == null || contador == 0){
            throw new UserNotFoundException("Usuário com o ID " + id + " não encontrado!");

        }
        repo.deleteById(id);
    }

}
