package mjvschool.pedido.dto;

import java.time.LocalDateTime;

public class PedidoListagem {
	private Integer id;
	private LocalDateTime dataHora;
	private String cpfCliente;
	private String nomeCliente;
	private Double valorTotal;

	public PedidoListagem(Integer id, LocalDateTime dataHora, String cpfCliente, String nomeCliente,
			Double valorTotal) {
		super();
		this.id = id;
		this.dataHora = dataHora;
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
		this.valorTotal = valorTotal;
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public Double getValorTotal() {
		return valorTotal;
	}
}
