package pl.kamil.backend.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class SpringSessionAtributeTestController {

    @GetMapping("/public/token")
    public Map<String, String> token(HttpSession session, Principal principal) {
        System.out.println(session.isNew());
        System.out.println(session.getId());
        if (principal != null)
            System.out.println(principal.getName());
        else
            System.out.println("pusty principal");
        return Collections.singletonMap("public/token", session.getId());
    }

    @GetMapping("/private/token")
    public Map<String, String> token2(HttpSession session) {
        return Collections.singletonMap("private/token", session.getId());
    }

    @GetMapping("/colors")
    public String index(Model model, HttpSession session) {
        List<String> favoriteColors = getFavColors(session);
        model.addAttribute("favoriteColors", favoriteColors);
        model.addAttribute("sessionId", session.getId());

        return new Gson().toJson(model);
    }

    @PostMapping("/colors")
    public String saveMessage(@RequestParam("color") String color,
            HttpServletRequest request) {

        List<String> favoriteColors = getFavColors(request.getSession());
        if (StringUtils.hasLength(color)) {
            favoriteColors.add(color);

            request.getSession().setAttribute("favoriteColors", favoriteColors);
        }
        return "redirect:/";
    }

    @SuppressWarnings(value = "unchecked")
    private List<String> getFavColors(HttpSession session) {
        List<String> favoriteColors = (List<String>) session
                .getAttribute("favoriteColors");

        if (favoriteColors == null) {
            favoriteColors = new ArrayList<>();
        }
        return favoriteColors;
    }
}