package br.com.fiap.quotelevelling.cotacao;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import br.com.fiap.quotelevelling.empresa.cliente.ClienteRepository;
import br.com.fiap.quotelevelling.empresa.fornecedor.Fornecedor;
import br.com.fiap.quotelevelling.empresa.fornecedor.FornecedorRepository;
import br.com.fiap.quotelevelling.material.Material;
import br.com.fiap.quotelevelling.material.MaterialRepository;
import br.com.fiap.quotelevelling.user.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("cotacoes")
@Slf4j
public class CotacaoController {
    
    @Autowired
    CotacaoRepository cotacaoRepository;

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal DefaultOAuth2User user){
       
        List<Cotacao> cotacoes = cotacaoRepository.findAll();
        for (Cotacao cotacao : cotacoes) {
            Set<Material> materiais = cotacao.getMateriais();
        }


        model.addAttribute("cotacoes", cotacaoRepository.findAll());
        model.addAttribute("user", user.getAttribute("name"));
        model.addAttribute("avatar", user.getAttribute("avatar_url"));
        return "cotacao/index";
    }

    @GetMapping("new")
    public String form(Cotacao cotacao, Model model, @AuthenticationPrincipal DefaultOAuth2User user){
        model.addAttribute("materiais", materialRepository.findAll());
        
        model.addAttribute("user", user.getAttribute("name"));
        model.addAttribute("avatar", user.getAttribute("avatar_url"));

        return "cotacao/form";
    }

    @PostMapping
    public String insert(Long materialId, int quantidade, RedirectAttributes redirect, Model model, @AuthenticationPrincipal DefaultOAuth2User user){
        Material material = materialRepository.findById(materialId).orElse(null);

        if (material != null && material.getQtd_material() >= quantidade) {
            Cotacao cotacao = new Cotacao();
            cotacao.setValTotal_cotacao(material.getValUnit_material() * quantidade);
            cotacao.setQtdTotal_cotacao(quantidade);

            Long clienteId = (long) 2;
            cotacao.setCliente(clienteRepository.getById(clienteId));

            Fornecedor fornecedorMaterial = material.getFornecedor();
            cotacao.setFornecedor(fornecedorRepository.getById(fornecedorMaterial.getId_empresa()));

            cotacao.getMateriais().add(material);

            // Realizar baixa na quantidade do material
            material.setQtd_material(material.getQtd_material() - quantidade);

            cotacaoRepository.save(cotacao);
        } else {
            model.addAttribute("error", "Material inválido ou quantidade insuficiente.");
            model.addAttribute("materiais", materialRepository.findAll());
            return "cotacao/form";
        }

        redirect.addFlashAttribute("message", messageSource.getMessage("cotacao.create", null , LocaleContextHolder.getLocale()));
        return "redirect:/cotacoes";
    }

}
