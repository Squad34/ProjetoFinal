package br.com.recode.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.recode.model.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PageController {

	
	//----------------------------------------
	//Acessiveis à qualquer um
	//----------------------------------------
	
	@GetMapping({"", "/", "/index"})
	public ModelAndView index(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("index.html");
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
	}
	
	@GetMapping("/login")
    public ModelAndView login(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("login.html");
		
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
		
	    return modelAndView;
    }
	
	@GetMapping("/sobre")
    public ModelAndView sobre(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("sobre.html");
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	
	@GetMapping("/contato")
    public ModelAndView contato(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("contato.html");
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	
	@GetMapping("/cadastroUsuario")
    public ModelAndView cadastroUsuario(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("cadastroUsuario.html");
		modelAndView.addObject("usuario", new Usuario());
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	
	//----------------------------------------
	//Necessita Login
	//----------------------------------------
	@GetMapping("/usuario/doacoes")
	public ModelAndView  doacoes(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("usuarioDoacoes.html");
		//List<Destino> doacoes = doacaoServico.listarTodosLista();
		//modelAndView.addObject("doacoes", coacoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	
	@GetMapping("/usuario/requisicoes")
	public ModelAndView  requisicoes(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("usuarioRequisicoes.html");
		//List<Destino> requisicoes = requisicaoServico.listarTodosLista();
		//modelAndView.addObject("requisicoes", requisicoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	
	@GetMapping("/usuario/dados")
	public ModelAndView  meusdados(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("usuarioDados.html");
		//Usuario usuario = usuarioServico.listarTodosLista();
		//modelAndView.addObject("requisicoes", requisicoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	//----------------------------------------
	//Somente Admin
	//----------------------------------------
	@GetMapping("/admin/listarDoacoes")
	public ModelAndView listarDoacoes(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("admin/listarDoacoes.html");
		//List<Destino> doacoes = doacaoServico.listarTodosLista();
		//modelAndView.addObject("doacoes", coacoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	@GetMapping("/admin/listarRequisicoes")
	public ModelAndView listarRequisicoes(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("admin/listarRequisicoes.html");
		//List<Destino> doacoes = doacaoServico.listarTodosLista();
		//modelAndView.addObject("doacoes", coacoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	@GetMapping("/admin/listarUsuarios")
	public ModelAndView listarUsuarios(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("admin/listarUsuarios.html");
		//List<Destino> doacoes = doacaoServico.listarTodosLista();
		//modelAndView.addObject("doacoes", coacoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	@GetMapping({"/admin","/admin/", "/admin/efetuarDoacao"})
	public ModelAndView efetuarDoacao(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("admin/efetuarDoacao.html");
		//List<Destino> doacoes = doacaoServico.listarTodosLista();
		//modelAndView.addObject("doacoes", coacoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	
	
	public ModelAndView adicionarDadosUsuarioView(Principal principal, ModelAndView modelAndView) {
		if(principal != null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			boolean isAdmin = authentication.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
			
			modelAndView.addObject("isLogged", true);
			if(principal.getName() != null) {
				Usuario usuarioLogado = Usuario.class.cast(authentication.getPrincipal());
				modelAndView.addObject("usuarioNavbar", usuarioLogado);
				log.info(usuarioLogado);
			} else {
				modelAndView.addObject("usuarioNavbar", "usuario");
			}
			modelAndView.addObject("isAdmin", isAdmin);
		} else {
			modelAndView.addObject("usuarioNavbar", "usuario");
			modelAndView.addObject("isLogged", false);
		}
		return modelAndView;
	}
}
