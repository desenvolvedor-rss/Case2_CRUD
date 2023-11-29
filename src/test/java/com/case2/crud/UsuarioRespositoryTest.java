package com.case2.crud;

import com.case2.crud.usuario.Usuario;
import com.case2.crud.usuario.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UsuarioRespositoryTest {

    @Autowired
    private UsuarioRepository repo;

    @Test
    public void testADDNew() {
        Usuario usuario = new Usuario();

        usuario.setNome("Okada");
        usuario.setSobrenome("Ipaq");
        usuario.setEmail("adoroproblemade@ipag.com");
        usuario.setSenha("password");

        Usuario saveUsuario = repo.save(usuario);

        Assertions.assertNotNull(saveUsuario);
        Assertions.assertTrue(saveUsuario.getId() > 0);
    }

    @Test
    public void testlistAll() {
        Iterable<Usuario> usuarios = repo.findAll(); //Chama o findAll para obter todos os usuarios do bando de dados
        Assertions.assertTrue(usuarios.iterator().hasNext(), "Lista de usuários está vazia");

        //MatcherAssert.assertThat(usuarios, Matchers.iterableWithSize(Matchers.greaterThan(0))); //Verifica se a quantida de lista é mairo que zero

        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }

    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<Usuario> optionalUsuario = repo.findById(userId);
        Assertions.assertTrue(optionalUsuario.isPresent(), "Usuário não encontrado");

        Usuario usuario = optionalUsuario.get();
        usuario.setSenha("password");
        Usuario updateUsuario = repo.save(usuario);
        Assertions.assertEquals("password", updateUsuario.getSenha());
        //MatcherAssert.assertThat(updateUsuario.getSenha()).isEqualTo("password");
    }

    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<Usuario> optionalUsuario = repo.findById(userId);
        Assertions.assertTrue(optionalUsuario.isPresent());
        System.out.println(optionalUsuario.get());

    }

    @Test
    public void testDelete(){
        Integer userId = 4;
        repo.deleteById(userId);
        Optional<Usuario> optionalUsuario = repo.findById(userId);
        Assertions.assertFalse(optionalUsuario.isPresent());

    }


}
