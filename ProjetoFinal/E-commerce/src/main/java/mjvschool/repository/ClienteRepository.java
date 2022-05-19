package mjvschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mjvschool.cadastro.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer>{

}
