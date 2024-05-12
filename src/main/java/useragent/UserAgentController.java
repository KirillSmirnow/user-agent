package useragent;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ua_parser.Parser;

@Controller
public class UserAgentController {

    private final Parser parser = new Parser();

    @GetMapping("/**")
    public String getInfo(HttpServletRequest request, ModelMap modelMap) {
        var userAgentHeader = request.getHeader("User-Agent");
        var client = parser.parse(userAgentHeader);
        var userAgentInfo = UserAgentInfo.builder()
                .userAgent(client.userAgent.family)
                .os(client.os.family)
                .device(client.device.family)
                .ipAddress(request.getRemoteAddr())
                .build();
        modelMap.addAttribute("dataModel", userAgentInfo);
        return "UserAgent";
    }
}
