package useragent;

import com.maxmind.geoip2.DatabaseReader;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ua_parser.Parser;

import java.net.InetAddress;

@Controller
@RequiredArgsConstructor
public class UserAgentController {

    private final UserAgentInfoRepository userAgentInfoRepository;

    private final Parser parser = new Parser();
    private DatabaseReader databaseReader;

    @PostConstruct
    @SneakyThrows
    public void initialize() {
        databaseReader = new DatabaseReader.Builder(getClass().getResourceAsStream("/GeoLite2-City.mmdb")).build();
    }

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
                .location(resolveLocation(request.getRemoteAddr()))
                .build();
        userAgentInfoRepository.save(userAgentInfo);
        modelMap.addAttribute("dataModel", userAgentInfo);
        return "UserAgent";
    }

    private String resolveLocation(String ipAddress) {
        try {
            var response = databaseReader.city(InetAddress.getByName(ipAddress));
            return "%s, %s".formatted(response.getCity().getName(), response.getCountry().getName());
        } catch (Exception e) {
            return null;
        }
    }
}
