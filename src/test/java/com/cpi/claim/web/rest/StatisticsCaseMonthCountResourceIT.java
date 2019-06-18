package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.StatisticsCaseMonthCount;
import com.cpi.claim.repository.StatisticsCaseMonthCountRepository;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link StatisticsCaseMonthCountResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class StatisticsCaseMonthCountResourceIT {

    private static final Integer DEFAULT_CASE_YEAR = 1;
    private static final Integer UPDATED_CASE_YEAR = 2;

    private static final Integer DEFAULT_CASE_MONTH = 1;
    private static final Integer UPDATED_CASE_MONTH = 2;

    private static final String DEFAULT_INSURANCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CASE_COUNT = 1;
    private static final Integer UPDATED_CASE_COUNT = 2;

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private StatisticsCaseMonthCountRepository statisticsCaseMonthCountRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restStatisticsCaseMonthCountMockMvc;

    private StatisticsCaseMonthCount statisticsCaseMonthCount;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatisticsCaseMonthCountResource statisticsCaseMonthCountResource = new StatisticsCaseMonthCountResource(statisticsCaseMonthCountRepository);
        this.restStatisticsCaseMonthCountMockMvc = MockMvcBuilders.standaloneSetup(statisticsCaseMonthCountResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticsCaseMonthCount createEntity(EntityManager em) {
        StatisticsCaseMonthCount statisticsCaseMonthCount = new StatisticsCaseMonthCount()
            .caseYear(DEFAULT_CASE_YEAR)
            .caseMonth(DEFAULT_CASE_MONTH)
            .insuranceType(DEFAULT_INSURANCE_TYPE)
            .caseCount(DEFAULT_CASE_COUNT)
            .updateTime(DEFAULT_UPDATE_TIME);
        return statisticsCaseMonthCount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticsCaseMonthCount createUpdatedEntity(EntityManager em) {
        StatisticsCaseMonthCount statisticsCaseMonthCount = new StatisticsCaseMonthCount()
            .caseYear(UPDATED_CASE_YEAR)
            .caseMonth(UPDATED_CASE_MONTH)
            .insuranceType(UPDATED_INSURANCE_TYPE)
            .caseCount(UPDATED_CASE_COUNT)
            .updateTime(UPDATED_UPDATE_TIME);
        return statisticsCaseMonthCount;
    }

    @BeforeEach
    public void initTest() {
        statisticsCaseMonthCount = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatisticsCaseMonthCount() throws Exception {
        int databaseSizeBeforeCreate = statisticsCaseMonthCountRepository.findAll().size();

        // Create the StatisticsCaseMonthCount
        restStatisticsCaseMonthCountMockMvc.perform(post("/api/statistics-case-month-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsCaseMonthCount)))
            .andExpect(status().isCreated());

        // Validate the StatisticsCaseMonthCount in the database
        List<StatisticsCaseMonthCount> statisticsCaseMonthCountList = statisticsCaseMonthCountRepository.findAll();
        assertThat(statisticsCaseMonthCountList).hasSize(databaseSizeBeforeCreate + 1);
        StatisticsCaseMonthCount testStatisticsCaseMonthCount = statisticsCaseMonthCountList.get(statisticsCaseMonthCountList.size() - 1);
        assertThat(testStatisticsCaseMonthCount.getCaseYear()).isEqualTo(DEFAULT_CASE_YEAR);
        assertThat(testStatisticsCaseMonthCount.getCaseMonth()).isEqualTo(DEFAULT_CASE_MONTH);
        assertThat(testStatisticsCaseMonthCount.getInsuranceType()).isEqualTo(DEFAULT_INSURANCE_TYPE);
        assertThat(testStatisticsCaseMonthCount.getCaseCount()).isEqualTo(DEFAULT_CASE_COUNT);
        assertThat(testStatisticsCaseMonthCount.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createStatisticsCaseMonthCountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statisticsCaseMonthCountRepository.findAll().size();

        // Create the StatisticsCaseMonthCount with an existing ID
        statisticsCaseMonthCount.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatisticsCaseMonthCountMockMvc.perform(post("/api/statistics-case-month-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsCaseMonthCount)))
            .andExpect(status().isBadRequest());

        // Validate the StatisticsCaseMonthCount in the database
        List<StatisticsCaseMonthCount> statisticsCaseMonthCountList = statisticsCaseMonthCountRepository.findAll();
        assertThat(statisticsCaseMonthCountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStatisticsCaseMonthCounts() throws Exception {
        // Initialize the database
        statisticsCaseMonthCountRepository.saveAndFlush(statisticsCaseMonthCount);

        // Get all the statisticsCaseMonthCountList
        restStatisticsCaseMonthCountMockMvc.perform(get("/api/statistics-case-month-counts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statisticsCaseMonthCount.getId().intValue())))
            .andExpect(jsonPath("$.[*].caseYear").value(hasItem(DEFAULT_CASE_YEAR)))
            .andExpect(jsonPath("$.[*].caseMonth").value(hasItem(DEFAULT_CASE_MONTH)))
            .andExpect(jsonPath("$.[*].insuranceType").value(hasItem(DEFAULT_INSURANCE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].caseCount").value(hasItem(DEFAULT_CASE_COUNT)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getStatisticsCaseMonthCount() throws Exception {
        // Initialize the database
        statisticsCaseMonthCountRepository.saveAndFlush(statisticsCaseMonthCount);

        // Get the statisticsCaseMonthCount
        restStatisticsCaseMonthCountMockMvc.perform(get("/api/statistics-case-month-counts/{id}", statisticsCaseMonthCount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statisticsCaseMonthCount.getId().intValue()))
            .andExpect(jsonPath("$.caseYear").value(DEFAULT_CASE_YEAR))
            .andExpect(jsonPath("$.caseMonth").value(DEFAULT_CASE_MONTH))
            .andExpect(jsonPath("$.insuranceType").value(DEFAULT_INSURANCE_TYPE.toString()))
            .andExpect(jsonPath("$.caseCount").value(DEFAULT_CASE_COUNT))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStatisticsCaseMonthCount() throws Exception {
        // Get the statisticsCaseMonthCount
        restStatisticsCaseMonthCountMockMvc.perform(get("/api/statistics-case-month-counts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatisticsCaseMonthCount() throws Exception {
        // Initialize the database
        statisticsCaseMonthCountRepository.saveAndFlush(statisticsCaseMonthCount);

        int databaseSizeBeforeUpdate = statisticsCaseMonthCountRepository.findAll().size();

        // Update the statisticsCaseMonthCount
        StatisticsCaseMonthCount updatedStatisticsCaseMonthCount = statisticsCaseMonthCountRepository.findById(statisticsCaseMonthCount.getId()).get();
        // Disconnect from session so that the updates on updatedStatisticsCaseMonthCount are not directly saved in db
        em.detach(updatedStatisticsCaseMonthCount);
        updatedStatisticsCaseMonthCount
            .caseYear(UPDATED_CASE_YEAR)
            .caseMonth(UPDATED_CASE_MONTH)
            .insuranceType(UPDATED_INSURANCE_TYPE)
            .caseCount(UPDATED_CASE_COUNT)
            .updateTime(UPDATED_UPDATE_TIME);

        restStatisticsCaseMonthCountMockMvc.perform(put("/api/statistics-case-month-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatisticsCaseMonthCount)))
            .andExpect(status().isOk());

        // Validate the StatisticsCaseMonthCount in the database
        List<StatisticsCaseMonthCount> statisticsCaseMonthCountList = statisticsCaseMonthCountRepository.findAll();
        assertThat(statisticsCaseMonthCountList).hasSize(databaseSizeBeforeUpdate);
        StatisticsCaseMonthCount testStatisticsCaseMonthCount = statisticsCaseMonthCountList.get(statisticsCaseMonthCountList.size() - 1);
        assertThat(testStatisticsCaseMonthCount.getCaseYear()).isEqualTo(UPDATED_CASE_YEAR);
        assertThat(testStatisticsCaseMonthCount.getCaseMonth()).isEqualTo(UPDATED_CASE_MONTH);
        assertThat(testStatisticsCaseMonthCount.getInsuranceType()).isEqualTo(UPDATED_INSURANCE_TYPE);
        assertThat(testStatisticsCaseMonthCount.getCaseCount()).isEqualTo(UPDATED_CASE_COUNT);
        assertThat(testStatisticsCaseMonthCount.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingStatisticsCaseMonthCount() throws Exception {
        int databaseSizeBeforeUpdate = statisticsCaseMonthCountRepository.findAll().size();

        // Create the StatisticsCaseMonthCount

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatisticsCaseMonthCountMockMvc.perform(put("/api/statistics-case-month-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsCaseMonthCount)))
            .andExpect(status().isBadRequest());

        // Validate the StatisticsCaseMonthCount in the database
        List<StatisticsCaseMonthCount> statisticsCaseMonthCountList = statisticsCaseMonthCountRepository.findAll();
        assertThat(statisticsCaseMonthCountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatisticsCaseMonthCount() throws Exception {
        // Initialize the database
        statisticsCaseMonthCountRepository.saveAndFlush(statisticsCaseMonthCount);

        int databaseSizeBeforeDelete = statisticsCaseMonthCountRepository.findAll().size();

        // Delete the statisticsCaseMonthCount
        restStatisticsCaseMonthCountMockMvc.perform(delete("/api/statistics-case-month-counts/{id}", statisticsCaseMonthCount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<StatisticsCaseMonthCount> statisticsCaseMonthCountList = statisticsCaseMonthCountRepository.findAll();
        assertThat(statisticsCaseMonthCountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticsCaseMonthCount.class);
        StatisticsCaseMonthCount statisticsCaseMonthCount1 = new StatisticsCaseMonthCount();
        statisticsCaseMonthCount1.setId(1L);
        StatisticsCaseMonthCount statisticsCaseMonthCount2 = new StatisticsCaseMonthCount();
        statisticsCaseMonthCount2.setId(statisticsCaseMonthCount1.getId());
        assertThat(statisticsCaseMonthCount1).isEqualTo(statisticsCaseMonthCount2);
        statisticsCaseMonthCount2.setId(2L);
        assertThat(statisticsCaseMonthCount1).isNotEqualTo(statisticsCaseMonthCount2);
        statisticsCaseMonthCount1.setId(null);
        assertThat(statisticsCaseMonthCount1).isNotEqualTo(statisticsCaseMonthCount2);
    }
}
