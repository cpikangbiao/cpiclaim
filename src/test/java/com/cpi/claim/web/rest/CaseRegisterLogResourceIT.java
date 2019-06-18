package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.CaseRegisterLog;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.repository.CaseRegisterLogRepository;
import com.cpi.claim.service.CaseRegisterLogService;
import com.cpi.claim.service.dto.CaseRegisterLogDTO;
import com.cpi.claim.service.mapper.CaseRegisterLogMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseRegisterLogCriteria;
import com.cpi.claim.service.CaseRegisterLogQueryService;

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
 * Integration tests for the {@Link CaseRegisterLogResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseRegisterLogResourceIT {

    private static final String DEFAULT_NUMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGN_USER = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGN_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_ASSIGN_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ASSIGN_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ASSIGNED_USER = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_USER = "BBBBBBBBBB";

    @Autowired
    private CaseRegisterLogRepository caseRegisterLogRepository;

    @Autowired
    private CaseRegisterLogMapper caseRegisterLogMapper;

    @Autowired
    private CaseRegisterLogService caseRegisterLogService;

    @Autowired
    private CaseRegisterLogQueryService caseRegisterLogQueryService;

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

    private MockMvc restCaseRegisterLogMockMvc;

    private CaseRegisterLog caseRegisterLog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseRegisterLogResource caseRegisterLogResource = new CaseRegisterLogResource(caseRegisterLogService, caseRegisterLogQueryService);
        this.restCaseRegisterLogMockMvc = MockMvcBuilders.standaloneSetup(caseRegisterLogResource)
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
    public static CaseRegisterLog createEntity(EntityManager em) {
        CaseRegisterLog caseRegisterLog = new CaseRegisterLog()
            .numberId(DEFAULT_NUMBER_ID)
            .assignUser(DEFAULT_ASSIGN_USER)
            .assignTime(DEFAULT_ASSIGN_TIME)
            .assignedUser(DEFAULT_ASSIGNED_USER);
        return caseRegisterLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseRegisterLog createUpdatedEntity(EntityManager em) {
        CaseRegisterLog caseRegisterLog = new CaseRegisterLog()
            .numberId(UPDATED_NUMBER_ID)
            .assignUser(UPDATED_ASSIGN_USER)
            .assignTime(UPDATED_ASSIGN_TIME)
            .assignedUser(UPDATED_ASSIGNED_USER);
        return caseRegisterLog;
    }

    @BeforeEach
    public void initTest() {
        caseRegisterLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseRegisterLog() throws Exception {
        int databaseSizeBeforeCreate = caseRegisterLogRepository.findAll().size();

        // Create the CaseRegisterLog
        CaseRegisterLogDTO caseRegisterLogDTO = caseRegisterLogMapper.toDto(caseRegisterLog);
        restCaseRegisterLogMockMvc.perform(post("/api/case-register-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseRegisterLogDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseRegisterLog in the database
        List<CaseRegisterLog> caseRegisterLogList = caseRegisterLogRepository.findAll();
        assertThat(caseRegisterLogList).hasSize(databaseSizeBeforeCreate + 1);
        CaseRegisterLog testCaseRegisterLog = caseRegisterLogList.get(caseRegisterLogList.size() - 1);
        assertThat(testCaseRegisterLog.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCaseRegisterLog.getAssignUser()).isEqualTo(DEFAULT_ASSIGN_USER);
        assertThat(testCaseRegisterLog.getAssignTime()).isEqualTo(DEFAULT_ASSIGN_TIME);
        assertThat(testCaseRegisterLog.getAssignedUser()).isEqualTo(DEFAULT_ASSIGNED_USER);
    }

    @Test
    @Transactional
    public void createCaseRegisterLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseRegisterLogRepository.findAll().size();

        // Create the CaseRegisterLog with an existing ID
        caseRegisterLog.setId(1L);
        CaseRegisterLogDTO caseRegisterLogDTO = caseRegisterLogMapper.toDto(caseRegisterLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseRegisterLogMockMvc.perform(post("/api/case-register-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseRegisterLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseRegisterLog in the database
        List<CaseRegisterLog> caseRegisterLogList = caseRegisterLogRepository.findAll();
        assertThat(caseRegisterLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaseRegisterLogs() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList
        restCaseRegisterLogMockMvc.perform(get("/api/case-register-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseRegisterLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID.toString())))
            .andExpect(jsonPath("$.[*].assignUser").value(hasItem(DEFAULT_ASSIGN_USER.toString())))
            .andExpect(jsonPath("$.[*].assignTime").value(hasItem(DEFAULT_ASSIGN_TIME.toString())))
            .andExpect(jsonPath("$.[*].assignedUser").value(hasItem(DEFAULT_ASSIGNED_USER.toString())));
    }
    
    @Test
    @Transactional
    public void getCaseRegisterLog() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get the caseRegisterLog
        restCaseRegisterLogMockMvc.perform(get("/api/case-register-logs/{id}", caseRegisterLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseRegisterLog.getId().intValue()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID.toString()))
            .andExpect(jsonPath("$.assignUser").value(DEFAULT_ASSIGN_USER.toString()))
            .andExpect(jsonPath("$.assignTime").value(DEFAULT_ASSIGN_TIME.toString()))
            .andExpect(jsonPath("$.assignedUser").value(DEFAULT_ASSIGNED_USER.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where numberId equals to DEFAULT_NUMBER_ID
        defaultCaseRegisterLogShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the caseRegisterLogList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseRegisterLogShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCaseRegisterLogShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the caseRegisterLogList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseRegisterLogShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where numberId is not null
        defaultCaseRegisterLogShouldBeFound("numberId.specified=true");

        // Get all the caseRegisterLogList where numberId is null
        defaultCaseRegisterLogShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByAssignUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where assignUser equals to DEFAULT_ASSIGN_USER
        defaultCaseRegisterLogShouldBeFound("assignUser.equals=" + DEFAULT_ASSIGN_USER);

        // Get all the caseRegisterLogList where assignUser equals to UPDATED_ASSIGN_USER
        defaultCaseRegisterLogShouldNotBeFound("assignUser.equals=" + UPDATED_ASSIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByAssignUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where assignUser in DEFAULT_ASSIGN_USER or UPDATED_ASSIGN_USER
        defaultCaseRegisterLogShouldBeFound("assignUser.in=" + DEFAULT_ASSIGN_USER + "," + UPDATED_ASSIGN_USER);

        // Get all the caseRegisterLogList where assignUser equals to UPDATED_ASSIGN_USER
        defaultCaseRegisterLogShouldNotBeFound("assignUser.in=" + UPDATED_ASSIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByAssignUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where assignUser is not null
        defaultCaseRegisterLogShouldBeFound("assignUser.specified=true");

        // Get all the caseRegisterLogList where assignUser is null
        defaultCaseRegisterLogShouldNotBeFound("assignUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByAssignTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where assignTime equals to DEFAULT_ASSIGN_TIME
        defaultCaseRegisterLogShouldBeFound("assignTime.equals=" + DEFAULT_ASSIGN_TIME);

        // Get all the caseRegisterLogList where assignTime equals to UPDATED_ASSIGN_TIME
        defaultCaseRegisterLogShouldNotBeFound("assignTime.equals=" + UPDATED_ASSIGN_TIME);
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByAssignTimeIsInShouldWork() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where assignTime in DEFAULT_ASSIGN_TIME or UPDATED_ASSIGN_TIME
        defaultCaseRegisterLogShouldBeFound("assignTime.in=" + DEFAULT_ASSIGN_TIME + "," + UPDATED_ASSIGN_TIME);

        // Get all the caseRegisterLogList where assignTime equals to UPDATED_ASSIGN_TIME
        defaultCaseRegisterLogShouldNotBeFound("assignTime.in=" + UPDATED_ASSIGN_TIME);
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByAssignTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where assignTime is not null
        defaultCaseRegisterLogShouldBeFound("assignTime.specified=true");

        // Get all the caseRegisterLogList where assignTime is null
        defaultCaseRegisterLogShouldNotBeFound("assignTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByAssignedUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where assignedUser equals to DEFAULT_ASSIGNED_USER
        defaultCaseRegisterLogShouldBeFound("assignedUser.equals=" + DEFAULT_ASSIGNED_USER);

        // Get all the caseRegisterLogList where assignedUser equals to UPDATED_ASSIGNED_USER
        defaultCaseRegisterLogShouldNotBeFound("assignedUser.equals=" + UPDATED_ASSIGNED_USER);
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByAssignedUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where assignedUser in DEFAULT_ASSIGNED_USER or UPDATED_ASSIGNED_USER
        defaultCaseRegisterLogShouldBeFound("assignedUser.in=" + DEFAULT_ASSIGNED_USER + "," + UPDATED_ASSIGNED_USER);

        // Get all the caseRegisterLogList where assignedUser equals to UPDATED_ASSIGNED_USER
        defaultCaseRegisterLogShouldNotBeFound("assignedUser.in=" + UPDATED_ASSIGNED_USER);
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByAssignedUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        // Get all the caseRegisterLogList where assignedUser is not null
        defaultCaseRegisterLogShouldBeFound("assignedUser.specified=true");

        // Get all the caseRegisterLogList where assignedUser is null
        defaultCaseRegisterLogShouldNotBeFound("assignedUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseRegisterLogsByVesselCaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselCase vesselCase = VesselCaseResourceIT.createEntity(em);
        em.persist(vesselCase);
        em.flush();
        caseRegisterLog.setVesselCase(vesselCase);
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);
        Long vesselCaseId = vesselCase.getId();

        // Get all the caseRegisterLogList where vesselCase equals to vesselCaseId
        defaultCaseRegisterLogShouldBeFound("vesselCaseId.equals=" + vesselCaseId);

        // Get all the caseRegisterLogList where vesselCase equals to vesselCaseId + 1
        defaultCaseRegisterLogShouldNotBeFound("vesselCaseId.equals=" + (vesselCaseId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCaseRegisterLogShouldBeFound(String filter) throws Exception {
        restCaseRegisterLogMockMvc.perform(get("/api/case-register-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseRegisterLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].assignUser").value(hasItem(DEFAULT_ASSIGN_USER)))
            .andExpect(jsonPath("$.[*].assignTime").value(hasItem(DEFAULT_ASSIGN_TIME.toString())))
            .andExpect(jsonPath("$.[*].assignedUser").value(hasItem(DEFAULT_ASSIGNED_USER)));

        // Check, that the count call also returns 1
        restCaseRegisterLogMockMvc.perform(get("/api/case-register-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCaseRegisterLogShouldNotBeFound(String filter) throws Exception {
        restCaseRegisterLogMockMvc.perform(get("/api/case-register-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCaseRegisterLogMockMvc.perform(get("/api/case-register-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCaseRegisterLog() throws Exception {
        // Get the caseRegisterLog
        restCaseRegisterLogMockMvc.perform(get("/api/case-register-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseRegisterLog() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        int databaseSizeBeforeUpdate = caseRegisterLogRepository.findAll().size();

        // Update the caseRegisterLog
        CaseRegisterLog updatedCaseRegisterLog = caseRegisterLogRepository.findById(caseRegisterLog.getId()).get();
        // Disconnect from session so that the updates on updatedCaseRegisterLog are not directly saved in db
        em.detach(updatedCaseRegisterLog);
        updatedCaseRegisterLog
            .numberId(UPDATED_NUMBER_ID)
            .assignUser(UPDATED_ASSIGN_USER)
            .assignTime(UPDATED_ASSIGN_TIME)
            .assignedUser(UPDATED_ASSIGNED_USER);
        CaseRegisterLogDTO caseRegisterLogDTO = caseRegisterLogMapper.toDto(updatedCaseRegisterLog);

        restCaseRegisterLogMockMvc.perform(put("/api/case-register-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseRegisterLogDTO)))
            .andExpect(status().isOk());

        // Validate the CaseRegisterLog in the database
        List<CaseRegisterLog> caseRegisterLogList = caseRegisterLogRepository.findAll();
        assertThat(caseRegisterLogList).hasSize(databaseSizeBeforeUpdate);
        CaseRegisterLog testCaseRegisterLog = caseRegisterLogList.get(caseRegisterLogList.size() - 1);
        assertThat(testCaseRegisterLog.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCaseRegisterLog.getAssignUser()).isEqualTo(UPDATED_ASSIGN_USER);
        assertThat(testCaseRegisterLog.getAssignTime()).isEqualTo(UPDATED_ASSIGN_TIME);
        assertThat(testCaseRegisterLog.getAssignedUser()).isEqualTo(UPDATED_ASSIGNED_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseRegisterLog() throws Exception {
        int databaseSizeBeforeUpdate = caseRegisterLogRepository.findAll().size();

        // Create the CaseRegisterLog
        CaseRegisterLogDTO caseRegisterLogDTO = caseRegisterLogMapper.toDto(caseRegisterLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseRegisterLogMockMvc.perform(put("/api/case-register-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseRegisterLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseRegisterLog in the database
        List<CaseRegisterLog> caseRegisterLogList = caseRegisterLogRepository.findAll();
        assertThat(caseRegisterLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseRegisterLog() throws Exception {
        // Initialize the database
        caseRegisterLogRepository.saveAndFlush(caseRegisterLog);

        int databaseSizeBeforeDelete = caseRegisterLogRepository.findAll().size();

        // Delete the caseRegisterLog
        restCaseRegisterLogMockMvc.perform(delete("/api/case-register-logs/{id}", caseRegisterLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CaseRegisterLog> caseRegisterLogList = caseRegisterLogRepository.findAll();
        assertThat(caseRegisterLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseRegisterLog.class);
        CaseRegisterLog caseRegisterLog1 = new CaseRegisterLog();
        caseRegisterLog1.setId(1L);
        CaseRegisterLog caseRegisterLog2 = new CaseRegisterLog();
        caseRegisterLog2.setId(caseRegisterLog1.getId());
        assertThat(caseRegisterLog1).isEqualTo(caseRegisterLog2);
        caseRegisterLog2.setId(2L);
        assertThat(caseRegisterLog1).isNotEqualTo(caseRegisterLog2);
        caseRegisterLog1.setId(null);
        assertThat(caseRegisterLog1).isNotEqualTo(caseRegisterLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseRegisterLogDTO.class);
        CaseRegisterLogDTO caseRegisterLogDTO1 = new CaseRegisterLogDTO();
        caseRegisterLogDTO1.setId(1L);
        CaseRegisterLogDTO caseRegisterLogDTO2 = new CaseRegisterLogDTO();
        assertThat(caseRegisterLogDTO1).isNotEqualTo(caseRegisterLogDTO2);
        caseRegisterLogDTO2.setId(caseRegisterLogDTO1.getId());
        assertThat(caseRegisterLogDTO1).isEqualTo(caseRegisterLogDTO2);
        caseRegisterLogDTO2.setId(2L);
        assertThat(caseRegisterLogDTO1).isNotEqualTo(caseRegisterLogDTO2);
        caseRegisterLogDTO1.setId(null);
        assertThat(caseRegisterLogDTO1).isNotEqualTo(caseRegisterLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseRegisterLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseRegisterLogMapper.fromId(null)).isNull();
    }
}
