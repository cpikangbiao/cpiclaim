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

package com.cpi.claim.service.dto.common;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Currency entity.
 */
public class CurrencyDTO implements Serializable {

    public final static Long  CURRENCY_USD = new Long(95);

    public final static Long  CURRENCY_CNY = new Long(14);

    private Long id;

    @NotNull
    @Size(max = 100)
    private String fullNameEnglish;

    @Size(max = 255)
    private String fullNameChinese;

    @NotNull
    @Size(max = 255)
    private String countryNameEnglish;

    @Size(max = 255)
    private String countryNameChinese;

    @NotNull
    @Size(max = 100)
    private String nameAbbre;

    private Integer currencySort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullNameEnglish() {
        return fullNameEnglish;
    }

    public void setFullNameEnglish(String fullNameEnglish) {
        this.fullNameEnglish = fullNameEnglish;
    }

    public String getFullNameChinese() {
        return fullNameChinese;
    }

    public void setFullNameChinese(String fullNameChinese) {
        this.fullNameChinese = fullNameChinese;
    }

    public String getCountryNameEnglish() {
        return countryNameEnglish;
    }

    public void setCountryNameEnglish(String countryNameEnglish) {
        this.countryNameEnglish = countryNameEnglish;
    }

    public String getCountryNameChinese() {
        return countryNameChinese;
    }

    public void setCountryNameChinese(String countryNameChinese) {
        this.countryNameChinese = countryNameChinese;
    }

    public String getNameAbbre() {
        return nameAbbre;
    }

    public void setNameAbbre(String nameAbbre) {
        this.nameAbbre = nameAbbre;
    }

    public Integer getCurrencySort() {
        return currencySort;
    }

    public void setCurrencySort(Integer currencySort) {
        this.currencySort = currencySort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrencyDTO currencyDTO = (CurrencyDTO) o;
        if(currencyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), currencyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
            "id=" + getId() +
            ", fullNameEnglish='" + getFullNameEnglish() + "'" +
            ", fullNameChinese='" + getFullNameChinese() + "'" +
            ", countryNameEnglish='" + getCountryNameEnglish() + "'" +
            ", countryNameChinese='" + getCountryNameChinese() + "'" +
            ", nameAbbre='" + getNameAbbre() + "'" +
            ", currencySort=" + getCurrencySort() +
            "}";
    }
}
