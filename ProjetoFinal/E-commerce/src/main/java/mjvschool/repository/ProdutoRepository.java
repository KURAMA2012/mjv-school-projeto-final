package mjvschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mjvschool.cadastro.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
