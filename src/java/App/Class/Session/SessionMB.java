
package App.Class.Session;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionMB {
    
    private HttpSession session;
    

    public SessionMB() {
        this.session  = (HttpSession)  FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public HttpSession getSession() {
        return session;
    }
    
    public Object getAttribute(String name){
        return session.getAttribute(name);
    }
    
    public void setAttribute (String name, Object value){
        session.setAttribute(name, value);
    }
    
    
   
    
    
}
