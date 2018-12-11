/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */
package com.cpi.claim.repository.jasperreport;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.client.AuthorizedFeignClient;
import com.cpi.claim.config.LongTimeFeignConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/6/22
 * @since 1.0.0
 */
@AuthorizedFeignClient(name = "cpijasperreport", configuration = LongTimeFeignConfiguration.class)
public interface JasperReportUtility {

    @RequestMapping(value = "/api/jasperreport/pdf-withid", method = RequestMethod.POST)
    ResponseEntity<byte[]> processPDF(@RequestParam(value = "typeid", required = true) Integer typeid,
                                      @RequestBody Map<String, Object> parameters);

    @RequestMapping(value = "/api/jasperreport/pdf-withfile", method = RequestMethod.POST)
    ResponseEntity<byte[]> processPDF(@RequestParam(value = "filename", required = true) String jasperFileName,
                                      @RequestBody Map<String, Object> parameters);

    @RequestMapping(value = "/api/jasperreport/html-withfile", method = RequestMethod.POST)
    ResponseEntity<byte[]> processHTML(@RequestParam(value = "filename", required = true) String jasperFileName,
                                       @RequestBody Map<String, Object> parameters);

    @PostMapping("/api/test/addimage")
    @Timed
    Map<String, Object> addImageMapParamete(@RequestParam(value = "path", required = true) String path,
                                            @RequestParam(value = "imageFileName", required = true) String imageFileName,
                                            @RequestParam(value = "imageParameterName", required = true) String imageParameterName);
}
