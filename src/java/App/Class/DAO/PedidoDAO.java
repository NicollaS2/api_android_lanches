package App.Class.DAO;

//Classe DAO Pedido por Usuario
import App.Class.Controllers.UsuarioController;
import App.Class.Model.Estabelecimentos;
import App.Class.Model.Funcionario;
import App.Class.Model.Pedido;
import App.Class.Model.PedidoRequest;
import App.Class.Model.PedidoResponse;
import App.Class.Model.Pedido_Produto;
import App.Class.Model.Preco;
import App.Class.Model.Produto;
import App.Class.Model.TipoFuncionario;
import App.Class.Model.TipoProduto;
import App.Class.Model.Usuario;
import App.Class.error.LibError;
import App.Class.error.PiError;
import App.DataBase.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Responsável pela conexão com o banco de dados
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo
public class PedidoDAO {

    public boolean inserirPedido(PedidoRequest pedidoRequest) throws PiError {
        boolean retorno = false;
        Produto produto = new Produto();
        Pedido_Produto pedidoProduto = new Pedido_Produto();
        Estabelecimentos estabelecimentos = new Estabelecimentos();
        Funcionario funcionario = new Funcionario();
        Pedido pedido = new Pedido();
        int id_pedido = 0;
        
        
        // conversão do objeto para objeto pedido e lista de produtosComanda

        Pedido dadosPedido = pedidoRequest.getPedido();
        ArrayList<Pedido_Produto> lista = pedidoRequest.getListaProdutos();
        
        //chamando metodo inserir usuario 
        Usuario dadosUsuario = new Usuario();
        dadosUsuario.setTelefone(dadosPedido.getUsuario().getTelefone());
        dadosUsuario.setNome(dadosPedido.getUsuario().getNome());
        dadosUsuario.setEmail(dadosPedido.getUsuario().getTelefone());// email pode ser o telefone para usuarios (padrao)
        dadosUsuario.setSenha("123");// senha padrao ja definida para usuarios

        int id_usuario = new UsuarioController().inserirUsuario(dadosUsuario);
        try {
            //inserir um pedido na tabela pedido
            Database base = new Database();
            String sql_pedido = "INSERT INTO pedido(data_pedido,id_usuario,id_estabelecimento) VALUES(NOW(),?,?)";

            base.setQuerySql(sql_pedido);
            base.setQueryParameter().setInt(1, id_usuario);
            base.setQueryParameter().setInt(2, dadosPedido.getEstabelecimentos().getId_estabelecimentos());
            base.preStatement.executeUpdate();
            ResultSet resultado = base.preStatement.getGeneratedKeys();
            
            //caso o pedido seja inserido com sucesso
            //entra na condição 
            if (resultado.first()) {
                id_pedido = resultado.getInt(1);
                retorno = true;
                
                //loop para inserir os produtos na comanda.
                for(Pedido_Produto ped : lista){
                    
                    Pedido objeto = new Pedido();
                    objeto.setId_pedido(id_pedido);//setando o id do pedido total
                    ped.setPedido(objeto);
                    InserirUmNovoPedidoTabelaPedido_Produto(ped); //metodo para inserir na comanda
                    
                }

            }

        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "PedidoDAO:inserirPedido",
                    "lógica de negócio - inserção de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "PedidoDAO:inserirPedido",
                    "lógica de negócio - busca de dados");
            throw error;
        }

        return retorno;

    }

    public boolean InserirUmNovoPedidoTabelaPedido_Produto(Pedido_Produto dadosPedido) throws PiError {
        boolean retorno = false;

        try {
            //inserir um produto novo no pedido
            Database base2 = new Database();
            String sql_pedidoProduto = "INSERT INTO pedido_produto(quantidade,observacoes,id_pedido,id_produto,id_funcionario,id_status) VALUES(?,?,?,?,?,?)";

            base2.setQuerySql(sql_pedidoProduto);
            base2.setQueryParameter().setInt(1, dadosPedido.getQuantidade());
            base2.setQueryParameter().setString(2, dadosPedido.getObservacoes());
            base2.setQueryParameter().setInt(3, dadosPedido.getPedido().getId_pedido());
            base2.setQueryParameter().setInt(4, dadosPedido.getProduto().getId_produto());
            base2.setQueryParameter().setInt(5, dadosPedido.getFuncionario().getId_funcionario());
            base2.setQueryParameter().setInt(6, dadosPedido.getStatus().getId_status());

            int num = base2.preStatement.executeUpdate();
            if (num == 1) {
                retorno = true;
            }

        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "PedidoDAO:inserirProdutosNaComanda",
                    "lógica de negócio - inserção de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "PedidoDAO:inserirProdutosNaComanda",
                    "lógica de negócio - inserção de dados");
            throw error;
        }

        return retorno;
    }
    
    public ArrayList<PedidoResponse> buscarComandaPorPedido(int id) throws PiError{
    
        ArrayList<PedidoResponse> lista = new ArrayList<>();
        try {
            Database base = new Database();
            String sql = "";

            sql = "SELECT * FROM pedido_produto AS pd "
                    + "INNER JOIN pedido as p ON pd.id_pedido = p.id_pedido "
                    + "INNER JOIN estabelecimento as e ON e.id_estabelecimento = p.id_estabelecimento "
                    + "INNER JOIN usuario as u ON p.id_usuario = u.id_usuario "
                    + "INNER JOIN funcionario as f ON pd.id_funcionario = f.id_funcionario "
                    + "INNER JOIN produto as pr ON pr.id_produto = pd.id_produto "
                    + "INNER JOIN preco as pc ON pr.id_preco = pc.id_preco "
                    + "INNER JOIN tipo_produto as tp ON tp.id_tipo = pr.id_tipo "                 
                    + "WHERE p.id_pedido = ?";

            base.setQuerySql(sql);
            base.setQueryParameter().setInt(1, id);


            ResultSet resultado = base.preStatement.executeQuery();
            
            //loop para inserir dados do banco na lista
            while (resultado.next()) {
                
                
                PedidoResponse pedido = new PedidoResponse();

                pedido.setData_pedido(resultado.getString("p.data_pedido"));
                pedido.setIdFuncionario(resultado.getInt("f.id_funcionario"));
                pedido.setId_pedido(resultado.getInt("p.id_pedido"));
                pedido.setIdProduto(resultado.getInt("pr.id_produto"));
                pedido.setDescricaoProduto(resultado.getString("pr.descricao"));
                pedido.setValor(resultado.getDouble("pc.valor"));
                pedido.setNomeProduto(resultado.getString("pr.nome"));
                pedido.setId_tipo(resultado.getInt("tp.id_tipo"));
                pedido.setDescricao(resultado.getString("pd.observacoes"));
               

                lista.add(pedido);
                
               
            }
            base.close();
        }catch(PiError error){
            throw error;
            
        }catch(SQLException e){
            PiError error = new PiError(LibError.codeEP04, 
                    LibError.msgEP04 + e.getMessage(), 
                    "PedidoDAO:buscarPedidos", 
                    "lógica de negócio - busca de dados");
            throw error;
            
        }catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999, 
                    LibError.msgEP999 + e.getMessage(), 
                    "PedidoDAO:buscarPedidos", 
                    "lógica de negócio - busca de dados");
            throw error;
        }

        return lista;
    }
    public ArrayList<Pedido> buscarPedidosPorEstabelecimento(int id) throws PiError{
    
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            Database base = new Database();
            String sql = "";

            sql = "SELECT * FROM pedido AS p "
                    + "INNER JOIN estabelecimento as e ON e.id_estabelecimento = p.id_estabelecimento "
                    + "INNER JOIN usuario as u ON p.id_usuario = u.id_usuario "
                    + "WHERE e.id_estabelecimento = ? and p.data_pedido BETWEEN TIMESTAMP(curdate(), '00:00:00') AND TIMESTAMP(curdate(), '23:59:59') and p.status = 1;";

            base.setQuerySql(sql);
            base.setQueryParameter().setInt(1, id);


            ResultSet resultado = base.preStatement.executeQuery();
            
            //loop para inserir dados do banco na lista
            while (resultado.next()) {
                Pedido pedido = new Pedido();
                Usuario usuario = new Usuario();
                
                usuario.setNome(resultado.getString("u.nome"));
                
                pedido.setId_pedido(resultado.getInt("p.id_pedido"));
                pedido.setData_pedido(resultado.getString("p.data_pedido"));
                pedido.setUsuario(usuario);
                
                lista.add(pedido);
                
               
            }
            base.close();
        }catch(PiError error){
            throw error;
            
        }catch(SQLException e){
            PiError error = new PiError(LibError.codeEP04, 
                    LibError.msgEP04 + e.getMessage(), 
                    "PedidoDAO:buscarPedidos", 
                    "lógica de negócio - busca de dados");
            throw error;
            
        }catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999, 
                    LibError.msgEP999 + e.getMessage(), 
                    "PedidoDAO:buscarPedidos", 
                    "lógica de negócio - busca de dados");
            throw error;
        }

        return lista;
    }
    
    
    public boolean MudarStatus(long dado) throws PiError {

        boolean resposta = false;
        try {
            Database base = new Database();

            String sql = "UPDATE `pedido` SET `status` = 6"
                    + " WHERE `id_pedido` = ?";

            base.setQuerySql(sql);
            base.setQueryParameter().setLong(1, dado);
            

            base.preStatement.executeUpdate();
            ResultSet resultado = base.preStatement.getGeneratedKeys();

            if (resultado.first()) {
                resposta = true; 
            }
            base.close();
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "PedidoDAO:MudarStatus",
                    "lógica de negócio - inserção de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "PedidoDAO:MudarStatus",
                    "lógica de negócio - inserção de dados");
            throw error;
        }

        return resposta;
    }
    
    public ArrayList<String> produtosMaisVendidos(int id) throws PiError{
    
        ArrayList<String> lista = new ArrayList<>();
        try {
            Database base = new Database();
            String sql = "";

            sql = "select count(*) as nvezes, p.nome from produto as p "
                    +"inner join pedido_produto as pp on pp.id_produto = p.id_produto "
                    +"inner join pedido as ped on ped.id_pedido = pp.id_pedido "
                    +"inner join estabelecimento as e on e.id_estabelecimento = ped.id_estabelecimento "
                    +"where (ped.status = 6) and (ped.id_estabelecimento = ?) and (ped.data_pedido BETWEEN TIMESTAMP(curdate(), '00:00:00') AND TIMESTAMP(curdate(), '23:59:59')) "
                    +"GROUP BY nome ORDER BY nvezes DESC ; ";

            base.setQuerySql(sql);
            base.setQueryParameter().setInt(1, id);


            ResultSet resultado = base.preStatement.executeQuery();
            
            //loop para inserir dados do banco na lista
            while (resultado.next()) {
                String produto = "Produto: "+resultado.getString("p.nome")+" | Nº Vendas: "+ resultado.getInt("nvezes");
               
                lista.add(produto);

            }
            base.close();
        }catch(PiError error){
            throw error;
            
        }catch(SQLException e){
            PiError error = new PiError(LibError.codeEP04, 
                    LibError.msgEP04 + e.getMessage(), 
                    "PedidoDAO:buscarPedidos", 
                    "lógica de negócio - busca de dados");
            throw error;
            
        }catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999, 
                    LibError.msgEP999 + e.getMessage(), 
                    "PedidoDAO:buscarPedidos", 
                    "lógica de negócio - busca de dados");
            throw error;
        }

        return lista;
    }
    
    public String ValorTotal(int id) throws PiError{
    
        String lista = null;
        try {
            Database base = new Database();
            String sql = "";

            sql = "select sum(p.valor) as v_vendas from preco as p "
                    +"inner join produto as pro on pro.id_preco = p.id_preco "
                    +"inner join pedido_produto as pp on pp.id_produto = pro.id_produto "
                    +"inner join pedido as pe on pe.id_pedido = pp.id_pedido "
                    +"inner join estabelecimento as e on e.id_estabelecimento = pe.id_estabelecimento "
                    +"where e.id_estabelecimento = ? and (pe.status = 6) and pe.data_pedido BETWEEN TIMESTAMP(curdate(), '00:00:00') AND TIMESTAMP(curdate(), '23:59:59')";

            base.setQuerySql(sql);
            base.setQueryParameter().setInt(1, id);


            ResultSet resultado = base.preStatement.executeQuery();
            
            //loop para inserir dados do banco na lista
            if (resultado.next()) {
                lista = "Valor Total Vendas: R$"+resultado.getDouble("v_vendas");           
            }
            base.close();
        }catch(PiError error){
            throw error;
            
        }catch(SQLException e){
            PiError error = new PiError(LibError.codeEP04, 
                    LibError.msgEP04 + e.getMessage(), 
                    "PedidoDAO:buscarPedidos", 
                    "lógica de negócio - busca de dados");
            throw error;
            
        }catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999, 
                    LibError.msgEP999 + e.getMessage(), 
                    "PedidoDAO:buscarPedidos", 
                    "lógica de negócio - busca de dados");
            throw error;
        }

        return lista;
    }

}
