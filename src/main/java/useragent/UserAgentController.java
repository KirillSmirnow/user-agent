package useragent;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ua_parser.Parser;

@Controller
@RequiredArgsConstructor
public class UserAgentController {

    private final UserAgentInfoRepository userAgentInfoRepository;

    private final Parser parser = new Parser();

    @GetMapping("/**")
    public String getInfo(HttpServletRequest request, ModelMap modelMap) {
        var userAgentHeader = request.getHeader("User-Agent");
        var client = parser.parse(userAgentHeader);
        var userAgentInfo = UserAgentInfo.builder()
                .userAgentHeader(userAgentHeader)
                .userAgent(client.userAgent != null ? client.userAgent.family : null)
                .os(client.os != null ? client.os.family : null)
                .device(client.device != null ? client.device.family : null)
                .ipAddress(request.getRemoteAddr())
                .build();
        userAgentInfoRepository.save(userAgentInfo);
        modelMap.addAttribute("dataModel", userAgentInfo);
        return "UserAgent";
    }
}
