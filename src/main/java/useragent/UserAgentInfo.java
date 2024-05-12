package useragent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAgentInfo {
    private final String userAgent;
    private final String os;
    private final String device;
    private final String ipAddress;
    private final String location;
}
