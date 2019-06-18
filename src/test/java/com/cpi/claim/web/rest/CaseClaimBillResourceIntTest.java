package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.domain.ClaimBillStatus;
import com.cpi.claim.domain.ClaimBillType;
import com.cpi.claim.domain.ClaimBillFinanceType;
import com.cpi.claim.domain.Creditor;
import com.cpi.claim.repository.CaseClaimBillRepository;
import com.cpi.claim.service.CaseClaimBillService;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
import com.cpi.claim.service.mapper.CaseClaimBillMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseClaimBillCriteria;
import com.cpi.claim.service.CaseClaimBillQueryService;

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
 * Test class for the CaseClaimBillResource REST controller.
 *
 * @see CaseClaimBillResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseClaimBillResourceIntTest {

    private static final String DEFAULT_CLAIM_BILL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_BILL_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CLAIM_BILL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLAIM_BILL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_REGISTER_USER_ID = 1L;
    private static final Long UPDATED_REGISTER_USER_ID = 2L;

    private static final String DEFAULT_CLIENT_BILL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_BILL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BILL_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_BILL_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_DUE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DUE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MEMBER_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_YEAR = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final Long DEFAULT_CLAIM_AMOUNT_CURRENCY = 1L;
    private static final Long UPDATED_CLAIM_AMOUNT_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_CLAIM_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CLAIM_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEDUCTIBLE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCTIBLE = new BigDecimal(2);

    private static final Long DEFAULT_DEDUCTIBLE_CURRENCY = 1L;
    private static final Long UPDATED_DEDUCTIBLE_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_DEDUCTIBLE_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCTIBLE_CURRENCY_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEDUCTIBLE_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCTIBLE_DOLLAR = new BigDecimal(2);

    private static final Long DEFAULT_BILL_CURRENCY = 1L;
    private static final Long UPDATED_BILL_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_BILL_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BILL_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BILL_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BILL_CURRENCY_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BILL_AMOUNT_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_BILL_AMOUNT_DOLLAR = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SIGNED = false;
    private static final Boolean UPDATED_IS_SIGNED = true;

    private static final Long DEFAULT_SIGN_USER = 1L;
    private static final Long UPDATED_SIGN_USER = 2L;

    private static final Instant DEFAULT_SIGN_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SIGN_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_PROCESS_ID = 1L;
    private static final Long UPDATED_PROCESS_ID = 2L;

    private static final Integer DEFAULT_PRINT_NUMBER = 1;
    private static final Integer UPDATED_PRINT_NUMBER = 2;

    @Autowired
    private CaseClaimBillRepository caseClaimBillRepository;


    @Autowired
    private CaseClaimBillMapper caseClaimBillMapper;
    

    @Autowired
    private CaseClaimBillService caseClaimBillService;

    @Autowired
    private CaseClaimBillQueryService caseClaimBillQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaseClaimBillMockMvc;

    private CaseClaimBill caseClaimBill;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseClaimBillResource caseClaimBillResource = new CaseClaimBillResource(caseClaimBillService, caseClaimBillQueryService);
        this.restCaseClaimBillMockMvc = MockMvcBuilders.standaloneSetup(caseClaimBillResource)
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
    public static CaseClaimBill createEntity(EntityManager em) {
        CaseClaimBill caseClaimBill = new CaseClaimBill()
            .claimBillCode(DEFAULT_CLAIM_BILL_CODE)
            .claimBillDate(DEFAULT_CLAIM_BILL_DATE)
            .registerUserId(DEFAULT_REGISTER_USER_ID)
            .clientBillNo(DEFAULT_CLIENT_BILL_NO)
            .billDescription(DEFAULT_BILL_DESCRIPTION)
            .dueDate(DEFAULT_DUE_DATE)
            .memberYear(DEFAULT_MEMBER_YEAR)
            .numberId(DEFAULT_NUMBER_ID)
            .claimAmountCurrency(DEFAULT_CLAIM_AMOUNT_CURRENCY)
            .claimAmount(DEFAULT_CLAIM_AMOUNT)
            .deductible(DEFAULT_DEDUCTIBLE)
            .deductibleCurrency(DEFAULT_DEDUCTIBLE_CURRENCY)
            .deductibleCurrencyRate(DEFAULT_DEDUCTIBLE_CURRENCY_RATE)
            .deductibleDollar(DEFAULT_DEDUCTIBLE_DOLLAR)
            .billCurrency(DEFAULT_BILL_CURRENCY)
            .billAmount(DEFAULT_BILL_AMOUNT)
            .billCurrencyRate(DEFAULT_BILL_CURRENCY_RATE)
            .billAmountDollar(DEFAULT_BILL_AMOUNT_DOLLAR)
            .remark(DEFAULT_REMARK)
            .isSigned(DEFAULT_IS_SIGNED)
            .signUser(DEFAULT_SIGN_USER)
            .signDate(DEFAULT_SIGN_DATE)
            .processId(DEFAULT_PROCESS_ID)
            .printNumber(DEFAULT_PRINT_NUMBER);
        return caseClaimBill;
    }

    @Before
    public void initTest() {
        caseClaimBill = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseClaimBill() throws Exception {
        int databaseSizeBeforeCreate = caseClaimBillRepository.findAll().size();

        // Create the CaseClaimBill
        CaseClaimBillDTO caseClaimBillDTO = caseClaimBillMapper.toDto(caseClaimBill);
        restCaseClaimBillMockMvc.perform(post("/api/case-claim-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseClaimBill in the database
        List<CaseClaimBill> caseClaimBillList = caseClaimBillRepository.findAll();
        assertThat(caseClaimBillList).hasSize(databaseSizeBeforeCreate + 1);
        CaseClaimBill testCaseClaimBill = caseClaimBillList.get(caseClaimBillList.size() - 1);
        assertThat(testCaseClaimBill.getClaimBillCode()).isEqualTo(DEFAULT_CLAIM_BILL_CODE);
        assertThat(testCaseClaimBill.getClaimBillDate()).isEqualTo(DEFAULT_CLAIM_BILL_DATE);
        assertThat(testCaseClaimBill.getRegisterUserId()).isEqualTo(DEFAULT_REGISTER_USER_ID);
        assertThat(testCaseClaimBill.getClientBillNo()).isEqualTo(DEFAULT_CLIENT_BILL_NO);
        assertThat(testCaseClaimBill.getBillDescription()).isEqualTo(DEFAULT_BILL_DESCRIPTION);
        assertThat(testCaseClaimBill.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testCaseClaimBill.getMemberYear()).isEqualTo(DEFAULT_MEMBER_YEAR);
        assertThat(testCaseClaimBill.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCaseClaimBill.getClaimAmountCurrency()).isEqualTo(DEFAULT_CLAIM_AMOUNT_CURRENCY);
        assertThat(testCaseClaimBill.getClaimAmount()).isEqualTo(DEFAULT_CLAIM_AMOUNT);
        assertThat(testCaseClaimBill.getDeductible()).isEqualTo(DEFAULT_DEDUCTIBLE);
        assertThat(testCaseClaimBill.getDeductibleCurrency()).isEqualTo(DEFAULT_DEDUCTIBLE_CURRENCY);
        assertThat(testCaseClaimBill.getDeductibleCurrencyRate()).isEqualTo(DEFAULT_DEDUCTIBLE_CURRENCY_RATE);
        assertThat(testCaseClaimBill.getDeductibleDollar()).isEqualTo(DEFAULT_DEDUCTIBLE_DOLLAR);
        assertThat(testCaseClaimBill.getBillCurrency()).isEqualTo(DEFAULT_BILL_CURRENCY);
        assertThat(testCaseClaimBill.getBillAmount()).isEqualTo(DEFAULT_BILL_AMOUNT);
        assertThat(testCaseClaimBill.getBillCurrencyRate()).isEqualTo(DEFAULT_BILL_CURRENCY_RATE);
        assertThat(testCaseClaimBill.getBillAmountDollar()).isEqualTo(DEFAULT_BILL_AMOUNT_DOLLAR);
        assertThat(testCaseClaimBill.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testCaseClaimBill.isIsSigned()).isEqualTo(DEFAULT_IS_SIGNED);
        assertThat(testCaseClaimBill.getSignUser()).isEqualTo(DEFAULT_SIGN_USER);
        assertThat(testCaseClaimBill.getSignDate()).isEqualTo(DEFAULT_SIGN_DATE);
        assertThat(testCaseClaimBill.getProcessId()).isEqualTo(DEFAULT_PROCESS_ID);
        assertThat(testCaseClaimBill.getPrintNumber()).isEqualTo(DEFAULT_PRINT_NUMBER);
    }

    @Test
    @Transactional
    public void createCaseClaimBillWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseClaimBillRepository.findAll().size();

        // Create the CaseClaimBill with an existing ID
        caseClaimBill.setId(1L);
        CaseClaimBillDTO caseClaimBillDTO = caseClaimBillMapper.toDto(caseClaimBill);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseClaimBillMockMvc.perform(post("/api/case-claim-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaimBill in the database
        List<CaseClaimBill> caseClaimBillList = caseClaimBillRepository.findAll();
        assertThat(caseClaimBillList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBills() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList
        restCaseClaimBillMockMvc.perform(get("/api/case-claim-bills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaimBill.getId().intValue())))
            .andExpect(jsonPath("$.[*].claimBillCode").value(hasItem(DEFAULT_CLAIM_BILL_CODE.toString())))
            .andExpect(jsonPath("$.[*].claimBillDate").value(hasItem(DEFAULT_CLAIM_BILL_DATE.toString())))
            .andExpect(jsonPath("$.[*].registerUserId").value(hasItem(DEFAULT_REGISTER_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].clientBillNo").value(hasItem(DEFAULT_CLIENT_BILL_NO.toString())))
            .andExpect(jsonPath("$.[*].billDescription").value(hasItem(DEFAULT_BILL_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].memberYear").value(hasItem(DEFAULT_MEMBER_YEAR.toString())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].claimAmountCurrency").value(hasItem(DEFAULT_CLAIM_AMOUNT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].claimAmount").value(hasItem(DEFAULT_CLAIM_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].deductible").value(hasItem(DEFAULT_DEDUCTIBLE.intValue())))
            .andExpect(jsonPath("$.[*].deductibleCurrency").value(hasItem(DEFAULT_DEDUCTIBLE_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].deductibleCurrencyRate").value(hasItem(DEFAULT_DEDUCTIBLE_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].deductibleDollar").value(hasItem(DEFAULT_DEDUCTIBLE_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].billCurrency").value(hasItem(DEFAULT_BILL_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].billAmount").value(hasItem(DEFAULT_BILL_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].billCurrencyRate").value(hasItem(DEFAULT_BILL_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].billAmountDollar").value(hasItem(DEFAULT_BILL_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].isSigned").value(hasItem(DEFAULT_IS_SIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].signUser").value(hasItem(DEFAULT_SIGN_USER.intValue())))
            .andExpect(jsonPath("$.[*].signDate").value(hasItem(DEFAULT_SIGN_DATE.toString())))
            .andExpect(jsonPath("$.[*].processId").value(hasItem(DEFAULT_PROCESS_ID.intValue())))
            .andExpect(jsonPath("$.[*].printNumber").value(hasItem(DEFAULT_PRINT_NUMBER)));
    }
    

    @Test
    @Transactional
    public void getCaseClaimBill() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get the caseClaimBill
        restCaseClaimBillMockMvc.perform(get("/api/case-claim-bills/{id}", caseClaimBill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseClaimBill.getId().intValue()))
            .andExpect(jsonPath("$.claimBillCode").value(DEFAULT_CLAIM_BILL_CODE.toString()))
            .andExpect(jsonPath("$.claimBillDate").value(DEFAULT_CLAIM_BILL_DATE.toString()))
            .andExpect(jsonPath("$.registerUserId").value(DEFAULT_REGISTER_USER_ID.intValue()))
            .andExpect(jsonPath("$.clientBillNo").value(DEFAULT_CLIENT_BILL_NO.toString()))
            .andExpect(jsonPath("$.billDescription").value(DEFAULT_BILL_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dueDate").value(DEFAULT_DUE_DATE.toString()))
            .andExpect(jsonPath("$.memberYear").value(DEFAULT_MEMBER_YEAR.toString()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.claimAmountCurrency").value(DEFAULT_CLAIM_AMOUNT_CURRENCY.intValue()))
            .andExpect(jsonPath("$.claimAmount").value(DEFAULT_CLAIM_AMOUNT.intValue()))
            .andExpect(jsonPath("$.deductible").value(DEFAULT_DEDUCTIBLE.intValue()))
            .andExpect(jsonPath("$.deductibleCurrency").value(DEFAULT_DEDUCTIBLE_CURRENCY.intValue()))
            .andExpect(jsonPath("$.deductibleCurrencyRate").value(DEFAULT_DEDUCTIBLE_CURRENCY_RATE.intValue()))
            .andExpect(jsonPath("$.deductibleDollar").value(DEFAULT_DEDUCTIBLE_DOLLAR.intValue()))
            .andExpect(jsonPath("$.billCurrency").value(DEFAULT_BILL_CURRENCY.intValue()))
            .andExpect(jsonPath("$.billAmount").value(DEFAULT_BILL_AMOUNT.intValue()))
            .andExpect(jsonPath("$.billCurrencyRate").value(DEFAULT_BILL_CURRENCY_RATE.intValue()))
            .andExpect(jsonPath("$.billAmountDollar").value(DEFAULT_BILL_AMOUNT_DOLLAR.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.isSigned").value(DEFAULT_IS_SIGNED.booleanValue()))
            .andExpect(jsonPath("$.signUser").value(DEFAULT_SIGN_USER.intValue()))
            .andExpect(jsonPath("$.signDate").value(DEFAULT_SIGN_DATE.toString()))
            .andExpect(jsonPath("$.processId").value(DEFAULT_PROCESS_ID.intValue()))
            .andExpect(jsonPath("$.printNumber").value(DEFAULT_PRINT_NUMBER));
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimBillCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimBillCode equals to DEFAULT_CLAIM_BILL_CODE
        defaultCaseClaimBillShouldBeFound("claimBillCode.equals=" + DEFAULT_CLAIM_BILL_CODE);

        // Get all the caseClaimBillList where claimBillCode equals to UPDATED_CLAIM_BILL_CODE
        defaultCaseClaimBillShouldNotBeFound("claimBillCode.equals=" + UPDATED_CLAIM_BILL_CODE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimBillCodeIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimBillCode in DEFAULT_CLAIM_BILL_CODE or UPDATED_CLAIM_BILL_CODE
        defaultCaseClaimBillShouldBeFound("claimBillCode.in=" + DEFAULT_CLAIM_BILL_CODE + "," + UPDATED_CLAIM_BILL_CODE);

        // Get all the caseClaimBillList where claimBillCode equals to UPDATED_CLAIM_BILL_CODE
        defaultCaseClaimBillShouldNotBeFound("claimBillCode.in=" + UPDATED_CLAIM_BILL_CODE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimBillCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimBillCode is not null
        defaultCaseClaimBillShouldBeFound("claimBillCode.specified=true");

        // Get all the caseClaimBillList where claimBillCode is null
        defaultCaseClaimBillShouldNotBeFound("claimBillCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimBillDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimBillDate equals to DEFAULT_CLAIM_BILL_DATE
        defaultCaseClaimBillShouldBeFound("claimBillDate.equals=" + DEFAULT_CLAIM_BILL_DATE);

        // Get all the caseClaimBillList where claimBillDate equals to UPDATED_CLAIM_BILL_DATE
        defaultCaseClaimBillShouldNotBeFound("claimBillDate.equals=" + UPDATED_CLAIM_BILL_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimBillDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimBillDate in DEFAULT_CLAIM_BILL_DATE or UPDATED_CLAIM_BILL_DATE
        defaultCaseClaimBillShouldBeFound("claimBillDate.in=" + DEFAULT_CLAIM_BILL_DATE + "," + UPDATED_CLAIM_BILL_DATE);

        // Get all the caseClaimBillList where claimBillDate equals to UPDATED_CLAIM_BILL_DATE
        defaultCaseClaimBillShouldNotBeFound("claimBillDate.in=" + UPDATED_CLAIM_BILL_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimBillDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimBillDate is not null
        defaultCaseClaimBillShouldBeFound("claimBillDate.specified=true");

        // Get all the caseClaimBillList where claimBillDate is null
        defaultCaseClaimBillShouldNotBeFound("claimBillDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByRegisterUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where registerUserId equals to DEFAULT_REGISTER_USER_ID
        defaultCaseClaimBillShouldBeFound("registerUserId.equals=" + DEFAULT_REGISTER_USER_ID);

        // Get all the caseClaimBillList where registerUserId equals to UPDATED_REGISTER_USER_ID
        defaultCaseClaimBillShouldNotBeFound("registerUserId.equals=" + UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByRegisterUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where registerUserId in DEFAULT_REGISTER_USER_ID or UPDATED_REGISTER_USER_ID
        defaultCaseClaimBillShouldBeFound("registerUserId.in=" + DEFAULT_REGISTER_USER_ID + "," + UPDATED_REGISTER_USER_ID);

        // Get all the caseClaimBillList where registerUserId equals to UPDATED_REGISTER_USER_ID
        defaultCaseClaimBillShouldNotBeFound("registerUserId.in=" + UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByRegisterUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where registerUserId is not null
        defaultCaseClaimBillShouldBeFound("registerUserId.specified=true");

        // Get all the caseClaimBillList where registerUserId is null
        defaultCaseClaimBillShouldNotBeFound("registerUserId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByRegisterUserIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where registerUserId greater than or equals to DEFAULT_REGISTER_USER_ID
        defaultCaseClaimBillShouldBeFound("registerUserId.greaterOrEqualThan=" + DEFAULT_REGISTER_USER_ID);

        // Get all the caseClaimBillList where registerUserId greater than or equals to UPDATED_REGISTER_USER_ID
        defaultCaseClaimBillShouldNotBeFound("registerUserId.greaterOrEqualThan=" + UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByRegisterUserIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where registerUserId less than or equals to DEFAULT_REGISTER_USER_ID
        defaultCaseClaimBillShouldNotBeFound("registerUserId.lessThan=" + DEFAULT_REGISTER_USER_ID);

        // Get all the caseClaimBillList where registerUserId less than or equals to UPDATED_REGISTER_USER_ID
        defaultCaseClaimBillShouldBeFound("registerUserId.lessThan=" + UPDATED_REGISTER_USER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByClientBillNoIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where clientBillNo equals to DEFAULT_CLIENT_BILL_NO
        defaultCaseClaimBillShouldBeFound("clientBillNo.equals=" + DEFAULT_CLIENT_BILL_NO);

        // Get all the caseClaimBillList where clientBillNo equals to UPDATED_CLIENT_BILL_NO
        defaultCaseClaimBillShouldNotBeFound("clientBillNo.equals=" + UPDATED_CLIENT_BILL_NO);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClientBillNoIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where clientBillNo in DEFAULT_CLIENT_BILL_NO or UPDATED_CLIENT_BILL_NO
        defaultCaseClaimBillShouldBeFound("clientBillNo.in=" + DEFAULT_CLIENT_BILL_NO + "," + UPDATED_CLIENT_BILL_NO);

        // Get all the caseClaimBillList where clientBillNo equals to UPDATED_CLIENT_BILL_NO
        defaultCaseClaimBillShouldNotBeFound("clientBillNo.in=" + UPDATED_CLIENT_BILL_NO);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClientBillNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where clientBillNo is not null
        defaultCaseClaimBillShouldBeFound("clientBillNo.specified=true");

        // Get all the caseClaimBillList where clientBillNo is null
        defaultCaseClaimBillShouldNotBeFound("clientBillNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billDescription equals to DEFAULT_BILL_DESCRIPTION
        defaultCaseClaimBillShouldBeFound("billDescription.equals=" + DEFAULT_BILL_DESCRIPTION);

        // Get all the caseClaimBillList where billDescription equals to UPDATED_BILL_DESCRIPTION
        defaultCaseClaimBillShouldNotBeFound("billDescription.equals=" + UPDATED_BILL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billDescription in DEFAULT_BILL_DESCRIPTION or UPDATED_BILL_DESCRIPTION
        defaultCaseClaimBillShouldBeFound("billDescription.in=" + DEFAULT_BILL_DESCRIPTION + "," + UPDATED_BILL_DESCRIPTION);

        // Get all the caseClaimBillList where billDescription equals to UPDATED_BILL_DESCRIPTION
        defaultCaseClaimBillShouldNotBeFound("billDescription.in=" + UPDATED_BILL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billDescription is not null
        defaultCaseClaimBillShouldBeFound("billDescription.specified=true");

        // Get all the caseClaimBillList where billDescription is null
        defaultCaseClaimBillShouldNotBeFound("billDescription.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDueDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where dueDate equals to DEFAULT_DUE_DATE
        defaultCaseClaimBillShouldBeFound("dueDate.equals=" + DEFAULT_DUE_DATE);

        // Get all the caseClaimBillList where dueDate equals to UPDATED_DUE_DATE
        defaultCaseClaimBillShouldNotBeFound("dueDate.equals=" + UPDATED_DUE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDueDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where dueDate in DEFAULT_DUE_DATE or UPDATED_DUE_DATE
        defaultCaseClaimBillShouldBeFound("dueDate.in=" + DEFAULT_DUE_DATE + "," + UPDATED_DUE_DATE);

        // Get all the caseClaimBillList where dueDate equals to UPDATED_DUE_DATE
        defaultCaseClaimBillShouldNotBeFound("dueDate.in=" + UPDATED_DUE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDueDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where dueDate is not null
        defaultCaseClaimBillShouldBeFound("dueDate.specified=true");

        // Get all the caseClaimBillList where dueDate is null
        defaultCaseClaimBillShouldNotBeFound("dueDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByMemberYearIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where memberYear equals to DEFAULT_MEMBER_YEAR
        defaultCaseClaimBillShouldBeFound("memberYear.equals=" + DEFAULT_MEMBER_YEAR);

        // Get all the caseClaimBillList where memberYear equals to UPDATED_MEMBER_YEAR
        defaultCaseClaimBillShouldNotBeFound("memberYear.equals=" + UPDATED_MEMBER_YEAR);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByMemberYearIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where memberYear in DEFAULT_MEMBER_YEAR or UPDATED_MEMBER_YEAR
        defaultCaseClaimBillShouldBeFound("memberYear.in=" + DEFAULT_MEMBER_YEAR + "," + UPDATED_MEMBER_YEAR);

        // Get all the caseClaimBillList where memberYear equals to UPDATED_MEMBER_YEAR
        defaultCaseClaimBillShouldNotBeFound("memberYear.in=" + UPDATED_MEMBER_YEAR);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByMemberYearIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where memberYear is not null
        defaultCaseClaimBillShouldBeFound("memberYear.specified=true");

        // Get all the caseClaimBillList where memberYear is null
        defaultCaseClaimBillShouldNotBeFound("memberYear.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where numberId equals to DEFAULT_NUMBER_ID
        defaultCaseClaimBillShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the caseClaimBillList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseClaimBillShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCaseClaimBillShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the caseClaimBillList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseClaimBillShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where numberId is not null
        defaultCaseClaimBillShouldBeFound("numberId.specified=true");

        // Get all the caseClaimBillList where numberId is null
        defaultCaseClaimBillShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultCaseClaimBillShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseClaimBillList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultCaseClaimBillShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultCaseClaimBillShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseClaimBillList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultCaseClaimBillShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimAmountCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimAmountCurrency equals to DEFAULT_CLAIM_AMOUNT_CURRENCY
        defaultCaseClaimBillShouldBeFound("claimAmountCurrency.equals=" + DEFAULT_CLAIM_AMOUNT_CURRENCY);

        // Get all the caseClaimBillList where claimAmountCurrency equals to UPDATED_CLAIM_AMOUNT_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("claimAmountCurrency.equals=" + UPDATED_CLAIM_AMOUNT_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimAmountCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimAmountCurrency in DEFAULT_CLAIM_AMOUNT_CURRENCY or UPDATED_CLAIM_AMOUNT_CURRENCY
        defaultCaseClaimBillShouldBeFound("claimAmountCurrency.in=" + DEFAULT_CLAIM_AMOUNT_CURRENCY + "," + UPDATED_CLAIM_AMOUNT_CURRENCY);

        // Get all the caseClaimBillList where claimAmountCurrency equals to UPDATED_CLAIM_AMOUNT_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("claimAmountCurrency.in=" + UPDATED_CLAIM_AMOUNT_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimAmountCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimAmountCurrency is not null
        defaultCaseClaimBillShouldBeFound("claimAmountCurrency.specified=true");

        // Get all the caseClaimBillList where claimAmountCurrency is null
        defaultCaseClaimBillShouldNotBeFound("claimAmountCurrency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimAmountCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimAmountCurrency greater than or equals to DEFAULT_CLAIM_AMOUNT_CURRENCY
        defaultCaseClaimBillShouldBeFound("claimAmountCurrency.greaterOrEqualThan=" + DEFAULT_CLAIM_AMOUNT_CURRENCY);

        // Get all the caseClaimBillList where claimAmountCurrency greater than or equals to UPDATED_CLAIM_AMOUNT_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("claimAmountCurrency.greaterOrEqualThan=" + UPDATED_CLAIM_AMOUNT_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimAmountCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimAmountCurrency less than or equals to DEFAULT_CLAIM_AMOUNT_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("claimAmountCurrency.lessThan=" + DEFAULT_CLAIM_AMOUNT_CURRENCY);

        // Get all the caseClaimBillList where claimAmountCurrency less than or equals to UPDATED_CLAIM_AMOUNT_CURRENCY
        defaultCaseClaimBillShouldBeFound("claimAmountCurrency.lessThan=" + UPDATED_CLAIM_AMOUNT_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimAmount equals to DEFAULT_CLAIM_AMOUNT
        defaultCaseClaimBillShouldBeFound("claimAmount.equals=" + DEFAULT_CLAIM_AMOUNT);

        // Get all the caseClaimBillList where claimAmount equals to UPDATED_CLAIM_AMOUNT
        defaultCaseClaimBillShouldNotBeFound("claimAmount.equals=" + UPDATED_CLAIM_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimAmountIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimAmount in DEFAULT_CLAIM_AMOUNT or UPDATED_CLAIM_AMOUNT
        defaultCaseClaimBillShouldBeFound("claimAmount.in=" + DEFAULT_CLAIM_AMOUNT + "," + UPDATED_CLAIM_AMOUNT);

        // Get all the caseClaimBillList where claimAmount equals to UPDATED_CLAIM_AMOUNT
        defaultCaseClaimBillShouldNotBeFound("claimAmount.in=" + UPDATED_CLAIM_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where claimAmount is not null
        defaultCaseClaimBillShouldBeFound("claimAmount.specified=true");

        // Get all the caseClaimBillList where claimAmount is null
        defaultCaseClaimBillShouldNotBeFound("claimAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductible equals to DEFAULT_DEDUCTIBLE
        defaultCaseClaimBillShouldBeFound("deductible.equals=" + DEFAULT_DEDUCTIBLE);

        // Get all the caseClaimBillList where deductible equals to UPDATED_DEDUCTIBLE
        defaultCaseClaimBillShouldNotBeFound("deductible.equals=" + UPDATED_DEDUCTIBLE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductible in DEFAULT_DEDUCTIBLE or UPDATED_DEDUCTIBLE
        defaultCaseClaimBillShouldBeFound("deductible.in=" + DEFAULT_DEDUCTIBLE + "," + UPDATED_DEDUCTIBLE);

        // Get all the caseClaimBillList where deductible equals to UPDATED_DEDUCTIBLE
        defaultCaseClaimBillShouldNotBeFound("deductible.in=" + UPDATED_DEDUCTIBLE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductible is not null
        defaultCaseClaimBillShouldBeFound("deductible.specified=true");

        // Get all the caseClaimBillList where deductible is null
        defaultCaseClaimBillShouldNotBeFound("deductible.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleCurrency equals to DEFAULT_DEDUCTIBLE_CURRENCY
        defaultCaseClaimBillShouldBeFound("deductibleCurrency.equals=" + DEFAULT_DEDUCTIBLE_CURRENCY);

        // Get all the caseClaimBillList where deductibleCurrency equals to UPDATED_DEDUCTIBLE_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("deductibleCurrency.equals=" + UPDATED_DEDUCTIBLE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleCurrency in DEFAULT_DEDUCTIBLE_CURRENCY or UPDATED_DEDUCTIBLE_CURRENCY
        defaultCaseClaimBillShouldBeFound("deductibleCurrency.in=" + DEFAULT_DEDUCTIBLE_CURRENCY + "," + UPDATED_DEDUCTIBLE_CURRENCY);

        // Get all the caseClaimBillList where deductibleCurrency equals to UPDATED_DEDUCTIBLE_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("deductibleCurrency.in=" + UPDATED_DEDUCTIBLE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleCurrency is not null
        defaultCaseClaimBillShouldBeFound("deductibleCurrency.specified=true");

        // Get all the caseClaimBillList where deductibleCurrency is null
        defaultCaseClaimBillShouldNotBeFound("deductibleCurrency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleCurrency greater than or equals to DEFAULT_DEDUCTIBLE_CURRENCY
        defaultCaseClaimBillShouldBeFound("deductibleCurrency.greaterOrEqualThan=" + DEFAULT_DEDUCTIBLE_CURRENCY);

        // Get all the caseClaimBillList where deductibleCurrency greater than or equals to UPDATED_DEDUCTIBLE_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("deductibleCurrency.greaterOrEqualThan=" + UPDATED_DEDUCTIBLE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleCurrency less than or equals to DEFAULT_DEDUCTIBLE_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("deductibleCurrency.lessThan=" + DEFAULT_DEDUCTIBLE_CURRENCY);

        // Get all the caseClaimBillList where deductibleCurrency less than or equals to UPDATED_DEDUCTIBLE_CURRENCY
        defaultCaseClaimBillShouldBeFound("deductibleCurrency.lessThan=" + UPDATED_DEDUCTIBLE_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleCurrencyRateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleCurrencyRate equals to DEFAULT_DEDUCTIBLE_CURRENCY_RATE
        defaultCaseClaimBillShouldBeFound("deductibleCurrencyRate.equals=" + DEFAULT_DEDUCTIBLE_CURRENCY_RATE);

        // Get all the caseClaimBillList where deductibleCurrencyRate equals to UPDATED_DEDUCTIBLE_CURRENCY_RATE
        defaultCaseClaimBillShouldNotBeFound("deductibleCurrencyRate.equals=" + UPDATED_DEDUCTIBLE_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleCurrencyRateIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleCurrencyRate in DEFAULT_DEDUCTIBLE_CURRENCY_RATE or UPDATED_DEDUCTIBLE_CURRENCY_RATE
        defaultCaseClaimBillShouldBeFound("deductibleCurrencyRate.in=" + DEFAULT_DEDUCTIBLE_CURRENCY_RATE + "," + UPDATED_DEDUCTIBLE_CURRENCY_RATE);

        // Get all the caseClaimBillList where deductibleCurrencyRate equals to UPDATED_DEDUCTIBLE_CURRENCY_RATE
        defaultCaseClaimBillShouldNotBeFound("deductibleCurrencyRate.in=" + UPDATED_DEDUCTIBLE_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleCurrencyRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleCurrencyRate is not null
        defaultCaseClaimBillShouldBeFound("deductibleCurrencyRate.specified=true");

        // Get all the caseClaimBillList where deductibleCurrencyRate is null
        defaultCaseClaimBillShouldNotBeFound("deductibleCurrencyRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleDollar equals to DEFAULT_DEDUCTIBLE_DOLLAR
        defaultCaseClaimBillShouldBeFound("deductibleDollar.equals=" + DEFAULT_DEDUCTIBLE_DOLLAR);

        // Get all the caseClaimBillList where deductibleDollar equals to UPDATED_DEDUCTIBLE_DOLLAR
        defaultCaseClaimBillShouldNotBeFound("deductibleDollar.equals=" + UPDATED_DEDUCTIBLE_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleDollarIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleDollar in DEFAULT_DEDUCTIBLE_DOLLAR or UPDATED_DEDUCTIBLE_DOLLAR
        defaultCaseClaimBillShouldBeFound("deductibleDollar.in=" + DEFAULT_DEDUCTIBLE_DOLLAR + "," + UPDATED_DEDUCTIBLE_DOLLAR);

        // Get all the caseClaimBillList where deductibleDollar equals to UPDATED_DEDUCTIBLE_DOLLAR
        defaultCaseClaimBillShouldNotBeFound("deductibleDollar.in=" + UPDATED_DEDUCTIBLE_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByDeductibleDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where deductibleDollar is not null
        defaultCaseClaimBillShouldBeFound("deductibleDollar.specified=true");

        // Get all the caseClaimBillList where deductibleDollar is null
        defaultCaseClaimBillShouldNotBeFound("deductibleDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billCurrency equals to DEFAULT_BILL_CURRENCY
        defaultCaseClaimBillShouldBeFound("billCurrency.equals=" + DEFAULT_BILL_CURRENCY);

        // Get all the caseClaimBillList where billCurrency equals to UPDATED_BILL_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("billCurrency.equals=" + UPDATED_BILL_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billCurrency in DEFAULT_BILL_CURRENCY or UPDATED_BILL_CURRENCY
        defaultCaseClaimBillShouldBeFound("billCurrency.in=" + DEFAULT_BILL_CURRENCY + "," + UPDATED_BILL_CURRENCY);

        // Get all the caseClaimBillList where billCurrency equals to UPDATED_BILL_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("billCurrency.in=" + UPDATED_BILL_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billCurrency is not null
        defaultCaseClaimBillShouldBeFound("billCurrency.specified=true");

        // Get all the caseClaimBillList where billCurrency is null
        defaultCaseClaimBillShouldNotBeFound("billCurrency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billCurrency greater than or equals to DEFAULT_BILL_CURRENCY
        defaultCaseClaimBillShouldBeFound("billCurrency.greaterOrEqualThan=" + DEFAULT_BILL_CURRENCY);

        // Get all the caseClaimBillList where billCurrency greater than or equals to UPDATED_BILL_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("billCurrency.greaterOrEqualThan=" + UPDATED_BILL_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billCurrency less than or equals to DEFAULT_BILL_CURRENCY
        defaultCaseClaimBillShouldNotBeFound("billCurrency.lessThan=" + DEFAULT_BILL_CURRENCY);

        // Get all the caseClaimBillList where billCurrency less than or equals to UPDATED_BILL_CURRENCY
        defaultCaseClaimBillShouldBeFound("billCurrency.lessThan=" + UPDATED_BILL_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billAmount equals to DEFAULT_BILL_AMOUNT
        defaultCaseClaimBillShouldBeFound("billAmount.equals=" + DEFAULT_BILL_AMOUNT);

        // Get all the caseClaimBillList where billAmount equals to UPDATED_BILL_AMOUNT
        defaultCaseClaimBillShouldNotBeFound("billAmount.equals=" + UPDATED_BILL_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillAmountIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billAmount in DEFAULT_BILL_AMOUNT or UPDATED_BILL_AMOUNT
        defaultCaseClaimBillShouldBeFound("billAmount.in=" + DEFAULT_BILL_AMOUNT + "," + UPDATED_BILL_AMOUNT);

        // Get all the caseClaimBillList where billAmount equals to UPDATED_BILL_AMOUNT
        defaultCaseClaimBillShouldNotBeFound("billAmount.in=" + UPDATED_BILL_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billAmount is not null
        defaultCaseClaimBillShouldBeFound("billAmount.specified=true");

        // Get all the caseClaimBillList where billAmount is null
        defaultCaseClaimBillShouldNotBeFound("billAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillCurrencyRateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billCurrencyRate equals to DEFAULT_BILL_CURRENCY_RATE
        defaultCaseClaimBillShouldBeFound("billCurrencyRate.equals=" + DEFAULT_BILL_CURRENCY_RATE);

        // Get all the caseClaimBillList where billCurrencyRate equals to UPDATED_BILL_CURRENCY_RATE
        defaultCaseClaimBillShouldNotBeFound("billCurrencyRate.equals=" + UPDATED_BILL_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillCurrencyRateIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billCurrencyRate in DEFAULT_BILL_CURRENCY_RATE or UPDATED_BILL_CURRENCY_RATE
        defaultCaseClaimBillShouldBeFound("billCurrencyRate.in=" + DEFAULT_BILL_CURRENCY_RATE + "," + UPDATED_BILL_CURRENCY_RATE);

        // Get all the caseClaimBillList where billCurrencyRate equals to UPDATED_BILL_CURRENCY_RATE
        defaultCaseClaimBillShouldNotBeFound("billCurrencyRate.in=" + UPDATED_BILL_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillCurrencyRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billCurrencyRate is not null
        defaultCaseClaimBillShouldBeFound("billCurrencyRate.specified=true");

        // Get all the caseClaimBillList where billCurrencyRate is null
        defaultCaseClaimBillShouldNotBeFound("billCurrencyRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillAmountDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billAmountDollar equals to DEFAULT_BILL_AMOUNT_DOLLAR
        defaultCaseClaimBillShouldBeFound("billAmountDollar.equals=" + DEFAULT_BILL_AMOUNT_DOLLAR);

        // Get all the caseClaimBillList where billAmountDollar equals to UPDATED_BILL_AMOUNT_DOLLAR
        defaultCaseClaimBillShouldNotBeFound("billAmountDollar.equals=" + UPDATED_BILL_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillAmountDollarIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billAmountDollar in DEFAULT_BILL_AMOUNT_DOLLAR or UPDATED_BILL_AMOUNT_DOLLAR
        defaultCaseClaimBillShouldBeFound("billAmountDollar.in=" + DEFAULT_BILL_AMOUNT_DOLLAR + "," + UPDATED_BILL_AMOUNT_DOLLAR);

        // Get all the caseClaimBillList where billAmountDollar equals to UPDATED_BILL_AMOUNT_DOLLAR
        defaultCaseClaimBillShouldNotBeFound("billAmountDollar.in=" + UPDATED_BILL_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByBillAmountDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where billAmountDollar is not null
        defaultCaseClaimBillShouldBeFound("billAmountDollar.specified=true");

        // Get all the caseClaimBillList where billAmountDollar is null
        defaultCaseClaimBillShouldNotBeFound("billAmountDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByIsSignedIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where isSigned equals to DEFAULT_IS_SIGNED
        defaultCaseClaimBillShouldBeFound("isSigned.equals=" + DEFAULT_IS_SIGNED);

        // Get all the caseClaimBillList where isSigned equals to UPDATED_IS_SIGNED
        defaultCaseClaimBillShouldNotBeFound("isSigned.equals=" + UPDATED_IS_SIGNED);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByIsSignedIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where isSigned in DEFAULT_IS_SIGNED or UPDATED_IS_SIGNED
        defaultCaseClaimBillShouldBeFound("isSigned.in=" + DEFAULT_IS_SIGNED + "," + UPDATED_IS_SIGNED);

        // Get all the caseClaimBillList where isSigned equals to UPDATED_IS_SIGNED
        defaultCaseClaimBillShouldNotBeFound("isSigned.in=" + UPDATED_IS_SIGNED);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByIsSignedIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where isSigned is not null
        defaultCaseClaimBillShouldBeFound("isSigned.specified=true");

        // Get all the caseClaimBillList where isSigned is null
        defaultCaseClaimBillShouldNotBeFound("isSigned.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsBySignUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where signUser equals to DEFAULT_SIGN_USER
        defaultCaseClaimBillShouldBeFound("signUser.equals=" + DEFAULT_SIGN_USER);

        // Get all the caseClaimBillList where signUser equals to UPDATED_SIGN_USER
        defaultCaseClaimBillShouldNotBeFound("signUser.equals=" + UPDATED_SIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsBySignUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where signUser in DEFAULT_SIGN_USER or UPDATED_SIGN_USER
        defaultCaseClaimBillShouldBeFound("signUser.in=" + DEFAULT_SIGN_USER + "," + UPDATED_SIGN_USER);

        // Get all the caseClaimBillList where signUser equals to UPDATED_SIGN_USER
        defaultCaseClaimBillShouldNotBeFound("signUser.in=" + UPDATED_SIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsBySignUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where signUser is not null
        defaultCaseClaimBillShouldBeFound("signUser.specified=true");

        // Get all the caseClaimBillList where signUser is null
        defaultCaseClaimBillShouldNotBeFound("signUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsBySignUserIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where signUser greater than or equals to DEFAULT_SIGN_USER
        defaultCaseClaimBillShouldBeFound("signUser.greaterOrEqualThan=" + DEFAULT_SIGN_USER);

        // Get all the caseClaimBillList where signUser greater than or equals to UPDATED_SIGN_USER
        defaultCaseClaimBillShouldNotBeFound("signUser.greaterOrEqualThan=" + UPDATED_SIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsBySignUserIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where signUser less than or equals to DEFAULT_SIGN_USER
        defaultCaseClaimBillShouldNotBeFound("signUser.lessThan=" + DEFAULT_SIGN_USER);

        // Get all the caseClaimBillList where signUser less than or equals to UPDATED_SIGN_USER
        defaultCaseClaimBillShouldBeFound("signUser.lessThan=" + UPDATED_SIGN_USER);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsBySignDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where signDate equals to DEFAULT_SIGN_DATE
        defaultCaseClaimBillShouldBeFound("signDate.equals=" + DEFAULT_SIGN_DATE);

        // Get all the caseClaimBillList where signDate equals to UPDATED_SIGN_DATE
        defaultCaseClaimBillShouldNotBeFound("signDate.equals=" + UPDATED_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsBySignDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where signDate in DEFAULT_SIGN_DATE or UPDATED_SIGN_DATE
        defaultCaseClaimBillShouldBeFound("signDate.in=" + DEFAULT_SIGN_DATE + "," + UPDATED_SIGN_DATE);

        // Get all the caseClaimBillList where signDate equals to UPDATED_SIGN_DATE
        defaultCaseClaimBillShouldNotBeFound("signDate.in=" + UPDATED_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsBySignDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where signDate is not null
        defaultCaseClaimBillShouldBeFound("signDate.specified=true");

        // Get all the caseClaimBillList where signDate is null
        defaultCaseClaimBillShouldNotBeFound("signDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByProcessIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where processId equals to DEFAULT_PROCESS_ID
        defaultCaseClaimBillShouldBeFound("processId.equals=" + DEFAULT_PROCESS_ID);

        // Get all the caseClaimBillList where processId equals to UPDATED_PROCESS_ID
        defaultCaseClaimBillShouldNotBeFound("processId.equals=" + UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByProcessIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where processId in DEFAULT_PROCESS_ID or UPDATED_PROCESS_ID
        defaultCaseClaimBillShouldBeFound("processId.in=" + DEFAULT_PROCESS_ID + "," + UPDATED_PROCESS_ID);

        // Get all the caseClaimBillList where processId equals to UPDATED_PROCESS_ID
        defaultCaseClaimBillShouldNotBeFound("processId.in=" + UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByProcessIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where processId is not null
        defaultCaseClaimBillShouldBeFound("processId.specified=true");

        // Get all the caseClaimBillList where processId is null
        defaultCaseClaimBillShouldNotBeFound("processId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByProcessIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where processId greater than or equals to DEFAULT_PROCESS_ID
        defaultCaseClaimBillShouldBeFound("processId.greaterOrEqualThan=" + DEFAULT_PROCESS_ID);

        // Get all the caseClaimBillList where processId greater than or equals to UPDATED_PROCESS_ID
        defaultCaseClaimBillShouldNotBeFound("processId.greaterOrEqualThan=" + UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByProcessIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where processId less than or equals to DEFAULT_PROCESS_ID
        defaultCaseClaimBillShouldNotBeFound("processId.lessThan=" + DEFAULT_PROCESS_ID);

        // Get all the caseClaimBillList where processId less than or equals to UPDATED_PROCESS_ID
        defaultCaseClaimBillShouldBeFound("processId.lessThan=" + UPDATED_PROCESS_ID);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByPrintNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where printNumber equals to DEFAULT_PRINT_NUMBER
        defaultCaseClaimBillShouldBeFound("printNumber.equals=" + DEFAULT_PRINT_NUMBER);

        // Get all the caseClaimBillList where printNumber equals to UPDATED_PRINT_NUMBER
        defaultCaseClaimBillShouldNotBeFound("printNumber.equals=" + UPDATED_PRINT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByPrintNumberIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where printNumber in DEFAULT_PRINT_NUMBER or UPDATED_PRINT_NUMBER
        defaultCaseClaimBillShouldBeFound("printNumber.in=" + DEFAULT_PRINT_NUMBER + "," + UPDATED_PRINT_NUMBER);

        // Get all the caseClaimBillList where printNumber equals to UPDATED_PRINT_NUMBER
        defaultCaseClaimBillShouldNotBeFound("printNumber.in=" + UPDATED_PRINT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByPrintNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where printNumber is not null
        defaultCaseClaimBillShouldBeFound("printNumber.specified=true");

        // Get all the caseClaimBillList where printNumber is null
        defaultCaseClaimBillShouldNotBeFound("printNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByPrintNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where printNumber greater than or equals to DEFAULT_PRINT_NUMBER
        defaultCaseClaimBillShouldBeFound("printNumber.greaterOrEqualThan=" + DEFAULT_PRINT_NUMBER);

        // Get all the caseClaimBillList where printNumber greater than or equals to UPDATED_PRINT_NUMBER
        defaultCaseClaimBillShouldNotBeFound("printNumber.greaterOrEqualThan=" + UPDATED_PRINT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillsByPrintNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        // Get all the caseClaimBillList where printNumber less than or equals to DEFAULT_PRINT_NUMBER
        defaultCaseClaimBillShouldNotBeFound("printNumber.lessThan=" + DEFAULT_PRINT_NUMBER);

        // Get all the caseClaimBillList where printNumber less than or equals to UPDATED_PRINT_NUMBER
        defaultCaseClaimBillShouldBeFound("printNumber.lessThan=" + UPDATED_PRINT_NUMBER);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsBySubcaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselSubCase subcase = VesselSubCaseResourceIntTest.createEntity(em);
        em.persist(subcase);
        em.flush();
        caseClaimBill.setSubcase(subcase);
        caseClaimBillRepository.saveAndFlush(caseClaimBill);
        Long subcaseId = subcase.getId();

        // Get all the caseClaimBillList where subcase equals to subcaseId
        defaultCaseClaimBillShouldBeFound("subcaseId.equals=" + subcaseId);

        // Get all the caseClaimBillList where subcase equals to subcaseId + 1
        defaultCaseClaimBillShouldNotBeFound("subcaseId.equals=" + (subcaseId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimBillStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        ClaimBillStatus claimBillStatus = ClaimBillStatusResourceIntTest.createEntity(em);
        em.persist(claimBillStatus);
        em.flush();
        caseClaimBill.setClaimBillStatus(claimBillStatus);
        caseClaimBillRepository.saveAndFlush(caseClaimBill);
        Long claimBillStatusId = claimBillStatus.getId();

        // Get all the caseClaimBillList where claimBillStatus equals to claimBillStatusId
        defaultCaseClaimBillShouldBeFound("claimBillStatusId.equals=" + claimBillStatusId);

        // Get all the caseClaimBillList where claimBillStatus equals to claimBillStatusId + 1
        defaultCaseClaimBillShouldNotBeFound("claimBillStatusId.equals=" + (claimBillStatusId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimBillTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ClaimBillType claimBillType = ClaimBillTypeResourceIntTest.createEntity(em);
        em.persist(claimBillType);
        em.flush();
        caseClaimBill.setClaimBillType(claimBillType);
        caseClaimBillRepository.saveAndFlush(caseClaimBill);
        Long claimBillTypeId = claimBillType.getId();

        // Get all the caseClaimBillList where claimBillType equals to claimBillTypeId
        defaultCaseClaimBillShouldBeFound("claimBillTypeId.equals=" + claimBillTypeId);

        // Get all the caseClaimBillList where claimBillType equals to claimBillTypeId + 1
        defaultCaseClaimBillShouldNotBeFound("claimBillTypeId.equals=" + (claimBillTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByClaimBillFinanceTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ClaimBillFinanceType claimBillFinanceType = ClaimBillFinanceTypeResourceIntTest.createEntity(em);
        em.persist(claimBillFinanceType);
        em.flush();
        caseClaimBill.setClaimBillFinanceType(claimBillFinanceType);
        caseClaimBillRepository.saveAndFlush(caseClaimBill);
        Long claimBillFinanceTypeId = claimBillFinanceType.getId();

        // Get all the caseClaimBillList where claimBillFinanceType equals to claimBillFinanceTypeId
        defaultCaseClaimBillShouldBeFound("claimBillFinanceTypeId.equals=" + claimBillFinanceTypeId);

        // Get all the caseClaimBillList where claimBillFinanceType equals to claimBillFinanceTypeId + 1
        defaultCaseClaimBillShouldNotBeFound("claimBillFinanceTypeId.equals=" + (claimBillFinanceTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillsByCreditorIsEqualToSomething() throws Exception {
        // Initialize the database
        Creditor creditor = CreditorResourceIntTest.createEntity(em);
        em.persist(creditor);
        em.flush();
        caseClaimBill.setCreditor(creditor);
        caseClaimBillRepository.saveAndFlush(caseClaimBill);
        Long creditorId = creditor.getId();

        // Get all the caseClaimBillList where creditor equals to creditorId
        defaultCaseClaimBillShouldBeFound("creditorId.equals=" + creditorId);

        // Get all the caseClaimBillList where creditor equals to creditorId + 1
        defaultCaseClaimBillShouldNotBeFound("creditorId.equals=" + (creditorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCaseClaimBillShouldBeFound(String filter) throws Exception {
        restCaseClaimBillMockMvc.perform(get("/api/case-claim-bills?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaimBill.getId().intValue())))
            .andExpect(jsonPath("$.[*].claimBillCode").value(hasItem(DEFAULT_CLAIM_BILL_CODE.toString())))
            .andExpect(jsonPath("$.[*].claimBillDate").value(hasItem(DEFAULT_CLAIM_BILL_DATE.toString())))
            .andExpect(jsonPath("$.[*].registerUserId").value(hasItem(DEFAULT_REGISTER_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].clientBillNo").value(hasItem(DEFAULT_CLIENT_BILL_NO.toString())))
            .andExpect(jsonPath("$.[*].billDescription").value(hasItem(DEFAULT_BILL_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].memberYear").value(hasItem(DEFAULT_MEMBER_YEAR.toString())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].claimAmountCurrency").value(hasItem(DEFAULT_CLAIM_AMOUNT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].claimAmount").value(hasItem(DEFAULT_CLAIM_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].deductible").value(hasItem(DEFAULT_DEDUCTIBLE.intValue())))
            .andExpect(jsonPath("$.[*].deductibleCurrency").value(hasItem(DEFAULT_DEDUCTIBLE_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].deductibleCurrencyRate").value(hasItem(DEFAULT_DEDUCTIBLE_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].deductibleDollar").value(hasItem(DEFAULT_DEDUCTIBLE_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].billCurrency").value(hasItem(DEFAULT_BILL_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].billAmount").value(hasItem(DEFAULT_BILL_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].billCurrencyRate").value(hasItem(DEFAULT_BILL_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].billAmountDollar").value(hasItem(DEFAULT_BILL_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].isSigned").value(hasItem(DEFAULT_IS_SIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].signUser").value(hasItem(DEFAULT_SIGN_USER.intValue())))
            .andExpect(jsonPath("$.[*].signDate").value(hasItem(DEFAULT_SIGN_DATE.toString())))
            .andExpect(jsonPath("$.[*].processId").value(hasItem(DEFAULT_PROCESS_ID.intValue())))
            .andExpect(jsonPath("$.[*].printNumber").value(hasItem(DEFAULT_PRINT_NUMBER)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCaseClaimBillShouldNotBeFound(String filter) throws Exception {
        restCaseClaimBillMockMvc.perform(get("/api/case-claim-bills?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCaseClaimBill() throws Exception {
        // Get the caseClaimBill
        restCaseClaimBillMockMvc.perform(get("/api/case-claim-bills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseClaimBill() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        int databaseSizeBeforeUpdate = caseClaimBillRepository.findAll().size();

        // Update the caseClaimBill
        CaseClaimBill updatedCaseClaimBill = caseClaimBillRepository.findById(caseClaimBill.getId()).get();
        // Disconnect from session so that the updates on updatedCaseClaimBill are not directly saved in db
        em.detach(updatedCaseClaimBill);
        updatedCaseClaimBill
            .claimBillCode(UPDATED_CLAIM_BILL_CODE)
            .claimBillDate(UPDATED_CLAIM_BILL_DATE)
            .registerUserId(UPDATED_REGISTER_USER_ID)
            .clientBillNo(UPDATED_CLIENT_BILL_NO)
            .billDescription(UPDATED_BILL_DESCRIPTION)
            .dueDate(UPDATED_DUE_DATE)
            .memberYear(UPDATED_MEMBER_YEAR)
            .numberId(UPDATED_NUMBER_ID)
            .claimAmountCurrency(UPDATED_CLAIM_AMOUNT_CURRENCY)
            .claimAmount(UPDATED_CLAIM_AMOUNT)
            .deductible(UPDATED_DEDUCTIBLE)
            .deductibleCurrency(UPDATED_DEDUCTIBLE_CURRENCY)
            .deductibleCurrencyRate(UPDATED_DEDUCTIBLE_CURRENCY_RATE)
            .deductibleDollar(UPDATED_DEDUCTIBLE_DOLLAR)
            .billCurrency(UPDATED_BILL_CURRENCY)
            .billAmount(UPDATED_BILL_AMOUNT)
            .billCurrencyRate(UPDATED_BILL_CURRENCY_RATE)
            .billAmountDollar(UPDATED_BILL_AMOUNT_DOLLAR)
            .remark(UPDATED_REMARK)
            .isSigned(UPDATED_IS_SIGNED)
            .signUser(UPDATED_SIGN_USER)
            .signDate(UPDATED_SIGN_DATE)
            .processId(UPDATED_PROCESS_ID)
            .printNumber(UPDATED_PRINT_NUMBER);
        CaseClaimBillDTO caseClaimBillDTO = caseClaimBillMapper.toDto(updatedCaseClaimBill);

        restCaseClaimBillMockMvc.perform(put("/api/case-claim-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillDTO)))
            .andExpect(status().isOk());

        // Validate the CaseClaimBill in the database
        List<CaseClaimBill> caseClaimBillList = caseClaimBillRepository.findAll();
        assertThat(caseClaimBillList).hasSize(databaseSizeBeforeUpdate);
        CaseClaimBill testCaseClaimBill = caseClaimBillList.get(caseClaimBillList.size() - 1);
        assertThat(testCaseClaimBill.getClaimBillCode()).isEqualTo(UPDATED_CLAIM_BILL_CODE);
        assertThat(testCaseClaimBill.getClaimBillDate()).isEqualTo(UPDATED_CLAIM_BILL_DATE);
        assertThat(testCaseClaimBill.getRegisterUserId()).isEqualTo(UPDATED_REGISTER_USER_ID);
        assertThat(testCaseClaimBill.getClientBillNo()).isEqualTo(UPDATED_CLIENT_BILL_NO);
        assertThat(testCaseClaimBill.getBillDescription()).isEqualTo(UPDATED_BILL_DESCRIPTION);
        assertThat(testCaseClaimBill.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testCaseClaimBill.getMemberYear()).isEqualTo(UPDATED_MEMBER_YEAR);
        assertThat(testCaseClaimBill.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCaseClaimBill.getClaimAmountCurrency()).isEqualTo(UPDATED_CLAIM_AMOUNT_CURRENCY);
        assertThat(testCaseClaimBill.getClaimAmount()).isEqualTo(UPDATED_CLAIM_AMOUNT);
        assertThat(testCaseClaimBill.getDeductible()).isEqualTo(UPDATED_DEDUCTIBLE);
        assertThat(testCaseClaimBill.getDeductibleCurrency()).isEqualTo(UPDATED_DEDUCTIBLE_CURRENCY);
        assertThat(testCaseClaimBill.getDeductibleCurrencyRate()).isEqualTo(UPDATED_DEDUCTIBLE_CURRENCY_RATE);
        assertThat(testCaseClaimBill.getDeductibleDollar()).isEqualTo(UPDATED_DEDUCTIBLE_DOLLAR);
        assertThat(testCaseClaimBill.getBillCurrency()).isEqualTo(UPDATED_BILL_CURRENCY);
        assertThat(testCaseClaimBill.getBillAmount()).isEqualTo(UPDATED_BILL_AMOUNT);
        assertThat(testCaseClaimBill.getBillCurrencyRate()).isEqualTo(UPDATED_BILL_CURRENCY_RATE);
        assertThat(testCaseClaimBill.getBillAmountDollar()).isEqualTo(UPDATED_BILL_AMOUNT_DOLLAR);
        assertThat(testCaseClaimBill.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCaseClaimBill.isIsSigned()).isEqualTo(UPDATED_IS_SIGNED);
        assertThat(testCaseClaimBill.getSignUser()).isEqualTo(UPDATED_SIGN_USER);
        assertThat(testCaseClaimBill.getSignDate()).isEqualTo(UPDATED_SIGN_DATE);
        assertThat(testCaseClaimBill.getProcessId()).isEqualTo(UPDATED_PROCESS_ID);
        assertThat(testCaseClaimBill.getPrintNumber()).isEqualTo(UPDATED_PRINT_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseClaimBill() throws Exception {
        int databaseSizeBeforeUpdate = caseClaimBillRepository.findAll().size();

        // Create the CaseClaimBill
        CaseClaimBillDTO caseClaimBillDTO = caseClaimBillMapper.toDto(caseClaimBill);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCaseClaimBillMockMvc.perform(put("/api/case-claim-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaimBill in the database
        List<CaseClaimBill> caseClaimBillList = caseClaimBillRepository.findAll();
        assertThat(caseClaimBillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseClaimBill() throws Exception {
        // Initialize the database
        caseClaimBillRepository.saveAndFlush(caseClaimBill);

        int databaseSizeBeforeDelete = caseClaimBillRepository.findAll().size();

        // Get the caseClaimBill
        restCaseClaimBillMockMvc.perform(delete("/api/case-claim-bills/{id}", caseClaimBill.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseClaimBill> caseClaimBillList = caseClaimBillRepository.findAll();
        assertThat(caseClaimBillList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaimBill.class);
        CaseClaimBill caseClaimBill1 = new CaseClaimBill();
        caseClaimBill1.setId(1L);
        CaseClaimBill caseClaimBill2 = new CaseClaimBill();
        caseClaimBill2.setId(caseClaimBill1.getId());
        assertThat(caseClaimBill1).isEqualTo(caseClaimBill2);
        caseClaimBill2.setId(2L);
        assertThat(caseClaimBill1).isNotEqualTo(caseClaimBill2);
        caseClaimBill1.setId(null);
        assertThat(caseClaimBill1).isNotEqualTo(caseClaimBill2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaimBillDTO.class);
        CaseClaimBillDTO caseClaimBillDTO1 = new CaseClaimBillDTO();
        caseClaimBillDTO1.setId(1L);
        CaseClaimBillDTO caseClaimBillDTO2 = new CaseClaimBillDTO();
        assertThat(caseClaimBillDTO1).isNotEqualTo(caseClaimBillDTO2);
        caseClaimBillDTO2.setId(caseClaimBillDTO1.getId());
        assertThat(caseClaimBillDTO1).isEqualTo(caseClaimBillDTO2);
        caseClaimBillDTO2.setId(2L);
        assertThat(caseClaimBillDTO1).isNotEqualTo(caseClaimBillDTO2);
        caseClaimBillDTO1.setId(null);
        assertThat(caseClaimBillDTO1).isNotEqualTo(caseClaimBillDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseClaimBillMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseClaimBillMapper.fromId(null)).isNull();
    }
}
