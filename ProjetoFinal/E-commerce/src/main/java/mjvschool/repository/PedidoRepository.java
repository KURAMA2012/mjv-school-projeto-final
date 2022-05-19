package mjvschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mjvschool.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
}
