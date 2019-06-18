package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CasePayment;
import com.cpi.claim.domain.PaymentType;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.domain.Creditor;
import com.cpi.claim.repository.CasePaymentRepository;
import com.cpi.claim.service.CasePaymentService;
import com.cpi.claim.service.dto.CasePaymentDTO;
import com.cpi.claim.service.mapper.CasePaymentMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CasePaymentCriteria;
import com.cpi.claim.service.CasePaymentQueryService;

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
 * Test class for the CasePaymentResource REST controller.
 *
 * @see CasePaymentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CasePaymentResourceIntTest {

    private static final String DEFAULT_CLIENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final Instant DEFAULT_PAY_COST_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAY_COST_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CURRENCY = 1L;
    private static final Long UPDATED_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PAY_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAY_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PAY_COST_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAY_COST_DOLLAR = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEDUCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final Long DEFAULT_FEE_CREATE_USER = 1L;
    private static final Long UPDATED_FEE_CREATE_USER = 2L;

    private static final Instant DEFAULT_FEE_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FEE_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private CasePaymentRepository casePaymentRepository;


    @Autowired
    private CasePaymentMapper casePaymentMapper;
    

    @Autowired
    private CasePaymentService casePaymentService;

    @Autowired
    private CasePaymentQueryService casePaymentQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCasePaymentMockMvc;

    private CasePayment casePayment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CasePaymentResource casePaymentResource = new CasePaymentResource(casePaymentService, casePaymentQueryService);
        this.restCasePaymentMockMvc = MockMvcBuilders.standaloneSetup(casePaymentResource)
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
    public static CasePayment createEntity(EntityManager em) {
        CasePayment casePayment = new CasePayment()
            .clientNo(DEFAULT_CLIENT_NO)
            .numberId(DEFAULT_NUMBER_ID)
            .payCostDate(DEFAULT_PAY_COST_DATE)
            .currency(DEFAULT_CURRENCY)
            .currencyRate(DEFAULT_CURRENCY_RATE)
            .payCost(DEFAULT_PAY_COST)
            .payCostDollar(DEFAULT_PAY_COST_DOLLAR)
            .deduct(DEFAULT_DEDUCT)
            .amount(DEFAULT_AMOUNT)
            .feeCreateUser(DEFAULT_FEE_CREATE_USER)
            .feeCreateDate(DEFAULT_FEE_CREATE_DATE)
            .remark(DEFAULT_REMARK);
        return casePayment;
    }

    @Before
    public void initTest() {
        casePayment = createEntity(em);
    }

    @Test
    @Transactional
    public void createCasePayment() throws Exception {
        int databaseSizeBeforeCreate = casePaymentRepository.findAll().size();

        // Create the CasePayment
        CasePaymentDTO casePaymentDTO = casePaymentMapper.toDto(casePayment);
        restCasePaymentMockMvc.perform(post("/api/case-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casePaymentDTO)))
            .andExpect(status().isCreated());

        // Validate the CasePayment in the database
        List<CasePayment> casePaymentList = casePaymentRepository.findAll();
        assertThat(casePaymentList).hasSize(databaseSizeBeforeCreate + 1);
        CasePayment testCasePayment = casePaymentList.get(casePaymentList.size() - 1);
        assertThat(testCasePayment.getClientNo()).isEqualTo(DEFAULT_CLIENT_NO);
        assertThat(testCasePayment.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCasePayment.getPayCostDate()).isEqualTo(DEFAULT_PAY_COST_DATE);
        assertThat(testCasePayment.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testCasePayment.getCurrencyRate()).isEqualTo(DEFAULT_CURRENCY_RATE);
        assertThat(testCasePayment.getPayCost()).isEqualTo(DEFAULT_PAY_COST);
        assertThat(testCasePayment.getPayCostDollar()).isEqualTo(DEFAULT_PAY_COST_DOLLAR);
        assertThat(testCasePayment.getDeduct()).isEqualTo(DEFAULT_DEDUCT);
        assertThat(testCasePayment.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCasePayment.getFeeCreateUser()).isEqualTo(DEFAULT_FEE_CREATE_USER);
        assertThat(testCasePayment.getFeeCreateDate()).isEqualTo(DEFAULT_FEE_CREATE_DATE);
        assertThat(testCasePayment.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createCasePaymentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = casePaymentRepository.findAll().size();

        // Create the CasePayment with an existing ID
        casePayment.setId(1L);
        CasePaymentDTO casePaymentDTO = casePaymentMapper.toDto(casePayment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCasePaymentMockMvc.perform(post("/api/case-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casePaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CasePayment in the database
        List<CasePayment> casePaymentList = casePaymentRepository.findAll();
        assertThat(casePaymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCasePayments() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList
        restCasePaymentMockMvc.perform(get("/api/case-payments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casePayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientNo").value(hasItem(DEFAULT_CLIENT_NO.toString())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].payCostDate").value(hasItem(DEFAULT_PAY_COST_DATE.toString())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].payCost").value(hasItem(DEFAULT_PAY_COST.intValue())))
            .andExpect(jsonPath("$.[*].payCostDollar").value(hasItem(DEFAULT_PAY_COST_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].deduct").value(hasItem(DEFAULT_DEDUCT.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].feeCreateUser").value(hasItem(DEFAULT_FEE_CREATE_USER.intValue())))
            .andExpect(jsonPath("$.[*].feeCreateDate").value(hasItem(DEFAULT_FEE_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }
    

    @Test
    @Transactional
    public void getCasePayment() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get the casePayment
        restCasePaymentMockMvc.perform(get("/api/case-payments/{id}", casePayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(casePayment.getId().intValue()))
            .andExpect(jsonPath("$.clientNo").value(DEFAULT_CLIENT_NO.toString()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.payCostDate").value(DEFAULT_PAY_COST_DATE.toString()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.intValue()))
            .andExpect(jsonPath("$.currencyRate").value(DEFAULT_CURRENCY_RATE.intValue()))
            .andExpect(jsonPath("$.payCost").value(DEFAULT_PAY_COST.intValue()))
            .andExpect(jsonPath("$.payCostDollar").value(DEFAULT_PAY_COST_DOLLAR.intValue()))
            .andExpect(jsonPath("$.deduct").value(DEFAULT_DEDUCT.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.feeCreateUser").value(DEFAULT_FEE_CREATE_USER.intValue()))
            .andExpect(jsonPath("$.feeCreateDate").value(DEFAULT_FEE_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByClientNoIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where clientNo equals to DEFAULT_CLIENT_NO
        defaultCasePaymentShouldBeFound("clientNo.equals=" + DEFAULT_CLIENT_NO);

        // Get all the casePaymentList where clientNo equals to UPDATED_CLIENT_NO
        defaultCasePaymentShouldNotBeFound("clientNo.equals=" + UPDATED_CLIENT_NO);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByClientNoIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where clientNo in DEFAULT_CLIENT_NO or UPDATED_CLIENT_NO
        defaultCasePaymentShouldBeFound("clientNo.in=" + DEFAULT_CLIENT_NO + "," + UPDATED_CLIENT_NO);

        // Get all the casePaymentList where clientNo equals to UPDATED_CLIENT_NO
        defaultCasePaymentShouldNotBeFound("clientNo.in=" + UPDATED_CLIENT_NO);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByClientNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where clientNo is not null
        defaultCasePaymentShouldBeFound("clientNo.specified=true");

        // Get all the casePaymentList where clientNo is null
        defaultCasePaymentShouldNotBeFound("clientNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where numberId equals to DEFAULT_NUMBER_ID
        defaultCasePaymentShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the casePaymentList where numberId equals to UPDATED_NUMBER_ID
        defaultCasePaymentShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCasePaymentShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the casePaymentList where numberId equals to UPDATED_NUMBER_ID
        defaultCasePaymentShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where numberId is not null
        defaultCasePaymentShouldBeFound("numberId.specified=true");

        // Get all the casePaymentList where numberId is null
        defaultCasePaymentShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultCasePaymentShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the casePaymentList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultCasePaymentShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultCasePaymentShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the casePaymentList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultCasePaymentShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllCasePaymentsByPayCostDateIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where payCostDate equals to DEFAULT_PAY_COST_DATE
        defaultCasePaymentShouldBeFound("payCostDate.equals=" + DEFAULT_PAY_COST_DATE);

        // Get all the casePaymentList where payCostDate equals to UPDATED_PAY_COST_DATE
        defaultCasePaymentShouldNotBeFound("payCostDate.equals=" + UPDATED_PAY_COST_DATE);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByPayCostDateIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where payCostDate in DEFAULT_PAY_COST_DATE or UPDATED_PAY_COST_DATE
        defaultCasePaymentShouldBeFound("payCostDate.in=" + DEFAULT_PAY_COST_DATE + "," + UPDATED_PAY_COST_DATE);

        // Get all the casePaymentList where payCostDate equals to UPDATED_PAY_COST_DATE
        defaultCasePaymentShouldNotBeFound("payCostDate.in=" + UPDATED_PAY_COST_DATE);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByPayCostDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where payCostDate is not null
        defaultCasePaymentShouldBeFound("payCostDate.specified=true");

        // Get all the casePaymentList where payCostDate is null
        defaultCasePaymentShouldNotBeFound("payCostDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where currency equals to DEFAULT_CURRENCY
        defaultCasePaymentShouldBeFound("currency.equals=" + DEFAULT_CURRENCY);

        // Get all the casePaymentList where currency equals to UPDATED_CURRENCY
        defaultCasePaymentShouldNotBeFound("currency.equals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where currency in DEFAULT_CURRENCY or UPDATED_CURRENCY
        defaultCasePaymentShouldBeFound("currency.in=" + DEFAULT_CURRENCY + "," + UPDATED_CURRENCY);

        // Get all the casePaymentList where currency equals to UPDATED_CURRENCY
        defaultCasePaymentShouldNotBeFound("currency.in=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where currency is not null
        defaultCasePaymentShouldBeFound("currency.specified=true");

        // Get all the casePaymentList where currency is null
        defaultCasePaymentShouldNotBeFound("currency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where currency greater than or equals to DEFAULT_CURRENCY
        defaultCasePaymentShouldBeFound("currency.greaterOrEqualThan=" + DEFAULT_CURRENCY);

        // Get all the casePaymentList where currency greater than or equals to UPDATED_CURRENCY
        defaultCasePaymentShouldNotBeFound("currency.greaterOrEqualThan=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where currency less than or equals to DEFAULT_CURRENCY
        defaultCasePaymentShouldNotBeFound("currency.lessThan=" + DEFAULT_CURRENCY);

        // Get all the casePaymentList where currency less than or equals to UPDATED_CURRENCY
        defaultCasePaymentShouldBeFound("currency.lessThan=" + UPDATED_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCasePaymentsByCurrencyRateIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where currencyRate equals to DEFAULT_CURRENCY_RATE
        defaultCasePaymentShouldBeFound("currencyRate.equals=" + DEFAULT_CURRENCY_RATE);

        // Get all the casePaymentList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultCasePaymentShouldNotBeFound("currencyRate.equals=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByCurrencyRateIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where currencyRate in DEFAULT_CURRENCY_RATE or UPDATED_CURRENCY_RATE
        defaultCasePaymentShouldBeFound("currencyRate.in=" + DEFAULT_CURRENCY_RATE + "," + UPDATED_CURRENCY_RATE);

        // Get all the casePaymentList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultCasePaymentShouldNotBeFound("currencyRate.in=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByCurrencyRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where currencyRate is not null
        defaultCasePaymentShouldBeFound("currencyRate.specified=true");

        // Get all the casePaymentList where currencyRate is null
        defaultCasePaymentShouldNotBeFound("currencyRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByPayCostIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where payCost equals to DEFAULT_PAY_COST
        defaultCasePaymentShouldBeFound("payCost.equals=" + DEFAULT_PAY_COST);

        // Get all the casePaymentList where payCost equals to UPDATED_PAY_COST
        defaultCasePaymentShouldNotBeFound("payCost.equals=" + UPDATED_PAY_COST);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByPayCostIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where payCost in DEFAULT_PAY_COST or UPDATED_PAY_COST
        defaultCasePaymentShouldBeFound("payCost.in=" + DEFAULT_PAY_COST + "," + UPDATED_PAY_COST);

        // Get all the casePaymentList where payCost equals to UPDATED_PAY_COST
        defaultCasePaymentShouldNotBeFound("payCost.in=" + UPDATED_PAY_COST);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByPayCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where payCost is not null
        defaultCasePaymentShouldBeFound("payCost.specified=true");

        // Get all the casePaymentList where payCost is null
        defaultCasePaymentShouldNotBeFound("payCost.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByPayCostDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where payCostDollar equals to DEFAULT_PAY_COST_DOLLAR
        defaultCasePaymentShouldBeFound("payCostDollar.equals=" + DEFAULT_PAY_COST_DOLLAR);

        // Get all the casePaymentList where payCostDollar equals to UPDATED_PAY_COST_DOLLAR
        defaultCasePaymentShouldNotBeFound("payCostDollar.equals=" + UPDATED_PAY_COST_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByPayCostDollarIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where payCostDollar in DEFAULT_PAY_COST_DOLLAR or UPDATED_PAY_COST_DOLLAR
        defaultCasePaymentShouldBeFound("payCostDollar.in=" + DEFAULT_PAY_COST_DOLLAR + "," + UPDATED_PAY_COST_DOLLAR);

        // Get all the casePaymentList where payCostDollar equals to UPDATED_PAY_COST_DOLLAR
        defaultCasePaymentShouldNotBeFound("payCostDollar.in=" + UPDATED_PAY_COST_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByPayCostDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where payCostDollar is not null
        defaultCasePaymentShouldBeFound("payCostDollar.specified=true");

        // Get all the casePaymentList where payCostDollar is null
        defaultCasePaymentShouldNotBeFound("payCostDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByDeductIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where deduct equals to DEFAULT_DEDUCT
        defaultCasePaymentShouldBeFound("deduct.equals=" + DEFAULT_DEDUCT);

        // Get all the casePaymentList where deduct equals to UPDATED_DEDUCT
        defaultCasePaymentShouldNotBeFound("deduct.equals=" + UPDATED_DEDUCT);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByDeductIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where deduct in DEFAULT_DEDUCT or UPDATED_DEDUCT
        defaultCasePaymentShouldBeFound("deduct.in=" + DEFAULT_DEDUCT + "," + UPDATED_DEDUCT);

        // Get all the casePaymentList where deduct equals to UPDATED_DEDUCT
        defaultCasePaymentShouldNotBeFound("deduct.in=" + UPDATED_DEDUCT);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByDeductIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where deduct is not null
        defaultCasePaymentShouldBeFound("deduct.specified=true");

        // Get all the casePaymentList where deduct is null
        defaultCasePaymentShouldNotBeFound("deduct.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where amount equals to DEFAULT_AMOUNT
        defaultCasePaymentShouldBeFound("amount.equals=" + DEFAULT_AMOUNT);

        // Get all the casePaymentList where amount equals to UPDATED_AMOUNT
        defaultCasePaymentShouldNotBeFound("amount.equals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByAmountIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where amount in DEFAULT_AMOUNT or UPDATED_AMOUNT
        defaultCasePaymentShouldBeFound("amount.in=" + DEFAULT_AMOUNT + "," + UPDATED_AMOUNT);

        // Get all the casePaymentList where amount equals to UPDATED_AMOUNT
        defaultCasePaymentShouldNotBeFound("amount.in=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where amount is not null
        defaultCasePaymentShouldBeFound("amount.specified=true");

        // Get all the casePaymentList where amount is null
        defaultCasePaymentShouldNotBeFound("amount.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByFeeCreateUserIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where feeCreateUser equals to DEFAULT_FEE_CREATE_USER
        defaultCasePaymentShouldBeFound("feeCreateUser.equals=" + DEFAULT_FEE_CREATE_USER);

        // Get all the casePaymentList where feeCreateUser equals to UPDATED_FEE_CREATE_USER
        defaultCasePaymentShouldNotBeFound("feeCreateUser.equals=" + UPDATED_FEE_CREATE_USER);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByFeeCreateUserIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where feeCreateUser in DEFAULT_FEE_CREATE_USER or UPDATED_FEE_CREATE_USER
        defaultCasePaymentShouldBeFound("feeCreateUser.in=" + DEFAULT_FEE_CREATE_USER + "," + UPDATED_FEE_CREATE_USER);

        // Get all the casePaymentList where feeCreateUser equals to UPDATED_FEE_CREATE_USER
        defaultCasePaymentShouldNotBeFound("feeCreateUser.in=" + UPDATED_FEE_CREATE_USER);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByFeeCreateUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where feeCreateUser is not null
        defaultCasePaymentShouldBeFound("feeCreateUser.specified=true");

        // Get all the casePaymentList where feeCreateUser is null
        defaultCasePaymentShouldNotBeFound("feeCreateUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByFeeCreateUserIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where feeCreateUser greater than or equals to DEFAULT_FEE_CREATE_USER
        defaultCasePaymentShouldBeFound("feeCreateUser.greaterOrEqualThan=" + DEFAULT_FEE_CREATE_USER);

        // Get all the casePaymentList where feeCreateUser greater than or equals to UPDATED_FEE_CREATE_USER
        defaultCasePaymentShouldNotBeFound("feeCreateUser.greaterOrEqualThan=" + UPDATED_FEE_CREATE_USER);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByFeeCreateUserIsLessThanSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where feeCreateUser less than or equals to DEFAULT_FEE_CREATE_USER
        defaultCasePaymentShouldNotBeFound("feeCreateUser.lessThan=" + DEFAULT_FEE_CREATE_USER);

        // Get all the casePaymentList where feeCreateUser less than or equals to UPDATED_FEE_CREATE_USER
        defaultCasePaymentShouldBeFound("feeCreateUser.lessThan=" + UPDATED_FEE_CREATE_USER);
    }


    @Test
    @Transactional
    public void getAllCasePaymentsByFeeCreateDateIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where feeCreateDate equals to DEFAULT_FEE_CREATE_DATE
        defaultCasePaymentShouldBeFound("feeCreateDate.equals=" + DEFAULT_FEE_CREATE_DATE);

        // Get all the casePaymentList where feeCreateDate equals to UPDATED_FEE_CREATE_DATE
        defaultCasePaymentShouldNotBeFound("feeCreateDate.equals=" + UPDATED_FEE_CREATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByFeeCreateDateIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where feeCreateDate in DEFAULT_FEE_CREATE_DATE or UPDATED_FEE_CREATE_DATE
        defaultCasePaymentShouldBeFound("feeCreateDate.in=" + DEFAULT_FEE_CREATE_DATE + "," + UPDATED_FEE_CREATE_DATE);

        // Get all the casePaymentList where feeCreateDate equals to UPDATED_FEE_CREATE_DATE
        defaultCasePaymentShouldNotBeFound("feeCreateDate.in=" + UPDATED_FEE_CREATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByFeeCreateDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        // Get all the casePaymentList where feeCreateDate is not null
        defaultCasePaymentShouldBeFound("feeCreateDate.specified=true");

        // Get all the casePaymentList where feeCreateDate is null
        defaultCasePaymentShouldNotBeFound("feeCreateDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentsByPaymentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        PaymentType paymentType = PaymentTypeResourceIntTest.createEntity(em);
        em.persist(paymentType);
        em.flush();
        casePayment.setPaymentType(paymentType);
        casePaymentRepository.saveAndFlush(casePayment);
        Long paymentTypeId = paymentType.getId();

        // Get all the casePaymentList where paymentType equals to paymentTypeId
        defaultCasePaymentShouldBeFound("paymentTypeId.equals=" + paymentTypeId);

        // Get all the casePaymentList where paymentType equals to paymentTypeId + 1
        defaultCasePaymentShouldNotBeFound("paymentTypeId.equals=" + (paymentTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllCasePaymentsBySubcaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselSubCase subcase = VesselSubCaseResourceIntTest.createEntity(em);
        em.persist(subcase);
        em.flush();
        casePayment.setSubcase(subcase);
        casePaymentRepository.saveAndFlush(casePayment);
        Long subcaseId = subcase.getId();

        // Get all the casePaymentList where subcase equals to subcaseId
        defaultCasePaymentShouldBeFound("subcaseId.equals=" + subcaseId);

        // Get all the casePaymentList where subcase equals to subcaseId + 1
        defaultCasePaymentShouldNotBeFound("subcaseId.equals=" + (subcaseId + 1));
    }


    @Test
    @Transactional
    public void getAllCasePaymentsByCreditorIsEqualToSomething() throws Exception {
        // Initialize the database
        Creditor creditor = CreditorResourceIntTest.createEntity(em);
        em.persist(creditor);
        em.flush();
        casePayment.setCreditor(creditor);
        casePaymentRepository.saveAndFlush(casePayment);
        Long creditorId = creditor.getId();

        // Get all the casePaymentList where creditor equals to creditorId
        defaultCasePaymentShouldBeFound("creditorId.equals=" + creditorId);

        // Get all the casePaymentList where creditor equals to creditorId + 1
        defaultCasePaymentShouldNotBeFound("creditorId.equals=" + (creditorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCasePaymentShouldBeFound(String filter) throws Exception {
        restCasePaymentMockMvc.perform(get("/api/case-payments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casePayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientNo").value(hasItem(DEFAULT_CLIENT_NO.toString())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].payCostDate").value(hasItem(DEFAULT_PAY_COST_DATE.toString())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].payCost").value(hasItem(DEFAULT_PAY_COST.intValue())))
            .andExpect(jsonPath("$.[*].payCostDollar").value(hasItem(DEFAULT_PAY_COST_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].deduct").value(hasItem(DEFAULT_DEDUCT.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].feeCreateUser").value(hasItem(DEFAULT_FEE_CREATE_USER.intValue())))
            .andExpect(jsonPath("$.[*].feeCreateDate").value(hasItem(DEFAULT_FEE_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCasePaymentShouldNotBeFound(String filter) throws Exception {
        restCasePaymentMockMvc.perform(get("/api/case-payments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCasePayment() throws Exception {
        // Get the casePayment
        restCasePaymentMockMvc.perform(get("/api/case-payments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCasePayment() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        int databaseSizeBeforeUpdate = casePaymentRepository.findAll().size();

        // Update the casePayment
        CasePayment updatedCasePayment = casePaymentRepository.findById(casePayment.getId()).get();
        // Disconnect from session so that the updates on updatedCasePayment are not directly saved in db
        em.detach(updatedCasePayment);
        updatedCasePayment
            .clientNo(UPDATED_CLIENT_NO)
            .numberId(UPDATED_NUMBER_ID)
            .payCostDate(UPDATED_PAY_COST_DATE)
            .currency(UPDATED_CURRENCY)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .payCost(UPDATED_PAY_COST)
            .payCostDollar(UPDATED_PAY_COST_DOLLAR)
            .deduct(UPDATED_DEDUCT)
            .amount(UPDATED_AMOUNT)
            .feeCreateUser(UPDATED_FEE_CREATE_USER)
            .feeCreateDate(UPDATED_FEE_CREATE_DATE)
            .remark(UPDATED_REMARK);
        CasePaymentDTO casePaymentDTO = casePaymentMapper.toDto(updatedCasePayment);

        restCasePaymentMockMvc.perform(put("/api/case-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casePaymentDTO)))
            .andExpect(status().isOk());

        // Validate the CasePayment in the database
        List<CasePayment> casePaymentList = casePaymentRepository.findAll();
        assertThat(casePaymentList).hasSize(databaseSizeBeforeUpdate);
        CasePayment testCasePayment = casePaymentList.get(casePaymentList.size() - 1);
        assertThat(testCasePayment.getClientNo()).isEqualTo(UPDATED_CLIENT_NO);
        assertThat(testCasePayment.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCasePayment.getPayCostDate()).isEqualTo(UPDATED_PAY_COST_DATE);
        assertThat(testCasePayment.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCasePayment.getCurrencyRate()).isEqualTo(UPDATED_CURRENCY_RATE);
        assertThat(testCasePayment.getPayCost()).isEqualTo(UPDATED_PAY_COST);
        assertThat(testCasePayment.getPayCostDollar()).isEqualTo(UPDATED_PAY_COST_DOLLAR);
        assertThat(testCasePayment.getDeduct()).isEqualTo(UPDATED_DEDUCT);
        assertThat(testCasePayment.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCasePayment.getFeeCreateUser()).isEqualTo(UPDATED_FEE_CREATE_USER);
        assertThat(testCasePayment.getFeeCreateDate()).isEqualTo(UPDATED_FEE_CREATE_DATE);
        assertThat(testCasePayment.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingCasePayment() throws Exception {
        int databaseSizeBeforeUpdate = casePaymentRepository.findAll().size();

        // Create the CasePayment
        CasePaymentDTO casePaymentDTO = casePaymentMapper.toDto(casePayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCasePaymentMockMvc.perform(put("/api/case-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casePaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CasePayment in the database
        List<CasePayment> casePaymentList = casePaymentRepository.findAll();
        assertThat(casePaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCasePayment() throws Exception {
        // Initialize the database
        casePaymentRepository.saveAndFlush(casePayment);

        int databaseSizeBeforeDelete = casePaymentRepository.findAll().size();

        // Get the casePayment
        restCasePaymentMockMvc.perform(delete("/api/case-payments/{id}", casePayment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CasePayment> casePaymentList = casePaymentRepository.findAll();
        assertThat(casePaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CasePayment.class);
        CasePayment casePayment1 = new CasePayment();
        casePayment1.setId(1L);
        CasePayment casePayment2 = new CasePayment();
        casePayment2.setId(casePayment1.getId());
        assertThat(casePayment1).isEqualTo(casePayment2);
        casePayment2.setId(2L);
        assertThat(casePayment1).isNotEqualTo(casePayment2);
        casePayment1.setId(null);
        assertThat(casePayment1).isNotEqualTo(casePayment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CasePaymentDTO.class);
        CasePaymentDTO casePaymentDTO1 = new CasePaymentDTO();
        casePaymentDTO1.setId(1L);
        CasePaymentDTO casePaymentDTO2 = new CasePaymentDTO();
        assertThat(casePaymentDTO1).isNotEqualTo(casePaymentDTO2);
        casePaymentDTO2.setId(casePaymentDTO1.getId());
        assertThat(casePaymentDTO1).isEqualTo(casePaymentDTO2);
        casePaymentDTO2.setId(2L);
        assertThat(casePaymentDTO1).isNotEqualTo(casePaymentDTO2);
        casePaymentDTO1.setId(null);
        assertThat(casePaymentDTO1).isNotEqualTo(casePaymentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(casePaymentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(casePaymentMapper.fromId(null)).isNull();
    }
}
