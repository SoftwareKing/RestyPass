package df.open.restypass.testclient.service;

import df.open.restypass.annotation.RestyMethod;
import df.open.restypass.annotation.RestyService;
import df.open.restypass.lb.RandomLoadBalancer;
import df.open.restypass.testclient.entity.Response;
import df.open.restypass.testclient.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 说明:
 * <p/>
 * Copyright: Copyright (c)
 * <p/>
 * Company:
 * <p/>
 *
 * @author darren-fu
 * @version 1.0.0
 * @contact 13914793391
 * @date 2016/11/22
 */
@RestyService(serviceName = "server",
        fallbackClass = ProxyServiceImpl.class,
        retry = 1,
        fallbackEnabled = true
)
@RequestMapping(value = "/resty")
public interface ProxyService extends ApplicationService {

    @RestyMethod(retry = 2)
    @RequestMapping(value = "/get_nothing", method = RequestMethod.GET, headers = "Client=RestyProxy", params = "Param1=val1")
    void getNothing();


    @RestyMethod
    @RequestMapping(value = "/get_string", method = RequestMethod.GET)
    String getString();

    @RestyMethod
    @RequestMapping(value = "/get_user", method = RequestMethod.GET)
    User getUser();

    @RestyMethod()
    @RequestMapping(value = "/list")
    List<User> getList();

    @RestyMethod()
    @RequestMapping(value = "/get_age", method = RequestMethod.GET)
    Response<String> getAge(@RequestParam("id") Long id, String code, @PathVariable(value = "name") String name);

    int getHeight(Long id);

    @RestyMethod
    @RequestMapping(value = "/update/{name}", method = RequestMethod.POST)
    String update(@RequestParam("id") Long id, @PathVariable(value = "name") String name, User user);

}
