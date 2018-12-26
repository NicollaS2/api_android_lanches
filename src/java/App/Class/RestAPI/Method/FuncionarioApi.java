package App.Class.RestAPI.Method;

import App.Class.RestAPI.Config.Json;
import App.Class.Controllers.FuncionarioController;
import App.Class.Model.Funcionario;
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

@Path("Funcionario")
public class FuncionarioApi {

    @Context
    private UriInfo context;

    public FuncionarioApi() {
    }

    //@QueryParam("login") String login,
    //public Response getUserById(@PathParam("id") String id)
    @Path("buscar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterFuncionariosPorEstabelecimento(@QueryParam("id") int id) throws JsonProcessingException {

        try {

            return Response.status(200).entity(Json.toJson(
                    new FuncionarioController().buscarFuncionariosPorEstabelecimento(id))).build();

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
    public Response inserirFuncionarios(String bodyJson) throws IOException {

        try {

            Funcionario dados = Json.toObject(bodyJson, Funcionario.class);
            int retorno = new FuncionarioController().inserirFuncionario(dados);
            boolean resposta = false;
            if (retorno != 0) {
                resposta = true;
            }
            return Response.status(200).entity(Json.toJson(new TrueFalseResponse(resposta))).build();

        } catch (PiError error) {
//            ApiError apierror = new ApiError(error);
//            return Response.status(500).entity(Json.toJson(apierror)).build();
            boolean result = false;
            return Response.status(500).entity(Json.toJson(new TrueFalseResponse(result))).build();

        } catch (Exception e) {
           /* PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "FuncionariosApi:inserrirFuncionario",
                    "api para inserção de dados");
            ApiError apierror = new ApiError(error); */
            boolean result = false;
            return Response.status(500).entity(Json.toJson(new TrueFalseResponse(result))).build();
        }

    }
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginFuncionarios(String bodyJson) throws IOException {

        try {

            Funcionario dados = Json.toObject(bodyJson, Funcionario.class);
            return Response.status(200).entity(Json.toJson((new FuncionarioController().loginFuncionarios(dados)))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "FuncionarioApi:loginFuncionario",
                    "api para autenticação de usuario");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }
    
    
    //@QueryParam("login") String login,
    //public Response getUserById(@PathParam("id") String id)
    @Path("excluir")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirFuncionario(@QueryParam("id") long id) throws JsonProcessingException {

        try {

            return Response.status(200).entity(Json.toJson(new TrueFalseResponse(
                    new FuncionarioController().excluirFuncionarios(id)))).build();

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

}
