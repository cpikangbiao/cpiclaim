/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.CaseClaimBillApprovalLog;
import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.repository.CaseClaimBillApprovalLogRepository;
import com.cpi.claim.service.CaseClaimBillApprovalLogService;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillApprovalLogMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogCriteria;
import com.cpi.claim.service.CaseClaimBillApprovalLogQueryService;

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
 * Integration tests for the {@Link CaseClaimBillApprovalLogResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseClaimBillApprovalLogResourceIT {

    private static final Long DEFAULT_PROCESS_ID = 1L;
    private static final Long UPDATED_PROCESS_ID = 2L;

    private static final Instant DEFAULT_INSERT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSERT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_APPROVAL_USER = "AAAAAAAAAA";
    private static final String UPDATED_APPROVAL_USER = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVAL_OPINION = "AAAAAAAAAA";
    private static final String UPDATED_APPROVAL_OPINION = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVAL_TRANSITION = "AAAAAAAAAA";
    private static final String UPDATED_APPROVAL_TRANSITION = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private CaseClaimBillApprovalLogRepository caseClaimBillApprovalLogRepository;

    @Autowired
    private CaseClaimBillApprovalLogMapper caseClaimBillApprovalLogMapper;

    @Autowired
    private CaseClaimBillApprovalLogService caseClaimBillApprovalLogService;

    @Autowired
    private CaseClaimBillApprovalLogQueryService caseClaimBillApprovalLogQueryService;

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

    private MockMvc restCaseClaimBillApprovalLogMockMvc;

    private CaseClaimBillApprovalLog caseClaimBillApprovalLog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseClaimBillApprovalLogResource caseClaimBillApprovalLogResource = new CaseClaimBillApprovalLogResource(caseClaimBillApprovalLogService, caseClaimBillApprovalLogQueryService);
        this.restCaseClaimBillApprovalLogMockMvc = MockMvcBuilders.standaloneSetup(caseClaimBillApprovalLogResource)
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
    public static CaseClaimBillApprovalLog createEntity(EntityManager em) {
        CaseClaimBillApprovalLog caseClaimBillApprovalLog = new CaseClaimBillApprovalLog()
            .processId(DEFAULT_PROCESS_ID)
            .insertTime(DEFAULT_INSERT_TIME)
            .approvalUser(DEFAULT_APPROVAL_USER)
            .approvalOpinion(DEFAULT_APPROVAL_OPINION)
            .approvalTransition(DEFAULT_APPROVAL_TRANSITION)
            .remark(DEFAULT_REMARK);
        return caseClaimBillApprovalLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseClaimBillApprovalLog createUpdatedEntity(EntityManager em) {
        CaseClaimBillApprovalLog caseClaimBillApprovalLog = new CaseClaimBillApprovalLog()
            .processId(UPDATED_PROCESS_ID)
            .insertTime(UPDATED_INSERT_TIME)
            .approvalUser(UPDATED_APPROVAL_USER)
            .approvalOpinion(UPDATED_APPROVAL_OPINION)
            .approvalTransition(UPDATED_APPROVAL_TRANSITION)
            .remark(UPDATED_REMARK);
        return caseClaimBillApprovalLog;
    }

    @BeforeEach
    public void initTest() {
        caseClaimBillApprovalLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseClaimBillApprovalLog() throws Exception {
        int databaseSizeBeforeCreate = caseClaimBillApprovalLogRepository.findAll().size();

        // Create the CaseClaimBillApprovalLog
        CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO = caseClaimBillApprovalLogMapper.toDto(caseClaimBillApprovalLog);
        restCaseClaimBillApprovalLogMockMvc.perform(post("/api/case-claim-bill-approval-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillApprovalLogDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseClaimBillApprovalLog in the database
        List<CaseClaimBillApprovalLog> caseClaimBillApprovalLogList = caseClaimBillApprovalLogRepository.findAll();
        assertThat(caseClaimBillApprovalLogList).hasSize(databaseSizeBeforeCreate + 1);
        CaseClaimBillApprovalLog testCaseClaimBillApprovalLog = caseClaimBillApprovalLogList.get(caseClaimBillApprovalLogList.size() - 1);
        assertThat(testCaseClaimBillApprovalLog.getProcessId()).isEqualTo(DEFAULT_PROCESS_ID);
        assertThat(testCaseClaimBillApprovalLog.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testCaseClaimBillApprovalLog.getApprovalUser()).isEqualTo(DEFAULT_APPROVAL_USER);
        assertThat(testCaseClaimBillApprovalLog.getApprovalOpinion()).isEqualTo(DEFAULT_APPROVAL_OPINION);
        assertThat(testCaseClaimBillApprovalLog.getApprovalTransition()).isEqualTo(DEFAULT_APPROVAL_TRANSITION);
        assertThat(testCaseClaimBillApprovalLog.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createCaseClaimBillApprovalLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseClaimBillApprovalLogRepository.findAll().size();

        // Create the CaseClaimBillApprovalLog with an existing ID
        caseClaimBillApprovalLog.setId(1L);
        CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO = caseClaimBillApprovalLogMapper.toDto(caseClaimBillApprovalLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseClaimBillApprovalLogMockMvc.perform(post("/api/case-claim-bill-approval-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillApprovalLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaimBillApprovalLog in the database
        List<CaseClaimBillApprovalLog> caseClaimBillApprovalLogList = caseClaimBillApprovalLogRepository.findAll();
        assertThat(caseClaimBillApprovalLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogs() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList
        restCaseClaimBillApprovalLogMockMvc.perform(get("/api/case-claim-bill-approval-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaimBillApprovalLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].processId").value(hasItem(DEFAULT_PROCESS_ID.intValue())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].approvalUser").value(hasItem(DEFAULT_APPROVAL_USER.toString())))
            .andExpect(jsonPath("$.[*].approvalOpinion").value(hasItem(DEFAULT_APPROVAL_OPINION.toString())))
            .andExpect(jsonPath("$.[*].approvalTransition").value(hasItem(DEFAULT_APPROVAL_TRANSITION.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getCaseClaimBillApprovalLog() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get the caseClaimBillApprovalLog
        restCaseClaimBillApprovalLogMockMvc.perform(get("/api/case-claim-bill-approval-logs/{id}", caseClaimBillApprovalLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseClaimBillApprovalLog.getId().intValue()))
            .andExpect(jsonPath("$.processId").value(DEFAULT_PROCESS_ID.intValue()))
            .andExpect(jsonPath("$.insertTime").value(DEFAULT_INSERT_TIME.toString()))
            .andExpect(jsonPath("$.approvalUser").value(DEFAULT_APPROVAL_USER.toString()))
            .andExpect(jsonPath("$.approvalOpinion").value(DEFAULT_APPROVAL_OPINION.toString()))
            .andExpect(jsonPath("$.approvalTransition").value(DEFAULT_APPROVAL_TRANSITION.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByProcessIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where processId equals to DEFAULT_PROCESS_ID
        defaultCaseClaimBillApprovalLogShouldBeFound("processId.equals=" + DEFAULT_PROCESS_ID);

        // Get all the caseClaimBillApprovalLogList where processId equals to UPDATED_PROCESS_ID
        defaultCaseClaimBillApprovalLogShouldNotBeFound("processId.equals=" + UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByProcessIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where processId in DEFAULT_PROCESS_ID or UPDATED_PROCESS_ID
        defaultCaseClaimBillApprovalLogShouldBeFound("processId.in=" + DEFAULT_PROCESS_ID + "," + UPDATED_PROCESS_ID);

        // Get all the caseClaimBillApprovalLogList where processId equals to UPDATED_PROCESS_ID
        defaultCaseClaimBillApprovalLogShouldNotBeFound("processId.in=" + UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByProcessIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where processId is not null
        defaultCaseClaimBillApprovalLogShouldBeFound("processId.specified=true");

        // Get all the caseClaimBillApprovalLogList where processId is null
        defaultCaseClaimBillApprovalLogShouldNotBeFound("processId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByProcessIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where processId greater than or equals to DEFAULT_PROCESS_ID
        defaultCaseClaimBillApprovalLogShouldBeFound("processId.greaterOrEqualThan=" + DEFAULT_PROCESS_ID);

        // Get all the caseClaimBillApprovalLogList where processId greater than or equals to UPDATED_PROCESS_ID
        defaultCaseClaimBillApprovalLogShouldNotBeFound("processId.greaterOrEqualThan=" + UPDATED_PROCESS_ID);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByProcessIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where processId less than or equals to DEFAULT_PROCESS_ID
        defaultCaseClaimBillApprovalLogShouldNotBeFound("processId.lessThan=" + DEFAULT_PROCESS_ID);

        // Get all the caseClaimBillApprovalLogList where processId less than or equals to UPDATED_PROCESS_ID
        defaultCaseClaimBillApprovalLogShouldBeFound("processId.lessThan=" + UPDATED_PROCESS_ID);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where insertTime equals to DEFAULT_INSERT_TIME
        defaultCaseClaimBillApprovalLogShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the caseClaimBillApprovalLogList where insertTime equals to UPDATED_INSERT_TIME
        defaultCaseClaimBillApprovalLogShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultCaseClaimBillApprovalLogShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the caseClaimBillApprovalLogList where insertTime equals to UPDATED_INSERT_TIME
        defaultCaseClaimBillApprovalLogShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where insertTime is not null
        defaultCaseClaimBillApprovalLogShouldBeFound("insertTime.specified=true");

        // Get all the caseClaimBillApprovalLogList where insertTime is null
        defaultCaseClaimBillApprovalLogShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByApprovalUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where approvalUser equals to DEFAULT_APPROVAL_USER
        defaultCaseClaimBillApprovalLogShouldBeFound("approvalUser.equals=" + DEFAULT_APPROVAL_USER);

        // Get all the caseClaimBillApprovalLogList where approvalUser equals to UPDATED_APPROVAL_USER
        defaultCaseClaimBillApprovalLogShouldNotBeFound("approvalUser.equals=" + UPDATED_APPROVAL_USER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByApprovalUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where approvalUser in DEFAULT_APPROVAL_USER or UPDATED_APPROVAL_USER
        defaultCaseClaimBillApprovalLogShouldBeFound("approvalUser.in=" + DEFAULT_APPROVAL_USER + "," + UPDATED_APPROVAL_USER);

        // Get all the caseClaimBillApprovalLogList where approvalUser equals to UPDATED_APPROVAL_USER
        defaultCaseClaimBillApprovalLogShouldNotBeFound("approvalUser.in=" + UPDATED_APPROVAL_USER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByApprovalUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where approvalUser is not null
        defaultCaseClaimBillApprovalLogShouldBeFound("approvalUser.specified=true");

        // Get all the caseClaimBillApprovalLogList where approvalUser is null
        defaultCaseClaimBillApprovalLogShouldNotBeFound("approvalUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByApprovalOpinionIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where approvalOpinion equals to DEFAULT_APPROVAL_OPINION
        defaultCaseClaimBillApprovalLogShouldBeFound("approvalOpinion.equals=" + DEFAULT_APPROVAL_OPINION);

        // Get all the caseClaimBillApprovalLogList where approvalOpinion equals to UPDATED_APPROVAL_OPINION
        defaultCaseClaimBillApprovalLogShouldNotBeFound("approvalOpinion.equals=" + UPDATED_APPROVAL_OPINION);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByApprovalOpinionIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where approvalOpinion in DEFAULT_APPROVAL_OPINION or UPDATED_APPROVAL_OPINION
        defaultCaseClaimBillApprovalLogShouldBeFound("approvalOpinion.in=" + DEFAULT_APPROVAL_OPINION + "," + UPDATED_APPROVAL_OPINION);

        // Get all the caseClaimBillApprovalLogList where approvalOpinion equals to UPDATED_APPROVAL_OPINION
        defaultCaseClaimBillApprovalLogShouldNotBeFound("approvalOpinion.in=" + UPDATED_APPROVAL_OPINION);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByApprovalOpinionIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where approvalOpinion is not null
        defaultCaseClaimBillApprovalLogShouldBeFound("approvalOpinion.specified=true");

        // Get all the caseClaimBillApprovalLogList where approvalOpinion is null
        defaultCaseClaimBillApprovalLogShouldNotBeFound("approvalOpinion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByApprovalTransitionIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where approvalTransition equals to DEFAULT_APPROVAL_TRANSITION
        defaultCaseClaimBillApprovalLogShouldBeFound("approvalTransition.equals=" + DEFAULT_APPROVAL_TRANSITION);

        // Get all the caseClaimBillApprovalLogList where approvalTransition equals to UPDATED_APPROVAL_TRANSITION
        defaultCaseClaimBillApprovalLogShouldNotBeFound("approvalTransition.equals=" + UPDATED_APPROVAL_TRANSITION);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByApprovalTransitionIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where approvalTransition in DEFAULT_APPROVAL_TRANSITION or UPDATED_APPROVAL_TRANSITION
        defaultCaseClaimBillApprovalLogShouldBeFound("approvalTransition.in=" + DEFAULT_APPROVAL_TRANSITION + "," + UPDATED_APPROVAL_TRANSITION);

        // Get all the caseClaimBillApprovalLogList where approvalTransition equals to UPDATED_APPROVAL_TRANSITION
        defaultCaseClaimBillApprovalLogShouldNotBeFound("approvalTransition.in=" + UPDATED_APPROVAL_TRANSITION);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByApprovalTransitionIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where approvalTransition is not null
        defaultCaseClaimBillApprovalLogShouldBeFound("approvalTransition.specified=true");

        // Get all the caseClaimBillApprovalLogList where approvalTransition is null
        defaultCaseClaimBillApprovalLogShouldNotBeFound("approvalTransition.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByRemarkIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where remark equals to DEFAULT_REMARK
        defaultCaseClaimBillApprovalLogShouldBeFound("remark.equals=" + DEFAULT_REMARK);

        // Get all the caseClaimBillApprovalLogList where remark equals to UPDATED_REMARK
        defaultCaseClaimBillApprovalLogShouldNotBeFound("remark.equals=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByRemarkIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where remark in DEFAULT_REMARK or UPDATED_REMARK
        defaultCaseClaimBillApprovalLogShouldBeFound("remark.in=" + DEFAULT_REMARK + "," + UPDATED_REMARK);

        // Get all the caseClaimBillApprovalLogList where remark equals to UPDATED_REMARK
        defaultCaseClaimBillApprovalLogShouldNotBeFound("remark.in=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByRemarkIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        // Get all the caseClaimBillApprovalLogList where remark is not null
        defaultCaseClaimBillApprovalLogShouldBeFound("remark.specified=true");

        // Get all the caseClaimBillApprovalLogList where remark is null
        defaultCaseClaimBillApprovalLogShouldNotBeFound("remark.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillApprovalLogsByCaseClaimBillIsEqualToSomething() throws Exception {
        // Initialize the database
        CaseClaimBill caseClaimBill = CaseClaimBillResourceIT.createEntity(em);
        em.persist(caseClaimBill);
        em.flush();
        caseClaimBillApprovalLog.setCaseClaimBill(caseClaimBill);
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);
        Long caseClaimBillId = caseClaimBill.getId();

        // Get all the caseClaimBillApprovalLogList where caseClaimBill equals to caseClaimBillId
        defaultCaseClaimBillApprovalLogShouldBeFound("caseClaimBillId.equals=" + caseClaimBillId);

        // Get all the caseClaimBillApprovalLogList where caseClaimBill equals to caseClaimBillId + 1
        defaultCaseClaimBillApprovalLogShouldNotBeFound("caseClaimBillId.equals=" + (caseClaimBillId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCaseClaimBillApprovalLogShouldBeFound(String filter) throws Exception {
        restCaseClaimBillApprovalLogMockMvc.perform(get("/api/case-claim-bill-approval-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaimBillApprovalLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].processId").value(hasItem(DEFAULT_PROCESS_ID.intValue())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].approvalUser").value(hasItem(DEFAULT_APPROVAL_USER)))
            .andExpect(jsonPath("$.[*].approvalOpinion").value(hasItem(DEFAULT_APPROVAL_OPINION)))
            .andExpect(jsonPath("$.[*].approvalTransition").value(hasItem(DEFAULT_APPROVAL_TRANSITION)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));

        // Check, that the count call also returns 1
        restCaseClaimBillApprovalLogMockMvc.perform(get("/api/case-claim-bill-approval-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCaseClaimBillApprovalLogShouldNotBeFound(String filter) throws Exception {
        restCaseClaimBillApprovalLogMockMvc.perform(get("/api/case-claim-bill-approval-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCaseClaimBillApprovalLogMockMvc.perform(get("/api/case-claim-bill-approval-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCaseClaimBillApprovalLog() throws Exception {
        // Get the caseClaimBillApprovalLog
        restCaseClaimBillApprovalLogMockMvc.perform(get("/api/case-claim-bill-approval-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseClaimBillApprovalLog() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        int databaseSizeBeforeUpdate = caseClaimBillApprovalLogRepository.findAll().size();

        // Update the caseClaimBillApprovalLog
        CaseClaimBillApprovalLog updatedCaseClaimBillApprovalLog = caseClaimBillApprovalLogRepository.findById(caseClaimBillApprovalLog.getId()).get();
        // Disconnect from session so that the updates on updatedCaseClaimBillApprovalLog are not directly saved in db
        em.detach(updatedCaseClaimBillApprovalLog);
        updatedCaseClaimBillApprovalLog
            .processId(UPDATED_PROCESS_ID)
            .insertTime(UPDATED_INSERT_TIME)
            .approvalUser(UPDATED_APPROVAL_USER)
            .approvalOpinion(UPDATED_APPROVAL_OPINION)
            .approvalTransition(UPDATED_APPROVAL_TRANSITION)
            .remark(UPDATED_REMARK);
        CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO = caseClaimBillApprovalLogMapper.toDto(updatedCaseClaimBillApprovalLog);

        restCaseClaimBillApprovalLogMockMvc.perform(put("/api/case-claim-bill-approval-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillApprovalLogDTO)))
            .andExpect(status().isOk());

        // Validate the CaseClaimBillApprovalLog in the database
        List<CaseClaimBillApprovalLog> caseClaimBillApprovalLogList = caseClaimBillApprovalLogRepository.findAll();
        assertThat(caseClaimBillApprovalLogList).hasSize(databaseSizeBeforeUpdate);
        CaseClaimBillApprovalLog testCaseClaimBillApprovalLog = caseClaimBillApprovalLogList.get(caseClaimBillApprovalLogList.size() - 1);
        assertThat(testCaseClaimBillApprovalLog.getProcessId()).isEqualTo(UPDATED_PROCESS_ID);
        assertThat(testCaseClaimBillApprovalLog.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testCaseClaimBillApprovalLog.getApprovalUser()).isEqualTo(UPDATED_APPROVAL_USER);
        assertThat(testCaseClaimBillApprovalLog.getApprovalOpinion()).isEqualTo(UPDATED_APPROVAL_OPINION);
        assertThat(testCaseClaimBillApprovalLog.getApprovalTransition()).isEqualTo(UPDATED_APPROVAL_TRANSITION);
        assertThat(testCaseClaimBillApprovalLog.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseClaimBillApprovalLog() throws Exception {
        int databaseSizeBeforeUpdate = caseClaimBillApprovalLogRepository.findAll().size();

        // Create the CaseClaimBillApprovalLog
        CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO = caseClaimBillApprovalLogMapper.toDto(caseClaimBillApprovalLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseClaimBillApprovalLogMockMvc.perform(put("/api/case-claim-bill-approval-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillApprovalLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaimBillApprovalLog in the database
        List<CaseClaimBillApprovalLog> caseClaimBillApprovalLogList = caseClaimBillApprovalLogRepository.findAll();
        assertThat(caseClaimBillApprovalLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseClaimBillApprovalLog() throws Exception {
        // Initialize the database
        caseClaimBillApprovalLogRepository.saveAndFlush(caseClaimBillApprovalLog);

        int databaseSizeBeforeDelete = caseClaimBillApprovalLogRepository.findAll().size();

        // Delete the caseClaimBillApprovalLog
        restCaseClaimBillApprovalLogMockMvc.perform(delete("/api/case-claim-bill-approval-logs/{id}", caseClaimBillApprovalLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CaseClaimBillApprovalLog> caseClaimBillApprovalLogList = caseClaimBillApprovalLogRepository.findAll();
        assertThat(caseClaimBillApprovalLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaimBillApprovalLog.class);
        CaseClaimBillApprovalLog caseClaimBillApprovalLog1 = new CaseClaimBillApprovalLog();
        caseClaimBillApprovalLog1.setId(1L);
        CaseClaimBillApprovalLog caseClaimBillApprovalLog2 = new CaseClaimBillApprovalLog();
        caseClaimBillApprovalLog2.setId(caseClaimBillApprovalLog1.getId());
        assertThat(caseClaimBillApprovalLog1).isEqualTo(caseClaimBillApprovalLog2);
        caseClaimBillApprovalLog2.setId(2L);
        assertThat(caseClaimBillApprovalLog1).isNotEqualTo(caseClaimBillApprovalLog2);
        caseClaimBillApprovalLog1.setId(null);
        assertThat(caseClaimBillApprovalLog1).isNotEqualTo(caseClaimBillApprovalLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaimBillApprovalLogDTO.class);
        CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO1 = new CaseClaimBillApprovalLogDTO();
        caseClaimBillApprovalLogDTO1.setId(1L);
        CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO2 = new CaseClaimBillApprovalLogDTO();
        assertThat(caseClaimBillApprovalLogDTO1).isNotEqualTo(caseClaimBillApprovalLogDTO2);
        caseClaimBillApprovalLogDTO2.setId(caseClaimBillApprovalLogDTO1.getId());
        assertThat(caseClaimBillApprovalLogDTO1).isEqualTo(caseClaimBillApprovalLogDTO2);
        caseClaimBillApprovalLogDTO2.setId(2L);
        assertThat(caseClaimBillApprovalLogDTO1).isNotEqualTo(caseClaimBillApprovalLogDTO2);
        caseClaimBillApprovalLogDTO1.setId(null);
        assertThat(caseClaimBillApprovalLogDTO1).isNotEqualTo(caseClaimBillApprovalLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseClaimBillApprovalLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseClaimBillApprovalLogMapper.fromId(null)).isNull();
    }
}
