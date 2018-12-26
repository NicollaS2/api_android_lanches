package App.Class.RestAPI.Method;

import App.Class.RestAPI.Config.Json;
import App.Class.Controllers.ProdutoController;
import App.Class.Model.Estabelecimentos;
import App.Class.Model.Preco;
import App.Class.Model.Produto;
import App.Class.Model.TipoProduto;
import App.Class.Util.TrueFalseResponse;
import App.Class.error.ApiError;
import App.Class.error.LibError;
import App.Class.error.PiError;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("Produtos")
public class ProdutosApi {

    @Context
    private UriInfo context;

    public ProdutosApi() {
    }

    //@QueryParam("login") String login,
    //@Path("{id}")
    //public Response getUserById(@PathParam("id") String id)
    @Path("buscar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterProdutos(@QueryParam("id") int idEstabelecimento) throws JsonProcessingException {

        try {
            
            return Response.status(200).entity(Json.toJson(new ProdutoController().buscarListaDeProdutosSimplificada(idEstabelecimento))).build();
        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "ProdutoApi:obterProduto",
                    "api para obter dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }
    
    @Path("buscarporid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterProdutoporid(@QueryParam("id") long id) throws JsonProcessingException {

        try {
            
            return Response.status(200).entity(Json.toJson(new ProdutoController().buscarProdutoId(id))).build();
        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "ProdutoApi:obterProduto",
                    "api para obter dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }

    @Path("cadastrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirProdutos(String bodyJson) throws IOException {

        try {

            Produto dados = Json.toObject(bodyJson, Produto.class);

            return Response.status(200).entity(Json.toJson(new TrueFalseResponse(
                    new ProdutoController().inserirProdutos(dados)))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "ProdutoApi:inserirProdutos",
                    "api para inserção de dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }
    
    
    @Path("excluir")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirProduto(@QueryParam("id") long id) throws JsonProcessingException {

        try {

            return Response.status(200).entity(Json.toJson(new TrueFalseResponse(
                    new ProdutoController().excluirProdutos(id)))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "ProdutosApi:excluirFuncionario",
                    "api para excluir");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }

}
