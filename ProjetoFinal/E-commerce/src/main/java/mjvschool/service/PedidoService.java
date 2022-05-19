package mjvschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mjvschool.pedido.ItemPedido;
import mjvschool.pedido.Pedido;
import mjvschool.pedido.PedidoStatus;
import mjvschool.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;

	public void gravar(Pedido pedido) {
		pedido.setStatus(PedidoStatus.NOVO);
		Double valorTotal = 0.0;

		/*Iteracaco nos itens*/
		for(ItemPedido item: pedido.getItens()) {
			/*Associa pedido ao item*/
			item.setPedido(pedido);

			/*Calcula sub total do produto*/
			double subTotal = item.getQuatidade() * item.getValorUnitario();
			item.setSubTotal(subTotal);

			/*Soma o valor total do pedido*/
			valorTotal = valorTotal + item.getSubTotal();
		}
		
		/*Atualiza valor total*/
		pedido.setValorTotal(valorTotal);
		
		/*Gravo pedido*/
		pedidoRepository.save(pedido);
	}
}
