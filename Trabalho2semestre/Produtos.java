package Trabalho2semestre;

import java.util.ArrayList;
import java.util.List;

public class Produtos {
    //Utilizando o private para não ter o valor alterado por outra variavel.
    private String Nome;
    private String Codigo;
    private float PUC, PUV;
    /*
    PCU- Preço unitario Custo
    PUV - Preço Unitário Venda
    */
    private int qtd_Estoque;
    private int qtd_Vendida;

    private static final List<Produtos> lista_produtos= new ArrayList<>();
    //criando uma lista para armazenar os produtos

    public Produtos(String cod, String nome, int qtd, float puc, float puv) {
        //armazenando dentro de um construtor
        //o this referencia como um atributo da classe desejada e o outro é o parametro de referência do construtor.
        this.Codigo = cod;
        this.Nome = nome;
        this.PUC = puc;
        this.PUV = puv;
        this.qtd_Estoque = qtd;
        this.qtd_Vendida = 0;
    }

    public static void adicionarProduto(Produtos produtos){
        lista_produtos.add(produtos);
    }
    //Serve para criar produtos recebendo o produto e o armazenando na lista produtos.
    public static List<Produtos> getListaProdutos(){
        return lista_produtos;
    }
    //permite a consulta do que esta armazenado na lista de produtos
    public String getCodigo() {
        return Codigo;
    }
    //serve para dar acesso ao codigo de determinado produto.
    public String getNome() {
        return Nome;
    }
    //serve para dar acesso ao Nome de determinado produto.
    public int getQtdEstoque() {
        return qtd_Estoque;
    }
    //serve para dar acesso a quantidade que tem em estoque de determinado produto.
    public void setQuantidadeEstoque(int quantidadeEstoque){this.qtd_Estoque = quantidadeEstoque;}
    //serve para dar permissão de alteração de quantidade de determinado produto que tem em estoque.
    public float getPUC() {
        return PUC;
    }
    //serve para dar acesso ap preço unitario de compra de determinado produto.
    public float getPUV() {
        return PUV;
    }
    //serve para dar acesso ap preço unitario de venda de determinado produto.
    public void setPUV(float PUV){this.PUV= PUV;}

    public void vender(int quantidade){
        //verificaz se a quantidade requisitada e menor do que a quantidade que tem no estoque, após isso diminui a quantidade requisitada do estoque.
        if(quantidade <= qtd_Estoque){
            qtd_Estoque -= quantidade;
            qtd_Vendida += quantidade;
        }else{
            InOut.MsgDeErro("Erro","Quantidade vendida maior que a quantidade em estoque.");
        }
    }
    public float calcularValorTotal(){
        //calcula o valor total que se tem de mercadoria ainda em estoque.
        return qtd_Estoque * PUC;
    }
    public float calcularValorTotalVenda(){
        //calcula quanto de determinado produto já foi vendida.
        return qtd_Vendida * PUV;
    }
    public float calcularLucro(){
        //calcula quanto de lucro que determinado produto deu.
        return calcularValorTotalVenda() - (qtd_Vendida * PUC);
    }
    public String toString(){
        //É chamado nos relatórios para mostrar nesse formato todas as informções requisitadas.
        return"Código: "+Codigo+"\n"+
        "Nome: " +Nome+"\n"+
        "Quantidade em Estoque"+qtd_Estoque+"\n"+
        "Preço Unitário de Custo:"+PUC+"\n"+
        "Preço Unitaŕio de Venda: "+PUV+"\n"+
        "Quantidade Vendida: "+qtd_Vendida+"\n"+
        "Lucro:" +calcularLucro()+"\n";
    }
}
