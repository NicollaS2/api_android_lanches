package App.Class.error;


public class PiError extends Exception{

    
    private String code;
    private String msgErro;
    private String applicationName;
    private String organizationName;
    
    
    public PiError() {
    }

 
    public PiError(String code, String msgErro, String applicationName, String organizationName) {
        super(msgErro);
        this.code = code;
        this.msgErro = msgErro;
        this.applicationName = applicationName;
        this.organizationName = organizationName;
    }

 

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsgErro() {
        return msgErro;
    }

    public void setMsgErro(String msgErro) {
        this.msgErro = msgErro;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    
    
    
}
