package mjvschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mjvschool.cadastro.Cliente;
import mjvschool.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	
	@ApiOperation(value  = "Retorna uma lista de clientes")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200" , description = "Cliente retornado com sucesso"),
		    @ApiResponse(responseCode = "404" , description = "Cliente não localizado"),
		})
	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable ("id")  Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		
		if (cliente != null ) {
			return ResponseEntity.ok(cliente);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não localizado");
		}
	}
	
	@ApiOperation(value  = "Cadastra um  cliente")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200" , description = "Cliente cadastrado com sucesso"),
		    @ApiResponse(responseCode = "404" , description = "Cliente não cadastrado"),
		})
	@PostMapping
	public Cliente cadastrar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	
	@ApiOperation(value  = "Deleta um cliente através  do Id")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200" , description = "Cliente deletado com sucesso"),
		    @ApiResponse(responseCode = "404" , description = "Cliente não localizado"),
		})
	@DeleteMapping ("/{id}")
	public void deletarPorId(@PathVariable ("id") Integer id) {
		clienteRepository.deleteById(id);
	}
		
		
	
	@PutMapping
	public void alterarPorId(@RequestBody Cliente cliente) {
		clienteRepository.save(cliente);
	}

}
