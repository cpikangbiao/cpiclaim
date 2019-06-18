package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.Risk;
import com.cpi.claim.domain.RiskGroup;
import com.cpi.claim.repository.RiskRepository;
import com.cpi.claim.service.RiskService;
import com.cpi.claim.service.dto.RiskDTO;
import com.cpi.claim.service.mapper.RiskMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.RiskCriteria;
import com.cpi.claim.service.RiskQueryService;

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

import javax.persistence.EntityManager;
import java.util.List;


import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RiskResource REST controller.
 *
 * @see RiskResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class RiskResourceIntTest {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final Boolean DEFAULT_TCL_TYPE = false;
    private static final Boolean UPDATED_TCL_TYPE = true;

    private static final Boolean DEFAULT_PI_TYPE = false;
    private static final Boolean UPDATED_PI_TYPE = true;

    private static final String DEFAULT_RISK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RISK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RISK_NAME_CHINESE = "AAAAAAAAAA";
    private static final String UPDATED_RISK_NAME_CHINESE = "BBBBBBBBBB";

    private static final String DEFAULT_RISK_NAME_ENGLISH = "AAAAAAAAAA";
    private static final String UPDATED_RISK_NAME_ENGLISH = "BBBBBBBBBB";

    private static final String DEFAULT_RISK_NAME_ENGLISH_ABBR = "AAAAAAAAAA";
    private static final String UPDATED_RISK_NAME_ENGLISH_ABBR = "BBBBBBBBBB";

    @Autowired
    private RiskRepository riskRepository;


    @Autowired
    private RiskMapper riskMapper;
    

    @Autowired
    private RiskService riskService;

    @Autowired
    private RiskQueryService riskQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRiskMockMvc;

    private Risk risk;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RiskResource riskResource = new RiskResource(riskService, riskQueryService);
        this.restRiskMockMvc = MockMvcBuilders.standaloneSetup(riskResource)
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
    public static Risk createEntity(EntityManager em) {
        Risk risk = new Risk()
            .sortNum(DEFAULT_SORT_NUM)
            .tclType(DEFAULT_TCL_TYPE)
            .piType(DEFAULT_PI_TYPE)
            .riskName(DEFAULT_RISK_NAME)
            .riskNameChinese(DEFAULT_RISK_NAME_CHINESE)
            .riskNameEnglish(DEFAULT_RISK_NAME_ENGLISH)
            .riskNameEnglishAbbr(DEFAULT_RISK_NAME_ENGLISH_ABBR);
        return risk;
    }

    @Before
    public void initTest() {
        risk = createEntity(em);
    }

    @Test
    @Transactional
    public void createRisk() throws Exception {
        int databaseSizeBeforeCreate = riskRepository.findAll().size();

        // Create the Risk
        RiskDTO riskDTO = riskMapper.toDto(risk);
        restRiskMockMvc.perform(post("/api/risks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isCreated());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeCreate + 1);
        Risk testRisk = riskList.get(riskList.size() - 1);
        assertThat(testRisk.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testRisk.isTclType()).isEqualTo(DEFAULT_TCL_TYPE);
        assertThat(testRisk.isPiType()).isEqualTo(DEFAULT_PI_TYPE);
        assertThat(testRisk.getRiskName()).isEqualTo(DEFAULT_RISK_NAME);
        assertThat(testRisk.getRiskNameChinese()).isEqualTo(DEFAULT_RISK_NAME_CHINESE);
        assertThat(testRisk.getRiskNameEnglish()).isEqualTo(DEFAULT_RISK_NAME_ENGLISH);
        assertThat(testRisk.getRiskNameEnglishAbbr()).isEqualTo(DEFAULT_RISK_NAME_ENGLISH_ABBR);
    }

    @Test
    @Transactional
    public void createRiskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskRepository.findAll().size();

        // Create the Risk with an existing ID
        risk.setId(1L);
        RiskDTO riskDTO = riskMapper.toDto(risk);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskMockMvc.perform(post("/api/risks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRisks() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList
        restRiskMockMvc.perform(get("/api/risks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risk.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].tclType").value(hasItem(DEFAULT_TCL_TYPE.booleanValue())))
            .andExpect(jsonPath("$.[*].piType").value(hasItem(DEFAULT_PI_TYPE.booleanValue())))
            .andExpect(jsonPath("$.[*].riskName").value(hasItem(DEFAULT_RISK_NAME.toString())))
            .andExpect(jsonPath("$.[*].riskNameChinese").value(hasItem(DEFAULT_RISK_NAME_CHINESE.toString())))
            .andExpect(jsonPath("$.[*].riskNameEnglish").value(hasItem(DEFAULT_RISK_NAME_ENGLISH.toString())))
            .andExpect(jsonPath("$.[*].riskNameEnglishAbbr").value(hasItem(DEFAULT_RISK_NAME_ENGLISH_ABBR.toString())));
    }
    

    @Test
    @Transactional
    public void getRisk() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get the risk
        restRiskMockMvc.perform(get("/api/risks/{id}", risk.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(risk.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.tclType").value(DEFAULT_TCL_TYPE.booleanValue()))
            .andExpect(jsonPath("$.piType").value(DEFAULT_PI_TYPE.booleanValue()))
            .andExpect(jsonPath("$.riskName").value(DEFAULT_RISK_NAME.toString()))
            .andExpect(jsonPath("$.riskNameChinese").value(DEFAULT_RISK_NAME_CHINESE.toString()))
            .andExpect(jsonPath("$.riskNameEnglish").value(DEFAULT_RISK_NAME_ENGLISH.toString()))
            .andExpect(jsonPath("$.riskNameEnglishAbbr").value(DEFAULT_RISK_NAME_ENGLISH_ABBR.toString()));
    }

    @Test
    @Transactional
    public void getAllRisksBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where sortNum equals to DEFAULT_SORT_NUM
        defaultRiskShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the riskList where sortNum equals to UPDATED_SORT_NUM
        defaultRiskShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllRisksBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultRiskShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the riskList where sortNum equals to UPDATED_SORT_NUM
        defaultRiskShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllRisksBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where sortNum is not null
        defaultRiskShouldBeFound("sortNum.specified=true");

        // Get all the riskList where sortNum is null
        defaultRiskShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllRisksBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultRiskShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the riskList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultRiskShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllRisksBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultRiskShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the riskList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultRiskShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllRisksByTclTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where tclType equals to DEFAULT_TCL_TYPE
        defaultRiskShouldBeFound("tclType.equals=" + DEFAULT_TCL_TYPE);

        // Get all the riskList where tclType equals to UPDATED_TCL_TYPE
        defaultRiskShouldNotBeFound("tclType.equals=" + UPDATED_TCL_TYPE);
    }

    @Test
    @Transactional
    public void getAllRisksByTclTypeIsInShouldWork() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where tclType in DEFAULT_TCL_TYPE or UPDATED_TCL_TYPE
        defaultRiskShouldBeFound("tclType.in=" + DEFAULT_TCL_TYPE + "," + UPDATED_TCL_TYPE);

        // Get all the riskList where tclType equals to UPDATED_TCL_TYPE
        defaultRiskShouldNotBeFound("tclType.in=" + UPDATED_TCL_TYPE);
    }

    @Test
    @Transactional
    public void getAllRisksByTclTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where tclType is not null
        defaultRiskShouldBeFound("tclType.specified=true");

        // Get all the riskList where tclType is null
        defaultRiskShouldNotBeFound("tclType.specified=false");
    }

    @Test
    @Transactional
    public void getAllRisksByPiTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where piType equals to DEFAULT_PI_TYPE
        defaultRiskShouldBeFound("piType.equals=" + DEFAULT_PI_TYPE);

        // Get all the riskList where piType equals to UPDATED_PI_TYPE
        defaultRiskShouldNotBeFound("piType.equals=" + UPDATED_PI_TYPE);
    }

    @Test
    @Transactional
    public void getAllRisksByPiTypeIsInShouldWork() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where piType in DEFAULT_PI_TYPE or UPDATED_PI_TYPE
        defaultRiskShouldBeFound("piType.in=" + DEFAULT_PI_TYPE + "," + UPDATED_PI_TYPE);

        // Get all the riskList where piType equals to UPDATED_PI_TYPE
        defaultRiskShouldNotBeFound("piType.in=" + UPDATED_PI_TYPE);
    }

    @Test
    @Transactional
    public void getAllRisksByPiTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where piType is not null
        defaultRiskShouldBeFound("piType.specified=true");

        // Get all the riskList where piType is null
        defaultRiskShouldNotBeFound("piType.specified=false");
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameIsEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskName equals to DEFAULT_RISK_NAME
        defaultRiskShouldBeFound("riskName.equals=" + DEFAULT_RISK_NAME);

        // Get all the riskList where riskName equals to UPDATED_RISK_NAME
        defaultRiskShouldNotBeFound("riskName.equals=" + UPDATED_RISK_NAME);
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameIsInShouldWork() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskName in DEFAULT_RISK_NAME or UPDATED_RISK_NAME
        defaultRiskShouldBeFound("riskName.in=" + DEFAULT_RISK_NAME + "," + UPDATED_RISK_NAME);

        // Get all the riskList where riskName equals to UPDATED_RISK_NAME
        defaultRiskShouldNotBeFound("riskName.in=" + UPDATED_RISK_NAME);
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskName is not null
        defaultRiskShouldBeFound("riskName.specified=true");

        // Get all the riskList where riskName is null
        defaultRiskShouldNotBeFound("riskName.specified=false");
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameChineseIsEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskNameChinese equals to DEFAULT_RISK_NAME_CHINESE
        defaultRiskShouldBeFound("riskNameChinese.equals=" + DEFAULT_RISK_NAME_CHINESE);

        // Get all the riskList where riskNameChinese equals to UPDATED_RISK_NAME_CHINESE
        defaultRiskShouldNotBeFound("riskNameChinese.equals=" + UPDATED_RISK_NAME_CHINESE);
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameChineseIsInShouldWork() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskNameChinese in DEFAULT_RISK_NAME_CHINESE or UPDATED_RISK_NAME_CHINESE
        defaultRiskShouldBeFound("riskNameChinese.in=" + DEFAULT_RISK_NAME_CHINESE + "," + UPDATED_RISK_NAME_CHINESE);

        // Get all the riskList where riskNameChinese equals to UPDATED_RISK_NAME_CHINESE
        defaultRiskShouldNotBeFound("riskNameChinese.in=" + UPDATED_RISK_NAME_CHINESE);
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameChineseIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskNameChinese is not null
        defaultRiskShouldBeFound("riskNameChinese.specified=true");

        // Get all the riskList where riskNameChinese is null
        defaultRiskShouldNotBeFound("riskNameChinese.specified=false");
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameEnglishIsEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskNameEnglish equals to DEFAULT_RISK_NAME_ENGLISH
        defaultRiskShouldBeFound("riskNameEnglish.equals=" + DEFAULT_RISK_NAME_ENGLISH);

        // Get all the riskList where riskNameEnglish equals to UPDATED_RISK_NAME_ENGLISH
        defaultRiskShouldNotBeFound("riskNameEnglish.equals=" + UPDATED_RISK_NAME_ENGLISH);
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameEnglishIsInShouldWork() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskNameEnglish in DEFAULT_RISK_NAME_ENGLISH or UPDATED_RISK_NAME_ENGLISH
        defaultRiskShouldBeFound("riskNameEnglish.in=" + DEFAULT_RISK_NAME_ENGLISH + "," + UPDATED_RISK_NAME_ENGLISH);

        // Get all the riskList where riskNameEnglish equals to UPDATED_RISK_NAME_ENGLISH
        defaultRiskShouldNotBeFound("riskNameEnglish.in=" + UPDATED_RISK_NAME_ENGLISH);
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameEnglishIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskNameEnglish is not null
        defaultRiskShouldBeFound("riskNameEnglish.specified=true");

        // Get all the riskList where riskNameEnglish is null
        defaultRiskShouldNotBeFound("riskNameEnglish.specified=false");
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameEnglishAbbrIsEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskNameEnglishAbbr equals to DEFAULT_RISK_NAME_ENGLISH_ABBR
        defaultRiskShouldBeFound("riskNameEnglishAbbr.equals=" + DEFAULT_RISK_NAME_ENGLISH_ABBR);

        // Get all the riskList where riskNameEnglishAbbr equals to UPDATED_RISK_NAME_ENGLISH_ABBR
        defaultRiskShouldNotBeFound("riskNameEnglishAbbr.equals=" + UPDATED_RISK_NAME_ENGLISH_ABBR);
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameEnglishAbbrIsInShouldWork() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskNameEnglishAbbr in DEFAULT_RISK_NAME_ENGLISH_ABBR or UPDATED_RISK_NAME_ENGLISH_ABBR
        defaultRiskShouldBeFound("riskNameEnglishAbbr.in=" + DEFAULT_RISK_NAME_ENGLISH_ABBR + "," + UPDATED_RISK_NAME_ENGLISH_ABBR);

        // Get all the riskList where riskNameEnglishAbbr equals to UPDATED_RISK_NAME_ENGLISH_ABBR
        defaultRiskShouldNotBeFound("riskNameEnglishAbbr.in=" + UPDATED_RISK_NAME_ENGLISH_ABBR);
    }

    @Test
    @Transactional
    public void getAllRisksByRiskNameEnglishAbbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where riskNameEnglishAbbr is not null
        defaultRiskShouldBeFound("riskNameEnglishAbbr.specified=true");

        // Get all the riskList where riskNameEnglishAbbr is null
        defaultRiskShouldNotBeFound("riskNameEnglishAbbr.specified=false");
    }

    @Test
    @Transactional
    public void getAllRisksByRiskGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        RiskGroup riskGroup = RiskGroupResourceIntTest.createEntity(em);
        em.persist(riskGroup);
        em.flush();
        risk.setRiskGroup(riskGroup);
        riskRepository.saveAndFlush(risk);
        Long riskGroupId = riskGroup.getId();

        // Get all the riskList where riskGroup equals to riskGroupId
        defaultRiskShouldBeFound("riskGroupId.equals=" + riskGroupId);

        // Get all the riskList where riskGroup equals to riskGroupId + 1
        defaultRiskShouldNotBeFound("riskGroupId.equals=" + (riskGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultRiskShouldBeFound(String filter) throws Exception {
        restRiskMockMvc.perform(get("/api/risks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risk.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].tclType").value(hasItem(DEFAULT_TCL_TYPE.booleanValue())))
            .andExpect(jsonPath("$.[*].piType").value(hasItem(DEFAULT_PI_TYPE.booleanValue())))
            .andExpect(jsonPath("$.[*].riskName").value(hasItem(DEFAULT_RISK_NAME.toString())))
            .andExpect(jsonPath("$.[*].riskNameChinese").value(hasItem(DEFAULT_RISK_NAME_CHINESE.toString())))
            .andExpect(jsonPath("$.[*].riskNameEnglish").value(hasItem(DEFAULT_RISK_NAME_ENGLISH.toString())))
            .andExpect(jsonPath("$.[*].riskNameEnglishAbbr").value(hasItem(DEFAULT_RISK_NAME_ENGLISH_ABBR.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultRiskShouldNotBeFound(String filter) throws Exception {
        restRiskMockMvc.perform(get("/api/risks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingRisk() throws Exception {
        // Get the risk
        restRiskMockMvc.perform(get("/api/risks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRisk() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        int databaseSizeBeforeUpdate = riskRepository.findAll().size();

        // Update the risk
        Risk updatedRisk = riskRepository.findById(risk.getId()).get();
        // Disconnect from session so that the updates on updatedRisk are not directly saved in db
        em.detach(updatedRisk);
        updatedRisk
            .sortNum(UPDATED_SORT_NUM)
            .tclType(UPDATED_TCL_TYPE)
            .piType(UPDATED_PI_TYPE)
            .riskName(UPDATED_RISK_NAME)
            .riskNameChinese(UPDATED_RISK_NAME_CHINESE)
            .riskNameEnglish(UPDATED_RISK_NAME_ENGLISH)
            .riskNameEnglishAbbr(UPDATED_RISK_NAME_ENGLISH_ABBR);
        RiskDTO riskDTO = riskMapper.toDto(updatedRisk);

        restRiskMockMvc.perform(put("/api/risks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isOk());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeUpdate);
        Risk testRisk = riskList.get(riskList.size() - 1);
        assertThat(testRisk.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testRisk.isTclType()).isEqualTo(UPDATED_TCL_TYPE);
        assertThat(testRisk.isPiType()).isEqualTo(UPDATED_PI_TYPE);
        assertThat(testRisk.getRiskName()).isEqualTo(UPDATED_RISK_NAME);
        assertThat(testRisk.getRiskNameChinese()).isEqualTo(UPDATED_RISK_NAME_CHINESE);
        assertThat(testRisk.getRiskNameEnglish()).isEqualTo(UPDATED_RISK_NAME_ENGLISH);
        assertThat(testRisk.getRiskNameEnglishAbbr()).isEqualTo(UPDATED_RISK_NAME_ENGLISH_ABBR);
    }

    @Test
    @Transactional
    public void updateNonExistingRisk() throws Exception {
        int databaseSizeBeforeUpdate = riskRepository.findAll().size();

        // Create the Risk
        RiskDTO riskDTO = riskMapper.toDto(risk);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restRiskMockMvc.perform(put("/api/risks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRisk() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        int databaseSizeBeforeDelete = riskRepository.findAll().size();

        // Get the risk
        restRiskMockMvc.perform(delete("/api/risks/{id}", risk.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Risk.class);
        Risk risk1 = new Risk();
        risk1.setId(1L);
        Risk risk2 = new Risk();
        risk2.setId(risk1.getId());
        assertThat(risk1).isEqualTo(risk2);
        risk2.setId(2L);
        assertThat(risk1).isNotEqualTo(risk2);
        risk1.setId(null);
        assertThat(risk1).isNotEqualTo(risk2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskDTO.class);
        RiskDTO riskDTO1 = new RiskDTO();
        riskDTO1.setId(1L);
        RiskDTO riskDTO2 = new RiskDTO();
        assertThat(riskDTO1).isNotEqualTo(riskDTO2);
        riskDTO2.setId(riskDTO1.getId());
        assertThat(riskDTO1).isEqualTo(riskDTO2);
        riskDTO2.setId(2L);
        assertThat(riskDTO1).isNotEqualTo(riskDTO2);
        riskDTO1.setId(null);
        assertThat(riskDTO1).isNotEqualTo(riskDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(riskMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(riskMapper.fromId(null)).isNull();
    }
}
