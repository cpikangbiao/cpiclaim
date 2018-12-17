/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:40
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

package com.cpi.claim.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

import java.io.IOException;
import java.math.BigDecimal;

@Configuration
public class JacksonConfiguration {

    private final static int precision = 10;
    /*
     * Support for Hibernate types in Jackson.
     */
    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /*
     * Jackson Afterburner module to speed up serialization/deserialization.
     */
    @Bean
    public AfterburnerModule afterburnerModule() {
        return new AfterburnerModule();
    }

    /*
     * Module for serialization/deserialization of RFC7807 Problem.
     */
    @Bean
    ProblemModule problemModule() {
        return new ProblemModule();
    }

    /*
     * Module for serialization/deserialization of ConstraintViolationProblem.
     */
    @Bean
    ConstraintViolationProblemModule constraintViolationProblemModule() {
        return new ConstraintViolationProblemModule();
    }



    @Bean("jackson2ObjectMapperBuilderCustomizer")
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Jackson2ObjectMapperBuilderCustomizer customizer = new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {

                jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object arg0, JsonGenerator jsonGenerator, SerializerProvider arg2)
                        throws IOException, JsonProcessingException {
                        if (arg0 instanceof BigDecimal) {
                            if (jsonGenerator != null) {
                                jsonGenerator.writeString(((BigDecimal) arg0).setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString());
//                                jsonGenerator.writeString(((BigDecimal) arg0).toPlainString());
                            }

                        }
                    }
                });
            }
        };

        return customizer;
    }

//    @Bean
//    public ObjectMapper objectMapper() {
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setDateFormat(dateFormat);
//        return mapper;
//    }
//    private void customize(Jackson2ObjectMapperBuilder builder,
//                           List<Jackson2ObjectMapperBuilderCustomizer> customizers) {
//        for (Jackson2ObjectMapperBuilderCustomizer customizer : customizers) {
//            customizer.customize(builder);
//        }
//    }
//
//    private Jackson2ObjectMapperBuilder configureObjectMapper() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        List<String> activeProfiles = asList(env.getActiveProfiles());
//        if (activeProfiles.contains(SPRING_PROFILE_DEVELOPMENT)) {
//            builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT);
//        }
//        return builder;
//    }
//}

}
