package siw.stockfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import siw.stockfinder.model.User;
import siw.stockfinder.service.UserService;

@ControllerAdvice
public class GlobalController {
    @Autowired
    private UserService userService;
    @ModelAttribute("userAuthDetails")
    public UserDetails getUserAuthDetails() {
        UserDetails user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }
    @ModelAttribute("globalUser")
    public User getUser() {
        return userService.getCurrentUser();
    }
}
