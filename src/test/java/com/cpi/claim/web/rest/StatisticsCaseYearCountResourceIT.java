package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.StatisticsCaseYearCount;
import com.cpi.claim.repository.StatisticsCaseYearCountRepository;
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
 * Integration tests for the {@Link StatisticsCaseYearCountResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class StatisticsCaseYearCountResourceIT {

    private static final String DEFAULT_CASE_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_CASE_YEAR = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CASE_COUNT = 1;
    private static final Integer UPDATED_CASE_COUNT = 2;

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private StatisticsCaseYearCountRepository statisticsCaseYearCountRepository;

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

    private MockMvc restStatisticsCaseYearCountMockMvc;

    private StatisticsCaseYearCount statisticsCaseYearCount;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatisticsCaseYearCountResource statisticsCaseYearCountResource = new StatisticsCaseYearCountResource(statisticsCaseYearCountRepository);
        this.restStatisticsCaseYearCountMockMvc = MockMvcBuilders.standaloneSetup(statisticsCaseYearCountResource)
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
    public static StatisticsCaseYearCount createEntity(EntityManager em) {
        StatisticsCaseYearCount statisticsCaseYearCount = new StatisticsCaseYearCount()
            .caseYear(DEFAULT_CASE_YEAR)
            .insuranceType(DEFAULT_INSURANCE_TYPE)
            .caseCount(DEFAULT_CASE_COUNT)
            .updateTime(DEFAULT_UPDATE_TIME);
        return statisticsCaseYearCount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticsCaseYearCount createUpdatedEntity(EntityManager em) {
        StatisticsCaseYearCount statisticsCaseYearCount = new StatisticsCaseYearCount()
            .caseYear(UPDATED_CASE_YEAR)
            .insuranceType(UPDATED_INSURANCE_TYPE)
            .caseCount(UPDATED_CASE_COUNT)
            .updateTime(UPDATED_UPDATE_TIME);
        return statisticsCaseYearCount;
    }

    @BeforeEach
    public void initTest() {
        statisticsCaseYearCount = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatisticsCaseYearCount() throws Exception {
        int databaseSizeBeforeCreate = statisticsCaseYearCountRepository.findAll().size();

        // Create the StatisticsCaseYearCount
        restStatisticsCaseYearCountMockMvc.perform(post("/api/statistics-case-year-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsCaseYearCount)))
            .andExpect(status().isCreated());

        // Validate the StatisticsCaseYearCount in the database
        List<StatisticsCaseYearCount> statisticsCaseYearCountList = statisticsCaseYearCountRepository.findAll();
        assertThat(statisticsCaseYearCountList).hasSize(databaseSizeBeforeCreate + 1);
        StatisticsCaseYearCount testStatisticsCaseYearCount = statisticsCaseYearCountList.get(statisticsCaseYearCountList.size() - 1);
        assertThat(testStatisticsCaseYearCount.getCaseYear()).isEqualTo(DEFAULT_CASE_YEAR);
        assertThat(testStatisticsCaseYearCount.getInsuranceType()).isEqualTo(DEFAULT_INSURANCE_TYPE);
        assertThat(testStatisticsCaseYearCount.getCaseCount()).isEqualTo(DEFAULT_CASE_COUNT);
        assertThat(testStatisticsCaseYearCount.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createStatisticsCaseYearCountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statisticsCaseYearCountRepository.findAll().size();

        // Create the StatisticsCaseYearCount with an existing ID
        statisticsCaseYearCount.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatisticsCaseYearCountMockMvc.perform(post("/api/statistics-case-year-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsCaseYearCount)))
            .andExpect(status().isBadRequest());

        // Validate the StatisticsCaseYearCount in the database
        List<StatisticsCaseYearCount> statisticsCaseYearCountList = statisticsCaseYearCountRepository.findAll();
        assertThat(statisticsCaseYearCountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStatisticsCaseYearCounts() throws Exception {
        // Initialize the database
        statisticsCaseYearCountRepository.saveAndFlush(statisticsCaseYearCount);

        // Get all the statisticsCaseYearCountList
        restStatisticsCaseYearCountMockMvc.perform(get("/api/statistics-case-year-counts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statisticsCaseYearCount.getId().intValue())))
            .andExpect(jsonPath("$.[*].caseYear").value(hasItem(DEFAULT_CASE_YEAR.toString())))
            .andExpect(jsonPath("$.[*].insuranceType").value(hasItem(DEFAULT_INSURANCE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].caseCount").value(hasItem(DEFAULT_CASE_COUNT)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getStatisticsCaseYearCount() throws Exception {
        // Initialize the database
        statisticsCaseYearCountRepository.saveAndFlush(statisticsCaseYearCount);

        // Get the statisticsCaseYearCount
        restStatisticsCaseYearCountMockMvc.perform(get("/api/statistics-case-year-counts/{id}", statisticsCaseYearCount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statisticsCaseYearCount.getId().intValue()))
            .andExpect(jsonPath("$.caseYear").value(DEFAULT_CASE_YEAR.toString()))
            .andExpect(jsonPath("$.insuranceType").value(DEFAULT_INSURANCE_TYPE.toString()))
            .andExpect(jsonPath("$.caseCount").value(DEFAULT_CASE_COUNT))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStatisticsCaseYearCount() throws Exception {
        // Get the statisticsCaseYearCount
        restStatisticsCaseYearCountMockMvc.perform(get("/api/statistics-case-year-counts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatisticsCaseYearCount() throws Exception {
        // Initialize the database
        statisticsCaseYearCountRepository.saveAndFlush(statisticsCaseYearCount);

        int databaseSizeBeforeUpdate = statisticsCaseYearCountRepository.findAll().size();

        // Update the statisticsCaseYearCount
        StatisticsCaseYearCount updatedStatisticsCaseYearCount = statisticsCaseYearCountRepository.findById(statisticsCaseYearCount.getId()).get();
        // Disconnect from session so that the updates on updatedStatisticsCaseYearCount are not directly saved in db
        em.detach(updatedStatisticsCaseYearCount);
        updatedStatisticsCaseYearCount
            .caseYear(UPDATED_CASE_YEAR)
            .insuranceType(UPDATED_INSURANCE_TYPE)
            .caseCount(UPDATED_CASE_COUNT)
            .updateTime(UPDATED_UPDATE_TIME);

        restStatisticsCaseYearCountMockMvc.perform(put("/api/statistics-case-year-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatisticsCaseYearCount)))
            .andExpect(status().isOk());

        // Validate the StatisticsCaseYearCount in the database
        List<StatisticsCaseYearCount> statisticsCaseYearCountList = statisticsCaseYearCountRepository.findAll();
        assertThat(statisticsCaseYearCountList).hasSize(databaseSizeBeforeUpdate);
        StatisticsCaseYearCount testStatisticsCaseYearCount = statisticsCaseYearCountList.get(statisticsCaseYearCountList.size() - 1);
        assertThat(testStatisticsCaseYearCount.getCaseYear()).isEqualTo(UPDATED_CASE_YEAR);
        assertThat(testStatisticsCaseYearCount.getInsuranceType()).isEqualTo(UPDATED_INSURANCE_TYPE);
        assertThat(testStatisticsCaseYearCount.getCaseCount()).isEqualTo(UPDATED_CASE_COUNT);
        assertThat(testStatisticsCaseYearCount.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingStatisticsCaseYearCount() throws Exception {
        int databaseSizeBeforeUpdate = statisticsCaseYearCountRepository.findAll().size();

        // Create the StatisticsCaseYearCount

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatisticsCaseYearCountMockMvc.perform(put("/api/statistics-case-year-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsCaseYearCount)))
            .andExpect(status().isBadRequest());

        // Validate the StatisticsCaseYearCount in the database
        List<StatisticsCaseYearCount> statisticsCaseYearCountList = statisticsCaseYearCountRepository.findAll();
        assertThat(statisticsCaseYearCountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatisticsCaseYearCount() throws Exception {
        // Initialize the database
        statisticsCaseYearCountRepository.saveAndFlush(statisticsCaseYearCount);

        int databaseSizeBeforeDelete = statisticsCaseYearCountRepository.findAll().size();

        // Delete the statisticsCaseYearCount
        restStatisticsCaseYearCountMockMvc.perform(delete("/api/statistics-case-year-counts/{id}", statisticsCaseYearCount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<StatisticsCaseYearCount> statisticsCaseYearCountList = statisticsCaseYearCountRepository.findAll();
        assertThat(statisticsCaseYearCountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticsCaseYearCount.class);
        StatisticsCaseYearCount statisticsCaseYearCount1 = new StatisticsCaseYearCount();
        statisticsCaseYearCount1.setId(1L);
        StatisticsCaseYearCount statisticsCaseYearCount2 = new StatisticsCaseYearCount();
        statisticsCaseYearCount2.setId(statisticsCaseYearCount1.getId());
        assertThat(statisticsCaseYearCount1).isEqualTo(statisticsCaseYearCount2);
        statisticsCaseYearCount2.setId(2L);
        assertThat(statisticsCaseYearCount1).isNotEqualTo(statisticsCaseYearCount2);
        statisticsCaseYearCount1.setId(null);
        assertThat(statisticsCaseYearCount1).isNotEqualTo(statisticsCaseYearCount2);
    }
}
