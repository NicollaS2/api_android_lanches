package App.Class.Session;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageMB {

    private String object;
    private String textMessage;
    private String textDetail;
    private int tipo;

    public MessageMB(String object, String textMessage, String textDetail, int tipo) {
        this.object = object;
        this.textMessage = textMessage;
        this.textDetail = textDetail;
        this.tipo = tipo;

        FacesMessage facesMessage;

        switch (tipo) {
            case 1:
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, textMessage, textDetail);
                break;

            case 2:
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, textMessage, textDetail);
                break;

            case 3:
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, textMessage, textDetail);
                break;

            case 4:
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, textMessage, textDetail);
                break;

            default:
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, textMessage, textDetail);

        }

        FacesContext.getCurrentInstance().addMessage(object, facesMessage);

    }

}
