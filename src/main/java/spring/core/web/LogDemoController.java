package spring.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.core.common.MyLogger;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    // Request 스코프
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);

        logDemoService.logic("testId");
        return "OK";
    }
}
