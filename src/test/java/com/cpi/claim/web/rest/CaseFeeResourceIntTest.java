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

package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.FeeType;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.domain.Creditor;
import com.cpi.claim.repository.CaseFeeRepository;
import com.cpi.claim.service.CaseFeeService;
import com.cpi.claim.service.dto.CaseFeeDTO;
import com.cpi.claim.service.mapper.CaseFeeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseFeeCriteria;
import com.cpi.claim.service.CaseFeeQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CaseFeeResource REST controller.
 *
 * @see CaseFeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseFeeResourceIntTest {

    private static final String DEFAULT_CLIENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final Long DEFAULT_CURRENCY = 1L;
    private static final Long UPDATED_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_RATE = new BigDecimal(2);

    private static final Instant DEFAULT_FEE_COST_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FEE_COST_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_FEE_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_FEE_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_FEE_COST_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_FEE_COST_DOLLAR = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEDUCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCT = new BigDecimal(2);

    private static final Long DEFAULT_DEDUCT_CURRENCY = 1L;
    private static final Long UPDATED_DEDUCT_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_DEDUCT_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCT_CURRENCY_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEDUCT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCT_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT_DOLLAR = new BigDecimal(2);

    private static final Long DEFAULT_FEE_CREATE_USER = 1L;
    private static final Long UPDATED_FEE_CREATE_USER = 2L;

    private static final Instant DEFAULT_FEE_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FEE_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SIGNED = false;
    private static final Boolean UPDATED_IS_SIGNED = true;

    private static final Long DEFAULT_SIGN_USER = 1L;
    private static final Long UPDATED_SIGN_USER = 2L;

    private static final Long DEFAULT_SIGN_DATE = 1L;
    private static final Long UPDATED_SIGN_DATE = 2L;

    private static final Long DEFAULT_PROCESS_ID = 1L;
    private static final Long UPDATED_PROCESS_ID = 2L;

    @Autowired
    private CaseFeeRepository caseFeeRepository;


    @Autowired
    private CaseFeeMapper caseFeeMapper;


    @Autowired
    private CaseFeeService caseFeeService;

    @Autowired
    private CaseFeeQueryService caseFeeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaseFeeMockMvc;

    private CaseFee caseFee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseFeeResource caseFeeResource = new CaseFeeResource(caseFeeService, caseFeeQueryService);
        this.restCaseFeeMockMvc = MockMvcBuilders.standaloneSetup(caseFeeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseFee createEntity(EntityManager em) {
        CaseFee caseFee = new CaseFee()
            .clientNo(DEFAULT_CLIENT_NO)
            .numberId(DEFAULT_NUMBER_ID)
            .currency(DEFAULT_CURRENCY)
            .currencyRate(DEFAULT_CURRENCY_RATE)
            .feeCostDate(DEFAULT_FEE_COST_DATE)
            .feeCost(DEFAULT_FEE_COST)
            .feeCostDollar(DEFAULT_FEE_COST_DOLLAR)
            .deduct(DEFAULT_DEDUCT)
            .deductCurrency(DEFAULT_DEDUCT_CURRENCY)
            .deductCurrencyRate(DEFAULT_DEDUCT_CURRENCY_RATE)
            .deductAmount(DEFAULT_DEDUCT_AMOUNT)
            .amountDollar(DEFAULT_AMOUNT_DOLLAR)
            .feeCreateUser(DEFAULT_FEE_CREATE_USER)
            .feeCreateDate(DEFAULT_FEE_CREATE_DATE)
            .remark(DEFAULT_REMARK)
            .isSigned(DEFAULT_IS_SIGNED)
            .signUser(DEFAULT_SIGN_USER)
            .signDate(DEFAULT_SIGN_DATE)
            .processId(DEFAULT_PROCESS_ID);
        return caseFee;
    }

    @Before
    public void initTest() {
        caseFee = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseFee() throws Exception {
        int databaseSizeBeforeCreate = caseFeeRepository.findAll().size();

        // Create the CaseFee
        CaseFeeDTO caseFeeDTO = caseFeeMapper.toDto(caseFee);
        restCaseFeeMockMvc.perform(post("/api/case-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseFeeDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseFee in the database
        List<CaseFee> caseFeeList = caseFeeRepository.findAll();
        assertThat(caseFeeList).hasSize(databaseSizeBeforeCreate + 1);
        CaseFee testCaseFee = caseFeeList.get(caseFeeList.size() - 1);
        assertThat(testCaseFee.getClientNo()).isEqualTo(DEFAULT_CLIENT_NO);
        assertThat(testCaseFee.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCaseFee.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testCaseFee.getCurrencyRate()).isEqualTo(DEFAULT_CURRENCY_RATE);
        assertThat(testCaseFee.getFeeCostDate()).isEqualTo(DEFAULT_FEE_COST_DATE);
        assertThat(testCaseFee.getFeeCost()).isEqualTo(DEFAULT_FEE_COST);
        assertThat(testCaseFee.getFeeCostDollar()).isEqualTo(DEFAULT_FEE_COST_DOLLAR);
        assertThat(testCaseFee.getDeduct()).isEqualTo(DEFAULT_DEDUCT);
        assertThat(testCaseFee.getDeductCurrency()).isEqualTo(DEFAULT_DEDUCT_CURRENCY);
        assertThat(testCaseFee.getDeductCurrencyRate()).isEqualTo(DEFAULT_DEDUCT_CURRENCY_RATE);
        assertThat(testCaseFee.getDeductAmount()).isEqualTo(DEFAULT_DEDUCT_AMOUNT);
        assertThat(testCaseFee.getAmountDollar()).isEqualTo(DEFAULT_AMOUNT_DOLLAR);
        assertThat(testCaseFee.getFeeCreateUser()).isEqualTo(DEFAULT_FEE_CREATE_USER);
        assertThat(testCaseFee.getFeeCreateDate()).isEqualTo(DEFAULT_FEE_CREATE_DATE);
        assertThat(testCaseFee.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testCaseFee.isIsSigned()).isEqualTo(DEFAULT_IS_SIGNED);
        assertThat(testCaseFee.getSignUser()).isEqualTo(DEFAULT_SIGN_USER);
        assertThat(testCaseFee.getSignDate()).isEqualTo(DEFAULT_SIGN_DATE);
        assertThat(testCaseFee.getProcessId()).isEqualTo(DEFAULT_PROCESS_ID);
    }

    @Test
    @Transactional
    public void createCaseFeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseFeeRepository.findAll().size();

        // Create the CaseFee with an existing ID
        caseFee.setId(1L);
        CaseFeeDTO caseFeeDTO = caseFeeMapper.toDto(caseFee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseFeeMockMvc.perform(post("/api/case-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseFeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseFee in the database
        List<CaseFee> caseFeeList = caseFeeRepository.findAll();
        assertThat(caseFeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCaseFees() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList
        restCaseFeeMockMvc.perform(get("/api/case-fees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseFee.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientNo").value(hasItem(DEFAULT_CLIENT_NO.toString())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].feeCostDate").value(hasItem(DEFAULT_FEE_COST_DATE.toString())))
            .andExpect(jsonPath("$.[*].feeCost").value(hasItem(DEFAULT_FEE_COST.intValue())))
            .andExpect(jsonPath("$.[*].feeCostDollar").value(hasItem(DEFAULT_FEE_COST_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].deduct").value(hasItem(DEFAULT_DEDUCT.intValue())))
            .andExpect(jsonPath("$.[*].deductCurrency").value(hasItem(DEFAULT_DEDUCT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].deductCurrencyRate").value(hasItem(DEFAULT_DEDUCT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].deductAmount").value(hasItem(DEFAULT_DEDUCT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].amountDollar").value(hasItem(DEFAULT_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].feeCreateUser").value(hasItem(DEFAULT_FEE_CREATE_USER.intValue())))
            .andExpect(jsonPath("$.[*].feeCreateDate").value(hasItem(DEFAULT_FEE_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].isSigned").value(hasItem(DEFAULT_IS_SIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].signUser").value(hasItem(DEFAULT_SIGN_USER.intValue())))
            .andExpect(jsonPath("$.[*].signDate").value(hasItem(DEFAULT_SIGN_DATE.intValue())))
            .andExpect(jsonPath("$.[*].processId").value(hasItem(DEFAULT_PROCESS_ID.intValue())));
    }


    @Test
    @Transactional
    public void getCaseFee() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get the caseFee
        restCaseFeeMockMvc.perform(get("/api/case-fees/{id}", caseFee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseFee.getId().intValue()))
            .andExpect(jsonPath("$.clientNo").value(DEFAULT_CLIENT_NO.toString()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.intValue()))
            .andExpect(jsonPath("$.currencyRate").value(DEFAULT_CURRENCY_RATE.intValue()))
            .andExpect(jsonPath("$.feeCostDate").value(DEFAULT_FEE_COST_DATE.toString()))
            .andExpect(jsonPath("$.feeCost").value(DEFAULT_FEE_COST.intValue()))
            .andExpect(jsonPath("$.feeCostDollar").value(DEFAULT_FEE_COST_DOLLAR.intValue()))
            .andExpect(jsonPath("$.deduct").value(DEFAULT_DEDUCT.intValue()))
            .andExpect(jsonPath("$.deductCurrency").value(DEFAULT_DEDUCT_CURRENCY.intValue()))
            .andExpect(jsonPath("$.deductCurrencyRate").value(DEFAULT_DEDUCT_CURRENCY_RATE.intValue()))
            .andExpect(jsonPath("$.deductAmount").value(DEFAULT_DEDUCT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.amountDollar").value(DEFAULT_AMOUNT_DOLLAR.intValue()))
            .andExpect(jsonPath("$.feeCreateUser").value(DEFAULT_FEE_CREATE_USER.intValue()))
            .andExpect(jsonPath("$.feeCreateDate").value(DEFAULT_FEE_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.isSigned").value(DEFAULT_IS_SIGNED.booleanValue()))
            .andExpect(jsonPath("$.signUser").value(DEFAULT_SIGN_USER.intValue()))
            .andExpect(jsonPath("$.signDate").value(DEFAULT_SIGN_DATE.intValue()))
            .andExpect(jsonPath("$.processId").value(DEFAULT_PROCESS_ID.intValue()));
    }

    @Test
    @Transactional
    public void getAllCaseFeesByClientNoIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where clientNo equals to DEFAULT_CLIENT_NO
        defaultCaseFeeShouldBeFound("clientNo.equals=" + DEFAULT_CLIENT_NO);

        // Get all the caseFeeList where clientNo equals to UPDATED_CLIENT_NO
        defaultCaseFeeShouldNotBeFound("clientNo.equals=" + UPDATED_CLIENT_NO);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByClientNoIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where clientNo in DEFAULT_CLIENT_NO or UPDATED_CLIENT_NO
        defaultCaseFeeShouldBeFound("clientNo.in=" + DEFAULT_CLIENT_NO + "," + UPDATED_CLIENT_NO);

        // Get all the caseFeeList where clientNo equals to UPDATED_CLIENT_NO
        defaultCaseFeeShouldNotBeFound("clientNo.in=" + UPDATED_CLIENT_NO);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByClientNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where clientNo is not null
        defaultCaseFeeShouldBeFound("clientNo.specified=true");

        // Get all the caseFeeList where clientNo is null
        defaultCaseFeeShouldNotBeFound("clientNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where numberId equals to DEFAULT_NUMBER_ID
        defaultCaseFeeShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the caseFeeList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseFeeShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCaseFeeShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the caseFeeList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseFeeShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where numberId is not null
        defaultCaseFeeShouldBeFound("numberId.specified=true");

        // Get all the caseFeeList where numberId is null
        defaultCaseFeeShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultCaseFeeShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseFeeList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultCaseFeeShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultCaseFeeShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseFeeList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultCaseFeeShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseFeesByCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where currency equals to DEFAULT_CURRENCY
        defaultCaseFeeShouldBeFound("currency.equals=" + DEFAULT_CURRENCY);

        // Get all the caseFeeList where currency equals to UPDATED_CURRENCY
        defaultCaseFeeShouldNotBeFound("currency.equals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where currency in DEFAULT_CURRENCY or UPDATED_CURRENCY
        defaultCaseFeeShouldBeFound("currency.in=" + DEFAULT_CURRENCY + "," + UPDATED_CURRENCY);

        // Get all the caseFeeList where currency equals to UPDATED_CURRENCY
        defaultCaseFeeShouldNotBeFound("currency.in=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where currency is not null
        defaultCaseFeeShouldBeFound("currency.specified=true");

        // Get all the caseFeeList where currency is null
        defaultCaseFeeShouldNotBeFound("currency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where currency greater than or equals to DEFAULT_CURRENCY
        defaultCaseFeeShouldBeFound("currency.greaterOrEqualThan=" + DEFAULT_CURRENCY);

        // Get all the caseFeeList where currency greater than or equals to UPDATED_CURRENCY
        defaultCaseFeeShouldNotBeFound("currency.greaterOrEqualThan=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where currency less than or equals to DEFAULT_CURRENCY
        defaultCaseFeeShouldNotBeFound("currency.lessThan=" + DEFAULT_CURRENCY);

        // Get all the caseFeeList where currency less than or equals to UPDATED_CURRENCY
        defaultCaseFeeShouldBeFound("currency.lessThan=" + UPDATED_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCaseFeesByCurrencyRateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where currencyRate equals to DEFAULT_CURRENCY_RATE
        defaultCaseFeeShouldBeFound("currencyRate.equals=" + DEFAULT_CURRENCY_RATE);

        // Get all the caseFeeList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultCaseFeeShouldNotBeFound("currencyRate.equals=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByCurrencyRateIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where currencyRate in DEFAULT_CURRENCY_RATE or UPDATED_CURRENCY_RATE
        defaultCaseFeeShouldBeFound("currencyRate.in=" + DEFAULT_CURRENCY_RATE + "," + UPDATED_CURRENCY_RATE);

        // Get all the caseFeeList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultCaseFeeShouldNotBeFound("currencyRate.in=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByCurrencyRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where currencyRate is not null
        defaultCaseFeeShouldBeFound("currencyRate.specified=true");

        // Get all the caseFeeList where currencyRate is null
        defaultCaseFeeShouldNotBeFound("currencyRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCostDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCostDate equals to DEFAULT_FEE_COST_DATE
        defaultCaseFeeShouldBeFound("feeCostDate.equals=" + DEFAULT_FEE_COST_DATE);

        // Get all the caseFeeList where feeCostDate equals to UPDATED_FEE_COST_DATE
        defaultCaseFeeShouldNotBeFound("feeCostDate.equals=" + UPDATED_FEE_COST_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCostDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCostDate in DEFAULT_FEE_COST_DATE or UPDATED_FEE_COST_DATE
        defaultCaseFeeShouldBeFound("feeCostDate.in=" + DEFAULT_FEE_COST_DATE + "," + UPDATED_FEE_COST_DATE);

        // Get all the caseFeeList where feeCostDate equals to UPDATED_FEE_COST_DATE
        defaultCaseFeeShouldNotBeFound("feeCostDate.in=" + UPDATED_FEE_COST_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCostDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCostDate is not null
        defaultCaseFeeShouldBeFound("feeCostDate.specified=true");

        // Get all the caseFeeList where feeCostDate is null
        defaultCaseFeeShouldNotBeFound("feeCostDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCostIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCost equals to DEFAULT_FEE_COST
        defaultCaseFeeShouldBeFound("feeCost.equals=" + DEFAULT_FEE_COST);

        // Get all the caseFeeList where feeCost equals to UPDATED_FEE_COST
        defaultCaseFeeShouldNotBeFound("feeCost.equals=" + UPDATED_FEE_COST);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCostIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCost in DEFAULT_FEE_COST or UPDATED_FEE_COST
        defaultCaseFeeShouldBeFound("feeCost.in=" + DEFAULT_FEE_COST + "," + UPDATED_FEE_COST);

        // Get all the caseFeeList where feeCost equals to UPDATED_FEE_COST
        defaultCaseFeeShouldNotBeFound("feeCost.in=" + UPDATED_FEE_COST);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCost is not null
        defaultCaseFeeShouldBeFound("feeCost.specified=true");

        // Get all the caseFeeList where feeCost is null
        defaultCaseFeeShouldNotBeFound("feeCost.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCostDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCostDollar equals to DEFAULT_FEE_COST_DOLLAR
        defaultCaseFeeShouldBeFound("feeCostDollar.equals=" + DEFAULT_FEE_COST_DOLLAR);

        // Get all the caseFeeList where feeCostDollar equals to UPDATED_FEE_COST_DOLLAR
        defaultCaseFeeShouldNotBeFound("feeCostDollar.equals=" + UPDATED_FEE_COST_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCostDollarIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCostDollar in DEFAULT_FEE_COST_DOLLAR or UPDATED_FEE_COST_DOLLAR
        defaultCaseFeeShouldBeFound("feeCostDollar.in=" + DEFAULT_FEE_COST_DOLLAR + "," + UPDATED_FEE_COST_DOLLAR);

        // Get all the caseFeeList where feeCostDollar equals to UPDATED_FEE_COST_DOLLAR
        defaultCaseFeeShouldNotBeFound("feeCostDollar.in=" + UPDATED_FEE_COST_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCostDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCostDollar is not null
        defaultCaseFeeShouldBeFound("feeCostDollar.specified=true");

        // Get all the caseFeeList where feeCostDollar is null
        defaultCaseFeeShouldNotBeFound("feeCostDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deduct equals to DEFAULT_DEDUCT
        defaultCaseFeeShouldBeFound("deduct.equals=" + DEFAULT_DEDUCT);

        // Get all the caseFeeList where deduct equals to UPDATED_DEDUCT
        defaultCaseFeeShouldNotBeFound("deduct.equals=" + UPDATED_DEDUCT);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deduct in DEFAULT_DEDUCT or UPDATED_DEDUCT
        defaultCaseFeeShouldBeFound("deduct.in=" + DEFAULT_DEDUCT + "," + UPDATED_DEDUCT);

        // Get all the caseFeeList where deduct equals to UPDATED_DEDUCT
        defaultCaseFeeShouldNotBeFound("deduct.in=" + UPDATED_DEDUCT);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deduct is not null
        defaultCaseFeeShouldBeFound("deduct.specified=true");

        // Get all the caseFeeList where deduct is null
        defaultCaseFeeShouldNotBeFound("deduct.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductCurrency equals to DEFAULT_DEDUCT_CURRENCY
        defaultCaseFeeShouldBeFound("deductCurrency.equals=" + DEFAULT_DEDUCT_CURRENCY);

        // Get all the caseFeeList where deductCurrency equals to UPDATED_DEDUCT_CURRENCY
        defaultCaseFeeShouldNotBeFound("deductCurrency.equals=" + UPDATED_DEDUCT_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductCurrency in DEFAULT_DEDUCT_CURRENCY or UPDATED_DEDUCT_CURRENCY
        defaultCaseFeeShouldBeFound("deductCurrency.in=" + DEFAULT_DEDUCT_CURRENCY + "," + UPDATED_DEDUCT_CURRENCY);

        // Get all the caseFeeList where deductCurrency equals to UPDATED_DEDUCT_CURRENCY
        defaultCaseFeeShouldNotBeFound("deductCurrency.in=" + UPDATED_DEDUCT_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductCurrency is not null
        defaultCaseFeeShouldBeFound("deductCurrency.specified=true");

        // Get all the caseFeeList where deductCurrency is null
        defaultCaseFeeShouldNotBeFound("deductCurrency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductCurrency greater than or equals to DEFAULT_DEDUCT_CURRENCY
        defaultCaseFeeShouldBeFound("deductCurrency.greaterOrEqualThan=" + DEFAULT_DEDUCT_CURRENCY);

        // Get all the caseFeeList where deductCurrency greater than or equals to UPDATED_DEDUCT_CURRENCY
        defaultCaseFeeShouldNotBeFound("deductCurrency.greaterOrEqualThan=" + UPDATED_DEDUCT_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductCurrency less than or equals to DEFAULT_DEDUCT_CURRENCY
        defaultCaseFeeShouldNotBeFound("deductCurrency.lessThan=" + DEFAULT_DEDUCT_CURRENCY);

        // Get all the caseFeeList where deductCurrency less than or equals to UPDATED_DEDUCT_CURRENCY
        defaultCaseFeeShouldBeFound("deductCurrency.lessThan=" + UPDATED_DEDUCT_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCaseFeesByDeductCurrencyRateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductCurrencyRate equals to DEFAULT_DEDUCT_CURRENCY_RATE
        defaultCaseFeeShouldBeFound("deductCurrencyRate.equals=" + DEFAULT_DEDUCT_CURRENCY_RATE);

        // Get all the caseFeeList where deductCurrencyRate equals to UPDATED_DEDUCT_CURRENCY_RATE
        defaultCaseFeeShouldNotBeFound("deductCurrencyRate.equals=" + UPDATED_DEDUCT_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductCurrencyRateIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductCurrencyRate in DEFAULT_DEDUCT_CURRENCY_RATE or UPDATED_DEDUCT_CURRENCY_RATE
        defaultCaseFeeShouldBeFound("deductCurrencyRate.in=" + DEFAULT_DEDUCT_CURRENCY_RATE + "," + UPDATED_DEDUCT_CURRENCY_RATE);

        // Get all the caseFeeList where deductCurrencyRate equals to UPDATED_DEDUCT_CURRENCY_RATE
        defaultCaseFeeShouldNotBeFound("deductCurrencyRate.in=" + UPDATED_DEDUCT_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductCurrencyRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductCurrencyRate is not null
        defaultCaseFeeShouldBeFound("deductCurrencyRate.specified=true");

        // Get all the caseFeeList where deductCurrencyRate is null
        defaultCaseFeeShouldNotBeFound("deductCurrencyRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductAmount equals to DEFAULT_DEDUCT_AMOUNT
        defaultCaseFeeShouldBeFound("deductAmount.equals=" + DEFAULT_DEDUCT_AMOUNT);

        // Get all the caseFeeList where deductAmount equals to UPDATED_DEDUCT_AMOUNT
        defaultCaseFeeShouldNotBeFound("deductAmount.equals=" + UPDATED_DEDUCT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductAmountIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductAmount in DEFAULT_DEDUCT_AMOUNT or UPDATED_DEDUCT_AMOUNT
        defaultCaseFeeShouldBeFound("deductAmount.in=" + DEFAULT_DEDUCT_AMOUNT + "," + UPDATED_DEDUCT_AMOUNT);

        // Get all the caseFeeList where deductAmount equals to UPDATED_DEDUCT_AMOUNT
        defaultCaseFeeShouldNotBeFound("deductAmount.in=" + UPDATED_DEDUCT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByDeductAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where deductAmount is not null
        defaultCaseFeeShouldBeFound("deductAmount.specified=true");

        // Get all the caseFeeList where deductAmount is null
        defaultCaseFeeShouldNotBeFound("deductAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByAmountDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where amountDollar equals to DEFAULT_AMOUNT_DOLLAR
        defaultCaseFeeShouldBeFound("amountDollar.equals=" + DEFAULT_AMOUNT_DOLLAR);

        // Get all the caseFeeList where amountDollar equals to UPDATED_AMOUNT_DOLLAR
        defaultCaseFeeShouldNotBeFound("amountDollar.equals=" + UPDATED_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByAmountDollarIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where amountDollar in DEFAULT_AMOUNT_DOLLAR or UPDATED_AMOUNT_DOLLAR
        defaultCaseFeeShouldBeFound("amountDollar.in=" + DEFAULT_AMOUNT_DOLLAR + "," + UPDATED_AMOUNT_DOLLAR);

        // Get all the caseFeeList where amountDollar equals to UPDATED_AMOUNT_DOLLAR
        defaultCaseFeeShouldNotBeFound("amountDollar.in=" + UPDATED_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByAmountDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where amountDollar is not null
        defaultCaseFeeShouldBeFound("amountDollar.specified=true");

        // Get all the caseFeeList where amountDollar is null
        defaultCaseFeeShouldNotBeFound("amountDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCreateUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCreateUser equals to DEFAULT_FEE_CREATE_USER
        defaultCaseFeeShouldBeFound("feeCreateUser.equals=" + DEFAULT_FEE_CREATE_USER);

        // Get all the caseFeeList where feeCreateUser equals to UPDATED_FEE_CREATE_USER
        defaultCaseFeeShouldNotBeFound("feeCreateUser.equals=" + UPDATED_FEE_CREATE_USER);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCreateUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCreateUser in DEFAULT_FEE_CREATE_USER or UPDATED_FEE_CREATE_USER
        defaultCaseFeeShouldBeFound("feeCreateUser.in=" + DEFAULT_FEE_CREATE_USER + "," + UPDATED_FEE_CREATE_USER);

        // Get all the caseFeeList where feeCreateUser equals to UPDATED_FEE_CREATE_USER
        defaultCaseFeeShouldNotBeFound("feeCreateUser.in=" + UPDATED_FEE_CREATE_USER);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCreateUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCreateUser is not null
        defaultCaseFeeShouldBeFound("feeCreateUser.specified=true");

        // Get all the caseFeeList where feeCreateUser is null
        defaultCaseFeeShouldNotBeFound("feeCreateUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCreateUserIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCreateUser greater than or equals to DEFAULT_FEE_CREATE_USER
        defaultCaseFeeShouldBeFound("feeCreateUser.greaterOrEqualThan=" + DEFAULT_FEE_CREATE_USER);

        // Get all the caseFeeList where feeCreateUser greater than or equals to UPDATED_FEE_CREATE_USER
        defaultCaseFeeShouldNotBeFound("feeCreateUser.greaterOrEqualThan=" + UPDATED_FEE_CREATE_USER);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCreateUserIsLessThanSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCreateUser less than or equals to DEFAULT_FEE_CREATE_USER
        defaultCaseFeeShouldNotBeFound("feeCreateUser.lessThan=" + DEFAULT_FEE_CREATE_USER);

        // Get all the caseFeeList where feeCreateUser less than or equals to UPDATED_FEE_CREATE_USER
        defaultCaseFeeShouldBeFound("feeCreateUser.lessThan=" + UPDATED_FEE_CREATE_USER);
    }


    @Test
    @Transactional
    public void getAllCaseFeesByFeeCreateDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCreateDate equals to DEFAULT_FEE_CREATE_DATE
        defaultCaseFeeShouldBeFound("feeCreateDate.equals=" + DEFAULT_FEE_CREATE_DATE);

        // Get all the caseFeeList where feeCreateDate equals to UPDATED_FEE_CREATE_DATE
        defaultCaseFeeShouldNotBeFound("feeCreateDate.equals=" + UPDATED_FEE_CREATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCreateDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCreateDate in DEFAULT_FEE_CREATE_DATE or UPDATED_FEE_CREATE_DATE
        defaultCaseFeeShouldBeFound("feeCreateDate.in=" + DEFAULT_FEE_CREATE_DATE + "," + UPDATED_FEE_CREATE_DATE);

        // Get all the caseFeeList where feeCreateDate equals to UPDATED_FEE_CREATE_DATE
        defaultCaseFeeShouldNotBeFound("feeCreateDate.in=" + UPDATED_FEE_CREATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByFeeCreateDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where feeCreateDate is not null
        defaultCaseFeeShouldBeFound("feeCreateDate.specified=true");

        // Get all the caseFeeList where feeCreateDate is null
        defaultCaseFeeShouldNotBeFound("feeCreateDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByIsSignedIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where isSigned equals to DEFAULT_IS_SIGNED
        defaultCaseFeeShouldBeFound("isSigned.equals=" + DEFAULT_IS_SIGNED);

        // Get all the caseFeeList where isSigned equals to UPDATED_IS_SIGNED
        defaultCaseFeeShouldNotBeFound("isSigned.equals=" + UPDATED_IS_SIGNED);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByIsSignedIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where isSigned in DEFAULT_IS_SIGNED or UPDATED_IS_SIGNED
        defaultCaseFeeShouldBeFound("isSigned.in=" + DEFAULT_IS_SIGNED + "," + UPDATED_IS_SIGNED);

        // Get all the caseFeeList where isSigned equals to UPDATED_IS_SIGNED
        defaultCaseFeeShouldNotBeFound("isSigned.in=" + UPDATED_IS_SIGNED);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByIsSignedIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where isSigned is not null
        defaultCaseFeeShouldBeFound("isSigned.specified=true");

        // Get all the caseFeeList where isSigned is null
        defaultCaseFeeShouldNotBeFound("isSigned.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesBySignUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signUser equals to DEFAULT_SIGN_USER
        defaultCaseFeeShouldBeFound("signUser.equals=" + DEFAULT_SIGN_USER);

        // Get all the caseFeeList where signUser equals to UPDATED_SIGN_USER
        defaultCaseFeeShouldNotBeFound("signUser.equals=" + UPDATED_SIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseFeesBySignUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signUser in DEFAULT_SIGN_USER or UPDATED_SIGN_USER
        defaultCaseFeeShouldBeFound("signUser.in=" + DEFAULT_SIGN_USER + "," + UPDATED_SIGN_USER);

        // Get all the caseFeeList where signUser equals to UPDATED_SIGN_USER
        defaultCaseFeeShouldNotBeFound("signUser.in=" + UPDATED_SIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseFeesBySignUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signUser is not null
        defaultCaseFeeShouldBeFound("signUser.specified=true");

        // Get all the caseFeeList where signUser is null
        defaultCaseFeeShouldNotBeFound("signUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesBySignUserIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signUser greater than or equals to DEFAULT_SIGN_USER
        defaultCaseFeeShouldBeFound("signUser.greaterOrEqualThan=" + DEFAULT_SIGN_USER);

        // Get all the caseFeeList where signUser greater than or equals to UPDATED_SIGN_USER
        defaultCaseFeeShouldNotBeFound("signUser.greaterOrEqualThan=" + UPDATED_SIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseFeesBySignUserIsLessThanSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signUser less than or equals to DEFAULT_SIGN_USER
        defaultCaseFeeShouldNotBeFound("signUser.lessThan=" + DEFAULT_SIGN_USER);

        // Get all the caseFeeList where signUser less than or equals to UPDATED_SIGN_USER
        defaultCaseFeeShouldBeFound("signUser.lessThan=" + UPDATED_SIGN_USER);
    }


    @Test
    @Transactional
    public void getAllCaseFeesBySignDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signDate equals to DEFAULT_SIGN_DATE
        defaultCaseFeeShouldBeFound("signDate.equals=" + DEFAULT_SIGN_DATE);

        // Get all the caseFeeList where signDate equals to UPDATED_SIGN_DATE
        defaultCaseFeeShouldNotBeFound("signDate.equals=" + UPDATED_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesBySignDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signDate in DEFAULT_SIGN_DATE or UPDATED_SIGN_DATE
        defaultCaseFeeShouldBeFound("signDate.in=" + DEFAULT_SIGN_DATE + "," + UPDATED_SIGN_DATE);

        // Get all the caseFeeList where signDate equals to UPDATED_SIGN_DATE
        defaultCaseFeeShouldNotBeFound("signDate.in=" + UPDATED_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesBySignDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signDate is not null
        defaultCaseFeeShouldBeFound("signDate.specified=true");

        // Get all the caseFeeList where signDate is null
        defaultCaseFeeShouldNotBeFound("signDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesBySignDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signDate greater than or equals to DEFAULT_SIGN_DATE
        defaultCaseFeeShouldBeFound("signDate.greaterOrEqualThan=" + DEFAULT_SIGN_DATE);

        // Get all the caseFeeList where signDate greater than or equals to UPDATED_SIGN_DATE
        defaultCaseFeeShouldNotBeFound("signDate.greaterOrEqualThan=" + UPDATED_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseFeesBySignDateIsLessThanSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where signDate less than or equals to DEFAULT_SIGN_DATE
        defaultCaseFeeShouldNotBeFound("signDate.lessThan=" + DEFAULT_SIGN_DATE);

        // Get all the caseFeeList where signDate less than or equals to UPDATED_SIGN_DATE
        defaultCaseFeeShouldBeFound("signDate.lessThan=" + UPDATED_SIGN_DATE);
    }


    @Test
    @Transactional
    public void getAllCaseFeesByProcessIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where processId equals to DEFAULT_PROCESS_ID
        defaultCaseFeeShouldBeFound("processId.equals=" + DEFAULT_PROCESS_ID);

        // Get all the caseFeeList where processId equals to UPDATED_PROCESS_ID
        defaultCaseFeeShouldNotBeFound("processId.equals=" + UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByProcessIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where processId in DEFAULT_PROCESS_ID or UPDATED_PROCESS_ID
        defaultCaseFeeShouldBeFound("processId.in=" + DEFAULT_PROCESS_ID + "," + UPDATED_PROCESS_ID);

        // Get all the caseFeeList where processId equals to UPDATED_PROCESS_ID
        defaultCaseFeeShouldNotBeFound("processId.in=" + UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByProcessIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where processId is not null
        defaultCaseFeeShouldBeFound("processId.specified=true");

        // Get all the caseFeeList where processId is null
        defaultCaseFeeShouldNotBeFound("processId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeesByProcessIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where processId greater than or equals to DEFAULT_PROCESS_ID
        defaultCaseFeeShouldBeFound("processId.greaterOrEqualThan=" + DEFAULT_PROCESS_ID);

        // Get all the caseFeeList where processId greater than or equals to UPDATED_PROCESS_ID
        defaultCaseFeeShouldNotBeFound("processId.greaterOrEqualThan=" + UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void getAllCaseFeesByProcessIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        // Get all the caseFeeList where processId less than or equals to DEFAULT_PROCESS_ID
        defaultCaseFeeShouldNotBeFound("processId.lessThan=" + DEFAULT_PROCESS_ID);

        // Get all the caseFeeList where processId less than or equals to UPDATED_PROCESS_ID
        defaultCaseFeeShouldBeFound("processId.lessThan=" + UPDATED_PROCESS_ID);
    }


    @Test
    @Transactional
    public void getAllCaseFeesByFeeTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        FeeType feeType = FeeTypeResourceIntTest.createEntity(em);
        em.persist(feeType);
        em.flush();
        caseFee.setFeeType(feeType);
        caseFeeRepository.saveAndFlush(caseFee);
        Long feeTypeId = feeType.getId();

        // Get all the caseFeeList where feeType equals to feeTypeId
        defaultCaseFeeShouldBeFound("feeTypeId.equals=" + feeTypeId);

        // Get all the caseFeeList where feeType equals to feeTypeId + 1
        defaultCaseFeeShouldNotBeFound("feeTypeId.equals=" + (feeTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseFeesBySubcaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselSubCase subcase = VesselSubCaseResourceIntTest.createEntity(em);
        em.persist(subcase);
        em.flush();
        caseFee.setSubcase(subcase);
        caseFeeRepository.saveAndFlush(caseFee);
        Long subcaseId = subcase.getId();

        // Get all the caseFeeList where subcase equals to subcaseId
        defaultCaseFeeShouldBeFound("subcaseId.equals=" + subcaseId);

        // Get all the caseFeeList where subcase equals to subcaseId + 1
        defaultCaseFeeShouldNotBeFound("subcaseId.equals=" + (subcaseId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseFeesByCreditorIsEqualToSomething() throws Exception {
        // Initialize the database
        Creditor creditor = CreditorResourceIntTest.createEntity(em);
        em.persist(creditor);
        em.flush();
        caseFee.setCreditor(creditor);
        caseFeeRepository.saveAndFlush(caseFee);
        Long creditorId = creditor.getId();

        // Get all the caseFeeList where creditor equals to creditorId
        defaultCaseFeeShouldBeFound("creditorId.equals=" + creditorId);

        // Get all the caseFeeList where creditor equals to creditorId + 1
        defaultCaseFeeShouldNotBeFound("creditorId.equals=" + (creditorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCaseFeeShouldBeFound(String filter) throws Exception {
        restCaseFeeMockMvc.perform(get("/api/case-fees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseFee.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientNo").value(hasItem(DEFAULT_CLIENT_NO.toString())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].feeCostDate").value(hasItem(DEFAULT_FEE_COST_DATE.toString())))
            .andExpect(jsonPath("$.[*].feeCost").value(hasItem(DEFAULT_FEE_COST.intValue())))
            .andExpect(jsonPath("$.[*].feeCostDollar").value(hasItem(DEFAULT_FEE_COST_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].deduct").value(hasItem(DEFAULT_DEDUCT.intValue())))
            .andExpect(jsonPath("$.[*].deductCurrency").value(hasItem(DEFAULT_DEDUCT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].deductCurrencyRate").value(hasItem(DEFAULT_DEDUCT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].deductAmount").value(hasItem(DEFAULT_DEDUCT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].amountDollar").value(hasItem(DEFAULT_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].feeCreateUser").value(hasItem(DEFAULT_FEE_CREATE_USER.intValue())))
            .andExpect(jsonPath("$.[*].feeCreateDate").value(hasItem(DEFAULT_FEE_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].isSigned").value(hasItem(DEFAULT_IS_SIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].signUser").value(hasItem(DEFAULT_SIGN_USER.intValue())))
            .andExpect(jsonPath("$.[*].signDate").value(hasItem(DEFAULT_SIGN_DATE.intValue())))
            .andExpect(jsonPath("$.[*].processId").value(hasItem(DEFAULT_PROCESS_ID.intValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCaseFeeShouldNotBeFound(String filter) throws Exception {
        restCaseFeeMockMvc.perform(get("/api/case-fees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCaseFee() throws Exception {
        // Get the caseFee
        restCaseFeeMockMvc.perform(get("/api/case-fees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseFee() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        int databaseSizeBeforeUpdate = caseFeeRepository.findAll().size();

        // Update the caseFee
        CaseFee updatedCaseFee = caseFeeRepository.findById(caseFee.getId()).get();
        // Disconnect from session so that the updates on updatedCaseFee are not directly saved in db
        em.detach(updatedCaseFee);
        updatedCaseFee
            .clientNo(UPDATED_CLIENT_NO)
            .numberId(UPDATED_NUMBER_ID)
            .currency(UPDATED_CURRENCY)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .feeCostDate(UPDATED_FEE_COST_DATE)
            .feeCost(UPDATED_FEE_COST)
            .feeCostDollar(UPDATED_FEE_COST_DOLLAR)
            .deduct(UPDATED_DEDUCT)
            .deductCurrency(UPDATED_DEDUCT_CURRENCY)
            .deductCurrencyRate(UPDATED_DEDUCT_CURRENCY_RATE)
            .deductAmount(UPDATED_DEDUCT_AMOUNT)
            .amountDollar(UPDATED_AMOUNT_DOLLAR)
            .feeCreateUser(UPDATED_FEE_CREATE_USER)
            .feeCreateDate(UPDATED_FEE_CREATE_DATE)
            .remark(UPDATED_REMARK)
            .isSigned(UPDATED_IS_SIGNED)
            .signUser(UPDATED_SIGN_USER)
            .signDate(UPDATED_SIGN_DATE)
            .processId(UPDATED_PROCESS_ID);
        CaseFeeDTO caseFeeDTO = caseFeeMapper.toDto(updatedCaseFee);

        restCaseFeeMockMvc.perform(put("/api/case-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseFeeDTO)))
            .andExpect(status().isOk());

        // Validate the CaseFee in the database
        List<CaseFee> caseFeeList = caseFeeRepository.findAll();
        assertThat(caseFeeList).hasSize(databaseSizeBeforeUpdate);
        CaseFee testCaseFee = caseFeeList.get(caseFeeList.size() - 1);
        assertThat(testCaseFee.getClientNo()).isEqualTo(UPDATED_CLIENT_NO);
        assertThat(testCaseFee.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCaseFee.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCaseFee.getCurrencyRate()).isEqualTo(UPDATED_CURRENCY_RATE);
        assertThat(testCaseFee.getFeeCostDate()).isEqualTo(UPDATED_FEE_COST_DATE);
        assertThat(testCaseFee.getFeeCost()).isEqualTo(UPDATED_FEE_COST);
        assertThat(testCaseFee.getFeeCostDollar()).isEqualTo(UPDATED_FEE_COST_DOLLAR);
        assertThat(testCaseFee.getDeduct()).isEqualTo(UPDATED_DEDUCT);
        assertThat(testCaseFee.getDeductCurrency()).isEqualTo(UPDATED_DEDUCT_CURRENCY);
        assertThat(testCaseFee.getDeductCurrencyRate()).isEqualTo(UPDATED_DEDUCT_CURRENCY_RATE);
        assertThat(testCaseFee.getDeductAmount()).isEqualTo(UPDATED_DEDUCT_AMOUNT);
        assertThat(testCaseFee.getAmountDollar()).isEqualTo(UPDATED_AMOUNT_DOLLAR);
        assertThat(testCaseFee.getFeeCreateUser()).isEqualTo(UPDATED_FEE_CREATE_USER);
        assertThat(testCaseFee.getFeeCreateDate()).isEqualTo(UPDATED_FEE_CREATE_DATE);
        assertThat(testCaseFee.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCaseFee.isIsSigned()).isEqualTo(UPDATED_IS_SIGNED);
        assertThat(testCaseFee.getSignUser()).isEqualTo(UPDATED_SIGN_USER);
        assertThat(testCaseFee.getSignDate()).isEqualTo(UPDATED_SIGN_DATE);
        assertThat(testCaseFee.getProcessId()).isEqualTo(UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseFee() throws Exception {
        int databaseSizeBeforeUpdate = caseFeeRepository.findAll().size();

        // Create the CaseFee
        CaseFeeDTO caseFeeDTO = caseFeeMapper.toDto(caseFee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseFeeMockMvc.perform(put("/api/case-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseFeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseFee in the database
        List<CaseFee> caseFeeList = caseFeeRepository.findAll();
        assertThat(caseFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseFee() throws Exception {
        // Initialize the database
        caseFeeRepository.saveAndFlush(caseFee);

        int databaseSizeBeforeDelete = caseFeeRepository.findAll().size();

        // Get the caseFee
        restCaseFeeMockMvc.perform(delete("/api/case-fees/{id}", caseFee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseFee> caseFeeList = caseFeeRepository.findAll();
        assertThat(caseFeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseFee.class);
        CaseFee caseFee1 = new CaseFee();
        caseFee1.setId(1L);
        CaseFee caseFee2 = new CaseFee();
        caseFee2.setId(caseFee1.getId());
        assertThat(caseFee1).isEqualTo(caseFee2);
        caseFee2.setId(2L);
        assertThat(caseFee1).isNotEqualTo(caseFee2);
        caseFee1.setId(null);
        assertThat(caseFee1).isNotEqualTo(caseFee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseFeeDTO.class);
        CaseFeeDTO caseFeeDTO1 = new CaseFeeDTO();
        caseFeeDTO1.setId(1L);
        CaseFeeDTO caseFeeDTO2 = new CaseFeeDTO();
        assertThat(caseFeeDTO1).isNotEqualTo(caseFeeDTO2);
        caseFeeDTO2.setId(caseFeeDTO1.getId());
        assertThat(caseFeeDTO1).isEqualTo(caseFeeDTO2);
        caseFeeDTO2.setId(2L);
        assertThat(caseFeeDTO1).isNotEqualTo(caseFeeDTO2);
        caseFeeDTO1.setId(null);
        assertThat(caseFeeDTO1).isNotEqualTo(caseFeeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseFeeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseFeeMapper.fromId(null)).isNull();
    }
}
