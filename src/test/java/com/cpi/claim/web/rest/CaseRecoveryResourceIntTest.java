package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CaseRecovery;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.domain.RecoveryType;
import com.cpi.claim.domain.Creditor;
import com.cpi.claim.repository.CaseRecoveryRepository;
import com.cpi.claim.service.CaseRecoveryService;
import com.cpi.claim.service.dto.CaseRecoveryDTO;
import com.cpi.claim.service.mapper.CaseRecoveryMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseRecoveryCriteria;
import com.cpi.claim.service.CaseRecoveryQueryService;

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
 * Test class for the CaseRecoveryResource REST controller.
 *
 * @see CaseRecoveryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseRecoveryResourceIntTest {

    private static final String DEFAULT_CLIENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final Long DEFAULT_CURRENCY = 1L;
    private static final Long UPDATED_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_RATE = new BigDecimal(2);

    private static final Instant DEFAULT_ISSUE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ISSUE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_ISSUE_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ISSUE_AMOUNT = new BigDecimal(2);

    private static final Instant DEFAULT_RECEIVED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RECEIVED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_RECEIVED_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_RECEIVED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT_DOLLAR = new BigDecimal(2);

    private static final Instant DEFAULT_REGISTER_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REGISTER_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_REGISTER_USER = 1L;
    private static final Long UPDATED_REGISTER_USER = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private CaseRecoveryRepository caseRecoveryRepository;


    @Autowired
    private CaseRecoveryMapper caseRecoveryMapper;
    

    @Autowired
    private CaseRecoveryService caseRecoveryService;

    @Autowired
    private CaseRecoveryQueryService caseRecoveryQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaseRecoveryMockMvc;

    private CaseRecovery caseRecovery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseRecoveryResource caseRecoveryResource = new CaseRecoveryResource(caseRecoveryService, caseRecoveryQueryService);
        this.restCaseRecoveryMockMvc = MockMvcBuilders.standaloneSetup(caseRecoveryResource)
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
    public static CaseRecovery createEntity(EntityManager em) {
        CaseRecovery caseRecovery = new CaseRecovery()
            .clientNo(DEFAULT_CLIENT_NO)
            .numberId(DEFAULT_NUMBER_ID)
            .currency(DEFAULT_CURRENCY)
            .currencyRate(DEFAULT_CURRENCY_RATE)
            .issueDate(DEFAULT_ISSUE_DATE)
            .issueAmount(DEFAULT_ISSUE_AMOUNT)
            .receivedDate(DEFAULT_RECEIVED_DATE)
            .receivedAmount(DEFAULT_RECEIVED_AMOUNT)
            .amountDollar(DEFAULT_AMOUNT_DOLLAR)
            .registerDate(DEFAULT_REGISTER_DATE)
            .registerUser(DEFAULT_REGISTER_USER)
            .remark(DEFAULT_REMARK);
        return caseRecovery;
    }

    @Before
    public void initTest() {
        caseRecovery = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseRecovery() throws Exception {
        int databaseSizeBeforeCreate = caseRecoveryRepository.findAll().size();

        // Create the CaseRecovery
        CaseRecoveryDTO caseRecoveryDTO = caseRecoveryMapper.toDto(caseRecovery);
        restCaseRecoveryMockMvc.perform(post("/api/case-recoveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseRecoveryDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseRecovery in the database
        List<CaseRecovery> caseRecoveryList = caseRecoveryRepository.findAll();
        assertThat(caseRecoveryList).hasSize(databaseSizeBeforeCreate + 1);
        CaseRecovery testCaseRecovery = caseRecoveryList.get(caseRecoveryList.size() - 1);
        assertThat(testCaseRecovery.getClientNo()).isEqualTo(DEFAULT_CLIENT_NO);
        assertThat(testCaseRecovery.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCaseRecovery.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testCaseRecovery.getCurrencyRate()).isEqualTo(DEFAULT_CURRENCY_RATE);
        assertThat(testCaseRecovery.getIssueDate()).isEqualTo(DEFAULT_ISSUE_DATE);
        assertThat(testCaseRecovery.getIssueAmount()).isEqualTo(DEFAULT_ISSUE_AMOUNT);
        assertThat(testCaseRecovery.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
        assertThat(testCaseRecovery.getReceivedAmount()).isEqualTo(DEFAULT_RECEIVED_AMOUNT);
        assertThat(testCaseRecovery.getAmountDollar()).isEqualTo(DEFAULT_AMOUNT_DOLLAR);
        assertThat(testCaseRecovery.getRegisterDate()).isEqualTo(DEFAULT_REGISTER_DATE);
        assertThat(testCaseRecovery.getRegisterUser()).isEqualTo(DEFAULT_REGISTER_USER);
        assertThat(testCaseRecovery.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createCaseRecoveryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseRecoveryRepository.findAll().size();

        // Create the CaseRecovery with an existing ID
        caseRecovery.setId(1L);
        CaseRecoveryDTO caseRecoveryDTO = caseRecoveryMapper.toDto(caseRecovery);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseRecoveryMockMvc.perform(post("/api/case-recoveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseRecoveryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseRecovery in the database
        List<CaseRecovery> caseRecoveryList = caseRecoveryRepository.findAll();
        assertThat(caseRecoveryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveries() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList
        restCaseRecoveryMockMvc.perform(get("/api/case-recoveries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseRecovery.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientNo").value(hasItem(DEFAULT_CLIENT_NO.toString())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].issueDate").value(hasItem(DEFAULT_ISSUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].issueAmount").value(hasItem(DEFAULT_ISSUE_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].receivedDate").value(hasItem(DEFAULT_RECEIVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].receivedAmount").value(hasItem(DEFAULT_RECEIVED_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].amountDollar").value(hasItem(DEFAULT_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].registerDate").value(hasItem(DEFAULT_REGISTER_DATE.toString())))
            .andExpect(jsonPath("$.[*].registerUser").value(hasItem(DEFAULT_REGISTER_USER.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }
    

    @Test
    @Transactional
    public void getCaseRecovery() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get the caseRecovery
        restCaseRecoveryMockMvc.perform(get("/api/case-recoveries/{id}", caseRecovery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseRecovery.getId().intValue()))
            .andExpect(jsonPath("$.clientNo").value(DEFAULT_CLIENT_NO.toString()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.intValue()))
            .andExpect(jsonPath("$.currencyRate").value(DEFAULT_CURRENCY_RATE.intValue()))
            .andExpect(jsonPath("$.issueDate").value(DEFAULT_ISSUE_DATE.toString()))
            .andExpect(jsonPath("$.issueAmount").value(DEFAULT_ISSUE_AMOUNT.intValue()))
            .andExpect(jsonPath("$.receivedDate").value(DEFAULT_RECEIVED_DATE.toString()))
            .andExpect(jsonPath("$.receivedAmount").value(DEFAULT_RECEIVED_AMOUNT.intValue()))
            .andExpect(jsonPath("$.amountDollar").value(DEFAULT_AMOUNT_DOLLAR.intValue()))
            .andExpect(jsonPath("$.registerDate").value(DEFAULT_REGISTER_DATE.toString()))
            .andExpect(jsonPath("$.registerUser").value(DEFAULT_REGISTER_USER.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByClientNoIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where clientNo equals to DEFAULT_CLIENT_NO
        defaultCaseRecoveryShouldBeFound("clientNo.equals=" + DEFAULT_CLIENT_NO);

        // Get all the caseRecoveryList where clientNo equals to UPDATED_CLIENT_NO
        defaultCaseRecoveryShouldNotBeFound("clientNo.equals=" + UPDATED_CLIENT_NO);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByClientNoIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where clientNo in DEFAULT_CLIENT_NO or UPDATED_CLIENT_NO
        defaultCaseRecoveryShouldBeFound("clientNo.in=" + DEFAULT_CLIENT_NO + "," + UPDATED_CLIENT_NO);

        // Get all the caseRecoveryList where clientNo equals to UPDATED_CLIENT_NO
        defaultCaseRecoveryShouldNotBeFound("clientNo.in=" + UPDATED_CLIENT_NO);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByClientNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where clientNo is not null
        defaultCaseRecoveryShouldBeFound("clientNo.specified=true");

        // Get all the caseRecoveryList where clientNo is null
        defaultCaseRecoveryShouldNotBeFound("clientNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where numberId equals to DEFAULT_NUMBER_ID
        defaultCaseRecoveryShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the caseRecoveryList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseRecoveryShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCaseRecoveryShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the caseRecoveryList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseRecoveryShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where numberId is not null
        defaultCaseRecoveryShouldBeFound("numberId.specified=true");

        // Get all the caseRecoveryList where numberId is null
        defaultCaseRecoveryShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultCaseRecoveryShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseRecoveryList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultCaseRecoveryShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultCaseRecoveryShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseRecoveryList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultCaseRecoveryShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseRecoveriesByCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where currency equals to DEFAULT_CURRENCY
        defaultCaseRecoveryShouldBeFound("currency.equals=" + DEFAULT_CURRENCY);

        // Get all the caseRecoveryList where currency equals to UPDATED_CURRENCY
        defaultCaseRecoveryShouldNotBeFound("currency.equals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where currency in DEFAULT_CURRENCY or UPDATED_CURRENCY
        defaultCaseRecoveryShouldBeFound("currency.in=" + DEFAULT_CURRENCY + "," + UPDATED_CURRENCY);

        // Get all the caseRecoveryList where currency equals to UPDATED_CURRENCY
        defaultCaseRecoveryShouldNotBeFound("currency.in=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where currency is not null
        defaultCaseRecoveryShouldBeFound("currency.specified=true");

        // Get all the caseRecoveryList where currency is null
        defaultCaseRecoveryShouldNotBeFound("currency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where currency greater than or equals to DEFAULT_CURRENCY
        defaultCaseRecoveryShouldBeFound("currency.greaterOrEqualThan=" + DEFAULT_CURRENCY);

        // Get all the caseRecoveryList where currency greater than or equals to UPDATED_CURRENCY
        defaultCaseRecoveryShouldNotBeFound("currency.greaterOrEqualThan=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where currency less than or equals to DEFAULT_CURRENCY
        defaultCaseRecoveryShouldNotBeFound("currency.lessThan=" + DEFAULT_CURRENCY);

        // Get all the caseRecoveryList where currency less than or equals to UPDATED_CURRENCY
        defaultCaseRecoveryShouldBeFound("currency.lessThan=" + UPDATED_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCaseRecoveriesByCurrencyRateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where currencyRate equals to DEFAULT_CURRENCY_RATE
        defaultCaseRecoveryShouldBeFound("currencyRate.equals=" + DEFAULT_CURRENCY_RATE);

        // Get all the caseRecoveryList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultCaseRecoveryShouldNotBeFound("currencyRate.equals=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByCurrencyRateIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where currencyRate in DEFAULT_CURRENCY_RATE or UPDATED_CURRENCY_RATE
        defaultCaseRecoveryShouldBeFound("currencyRate.in=" + DEFAULT_CURRENCY_RATE + "," + UPDATED_CURRENCY_RATE);

        // Get all the caseRecoveryList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultCaseRecoveryShouldNotBeFound("currencyRate.in=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByCurrencyRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where currencyRate is not null
        defaultCaseRecoveryShouldBeFound("currencyRate.specified=true");

        // Get all the caseRecoveryList where currencyRate is null
        defaultCaseRecoveryShouldNotBeFound("currencyRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByIssueDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where issueDate equals to DEFAULT_ISSUE_DATE
        defaultCaseRecoveryShouldBeFound("issueDate.equals=" + DEFAULT_ISSUE_DATE);

        // Get all the caseRecoveryList where issueDate equals to UPDATED_ISSUE_DATE
        defaultCaseRecoveryShouldNotBeFound("issueDate.equals=" + UPDATED_ISSUE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByIssueDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where issueDate in DEFAULT_ISSUE_DATE or UPDATED_ISSUE_DATE
        defaultCaseRecoveryShouldBeFound("issueDate.in=" + DEFAULT_ISSUE_DATE + "," + UPDATED_ISSUE_DATE);

        // Get all the caseRecoveryList where issueDate equals to UPDATED_ISSUE_DATE
        defaultCaseRecoveryShouldNotBeFound("issueDate.in=" + UPDATED_ISSUE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByIssueDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where issueDate is not null
        defaultCaseRecoveryShouldBeFound("issueDate.specified=true");

        // Get all the caseRecoveryList where issueDate is null
        defaultCaseRecoveryShouldNotBeFound("issueDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByIssueAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where issueAmount equals to DEFAULT_ISSUE_AMOUNT
        defaultCaseRecoveryShouldBeFound("issueAmount.equals=" + DEFAULT_ISSUE_AMOUNT);

        // Get all the caseRecoveryList where issueAmount equals to UPDATED_ISSUE_AMOUNT
        defaultCaseRecoveryShouldNotBeFound("issueAmount.equals=" + UPDATED_ISSUE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByIssueAmountIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where issueAmount in DEFAULT_ISSUE_AMOUNT or UPDATED_ISSUE_AMOUNT
        defaultCaseRecoveryShouldBeFound("issueAmount.in=" + DEFAULT_ISSUE_AMOUNT + "," + UPDATED_ISSUE_AMOUNT);

        // Get all the caseRecoveryList where issueAmount equals to UPDATED_ISSUE_AMOUNT
        defaultCaseRecoveryShouldNotBeFound("issueAmount.in=" + UPDATED_ISSUE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByIssueAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where issueAmount is not null
        defaultCaseRecoveryShouldBeFound("issueAmount.specified=true");

        // Get all the caseRecoveryList where issueAmount is null
        defaultCaseRecoveryShouldNotBeFound("issueAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByReceivedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where receivedDate equals to DEFAULT_RECEIVED_DATE
        defaultCaseRecoveryShouldBeFound("receivedDate.equals=" + DEFAULT_RECEIVED_DATE);

        // Get all the caseRecoveryList where receivedDate equals to UPDATED_RECEIVED_DATE
        defaultCaseRecoveryShouldNotBeFound("receivedDate.equals=" + UPDATED_RECEIVED_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByReceivedDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where receivedDate in DEFAULT_RECEIVED_DATE or UPDATED_RECEIVED_DATE
        defaultCaseRecoveryShouldBeFound("receivedDate.in=" + DEFAULT_RECEIVED_DATE + "," + UPDATED_RECEIVED_DATE);

        // Get all the caseRecoveryList where receivedDate equals to UPDATED_RECEIVED_DATE
        defaultCaseRecoveryShouldNotBeFound("receivedDate.in=" + UPDATED_RECEIVED_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByReceivedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where receivedDate is not null
        defaultCaseRecoveryShouldBeFound("receivedDate.specified=true");

        // Get all the caseRecoveryList where receivedDate is null
        defaultCaseRecoveryShouldNotBeFound("receivedDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByReceivedAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where receivedAmount equals to DEFAULT_RECEIVED_AMOUNT
        defaultCaseRecoveryShouldBeFound("receivedAmount.equals=" + DEFAULT_RECEIVED_AMOUNT);

        // Get all the caseRecoveryList where receivedAmount equals to UPDATED_RECEIVED_AMOUNT
        defaultCaseRecoveryShouldNotBeFound("receivedAmount.equals=" + UPDATED_RECEIVED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByReceivedAmountIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where receivedAmount in DEFAULT_RECEIVED_AMOUNT or UPDATED_RECEIVED_AMOUNT
        defaultCaseRecoveryShouldBeFound("receivedAmount.in=" + DEFAULT_RECEIVED_AMOUNT + "," + UPDATED_RECEIVED_AMOUNT);

        // Get all the caseRecoveryList where receivedAmount equals to UPDATED_RECEIVED_AMOUNT
        defaultCaseRecoveryShouldNotBeFound("receivedAmount.in=" + UPDATED_RECEIVED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByReceivedAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where receivedAmount is not null
        defaultCaseRecoveryShouldBeFound("receivedAmount.specified=true");

        // Get all the caseRecoveryList where receivedAmount is null
        defaultCaseRecoveryShouldNotBeFound("receivedAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByAmountDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where amountDollar equals to DEFAULT_AMOUNT_DOLLAR
        defaultCaseRecoveryShouldBeFound("amountDollar.equals=" + DEFAULT_AMOUNT_DOLLAR);

        // Get all the caseRecoveryList where amountDollar equals to UPDATED_AMOUNT_DOLLAR
        defaultCaseRecoveryShouldNotBeFound("amountDollar.equals=" + UPDATED_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByAmountDollarIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where amountDollar in DEFAULT_AMOUNT_DOLLAR or UPDATED_AMOUNT_DOLLAR
        defaultCaseRecoveryShouldBeFound("amountDollar.in=" + DEFAULT_AMOUNT_DOLLAR + "," + UPDATED_AMOUNT_DOLLAR);

        // Get all the caseRecoveryList where amountDollar equals to UPDATED_AMOUNT_DOLLAR
        defaultCaseRecoveryShouldNotBeFound("amountDollar.in=" + UPDATED_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByAmountDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where amountDollar is not null
        defaultCaseRecoveryShouldBeFound("amountDollar.specified=true");

        // Get all the caseRecoveryList where amountDollar is null
        defaultCaseRecoveryShouldNotBeFound("amountDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByRegisterDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where registerDate equals to DEFAULT_REGISTER_DATE
        defaultCaseRecoveryShouldBeFound("registerDate.equals=" + DEFAULT_REGISTER_DATE);

        // Get all the caseRecoveryList where registerDate equals to UPDATED_REGISTER_DATE
        defaultCaseRecoveryShouldNotBeFound("registerDate.equals=" + UPDATED_REGISTER_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByRegisterDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where registerDate in DEFAULT_REGISTER_DATE or UPDATED_REGISTER_DATE
        defaultCaseRecoveryShouldBeFound("registerDate.in=" + DEFAULT_REGISTER_DATE + "," + UPDATED_REGISTER_DATE);

        // Get all the caseRecoveryList where registerDate equals to UPDATED_REGISTER_DATE
        defaultCaseRecoveryShouldNotBeFound("registerDate.in=" + UPDATED_REGISTER_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByRegisterDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where registerDate is not null
        defaultCaseRecoveryShouldBeFound("registerDate.specified=true");

        // Get all the caseRecoveryList where registerDate is null
        defaultCaseRecoveryShouldNotBeFound("registerDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByRegisterUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where registerUser equals to DEFAULT_REGISTER_USER
        defaultCaseRecoveryShouldBeFound("registerUser.equals=" + DEFAULT_REGISTER_USER);

        // Get all the caseRecoveryList where registerUser equals to UPDATED_REGISTER_USER
        defaultCaseRecoveryShouldNotBeFound("registerUser.equals=" + UPDATED_REGISTER_USER);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByRegisterUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where registerUser in DEFAULT_REGISTER_USER or UPDATED_REGISTER_USER
        defaultCaseRecoveryShouldBeFound("registerUser.in=" + DEFAULT_REGISTER_USER + "," + UPDATED_REGISTER_USER);

        // Get all the caseRecoveryList where registerUser equals to UPDATED_REGISTER_USER
        defaultCaseRecoveryShouldNotBeFound("registerUser.in=" + UPDATED_REGISTER_USER);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByRegisterUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where registerUser is not null
        defaultCaseRecoveryShouldBeFound("registerUser.specified=true");

        // Get all the caseRecoveryList where registerUser is null
        defaultCaseRecoveryShouldNotBeFound("registerUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByRegisterUserIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where registerUser greater than or equals to DEFAULT_REGISTER_USER
        defaultCaseRecoveryShouldBeFound("registerUser.greaterOrEqualThan=" + DEFAULT_REGISTER_USER);

        // Get all the caseRecoveryList where registerUser greater than or equals to UPDATED_REGISTER_USER
        defaultCaseRecoveryShouldNotBeFound("registerUser.greaterOrEqualThan=" + UPDATED_REGISTER_USER);
    }

    @Test
    @Transactional
    public void getAllCaseRecoveriesByRegisterUserIsLessThanSomething() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        // Get all the caseRecoveryList where registerUser less than or equals to DEFAULT_REGISTER_USER
        defaultCaseRecoveryShouldNotBeFound("registerUser.lessThan=" + DEFAULT_REGISTER_USER);

        // Get all the caseRecoveryList where registerUser less than or equals to UPDATED_REGISTER_USER
        defaultCaseRecoveryShouldBeFound("registerUser.lessThan=" + UPDATED_REGISTER_USER);
    }


    @Test
    @Transactional
    public void getAllCaseRecoveriesBySubcaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselSubCase subcase = VesselSubCaseResourceIntTest.createEntity(em);
        em.persist(subcase);
        em.flush();
        caseRecovery.setSubcase(subcase);
        caseRecoveryRepository.saveAndFlush(caseRecovery);
        Long subcaseId = subcase.getId();

        // Get all the caseRecoveryList where subcase equals to subcaseId
        defaultCaseRecoveryShouldBeFound("subcaseId.equals=" + subcaseId);

        // Get all the caseRecoveryList where subcase equals to subcaseId + 1
        defaultCaseRecoveryShouldNotBeFound("subcaseId.equals=" + (subcaseId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseRecoveriesByRecoveryTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        RecoveryType recoveryType = RecoveryTypeResourceIntTest.createEntity(em);
        em.persist(recoveryType);
        em.flush();
        caseRecovery.setRecoveryType(recoveryType);
        caseRecoveryRepository.saveAndFlush(caseRecovery);
        Long recoveryTypeId = recoveryType.getId();

        // Get all the caseRecoveryList where recoveryType equals to recoveryTypeId
        defaultCaseRecoveryShouldBeFound("recoveryTypeId.equals=" + recoveryTypeId);

        // Get all the caseRecoveryList where recoveryType equals to recoveryTypeId + 1
        defaultCaseRecoveryShouldNotBeFound("recoveryTypeId.equals=" + (recoveryTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseRecoveriesByCreditorIsEqualToSomething() throws Exception {
        // Initialize the database
        Creditor creditor = CreditorResourceIntTest.createEntity(em);
        em.persist(creditor);
        em.flush();
        caseRecovery.setCreditor(creditor);
        caseRecoveryRepository.saveAndFlush(caseRecovery);
        Long creditorId = creditor.getId();

        // Get all the caseRecoveryList where creditor equals to creditorId
        defaultCaseRecoveryShouldBeFound("creditorId.equals=" + creditorId);

        // Get all the caseRecoveryList where creditor equals to creditorId + 1
        defaultCaseRecoveryShouldNotBeFound("creditorId.equals=" + (creditorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCaseRecoveryShouldBeFound(String filter) throws Exception {
        restCaseRecoveryMockMvc.perform(get("/api/case-recoveries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseRecovery.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientNo").value(hasItem(DEFAULT_CLIENT_NO.toString())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].issueDate").value(hasItem(DEFAULT_ISSUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].issueAmount").value(hasItem(DEFAULT_ISSUE_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].receivedDate").value(hasItem(DEFAULT_RECEIVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].receivedAmount").value(hasItem(DEFAULT_RECEIVED_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].amountDollar").value(hasItem(DEFAULT_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].registerDate").value(hasItem(DEFAULT_REGISTER_DATE.toString())))
            .andExpect(jsonPath("$.[*].registerUser").value(hasItem(DEFAULT_REGISTER_USER.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCaseRecoveryShouldNotBeFound(String filter) throws Exception {
        restCaseRecoveryMockMvc.perform(get("/api/case-recoveries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCaseRecovery() throws Exception {
        // Get the caseRecovery
        restCaseRecoveryMockMvc.perform(get("/api/case-recoveries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseRecovery() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        int databaseSizeBeforeUpdate = caseRecoveryRepository.findAll().size();

        // Update the caseRecovery
        CaseRecovery updatedCaseRecovery = caseRecoveryRepository.findById(caseRecovery.getId()).get();
        // Disconnect from session so that the updates on updatedCaseRecovery are not directly saved in db
        em.detach(updatedCaseRecovery);
        updatedCaseRecovery
            .clientNo(UPDATED_CLIENT_NO)
            .numberId(UPDATED_NUMBER_ID)
            .currency(UPDATED_CURRENCY)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueAmount(UPDATED_ISSUE_AMOUNT)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .receivedAmount(UPDATED_RECEIVED_AMOUNT)
            .amountDollar(UPDATED_AMOUNT_DOLLAR)
            .registerDate(UPDATED_REGISTER_DATE)
            .registerUser(UPDATED_REGISTER_USER)
            .remark(UPDATED_REMARK);
        CaseRecoveryDTO caseRecoveryDTO = caseRecoveryMapper.toDto(updatedCaseRecovery);

        restCaseRecoveryMockMvc.perform(put("/api/case-recoveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseRecoveryDTO)))
            .andExpect(status().isOk());

        // Validate the CaseRecovery in the database
        List<CaseRecovery> caseRecoveryList = caseRecoveryRepository.findAll();
        assertThat(caseRecoveryList).hasSize(databaseSizeBeforeUpdate);
        CaseRecovery testCaseRecovery = caseRecoveryList.get(caseRecoveryList.size() - 1);
        assertThat(testCaseRecovery.getClientNo()).isEqualTo(UPDATED_CLIENT_NO);
        assertThat(testCaseRecovery.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCaseRecovery.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCaseRecovery.getCurrencyRate()).isEqualTo(UPDATED_CURRENCY_RATE);
        assertThat(testCaseRecovery.getIssueDate()).isEqualTo(UPDATED_ISSUE_DATE);
        assertThat(testCaseRecovery.getIssueAmount()).isEqualTo(UPDATED_ISSUE_AMOUNT);
        assertThat(testCaseRecovery.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testCaseRecovery.getReceivedAmount()).isEqualTo(UPDATED_RECEIVED_AMOUNT);
        assertThat(testCaseRecovery.getAmountDollar()).isEqualTo(UPDATED_AMOUNT_DOLLAR);
        assertThat(testCaseRecovery.getRegisterDate()).isEqualTo(UPDATED_REGISTER_DATE);
        assertThat(testCaseRecovery.getRegisterUser()).isEqualTo(UPDATED_REGISTER_USER);
        assertThat(testCaseRecovery.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseRecovery() throws Exception {
        int databaseSizeBeforeUpdate = caseRecoveryRepository.findAll().size();

        // Create the CaseRecovery
        CaseRecoveryDTO caseRecoveryDTO = caseRecoveryMapper.toDto(caseRecovery);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCaseRecoveryMockMvc.perform(put("/api/case-recoveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseRecoveryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseRecovery in the database
        List<CaseRecovery> caseRecoveryList = caseRecoveryRepository.findAll();
        assertThat(caseRecoveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseRecovery() throws Exception {
        // Initialize the database
        caseRecoveryRepository.saveAndFlush(caseRecovery);

        int databaseSizeBeforeDelete = caseRecoveryRepository.findAll().size();

        // Get the caseRecovery
        restCaseRecoveryMockMvc.perform(delete("/api/case-recoveries/{id}", caseRecovery.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseRecovery> caseRecoveryList = caseRecoveryRepository.findAll();
        assertThat(caseRecoveryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseRecovery.class);
        CaseRecovery caseRecovery1 = new CaseRecovery();
        caseRecovery1.setId(1L);
        CaseRecovery caseRecovery2 = new CaseRecovery();
        caseRecovery2.setId(caseRecovery1.getId());
        assertThat(caseRecovery1).isEqualTo(caseRecovery2);
        caseRecovery2.setId(2L);
        assertThat(caseRecovery1).isNotEqualTo(caseRecovery2);
        caseRecovery1.setId(null);
        assertThat(caseRecovery1).isNotEqualTo(caseRecovery2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseRecoveryDTO.class);
        CaseRecoveryDTO caseRecoveryDTO1 = new CaseRecoveryDTO();
        caseRecoveryDTO1.setId(1L);
        CaseRecoveryDTO caseRecoveryDTO2 = new CaseRecoveryDTO();
        assertThat(caseRecoveryDTO1).isNotEqualTo(caseRecoveryDTO2);
        caseRecoveryDTO2.setId(caseRecoveryDTO1.getId());
        assertThat(caseRecoveryDTO1).isEqualTo(caseRecoveryDTO2);
        caseRecoveryDTO2.setId(2L);
        assertThat(caseRecoveryDTO1).isNotEqualTo(caseRecoveryDTO2);
        caseRecoveryDTO1.setId(null);
        assertThat(caseRecoveryDTO1).isNotEqualTo(caseRecoveryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseRecoveryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseRecoveryMapper.fromId(null)).isNull();
    }
}
