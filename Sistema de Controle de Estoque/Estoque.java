package Trabalho2semestre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Estoque {
    private List<Produtos> produto;
    //Cria uma lista para guardar os produtos.
    public Estoque(){
        produto = new ArrayList<>();
    }
    //É um construtor que serve para criar um novo objeto.

    public void adicionarProdutos(Produtos produtos) {
        //serve para adicionar determinado produto e antes faz uma verificação se esse codigo do produto está disponivel e se já atingiu a quantidade maxima permitida de cadastros.
        if (produto.size() >= 48) {
            InOut.MsgDeErro("Erro", "Limite máximo de 48 produtos atingido.");
            return;
        }
        for (Produtos p : produto){
            if(p.getCodigo().equals(produtos.getCodigo())){
                InOut.MsgDeErro("Erro:","Produto com o mesmo código já existe.");
                return;
            }
        }
        produto.add(produtos);
    }
    public void alterarProduto(String codigo, int novaQTD, float novoPreco){
        //serve para alterar determinado produto.
        for (Produtos p : produto){
            if(p.getCodigo().equals(codigo)){
                p.setQuantidadeEstoque(novaQTD);
                p.setPUV(novoPreco);
                return;
            }
        }
        InOut.MsgDeErro("Erro","Produto não encontrado.");
    }
    public void excluirProduto(String codigo){
        //remove determinado produto da lista.
        produto.removeIf(p->p.getCodigo().equals(codigo));
    }

    public List<Produtos> relatorioPr() {
        //compara e traz os produtos na ordem crescente.
        Collections.sort(produto, Comparator.comparing(Produtos::getCodigo));
        return produto;
    }
    public Produtos relatorioEspecifico(String codigo){
        //traz todos os dados de determinado produto.
        for (Produtos p: produto){
            if (p.getCodigo().equals(codigo)){
                return p;
            }
        }
        InOut.MsgDeErro("Erro", "Produto não encontrado.");
        return null;
    }
    public void relatorioCompleto() {
        //Traz todos os dados de todos os produtos cadastrados.
        if (produto.isEmpty()) {
            InOut.MsgDeInformacao("Relatório Completo", "Nenhum produto cadastrado.");
            return;
        }

        StringBuilder relatorio = new StringBuilder("Relatório Completo:\n\n");
        //É melhor utilizar ele pois vai ter muitos textos longos.

        for (Produtos p : produto) {
            relatorio.append("Código: ").append(p.getCodigo()).append("\n")
                    //o apend é melhor que o + com string pois tem esta sendo utilizado diversos textos grandes.
                    .append("Nome: ").append(p.getNome()).append("\n")
                    .append("Quantidade em Estoque: ").append(p.getQtdEstoque()).append("\n")
                    .append("Preço Unitário de Custo: R$ ").append(p.getPUC()).append("\n")
                    .append("Preço Unitário de Venda: R$ ").append(p.getPUV()).append("\n")
                    .append("Valor Total em Estoque: R$ ").append(p.calcularValorTotal()).append("\n")
                    .append("Valor Total de Venda: R$ ").append(p.calcularValorTotalVenda()).append("\n")
                    .append("Lucro Obtido: R$ ").append(p.calcularLucro()).append("\n")
                    .append("----------------------------------------\n");
        }

        InOut.MsgDeInformacao("Relatório Completo", relatorio.toString());
    }
}
