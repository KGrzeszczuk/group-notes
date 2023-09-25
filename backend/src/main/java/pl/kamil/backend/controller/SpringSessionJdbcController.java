package pl.kamil.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class SpringSessionJdbcController {

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        List<String> favoriteColors = getFavColors(session);
        model.addAttribute("favoriteColors", favoriteColors);
        model.addAttribute("sessionId", session.getId());
        return "index";
    }

    @PostMapping("/saveColor")
    public String saveMessage(@RequestParam("color") String color,
            HttpServletRequest request) {
            System.out.print("Test1");

        List<String> favoriteColors = getFavColors(request.getSession());
        if (StringUtils.hasLength(color)) {
            favoriteColors.add(color);
                        System.out.print("2");

            System.out.print(favoriteColors);
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