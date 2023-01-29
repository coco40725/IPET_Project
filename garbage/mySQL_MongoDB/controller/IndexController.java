package temp.controller;

import com.ipet.web.salon.repositories.SalonAppointmentQueryRepository;
import com.ipet.web.salon.repositories.SalonAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 10:36
 */

@Controller
public class IndexController {
    @RequestMapping("/ipet-back/home")
    public String ipetBackHome(){
        return "backstage/back-index";
    }

    @RequestMapping("/ipet-front/home")
    public String ipetFrontHome(){
        return "frontstage/front-index";
    }

}
