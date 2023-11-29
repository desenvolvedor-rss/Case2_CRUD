package com.case2.crud.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    private String showlistaUsuario(Model model) {
        List<Usuario> listaUsuarios = service.listAll();
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "Usuarios";

    }

    @GetMapping("/usuarios/new")
    public String novaJanela(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("pageTitle", "Adicionar novo usuário");
        return "usuario_janela";
    }

    @PostMapping("/usuario/save")
    public String salvarUsuario(Usuario usuario, RedirectAttributes ra) {
        service.save(usuario);
        ra.addFlashAttribute("message", "Usuário salvo com sucesso!");
        return "redirect:/usuarios";

    }

    @GetMapping("/usuarios/edit/{id}")
    public String abaEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Usuario usuario = service.get(id);
            model.addAttribute("usuario", usuario);
            model.addAttribute("pageTitle", "Editar usuário identificação: " + id);
            return "usuario_janela";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/usuarios";
        }
    }

    @GetMapping("/usuarios/delete/{id}")
    public String deletarUsuario(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.deletar(id);
            ra.addFlashAttribute("message", "O usuário identificação " + id + " foi apagado!");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/usuarios";
    }


}
