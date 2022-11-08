package br.com.recode.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.recode.model.DoacaoEquipamento;
import br.com.recode.model.RequisicaoEquipamento;
import br.com.recode.model.Usuario;
import br.com.recode.servico.DoacaoEquipamentoServico;
import br.com.recode.servico.RequisicaoEquipamentoServico;
import br.com.recode.servico.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PageController {
	
	private final UsuarioDetailsService usuarioDetailsService;
	private final RequisicaoEquipamentoServico requisicaoEquipamentoServico;
	private final DoacaoEquipamentoServico doacaoEquipamentoServico;

	
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
		ModelAndView modelAndView = new ModelAndView("usuario/usuarioDoacoes.html");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = Usuario.class.cast(authentication.getPrincipal());
		List<DoacaoEquipamento> doacoes = doacaoEquipamentoServico.listarTodosUsuario(usuarioLogado);
		modelAndView.addObject("doacoes", doacoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	@GetMapping("/usuario/cadastroDoacao")
	public ModelAndView cadastroDoacao(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("usuario/usuarioCadastroDoacao.html");
		modelAndView.addObject("doacaoEquipamento", new DoacaoEquipamento());
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	
	@GetMapping("/usuario/requisicoes")
	public ModelAndView  requisicoes(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("usuario/usuarioRequisicoes.html");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = Usuario.class.cast(authentication.getPrincipal());
		List<RequisicaoEquipamento> requisicoes = requisicaoEquipamentoServico.listarTodosUsuario(usuarioLogado);
		modelAndView.addObject("requisicoes", requisicoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	@GetMapping("/usuario/cadastroRequisicao")
	public ModelAndView cadastroRequisicao(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("usuario/usuarioCadastroRequisicao.html");
		modelAndView.addObject("requisicaoEquipamento", new RequisicaoEquipamento());
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	
	@GetMapping("/usuario/dados")
	public ModelAndView  meusdados(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("usuario/usuarioDados.html");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = Usuario.class.cast(authentication.getPrincipal());
		modelAndView.addObject("usuario", usuarioLogado);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	//----------------------------------------
	//Somente Admin
	//----------------------------------------
	@GetMapping("/admin/listarDoacoes")
	public ModelAndView listarDoacoes(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("admin/listarDoacoes.html");
		List<DoacaoEquipamento> doacoes = doacaoEquipamentoServico.listarTodosLista();
		modelAndView.addObject("doacoes", doacoes);
		modelAndView = adicionarDadosUsuarioView(principal, modelAndView);
	    return modelAndView;
    }
	@GetMapping("/admin/listarRequisicoes")
	public ModelAndView listarRequisicoes(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("admin/listarRequisicoes.html");
		List<RequisicaoEquipamento> requisicoes = requisicaoEquipamentoServico.listarTodosLista();
		modelAndView.addObject("requisicoes", requisicoes);
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
	//----------------------------------------
	//Posts
	//----------------------------------------
	@PostMapping("/usuario/atualizarUsuario")
    public ModelAndView atualizarUsuario(Usuario usuario){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = Usuario.class.cast(authentication.getPrincipal());
		usuario.setId(usuarioLogado.getId());
		usuario.setUsername(usuarioLogado.getUsername());
		usuario.setPassword(usuarioLogado.getPassword());
        usuario.setAuthorities("ROLE_USER");
        usuarioDetailsService.atualizarUsuario(usuario);
       return modelAndView;
    }
	
	@PostMapping("/cadastrarUsuario")
    public ModelAndView cadastrarUsuario(Usuario usuario){
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        usuario.setAuthorities("ROLE_USER");
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioDetailsService.cadastrarUsuario(usuario);
       return modelAndView;
    }
	@PostMapping("/usuario/cadastrarRequisicao")
    public ModelAndView cadastrarRequisicao(RequisicaoEquipamento requisicaoEquipamento){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = Usuario.class.cast(authentication.getPrincipal());
		requisicaoEquipamento.setUsuario(usuarioLogado);
        ModelAndView modelAndView = new ModelAndView("redirect:/usuario/requisicoes");
        requisicaoEquipamentoServico.cadastrarRequisicaoEquipamento(requisicaoEquipamento);
        return modelAndView;
    }
	@PostMapping("/usuario/cadastrarDoacao")
    public ModelAndView cadastrarDoacao(DoacaoEquipamento doacaoEquipamento){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = Usuario.class.cast(authentication.getPrincipal());
		doacaoEquipamento.setUsuario(usuarioLogado);
        ModelAndView modelAndView = new ModelAndView("redirect:/usuario/doacoes");
        doacaoEquipamentoServico.cadastrarDoacaoEquipamento(doacaoEquipamento);
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
