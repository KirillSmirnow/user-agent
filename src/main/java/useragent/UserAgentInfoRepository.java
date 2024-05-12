package useragent;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAgentInfoRepository extends MongoRepository<UserAgentInfo, Object> {
}
