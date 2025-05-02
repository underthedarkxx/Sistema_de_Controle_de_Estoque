package Sistema_de_Controle_de_Estoque;
import Sistema_de_Controle_de_Estoque.InOut;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        int opcoes, qtd;
        String nome, cod;
        float PUC, PUV;
        
        do {
            //roda pelo menos uma vez o programa.
            opcoes = InOut.leInt("Escolha uma das opções abaixo:\n" +
                    "1: Inclusão de cadastros\n" +
                    "2: Alteração de um cadastro específico\n" +
                    "3: Exclusão de um cadastro específico\n" +
                    "4: Realizar venda do produto\n"+
                    "5: Relatório Pr\n" +
                    "6: Relatório Específico\n" +
                    "7: Relatório Completo\n" +
                    "0: Sair");
            switch (opcoes) {
                case 1:
                    //recebe as informações do produto que vai ser cadastrado.
                    cod = InOut.leString("Codigo: ");
                    nome = InOut.leString("Nome: ");
                    qtd = InOut.leInt("Quantidade em estoque: ");
                    PUC = InOut.leFloat("Preço Unitário de Custo:");
                    PUV = InOut.leFloat("Preço Unitário de Venda:");

                    if(PUC <= 0 || PUV <= 0){
                        //verifica se os valores de precós são maiores que 0.
                        InOut.MsgDeErro("Erro", "Os preços devem ser maiores que zero.");
                        break;
                    }
                    Produtos novoProduto = new Produtos(cod, nome,qtd,PUC,PUV);
                    //alimenta o objeto produto com os dados que foram adquiridos.
                    try{
                        //tenta armazenar o produto na função adicionar produtos
                        estoque.adicionarProdutos(novoProduto);
                        InOut.MsgDeInformacao("Sucesso", "Produto adicionado com sucesso!");
                    }catch(IllegalArgumentException e){
                        //se não for adicionado valores validos é pedido para tentar novamente.
                        InOut.MsgDeErro("Erro","Tente inserir o produto com os dados validos.");
                    }
                    break;
                case 2:
                    //recebe o codigo que necessita ser alterado e faz a alteração na função alterarProduto.
                    String codigoAlterar = InOut.leString("Digite o código do produto a ser alterado: ");
                    int novaQuantidade = InOut.leInt("Digite a nova quantidade em estoque: ");
                    float novoPreco = InOut.leFloat("Digite o novo preço unitário de custo: ");
                    try {
                        //tenta acessar a função com os dados recebidos, se não conseguir emite uma mensagem de erro.
                        estoque.alterarProduto(codigoAlterar, novaQuantidade, novoPreco);
                        InOut.MsgDeInformacao("Sucesso", "Produto alterado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        InOut.MsgDeErro("Erro","Tente inserir o produto com os dados validos.");
                    }
                    break;
                case 3:
                    //recebe o codigo do produto que precisa ser excluido e tenta excluir, se não pede para tentar novamente.
                    String codigoExcluir = InOut.leString("Digite o código do produto a ser excluído: ");
                    try{
                        estoque.excluirProduto(codigoExcluir);
                            InOut.MsgDeInformacao("Sucesso", "Produto excluido com sucesso!");
                    }catch (IllegalArgumentException e){
                        InOut.MsgDeErro("Erro","Aconteceu algum erro ao excluir o produto, tente novamente.");
                    }
                    break;
                case 4:
                    //recebe quais pedidos vai ser vendido e a quantidade enviando para a função vender e faz uma verificação se deu certo a venda.
                    String codigoVenda = InOut.leString("Digite o código do produto a ser vendido:");
                    int quantidadeVenda = InOut.leInt("Digite a quantidade a ser vendida:");
                    Produtos produtoVenda = estoque.relatorioEspecifico(codigoVenda);
                    if (produtoVenda != null) {
                        produtoVenda.vender(quantidadeVenda);
                        InOut.MsgDeInformacao("Venda realizada", "Venda efetuada com sucesso!");
                    }
                    break;
                case 5:
                    //produz um relatorio em ordem crescente.
                    StringBuilder relatorioParcial = new StringBuilder("Relatório Parcial:\n");
                    for (Produtos p : estoque.relatorioPr()) {
                        relatorioParcial.append(p.getCodigo()).append(" - ").append(p.getNome()).append("\n");
                    }
                    InOut.MsgDeInformacao("Relatório Parcial", relatorioParcial.toString());
                    break;
                case 6:
                    //produz um relatorio de um produto especifico atraves de um codigo.
                    String codigoRelatorio = InOut.leString("Digite o código do produto para o relatório específico: ");
                    try {
                        Produtos produtoEspecifico = estoque.relatorioEspecifico(codigoRelatorio);
                        InOut.MsgDeInformacao("Relatório Específico", produtoEspecifico.toString());
                    } catch (IllegalArgumentException e) {
                        InOut.MsgDeErro("Erro", e.getMessage());
                    }
                    break;
                case 7:
                    //faz um relatorio completo de todos os produtos cadastrados com o cálculo do valor total de um determinado
                    //produto, o valor total de venda e o lucro obtido.
                    StringBuilder relatorioCompleto = new StringBuilder("Relatório Completo:\n");
                    for (Produtos p : estoque.relatorioPr()) {
                        relatorioCompleto.append(p.toString()).append("\n");
                    }
                    InOut.MsgDeInformacao("Relatório Completo", relatorioCompleto.toString());
                    break;
                case 0:
                    InOut.MsgDeInformacao("Saindo", "Você saiu do sistema.");
                    break;
                default:
                    if (opcoes != 0) {
                        InOut.MsgDeAviso("Opção Inválida", "Por favor, escolha uma opção válida.");
                    }
                    break;
            }
        } while (opcoes != 0);
    }
}
