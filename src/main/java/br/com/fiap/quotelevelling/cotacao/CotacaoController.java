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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.quotelevelling.empresa.cliente.ClienteRepository;
import br.com.fiap.quotelevelling.empresa.fornecedor.Fornecedor;
import br.com.fiap.quotelevelling.empresa.fornecedor.FornecedorRepository;
import br.com.fiap.quotelevelling.material.Material;
import br.com.fiap.quotelevelling.material.MaterialRepository;


@Controller
@RequestMapping("cotacoes")
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
    public String insert(Long[] materialIds, int[] quantidades, RedirectAttributes redirect, Model model, @AuthenticationPrincipal DefaultOAuth2User user){
        Cotacao cotacao = new Cotacao();
        double valTotal = 0;
        int qtdTotal = 0;

        for (int i = 0; i < materialIds.length; i++) {
            Long materialId = materialIds[i];
            int quantidade = quantidades[i];
            Material material = materialRepository.findById(materialId).orElse(null);

            if (material != null && material.getQtd_material() >= quantidade) {
                valTotal += material.getValUnit_material() * quantidade;
                qtdTotal += quantidade;

                cotacao.getMateriais().add(material);

                material.setQtd_material(material.getQtd_material() - quantidade);
                materialRepository.save(material);
            } else {
                model.addAttribute("error", "Material inválido ou quantidade insuficiente.");
                model.addAttribute("materiais", materialRepository.findAll());
                return "cotacao/form";
            }
        }

        cotacao.setValTotal_cotacao(valTotal);
        cotacao.setQtdTotal_cotacao(qtdTotal);

        Long clienteId = (long) 2;
        cotacao.setCliente(clienteRepository.getById(clienteId));

        Fornecedor fornecedorMaterial = materialRepository.findById(materialIds[0]).get().getFornecedor();
        cotacao.setFornecedor(fornecedorRepository.getById(fornecedorMaterial.getId_empresa()));

        cotacaoRepository.save(cotacao);

        redirect.addFlashAttribute("message", messageSource.getMessage("cotacao.create", null , LocaleContextHolder.getLocale()));
        return "redirect:/cotacoes";
    }
}