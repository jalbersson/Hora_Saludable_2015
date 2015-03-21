
package auxiliar;

import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class PasswordView {
     
    private String password1;  
    private String password2;  
   
    private String password5;
 
    public String getPassword1() {
        return password1;
    }
 
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
 
    public String getPassword2() {
        return password2;
    }
 
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
 
   
    public String getPassword5() {
        return password5;
    }
 
    public void setPassword5(String password5) {
        this.password5 = password5;
    }  
}
