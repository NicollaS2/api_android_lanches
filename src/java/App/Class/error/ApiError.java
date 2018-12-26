package App.Class.error;


public class ApiError {
    
    private String code;
    private String msgErro;
    private String applicationName;
    private String organizationName;

    public ApiError() {
    }

    public ApiError(String code, String msgErro, String applicationName, String organizationName) {
        this.code = code;
        this.msgErro = msgErro;
        this.applicationName = applicationName;
        this.organizationName = organizationName;
    }

    
    public ApiError(PiError piError) {
        this.code = piError.getCode();
        this.msgErro = piError.getMsgErro();
        this.applicationName = piError.getApplicationName();
        this.organizationName = piError.getOrganizationName();
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
