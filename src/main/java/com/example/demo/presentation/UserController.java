package com.example.demo.presentation;

import com.example.demo.application.form.UserForm;
import com.example.demo.application.usecase.UserAuthUsercase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserAuthUsercase userAuthUsercase;

    @GetMapping
    public ModelAndView loginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("user/login");
        modelAndView.addObject("userForm", new UserForm());

        return modelAndView;
    }

    /**
     * 登録ページの表示
     * @return
     */
    @GetMapping("/signup")
    public ModelAndView signup(ModelAndView modelAndView) {
        modelAndView.setViewName("user/signup");
        modelAndView.addObject("userForm", new UserForm());

        return modelAndView;
    }
    /**
     * 登録ページの表示
     * @param userForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/signup")
    public ModelAndView register(
         @Validated @ModelAttribute UserForm userForm,
         BindingResult bindingResult,
         HttpServletRequest request
    ) {
        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView(("user/signup"));
            modelAndView.addObject("userForm", userForm);
            return modelAndView;
        }
        try {
            userAuthUsercase.userCreate(userForm, request);
        } catch (Exception e) {
            log.error("ユーザ作成 or ログイン失敗", e);
            return new ModelAndView("redirect:/user");
        }

        //TODO: ユーザ作成処理

        return new ModelAndView("redirect:/board");
    }


}
