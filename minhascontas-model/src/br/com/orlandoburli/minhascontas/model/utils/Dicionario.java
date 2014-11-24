package br.com.orlandoburli.minhascontas.model.utils;

public final class Dicionario {

	public final class Usuario {
		public static final String TABELA_USUARIO = "usuario";
		public static final String SEQUENCIA_USUARIO = "seq_id_usuario";

		public static final String CONSTRAINT_UNIQUE_EMAIL = "unq_email_usu";
		public static final String CONSTRAINT_UNIQUE_HASH_EMAIL = "unq_hash_email_usu";

		public final class Colunas {
			public static final String ID_USUARIO = "id_usuario";
			public static final String NOME = "nome";
			public static final String SOBRENOME = "sobrenome";
			public static final String TIPO_AUTENTICACAO = "tipo_autenticacao";
			public static final String EMAIL = "email";
			public static final String ID_FACEBOOK = "id_facebook";
			public static final String SENHA = "senha";
			public static final String PATH_FOTO = "path_foto";
			public static final String FLAG_CONFIRMADO = "flag_confirmado";
			public static final String HASH_EMAIL = "hash_email";
		}
	}

	public final class Token {
		public static final String TABELA_TOKEN = "token";
		public static final String SEQUENCIA_TOKEN = "seq_id_token";

		public static final String CONSTRAINT_UNIQUE_CHAVE = "unq_chave_token";

		public final class Colunas {
			public static final String ID_TOKEN = "id_token";
			public static final String CHAVE_TOKEN = "chave_token";
			public static final String ID_USUARIO = Usuario.Colunas.ID_USUARIO;
			public static final String DATA_HORA_CRIACAO = "data_hora_criacao";
			public static final String DATA_HORA_ULTIMO_ACESSO = "data_hora_ultimo_acesso";
			public static final String EXPIRADO = "expirado";
		}
	}

	public final class Categoria {
		public static final String TABELA_CATEGORIA = "categoria";
		public static final String SEQUENCIA_CATEGORIA = "seq_categoria";

		public final class Colunas {
			public static final String ID_CATEGORIA = "id_categoria";
			public static final String ID_USUARIO = "id_usuario";
			public static final String NOME_CATEGORIA = "nome_categoria";
			public static final String TIPO_CATEGORIA = "tipo_categoria";
			public static final String ID_CATEGORIA_PAI = "id_categoria_pai";
		}
	}

	public final class Conta {
		public static final String TABELA_CONTA = "conta";
		public static final String SEQUENCIA_CONTA = "seq_conta";

		public final class Colunas {
			public static final String ID_CONTA = "id_conta";
			public static final String NOME_CONTA = "nome_conta";
			public static final String ID_USUARIO = "id_usuario";
			public static final String SALDO_ATUAL = "saldo_atual";
		}
	}

	public final class Movimentacao {
		public static final String TABELA_MOVIMENTACAO = "movimentacao";
		public static final String SEQUENCIA_MOVIMENTACAO = "seq_movimentacao";

		public final class Colunas {
			public static final String ID_MOVIMENTACAO = "id_movimentacao";
			public static final String ID_CONTA = "id_conta";
			public static final String TIPO_MOVIMENTACAO = "tipo_movimentacao";
			public static final String DATA_MOVIMENTACAO = "data_movimentacao";
			public static final String DATA_HORA_LANCAMENTO_MOV = "dt_hr_lanc_mov";
			public static final String VALOR_MOVIMENTACAO = "valor_movimentacao";
			public static final String SALDO_ATUAL = "saldo_atual";
		}
	}

	public final class Despesa {
		public static final String TABELA_DESPESA = "despesa";
		public static final String SEQUENCIA_DESPESA = "seq_id_despesa";

		public final class Colunas {
			public static final String ID_DESPESA = "id_despesa";
			public static final String ID_USUARIO = "id_usuario";
			public static final String DESCRICAO_DESPESA = "descricao_despesa";
			public static final String DATA_VENCIMENTO = "data_vencimento";
			public static final String DATA_PAGAMENTO = "data_pagamento";
			public static final String VALOR = "valor";
			public static final String REINCIDENTE = "reincidente";
			public static final String ID_CATEGORIA = "id_categoria";
			public static final String JUROS_MULTAS = "juros_multas";
			public static final String DESCONTO = "desconto";
			public static final String VALOR_TOTAL = "valor_total";
			public static final String VALOR_PAGO = "valor_pago";
			public static final String ID_MOVIMENTACAO = "id_movimentacao";
			public static final String STATUS_DESPESA = "status_despesa";
		}
	}

	public final class Receita {
		public static final String TABELA_RECEITA = "receita";
		public static final String SEQUENCIA_RECEITA = "seq_id_receita";

		public final class Colunas {
			public static final String ID_RECEITA = "id_receita";
			public static final String ID_USUARIO = "id_usuario";
			public static final String DESCRICAO_RECEITA = "descricao_receita";
			public static final String DATA_VENCIMENTO = "data_vencimento";
			public static final String DATA_RECEBIMENTO = "data_recebimento";
			public static final String VALOR = "valor";
			public static final String ID_CATEGORIA = "id_categoria";
			public static final String JUROS_MULTAS = "juros_multas";
			public static final String DESCONTO = "desconto";
			public static final String VALOR_TOTAL = "valor_total";
			public static final String VALOR_RECEBIDO = "valor_recebido";
			public static final String ID_MOVIMENTACAO = "id_movimentacao";
			public static final String STATUS_RECEITA = "status_receita";
		}
	}
}
