package tacos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class) //junit 4
@ExtendWith(SpringExtension.class) //junit 5 instead of @RunWith
@SpringBootTest
class TacoCloudApplicationTests {

    @Test
    void contextLoads() {
    }

}
