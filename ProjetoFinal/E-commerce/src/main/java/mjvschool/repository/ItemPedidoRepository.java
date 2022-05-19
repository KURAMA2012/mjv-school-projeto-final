package mjvschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mjvschool.pedido.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
	
}
