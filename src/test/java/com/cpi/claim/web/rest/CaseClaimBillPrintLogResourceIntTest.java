package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CaseClaimBillPrintLog;
import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.repository.CaseClaimBillPrintLogRepository;
import com.cpi.claim.service.CaseClaimBillPrintLogService;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillPrintLogMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogCriteria;
import com.cpi.claim.service.CaseClaimBillPrintLogQueryService;

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
 * Test class for the CaseClaimBillPrintLogResource REST controller.
 *
 * @see CaseClaimBillPrintLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseClaimBillPrintLogResourceIntTest {

    private static final String DEFAULT_OPERATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_OPERATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_OPERATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OPERATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private CaseClaimBillPrintLogRepository caseClaimBillPrintLogRepository;


    @Autowired
    private CaseClaimBillPrintLogMapper caseClaimBillPrintLogMapper;
    

    @Autowired
    private CaseClaimBillPrintLogService caseClaimBillPrintLogService;

    @Autowired
    private CaseClaimBillPrintLogQueryService caseClaimBillPrintLogQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaseClaimBillPrintLogMockMvc;

    private CaseClaimBillPrintLog caseClaimBillPrintLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseClaimBillPrintLogResource caseClaimBillPrintLogResource = new CaseClaimBillPrintLogResource(caseClaimBillPrintLogService, caseClaimBillPrintLogQueryService);
        this.restCaseClaimBillPrintLogMockMvc = MockMvcBuilders.standaloneSetup(caseClaimBillPrintLogResource)
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
    public static CaseClaimBillPrintLog createEntity(EntityManager em) {
        CaseClaimBillPrintLog caseClaimBillPrintLog = new CaseClaimBillPrintLog()
            .operateType(DEFAULT_OPERATE_TYPE)
            .operateUser(DEFAULT_OPERATE_USER)
            .operateDate(DEFAULT_OPERATE_DATE);
        return caseClaimBillPrintLog;
    }

    @Before
    public void initTest() {
        caseClaimBillPrintLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseClaimBillPrintLog() throws Exception {
        int databaseSizeBeforeCreate = caseClaimBillPrintLogRepository.findAll().size();

        // Create the CaseClaimBillPrintLog
        CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO = caseClaimBillPrintLogMapper.toDto(caseClaimBillPrintLog);
        restCaseClaimBillPrintLogMockMvc.perform(post("/api/case-claim-bill-print-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillPrintLogDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseClaimBillPrintLog in the database
        List<CaseClaimBillPrintLog> caseClaimBillPrintLogList = caseClaimBillPrintLogRepository.findAll();
        assertThat(caseClaimBillPrintLogList).hasSize(databaseSizeBeforeCreate + 1);
        CaseClaimBillPrintLog testCaseClaimBillPrintLog = caseClaimBillPrintLogList.get(caseClaimBillPrintLogList.size() - 1);
        assertThat(testCaseClaimBillPrintLog.getOperateType()).isEqualTo(DEFAULT_OPERATE_TYPE);
        assertThat(testCaseClaimBillPrintLog.getOperateUser()).isEqualTo(DEFAULT_OPERATE_USER);
        assertThat(testCaseClaimBillPrintLog.getOperateDate()).isEqualTo(DEFAULT_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void createCaseClaimBillPrintLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseClaimBillPrintLogRepository.findAll().size();

        // Create the CaseClaimBillPrintLog with an existing ID
        caseClaimBillPrintLog.setId(1L);
        CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO = caseClaimBillPrintLogMapper.toDto(caseClaimBillPrintLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseClaimBillPrintLogMockMvc.perform(post("/api/case-claim-bill-print-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillPrintLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaimBillPrintLog in the database
        List<CaseClaimBillPrintLog> caseClaimBillPrintLogList = caseClaimBillPrintLogRepository.findAll();
        assertThat(caseClaimBillPrintLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogs() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList
        restCaseClaimBillPrintLogMockMvc.perform(get("/api/case-claim-bill-print-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaimBillPrintLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].operateType").value(hasItem(DEFAULT_OPERATE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].operateUser").value(hasItem(DEFAULT_OPERATE_USER.toString())))
            .andExpect(jsonPath("$.[*].operateDate").value(hasItem(DEFAULT_OPERATE_DATE.toString())));
    }
    

    @Test
    @Transactional
    public void getCaseClaimBillPrintLog() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get the caseClaimBillPrintLog
        restCaseClaimBillPrintLogMockMvc.perform(get("/api/case-claim-bill-print-logs/{id}", caseClaimBillPrintLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseClaimBillPrintLog.getId().intValue()))
            .andExpect(jsonPath("$.operateType").value(DEFAULT_OPERATE_TYPE.toString()))
            .andExpect(jsonPath("$.operateUser").value(DEFAULT_OPERATE_USER.toString()))
            .andExpect(jsonPath("$.operateDate").value(DEFAULT_OPERATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByOperateTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList where operateType equals to DEFAULT_OPERATE_TYPE
        defaultCaseClaimBillPrintLogShouldBeFound("operateType.equals=" + DEFAULT_OPERATE_TYPE);

        // Get all the caseClaimBillPrintLogList where operateType equals to UPDATED_OPERATE_TYPE
        defaultCaseClaimBillPrintLogShouldNotBeFound("operateType.equals=" + UPDATED_OPERATE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByOperateTypeIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList where operateType in DEFAULT_OPERATE_TYPE or UPDATED_OPERATE_TYPE
        defaultCaseClaimBillPrintLogShouldBeFound("operateType.in=" + DEFAULT_OPERATE_TYPE + "," + UPDATED_OPERATE_TYPE);

        // Get all the caseClaimBillPrintLogList where operateType equals to UPDATED_OPERATE_TYPE
        defaultCaseClaimBillPrintLogShouldNotBeFound("operateType.in=" + UPDATED_OPERATE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByOperateTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList where operateType is not null
        defaultCaseClaimBillPrintLogShouldBeFound("operateType.specified=true");

        // Get all the caseClaimBillPrintLogList where operateType is null
        defaultCaseClaimBillPrintLogShouldNotBeFound("operateType.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByOperateUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList where operateUser equals to DEFAULT_OPERATE_USER
        defaultCaseClaimBillPrintLogShouldBeFound("operateUser.equals=" + DEFAULT_OPERATE_USER);

        // Get all the caseClaimBillPrintLogList where operateUser equals to UPDATED_OPERATE_USER
        defaultCaseClaimBillPrintLogShouldNotBeFound("operateUser.equals=" + UPDATED_OPERATE_USER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByOperateUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList where operateUser in DEFAULT_OPERATE_USER or UPDATED_OPERATE_USER
        defaultCaseClaimBillPrintLogShouldBeFound("operateUser.in=" + DEFAULT_OPERATE_USER + "," + UPDATED_OPERATE_USER);

        // Get all the caseClaimBillPrintLogList where operateUser equals to UPDATED_OPERATE_USER
        defaultCaseClaimBillPrintLogShouldNotBeFound("operateUser.in=" + UPDATED_OPERATE_USER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByOperateUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList where operateUser is not null
        defaultCaseClaimBillPrintLogShouldBeFound("operateUser.specified=true");

        // Get all the caseClaimBillPrintLogList where operateUser is null
        defaultCaseClaimBillPrintLogShouldNotBeFound("operateUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByOperateDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList where operateDate equals to DEFAULT_OPERATE_DATE
        defaultCaseClaimBillPrintLogShouldBeFound("operateDate.equals=" + DEFAULT_OPERATE_DATE);

        // Get all the caseClaimBillPrintLogList where operateDate equals to UPDATED_OPERATE_DATE
        defaultCaseClaimBillPrintLogShouldNotBeFound("operateDate.equals=" + UPDATED_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByOperateDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList where operateDate in DEFAULT_OPERATE_DATE or UPDATED_OPERATE_DATE
        defaultCaseClaimBillPrintLogShouldBeFound("operateDate.in=" + DEFAULT_OPERATE_DATE + "," + UPDATED_OPERATE_DATE);

        // Get all the caseClaimBillPrintLogList where operateDate equals to UPDATED_OPERATE_DATE
        defaultCaseClaimBillPrintLogShouldNotBeFound("operateDate.in=" + UPDATED_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByOperateDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        // Get all the caseClaimBillPrintLogList where operateDate is not null
        defaultCaseClaimBillPrintLogShouldBeFound("operateDate.specified=true");

        // Get all the caseClaimBillPrintLogList where operateDate is null
        defaultCaseClaimBillPrintLogShouldNotBeFound("operateDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillPrintLogsByCaseClaimBillIsEqualToSomething() throws Exception {
        // Initialize the database
        CaseClaimBill caseClaimBill = CaseClaimBillResourceIntTest.createEntity(em);
        em.persist(caseClaimBill);
        em.flush();
        caseClaimBillPrintLog.setCaseClaimBill(caseClaimBill);
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);
        Long caseClaimBillId = caseClaimBill.getId();

        // Get all the caseClaimBillPrintLogList where caseClaimBill equals to caseClaimBillId
        defaultCaseClaimBillPrintLogShouldBeFound("caseClaimBillId.equals=" + caseClaimBillId);

        // Get all the caseClaimBillPrintLogList where caseClaimBill equals to caseClaimBillId + 1
        defaultCaseClaimBillPrintLogShouldNotBeFound("caseClaimBillId.equals=" + (caseClaimBillId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCaseClaimBillPrintLogShouldBeFound(String filter) throws Exception {
        restCaseClaimBillPrintLogMockMvc.perform(get("/api/case-claim-bill-print-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaimBillPrintLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].operateType").value(hasItem(DEFAULT_OPERATE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].operateUser").value(hasItem(DEFAULT_OPERATE_USER.toString())))
            .andExpect(jsonPath("$.[*].operateDate").value(hasItem(DEFAULT_OPERATE_DATE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCaseClaimBillPrintLogShouldNotBeFound(String filter) throws Exception {
        restCaseClaimBillPrintLogMockMvc.perform(get("/api/case-claim-bill-print-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCaseClaimBillPrintLog() throws Exception {
        // Get the caseClaimBillPrintLog
        restCaseClaimBillPrintLogMockMvc.perform(get("/api/case-claim-bill-print-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseClaimBillPrintLog() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        int databaseSizeBeforeUpdate = caseClaimBillPrintLogRepository.findAll().size();

        // Update the caseClaimBillPrintLog
        CaseClaimBillPrintLog updatedCaseClaimBillPrintLog = caseClaimBillPrintLogRepository.findById(caseClaimBillPrintLog.getId()).get();
        // Disconnect from session so that the updates on updatedCaseClaimBillPrintLog are not directly saved in db
        em.detach(updatedCaseClaimBillPrintLog);
        updatedCaseClaimBillPrintLog
            .operateType(UPDATED_OPERATE_TYPE)
            .operateUser(UPDATED_OPERATE_USER)
            .operateDate(UPDATED_OPERATE_DATE);
        CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO = caseClaimBillPrintLogMapper.toDto(updatedCaseClaimBillPrintLog);

        restCaseClaimBillPrintLogMockMvc.perform(put("/api/case-claim-bill-print-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillPrintLogDTO)))
            .andExpect(status().isOk());

        // Validate the CaseClaimBillPrintLog in the database
        List<CaseClaimBillPrintLog> caseClaimBillPrintLogList = caseClaimBillPrintLogRepository.findAll();
        assertThat(caseClaimBillPrintLogList).hasSize(databaseSizeBeforeUpdate);
        CaseClaimBillPrintLog testCaseClaimBillPrintLog = caseClaimBillPrintLogList.get(caseClaimBillPrintLogList.size() - 1);
        assertThat(testCaseClaimBillPrintLog.getOperateType()).isEqualTo(UPDATED_OPERATE_TYPE);
        assertThat(testCaseClaimBillPrintLog.getOperateUser()).isEqualTo(UPDATED_OPERATE_USER);
        assertThat(testCaseClaimBillPrintLog.getOperateDate()).isEqualTo(UPDATED_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseClaimBillPrintLog() throws Exception {
        int databaseSizeBeforeUpdate = caseClaimBillPrintLogRepository.findAll().size();

        // Create the CaseClaimBillPrintLog
        CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO = caseClaimBillPrintLogMapper.toDto(caseClaimBillPrintLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCaseClaimBillPrintLogMockMvc.perform(put("/api/case-claim-bill-print-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillPrintLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaimBillPrintLog in the database
        List<CaseClaimBillPrintLog> caseClaimBillPrintLogList = caseClaimBillPrintLogRepository.findAll();
        assertThat(caseClaimBillPrintLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseClaimBillPrintLog() throws Exception {
        // Initialize the database
        caseClaimBillPrintLogRepository.saveAndFlush(caseClaimBillPrintLog);

        int databaseSizeBeforeDelete = caseClaimBillPrintLogRepository.findAll().size();

        // Get the caseClaimBillPrintLog
        restCaseClaimBillPrintLogMockMvc.perform(delete("/api/case-claim-bill-print-logs/{id}", caseClaimBillPrintLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseClaimBillPrintLog> caseClaimBillPrintLogList = caseClaimBillPrintLogRepository.findAll();
        assertThat(caseClaimBillPrintLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaimBillPrintLog.class);
        CaseClaimBillPrintLog caseClaimBillPrintLog1 = new CaseClaimBillPrintLog();
        caseClaimBillPrintLog1.setId(1L);
        CaseClaimBillPrintLog caseClaimBillPrintLog2 = new CaseClaimBillPrintLog();
        caseClaimBillPrintLog2.setId(caseClaimBillPrintLog1.getId());
        assertThat(caseClaimBillPrintLog1).isEqualTo(caseClaimBillPrintLog2);
        caseClaimBillPrintLog2.setId(2L);
        assertThat(caseClaimBillPrintLog1).isNotEqualTo(caseClaimBillPrintLog2);
        caseClaimBillPrintLog1.setId(null);
        assertThat(caseClaimBillPrintLog1).isNotEqualTo(caseClaimBillPrintLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaimBillPrintLogDTO.class);
        CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO1 = new CaseClaimBillPrintLogDTO();
        caseClaimBillPrintLogDTO1.setId(1L);
        CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO2 = new CaseClaimBillPrintLogDTO();
        assertThat(caseClaimBillPrintLogDTO1).isNotEqualTo(caseClaimBillPrintLogDTO2);
        caseClaimBillPrintLogDTO2.setId(caseClaimBillPrintLogDTO1.getId());
        assertThat(caseClaimBillPrintLogDTO1).isEqualTo(caseClaimBillPrintLogDTO2);
        caseClaimBillPrintLogDTO2.setId(2L);
        assertThat(caseClaimBillPrintLogDTO1).isNotEqualTo(caseClaimBillPrintLogDTO2);
        caseClaimBillPrintLogDTO1.setId(null);
        assertThat(caseClaimBillPrintLogDTO1).isNotEqualTo(caseClaimBillPrintLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseClaimBillPrintLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseClaimBillPrintLogMapper.fromId(null)).isNull();
    }
}
