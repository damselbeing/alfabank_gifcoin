package alfabank.gifcoin.controllers;

import alfabank.gifcoin.services.impl.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class AppController {

    @Autowired
    AppService appService;

    @GetMapping("result")
    public ModelAndView getResult() {
        return new ModelAndView("redirect:" + appService.getGif());
    }
}