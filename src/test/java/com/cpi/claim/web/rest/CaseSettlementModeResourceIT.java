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
import com.cpi.claim.domain.CaseSettlementMode;
import com.cpi.claim.repository.CaseSettlementModeRepository;
import com.cpi.claim.service.CaseSettlementModeService;
import com.cpi.claim.service.dto.CaseSettlementModeDTO;
import com.cpi.claim.service.mapper.CaseSettlementModeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseSettlementModeCriteria;
import com.cpi.claim.service.CaseSettlementModeQueryService;

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
import java.util.List;

import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link CaseSettlementModeResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseSettlementModeResourceIT {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_SETTLEMENT_MODE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT_MODE_NAME = "BBBBBBBBBB";

    @Autowired
    private CaseSettlementModeRepository caseSettlementModeRepository;

    @Autowired
    private CaseSettlementModeMapper caseSettlementModeMapper;

    @Autowired
    private CaseSettlementModeService caseSettlementModeService;

    @Autowired
    private CaseSettlementModeQueryService caseSettlementModeQueryService;

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

    private MockMvc restCaseSettlementModeMockMvc;

    private CaseSettlementMode caseSettlementMode;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseSettlementModeResource caseSettlementModeResource = new CaseSettlementModeResource(caseSettlementModeService, caseSettlementModeQueryService);
        this.restCaseSettlementModeMockMvc = MockMvcBuilders.standaloneSetup(caseSettlementModeResource)
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
    public static CaseSettlementMode createEntity(EntityManager em) {
        CaseSettlementMode caseSettlementMode = new CaseSettlementMode()
            .sortNum(DEFAULT_SORT_NUM)
            .settlementModeName(DEFAULT_SETTLEMENT_MODE_NAME);
        return caseSettlementMode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseSettlementMode createUpdatedEntity(EntityManager em) {
        CaseSettlementMode caseSettlementMode = new CaseSettlementMode()
            .sortNum(UPDATED_SORT_NUM)
            .settlementModeName(UPDATED_SETTLEMENT_MODE_NAME);
        return caseSettlementMode;
    }

    @BeforeEach
    public void initTest() {
        caseSettlementMode = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseSettlementMode() throws Exception {
        int databaseSizeBeforeCreate = caseSettlementModeRepository.findAll().size();

        // Create the CaseSettlementMode
        CaseSettlementModeDTO caseSettlementModeDTO = caseSettlementModeMapper.toDto(caseSettlementMode);
        restCaseSettlementModeMockMvc.perform(post("/api/case-settlement-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSettlementModeDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseSettlementMode in the database
        List<CaseSettlementMode> caseSettlementModeList = caseSettlementModeRepository.findAll();
        assertThat(caseSettlementModeList).hasSize(databaseSizeBeforeCreate + 1);
        CaseSettlementMode testCaseSettlementMode = caseSettlementModeList.get(caseSettlementModeList.size() - 1);
        assertThat(testCaseSettlementMode.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testCaseSettlementMode.getSettlementModeName()).isEqualTo(DEFAULT_SETTLEMENT_MODE_NAME);
    }

    @Test
    @Transactional
    public void createCaseSettlementModeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseSettlementModeRepository.findAll().size();

        // Create the CaseSettlementMode with an existing ID
        caseSettlementMode.setId(1L);
        CaseSettlementModeDTO caseSettlementModeDTO = caseSettlementModeMapper.toDto(caseSettlementMode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseSettlementModeMockMvc.perform(post("/api/case-settlement-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSettlementModeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseSettlementMode in the database
        List<CaseSettlementMode> caseSettlementModeList = caseSettlementModeRepository.findAll();
        assertThat(caseSettlementModeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSettlementModeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = caseSettlementModeRepository.findAll().size();
        // set the field null
        caseSettlementMode.setSettlementModeName(null);

        // Create the CaseSettlementMode, which fails.
        CaseSettlementModeDTO caseSettlementModeDTO = caseSettlementModeMapper.toDto(caseSettlementMode);

        restCaseSettlementModeMockMvc.perform(post("/api/case-settlement-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSettlementModeDTO)))
            .andExpect(status().isBadRequest());

        List<CaseSettlementMode> caseSettlementModeList = caseSettlementModeRepository.findAll();
        assertThat(caseSettlementModeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCaseSettlementModes() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get all the caseSettlementModeList
        restCaseSettlementModeMockMvc.perform(get("/api/case-settlement-modes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseSettlementMode.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].settlementModeName").value(hasItem(DEFAULT_SETTLEMENT_MODE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getCaseSettlementMode() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get the caseSettlementMode
        restCaseSettlementModeMockMvc.perform(get("/api/case-settlement-modes/{id}", caseSettlementMode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseSettlementMode.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.settlementModeName").value(DEFAULT_SETTLEMENT_MODE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseSettlementModesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get all the caseSettlementModeList where sortNum equals to DEFAULT_SORT_NUM
        defaultCaseSettlementModeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the caseSettlementModeList where sortNum equals to UPDATED_SORT_NUM
        defaultCaseSettlementModeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCaseSettlementModesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get all the caseSettlementModeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultCaseSettlementModeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the caseSettlementModeList where sortNum equals to UPDATED_SORT_NUM
        defaultCaseSettlementModeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCaseSettlementModesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get all the caseSettlementModeList where sortNum is not null
        defaultCaseSettlementModeShouldBeFound("sortNum.specified=true");

        // Get all the caseSettlementModeList where sortNum is null
        defaultCaseSettlementModeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseSettlementModesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get all the caseSettlementModeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultCaseSettlementModeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the caseSettlementModeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultCaseSettlementModeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCaseSettlementModesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get all the caseSettlementModeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultCaseSettlementModeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the caseSettlementModeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultCaseSettlementModeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllCaseSettlementModesBySettlementModeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get all the caseSettlementModeList where settlementModeName equals to DEFAULT_SETTLEMENT_MODE_NAME
        defaultCaseSettlementModeShouldBeFound("settlementModeName.equals=" + DEFAULT_SETTLEMENT_MODE_NAME);

        // Get all the caseSettlementModeList where settlementModeName equals to UPDATED_SETTLEMENT_MODE_NAME
        defaultCaseSettlementModeShouldNotBeFound("settlementModeName.equals=" + UPDATED_SETTLEMENT_MODE_NAME);
    }

    @Test
    @Transactional
    public void getAllCaseSettlementModesBySettlementModeNameIsInShouldWork() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get all the caseSettlementModeList where settlementModeName in DEFAULT_SETTLEMENT_MODE_NAME or UPDATED_SETTLEMENT_MODE_NAME
        defaultCaseSettlementModeShouldBeFound("settlementModeName.in=" + DEFAULT_SETTLEMENT_MODE_NAME + "," + UPDATED_SETTLEMENT_MODE_NAME);

        // Get all the caseSettlementModeList where settlementModeName equals to UPDATED_SETTLEMENT_MODE_NAME
        defaultCaseSettlementModeShouldNotBeFound("settlementModeName.in=" + UPDATED_SETTLEMENT_MODE_NAME);
    }

    @Test
    @Transactional
    public void getAllCaseSettlementModesBySettlementModeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        // Get all the caseSettlementModeList where settlementModeName is not null
        defaultCaseSettlementModeShouldBeFound("settlementModeName.specified=true");

        // Get all the caseSettlementModeList where settlementModeName is null
        defaultCaseSettlementModeShouldNotBeFound("settlementModeName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCaseSettlementModeShouldBeFound(String filter) throws Exception {
        restCaseSettlementModeMockMvc.perform(get("/api/case-settlement-modes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseSettlementMode.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].settlementModeName").value(hasItem(DEFAULT_SETTLEMENT_MODE_NAME)));

        // Check, that the count call also returns 1
        restCaseSettlementModeMockMvc.perform(get("/api/case-settlement-modes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCaseSettlementModeShouldNotBeFound(String filter) throws Exception {
        restCaseSettlementModeMockMvc.perform(get("/api/case-settlement-modes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCaseSettlementModeMockMvc.perform(get("/api/case-settlement-modes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCaseSettlementMode() throws Exception {
        // Get the caseSettlementMode
        restCaseSettlementModeMockMvc.perform(get("/api/case-settlement-modes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseSettlementMode() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        int databaseSizeBeforeUpdate = caseSettlementModeRepository.findAll().size();

        // Update the caseSettlementMode
        CaseSettlementMode updatedCaseSettlementMode = caseSettlementModeRepository.findById(caseSettlementMode.getId()).get();
        // Disconnect from session so that the updates on updatedCaseSettlementMode are not directly saved in db
        em.detach(updatedCaseSettlementMode);
        updatedCaseSettlementMode
            .sortNum(UPDATED_SORT_NUM)
            .settlementModeName(UPDATED_SETTLEMENT_MODE_NAME);
        CaseSettlementModeDTO caseSettlementModeDTO = caseSettlementModeMapper.toDto(updatedCaseSettlementMode);

        restCaseSettlementModeMockMvc.perform(put("/api/case-settlement-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSettlementModeDTO)))
            .andExpect(status().isOk());

        // Validate the CaseSettlementMode in the database
        List<CaseSettlementMode> caseSettlementModeList = caseSettlementModeRepository.findAll();
        assertThat(caseSettlementModeList).hasSize(databaseSizeBeforeUpdate);
        CaseSettlementMode testCaseSettlementMode = caseSettlementModeList.get(caseSettlementModeList.size() - 1);
        assertThat(testCaseSettlementMode.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testCaseSettlementMode.getSettlementModeName()).isEqualTo(UPDATED_SETTLEMENT_MODE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseSettlementMode() throws Exception {
        int databaseSizeBeforeUpdate = caseSettlementModeRepository.findAll().size();

        // Create the CaseSettlementMode
        CaseSettlementModeDTO caseSettlementModeDTO = caseSettlementModeMapper.toDto(caseSettlementMode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseSettlementModeMockMvc.perform(put("/api/case-settlement-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSettlementModeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseSettlementMode in the database
        List<CaseSettlementMode> caseSettlementModeList = caseSettlementModeRepository.findAll();
        assertThat(caseSettlementModeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseSettlementMode() throws Exception {
        // Initialize the database
        caseSettlementModeRepository.saveAndFlush(caseSettlementMode);

        int databaseSizeBeforeDelete = caseSettlementModeRepository.findAll().size();

        // Delete the caseSettlementMode
        restCaseSettlementModeMockMvc.perform(delete("/api/case-settlement-modes/{id}", caseSettlementMode.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CaseSettlementMode> caseSettlementModeList = caseSettlementModeRepository.findAll();
        assertThat(caseSettlementModeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseSettlementMode.class);
        CaseSettlementMode caseSettlementMode1 = new CaseSettlementMode();
        caseSettlementMode1.setId(1L);
        CaseSettlementMode caseSettlementMode2 = new CaseSettlementMode();
        caseSettlementMode2.setId(caseSettlementMode1.getId());
        assertThat(caseSettlementMode1).isEqualTo(caseSettlementMode2);
        caseSettlementMode2.setId(2L);
        assertThat(caseSettlementMode1).isNotEqualTo(caseSettlementMode2);
        caseSettlementMode1.setId(null);
        assertThat(caseSettlementMode1).isNotEqualTo(caseSettlementMode2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseSettlementModeDTO.class);
        CaseSettlementModeDTO caseSettlementModeDTO1 = new CaseSettlementModeDTO();
        caseSettlementModeDTO1.setId(1L);
        CaseSettlementModeDTO caseSettlementModeDTO2 = new CaseSettlementModeDTO();
        assertThat(caseSettlementModeDTO1).isNotEqualTo(caseSettlementModeDTO2);
        caseSettlementModeDTO2.setId(caseSettlementModeDTO1.getId());
        assertThat(caseSettlementModeDTO1).isEqualTo(caseSettlementModeDTO2);
        caseSettlementModeDTO2.setId(2L);
        assertThat(caseSettlementModeDTO1).isNotEqualTo(caseSettlementModeDTO2);
        caseSettlementModeDTO1.setId(null);
        assertThat(caseSettlementModeDTO1).isNotEqualTo(caseSettlementModeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseSettlementModeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseSettlementModeMapper.fromId(null)).isNull();
    }
}
