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
import com.cpi.claim.domain.ClaimBillType;
import com.cpi.claim.repository.ClaimBillTypeRepository;
import com.cpi.claim.service.ClaimBillTypeService;
import com.cpi.claim.service.dto.ClaimBillTypeDTO;
import com.cpi.claim.service.mapper.ClaimBillTypeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.ClaimBillTypeCriteria;
import com.cpi.claim.service.ClaimBillTypeQueryService;

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
 * Integration tests for the {@Link ClaimBillTypeResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class ClaimBillTypeResourceIT {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_CLAIM_BILL_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_BILL_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private ClaimBillTypeRepository claimBillTypeRepository;

    @Autowired
    private ClaimBillTypeMapper claimBillTypeMapper;

    @Autowired
    private ClaimBillTypeService claimBillTypeService;

    @Autowired
    private ClaimBillTypeQueryService claimBillTypeQueryService;

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

    private MockMvc restClaimBillTypeMockMvc;

    private ClaimBillType claimBillType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimBillTypeResource claimBillTypeResource = new ClaimBillTypeResource(claimBillTypeService, claimBillTypeQueryService);
        this.restClaimBillTypeMockMvc = MockMvcBuilders.standaloneSetup(claimBillTypeResource)
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
    public static ClaimBillType createEntity(EntityManager em) {
        ClaimBillType claimBillType = new ClaimBillType()
            .sortNum(DEFAULT_SORT_NUM)
            .claimBillTypeName(DEFAULT_CLAIM_BILL_TYPE_NAME);
        return claimBillType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimBillType createUpdatedEntity(EntityManager em) {
        ClaimBillType claimBillType = new ClaimBillType()
            .sortNum(UPDATED_SORT_NUM)
            .claimBillTypeName(UPDATED_CLAIM_BILL_TYPE_NAME);
        return claimBillType;
    }

    @BeforeEach
    public void initTest() {
        claimBillType = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimBillType() throws Exception {
        int databaseSizeBeforeCreate = claimBillTypeRepository.findAll().size();

        // Create the ClaimBillType
        ClaimBillTypeDTO claimBillTypeDTO = claimBillTypeMapper.toDto(claimBillType);
        restClaimBillTypeMockMvc.perform(post("/api/claim-bill-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimBillType in the database
        List<ClaimBillType> claimBillTypeList = claimBillTypeRepository.findAll();
        assertThat(claimBillTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimBillType testClaimBillType = claimBillTypeList.get(claimBillTypeList.size() - 1);
        assertThat(testClaimBillType.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testClaimBillType.getClaimBillTypeName()).isEqualTo(DEFAULT_CLAIM_BILL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void createClaimBillTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimBillTypeRepository.findAll().size();

        // Create the ClaimBillType with an existing ID
        claimBillType.setId(1L);
        ClaimBillTypeDTO claimBillTypeDTO = claimBillTypeMapper.toDto(claimBillType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimBillTypeMockMvc.perform(post("/api/claim-bill-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimBillType in the database
        List<ClaimBillType> claimBillTypeList = claimBillTypeRepository.findAll();
        assertThat(claimBillTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClaimBillTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = claimBillTypeRepository.findAll().size();
        // set the field null
        claimBillType.setClaimBillTypeName(null);

        // Create the ClaimBillType, which fails.
        ClaimBillTypeDTO claimBillTypeDTO = claimBillTypeMapper.toDto(claimBillType);

        restClaimBillTypeMockMvc.perform(post("/api/claim-bill-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ClaimBillType> claimBillTypeList = claimBillTypeRepository.findAll();
        assertThat(claimBillTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClaimBillTypes() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get all the claimBillTypeList
        restClaimBillTypeMockMvc.perform(get("/api/claim-bill-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimBillType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].claimBillTypeName").value(hasItem(DEFAULT_CLAIM_BILL_TYPE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getClaimBillType() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get the claimBillType
        restClaimBillTypeMockMvc.perform(get("/api/claim-bill-types/{id}", claimBillType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimBillType.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.claimBillTypeName").value(DEFAULT_CLAIM_BILL_TYPE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllClaimBillTypesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get all the claimBillTypeList where sortNum equals to DEFAULT_SORT_NUM
        defaultClaimBillTypeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the claimBillTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultClaimBillTypeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllClaimBillTypesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get all the claimBillTypeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultClaimBillTypeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the claimBillTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultClaimBillTypeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllClaimBillTypesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get all the claimBillTypeList where sortNum is not null
        defaultClaimBillTypeShouldBeFound("sortNum.specified=true");

        // Get all the claimBillTypeList where sortNum is null
        defaultClaimBillTypeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllClaimBillTypesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get all the claimBillTypeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultClaimBillTypeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the claimBillTypeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultClaimBillTypeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllClaimBillTypesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get all the claimBillTypeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultClaimBillTypeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the claimBillTypeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultClaimBillTypeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllClaimBillTypesByClaimBillTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get all the claimBillTypeList where claimBillTypeName equals to DEFAULT_CLAIM_BILL_TYPE_NAME
        defaultClaimBillTypeShouldBeFound("claimBillTypeName.equals=" + DEFAULT_CLAIM_BILL_TYPE_NAME);

        // Get all the claimBillTypeList where claimBillTypeName equals to UPDATED_CLAIM_BILL_TYPE_NAME
        defaultClaimBillTypeShouldNotBeFound("claimBillTypeName.equals=" + UPDATED_CLAIM_BILL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllClaimBillTypesByClaimBillTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get all the claimBillTypeList where claimBillTypeName in DEFAULT_CLAIM_BILL_TYPE_NAME or UPDATED_CLAIM_BILL_TYPE_NAME
        defaultClaimBillTypeShouldBeFound("claimBillTypeName.in=" + DEFAULT_CLAIM_BILL_TYPE_NAME + "," + UPDATED_CLAIM_BILL_TYPE_NAME);

        // Get all the claimBillTypeList where claimBillTypeName equals to UPDATED_CLAIM_BILL_TYPE_NAME
        defaultClaimBillTypeShouldNotBeFound("claimBillTypeName.in=" + UPDATED_CLAIM_BILL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllClaimBillTypesByClaimBillTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        // Get all the claimBillTypeList where claimBillTypeName is not null
        defaultClaimBillTypeShouldBeFound("claimBillTypeName.specified=true");

        // Get all the claimBillTypeList where claimBillTypeName is null
        defaultClaimBillTypeShouldNotBeFound("claimBillTypeName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultClaimBillTypeShouldBeFound(String filter) throws Exception {
        restClaimBillTypeMockMvc.perform(get("/api/claim-bill-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimBillType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].claimBillTypeName").value(hasItem(DEFAULT_CLAIM_BILL_TYPE_NAME)));

        // Check, that the count call also returns 1
        restClaimBillTypeMockMvc.perform(get("/api/claim-bill-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultClaimBillTypeShouldNotBeFound(String filter) throws Exception {
        restClaimBillTypeMockMvc.perform(get("/api/claim-bill-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restClaimBillTypeMockMvc.perform(get("/api/claim-bill-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingClaimBillType() throws Exception {
        // Get the claimBillType
        restClaimBillTypeMockMvc.perform(get("/api/claim-bill-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimBillType() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        int databaseSizeBeforeUpdate = claimBillTypeRepository.findAll().size();

        // Update the claimBillType
        ClaimBillType updatedClaimBillType = claimBillTypeRepository.findById(claimBillType.getId()).get();
        // Disconnect from session so that the updates on updatedClaimBillType are not directly saved in db
        em.detach(updatedClaimBillType);
        updatedClaimBillType
            .sortNum(UPDATED_SORT_NUM)
            .claimBillTypeName(UPDATED_CLAIM_BILL_TYPE_NAME);
        ClaimBillTypeDTO claimBillTypeDTO = claimBillTypeMapper.toDto(updatedClaimBillType);

        restClaimBillTypeMockMvc.perform(put("/api/claim-bill-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimBillType in the database
        List<ClaimBillType> claimBillTypeList = claimBillTypeRepository.findAll();
        assertThat(claimBillTypeList).hasSize(databaseSizeBeforeUpdate);
        ClaimBillType testClaimBillType = claimBillTypeList.get(claimBillTypeList.size() - 1);
        assertThat(testClaimBillType.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testClaimBillType.getClaimBillTypeName()).isEqualTo(UPDATED_CLAIM_BILL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimBillType() throws Exception {
        int databaseSizeBeforeUpdate = claimBillTypeRepository.findAll().size();

        // Create the ClaimBillType
        ClaimBillTypeDTO claimBillTypeDTO = claimBillTypeMapper.toDto(claimBillType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimBillTypeMockMvc.perform(put("/api/claim-bill-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimBillType in the database
        List<ClaimBillType> claimBillTypeList = claimBillTypeRepository.findAll();
        assertThat(claimBillTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimBillType() throws Exception {
        // Initialize the database
        claimBillTypeRepository.saveAndFlush(claimBillType);

        int databaseSizeBeforeDelete = claimBillTypeRepository.findAll().size();

        // Delete the claimBillType
        restClaimBillTypeMockMvc.perform(delete("/api/claim-bill-types/{id}", claimBillType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ClaimBillType> claimBillTypeList = claimBillTypeRepository.findAll();
        assertThat(claimBillTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimBillType.class);
        ClaimBillType claimBillType1 = new ClaimBillType();
        claimBillType1.setId(1L);
        ClaimBillType claimBillType2 = new ClaimBillType();
        claimBillType2.setId(claimBillType1.getId());
        assertThat(claimBillType1).isEqualTo(claimBillType2);
        claimBillType2.setId(2L);
        assertThat(claimBillType1).isNotEqualTo(claimBillType2);
        claimBillType1.setId(null);
        assertThat(claimBillType1).isNotEqualTo(claimBillType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimBillTypeDTO.class);
        ClaimBillTypeDTO claimBillTypeDTO1 = new ClaimBillTypeDTO();
        claimBillTypeDTO1.setId(1L);
        ClaimBillTypeDTO claimBillTypeDTO2 = new ClaimBillTypeDTO();
        assertThat(claimBillTypeDTO1).isNotEqualTo(claimBillTypeDTO2);
        claimBillTypeDTO2.setId(claimBillTypeDTO1.getId());
        assertThat(claimBillTypeDTO1).isEqualTo(claimBillTypeDTO2);
        claimBillTypeDTO2.setId(2L);
        assertThat(claimBillTypeDTO1).isNotEqualTo(claimBillTypeDTO2);
        claimBillTypeDTO1.setId(null);
        assertThat(claimBillTypeDTO1).isNotEqualTo(claimBillTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(claimBillTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(claimBillTypeMapper.fromId(null)).isNull();
    }
}
