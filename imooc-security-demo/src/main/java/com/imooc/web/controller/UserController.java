package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQuertCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhupeiyou
 * @since 2020-07-01 22:56
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    public ResponseEntity<User> regist(User user, HttpServletRequest request){
        String username = user.getUsername();
        providerSignInUtils.doPostSignUp(username,new ServletWebRequest(request));

        return ResponseEntity.ok(user);
    }

    @GetMapping("/authentication")
    public ResponseEntity<Authentication> getCurrentUser(Authentication authentication){
        return ResponseEntity.ok(authentication);
    }
    @GetMapping("/principal")
    public ResponseEntity<UserDetails> getCurrentUserAboutPrincipal(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(userDetails);
    }
    @PutMapping("/{id:\\d+}")
    public User update (@Valid  @RequestBody User user, BindingResult errors) {
        if (errors !=null && errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {

                FieldError fieldError = (FieldError) error;
                System.out.println(fieldError.getField() + " : " +error.getDefaultMessage());
            });
        }

        System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        return user;
    }

    @PostMapping
    //public User create(@Valid  @RequestBody User user, BindingResult errors) {
    public User create(@Valid  @RequestBody User user) {
        /*if (errors !=null && errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                String objectName = error.getObjectName();
                System.out.println(objectName + " : " +error.getDefaultMessage());
            });
        }*/

        System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        return user;
    }

    @JsonView(User.UserSimpleView.class)
    @GetMapping
    public List<User> query(UserQuertCondition condition, @PageableDefault(page = 2,size = 17,sort = "username,asc") Pageable pageable){

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @JsonView(User.UserDetailView.class)
    @GetMapping("/{id:\\d+}")
    public User getInfo(@PathVariable String id){
        User user = new User();
        user.setUsername("tom");
        user.setId(id);
        return user;
    }
}
