/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Class.RestAPI.Config;

import App.Class.Controllers.EstabelecimentosController;
import App.Class.Controllers.FuncionarioController;
import App.Class.Controllers.ProdutoController;
import App.Class.Model.Estabelecimentos;
import App.Class.Model.Funcionario;
import App.Class.Model.Preco;
import App.Class.Model.Produto;
import App.Class.Model.ProdutoResponse;
import App.Class.Model.TipoFuncionario;
import App.Class.Model.TipoProduto;
import App.Class.error.PiError;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 *
 * @author pc
 */
public class Teste {
    public static void main(String[] args) throws JsonProcessingException, PiError{
//        Produto produto = new Produto(0, "descricao", new Preco()
//                , new TipoProduto(), new Estabelecimentos());
//        produto.setDescricao("");



///------------------------------------------------------------
//        PedidoRequest pr = new PedidoRequest();
//        
//        Pedido pedido = new Pedido();
//        pedido.setEstabelecimentos( new Estabelecimentos(1, "", ""));
//        pedido.setUsuario( new Usuario(1, "", null, null, null));
//        pr.setPedido(pedido);
//        
//        Pedido_Produto dados = new Pedido_Produto();
//        ArrayList<Pedido_Produto> lista = new ArrayList<>();
//        dados.setFuncionario(new Funcionario(1, null, null, null,new Estabelecimentos(), new TipoFuncionario(), "", ""));
//        dados.setObservações("nummm  ddd");
//        
//        dados.setProduto(new Produto(1, "", new Preco(), new TipoProduto(),  new Estabelecimentos()));
//        dados.setQuantidade(1);
//        dados.setStatus(new Status(1, ""));
//        lista.add(dados);
//        pr.setListaProdutos(lista);

//--------------------------------------------------------------

//          f.setEstabelecimento(new Estabelecimentos(1, "", ""));
//          f.setTipoFuncionario(new TipoFuncionario(1, ""));
////        
////        System.out.println(Json.toJson(f));
//          TipoProduto tipo = new TipoProduto();
//          Estabelecimentos est = new Estabelecimentos();
//          Preco pre = new Preco();  
//          Produto produtos= new Produto();
//          produtos.setDescricao("descrição");
//          produtos.setNome("cudojao");
//          produtos.setTipoProduto(tipo);
//          produtos.setEstabelecimentos(est);
//          produtos.setPreco(pre);
//               
//
//            Funcionario funcionario = new Funcionario();
//
////            Usuario us = new Usuario(0, "morgan", "teste", "123", "955955");
//              funcionario.setNome("testestse");
//                funcionario.setEmail("lucas");
//                funcionario.setSenha("123");
//                
//            funcionario.setImg("");
//            funcionario.setEstabelecimento(new Estabelecimentos(1, "", ""));
//            funcionario.setTipoFuncionario(new TipoFuncionario(2, ""));
            
            
              ProdutoResponse produto = new ProdutoResponse();
              produto.setDescricao("testetestes");
              produto.setNome("teste novo");
              produto.setEstabelecimentos(1);
              produto.setTipoProduto(1);
              produto.setPreco(2);

              

            System.out.println(Json.toJson(new ProdutoController().buscarListaDeProdutosSimplificada(1)));
            
          
            
    }
}
