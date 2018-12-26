package App.Class.Session;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


public class RedirectMB {
    
    private String url;

    public RedirectMB(String url) {
        try {
            this.url = url;
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + url);
            
        } catch (IOException error) {
            System.out.println("erro no redirecionamento do site" + error);
        }
    }
  
    
}
