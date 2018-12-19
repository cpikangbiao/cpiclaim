/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-18 上午10:05
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
package com.cpi.claim.service.utility.common;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/10/30
 * @since 1.0.0
 */
public class TimeFormatUtility {

    private static final String DATE_FORMAT_ENGLISH = "dd MMMM yyyy";

    private static final String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";


    public static String formatEnglishInstant(Instant instant){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_ENGLISH, Locale.ENGLISH);
        return simpleDateFormat.format(new Date(instant.toEpochMilli()));
    }

    public static String formatChineseInstant(Instant instant){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_CHINESE, Locale.CHINESE);
        return simpleDateFormat.format(new Date(instant.toEpochMilli()));
    }
}
