package br.com.enjoeichallenge.repository.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteRepository{

    private static final String[] SCRIPT_DATABASE_CREATE = new String[] {

            "DROP TABLE IF EXISTS Cliente;" 		,
            "DROP TABLE IF EXISTS Despesa;" 		,
            "DROP TABLE IF EXISTS Recebimento;" 	,
            "DROP TABLE IF EXISTS TipoDespesa;" 	,
            "DROP TABLE IF EXISTS Pedido;" 			,
            "DROP TABLE IF EXISTS FormaPagto;" 		,
            "DROP TABLE IF EXISTS Transportador;" 	,
            "DROP TABLE IF EXISTS Produto;" 		,
            "DROP TABLE IF EXISTS ItensPed;" 		,
            "DROP TABLE IF EXISTS Vendedor;" 		,
            "DROP TABLE IF EXISTS Visita;" 			,
            "DROP TABLE IF EXISTS Parcela;" 		,
            "DROP TABLE IF EXISTS Mensagem;" 		,
            "DROP TABLE IF EXISTS MovEstoque;" 		,


            "	CREATE TABLE 'Cliente' (	"	+
                    "	  'CodCliente' 		integer(10) 	,	"	+
                    "	  'Nome' 			text(100) 		,	"	+
                    "	  'CNPJCPF' 		text(20) 		,	"	+
                    "	  'Email' 			text(50) 		,	"	+
                    "	  'Telefone' 		text(50) 		,	"	+
                    "	  'Cidade' 			text(50) 		,	"	+//COMPLEMENTO
                    "	  'Rua' 			text(50) 		,	"	+//ENDERECO E NUMERO
                    "	  'NumeroRua' 		integer(5) 		,	"	+//CAMPO SEM USO,
                    "	  'Regiao' 			text(10) 		,	"	+//CONTATO
                    "	  'Sincronizar' 	integer(1) 		,	"	+
                    "	  'Observacoes' 	text(100) 			"	+//INFO ADICIONAL DE BUSCA
                    "	);	"
            ,

            "	CREATE TABLE 'Despesa' (	"	+
                    "	  'CodDespesa' 		integer(10) 	,	"	+
                    "	  'CodTipoDespesa'  integer(10) 	,	"	+
                    "	  'Total' 			decimal(9,2) 	,	"	+
                    "	  'Observacoes' 	text(100) 		,	"	+
                    "	  'Sincronizar' 	integer(1) 		,	"	+
                    "	  'DataPagamento' 	datetime 		,	"	+
                    "	  'DataDigitacao' 	datetime			" 	+
                    "	);	"
            ,

            "	CREATE TABLE 'Recebimento' (	"	+
                    "	  'CodRecebimento' 	integer(10) 	,	"	+
                    "	  'CodCliente'  	integer(10) 	,	"	+
                    "	  'Total' 			decimal(9,2) 	,	"	+
                    "	  'Observacoes' 	text(100) 		,	"	+
                    "	  'Sincronizar' 	integer(1) 		,	"	+
                    "	  'DataRecebimento' datetime 		,	"	+
                    "	  'DataDigitacao' 	datetime			" 	+
                    "	);	"
            ,

            "	CREATE TABLE 'Mensagem' (	"	+
                    "	  'CodMensagem' 	integer(10) 	,	"	+
                    "	  'Tipo'  			integer(2) 		,	"	+
                    "	  'Aberta' 			integer(1) 		,	"	+
                    "	  'Titulo' 			text(60) 		,	"	+
                    "	  'Mensagem' 		blob	 		,	"	+
                    "	  'DataDigitacao' 	datetime			" 	+
                    "	);"

            ,

            "	CREATE TABLE 'MovEstoque' (	"	+
                    "	  'CodMov'		 	integer(10) 	,	"	+
                    "	  'CodProd'  		integer(10) 	,	"	+
                    "	  'Quantidade' 		decimal(16,3) 	,	"	+
                    "	  'DataLancamento' 	datetime 		,	"	+
                    "	  'Sincronizar' 	integer(1) 		,	"	+
                    "	  'Observacoes'	 	text(100)			" 	+
                    "	);	"
            ,

            "	CREATE TABLE 'Parcela' (	"	+
                    "	  'CodParcela' 		integer(10) 	,	"	+
                    "	  'NumParcela' 		integer(2) 		,	"	+
                    "	  'NumPed' 			text(20)	 	,	"	+
                    "	  'DataVencimento'	datetime 		,	"	+
                    "	  'ValorParcela' 	decimal(16,3) 	,	"	+
                    "	  'Observacoes'	 	text(100)		,	" 	+
                    "	  'Recebido' 		integer(1)  		"	+
                    "	);	"
            ,

            "	CREATE TABLE 'TipoDespesa' (	"	+
                    "	  'CodTipoDespesa' 		integer(10) 	,	"	+
                    "	  'Descricao' 			text(50) 			"	+
                    "	);	"
            ,

            "INSERT INTO 'TipoDespesa' VALUES (1, 'Produtos para Revenda/Produ��o'			);" ,
            "INSERT INTO 'TipoDespesa' VALUES (2, 'Viagens, Reuni�es e Refei��es'			);" ,
            "INSERT INTO 'TipoDespesa' VALUES (3, 'Impostos e Taxas'						);"	,
            "INSERT INTO 'TipoDespesa' VALUES (4, 'Comiss�es'								);"	,
            "INSERT INTO 'TipoDespesa' VALUES (5, 'Correio e Frete'							);"	,
            "INSERT INTO 'TipoDespesa' VALUES (6, 'Financeira e Juros'						);"	,
            "INSERT INTO 'TipoDespesa' VALUES (7, 'Agua, Energia, Telefone e Internet'		);"	,
            "INSERT INTO 'TipoDespesa' VALUES (8, 'Pessoal e Terceirizados'					);"	,
            "INSERT INTO 'TipoDespesa' VALUES (9, 'Diretoria'								);"	,
            "INSERT INTO 'TipoDespesa' VALUES (10, 'Alugu�is e Conserva��o'					);"	,
            "INSERT INTO 'TipoDespesa' VALUES (11, 'Escrit�rio, Inform�tica e Limpeza'		);"	,
            "INSERT INTO 'TipoDespesa' VALUES (12, 'Propaganda, Amostras e Eventos'			);"	,
            "INSERT INTO 'TipoDespesa' VALUES (13, 'Poupan�a e Investimentos'				);"	,
            "INSERT INTO 'TipoDespesa' VALUES (14, 'Outros'									);"	,


            "	CREATE TABLE 'Pedido' (	"	+
                    "	  'CodPed' 				INTEGER	PRIMARY KEY AUTOINCREMENT	,	"	+
                    "	  'NumPed' 				text(20)		,	"	+
                    "	  'CodCliente' 			integer(10) 	,	"	+
                    "	  'CodVendedor' 		integer(10) 	,	"	+
                    "	  'CodFormaPagto' 		integer(10) 	,	"	+
                    "	  'CodTransportador' 	integer(10) 	,	"	+
                    "	  'Total' 				decimal(9,2) 	,	"	+
                    "	  'Itens' 				integer(5) 		,	"	+
                    "	  'Observacoes' 		text(100)  		,	"	+
                    "	  'Andamento' 			integer(1) 		,	"	+
                    "	  'DataDigitacao' 		datetime		,	" 	+ //DEFAULT (datetime('now','localtime'))
                    "	  'DataSincronizacao' 	datetime		,	"	+
                    "	  'Sincronizar' 		integer(1) 		,	"	+
                    "	  'Parcelas' 			integer(5)			"	+
                    "	);	"


            ,

            "	CREATE TABLE 'FormaPagto' (	"	+
                    "	  'CodFormaPagto' 		integer(10) 	,	"	+
                    "	  'Descricao' 			text(50) 			"	+
                    "	);	"
            ,

            "INSERT INTO 'FormaPagto' VALUES (1, 'Dinheiro'				);" ,
            "INSERT INTO 'FormaPagto' VALUES (2, 'Cart�o de D�bito'		);" ,
            "INSERT INTO 'FormaPagto' VALUES (3, 'Cart�o de Cr�dito'	);"	,
            "INSERT INTO 'FormaPagto' VALUES (4, 'Dep�sito em Conta'	);"	,
            "INSERT INTO 'FormaPagto' VALUES (5, 'Boleto'				);"	,
            "INSERT INTO 'FormaPagto' VALUES (6, 'Carn�'				);"	,
            "INSERT INTO 'FormaPagto' VALUES (7, 'Cheque'				);"	,
            "INSERT INTO 'FormaPagto' VALUES (8, 'Outros'				);"	,


            "	CREATE TABLE 'Transportador' (	"	+
                    "	  'CodTransportador' 	integer(10) 	,	"	+
                    "	  'Nome' 				text(50) 			"	+
                    "	);	"
            ,

            "INSERT INTO 'Transportador' VALUES (1, 'Pr�prio'	);" 	,
            "INSERT INTO 'Transportador' VALUES (2, 'A Retirar'	);"		,
            "INSERT INTO 'Transportador' VALUES (3, 'Motoboy'	);"		,
            "INSERT INTO 'Transportador' VALUES (4, 'Correios'	);" 	,
            "INSERT INTO 'Transportador' VALUES (5, 'Outros'	);" 	,

            "	CREATE TABLE 'Produto' (	"	+
                    "	  'CodProd' 		integer(10) 	,	"	+
                    "	  'CodProdAntigo' 	integer(10) 	,	"	+
                    "	  'Refer' 			text(20) 		,	"	+
                    "	  'Descricao' 		text(100) 		,	"	+
                    "	  'Estoque' 		decimal(16,3) 	,	"	+
                    "	  'Preco' 			decimal(16,3) 	,	"	+
                    "	  'Unidade' 		text(10) 		,	"	+
                    "	  'CodBarras' 		text(100) 		,	"	+
                    "	  'Observacoes' 	text(100) 		,	"	+
                    "	  'DeEstoque' 		integer(1) 		,	"	+
                    "	  'Regiao' 			text(200) 		,	"	+//PRECO CUSTO
                    "	  'Sincronizar' 	integer(1) 		,	"	+
                    "	  'Promocao' 		integer(1) 			"	+
                    "	);	"
            ,

            "	CREATE TABLE 'ItensPed' (	"	+
                    "	  'NumPed' 			text(20)	 	,	"	+
                    "	  'CodProd' 		integer(10) 	,	"	+
                    "	  'Quantidade' 		integer(10) 	,	"	+
                    "	  'PrecoOriginal' 	decimal(9,2) 	,	"	+
                    "	  'Desconto' 		decimal(9,2) 		"	+
                    "	);	"

            ,

            "	CREATE TABLE 'Vendedor' (	"	+
                    "	  'CodVendedor' 		integer(10) ,	"	+
                    "	  'Nome' 				text(50) 	,	"	+
                    "	  'Email' 				text(100) 	,	"	+
                    "	  'Senha' 				text(50) 	,	"	+
                    "	  'RamoAtuacao' 		text(100) 	,	"	+
                    "	  'CodEmitente'		 	integer(10)	,	"	+
                    "	  'VersaoApp'		 	integer(2)	,	"	+
                    "	  'EmailEmitente' 		text(100) 	,	"	+
                    "	  'CPFCNPJ' 			text(20) 	,	"	+
                    "	  'VencimentoLicenca' 	datetime 	,	"	+
                    "	  'RazaoSocial' 		text(100) 		"	+
                    "	);	"

            ,

            "	CREATE TABLE 'Visita' (	"	+
                    "	  'CodVisita'	 		integer(10) ,	"	+
                    "	  'CodVendedor' 		integer(10) ,	"	+
                    "	  'CodCliente' 			integer(10) ,	"	+
                    "	  'NomeCliente' 		text(50)	,	"	+
                    "	  'DataAgendada' 		text(50)	,	"	+
                    "	  'DataVisitaInicio' 	text(50) 	,	"	+
                    "	  'DataVisitaTermino' 	text(50) 	,	"	+
                    "	  'Titulo' 				text(50) 	,	"	+
                    "	  'Tipo' 				text(50) 	,	"	+
                    "	  'Ocorrencias' 		text(200) 	,	"	+
                    "	  'Finalizada' 			integer(1) 	,	"	+
                    "	  'Origem' 				integer(1) 	,	"	+
                    "	  'Sincronizada' 		integer(1) 	DEFAULT 1,	"	+
                    "	  'Sincronizar' 		integer(1) 	,	"	+
                    "	  'ValorPago' 			decimal(9,2)	"	+
                    "	);	"

    };

    private static String NOME_BANCO = "enjoeichallenge_database";

    private static final int VERSAO_BANCO = 1;

    protected static SQLiteDatabase db;

    private static SQLiteHelper dbHelper;

    public SQLiteDatabase open(Context ctx) {

        if(dbHelper==null){

            dbHelper = new SQLiteHelper(ctx,
                    SQLiteRepository.NOME_BANCO,
                    SQLiteRepository.VERSAO_BANCO,
                    SQLiteRepository.SCRIPT_DATABASE_CREATE);

            db = dbHelper.getWritableDatabase();

        }

        return db;

    }

    public void close() {

        if(dbHelper != null) {
            dbHelper.close();
            dbHelper = null;
        }

    }

}
