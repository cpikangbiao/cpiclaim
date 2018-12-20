package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CaseCloseLog;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.repository.CaseCloseLogRepository;
import com.cpi.claim.service.CaseCloseLogService;
import com.cpi.claim.service.dto.CaseCloseLogDTO;
import com.cpi.claim.service.mapper.CaseCloseLogMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseCloseLogCriteria;
import com.cpi.claim.service.CaseCloseLogQueryService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CaseCloseLogResource REST controller.
 *
 * @see CaseCloseLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseCloseLogResourceIntTest {

    private static final String DEFAULT_OPERATE_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OPERATE_USER_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_OPERATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OPERATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_OPERATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATE_TYPE = "BBBBBBBBBB";

    @Autowired
    private CaseCloseLogRepository caseCloseLogRepository;


    @Autowired
    private CaseCloseLogMapper caseCloseLogMapper;
    

    @Autowired
    private CaseCloseLogService caseCloseLogService;

    @Autowired
    private CaseCloseLogQueryService caseCloseLogQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaseCloseLogMockMvc;

    private CaseCloseLog caseCloseLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseCloseLogResource caseCloseLogResource = new CaseCloseLogResource(caseCloseLogService, caseCloseLogQueryService);
        this.restCaseCloseLogMockMvc = MockMvcBuilders.standaloneSetup(caseCloseLogResource)
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
    public static CaseCloseLog createEntity(EntityManager em) {
        CaseCloseLog caseCloseLog = new CaseCloseLog()
            .operateUserId(DEFAULT_OPERATE_USER_ID)
            .operateDate(DEFAULT_OPERATE_DATE)
            .operateType(DEFAULT_OPERATE_TYPE);
        return caseCloseLog;
    }

    @Before
    public void initTest() {
        caseCloseLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseCloseLog() throws Exception {
        int databaseSizeBeforeCreate = caseCloseLogRepository.findAll().size();

        // Create the CaseCloseLog
        CaseCloseLogDTO caseCloseLogDTO = caseCloseLogMapper.toDto(caseCloseLog);
        restCaseCloseLogMockMvc.perform(post("/api/case-close-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseCloseLogDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseCloseLog in the database
        List<CaseCloseLog> caseCloseLogList = caseCloseLogRepository.findAll();
        assertThat(caseCloseLogList).hasSize(databaseSizeBeforeCreate + 1);
        CaseCloseLog testCaseCloseLog = caseCloseLogList.get(caseCloseLogList.size() - 1);
        assertThat(testCaseCloseLog.getOperateUserId()).isEqualTo(DEFAULT_OPERATE_USER_ID);
        assertThat(testCaseCloseLog.getOperateDate()).isEqualTo(DEFAULT_OPERATE_DATE);
        assertThat(testCaseCloseLog.getOperateType()).isEqualTo(DEFAULT_OPERATE_TYPE);
    }

    @Test
    @Transactional
    public void createCaseCloseLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseCloseLogRepository.findAll().size();

        // Create the CaseCloseLog with an existing ID
        caseCloseLog.setId(1L);
        CaseCloseLogDTO caseCloseLogDTO = caseCloseLogMapper.toDto(caseCloseLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseCloseLogMockMvc.perform(post("/api/case-close-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseCloseLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseCloseLog in the database
        List<CaseCloseLog> caseCloseLogList = caseCloseLogRepository.findAll();
        assertThat(caseCloseLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogs() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList
        restCaseCloseLogMockMvc.perform(get("/api/case-close-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseCloseLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].operateUserId").value(hasItem(DEFAULT_OPERATE_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].operateDate").value(hasItem(DEFAULT_OPERATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].operateType").value(hasItem(DEFAULT_OPERATE_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getCaseCloseLog() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get the caseCloseLog
        restCaseCloseLogMockMvc.perform(get("/api/case-close-logs/{id}", caseCloseLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseCloseLog.getId().intValue()))
            .andExpect(jsonPath("$.operateUserId").value(DEFAULT_OPERATE_USER_ID.toString()))
            .andExpect(jsonPath("$.operateDate").value(DEFAULT_OPERATE_DATE.toString()))
            .andExpect(jsonPath("$.operateType").value(DEFAULT_OPERATE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByOperateUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList where operateUserId equals to DEFAULT_OPERATE_USER_ID
        defaultCaseCloseLogShouldBeFound("operateUserId.equals=" + DEFAULT_OPERATE_USER_ID);

        // Get all the caseCloseLogList where operateUserId equals to UPDATED_OPERATE_USER_ID
        defaultCaseCloseLogShouldNotBeFound("operateUserId.equals=" + UPDATED_OPERATE_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByOperateUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList where operateUserId in DEFAULT_OPERATE_USER_ID or UPDATED_OPERATE_USER_ID
        defaultCaseCloseLogShouldBeFound("operateUserId.in=" + DEFAULT_OPERATE_USER_ID + "," + UPDATED_OPERATE_USER_ID);

        // Get all the caseCloseLogList where operateUserId equals to UPDATED_OPERATE_USER_ID
        defaultCaseCloseLogShouldNotBeFound("operateUserId.in=" + UPDATED_OPERATE_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByOperateUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList where operateUserId is not null
        defaultCaseCloseLogShouldBeFound("operateUserId.specified=true");

        // Get all the caseCloseLogList where operateUserId is null
        defaultCaseCloseLogShouldNotBeFound("operateUserId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByOperateDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList where operateDate equals to DEFAULT_OPERATE_DATE
        defaultCaseCloseLogShouldBeFound("operateDate.equals=" + DEFAULT_OPERATE_DATE);

        // Get all the caseCloseLogList where operateDate equals to UPDATED_OPERATE_DATE
        defaultCaseCloseLogShouldNotBeFound("operateDate.equals=" + UPDATED_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByOperateDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList where operateDate in DEFAULT_OPERATE_DATE or UPDATED_OPERATE_DATE
        defaultCaseCloseLogShouldBeFound("operateDate.in=" + DEFAULT_OPERATE_DATE + "," + UPDATED_OPERATE_DATE);

        // Get all the caseCloseLogList where operateDate equals to UPDATED_OPERATE_DATE
        defaultCaseCloseLogShouldNotBeFound("operateDate.in=" + UPDATED_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByOperateDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList where operateDate is not null
        defaultCaseCloseLogShouldBeFound("operateDate.specified=true");

        // Get all the caseCloseLogList where operateDate is null
        defaultCaseCloseLogShouldNotBeFound("operateDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByOperateTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList where operateType equals to DEFAULT_OPERATE_TYPE
        defaultCaseCloseLogShouldBeFound("operateType.equals=" + DEFAULT_OPERATE_TYPE);

        // Get all the caseCloseLogList where operateType equals to UPDATED_OPERATE_TYPE
        defaultCaseCloseLogShouldNotBeFound("operateType.equals=" + UPDATED_OPERATE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByOperateTypeIsInShouldWork() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList where operateType in DEFAULT_OPERATE_TYPE or UPDATED_OPERATE_TYPE
        defaultCaseCloseLogShouldBeFound("operateType.in=" + DEFAULT_OPERATE_TYPE + "," + UPDATED_OPERATE_TYPE);

        // Get all the caseCloseLogList where operateType equals to UPDATED_OPERATE_TYPE
        defaultCaseCloseLogShouldNotBeFound("operateType.in=" + UPDATED_OPERATE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByOperateTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        // Get all the caseCloseLogList where operateType is not null
        defaultCaseCloseLogShouldBeFound("operateType.specified=true");

        // Get all the caseCloseLogList where operateType is null
        defaultCaseCloseLogShouldNotBeFound("operateType.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseCloseLogsByVesselCaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselCase vesselCase = VesselCaseResourceIntTest.createEntity(em);
        em.persist(vesselCase);
        em.flush();
        caseCloseLog.setVesselCase(vesselCase);
        caseCloseLogRepository.saveAndFlush(caseCloseLog);
        Long vesselCaseId = vesselCase.getId();

        // Get all the caseCloseLogList where vesselCase equals to vesselCaseId
        defaultCaseCloseLogShouldBeFound("vesselCaseId.equals=" + vesselCaseId);

        // Get all the caseCloseLogList where vesselCase equals to vesselCaseId + 1
        defaultCaseCloseLogShouldNotBeFound("vesselCaseId.equals=" + (vesselCaseId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCaseCloseLogShouldBeFound(String filter) throws Exception {
        restCaseCloseLogMockMvc.perform(get("/api/case-close-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseCloseLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].operateUserId").value(hasItem(DEFAULT_OPERATE_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].operateDate").value(hasItem(DEFAULT_OPERATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].operateType").value(hasItem(DEFAULT_OPERATE_TYPE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCaseCloseLogShouldNotBeFound(String filter) throws Exception {
        restCaseCloseLogMockMvc.perform(get("/api/case-close-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCaseCloseLog() throws Exception {
        // Get the caseCloseLog
        restCaseCloseLogMockMvc.perform(get("/api/case-close-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseCloseLog() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        int databaseSizeBeforeUpdate = caseCloseLogRepository.findAll().size();

        // Update the caseCloseLog
        CaseCloseLog updatedCaseCloseLog = caseCloseLogRepository.findById(caseCloseLog.getId()).get();
        // Disconnect from session so that the updates on updatedCaseCloseLog are not directly saved in db
        em.detach(updatedCaseCloseLog);
        updatedCaseCloseLog
            .operateUserId(UPDATED_OPERATE_USER_ID)
            .operateDate(UPDATED_OPERATE_DATE)
            .operateType(UPDATED_OPERATE_TYPE);
        CaseCloseLogDTO caseCloseLogDTO = caseCloseLogMapper.toDto(updatedCaseCloseLog);

        restCaseCloseLogMockMvc.perform(put("/api/case-close-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseCloseLogDTO)))
            .andExpect(status().isOk());

        // Validate the CaseCloseLog in the database
        List<CaseCloseLog> caseCloseLogList = caseCloseLogRepository.findAll();
        assertThat(caseCloseLogList).hasSize(databaseSizeBeforeUpdate);
        CaseCloseLog testCaseCloseLog = caseCloseLogList.get(caseCloseLogList.size() - 1);
        assertThat(testCaseCloseLog.getOperateUserId()).isEqualTo(UPDATED_OPERATE_USER_ID);
        assertThat(testCaseCloseLog.getOperateDate()).isEqualTo(UPDATED_OPERATE_DATE);
        assertThat(testCaseCloseLog.getOperateType()).isEqualTo(UPDATED_OPERATE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseCloseLog() throws Exception {
        int databaseSizeBeforeUpdate = caseCloseLogRepository.findAll().size();

        // Create the CaseCloseLog
        CaseCloseLogDTO caseCloseLogDTO = caseCloseLogMapper.toDto(caseCloseLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCaseCloseLogMockMvc.perform(put("/api/case-close-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseCloseLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseCloseLog in the database
        List<CaseCloseLog> caseCloseLogList = caseCloseLogRepository.findAll();
        assertThat(caseCloseLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseCloseLog() throws Exception {
        // Initialize the database
        caseCloseLogRepository.saveAndFlush(caseCloseLog);

        int databaseSizeBeforeDelete = caseCloseLogRepository.findAll().size();

        // Get the caseCloseLog
        restCaseCloseLogMockMvc.perform(delete("/api/case-close-logs/{id}", caseCloseLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseCloseLog> caseCloseLogList = caseCloseLogRepository.findAll();
        assertThat(caseCloseLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseCloseLog.class);
        CaseCloseLog caseCloseLog1 = new CaseCloseLog();
        caseCloseLog1.setId(1L);
        CaseCloseLog caseCloseLog2 = new CaseCloseLog();
        caseCloseLog2.setId(caseCloseLog1.getId());
        assertThat(caseCloseLog1).isEqualTo(caseCloseLog2);
        caseCloseLog2.setId(2L);
        assertThat(caseCloseLog1).isNotEqualTo(caseCloseLog2);
        caseCloseLog1.setId(null);
        assertThat(caseCloseLog1).isNotEqualTo(caseCloseLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseCloseLogDTO.class);
        CaseCloseLogDTO caseCloseLogDTO1 = new CaseCloseLogDTO();
        caseCloseLogDTO1.setId(1L);
        CaseCloseLogDTO caseCloseLogDTO2 = new CaseCloseLogDTO();
        assertThat(caseCloseLogDTO1).isNotEqualTo(caseCloseLogDTO2);
        caseCloseLogDTO2.setId(caseCloseLogDTO1.getId());
        assertThat(caseCloseLogDTO1).isEqualTo(caseCloseLogDTO2);
        caseCloseLogDTO2.setId(2L);
        assertThat(caseCloseLogDTO1).isNotEqualTo(caseCloseLogDTO2);
        caseCloseLogDTO1.setId(null);
        assertThat(caseCloseLogDTO1).isNotEqualTo(caseCloseLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseCloseLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseCloseLogMapper.fromId(null)).isNull();
    }
}
