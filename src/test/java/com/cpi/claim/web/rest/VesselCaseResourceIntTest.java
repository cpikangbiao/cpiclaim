package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.domain.CpiInsuranceType;
import com.cpi.claim.domain.CaseStatusType;
import com.cpi.claim.domain.CaseSettlementMode;
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.service.VesselCaseService;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.mapper.VesselCaseMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.VesselCaseCriteria;
import com.cpi.claim.service.VesselCaseQueryService;

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
 * Test class for the VesselCaseResource REST controller.
 *
 * @see VesselCaseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class VesselCaseResourceIntTest {

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final String DEFAULT_CASE_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_CASE_YEAR = "BBBBBBBBBB";

    private static final Long DEFAULT_INSURED_VESSEL_ID = 1L;
    private static final Long UPDATED_INSURED_VESSEL_ID = 2L;

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VESSEL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VESSEL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_CHINESE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_CHINESE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VESSEL_CHINESE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VESSEL_CHINESE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_REINSURE_ID = 1L;
    private static final Long UPDATED_REINSURE_ID = 2L;

    private static final BigDecimal DEFAULT_DEDUCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCT = new BigDecimal(2);

    private static final Long DEFAULT_ASSIGNED_HANDLER = 1L;
    private static final Long UPDATED_ASSIGNED_HANDLER = 2L;

    private static final Instant DEFAULT_ASSIGNED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ASSIGNED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_REGISTERED_HANDLER = 1L;
    private static final Long UPDATED_REGISTERED_HANDLER = 2L;

    private static final Instant DEFAULT_REGISTERED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REGISTERED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CASE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CASE_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CASE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CASE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CASE_LOCATION = 1L;
    private static final Long UPDATED_CASE_LOCATION = 2L;

    private static final String DEFAULT_CASE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CASE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_VOYAGE_NO = "AAAAAAAAAA";
    private static final String UPDATED_VOYAGE_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_LOADING_PORT = 1L;
    private static final Long UPDATED_LOADING_PORT = 2L;

    private static final Instant DEFAULT_LOADING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LOADING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_DISCHARGING_PORT = 1L;
    private static final Long UPDATED_DISCHARGING_PORT = 2L;

    private static final Instant DEFAULT_DISCHARGING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DISCHARGING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LIMIT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LIMIT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CP_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CP_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CP_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ARREST_VESSEL = "AAAAAAAAAA";
    private static final String UPDATED_ARREST_VESSEL = "BBBBBBBBBB";

    private static final Long DEFAULT_JURISDICTION = 1L;
    private static final Long UPDATED_JURISDICTION = 2L;

    private static final Long DEFAULT_APPLICABLE_LAW = 1L;
    private static final Long UPDATED_APPLICABLE_LAW = 2L;

    private static final Instant DEFAULT_CLOSE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLOSE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CLOSE_HANDLER = 1L;
    private static final Long UPDATED_CLOSE_HANDLER = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SETTLEMENT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SETTLEMENT_AMOUNT = new BigDecimal(2);

    private static final Instant DEFAULT_SETTLEMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SETTLEMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private VesselCaseRepository vesselCaseRepository;


    @Autowired
    private VesselCaseMapper vesselCaseMapper;
    

    @Autowired
    private VesselCaseService vesselCaseService;

    @Autowired
    private VesselCaseQueryService vesselCaseQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVesselCaseMockMvc;

    private VesselCase vesselCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VesselCaseResource vesselCaseResource = new VesselCaseResource(vesselCaseService, vesselCaseQueryService);
        this.restVesselCaseMockMvc = MockMvcBuilders.standaloneSetup(vesselCaseResource)
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
    public static VesselCase createEntity(EntityManager em) {
        VesselCase vesselCase = new VesselCase()
            .numberId(DEFAULT_NUMBER_ID)
            .caseYear(DEFAULT_CASE_YEAR)
            .insuredVesselId(DEFAULT_INSURED_VESSEL_ID)
            .companyName(DEFAULT_COMPANY_NAME)
            .vesselName(DEFAULT_VESSEL_NAME)
            .companyChineseName(DEFAULT_COMPANY_CHINESE_NAME)
            .vesselChineseName(DEFAULT_VESSEL_CHINESE_NAME)
            .reinsureId(DEFAULT_REINSURE_ID)
            .deduct(DEFAULT_DEDUCT)
            .assignedHandler(DEFAULT_ASSIGNED_HANDLER)
            .assignedTime(DEFAULT_ASSIGNED_TIME)
            .registeredHandler(DEFAULT_REGISTERED_HANDLER)
            .registeredDate(DEFAULT_REGISTERED_DATE)
            .caseCode(DEFAULT_CASE_CODE)
            .caseDate(DEFAULT_CASE_DATE)
            .caseLocation(DEFAULT_CASE_LOCATION)
            .caseDescription(DEFAULT_CASE_DESCRIPTION)
            .voyageNo(DEFAULT_VOYAGE_NO)
            .loadingPort(DEFAULT_LOADING_PORT)
            .loadingDate(DEFAULT_LOADING_DATE)
            .dischargingPort(DEFAULT_DISCHARGING_PORT)
            .dischargingDate(DEFAULT_DISCHARGING_DATE)
            .limitTime(DEFAULT_LIMIT_TIME)
            .cpDate(DEFAULT_CP_DATE)
            .cpType(DEFAULT_CP_TYPE)
            .arrestVessel(DEFAULT_ARREST_VESSEL)
            .jurisdiction(DEFAULT_JURISDICTION)
            .applicableLaw(DEFAULT_APPLICABLE_LAW)
            .closeDate(DEFAULT_CLOSE_DATE)
            .closeHandler(DEFAULT_CLOSE_HANDLER)
            .remark(DEFAULT_REMARK)
            .settlementAmount(DEFAULT_SETTLEMENT_AMOUNT)
            .settlementDate(DEFAULT_SETTLEMENT_DATE);
        return vesselCase;
    }

    @Before
    public void initTest() {
        vesselCase = createEntity(em);
    }

    @Test
    @Transactional
    public void createVesselCase() throws Exception {
        int databaseSizeBeforeCreate = vesselCaseRepository.findAll().size();

        // Create the VesselCase
        VesselCaseDTO vesselCaseDTO = vesselCaseMapper.toDto(vesselCase);
        restVesselCaseMockMvc.perform(post("/api/vessel-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselCaseDTO)))
            .andExpect(status().isCreated());

        // Validate the VesselCase in the database
        List<VesselCase> vesselCaseList = vesselCaseRepository.findAll();
        assertThat(vesselCaseList).hasSize(databaseSizeBeforeCreate + 1);
        VesselCase testVesselCase = vesselCaseList.get(vesselCaseList.size() - 1);
        assertThat(testVesselCase.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testVesselCase.getCaseYear()).isEqualTo(DEFAULT_CASE_YEAR);
        assertThat(testVesselCase.getInsuredVesselId()).isEqualTo(DEFAULT_INSURED_VESSEL_ID);
        assertThat(testVesselCase.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testVesselCase.getVesselName()).isEqualTo(DEFAULT_VESSEL_NAME);
        assertThat(testVesselCase.getCompanyChineseName()).isEqualTo(DEFAULT_COMPANY_CHINESE_NAME);
        assertThat(testVesselCase.getVesselChineseName()).isEqualTo(DEFAULT_VESSEL_CHINESE_NAME);
        assertThat(testVesselCase.getReinsureId()).isEqualTo(DEFAULT_REINSURE_ID);
        assertThat(testVesselCase.getDeduct()).isEqualTo(DEFAULT_DEDUCT);
        assertThat(testVesselCase.getAssignedHandler()).isEqualTo(DEFAULT_ASSIGNED_HANDLER);
        assertThat(testVesselCase.getAssignedTime()).isEqualTo(DEFAULT_ASSIGNED_TIME);
        assertThat(testVesselCase.getRegisteredHandler()).isEqualTo(DEFAULT_REGISTERED_HANDLER);
        assertThat(testVesselCase.getRegisteredDate()).isEqualTo(DEFAULT_REGISTERED_DATE);
        assertThat(testVesselCase.getCaseCode()).isEqualTo(DEFAULT_CASE_CODE);
        assertThat(testVesselCase.getCaseDate()).isEqualTo(DEFAULT_CASE_DATE);
        assertThat(testVesselCase.getCaseLocation()).isEqualTo(DEFAULT_CASE_LOCATION);
        assertThat(testVesselCase.getCaseDescription()).isEqualTo(DEFAULT_CASE_DESCRIPTION);
        assertThat(testVesselCase.getVoyageNo()).isEqualTo(DEFAULT_VOYAGE_NO);
        assertThat(testVesselCase.getLoadingPort()).isEqualTo(DEFAULT_LOADING_PORT);
        assertThat(testVesselCase.getLoadingDate()).isEqualTo(DEFAULT_LOADING_DATE);
        assertThat(testVesselCase.getDischargingPort()).isEqualTo(DEFAULT_DISCHARGING_PORT);
        assertThat(testVesselCase.getDischargingDate()).isEqualTo(DEFAULT_DISCHARGING_DATE);
        assertThat(testVesselCase.getLimitTime()).isEqualTo(DEFAULT_LIMIT_TIME);
        assertThat(testVesselCase.getCpDate()).isEqualTo(DEFAULT_CP_DATE);
        assertThat(testVesselCase.getCpType()).isEqualTo(DEFAULT_CP_TYPE);
        assertThat(testVesselCase.getArrestVessel()).isEqualTo(DEFAULT_ARREST_VESSEL);
        assertThat(testVesselCase.getJurisdiction()).isEqualTo(DEFAULT_JURISDICTION);
        assertThat(testVesselCase.getApplicableLaw()).isEqualTo(DEFAULT_APPLICABLE_LAW);
        assertThat(testVesselCase.getCloseDate()).isEqualTo(DEFAULT_CLOSE_DATE);
        assertThat(testVesselCase.getCloseHandler()).isEqualTo(DEFAULT_CLOSE_HANDLER);
        assertThat(testVesselCase.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testVesselCase.getSettlementAmount()).isEqualTo(DEFAULT_SETTLEMENT_AMOUNT);
        assertThat(testVesselCase.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
    }

    @Test
    @Transactional
    public void createVesselCaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vesselCaseRepository.findAll().size();

        // Create the VesselCase with an existing ID
        vesselCase.setId(1L);
        VesselCaseDTO vesselCaseDTO = vesselCaseMapper.toDto(vesselCase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVesselCaseMockMvc.perform(post("/api/vessel-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VesselCase in the database
        List<VesselCase> vesselCaseList = vesselCaseRepository.findAll();
        assertThat(vesselCaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumberIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = vesselCaseRepository.findAll().size();
        // set the field null
        vesselCase.setNumberId(null);

        // Create the VesselCase, which fails.
        VesselCaseDTO vesselCaseDTO = vesselCaseMapper.toDto(vesselCase);

        restVesselCaseMockMvc.perform(post("/api/vessel-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselCaseDTO)))
            .andExpect(status().isBadRequest());

        List<VesselCase> vesselCaseList = vesselCaseRepository.findAll();
        assertThat(vesselCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInsuredVesselIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = vesselCaseRepository.findAll().size();
        // set the field null
        vesselCase.setInsuredVesselId(null);

        // Create the VesselCase, which fails.
        VesselCaseDTO vesselCaseDTO = vesselCaseMapper.toDto(vesselCase);

        restVesselCaseMockMvc.perform(post("/api/vessel-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselCaseDTO)))
            .andExpect(status().isBadRequest());

        List<VesselCase> vesselCaseList = vesselCaseRepository.findAll();
        assertThat(vesselCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCaseCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = vesselCaseRepository.findAll().size();
        // set the field null
        vesselCase.setCaseCode(null);

        // Create the VesselCase, which fails.
        VesselCaseDTO vesselCaseDTO = vesselCaseMapper.toDto(vesselCase);

        restVesselCaseMockMvc.perform(post("/api/vessel-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselCaseDTO)))
            .andExpect(status().isBadRequest());

        List<VesselCase> vesselCaseList = vesselCaseRepository.findAll();
        assertThat(vesselCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVesselCases() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList
        restVesselCaseMockMvc.perform(get("/api/vessel-cases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vesselCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].caseYear").value(hasItem(DEFAULT_CASE_YEAR.toString())))
            .andExpect(jsonPath("$.[*].insuredVesselId").value(hasItem(DEFAULT_INSURED_VESSEL_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].vesselName").value(hasItem(DEFAULT_VESSEL_NAME.toString())))
            .andExpect(jsonPath("$.[*].companyChineseName").value(hasItem(DEFAULT_COMPANY_CHINESE_NAME.toString())))
            .andExpect(jsonPath("$.[*].vesselChineseName").value(hasItem(DEFAULT_VESSEL_CHINESE_NAME.toString())))
            .andExpect(jsonPath("$.[*].reinsureId").value(hasItem(DEFAULT_REINSURE_ID.intValue())))
            .andExpect(jsonPath("$.[*].deduct").value(hasItem(DEFAULT_DEDUCT.intValue())))
            .andExpect(jsonPath("$.[*].assignedHandler").value(hasItem(DEFAULT_ASSIGNED_HANDLER.intValue())))
            .andExpect(jsonPath("$.[*].assignedTime").value(hasItem(DEFAULT_ASSIGNED_TIME.toString())))
            .andExpect(jsonPath("$.[*].registeredHandler").value(hasItem(DEFAULT_REGISTERED_HANDLER.intValue())))
            .andExpect(jsonPath("$.[*].registeredDate").value(hasItem(DEFAULT_REGISTERED_DATE.toString())))
            .andExpect(jsonPath("$.[*].caseCode").value(hasItem(DEFAULT_CASE_CODE.toString())))
            .andExpect(jsonPath("$.[*].caseDate").value(hasItem(DEFAULT_CASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].caseLocation").value(hasItem(DEFAULT_CASE_LOCATION.intValue())))
            .andExpect(jsonPath("$.[*].caseDescription").value(hasItem(DEFAULT_CASE_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].voyageNo").value(hasItem(DEFAULT_VOYAGE_NO.toString())))
            .andExpect(jsonPath("$.[*].loadingPort").value(hasItem(DEFAULT_LOADING_PORT.intValue())))
            .andExpect(jsonPath("$.[*].loadingDate").value(hasItem(DEFAULT_LOADING_DATE.toString())))
            .andExpect(jsonPath("$.[*].dischargingPort").value(hasItem(DEFAULT_DISCHARGING_PORT.intValue())))
            .andExpect(jsonPath("$.[*].dischargingDate").value(hasItem(DEFAULT_DISCHARGING_DATE.toString())))
            .andExpect(jsonPath("$.[*].limitTime").value(hasItem(DEFAULT_LIMIT_TIME.toString())))
            .andExpect(jsonPath("$.[*].cpDate").value(hasItem(DEFAULT_CP_DATE.toString())))
            .andExpect(jsonPath("$.[*].cpType").value(hasItem(DEFAULT_CP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].arrestVessel").value(hasItem(DEFAULT_ARREST_VESSEL.toString())))
            .andExpect(jsonPath("$.[*].jurisdiction").value(hasItem(DEFAULT_JURISDICTION.intValue())))
            .andExpect(jsonPath("$.[*].applicableLaw").value(hasItem(DEFAULT_APPLICABLE_LAW.intValue())))
            .andExpect(jsonPath("$.[*].closeDate").value(hasItem(DEFAULT_CLOSE_DATE.toString())))
            .andExpect(jsonPath("$.[*].closeHandler").value(hasItem(DEFAULT_CLOSE_HANDLER.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].settlementAmount").value(hasItem(DEFAULT_SETTLEMENT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].settlementDate").value(hasItem(DEFAULT_SETTLEMENT_DATE.toString())));
    }
    

    @Test
    @Transactional
    public void getVesselCase() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get the vesselCase
        restVesselCaseMockMvc.perform(get("/api/vessel-cases/{id}", vesselCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vesselCase.getId().intValue()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.caseYear").value(DEFAULT_CASE_YEAR.toString()))
            .andExpect(jsonPath("$.insuredVesselId").value(DEFAULT_INSURED_VESSEL_ID.intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.vesselName").value(DEFAULT_VESSEL_NAME.toString()))
            .andExpect(jsonPath("$.companyChineseName").value(DEFAULT_COMPANY_CHINESE_NAME.toString()))
            .andExpect(jsonPath("$.vesselChineseName").value(DEFAULT_VESSEL_CHINESE_NAME.toString()))
            .andExpect(jsonPath("$.reinsureId").value(DEFAULT_REINSURE_ID.intValue()))
            .andExpect(jsonPath("$.deduct").value(DEFAULT_DEDUCT.intValue()))
            .andExpect(jsonPath("$.assignedHandler").value(DEFAULT_ASSIGNED_HANDLER.intValue()))
            .andExpect(jsonPath("$.assignedTime").value(DEFAULT_ASSIGNED_TIME.toString()))
            .andExpect(jsonPath("$.registeredHandler").value(DEFAULT_REGISTERED_HANDLER.intValue()))
            .andExpect(jsonPath("$.registeredDate").value(DEFAULT_REGISTERED_DATE.toString()))
            .andExpect(jsonPath("$.caseCode").value(DEFAULT_CASE_CODE.toString()))
            .andExpect(jsonPath("$.caseDate").value(DEFAULT_CASE_DATE.toString()))
            .andExpect(jsonPath("$.caseLocation").value(DEFAULT_CASE_LOCATION.intValue()))
            .andExpect(jsonPath("$.caseDescription").value(DEFAULT_CASE_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.voyageNo").value(DEFAULT_VOYAGE_NO.toString()))
            .andExpect(jsonPath("$.loadingPort").value(DEFAULT_LOADING_PORT.intValue()))
            .andExpect(jsonPath("$.loadingDate").value(DEFAULT_LOADING_DATE.toString()))
            .andExpect(jsonPath("$.dischargingPort").value(DEFAULT_DISCHARGING_PORT.intValue()))
            .andExpect(jsonPath("$.dischargingDate").value(DEFAULT_DISCHARGING_DATE.toString()))
            .andExpect(jsonPath("$.limitTime").value(DEFAULT_LIMIT_TIME.toString()))
            .andExpect(jsonPath("$.cpDate").value(DEFAULT_CP_DATE.toString()))
            .andExpect(jsonPath("$.cpType").value(DEFAULT_CP_TYPE.toString()))
            .andExpect(jsonPath("$.arrestVessel").value(DEFAULT_ARREST_VESSEL.toString()))
            .andExpect(jsonPath("$.jurisdiction").value(DEFAULT_JURISDICTION.intValue()))
            .andExpect(jsonPath("$.applicableLaw").value(DEFAULT_APPLICABLE_LAW.intValue()))
            .andExpect(jsonPath("$.closeDate").value(DEFAULT_CLOSE_DATE.toString()))
            .andExpect(jsonPath("$.closeHandler").value(DEFAULT_CLOSE_HANDLER.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.settlementAmount").value(DEFAULT_SETTLEMENT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.settlementDate").value(DEFAULT_SETTLEMENT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getAllVesselCasesByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where numberId equals to DEFAULT_NUMBER_ID
        defaultVesselCaseShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the vesselCaseList where numberId equals to UPDATED_NUMBER_ID
        defaultVesselCaseShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultVesselCaseShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the vesselCaseList where numberId equals to UPDATED_NUMBER_ID
        defaultVesselCaseShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where numberId is not null
        defaultVesselCaseShouldBeFound("numberId.specified=true");

        // Get all the vesselCaseList where numberId is null
        defaultVesselCaseShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultVesselCaseShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the vesselCaseList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultVesselCaseShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultVesselCaseShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the vesselCaseList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultVesselCaseShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByCaseYearIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseYear equals to DEFAULT_CASE_YEAR
        defaultVesselCaseShouldBeFound("caseYear.equals=" + DEFAULT_CASE_YEAR);

        // Get all the vesselCaseList where caseYear equals to UPDATED_CASE_YEAR
        defaultVesselCaseShouldNotBeFound("caseYear.equals=" + UPDATED_CASE_YEAR);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseYearIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseYear in DEFAULT_CASE_YEAR or UPDATED_CASE_YEAR
        defaultVesselCaseShouldBeFound("caseYear.in=" + DEFAULT_CASE_YEAR + "," + UPDATED_CASE_YEAR);

        // Get all the vesselCaseList where caseYear equals to UPDATED_CASE_YEAR
        defaultVesselCaseShouldNotBeFound("caseYear.in=" + UPDATED_CASE_YEAR);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseYearIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseYear is not null
        defaultVesselCaseShouldBeFound("caseYear.specified=true");

        // Get all the vesselCaseList where caseYear is null
        defaultVesselCaseShouldNotBeFound("caseYear.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByInsuredVesselIdIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where insuredVesselId equals to DEFAULT_INSURED_VESSEL_ID
        defaultVesselCaseShouldBeFound("insuredVesselId.equals=" + DEFAULT_INSURED_VESSEL_ID);

        // Get all the vesselCaseList where insuredVesselId equals to UPDATED_INSURED_VESSEL_ID
        defaultVesselCaseShouldNotBeFound("insuredVesselId.equals=" + UPDATED_INSURED_VESSEL_ID);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByInsuredVesselIdIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where insuredVesselId in DEFAULT_INSURED_VESSEL_ID or UPDATED_INSURED_VESSEL_ID
        defaultVesselCaseShouldBeFound("insuredVesselId.in=" + DEFAULT_INSURED_VESSEL_ID + "," + UPDATED_INSURED_VESSEL_ID);

        // Get all the vesselCaseList where insuredVesselId equals to UPDATED_INSURED_VESSEL_ID
        defaultVesselCaseShouldNotBeFound("insuredVesselId.in=" + UPDATED_INSURED_VESSEL_ID);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByInsuredVesselIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where insuredVesselId is not null
        defaultVesselCaseShouldBeFound("insuredVesselId.specified=true");

        // Get all the vesselCaseList where insuredVesselId is null
        defaultVesselCaseShouldNotBeFound("insuredVesselId.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByInsuredVesselIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where insuredVesselId greater than or equals to DEFAULT_INSURED_VESSEL_ID
        defaultVesselCaseShouldBeFound("insuredVesselId.greaterOrEqualThan=" + DEFAULT_INSURED_VESSEL_ID);

        // Get all the vesselCaseList where insuredVesselId greater than or equals to UPDATED_INSURED_VESSEL_ID
        defaultVesselCaseShouldNotBeFound("insuredVesselId.greaterOrEqualThan=" + UPDATED_INSURED_VESSEL_ID);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByInsuredVesselIdIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where insuredVesselId less than or equals to DEFAULT_INSURED_VESSEL_ID
        defaultVesselCaseShouldNotBeFound("insuredVesselId.lessThan=" + DEFAULT_INSURED_VESSEL_ID);

        // Get all the vesselCaseList where insuredVesselId less than or equals to UPDATED_INSURED_VESSEL_ID
        defaultVesselCaseShouldBeFound("insuredVesselId.lessThan=" + UPDATED_INSURED_VESSEL_ID);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByCompanyNameIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where companyName equals to DEFAULT_COMPANY_NAME
        defaultVesselCaseShouldBeFound("companyName.equals=" + DEFAULT_COMPANY_NAME);

        // Get all the vesselCaseList where companyName equals to UPDATED_COMPANY_NAME
        defaultVesselCaseShouldNotBeFound("companyName.equals=" + UPDATED_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCompanyNameIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where companyName in DEFAULT_COMPANY_NAME or UPDATED_COMPANY_NAME
        defaultVesselCaseShouldBeFound("companyName.in=" + DEFAULT_COMPANY_NAME + "," + UPDATED_COMPANY_NAME);

        // Get all the vesselCaseList where companyName equals to UPDATED_COMPANY_NAME
        defaultVesselCaseShouldNotBeFound("companyName.in=" + UPDATED_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCompanyNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where companyName is not null
        defaultVesselCaseShouldBeFound("companyName.specified=true");

        // Get all the vesselCaseList where companyName is null
        defaultVesselCaseShouldNotBeFound("companyName.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByVesselNameIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where vesselName equals to DEFAULT_VESSEL_NAME
        defaultVesselCaseShouldBeFound("vesselName.equals=" + DEFAULT_VESSEL_NAME);

        // Get all the vesselCaseList where vesselName equals to UPDATED_VESSEL_NAME
        defaultVesselCaseShouldNotBeFound("vesselName.equals=" + UPDATED_VESSEL_NAME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByVesselNameIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where vesselName in DEFAULT_VESSEL_NAME or UPDATED_VESSEL_NAME
        defaultVesselCaseShouldBeFound("vesselName.in=" + DEFAULT_VESSEL_NAME + "," + UPDATED_VESSEL_NAME);

        // Get all the vesselCaseList where vesselName equals to UPDATED_VESSEL_NAME
        defaultVesselCaseShouldNotBeFound("vesselName.in=" + UPDATED_VESSEL_NAME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByVesselNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where vesselName is not null
        defaultVesselCaseShouldBeFound("vesselName.specified=true");

        // Get all the vesselCaseList where vesselName is null
        defaultVesselCaseShouldNotBeFound("vesselName.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCompanyChineseNameIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where companyChineseName equals to DEFAULT_COMPANY_CHINESE_NAME
        defaultVesselCaseShouldBeFound("companyChineseName.equals=" + DEFAULT_COMPANY_CHINESE_NAME);

        // Get all the vesselCaseList where companyChineseName equals to UPDATED_COMPANY_CHINESE_NAME
        defaultVesselCaseShouldNotBeFound("companyChineseName.equals=" + UPDATED_COMPANY_CHINESE_NAME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCompanyChineseNameIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where companyChineseName in DEFAULT_COMPANY_CHINESE_NAME or UPDATED_COMPANY_CHINESE_NAME
        defaultVesselCaseShouldBeFound("companyChineseName.in=" + DEFAULT_COMPANY_CHINESE_NAME + "," + UPDATED_COMPANY_CHINESE_NAME);

        // Get all the vesselCaseList where companyChineseName equals to UPDATED_COMPANY_CHINESE_NAME
        defaultVesselCaseShouldNotBeFound("companyChineseName.in=" + UPDATED_COMPANY_CHINESE_NAME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCompanyChineseNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where companyChineseName is not null
        defaultVesselCaseShouldBeFound("companyChineseName.specified=true");

        // Get all the vesselCaseList where companyChineseName is null
        defaultVesselCaseShouldNotBeFound("companyChineseName.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByVesselChineseNameIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where vesselChineseName equals to DEFAULT_VESSEL_CHINESE_NAME
        defaultVesselCaseShouldBeFound("vesselChineseName.equals=" + DEFAULT_VESSEL_CHINESE_NAME);

        // Get all the vesselCaseList where vesselChineseName equals to UPDATED_VESSEL_CHINESE_NAME
        defaultVesselCaseShouldNotBeFound("vesselChineseName.equals=" + UPDATED_VESSEL_CHINESE_NAME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByVesselChineseNameIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where vesselChineseName in DEFAULT_VESSEL_CHINESE_NAME or UPDATED_VESSEL_CHINESE_NAME
        defaultVesselCaseShouldBeFound("vesselChineseName.in=" + DEFAULT_VESSEL_CHINESE_NAME + "," + UPDATED_VESSEL_CHINESE_NAME);

        // Get all the vesselCaseList where vesselChineseName equals to UPDATED_VESSEL_CHINESE_NAME
        defaultVesselCaseShouldNotBeFound("vesselChineseName.in=" + UPDATED_VESSEL_CHINESE_NAME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByVesselChineseNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where vesselChineseName is not null
        defaultVesselCaseShouldBeFound("vesselChineseName.specified=true");

        // Get all the vesselCaseList where vesselChineseName is null
        defaultVesselCaseShouldNotBeFound("vesselChineseName.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByReinsureIdIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where reinsureId equals to DEFAULT_REINSURE_ID
        defaultVesselCaseShouldBeFound("reinsureId.equals=" + DEFAULT_REINSURE_ID);

        // Get all the vesselCaseList where reinsureId equals to UPDATED_REINSURE_ID
        defaultVesselCaseShouldNotBeFound("reinsureId.equals=" + UPDATED_REINSURE_ID);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByReinsureIdIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where reinsureId in DEFAULT_REINSURE_ID or UPDATED_REINSURE_ID
        defaultVesselCaseShouldBeFound("reinsureId.in=" + DEFAULT_REINSURE_ID + "," + UPDATED_REINSURE_ID);

        // Get all the vesselCaseList where reinsureId equals to UPDATED_REINSURE_ID
        defaultVesselCaseShouldNotBeFound("reinsureId.in=" + UPDATED_REINSURE_ID);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByReinsureIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where reinsureId is not null
        defaultVesselCaseShouldBeFound("reinsureId.specified=true");

        // Get all the vesselCaseList where reinsureId is null
        defaultVesselCaseShouldNotBeFound("reinsureId.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByReinsureIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where reinsureId greater than or equals to DEFAULT_REINSURE_ID
        defaultVesselCaseShouldBeFound("reinsureId.greaterOrEqualThan=" + DEFAULT_REINSURE_ID);

        // Get all the vesselCaseList where reinsureId greater than or equals to UPDATED_REINSURE_ID
        defaultVesselCaseShouldNotBeFound("reinsureId.greaterOrEqualThan=" + UPDATED_REINSURE_ID);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByReinsureIdIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where reinsureId less than or equals to DEFAULT_REINSURE_ID
        defaultVesselCaseShouldNotBeFound("reinsureId.lessThan=" + DEFAULT_REINSURE_ID);

        // Get all the vesselCaseList where reinsureId less than or equals to UPDATED_REINSURE_ID
        defaultVesselCaseShouldBeFound("reinsureId.lessThan=" + UPDATED_REINSURE_ID);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByDeductIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where deduct equals to DEFAULT_DEDUCT
        defaultVesselCaseShouldBeFound("deduct.equals=" + DEFAULT_DEDUCT);

        // Get all the vesselCaseList where deduct equals to UPDATED_DEDUCT
        defaultVesselCaseShouldNotBeFound("deduct.equals=" + UPDATED_DEDUCT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByDeductIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where deduct in DEFAULT_DEDUCT or UPDATED_DEDUCT
        defaultVesselCaseShouldBeFound("deduct.in=" + DEFAULT_DEDUCT + "," + UPDATED_DEDUCT);

        // Get all the vesselCaseList where deduct equals to UPDATED_DEDUCT
        defaultVesselCaseShouldNotBeFound("deduct.in=" + UPDATED_DEDUCT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByDeductIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where deduct is not null
        defaultVesselCaseShouldBeFound("deduct.specified=true");

        // Get all the vesselCaseList where deduct is null
        defaultVesselCaseShouldNotBeFound("deduct.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByAssignedHandlerIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where assignedHandler equals to DEFAULT_ASSIGNED_HANDLER
        defaultVesselCaseShouldBeFound("assignedHandler.equals=" + DEFAULT_ASSIGNED_HANDLER);

        // Get all the vesselCaseList where assignedHandler equals to UPDATED_ASSIGNED_HANDLER
        defaultVesselCaseShouldNotBeFound("assignedHandler.equals=" + UPDATED_ASSIGNED_HANDLER);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByAssignedHandlerIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where assignedHandler in DEFAULT_ASSIGNED_HANDLER or UPDATED_ASSIGNED_HANDLER
        defaultVesselCaseShouldBeFound("assignedHandler.in=" + DEFAULT_ASSIGNED_HANDLER + "," + UPDATED_ASSIGNED_HANDLER);

        // Get all the vesselCaseList where assignedHandler equals to UPDATED_ASSIGNED_HANDLER
        defaultVesselCaseShouldNotBeFound("assignedHandler.in=" + UPDATED_ASSIGNED_HANDLER);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByAssignedHandlerIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where assignedHandler is not null
        defaultVesselCaseShouldBeFound("assignedHandler.specified=true");

        // Get all the vesselCaseList where assignedHandler is null
        defaultVesselCaseShouldNotBeFound("assignedHandler.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByAssignedHandlerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where assignedHandler greater than or equals to DEFAULT_ASSIGNED_HANDLER
        defaultVesselCaseShouldBeFound("assignedHandler.greaterOrEqualThan=" + DEFAULT_ASSIGNED_HANDLER);

        // Get all the vesselCaseList where assignedHandler greater than or equals to UPDATED_ASSIGNED_HANDLER
        defaultVesselCaseShouldNotBeFound("assignedHandler.greaterOrEqualThan=" + UPDATED_ASSIGNED_HANDLER);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByAssignedHandlerIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where assignedHandler less than or equals to DEFAULT_ASSIGNED_HANDLER
        defaultVesselCaseShouldNotBeFound("assignedHandler.lessThan=" + DEFAULT_ASSIGNED_HANDLER);

        // Get all the vesselCaseList where assignedHandler less than or equals to UPDATED_ASSIGNED_HANDLER
        defaultVesselCaseShouldBeFound("assignedHandler.lessThan=" + UPDATED_ASSIGNED_HANDLER);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByAssignedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where assignedTime equals to DEFAULT_ASSIGNED_TIME
        defaultVesselCaseShouldBeFound("assignedTime.equals=" + DEFAULT_ASSIGNED_TIME);

        // Get all the vesselCaseList where assignedTime equals to UPDATED_ASSIGNED_TIME
        defaultVesselCaseShouldNotBeFound("assignedTime.equals=" + UPDATED_ASSIGNED_TIME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByAssignedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where assignedTime in DEFAULT_ASSIGNED_TIME or UPDATED_ASSIGNED_TIME
        defaultVesselCaseShouldBeFound("assignedTime.in=" + DEFAULT_ASSIGNED_TIME + "," + UPDATED_ASSIGNED_TIME);

        // Get all the vesselCaseList where assignedTime equals to UPDATED_ASSIGNED_TIME
        defaultVesselCaseShouldNotBeFound("assignedTime.in=" + UPDATED_ASSIGNED_TIME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByAssignedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where assignedTime is not null
        defaultVesselCaseShouldBeFound("assignedTime.specified=true");

        // Get all the vesselCaseList where assignedTime is null
        defaultVesselCaseShouldNotBeFound("assignedTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByRegisteredHandlerIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where registeredHandler equals to DEFAULT_REGISTERED_HANDLER
        defaultVesselCaseShouldBeFound("registeredHandler.equals=" + DEFAULT_REGISTERED_HANDLER);

        // Get all the vesselCaseList where registeredHandler equals to UPDATED_REGISTERED_HANDLER
        defaultVesselCaseShouldNotBeFound("registeredHandler.equals=" + UPDATED_REGISTERED_HANDLER);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByRegisteredHandlerIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where registeredHandler in DEFAULT_REGISTERED_HANDLER or UPDATED_REGISTERED_HANDLER
        defaultVesselCaseShouldBeFound("registeredHandler.in=" + DEFAULT_REGISTERED_HANDLER + "," + UPDATED_REGISTERED_HANDLER);

        // Get all the vesselCaseList where registeredHandler equals to UPDATED_REGISTERED_HANDLER
        defaultVesselCaseShouldNotBeFound("registeredHandler.in=" + UPDATED_REGISTERED_HANDLER);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByRegisteredHandlerIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where registeredHandler is not null
        defaultVesselCaseShouldBeFound("registeredHandler.specified=true");

        // Get all the vesselCaseList where registeredHandler is null
        defaultVesselCaseShouldNotBeFound("registeredHandler.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByRegisteredHandlerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where registeredHandler greater than or equals to DEFAULT_REGISTERED_HANDLER
        defaultVesselCaseShouldBeFound("registeredHandler.greaterOrEqualThan=" + DEFAULT_REGISTERED_HANDLER);

        // Get all the vesselCaseList where registeredHandler greater than or equals to UPDATED_REGISTERED_HANDLER
        defaultVesselCaseShouldNotBeFound("registeredHandler.greaterOrEqualThan=" + UPDATED_REGISTERED_HANDLER);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByRegisteredHandlerIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where registeredHandler less than or equals to DEFAULT_REGISTERED_HANDLER
        defaultVesselCaseShouldNotBeFound("registeredHandler.lessThan=" + DEFAULT_REGISTERED_HANDLER);

        // Get all the vesselCaseList where registeredHandler less than or equals to UPDATED_REGISTERED_HANDLER
        defaultVesselCaseShouldBeFound("registeredHandler.lessThan=" + UPDATED_REGISTERED_HANDLER);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByRegisteredDateIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where registeredDate equals to DEFAULT_REGISTERED_DATE
        defaultVesselCaseShouldBeFound("registeredDate.equals=" + DEFAULT_REGISTERED_DATE);

        // Get all the vesselCaseList where registeredDate equals to UPDATED_REGISTERED_DATE
        defaultVesselCaseShouldNotBeFound("registeredDate.equals=" + UPDATED_REGISTERED_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByRegisteredDateIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where registeredDate in DEFAULT_REGISTERED_DATE or UPDATED_REGISTERED_DATE
        defaultVesselCaseShouldBeFound("registeredDate.in=" + DEFAULT_REGISTERED_DATE + "," + UPDATED_REGISTERED_DATE);

        // Get all the vesselCaseList where registeredDate equals to UPDATED_REGISTERED_DATE
        defaultVesselCaseShouldNotBeFound("registeredDate.in=" + UPDATED_REGISTERED_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByRegisteredDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where registeredDate is not null
        defaultVesselCaseShouldBeFound("registeredDate.specified=true");

        // Get all the vesselCaseList where registeredDate is null
        defaultVesselCaseShouldNotBeFound("registeredDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseCode equals to DEFAULT_CASE_CODE
        defaultVesselCaseShouldBeFound("caseCode.equals=" + DEFAULT_CASE_CODE);

        // Get all the vesselCaseList where caseCode equals to UPDATED_CASE_CODE
        defaultVesselCaseShouldNotBeFound("caseCode.equals=" + UPDATED_CASE_CODE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseCodeIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseCode in DEFAULT_CASE_CODE or UPDATED_CASE_CODE
        defaultVesselCaseShouldBeFound("caseCode.in=" + DEFAULT_CASE_CODE + "," + UPDATED_CASE_CODE);

        // Get all the vesselCaseList where caseCode equals to UPDATED_CASE_CODE
        defaultVesselCaseShouldNotBeFound("caseCode.in=" + UPDATED_CASE_CODE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseCode is not null
        defaultVesselCaseShouldBeFound("caseCode.specified=true");

        // Get all the vesselCaseList where caseCode is null
        defaultVesselCaseShouldNotBeFound("caseCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseDateIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseDate equals to DEFAULT_CASE_DATE
        defaultVesselCaseShouldBeFound("caseDate.equals=" + DEFAULT_CASE_DATE);

        // Get all the vesselCaseList where caseDate equals to UPDATED_CASE_DATE
        defaultVesselCaseShouldNotBeFound("caseDate.equals=" + UPDATED_CASE_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseDateIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseDate in DEFAULT_CASE_DATE or UPDATED_CASE_DATE
        defaultVesselCaseShouldBeFound("caseDate.in=" + DEFAULT_CASE_DATE + "," + UPDATED_CASE_DATE);

        // Get all the vesselCaseList where caseDate equals to UPDATED_CASE_DATE
        defaultVesselCaseShouldNotBeFound("caseDate.in=" + UPDATED_CASE_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseDate is not null
        defaultVesselCaseShouldBeFound("caseDate.specified=true");

        // Get all the vesselCaseList where caseDate is null
        defaultVesselCaseShouldNotBeFound("caseDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseLocation equals to DEFAULT_CASE_LOCATION
        defaultVesselCaseShouldBeFound("caseLocation.equals=" + DEFAULT_CASE_LOCATION);

        // Get all the vesselCaseList where caseLocation equals to UPDATED_CASE_LOCATION
        defaultVesselCaseShouldNotBeFound("caseLocation.equals=" + UPDATED_CASE_LOCATION);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseLocationIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseLocation in DEFAULT_CASE_LOCATION or UPDATED_CASE_LOCATION
        defaultVesselCaseShouldBeFound("caseLocation.in=" + DEFAULT_CASE_LOCATION + "," + UPDATED_CASE_LOCATION);

        // Get all the vesselCaseList where caseLocation equals to UPDATED_CASE_LOCATION
        defaultVesselCaseShouldNotBeFound("caseLocation.in=" + UPDATED_CASE_LOCATION);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseLocationIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseLocation is not null
        defaultVesselCaseShouldBeFound("caseLocation.specified=true");

        // Get all the vesselCaseList where caseLocation is null
        defaultVesselCaseShouldNotBeFound("caseLocation.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseLocationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseLocation greater than or equals to DEFAULT_CASE_LOCATION
        defaultVesselCaseShouldBeFound("caseLocation.greaterOrEqualThan=" + DEFAULT_CASE_LOCATION);

        // Get all the vesselCaseList where caseLocation greater than or equals to UPDATED_CASE_LOCATION
        defaultVesselCaseShouldNotBeFound("caseLocation.greaterOrEqualThan=" + UPDATED_CASE_LOCATION);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseLocationIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseLocation less than or equals to DEFAULT_CASE_LOCATION
        defaultVesselCaseShouldNotBeFound("caseLocation.lessThan=" + DEFAULT_CASE_LOCATION);

        // Get all the vesselCaseList where caseLocation less than or equals to UPDATED_CASE_LOCATION
        defaultVesselCaseShouldBeFound("caseLocation.lessThan=" + UPDATED_CASE_LOCATION);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByCaseDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseDescription equals to DEFAULT_CASE_DESCRIPTION
        defaultVesselCaseShouldBeFound("caseDescription.equals=" + DEFAULT_CASE_DESCRIPTION);

        // Get all the vesselCaseList where caseDescription equals to UPDATED_CASE_DESCRIPTION
        defaultVesselCaseShouldNotBeFound("caseDescription.equals=" + UPDATED_CASE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseDescription in DEFAULT_CASE_DESCRIPTION or UPDATED_CASE_DESCRIPTION
        defaultVesselCaseShouldBeFound("caseDescription.in=" + DEFAULT_CASE_DESCRIPTION + "," + UPDATED_CASE_DESCRIPTION);

        // Get all the vesselCaseList where caseDescription equals to UPDATED_CASE_DESCRIPTION
        defaultVesselCaseShouldNotBeFound("caseDescription.in=" + UPDATED_CASE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCaseDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where caseDescription is not null
        defaultVesselCaseShouldBeFound("caseDescription.specified=true");

        // Get all the vesselCaseList where caseDescription is null
        defaultVesselCaseShouldNotBeFound("caseDescription.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByVoyageNoIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where voyageNo equals to DEFAULT_VOYAGE_NO
        defaultVesselCaseShouldBeFound("voyageNo.equals=" + DEFAULT_VOYAGE_NO);

        // Get all the vesselCaseList where voyageNo equals to UPDATED_VOYAGE_NO
        defaultVesselCaseShouldNotBeFound("voyageNo.equals=" + UPDATED_VOYAGE_NO);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByVoyageNoIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where voyageNo in DEFAULT_VOYAGE_NO or UPDATED_VOYAGE_NO
        defaultVesselCaseShouldBeFound("voyageNo.in=" + DEFAULT_VOYAGE_NO + "," + UPDATED_VOYAGE_NO);

        // Get all the vesselCaseList where voyageNo equals to UPDATED_VOYAGE_NO
        defaultVesselCaseShouldNotBeFound("voyageNo.in=" + UPDATED_VOYAGE_NO);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByVoyageNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where voyageNo is not null
        defaultVesselCaseShouldBeFound("voyageNo.specified=true");

        // Get all the vesselCaseList where voyageNo is null
        defaultVesselCaseShouldNotBeFound("voyageNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLoadingPortIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where loadingPort equals to DEFAULT_LOADING_PORT
        defaultVesselCaseShouldBeFound("loadingPort.equals=" + DEFAULT_LOADING_PORT);

        // Get all the vesselCaseList where loadingPort equals to UPDATED_LOADING_PORT
        defaultVesselCaseShouldNotBeFound("loadingPort.equals=" + UPDATED_LOADING_PORT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLoadingPortIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where loadingPort in DEFAULT_LOADING_PORT or UPDATED_LOADING_PORT
        defaultVesselCaseShouldBeFound("loadingPort.in=" + DEFAULT_LOADING_PORT + "," + UPDATED_LOADING_PORT);

        // Get all the vesselCaseList where loadingPort equals to UPDATED_LOADING_PORT
        defaultVesselCaseShouldNotBeFound("loadingPort.in=" + UPDATED_LOADING_PORT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLoadingPortIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where loadingPort is not null
        defaultVesselCaseShouldBeFound("loadingPort.specified=true");

        // Get all the vesselCaseList where loadingPort is null
        defaultVesselCaseShouldNotBeFound("loadingPort.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLoadingPortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where loadingPort greater than or equals to DEFAULT_LOADING_PORT
        defaultVesselCaseShouldBeFound("loadingPort.greaterOrEqualThan=" + DEFAULT_LOADING_PORT);

        // Get all the vesselCaseList where loadingPort greater than or equals to UPDATED_LOADING_PORT
        defaultVesselCaseShouldNotBeFound("loadingPort.greaterOrEqualThan=" + UPDATED_LOADING_PORT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLoadingPortIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where loadingPort less than or equals to DEFAULT_LOADING_PORT
        defaultVesselCaseShouldNotBeFound("loadingPort.lessThan=" + DEFAULT_LOADING_PORT);

        // Get all the vesselCaseList where loadingPort less than or equals to UPDATED_LOADING_PORT
        defaultVesselCaseShouldBeFound("loadingPort.lessThan=" + UPDATED_LOADING_PORT);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByLoadingDateIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where loadingDate equals to DEFAULT_LOADING_DATE
        defaultVesselCaseShouldBeFound("loadingDate.equals=" + DEFAULT_LOADING_DATE);

        // Get all the vesselCaseList where loadingDate equals to UPDATED_LOADING_DATE
        defaultVesselCaseShouldNotBeFound("loadingDate.equals=" + UPDATED_LOADING_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLoadingDateIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where loadingDate in DEFAULT_LOADING_DATE or UPDATED_LOADING_DATE
        defaultVesselCaseShouldBeFound("loadingDate.in=" + DEFAULT_LOADING_DATE + "," + UPDATED_LOADING_DATE);

        // Get all the vesselCaseList where loadingDate equals to UPDATED_LOADING_DATE
        defaultVesselCaseShouldNotBeFound("loadingDate.in=" + UPDATED_LOADING_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLoadingDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where loadingDate is not null
        defaultVesselCaseShouldBeFound("loadingDate.specified=true");

        // Get all the vesselCaseList where loadingDate is null
        defaultVesselCaseShouldNotBeFound("loadingDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByDischargingPortIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where dischargingPort equals to DEFAULT_DISCHARGING_PORT
        defaultVesselCaseShouldBeFound("dischargingPort.equals=" + DEFAULT_DISCHARGING_PORT);

        // Get all the vesselCaseList where dischargingPort equals to UPDATED_DISCHARGING_PORT
        defaultVesselCaseShouldNotBeFound("dischargingPort.equals=" + UPDATED_DISCHARGING_PORT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByDischargingPortIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where dischargingPort in DEFAULT_DISCHARGING_PORT or UPDATED_DISCHARGING_PORT
        defaultVesselCaseShouldBeFound("dischargingPort.in=" + DEFAULT_DISCHARGING_PORT + "," + UPDATED_DISCHARGING_PORT);

        // Get all the vesselCaseList where dischargingPort equals to UPDATED_DISCHARGING_PORT
        defaultVesselCaseShouldNotBeFound("dischargingPort.in=" + UPDATED_DISCHARGING_PORT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByDischargingPortIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where dischargingPort is not null
        defaultVesselCaseShouldBeFound("dischargingPort.specified=true");

        // Get all the vesselCaseList where dischargingPort is null
        defaultVesselCaseShouldNotBeFound("dischargingPort.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByDischargingPortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where dischargingPort greater than or equals to DEFAULT_DISCHARGING_PORT
        defaultVesselCaseShouldBeFound("dischargingPort.greaterOrEqualThan=" + DEFAULT_DISCHARGING_PORT);

        // Get all the vesselCaseList where dischargingPort greater than or equals to UPDATED_DISCHARGING_PORT
        defaultVesselCaseShouldNotBeFound("dischargingPort.greaterOrEqualThan=" + UPDATED_DISCHARGING_PORT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByDischargingPortIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where dischargingPort less than or equals to DEFAULT_DISCHARGING_PORT
        defaultVesselCaseShouldNotBeFound("dischargingPort.lessThan=" + DEFAULT_DISCHARGING_PORT);

        // Get all the vesselCaseList where dischargingPort less than or equals to UPDATED_DISCHARGING_PORT
        defaultVesselCaseShouldBeFound("dischargingPort.lessThan=" + UPDATED_DISCHARGING_PORT);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByDischargingDateIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where dischargingDate equals to DEFAULT_DISCHARGING_DATE
        defaultVesselCaseShouldBeFound("dischargingDate.equals=" + DEFAULT_DISCHARGING_DATE);

        // Get all the vesselCaseList where dischargingDate equals to UPDATED_DISCHARGING_DATE
        defaultVesselCaseShouldNotBeFound("dischargingDate.equals=" + UPDATED_DISCHARGING_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByDischargingDateIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where dischargingDate in DEFAULT_DISCHARGING_DATE or UPDATED_DISCHARGING_DATE
        defaultVesselCaseShouldBeFound("dischargingDate.in=" + DEFAULT_DISCHARGING_DATE + "," + UPDATED_DISCHARGING_DATE);

        // Get all the vesselCaseList where dischargingDate equals to UPDATED_DISCHARGING_DATE
        defaultVesselCaseShouldNotBeFound("dischargingDate.in=" + UPDATED_DISCHARGING_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByDischargingDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where dischargingDate is not null
        defaultVesselCaseShouldBeFound("dischargingDate.specified=true");

        // Get all the vesselCaseList where dischargingDate is null
        defaultVesselCaseShouldNotBeFound("dischargingDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLimitTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where limitTime equals to DEFAULT_LIMIT_TIME
        defaultVesselCaseShouldBeFound("limitTime.equals=" + DEFAULT_LIMIT_TIME);

        // Get all the vesselCaseList where limitTime equals to UPDATED_LIMIT_TIME
        defaultVesselCaseShouldNotBeFound("limitTime.equals=" + UPDATED_LIMIT_TIME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLimitTimeIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where limitTime in DEFAULT_LIMIT_TIME or UPDATED_LIMIT_TIME
        defaultVesselCaseShouldBeFound("limitTime.in=" + DEFAULT_LIMIT_TIME + "," + UPDATED_LIMIT_TIME);

        // Get all the vesselCaseList where limitTime equals to UPDATED_LIMIT_TIME
        defaultVesselCaseShouldNotBeFound("limitTime.in=" + UPDATED_LIMIT_TIME);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByLimitTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where limitTime is not null
        defaultVesselCaseShouldBeFound("limitTime.specified=true");

        // Get all the vesselCaseList where limitTime is null
        defaultVesselCaseShouldNotBeFound("limitTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCpDateIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where cpDate equals to DEFAULT_CP_DATE
        defaultVesselCaseShouldBeFound("cpDate.equals=" + DEFAULT_CP_DATE);

        // Get all the vesselCaseList where cpDate equals to UPDATED_CP_DATE
        defaultVesselCaseShouldNotBeFound("cpDate.equals=" + UPDATED_CP_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCpDateIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where cpDate in DEFAULT_CP_DATE or UPDATED_CP_DATE
        defaultVesselCaseShouldBeFound("cpDate.in=" + DEFAULT_CP_DATE + "," + UPDATED_CP_DATE);

        // Get all the vesselCaseList where cpDate equals to UPDATED_CP_DATE
        defaultVesselCaseShouldNotBeFound("cpDate.in=" + UPDATED_CP_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCpDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where cpDate is not null
        defaultVesselCaseShouldBeFound("cpDate.specified=true");

        // Get all the vesselCaseList where cpDate is null
        defaultVesselCaseShouldNotBeFound("cpDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCpTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where cpType equals to DEFAULT_CP_TYPE
        defaultVesselCaseShouldBeFound("cpType.equals=" + DEFAULT_CP_TYPE);

        // Get all the vesselCaseList where cpType equals to UPDATED_CP_TYPE
        defaultVesselCaseShouldNotBeFound("cpType.equals=" + UPDATED_CP_TYPE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCpTypeIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where cpType in DEFAULT_CP_TYPE or UPDATED_CP_TYPE
        defaultVesselCaseShouldBeFound("cpType.in=" + DEFAULT_CP_TYPE + "," + UPDATED_CP_TYPE);

        // Get all the vesselCaseList where cpType equals to UPDATED_CP_TYPE
        defaultVesselCaseShouldNotBeFound("cpType.in=" + UPDATED_CP_TYPE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCpTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where cpType is not null
        defaultVesselCaseShouldBeFound("cpType.specified=true");

        // Get all the vesselCaseList where cpType is null
        defaultVesselCaseShouldNotBeFound("cpType.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByArrestVesselIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where arrestVessel equals to DEFAULT_ARREST_VESSEL
        defaultVesselCaseShouldBeFound("arrestVessel.equals=" + DEFAULT_ARREST_VESSEL);

        // Get all the vesselCaseList where arrestVessel equals to UPDATED_ARREST_VESSEL
        defaultVesselCaseShouldNotBeFound("arrestVessel.equals=" + UPDATED_ARREST_VESSEL);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByArrestVesselIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where arrestVessel in DEFAULT_ARREST_VESSEL or UPDATED_ARREST_VESSEL
        defaultVesselCaseShouldBeFound("arrestVessel.in=" + DEFAULT_ARREST_VESSEL + "," + UPDATED_ARREST_VESSEL);

        // Get all the vesselCaseList where arrestVessel equals to UPDATED_ARREST_VESSEL
        defaultVesselCaseShouldNotBeFound("arrestVessel.in=" + UPDATED_ARREST_VESSEL);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByArrestVesselIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where arrestVessel is not null
        defaultVesselCaseShouldBeFound("arrestVessel.specified=true");

        // Get all the vesselCaseList where arrestVessel is null
        defaultVesselCaseShouldNotBeFound("arrestVessel.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByJurisdictionIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where jurisdiction equals to DEFAULT_JURISDICTION
        defaultVesselCaseShouldBeFound("jurisdiction.equals=" + DEFAULT_JURISDICTION);

        // Get all the vesselCaseList where jurisdiction equals to UPDATED_JURISDICTION
        defaultVesselCaseShouldNotBeFound("jurisdiction.equals=" + UPDATED_JURISDICTION);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByJurisdictionIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where jurisdiction in DEFAULT_JURISDICTION or UPDATED_JURISDICTION
        defaultVesselCaseShouldBeFound("jurisdiction.in=" + DEFAULT_JURISDICTION + "," + UPDATED_JURISDICTION);

        // Get all the vesselCaseList where jurisdiction equals to UPDATED_JURISDICTION
        defaultVesselCaseShouldNotBeFound("jurisdiction.in=" + UPDATED_JURISDICTION);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByJurisdictionIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where jurisdiction is not null
        defaultVesselCaseShouldBeFound("jurisdiction.specified=true");

        // Get all the vesselCaseList where jurisdiction is null
        defaultVesselCaseShouldNotBeFound("jurisdiction.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByJurisdictionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where jurisdiction greater than or equals to DEFAULT_JURISDICTION
        defaultVesselCaseShouldBeFound("jurisdiction.greaterOrEqualThan=" + DEFAULT_JURISDICTION);

        // Get all the vesselCaseList where jurisdiction greater than or equals to UPDATED_JURISDICTION
        defaultVesselCaseShouldNotBeFound("jurisdiction.greaterOrEqualThan=" + UPDATED_JURISDICTION);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByJurisdictionIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where jurisdiction less than or equals to DEFAULT_JURISDICTION
        defaultVesselCaseShouldNotBeFound("jurisdiction.lessThan=" + DEFAULT_JURISDICTION);

        // Get all the vesselCaseList where jurisdiction less than or equals to UPDATED_JURISDICTION
        defaultVesselCaseShouldBeFound("jurisdiction.lessThan=" + UPDATED_JURISDICTION);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByApplicableLawIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where applicableLaw equals to DEFAULT_APPLICABLE_LAW
        defaultVesselCaseShouldBeFound("applicableLaw.equals=" + DEFAULT_APPLICABLE_LAW);

        // Get all the vesselCaseList where applicableLaw equals to UPDATED_APPLICABLE_LAW
        defaultVesselCaseShouldNotBeFound("applicableLaw.equals=" + UPDATED_APPLICABLE_LAW);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByApplicableLawIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where applicableLaw in DEFAULT_APPLICABLE_LAW or UPDATED_APPLICABLE_LAW
        defaultVesselCaseShouldBeFound("applicableLaw.in=" + DEFAULT_APPLICABLE_LAW + "," + UPDATED_APPLICABLE_LAW);

        // Get all the vesselCaseList where applicableLaw equals to UPDATED_APPLICABLE_LAW
        defaultVesselCaseShouldNotBeFound("applicableLaw.in=" + UPDATED_APPLICABLE_LAW);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByApplicableLawIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where applicableLaw is not null
        defaultVesselCaseShouldBeFound("applicableLaw.specified=true");

        // Get all the vesselCaseList where applicableLaw is null
        defaultVesselCaseShouldNotBeFound("applicableLaw.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByApplicableLawIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where applicableLaw greater than or equals to DEFAULT_APPLICABLE_LAW
        defaultVesselCaseShouldBeFound("applicableLaw.greaterOrEqualThan=" + DEFAULT_APPLICABLE_LAW);

        // Get all the vesselCaseList where applicableLaw greater than or equals to UPDATED_APPLICABLE_LAW
        defaultVesselCaseShouldNotBeFound("applicableLaw.greaterOrEqualThan=" + UPDATED_APPLICABLE_LAW);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByApplicableLawIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where applicableLaw less than or equals to DEFAULT_APPLICABLE_LAW
        defaultVesselCaseShouldNotBeFound("applicableLaw.lessThan=" + DEFAULT_APPLICABLE_LAW);

        // Get all the vesselCaseList where applicableLaw less than or equals to UPDATED_APPLICABLE_LAW
        defaultVesselCaseShouldBeFound("applicableLaw.lessThan=" + UPDATED_APPLICABLE_LAW);
    }


    @Test
    @Transactional
    public void getAllVesselCasesByCloseDateIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where closeDate equals to DEFAULT_CLOSE_DATE
        defaultVesselCaseShouldBeFound("closeDate.equals=" + DEFAULT_CLOSE_DATE);

        // Get all the vesselCaseList where closeDate equals to UPDATED_CLOSE_DATE
        defaultVesselCaseShouldNotBeFound("closeDate.equals=" + UPDATED_CLOSE_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCloseDateIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where closeDate in DEFAULT_CLOSE_DATE or UPDATED_CLOSE_DATE
        defaultVesselCaseShouldBeFound("closeDate.in=" + DEFAULT_CLOSE_DATE + "," + UPDATED_CLOSE_DATE);

        // Get all the vesselCaseList where closeDate equals to UPDATED_CLOSE_DATE
        defaultVesselCaseShouldNotBeFound("closeDate.in=" + UPDATED_CLOSE_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCloseDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where closeDate is not null
        defaultVesselCaseShouldBeFound("closeDate.specified=true");

        // Get all the vesselCaseList where closeDate is null
        defaultVesselCaseShouldNotBeFound("closeDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCloseHandlerIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where closeHandler equals to DEFAULT_CLOSE_HANDLER
        defaultVesselCaseShouldBeFound("closeHandler.equals=" + DEFAULT_CLOSE_HANDLER);

        // Get all the vesselCaseList where closeHandler equals to UPDATED_CLOSE_HANDLER
        defaultVesselCaseShouldNotBeFound("closeHandler.equals=" + UPDATED_CLOSE_HANDLER);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCloseHandlerIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where closeHandler in DEFAULT_CLOSE_HANDLER or UPDATED_CLOSE_HANDLER
        defaultVesselCaseShouldBeFound("closeHandler.in=" + DEFAULT_CLOSE_HANDLER + "," + UPDATED_CLOSE_HANDLER);

        // Get all the vesselCaseList where closeHandler equals to UPDATED_CLOSE_HANDLER
        defaultVesselCaseShouldNotBeFound("closeHandler.in=" + UPDATED_CLOSE_HANDLER);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCloseHandlerIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where closeHandler is not null
        defaultVesselCaseShouldBeFound("closeHandler.specified=true");

        // Get all the vesselCaseList where closeHandler is null
        defaultVesselCaseShouldNotBeFound("closeHandler.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCloseHandlerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where closeHandler greater than or equals to DEFAULT_CLOSE_HANDLER
        defaultVesselCaseShouldBeFound("closeHandler.greaterOrEqualThan=" + DEFAULT_CLOSE_HANDLER);

        // Get all the vesselCaseList where closeHandler greater than or equals to UPDATED_CLOSE_HANDLER
        defaultVesselCaseShouldNotBeFound("closeHandler.greaterOrEqualThan=" + UPDATED_CLOSE_HANDLER);
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCloseHandlerIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where closeHandler less than or equals to DEFAULT_CLOSE_HANDLER
        defaultVesselCaseShouldNotBeFound("closeHandler.lessThan=" + DEFAULT_CLOSE_HANDLER);

        // Get all the vesselCaseList where closeHandler less than or equals to UPDATED_CLOSE_HANDLER
        defaultVesselCaseShouldBeFound("closeHandler.lessThan=" + UPDATED_CLOSE_HANDLER);
    }


    @Test
    @Transactional
    public void getAllVesselCasesBySettlementAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where settlementAmount equals to DEFAULT_SETTLEMENT_AMOUNT
        defaultVesselCaseShouldBeFound("settlementAmount.equals=" + DEFAULT_SETTLEMENT_AMOUNT);

        // Get all the vesselCaseList where settlementAmount equals to UPDATED_SETTLEMENT_AMOUNT
        defaultVesselCaseShouldNotBeFound("settlementAmount.equals=" + UPDATED_SETTLEMENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesBySettlementAmountIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where settlementAmount in DEFAULT_SETTLEMENT_AMOUNT or UPDATED_SETTLEMENT_AMOUNT
        defaultVesselCaseShouldBeFound("settlementAmount.in=" + DEFAULT_SETTLEMENT_AMOUNT + "," + UPDATED_SETTLEMENT_AMOUNT);

        // Get all the vesselCaseList where settlementAmount equals to UPDATED_SETTLEMENT_AMOUNT
        defaultVesselCaseShouldNotBeFound("settlementAmount.in=" + UPDATED_SETTLEMENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllVesselCasesBySettlementAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where settlementAmount is not null
        defaultVesselCaseShouldBeFound("settlementAmount.specified=true");

        // Get all the vesselCaseList where settlementAmount is null
        defaultVesselCaseShouldNotBeFound("settlementAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesBySettlementDateIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where settlementDate equals to DEFAULT_SETTLEMENT_DATE
        defaultVesselCaseShouldBeFound("settlementDate.equals=" + DEFAULT_SETTLEMENT_DATE);

        // Get all the vesselCaseList where settlementDate equals to UPDATED_SETTLEMENT_DATE
        defaultVesselCaseShouldNotBeFound("settlementDate.equals=" + UPDATED_SETTLEMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesBySettlementDateIsInShouldWork() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where settlementDate in DEFAULT_SETTLEMENT_DATE or UPDATED_SETTLEMENT_DATE
        defaultVesselCaseShouldBeFound("settlementDate.in=" + DEFAULT_SETTLEMENT_DATE + "," + UPDATED_SETTLEMENT_DATE);

        // Get all the vesselCaseList where settlementDate equals to UPDATED_SETTLEMENT_DATE
        defaultVesselCaseShouldNotBeFound("settlementDate.in=" + UPDATED_SETTLEMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllVesselCasesBySettlementDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        // Get all the vesselCaseList where settlementDate is not null
        defaultVesselCaseShouldBeFound("settlementDate.specified=true");

        // Get all the vesselCaseList where settlementDate is null
        defaultVesselCaseShouldNotBeFound("settlementDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselCasesByCpiInsuranceTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        CpiInsuranceType cpiInsuranceType = CpiInsuranceTypeResourceIntTest.createEntity(em);
        em.persist(cpiInsuranceType);
        em.flush();
        vesselCase.setCpiInsuranceType(cpiInsuranceType);
        vesselCaseRepository.saveAndFlush(vesselCase);
        Long cpiInsuranceTypeId = cpiInsuranceType.getId();

        // Get all the vesselCaseList where cpiInsuranceType equals to cpiInsuranceTypeId
        defaultVesselCaseShouldBeFound("cpiInsuranceTypeId.equals=" + cpiInsuranceTypeId);

        // Get all the vesselCaseList where cpiInsuranceType equals to cpiInsuranceTypeId + 1
        defaultVesselCaseShouldNotBeFound("cpiInsuranceTypeId.equals=" + (cpiInsuranceTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllVesselCasesByCaseStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        CaseStatusType caseStatus = CaseStatusTypeResourceIntTest.createEntity(em);
        em.persist(caseStatus);
        em.flush();
        vesselCase.setCaseStatus(caseStatus);
        vesselCaseRepository.saveAndFlush(vesselCase);
        Long caseStatusId = caseStatus.getId();

        // Get all the vesselCaseList where caseStatus equals to caseStatusId
        defaultVesselCaseShouldBeFound("caseStatusId.equals=" + caseStatusId);

        // Get all the vesselCaseList where caseStatus equals to caseStatusId + 1
        defaultVesselCaseShouldNotBeFound("caseStatusId.equals=" + (caseStatusId + 1));
    }


    @Test
    @Transactional
    public void getAllVesselCasesBySettlementModeIsEqualToSomething() throws Exception {
        // Initialize the database
        CaseSettlementMode settlementMode = CaseSettlementModeResourceIntTest.createEntity(em);
        em.persist(settlementMode);
        em.flush();
        vesselCase.setSettlementMode(settlementMode);
        vesselCaseRepository.saveAndFlush(vesselCase);
        Long settlementModeId = settlementMode.getId();

        // Get all the vesselCaseList where settlementMode equals to settlementModeId
        defaultVesselCaseShouldBeFound("settlementModeId.equals=" + settlementModeId);

        // Get all the vesselCaseList where settlementMode equals to settlementModeId + 1
        defaultVesselCaseShouldNotBeFound("settlementModeId.equals=" + (settlementModeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultVesselCaseShouldBeFound(String filter) throws Exception {
        restVesselCaseMockMvc.perform(get("/api/vessel-cases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vesselCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].caseYear").value(hasItem(DEFAULT_CASE_YEAR.toString())))
            .andExpect(jsonPath("$.[*].insuredVesselId").value(hasItem(DEFAULT_INSURED_VESSEL_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].vesselName").value(hasItem(DEFAULT_VESSEL_NAME.toString())))
            .andExpect(jsonPath("$.[*].companyChineseName").value(hasItem(DEFAULT_COMPANY_CHINESE_NAME.toString())))
            .andExpect(jsonPath("$.[*].vesselChineseName").value(hasItem(DEFAULT_VESSEL_CHINESE_NAME.toString())))
            .andExpect(jsonPath("$.[*].reinsureId").value(hasItem(DEFAULT_REINSURE_ID.intValue())))
            .andExpect(jsonPath("$.[*].deduct").value(hasItem(DEFAULT_DEDUCT.intValue())))
            .andExpect(jsonPath("$.[*].assignedHandler").value(hasItem(DEFAULT_ASSIGNED_HANDLER.intValue())))
            .andExpect(jsonPath("$.[*].assignedTime").value(hasItem(DEFAULT_ASSIGNED_TIME.toString())))
            .andExpect(jsonPath("$.[*].registeredHandler").value(hasItem(DEFAULT_REGISTERED_HANDLER.intValue())))
            .andExpect(jsonPath("$.[*].registeredDate").value(hasItem(DEFAULT_REGISTERED_DATE.toString())))
            .andExpect(jsonPath("$.[*].caseCode").value(hasItem(DEFAULT_CASE_CODE.toString())))
            .andExpect(jsonPath("$.[*].caseDate").value(hasItem(DEFAULT_CASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].caseLocation").value(hasItem(DEFAULT_CASE_LOCATION.intValue())))
            .andExpect(jsonPath("$.[*].caseDescription").value(hasItem(DEFAULT_CASE_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].voyageNo").value(hasItem(DEFAULT_VOYAGE_NO.toString())))
            .andExpect(jsonPath("$.[*].loadingPort").value(hasItem(DEFAULT_LOADING_PORT.intValue())))
            .andExpect(jsonPath("$.[*].loadingDate").value(hasItem(DEFAULT_LOADING_DATE.toString())))
            .andExpect(jsonPath("$.[*].dischargingPort").value(hasItem(DEFAULT_DISCHARGING_PORT.intValue())))
            .andExpect(jsonPath("$.[*].dischargingDate").value(hasItem(DEFAULT_DISCHARGING_DATE.toString())))
            .andExpect(jsonPath("$.[*].limitTime").value(hasItem(DEFAULT_LIMIT_TIME.toString())))
            .andExpect(jsonPath("$.[*].cpDate").value(hasItem(DEFAULT_CP_DATE.toString())))
            .andExpect(jsonPath("$.[*].cpType").value(hasItem(DEFAULT_CP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].arrestVessel").value(hasItem(DEFAULT_ARREST_VESSEL.toString())))
            .andExpect(jsonPath("$.[*].jurisdiction").value(hasItem(DEFAULT_JURISDICTION.intValue())))
            .andExpect(jsonPath("$.[*].applicableLaw").value(hasItem(DEFAULT_APPLICABLE_LAW.intValue())))
            .andExpect(jsonPath("$.[*].closeDate").value(hasItem(DEFAULT_CLOSE_DATE.toString())))
            .andExpect(jsonPath("$.[*].closeHandler").value(hasItem(DEFAULT_CLOSE_HANDLER.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].settlementAmount").value(hasItem(DEFAULT_SETTLEMENT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].settlementDate").value(hasItem(DEFAULT_SETTLEMENT_DATE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultVesselCaseShouldNotBeFound(String filter) throws Exception {
        restVesselCaseMockMvc.perform(get("/api/vessel-cases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingVesselCase() throws Exception {
        // Get the vesselCase
        restVesselCaseMockMvc.perform(get("/api/vessel-cases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVesselCase() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        int databaseSizeBeforeUpdate = vesselCaseRepository.findAll().size();

        // Update the vesselCase
        VesselCase updatedVesselCase = vesselCaseRepository.findById(vesselCase.getId()).get();
        // Disconnect from session so that the updates on updatedVesselCase are not directly saved in db
        em.detach(updatedVesselCase);
        updatedVesselCase
            .numberId(UPDATED_NUMBER_ID)
            .caseYear(UPDATED_CASE_YEAR)
            .insuredVesselId(UPDATED_INSURED_VESSEL_ID)
            .companyName(UPDATED_COMPANY_NAME)
            .vesselName(UPDATED_VESSEL_NAME)
            .companyChineseName(UPDATED_COMPANY_CHINESE_NAME)
            .vesselChineseName(UPDATED_VESSEL_CHINESE_NAME)
            .reinsureId(UPDATED_REINSURE_ID)
            .deduct(UPDATED_DEDUCT)
            .assignedHandler(UPDATED_ASSIGNED_HANDLER)
            .assignedTime(UPDATED_ASSIGNED_TIME)
            .registeredHandler(UPDATED_REGISTERED_HANDLER)
            .registeredDate(UPDATED_REGISTERED_DATE)
            .caseCode(UPDATED_CASE_CODE)
            .caseDate(UPDATED_CASE_DATE)
            .caseLocation(UPDATED_CASE_LOCATION)
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .voyageNo(UPDATED_VOYAGE_NO)
            .loadingPort(UPDATED_LOADING_PORT)
            .loadingDate(UPDATED_LOADING_DATE)
            .dischargingPort(UPDATED_DISCHARGING_PORT)
            .dischargingDate(UPDATED_DISCHARGING_DATE)
            .limitTime(UPDATED_LIMIT_TIME)
            .cpDate(UPDATED_CP_DATE)
            .cpType(UPDATED_CP_TYPE)
            .arrestVessel(UPDATED_ARREST_VESSEL)
            .jurisdiction(UPDATED_JURISDICTION)
            .applicableLaw(UPDATED_APPLICABLE_LAW)
            .closeDate(UPDATED_CLOSE_DATE)
            .closeHandler(UPDATED_CLOSE_HANDLER)
            .remark(UPDATED_REMARK)
            .settlementAmount(UPDATED_SETTLEMENT_AMOUNT)
            .settlementDate(UPDATED_SETTLEMENT_DATE);
        VesselCaseDTO vesselCaseDTO = vesselCaseMapper.toDto(updatedVesselCase);

        restVesselCaseMockMvc.perform(put("/api/vessel-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselCaseDTO)))
            .andExpect(status().isOk());

        // Validate the VesselCase in the database
        List<VesselCase> vesselCaseList = vesselCaseRepository.findAll();
        assertThat(vesselCaseList).hasSize(databaseSizeBeforeUpdate);
        VesselCase testVesselCase = vesselCaseList.get(vesselCaseList.size() - 1);
        assertThat(testVesselCase.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testVesselCase.getCaseYear()).isEqualTo(UPDATED_CASE_YEAR);
        assertThat(testVesselCase.getInsuredVesselId()).isEqualTo(UPDATED_INSURED_VESSEL_ID);
        assertThat(testVesselCase.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testVesselCase.getVesselName()).isEqualTo(UPDATED_VESSEL_NAME);
        assertThat(testVesselCase.getCompanyChineseName()).isEqualTo(UPDATED_COMPANY_CHINESE_NAME);
        assertThat(testVesselCase.getVesselChineseName()).isEqualTo(UPDATED_VESSEL_CHINESE_NAME);
        assertThat(testVesselCase.getReinsureId()).isEqualTo(UPDATED_REINSURE_ID);
        assertThat(testVesselCase.getDeduct()).isEqualTo(UPDATED_DEDUCT);
        assertThat(testVesselCase.getAssignedHandler()).isEqualTo(UPDATED_ASSIGNED_HANDLER);
        assertThat(testVesselCase.getAssignedTime()).isEqualTo(UPDATED_ASSIGNED_TIME);
        assertThat(testVesselCase.getRegisteredHandler()).isEqualTo(UPDATED_REGISTERED_HANDLER);
        assertThat(testVesselCase.getRegisteredDate()).isEqualTo(UPDATED_REGISTERED_DATE);
        assertThat(testVesselCase.getCaseCode()).isEqualTo(UPDATED_CASE_CODE);
        assertThat(testVesselCase.getCaseDate()).isEqualTo(UPDATED_CASE_DATE);
        assertThat(testVesselCase.getCaseLocation()).isEqualTo(UPDATED_CASE_LOCATION);
        assertThat(testVesselCase.getCaseDescription()).isEqualTo(UPDATED_CASE_DESCRIPTION);
        assertThat(testVesselCase.getVoyageNo()).isEqualTo(UPDATED_VOYAGE_NO);
        assertThat(testVesselCase.getLoadingPort()).isEqualTo(UPDATED_LOADING_PORT);
        assertThat(testVesselCase.getLoadingDate()).isEqualTo(UPDATED_LOADING_DATE);
        assertThat(testVesselCase.getDischargingPort()).isEqualTo(UPDATED_DISCHARGING_PORT);
        assertThat(testVesselCase.getDischargingDate()).isEqualTo(UPDATED_DISCHARGING_DATE);
        assertThat(testVesselCase.getLimitTime()).isEqualTo(UPDATED_LIMIT_TIME);
        assertThat(testVesselCase.getCpDate()).isEqualTo(UPDATED_CP_DATE);
        assertThat(testVesselCase.getCpType()).isEqualTo(UPDATED_CP_TYPE);
        assertThat(testVesselCase.getArrestVessel()).isEqualTo(UPDATED_ARREST_VESSEL);
        assertThat(testVesselCase.getJurisdiction()).isEqualTo(UPDATED_JURISDICTION);
        assertThat(testVesselCase.getApplicableLaw()).isEqualTo(UPDATED_APPLICABLE_LAW);
        assertThat(testVesselCase.getCloseDate()).isEqualTo(UPDATED_CLOSE_DATE);
        assertThat(testVesselCase.getCloseHandler()).isEqualTo(UPDATED_CLOSE_HANDLER);
        assertThat(testVesselCase.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testVesselCase.getSettlementAmount()).isEqualTo(UPDATED_SETTLEMENT_AMOUNT);
        assertThat(testVesselCase.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingVesselCase() throws Exception {
        int databaseSizeBeforeUpdate = vesselCaseRepository.findAll().size();

        // Create the VesselCase
        VesselCaseDTO vesselCaseDTO = vesselCaseMapper.toDto(vesselCase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restVesselCaseMockMvc.perform(put("/api/vessel-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VesselCase in the database
        List<VesselCase> vesselCaseList = vesselCaseRepository.findAll();
        assertThat(vesselCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVesselCase() throws Exception {
        // Initialize the database
        vesselCaseRepository.saveAndFlush(vesselCase);

        int databaseSizeBeforeDelete = vesselCaseRepository.findAll().size();

        // Get the vesselCase
        restVesselCaseMockMvc.perform(delete("/api/vessel-cases/{id}", vesselCase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VesselCase> vesselCaseList = vesselCaseRepository.findAll();
        assertThat(vesselCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VesselCase.class);
        VesselCase vesselCase1 = new VesselCase();
        vesselCase1.setId(1L);
        VesselCase vesselCase2 = new VesselCase();
        vesselCase2.setId(vesselCase1.getId());
        assertThat(vesselCase1).isEqualTo(vesselCase2);
        vesselCase2.setId(2L);
        assertThat(vesselCase1).isNotEqualTo(vesselCase2);
        vesselCase1.setId(null);
        assertThat(vesselCase1).isNotEqualTo(vesselCase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VesselCaseDTO.class);
        VesselCaseDTO vesselCaseDTO1 = new VesselCaseDTO();
        vesselCaseDTO1.setId(1L);
        VesselCaseDTO vesselCaseDTO2 = new VesselCaseDTO();
        assertThat(vesselCaseDTO1).isNotEqualTo(vesselCaseDTO2);
        vesselCaseDTO2.setId(vesselCaseDTO1.getId());
        assertThat(vesselCaseDTO1).isEqualTo(vesselCaseDTO2);
        vesselCaseDTO2.setId(2L);
        assertThat(vesselCaseDTO1).isNotEqualTo(vesselCaseDTO2);
        vesselCaseDTO1.setId(null);
        assertThat(vesselCaseDTO1).isNotEqualTo(vesselCaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vesselCaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vesselCaseMapper.fromId(null)).isNull();
    }
}
