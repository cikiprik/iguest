package com.iguest.mainCtl;


import com.iguest.entity.TdUser;
import com.iguest.service.LoginBean;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;


@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class Login extends SelectorComposer<Component>{
@Wire 
Textbox txtUsername,txtPassword;

@WireVariable(value= "LoginBean")
LoginBean loginBean;
    
@Wire
Button btnLogin;

 Session session = Executions.getCurrent().getDesktop().getSession();
 
    @Override
    public void doAfterCompose(Component component) throws Exception{
        super.doAfterCompose(component);
        System.out.println("MASUK LOGIN");
        if(session.getAttribute("authName") !=null && session.getAttribute("authRole") !=null){
            Executions.sendRedirect("home.zul");
        }
        
    }
    
@Listen("onClick = #btnLogin ")
    public void btnLogin() {
        TdUser tdUser = loginBean.loginCheck(txtUsername.getValue(), txtPassword.getValue());
        if(tdUser!=null){
            // ada user
            // create session
            session.setAttribute("authName", tdUser.getUsername());
            session.setAttribute("authRole", tdUser.getIdJnsUser().getIdJnsUser());
            System.out.println("data:"+tdUser);
            // redirect ke home 
            Executions.sendRedirect("home.zul");
        } else {
            // salah
            Clients.showNotification("Wrong username or password", "error", txtUsername, "end_center", 3000);
        }
                
    }

   
}