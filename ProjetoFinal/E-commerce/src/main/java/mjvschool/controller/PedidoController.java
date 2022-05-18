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

import mjvschool.pedido.ItemPedido;
import mjvschool.pedido.Pedido;
import mjvschool.repository.ItemPedidoRepository;
import mjvschool.repository.PedidoRepository;
import mjvschool.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@PostMapping
	public void gravar(@RequestBody Pedido pedido) {
		pedidoService.gravar(pedido);
	}
	
	@GetMapping
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
	@GetMapping ("/{id}")
	public Pedido buscarPorId(@PathVariable ("id") Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	

	@DeleteMapping ("/{id}")
	public void deletarPorId(@PathVariable ("id") Integer id) {
		Pedido pedido = pedidoRepository.getOne(id);
		/*
		for(ItemPedido item: pedido.getItens()) {
			itemPedidoRepository.delete(item);
		}
		*/
		pedidoRepository.delete(pedido);
	}
	
	@PutMapping("alterar-item-pedido/{idPedido}/{idItem}/{qtdItem}/{valorItem}")
	public ResponseEntity alterartem(@PathVariable ("idPedido") Integer idPedido,
						   @PathVariable ("idItem") Integer idItem,
						   @PathVariable ("qtdItem") Integer qtdItem,
						   @PathVariable ("valorItem") double valorItem) {
		
		ItemPedido itemPedido = itemPedidoRepository.getOne(idItem);
		itemPedido.setQuatidade(qtdItem);
		itemPedido.setValorUnitario(valorItem);

		Double subTotal = qtdItem * valorItem;
		
		itemPedido.setSubTotal(subTotal);
		itemPedidoRepository.save(itemPedido);
		
		double valorTotal = 0;
		Pedido pedido = pedidoRepository.findById(idPedido).orElse(null);
		if (pedido == null || pedido.getId() == null) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Pedido invalido");
		}

		/*atualiza valor total*/
		for(ItemPedido item: pedido.getItens()) {
			/*Soma o valor total do pedido*/
			valorTotal = valorTotal + item.getSubTotal();
		}
		
		pedido.setValorTotal(subTotal);
		pedidoRepository.save(pedido);
		
		return ResponseEntity.ok(itemPedido);
	}
	
	@DeleteMapping("deletar-item-pedido/{idItem}")
	public void deletartemPedido(
						   @PathVariable ("idItem") Integer idItem) {

		itemPedidoRepository.deleteById(idItem);
	}
}
