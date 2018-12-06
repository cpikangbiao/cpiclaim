package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CaseClaim;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.repository.CaseClaimRepository;
import com.cpi.claim.service.CaseClaimService;
import com.cpi.claim.service.dto.CaseClaimDTO;
import com.cpi.claim.service.mapper.CaseClaimMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseClaimCriteria;
import com.cpi.claim.service.CaseClaimQueryService;

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
 * Test class for the CaseClaimResource REST controller.
 *
 * @see CaseClaimResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseClaimResourceIntTest {

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final String DEFAULT_CLAIMER = "AAAAAAAAAA";
    private static final String UPDATED_CLAIMER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CLAIM_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLAIM_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_BILL_OF_LADING = "AAAAAAAAAA";
    private static final String UPDATED_BILL_OF_LADING = "BBBBBBBBBB";

    private static final Long DEFAULT_CURRENCY_ID = 1L;
    private static final Long UPDATED_CURRENCY_ID = 2L;

    private static final BigDecimal DEFAULT_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CLAIM_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_CLAIM_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CLAIM_COST_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_CLAIM_COST_DOLLAR = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private CaseClaimRepository caseClaimRepository;


    @Autowired
    private CaseClaimMapper caseClaimMapper;
    

    @Autowired
    private CaseClaimService caseClaimService;

    @Autowired
    private CaseClaimQueryService caseClaimQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaseClaimMockMvc;

    private CaseClaim caseClaim;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseClaimResource caseClaimResource = new CaseClaimResource(caseClaimService, caseClaimQueryService);
        this.restCaseClaimMockMvc = MockMvcBuilders.standaloneSetup(caseClaimResource)
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
    public static CaseClaim createEntity(EntityManager em) {
        CaseClaim caseClaim = new CaseClaim()
            .numberId(DEFAULT_NUMBER_ID)
            .claimer(DEFAULT_CLAIMER)
            .claimDate(DEFAULT_CLAIM_DATE)
            .billOfLading(DEFAULT_BILL_OF_LADING)
            .currencyId(DEFAULT_CURRENCY_ID)
            .currencyRate(DEFAULT_CURRENCY_RATE)
            .claimCost(DEFAULT_CLAIM_COST)
            .claimCostDollar(DEFAULT_CLAIM_COST_DOLLAR)
            .remark(DEFAULT_REMARK);
        return caseClaim;
    }

    @Before
    public void initTest() {
        caseClaim = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseClaim() throws Exception {
        int databaseSizeBeforeCreate = caseClaimRepository.findAll().size();

        // Create the CaseClaim
        CaseClaimDTO caseClaimDTO = caseClaimMapper.toDto(caseClaim);
        restCaseClaimMockMvc.perform(post("/api/case-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseClaim in the database
        List<CaseClaim> caseClaimList = caseClaimRepository.findAll();
        assertThat(caseClaimList).hasSize(databaseSizeBeforeCreate + 1);
        CaseClaim testCaseClaim = caseClaimList.get(caseClaimList.size() - 1);
        assertThat(testCaseClaim.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCaseClaim.getClaimer()).isEqualTo(DEFAULT_CLAIMER);
        assertThat(testCaseClaim.getClaimDate()).isEqualTo(DEFAULT_CLAIM_DATE);
        assertThat(testCaseClaim.getBillOfLading()).isEqualTo(DEFAULT_BILL_OF_LADING);
        assertThat(testCaseClaim.getCurrencyId()).isEqualTo(DEFAULT_CURRENCY_ID);
        assertThat(testCaseClaim.getCurrencyRate()).isEqualTo(DEFAULT_CURRENCY_RATE);
        assertThat(testCaseClaim.getClaimCost()).isEqualTo(DEFAULT_CLAIM_COST);
        assertThat(testCaseClaim.getClaimCostDollar()).isEqualTo(DEFAULT_CLAIM_COST_DOLLAR);
        assertThat(testCaseClaim.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createCaseClaimWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseClaimRepository.findAll().size();

        // Create the CaseClaim with an existing ID
        caseClaim.setId(1L);
        CaseClaimDTO caseClaimDTO = caseClaimMapper.toDto(caseClaim);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseClaimMockMvc.perform(post("/api/case-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaim in the database
        List<CaseClaim> caseClaimList = caseClaimRepository.findAll();
        assertThat(caseClaimList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCaseClaims() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList
        restCaseClaimMockMvc.perform(get("/api/case-claims?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaim.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].claimer").value(hasItem(DEFAULT_CLAIMER.toString())))
            .andExpect(jsonPath("$.[*].claimDate").value(hasItem(DEFAULT_CLAIM_DATE.toString())))
            .andExpect(jsonPath("$.[*].billOfLading").value(hasItem(DEFAULT_BILL_OF_LADING.toString())))
            .andExpect(jsonPath("$.[*].currencyId").value(hasItem(DEFAULT_CURRENCY_ID.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].claimCost").value(hasItem(DEFAULT_CLAIM_COST.intValue())))
            .andExpect(jsonPath("$.[*].claimCostDollar").value(hasItem(DEFAULT_CLAIM_COST_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }
    

    @Test
    @Transactional
    public void getCaseClaim() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get the caseClaim
        restCaseClaimMockMvc.perform(get("/api/case-claims/{id}", caseClaim.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseClaim.getId().intValue()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.claimer").value(DEFAULT_CLAIMER.toString()))
            .andExpect(jsonPath("$.claimDate").value(DEFAULT_CLAIM_DATE.toString()))
            .andExpect(jsonPath("$.billOfLading").value(DEFAULT_BILL_OF_LADING.toString()))
            .andExpect(jsonPath("$.currencyId").value(DEFAULT_CURRENCY_ID.intValue()))
            .andExpect(jsonPath("$.currencyRate").value(DEFAULT_CURRENCY_RATE.intValue()))
            .andExpect(jsonPath("$.claimCost").value(DEFAULT_CLAIM_COST.intValue()))
            .andExpect(jsonPath("$.claimCostDollar").value(DEFAULT_CLAIM_COST_DOLLAR.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where numberId equals to DEFAULT_NUMBER_ID
        defaultCaseClaimShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the caseClaimList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseClaimShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCaseClaimShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the caseClaimList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseClaimShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where numberId is not null
        defaultCaseClaimShouldBeFound("numberId.specified=true");

        // Get all the caseClaimList where numberId is null
        defaultCaseClaimShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultCaseClaimShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseClaimList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultCaseClaimShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultCaseClaimShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseClaimList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultCaseClaimShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseClaimsByClaimerIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimer equals to DEFAULT_CLAIMER
        defaultCaseClaimShouldBeFound("claimer.equals=" + DEFAULT_CLAIMER);

        // Get all the caseClaimList where claimer equals to UPDATED_CLAIMER
        defaultCaseClaimShouldNotBeFound("claimer.equals=" + UPDATED_CLAIMER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimerIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimer in DEFAULT_CLAIMER or UPDATED_CLAIMER
        defaultCaseClaimShouldBeFound("claimer.in=" + DEFAULT_CLAIMER + "," + UPDATED_CLAIMER);

        // Get all the caseClaimList where claimer equals to UPDATED_CLAIMER
        defaultCaseClaimShouldNotBeFound("claimer.in=" + UPDATED_CLAIMER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimerIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimer is not null
        defaultCaseClaimShouldBeFound("claimer.specified=true");

        // Get all the caseClaimList where claimer is null
        defaultCaseClaimShouldNotBeFound("claimer.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimDate equals to DEFAULT_CLAIM_DATE
        defaultCaseClaimShouldBeFound("claimDate.equals=" + DEFAULT_CLAIM_DATE);

        // Get all the caseClaimList where claimDate equals to UPDATED_CLAIM_DATE
        defaultCaseClaimShouldNotBeFound("claimDate.equals=" + UPDATED_CLAIM_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimDate in DEFAULT_CLAIM_DATE or UPDATED_CLAIM_DATE
        defaultCaseClaimShouldBeFound("claimDate.in=" + DEFAULT_CLAIM_DATE + "," + UPDATED_CLAIM_DATE);

        // Get all the caseClaimList where claimDate equals to UPDATED_CLAIM_DATE
        defaultCaseClaimShouldNotBeFound("claimDate.in=" + UPDATED_CLAIM_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimDate is not null
        defaultCaseClaimShouldBeFound("claimDate.specified=true");

        // Get all the caseClaimList where claimDate is null
        defaultCaseClaimShouldNotBeFound("claimDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByBillOfLadingIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where billOfLading equals to DEFAULT_BILL_OF_LADING
        defaultCaseClaimShouldBeFound("billOfLading.equals=" + DEFAULT_BILL_OF_LADING);

        // Get all the caseClaimList where billOfLading equals to UPDATED_BILL_OF_LADING
        defaultCaseClaimShouldNotBeFound("billOfLading.equals=" + UPDATED_BILL_OF_LADING);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByBillOfLadingIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where billOfLading in DEFAULT_BILL_OF_LADING or UPDATED_BILL_OF_LADING
        defaultCaseClaimShouldBeFound("billOfLading.in=" + DEFAULT_BILL_OF_LADING + "," + UPDATED_BILL_OF_LADING);

        // Get all the caseClaimList where billOfLading equals to UPDATED_BILL_OF_LADING
        defaultCaseClaimShouldNotBeFound("billOfLading.in=" + UPDATED_BILL_OF_LADING);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByBillOfLadingIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where billOfLading is not null
        defaultCaseClaimShouldBeFound("billOfLading.specified=true");

        // Get all the caseClaimList where billOfLading is null
        defaultCaseClaimShouldNotBeFound("billOfLading.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByCurrencyIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where currencyId equals to DEFAULT_CURRENCY_ID
        defaultCaseClaimShouldBeFound("currencyId.equals=" + DEFAULT_CURRENCY_ID);

        // Get all the caseClaimList where currencyId equals to UPDATED_CURRENCY_ID
        defaultCaseClaimShouldNotBeFound("currencyId.equals=" + UPDATED_CURRENCY_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByCurrencyIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where currencyId in DEFAULT_CURRENCY_ID or UPDATED_CURRENCY_ID
        defaultCaseClaimShouldBeFound("currencyId.in=" + DEFAULT_CURRENCY_ID + "," + UPDATED_CURRENCY_ID);

        // Get all the caseClaimList where currencyId equals to UPDATED_CURRENCY_ID
        defaultCaseClaimShouldNotBeFound("currencyId.in=" + UPDATED_CURRENCY_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByCurrencyIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where currencyId is not null
        defaultCaseClaimShouldBeFound("currencyId.specified=true");

        // Get all the caseClaimList where currencyId is null
        defaultCaseClaimShouldNotBeFound("currencyId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByCurrencyIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where currencyId greater than or equals to DEFAULT_CURRENCY_ID
        defaultCaseClaimShouldBeFound("currencyId.greaterOrEqualThan=" + DEFAULT_CURRENCY_ID);

        // Get all the caseClaimList where currencyId greater than or equals to UPDATED_CURRENCY_ID
        defaultCaseClaimShouldNotBeFound("currencyId.greaterOrEqualThan=" + UPDATED_CURRENCY_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByCurrencyIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where currencyId less than or equals to DEFAULT_CURRENCY_ID
        defaultCaseClaimShouldNotBeFound("currencyId.lessThan=" + DEFAULT_CURRENCY_ID);

        // Get all the caseClaimList where currencyId less than or equals to UPDATED_CURRENCY_ID
        defaultCaseClaimShouldBeFound("currencyId.lessThan=" + UPDATED_CURRENCY_ID);
    }


    @Test
    @Transactional
    public void getAllCaseClaimsByCurrencyRateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where currencyRate equals to DEFAULT_CURRENCY_RATE
        defaultCaseClaimShouldBeFound("currencyRate.equals=" + DEFAULT_CURRENCY_RATE);

        // Get all the caseClaimList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultCaseClaimShouldNotBeFound("currencyRate.equals=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByCurrencyRateIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where currencyRate in DEFAULT_CURRENCY_RATE or UPDATED_CURRENCY_RATE
        defaultCaseClaimShouldBeFound("currencyRate.in=" + DEFAULT_CURRENCY_RATE + "," + UPDATED_CURRENCY_RATE);

        // Get all the caseClaimList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultCaseClaimShouldNotBeFound("currencyRate.in=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByCurrencyRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where currencyRate is not null
        defaultCaseClaimShouldBeFound("currencyRate.specified=true");

        // Get all the caseClaimList where currencyRate is null
        defaultCaseClaimShouldNotBeFound("currencyRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimCostIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimCost equals to DEFAULT_CLAIM_COST
        defaultCaseClaimShouldBeFound("claimCost.equals=" + DEFAULT_CLAIM_COST);

        // Get all the caseClaimList where claimCost equals to UPDATED_CLAIM_COST
        defaultCaseClaimShouldNotBeFound("claimCost.equals=" + UPDATED_CLAIM_COST);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimCostIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimCost in DEFAULT_CLAIM_COST or UPDATED_CLAIM_COST
        defaultCaseClaimShouldBeFound("claimCost.in=" + DEFAULT_CLAIM_COST + "," + UPDATED_CLAIM_COST);

        // Get all the caseClaimList where claimCost equals to UPDATED_CLAIM_COST
        defaultCaseClaimShouldNotBeFound("claimCost.in=" + UPDATED_CLAIM_COST);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimCost is not null
        defaultCaseClaimShouldBeFound("claimCost.specified=true");

        // Get all the caseClaimList where claimCost is null
        defaultCaseClaimShouldNotBeFound("claimCost.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimCostDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimCostDollar equals to DEFAULT_CLAIM_COST_DOLLAR
        defaultCaseClaimShouldBeFound("claimCostDollar.equals=" + DEFAULT_CLAIM_COST_DOLLAR);

        // Get all the caseClaimList where claimCostDollar equals to UPDATED_CLAIM_COST_DOLLAR
        defaultCaseClaimShouldNotBeFound("claimCostDollar.equals=" + UPDATED_CLAIM_COST_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimCostDollarIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimCostDollar in DEFAULT_CLAIM_COST_DOLLAR or UPDATED_CLAIM_COST_DOLLAR
        defaultCaseClaimShouldBeFound("claimCostDollar.in=" + DEFAULT_CLAIM_COST_DOLLAR + "," + UPDATED_CLAIM_COST_DOLLAR);

        // Get all the caseClaimList where claimCostDollar equals to UPDATED_CLAIM_COST_DOLLAR
        defaultCaseClaimShouldNotBeFound("claimCostDollar.in=" + UPDATED_CLAIM_COST_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseClaimsByClaimCostDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        // Get all the caseClaimList where claimCostDollar is not null
        defaultCaseClaimShouldBeFound("claimCostDollar.specified=true");

        // Get all the caseClaimList where claimCostDollar is null
        defaultCaseClaimShouldNotBeFound("claimCostDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimsBySubcaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselSubCase subcase = VesselSubCaseResourceIntTest.createEntity(em);
        em.persist(subcase);
        em.flush();
        caseClaim.setSubcase(subcase);
        caseClaimRepository.saveAndFlush(caseClaim);
        Long subcaseId = subcase.getId();

        // Get all the caseClaimList where subcase equals to subcaseId
        defaultCaseClaimShouldBeFound("subcaseId.equals=" + subcaseId);

        // Get all the caseClaimList where subcase equals to subcaseId + 1
        defaultCaseClaimShouldNotBeFound("subcaseId.equals=" + (subcaseId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCaseClaimShouldBeFound(String filter) throws Exception {
        restCaseClaimMockMvc.perform(get("/api/case-claims?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaim.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].claimer").value(hasItem(DEFAULT_CLAIMER.toString())))
            .andExpect(jsonPath("$.[*].claimDate").value(hasItem(DEFAULT_CLAIM_DATE.toString())))
            .andExpect(jsonPath("$.[*].billOfLading").value(hasItem(DEFAULT_BILL_OF_LADING.toString())))
            .andExpect(jsonPath("$.[*].currencyId").value(hasItem(DEFAULT_CURRENCY_ID.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].claimCost").value(hasItem(DEFAULT_CLAIM_COST.intValue())))
            .andExpect(jsonPath("$.[*].claimCostDollar").value(hasItem(DEFAULT_CLAIM_COST_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCaseClaimShouldNotBeFound(String filter) throws Exception {
        restCaseClaimMockMvc.perform(get("/api/case-claims?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCaseClaim() throws Exception {
        // Get the caseClaim
        restCaseClaimMockMvc.perform(get("/api/case-claims/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseClaim() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        int databaseSizeBeforeUpdate = caseClaimRepository.findAll().size();

        // Update the caseClaim
        CaseClaim updatedCaseClaim = caseClaimRepository.findById(caseClaim.getId()).get();
        // Disconnect from session so that the updates on updatedCaseClaim are not directly saved in db
        em.detach(updatedCaseClaim);
        updatedCaseClaim
            .numberId(UPDATED_NUMBER_ID)
            .claimer(UPDATED_CLAIMER)
            .claimDate(UPDATED_CLAIM_DATE)
            .billOfLading(UPDATED_BILL_OF_LADING)
            .currencyId(UPDATED_CURRENCY_ID)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .claimCost(UPDATED_CLAIM_COST)
            .claimCostDollar(UPDATED_CLAIM_COST_DOLLAR)
            .remark(UPDATED_REMARK);
        CaseClaimDTO caseClaimDTO = caseClaimMapper.toDto(updatedCaseClaim);

        restCaseClaimMockMvc.perform(put("/api/case-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimDTO)))
            .andExpect(status().isOk());

        // Validate the CaseClaim in the database
        List<CaseClaim> caseClaimList = caseClaimRepository.findAll();
        assertThat(caseClaimList).hasSize(databaseSizeBeforeUpdate);
        CaseClaim testCaseClaim = caseClaimList.get(caseClaimList.size() - 1);
        assertThat(testCaseClaim.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCaseClaim.getClaimer()).isEqualTo(UPDATED_CLAIMER);
        assertThat(testCaseClaim.getClaimDate()).isEqualTo(UPDATED_CLAIM_DATE);
        assertThat(testCaseClaim.getBillOfLading()).isEqualTo(UPDATED_BILL_OF_LADING);
        assertThat(testCaseClaim.getCurrencyId()).isEqualTo(UPDATED_CURRENCY_ID);
        assertThat(testCaseClaim.getCurrencyRate()).isEqualTo(UPDATED_CURRENCY_RATE);
        assertThat(testCaseClaim.getClaimCost()).isEqualTo(UPDATED_CLAIM_COST);
        assertThat(testCaseClaim.getClaimCostDollar()).isEqualTo(UPDATED_CLAIM_COST_DOLLAR);
        assertThat(testCaseClaim.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseClaim() throws Exception {
        int databaseSizeBeforeUpdate = caseClaimRepository.findAll().size();

        // Create the CaseClaim
        CaseClaimDTO caseClaimDTO = caseClaimMapper.toDto(caseClaim);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCaseClaimMockMvc.perform(put("/api/case-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaim in the database
        List<CaseClaim> caseClaimList = caseClaimRepository.findAll();
        assertThat(caseClaimList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseClaim() throws Exception {
        // Initialize the database
        caseClaimRepository.saveAndFlush(caseClaim);

        int databaseSizeBeforeDelete = caseClaimRepository.findAll().size();

        // Get the caseClaim
        restCaseClaimMockMvc.perform(delete("/api/case-claims/{id}", caseClaim.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseClaim> caseClaimList = caseClaimRepository.findAll();
        assertThat(caseClaimList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaim.class);
        CaseClaim caseClaim1 = new CaseClaim();
        caseClaim1.setId(1L);
        CaseClaim caseClaim2 = new CaseClaim();
        caseClaim2.setId(caseClaim1.getId());
        assertThat(caseClaim1).isEqualTo(caseClaim2);
        caseClaim2.setId(2L);
        assertThat(caseClaim1).isNotEqualTo(caseClaim2);
        caseClaim1.setId(null);
        assertThat(caseClaim1).isNotEqualTo(caseClaim2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaimDTO.class);
        CaseClaimDTO caseClaimDTO1 = new CaseClaimDTO();
        caseClaimDTO1.setId(1L);
        CaseClaimDTO caseClaimDTO2 = new CaseClaimDTO();
        assertThat(caseClaimDTO1).isNotEqualTo(caseClaimDTO2);
        caseClaimDTO2.setId(caseClaimDTO1.getId());
        assertThat(caseClaimDTO1).isEqualTo(caseClaimDTO2);
        caseClaimDTO2.setId(2L);
        assertThat(caseClaimDTO1).isNotEqualTo(caseClaimDTO2);
        caseClaimDTO1.setId(null);
        assertThat(caseClaimDTO1).isNotEqualTo(caseClaimDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseClaimMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseClaimMapper.fromId(null)).isNull();
    }
}
