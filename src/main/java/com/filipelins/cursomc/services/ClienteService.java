package com.filipelins.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.filipelins.cursomc.domain.Cidade;
import com.filipelins.cursomc.domain.Cliente;
import com.filipelins.cursomc.domain.Endereco;
import com.filipelins.cursomc.domain.enums.Perfil;
import com.filipelins.cursomc.domain.enums.TipoCliente;
import com.filipelins.cursomc.dto.ClienteDTO;
import com.filipelins.cursomc.dto.ClienteNewDTO;
import com.filipelins.cursomc.repositories.ClienteRepository;
import com.filipelins.cursomc.repositories.EnderecoRepository;
import com.filipelins.cursomc.security.UserSS;
import com.filipelins.cursomc.services.exceptions.AuthorizationException;
import com.filipelins.cursomc.services.exceptions.DataIntegrityException;
import com.filipelins.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente findById(Integer id) {
		
		UserSS userSS = UserService.authenticated();
		if (userSS == null || !userSS.hasRole(Perfil.ADMIN) && !id.equals(userSS.getId())) {
			throw new AuthorizationException("Acesso Negado!");
		}
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente = repo.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Cliente currentCliente = findById(cliente.getId());
		updateData(currentCliente, cliente);
		return repo.save(currentCliente);
	}

	public void deleteById(Integer id) {
		findById(id);

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail());
	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objDTO.getTipoCliente()), bCryptPasswordEncoder.encode(objDTO.getSenha()));
		Cidade cid = new Cidade();
		cid.setId(objDTO.getCidadeId());
		Endereco end = new Endereco(objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());

		if (objDTO.getTelefone2() != null && !objDTO.getTelefone2().isEmpty()) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}

		if (objDTO.getTelefone3() != null && !objDTO.getTelefone3().isEmpty()) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}

		return cli;
	}

	private void updateData(Cliente currentCliente, Cliente cliente) {
		currentCliente.setNome(cliente.getNome());
		currentCliente.setEmail(cliente.getEmail());
	}
}
