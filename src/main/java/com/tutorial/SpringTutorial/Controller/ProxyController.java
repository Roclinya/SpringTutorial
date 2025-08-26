package com.tutorial.SpringTutorial.Controller;

import com.tutorial.SpringTutorial.Proxy.LogProxy;
import com.tutorial.SpringTutorial.Service.IHello;
import com.tutorial.SpringTutorial.Service.Impl.HelloSpeaker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ProxyController {

    //動態代理 https://openhome.cc/Gossip/JavaEssence/Proxy.html
    @PostMapping("/testDynamicPorxy")
    public void testDynamicPorxy(){
        IHello speaker = (IHello) new LogProxy().getLogProxy(new HelloSpeaker());
        speaker.hello("Jasper");
    }
}
