/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CountryRepository
 * Author:   admin
 * Date:     2018/4/26 8:44
 * Description: get country from remote microservice
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


package com.cpi.claim.repository.uaa;

import com.cpi.claim.client.AuthorizedFeignClient;
import com.cpi.claim.service.dto.uaa.UserDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 〈一句话功能简述〉<br>
 * 〈get country from remote microservice〉
 *
 * @author admin
 * @create 2018/4/26
 * @since 1.0.0
 */

@AuthorizedFeignClient(name = "cpiuaa")
public interface UserRepository {

    @RequestMapping(value = "/api/users/id/{userid}", method = RequestMethod.GET)
    UserDTO findUserByID(@PathVariable("userid") Long userid);

    @RequestMapping(value = "/api/users/{login}", method = RequestMethod.GET)
    UserDTO getUser(@PathVariable("login") String login);
}
