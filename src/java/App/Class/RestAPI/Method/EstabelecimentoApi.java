package App.Class.RestAPI.Method;

import App.Class.RestAPI.Config.Json;
import App.Class.Controllers.EstabelecimentosController;
import App.Class.Model.Funcionario;
import App.Class.error.ApiError;
import App.Class.error.LibError;
import App.Class.error.PiError;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("Estabelecimento")
public class EstabelecimentoApi {

    @Context
    private UriInfo context;

    public EstabelecimentoApi() {
    }

    @Path("inserir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirEstabelecimento(String bodyJson) throws IOException {

        try {

            Funcionario dados = Json.toObject(bodyJson, Funcionario.class);

            return Response.status(200).entity(Json.toJson(new EstabelecimentosController().inserirEstabelecimento(dados))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "EstabelecimentoApi:inserirEstabelecimento e funcionario master",
                    "api para inserção de dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();
        }

    }

}
