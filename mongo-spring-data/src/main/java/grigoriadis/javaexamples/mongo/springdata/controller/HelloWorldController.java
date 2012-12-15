package grigoriadis.javaexamples.mongo.springdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.DB;

@Controller
public class HelloWorldController
{
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/helloWorld")
    @ResponseBody
    public String hello()
    {
        final DB db = this.mongoTemplate.getDb();
        return "helloWorld";
    }
}
