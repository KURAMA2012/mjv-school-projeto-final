package mjvschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mjvschool.cadastro.Produto;
import mjvschool.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@PostMapping
	public void salvar(@RequestBody Produto produto) {
		produtoRepository.save(produto);
	}
	@PutMapping
	public void alterar(@RequestBody Produto produto) {
		produtoRepository.save(produto);
	}
	@DeleteMapping ("/{id}")
	public void excluir(@PathVariable ("id") Integer id) {
		produtoRepository.deleteById(id);
	}
	@GetMapping
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
	@GetMapping ("/{id}")
	public Produto buscar(@PathVariable ("id") Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
}
