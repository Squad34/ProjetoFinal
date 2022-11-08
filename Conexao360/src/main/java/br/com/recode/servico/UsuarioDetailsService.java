package br.com.recode.servico;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.recode.model.Usuario;
import br.com.recode.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService{
	
	private final UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		return Optional.ofNullable(usuarioRepositorio.findByUsername(username))
				.orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado"));
	}
	
	@Transactional
	public Usuario cadastrarUsuario(Usuario usuario) {	
		return usuarioRepositorio.save(usuario);
	}
	
	@Transactional
	public void atualizarUsuario(Usuario usuario) {	
		usuarioRepositorio.atualizarUsuario(usuario);
	}
	
	public List<Usuario> listarTodosLista(){
		return usuarioRepositorio.findAll();
	}
	
	public Usuario buscarPorId(long id){
		return usuarioRepositorio.getOne(id);
	}
}
