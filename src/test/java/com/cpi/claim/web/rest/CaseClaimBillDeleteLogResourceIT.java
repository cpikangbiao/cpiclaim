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
import com.cpi.claim.domain.CaseClaimBillDeleteLog;
import com.cpi.claim.repository.CaseClaimBillDeleteLogRepository;
import com.cpi.claim.service.CaseClaimBillDeleteLogService;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillDeleteLogMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogCriteria;
import com.cpi.claim.service.CaseClaimBillDeleteLogQueryService;

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
 * Integration tests for the {@Link CaseClaimBillDeleteLogResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseClaimBillDeleteLogResourceIT {

    private static final String DEFAULT_CLAIM_BILL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_BILL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_OPERATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_OPERATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OPERATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private CaseClaimBillDeleteLogRepository caseClaimBillDeleteLogRepository;

    @Autowired
    private CaseClaimBillDeleteLogMapper caseClaimBillDeleteLogMapper;

    @Autowired
    private CaseClaimBillDeleteLogService caseClaimBillDeleteLogService;

    @Autowired
    private CaseClaimBillDeleteLogQueryService caseClaimBillDeleteLogQueryService;

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

    private MockMvc restCaseClaimBillDeleteLogMockMvc;

    private CaseClaimBillDeleteLog caseClaimBillDeleteLog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseClaimBillDeleteLogResource caseClaimBillDeleteLogResource = new CaseClaimBillDeleteLogResource(caseClaimBillDeleteLogService, caseClaimBillDeleteLogQueryService);
        this.restCaseClaimBillDeleteLogMockMvc = MockMvcBuilders.standaloneSetup(caseClaimBillDeleteLogResource)
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
    public static CaseClaimBillDeleteLog createEntity(EntityManager em) {
        CaseClaimBillDeleteLog caseClaimBillDeleteLog = new CaseClaimBillDeleteLog()
            .claimBillCode(DEFAULT_CLAIM_BILL_CODE)
            .operateType(DEFAULT_OPERATE_TYPE)
            .operateUser(DEFAULT_OPERATE_USER)
            .operateDate(DEFAULT_OPERATE_DATE);
        return caseClaimBillDeleteLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseClaimBillDeleteLog createUpdatedEntity(EntityManager em) {
        CaseClaimBillDeleteLog caseClaimBillDeleteLog = new CaseClaimBillDeleteLog()
            .claimBillCode(UPDATED_CLAIM_BILL_CODE)
            .operateType(UPDATED_OPERATE_TYPE)
            .operateUser(UPDATED_OPERATE_USER)
            .operateDate(UPDATED_OPERATE_DATE);
        return caseClaimBillDeleteLog;
    }

    @BeforeEach
    public void initTest() {
        caseClaimBillDeleteLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseClaimBillDeleteLog() throws Exception {
        int databaseSizeBeforeCreate = caseClaimBillDeleteLogRepository.findAll().size();

        // Create the CaseClaimBillDeleteLog
        CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO = caseClaimBillDeleteLogMapper.toDto(caseClaimBillDeleteLog);
        restCaseClaimBillDeleteLogMockMvc.perform(post("/api/case-claim-bill-delete-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillDeleteLogDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseClaimBillDeleteLog in the database
        List<CaseClaimBillDeleteLog> caseClaimBillDeleteLogList = caseClaimBillDeleteLogRepository.findAll();
        assertThat(caseClaimBillDeleteLogList).hasSize(databaseSizeBeforeCreate + 1);
        CaseClaimBillDeleteLog testCaseClaimBillDeleteLog = caseClaimBillDeleteLogList.get(caseClaimBillDeleteLogList.size() - 1);
        assertThat(testCaseClaimBillDeleteLog.getClaimBillCode()).isEqualTo(DEFAULT_CLAIM_BILL_CODE);
        assertThat(testCaseClaimBillDeleteLog.getOperateType()).isEqualTo(DEFAULT_OPERATE_TYPE);
        assertThat(testCaseClaimBillDeleteLog.getOperateUser()).isEqualTo(DEFAULT_OPERATE_USER);
        assertThat(testCaseClaimBillDeleteLog.getOperateDate()).isEqualTo(DEFAULT_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void createCaseClaimBillDeleteLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseClaimBillDeleteLogRepository.findAll().size();

        // Create the CaseClaimBillDeleteLog with an existing ID
        caseClaimBillDeleteLog.setId(1L);
        CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO = caseClaimBillDeleteLogMapper.toDto(caseClaimBillDeleteLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseClaimBillDeleteLogMockMvc.perform(post("/api/case-claim-bill-delete-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillDeleteLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaimBillDeleteLog in the database
        List<CaseClaimBillDeleteLog> caseClaimBillDeleteLogList = caseClaimBillDeleteLogRepository.findAll();
        assertThat(caseClaimBillDeleteLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogs() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList
        restCaseClaimBillDeleteLogMockMvc.perform(get("/api/case-claim-bill-delete-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaimBillDeleteLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].claimBillCode").value(hasItem(DEFAULT_CLAIM_BILL_CODE.toString())))
            .andExpect(jsonPath("$.[*].operateType").value(hasItem(DEFAULT_OPERATE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].operateUser").value(hasItem(DEFAULT_OPERATE_USER.toString())))
            .andExpect(jsonPath("$.[*].operateDate").value(hasItem(DEFAULT_OPERATE_DATE.toString())));
    }

    @Test
    @Transactional
    public void getCaseClaimBillDeleteLog() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get the caseClaimBillDeleteLog
        restCaseClaimBillDeleteLogMockMvc.perform(get("/api/case-claim-bill-delete-logs/{id}", caseClaimBillDeleteLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseClaimBillDeleteLog.getId().intValue()))
            .andExpect(jsonPath("$.claimBillCode").value(DEFAULT_CLAIM_BILL_CODE.toString()))
            .andExpect(jsonPath("$.operateType").value(DEFAULT_OPERATE_TYPE.toString()))
            .andExpect(jsonPath("$.operateUser").value(DEFAULT_OPERATE_USER.toString()))
            .andExpect(jsonPath("$.operateDate").value(DEFAULT_OPERATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByClaimBillCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where claimBillCode equals to DEFAULT_CLAIM_BILL_CODE
        defaultCaseClaimBillDeleteLogShouldBeFound("claimBillCode.equals=" + DEFAULT_CLAIM_BILL_CODE);

        // Get all the caseClaimBillDeleteLogList where claimBillCode equals to UPDATED_CLAIM_BILL_CODE
        defaultCaseClaimBillDeleteLogShouldNotBeFound("claimBillCode.equals=" + UPDATED_CLAIM_BILL_CODE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByClaimBillCodeIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where claimBillCode in DEFAULT_CLAIM_BILL_CODE or UPDATED_CLAIM_BILL_CODE
        defaultCaseClaimBillDeleteLogShouldBeFound("claimBillCode.in=" + DEFAULT_CLAIM_BILL_CODE + "," + UPDATED_CLAIM_BILL_CODE);

        // Get all the caseClaimBillDeleteLogList where claimBillCode equals to UPDATED_CLAIM_BILL_CODE
        defaultCaseClaimBillDeleteLogShouldNotBeFound("claimBillCode.in=" + UPDATED_CLAIM_BILL_CODE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByClaimBillCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where claimBillCode is not null
        defaultCaseClaimBillDeleteLogShouldBeFound("claimBillCode.specified=true");

        // Get all the caseClaimBillDeleteLogList where claimBillCode is null
        defaultCaseClaimBillDeleteLogShouldNotBeFound("claimBillCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByOperateTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where operateType equals to DEFAULT_OPERATE_TYPE
        defaultCaseClaimBillDeleteLogShouldBeFound("operateType.equals=" + DEFAULT_OPERATE_TYPE);

        // Get all the caseClaimBillDeleteLogList where operateType equals to UPDATED_OPERATE_TYPE
        defaultCaseClaimBillDeleteLogShouldNotBeFound("operateType.equals=" + UPDATED_OPERATE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByOperateTypeIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where operateType in DEFAULT_OPERATE_TYPE or UPDATED_OPERATE_TYPE
        defaultCaseClaimBillDeleteLogShouldBeFound("operateType.in=" + DEFAULT_OPERATE_TYPE + "," + UPDATED_OPERATE_TYPE);

        // Get all the caseClaimBillDeleteLogList where operateType equals to UPDATED_OPERATE_TYPE
        defaultCaseClaimBillDeleteLogShouldNotBeFound("operateType.in=" + UPDATED_OPERATE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByOperateTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where operateType is not null
        defaultCaseClaimBillDeleteLogShouldBeFound("operateType.specified=true");

        // Get all the caseClaimBillDeleteLogList where operateType is null
        defaultCaseClaimBillDeleteLogShouldNotBeFound("operateType.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByOperateUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where operateUser equals to DEFAULT_OPERATE_USER
        defaultCaseClaimBillDeleteLogShouldBeFound("operateUser.equals=" + DEFAULT_OPERATE_USER);

        // Get all the caseClaimBillDeleteLogList where operateUser equals to UPDATED_OPERATE_USER
        defaultCaseClaimBillDeleteLogShouldNotBeFound("operateUser.equals=" + UPDATED_OPERATE_USER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByOperateUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where operateUser in DEFAULT_OPERATE_USER or UPDATED_OPERATE_USER
        defaultCaseClaimBillDeleteLogShouldBeFound("operateUser.in=" + DEFAULT_OPERATE_USER + "," + UPDATED_OPERATE_USER);

        // Get all the caseClaimBillDeleteLogList where operateUser equals to UPDATED_OPERATE_USER
        defaultCaseClaimBillDeleteLogShouldNotBeFound("operateUser.in=" + UPDATED_OPERATE_USER);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByOperateUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where operateUser is not null
        defaultCaseClaimBillDeleteLogShouldBeFound("operateUser.specified=true");

        // Get all the caseClaimBillDeleteLogList where operateUser is null
        defaultCaseClaimBillDeleteLogShouldNotBeFound("operateUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByOperateDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where operateDate equals to DEFAULT_OPERATE_DATE
        defaultCaseClaimBillDeleteLogShouldBeFound("operateDate.equals=" + DEFAULT_OPERATE_DATE);

        // Get all the caseClaimBillDeleteLogList where operateDate equals to UPDATED_OPERATE_DATE
        defaultCaseClaimBillDeleteLogShouldNotBeFound("operateDate.equals=" + UPDATED_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByOperateDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where operateDate in DEFAULT_OPERATE_DATE or UPDATED_OPERATE_DATE
        defaultCaseClaimBillDeleteLogShouldBeFound("operateDate.in=" + DEFAULT_OPERATE_DATE + "," + UPDATED_OPERATE_DATE);

        // Get all the caseClaimBillDeleteLogList where operateDate equals to UPDATED_OPERATE_DATE
        defaultCaseClaimBillDeleteLogShouldNotBeFound("operateDate.in=" + UPDATED_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseClaimBillDeleteLogsByOperateDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        // Get all the caseClaimBillDeleteLogList where operateDate is not null
        defaultCaseClaimBillDeleteLogShouldBeFound("operateDate.specified=true");

        // Get all the caseClaimBillDeleteLogList where operateDate is null
        defaultCaseClaimBillDeleteLogShouldNotBeFound("operateDate.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCaseClaimBillDeleteLogShouldBeFound(String filter) throws Exception {
        restCaseClaimBillDeleteLogMockMvc.perform(get("/api/case-claim-bill-delete-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseClaimBillDeleteLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].claimBillCode").value(hasItem(DEFAULT_CLAIM_BILL_CODE)))
            .andExpect(jsonPath("$.[*].operateType").value(hasItem(DEFAULT_OPERATE_TYPE)))
            .andExpect(jsonPath("$.[*].operateUser").value(hasItem(DEFAULT_OPERATE_USER)))
            .andExpect(jsonPath("$.[*].operateDate").value(hasItem(DEFAULT_OPERATE_DATE.toString())));

        // Check, that the count call also returns 1
        restCaseClaimBillDeleteLogMockMvc.perform(get("/api/case-claim-bill-delete-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCaseClaimBillDeleteLogShouldNotBeFound(String filter) throws Exception {
        restCaseClaimBillDeleteLogMockMvc.perform(get("/api/case-claim-bill-delete-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCaseClaimBillDeleteLogMockMvc.perform(get("/api/case-claim-bill-delete-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCaseClaimBillDeleteLog() throws Exception {
        // Get the caseClaimBillDeleteLog
        restCaseClaimBillDeleteLogMockMvc.perform(get("/api/case-claim-bill-delete-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseClaimBillDeleteLog() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        int databaseSizeBeforeUpdate = caseClaimBillDeleteLogRepository.findAll().size();

        // Update the caseClaimBillDeleteLog
        CaseClaimBillDeleteLog updatedCaseClaimBillDeleteLog = caseClaimBillDeleteLogRepository.findById(caseClaimBillDeleteLog.getId()).get();
        // Disconnect from session so that the updates on updatedCaseClaimBillDeleteLog are not directly saved in db
        em.detach(updatedCaseClaimBillDeleteLog);
        updatedCaseClaimBillDeleteLog
            .claimBillCode(UPDATED_CLAIM_BILL_CODE)
            .operateType(UPDATED_OPERATE_TYPE)
            .operateUser(UPDATED_OPERATE_USER)
            .operateDate(UPDATED_OPERATE_DATE);
        CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO = caseClaimBillDeleteLogMapper.toDto(updatedCaseClaimBillDeleteLog);

        restCaseClaimBillDeleteLogMockMvc.perform(put("/api/case-claim-bill-delete-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillDeleteLogDTO)))
            .andExpect(status().isOk());

        // Validate the CaseClaimBillDeleteLog in the database
        List<CaseClaimBillDeleteLog> caseClaimBillDeleteLogList = caseClaimBillDeleteLogRepository.findAll();
        assertThat(caseClaimBillDeleteLogList).hasSize(databaseSizeBeforeUpdate);
        CaseClaimBillDeleteLog testCaseClaimBillDeleteLog = caseClaimBillDeleteLogList.get(caseClaimBillDeleteLogList.size() - 1);
        assertThat(testCaseClaimBillDeleteLog.getClaimBillCode()).isEqualTo(UPDATED_CLAIM_BILL_CODE);
        assertThat(testCaseClaimBillDeleteLog.getOperateType()).isEqualTo(UPDATED_OPERATE_TYPE);
        assertThat(testCaseClaimBillDeleteLog.getOperateUser()).isEqualTo(UPDATED_OPERATE_USER);
        assertThat(testCaseClaimBillDeleteLog.getOperateDate()).isEqualTo(UPDATED_OPERATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseClaimBillDeleteLog() throws Exception {
        int databaseSizeBeforeUpdate = caseClaimBillDeleteLogRepository.findAll().size();

        // Create the CaseClaimBillDeleteLog
        CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO = caseClaimBillDeleteLogMapper.toDto(caseClaimBillDeleteLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseClaimBillDeleteLogMockMvc.perform(put("/api/case-claim-bill-delete-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseClaimBillDeleteLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseClaimBillDeleteLog in the database
        List<CaseClaimBillDeleteLog> caseClaimBillDeleteLogList = caseClaimBillDeleteLogRepository.findAll();
        assertThat(caseClaimBillDeleteLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseClaimBillDeleteLog() throws Exception {
        // Initialize the database
        caseClaimBillDeleteLogRepository.saveAndFlush(caseClaimBillDeleteLog);

        int databaseSizeBeforeDelete = caseClaimBillDeleteLogRepository.findAll().size();

        // Delete the caseClaimBillDeleteLog
        restCaseClaimBillDeleteLogMockMvc.perform(delete("/api/case-claim-bill-delete-logs/{id}", caseClaimBillDeleteLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CaseClaimBillDeleteLog> caseClaimBillDeleteLogList = caseClaimBillDeleteLogRepository.findAll();
        assertThat(caseClaimBillDeleteLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaimBillDeleteLog.class);
        CaseClaimBillDeleteLog caseClaimBillDeleteLog1 = new CaseClaimBillDeleteLog();
        caseClaimBillDeleteLog1.setId(1L);
        CaseClaimBillDeleteLog caseClaimBillDeleteLog2 = new CaseClaimBillDeleteLog();
        caseClaimBillDeleteLog2.setId(caseClaimBillDeleteLog1.getId());
        assertThat(caseClaimBillDeleteLog1).isEqualTo(caseClaimBillDeleteLog2);
        caseClaimBillDeleteLog2.setId(2L);
        assertThat(caseClaimBillDeleteLog1).isNotEqualTo(caseClaimBillDeleteLog2);
        caseClaimBillDeleteLog1.setId(null);
        assertThat(caseClaimBillDeleteLog1).isNotEqualTo(caseClaimBillDeleteLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseClaimBillDeleteLogDTO.class);
        CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO1 = new CaseClaimBillDeleteLogDTO();
        caseClaimBillDeleteLogDTO1.setId(1L);
        CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO2 = new CaseClaimBillDeleteLogDTO();
        assertThat(caseClaimBillDeleteLogDTO1).isNotEqualTo(caseClaimBillDeleteLogDTO2);
        caseClaimBillDeleteLogDTO2.setId(caseClaimBillDeleteLogDTO1.getId());
        assertThat(caseClaimBillDeleteLogDTO1).isEqualTo(caseClaimBillDeleteLogDTO2);
        caseClaimBillDeleteLogDTO2.setId(2L);
        assertThat(caseClaimBillDeleteLogDTO1).isNotEqualTo(caseClaimBillDeleteLogDTO2);
        caseClaimBillDeleteLogDTO1.setId(null);
        assertThat(caseClaimBillDeleteLogDTO1).isNotEqualTo(caseClaimBillDeleteLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseClaimBillDeleteLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseClaimBillDeleteLogMapper.fromId(null)).isNull();
    }
}
