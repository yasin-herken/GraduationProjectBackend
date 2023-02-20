package graduationproject.backend.User.aspect;

import graduationproject.backend.User.service.UserDetailsImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAspect {
    private final static String pointcutExpr = "execution(* graduationproject.backend.User.controller.SellerController.*(..))";
    @Before(pointcutExpr)
    public void getSellerInformation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() instanceof UserDetailsImpl userDetails){
            System.out.println(userDetails.getUsername());
        }else {
            throw new IllegalArgumentException("Seller is not authenticated");
        }
    }
}
