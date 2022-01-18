package ibf.nus.sg.SSFAssessment.repositories;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import static ibf.nus.sg.SSFAssessment.constants.*;

@Repository
public class BookRepository {
    
    @Autowired
    @Qualifier(REDIS_TEMPLATE)
    private RedisTemplate<String, String> template;

    public String getBook(String key) {
        return template.opsForValue().get(key);
    }

    public void save(String key, String value) {
        template.opsForValue().set(key, value, 10, TimeUnit.MINUTES);
        return;
    }
}
