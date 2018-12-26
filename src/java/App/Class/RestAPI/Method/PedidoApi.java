package App.Class.RestAPI.Method;

import App.Class.RestAPI.Config.Json;
import App.Class.Controllers.FuncionarioController;
import App.Class.Controllers.PedidoController;
import App.Class.Model.Funcionario;
import App.Class.Model.PedidoRequest;
import App.Class.Model.Pedido_Produto;
import App.Class.Util.TrueFalseResponse;
import App.Class.error.ApiError;
import App.Class.error.LibError;
import App.Class.error.PiError;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.ArrayList;
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

@Path("Pedido")
public class PedidoApi {

    @Context
    private UriInfo context;

    public PedidoApi() {
    }

    //@QueryParam("login") String login,
    //@Path("{id}")
    //public Response getUserById(@PathParam("id") String id)
    @Path("buscarComanda")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterComandaDoPedido(@QueryParam("id") int id) throws JsonProcessingException {

        try {

            return Response.status(200).entity(Json.toJson( 
                    new PedidoController().buscarComandaPorPedido(id))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "FuncionarioApi:obterFuncionario",
                    "api para obter dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }
    @Path("buscarPedidos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterPedido(@QueryParam("id") int id) throws JsonProcessingException {

        try {

            return Response.status(200).entity(Json.toJson( 
                    new PedidoController().buscarPedidoPorEstabelecimento(id))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "FuncionarioApi:obterFuncionario",
                    "api para obter dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }
    @Path("cadastrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirPedidoCompleto(String bodyJson) throws IOException {

        try {

            PedidoRequest dados = Json.toObject(bodyJson, PedidoRequest.class);
            
            return Response.status(200).entity(Json.toJson(
                    new TrueFalseResponse(
                            new PedidoController().inserirPedido(dados)))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "PedidoApi:inserirPedido",
                    "api para inserção de dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }
    
    @Path("status")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response mudarStatus(@QueryParam("id") long id) throws JsonProcessingException {

        try {

            return Response.status(200).entity(Json.toJson(new TrueFalseResponse(
                    new PedidoController().mudarStatus(id)))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "FuncionarioApi:excluirFuncionario",
                    "api para excluir dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }
    
    @Path("historico")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarHisorico(@QueryParam("id") int id) throws JsonProcessingException {

        try {

            return Response.status(200).entity(Json.toJson( 
                    new PedidoController().historico(id))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "FuncionarioApi:obterFuncionario",
                    "api para obter dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }
    
    @Path("valor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response valor(@QueryParam("id") int id) throws JsonProcessingException {

        try {

            return Response.status(200).entity(Json.toJson( 
                    new PedidoController().ValorTotal(id))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "FuncionarioApi:obterFuncionario",
                    "api para obter dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }
    
}
