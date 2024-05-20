package br.com.fiap.quotelevelling.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;


@Controller
@RequestMapping("materiais")
public class MaterialController {
    

    @Autowired
    MaterialRepository repository;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal DefaultOAuth2User user){
        model.addAttribute("materiais", repository.findAll());
        model.addAttribute("user", user.getAttribute("name"));
        model.addAttribute("avatar", user.getAttribute("avatar_url"));
        return "material/index";
    }

    @GetMapping("new")
    public String form(Material material){
        return "material/form";
    }

    @PostMapping
    public String insert(@Valid Material material, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors())return "material/form";
        repository.save(material);
        redirect.addFlashAttribute("message", messageSource.getMessage("endereco.create", null , LocaleContextHolder.getLocale()));
        return "redirect:/materiais";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        var material = repository.findById(id);

        if (material.isEmpty()){
            redirect.addFlashAttribute("message", "Erro ao apagar. Material não encontrado");
            return "redirect:/materiais";
        }

        try {
            repository.deleteById(id);
            redirect.addFlashAttribute("message", messageSource.getMessage("material.delete", null, LocaleContextHolder.getLocale()));
        } catch (DataIntegrityViolationException e) {
            redirect.addFlashAttribute("error", "Erro ao apagar. Material está associado a uma cotação e não pode ser deletado.");
        }
        return "redirect:/materiais";
    }
}

