import org.springframework.beans.factory.InitializingBean;

public class ForumStrings implements InitializingBean {
    private String LogginSucces;
    private String invalidLoggin;

    public ForumStrings(String logginSucces, String invalidLoggin) {
        LogginSucces = logginSucces;
        this.invalidLoggin = invalidLoggin;
    }



    public String getLogginSucces() {
        return LogginSucces;
    }

    public void setLogginSucces(String logginSucces) {
        LogginSucces = logginSucces;
    }

    public String getInvalidLoggin() {
        return invalidLoggin;
    }

    public void setInvalidLoggin(String invalidLoggin) {
        this.invalidLoggin = invalidLoggin;
    }

    @Override
    public String toString() {
        return "ForumStrings{" +
                "LogginSucces='" + LogginSucces + '\'' +
                ", invalidLoggin='" + invalidLoggin + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
