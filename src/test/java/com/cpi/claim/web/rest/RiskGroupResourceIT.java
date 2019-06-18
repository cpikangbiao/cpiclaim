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
import com.cpi.claim.domain.RiskGroup;
import com.cpi.claim.repository.RiskGroupRepository;
import com.cpi.claim.service.RiskGroupService;
import com.cpi.claim.service.dto.RiskGroupDTO;
import com.cpi.claim.service.mapper.RiskGroupMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.RiskGroupCriteria;
import com.cpi.claim.service.RiskGroupQueryService;

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
 * Integration tests for the {@Link RiskGroupResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class RiskGroupResourceIT {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_RISK_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RISK_GROUP_NAME = "BBBBBBBBBB";

    @Autowired
    private RiskGroupRepository riskGroupRepository;

    @Autowired
    private RiskGroupMapper riskGroupMapper;

    @Autowired
    private RiskGroupService riskGroupService;

    @Autowired
    private RiskGroupQueryService riskGroupQueryService;

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

    private MockMvc restRiskGroupMockMvc;

    private RiskGroup riskGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RiskGroupResource riskGroupResource = new RiskGroupResource(riskGroupService, riskGroupQueryService);
        this.restRiskGroupMockMvc = MockMvcBuilders.standaloneSetup(riskGroupResource)
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
    public static RiskGroup createEntity(EntityManager em) {
        RiskGroup riskGroup = new RiskGroup()
            .sortNum(DEFAULT_SORT_NUM)
            .riskGroupName(DEFAULT_RISK_GROUP_NAME);
        return riskGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskGroup createUpdatedEntity(EntityManager em) {
        RiskGroup riskGroup = new RiskGroup()
            .sortNum(UPDATED_SORT_NUM)
            .riskGroupName(UPDATED_RISK_GROUP_NAME);
        return riskGroup;
    }

    @BeforeEach
    public void initTest() {
        riskGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createRiskGroup() throws Exception {
        int databaseSizeBeforeCreate = riskGroupRepository.findAll().size();

        // Create the RiskGroup
        RiskGroupDTO riskGroupDTO = riskGroupMapper.toDto(riskGroup);
        restRiskGroupMockMvc.perform(post("/api/risk-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the RiskGroup in the database
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeCreate + 1);
        RiskGroup testRiskGroup = riskGroupList.get(riskGroupList.size() - 1);
        assertThat(testRiskGroup.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testRiskGroup.getRiskGroupName()).isEqualTo(DEFAULT_RISK_GROUP_NAME);
    }

    @Test
    @Transactional
    public void createRiskGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskGroupRepository.findAll().size();

        // Create the RiskGroup with an existing ID
        riskGroup.setId(1L);
        RiskGroupDTO riskGroupDTO = riskGroupMapper.toDto(riskGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskGroupMockMvc.perform(post("/api/risk-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RiskGroup in the database
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRiskGroupNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskGroupRepository.findAll().size();
        // set the field null
        riskGroup.setRiskGroupName(null);

        // Create the RiskGroup, which fails.
        RiskGroupDTO riskGroupDTO = riskGroupMapper.toDto(riskGroup);

        restRiskGroupMockMvc.perform(post("/api/risk-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskGroupDTO)))
            .andExpect(status().isBadRequest());

        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRiskGroups() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList
        restRiskGroupMockMvc.perform(get("/api/risk-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].riskGroupName").value(hasItem(DEFAULT_RISK_GROUP_NAME.toString())));
    }

    @Test
    @Transactional
    public void getRiskGroup() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get the riskGroup
        restRiskGroupMockMvc.perform(get("/api/risk-groups/{id}", riskGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(riskGroup.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.riskGroupName").value(DEFAULT_RISK_GROUP_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllRiskGroupsBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList where sortNum equals to DEFAULT_SORT_NUM
        defaultRiskGroupShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the riskGroupList where sortNum equals to UPDATED_SORT_NUM
        defaultRiskGroupShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllRiskGroupsBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultRiskGroupShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the riskGroupList where sortNum equals to UPDATED_SORT_NUM
        defaultRiskGroupShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllRiskGroupsBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList where sortNum is not null
        defaultRiskGroupShouldBeFound("sortNum.specified=true");

        // Get all the riskGroupList where sortNum is null
        defaultRiskGroupShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllRiskGroupsBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultRiskGroupShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the riskGroupList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultRiskGroupShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllRiskGroupsBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultRiskGroupShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the riskGroupList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultRiskGroupShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllRiskGroupsByRiskGroupNameIsEqualToSomething() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList where riskGroupName equals to DEFAULT_RISK_GROUP_NAME
        defaultRiskGroupShouldBeFound("riskGroupName.equals=" + DEFAULT_RISK_GROUP_NAME);

        // Get all the riskGroupList where riskGroupName equals to UPDATED_RISK_GROUP_NAME
        defaultRiskGroupShouldNotBeFound("riskGroupName.equals=" + UPDATED_RISK_GROUP_NAME);
    }

    @Test
    @Transactional
    public void getAllRiskGroupsByRiskGroupNameIsInShouldWork() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList where riskGroupName in DEFAULT_RISK_GROUP_NAME or UPDATED_RISK_GROUP_NAME
        defaultRiskGroupShouldBeFound("riskGroupName.in=" + DEFAULT_RISK_GROUP_NAME + "," + UPDATED_RISK_GROUP_NAME);

        // Get all the riskGroupList where riskGroupName equals to UPDATED_RISK_GROUP_NAME
        defaultRiskGroupShouldNotBeFound("riskGroupName.in=" + UPDATED_RISK_GROUP_NAME);
    }

    @Test
    @Transactional
    public void getAllRiskGroupsByRiskGroupNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList where riskGroupName is not null
        defaultRiskGroupShouldBeFound("riskGroupName.specified=true");

        // Get all the riskGroupList where riskGroupName is null
        defaultRiskGroupShouldNotBeFound("riskGroupName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRiskGroupShouldBeFound(String filter) throws Exception {
        restRiskGroupMockMvc.perform(get("/api/risk-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].riskGroupName").value(hasItem(DEFAULT_RISK_GROUP_NAME)));

        // Check, that the count call also returns 1
        restRiskGroupMockMvc.perform(get("/api/risk-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRiskGroupShouldNotBeFound(String filter) throws Exception {
        restRiskGroupMockMvc.perform(get("/api/risk-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRiskGroupMockMvc.perform(get("/api/risk-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRiskGroup() throws Exception {
        // Get the riskGroup
        restRiskGroupMockMvc.perform(get("/api/risk-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRiskGroup() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        int databaseSizeBeforeUpdate = riskGroupRepository.findAll().size();

        // Update the riskGroup
        RiskGroup updatedRiskGroup = riskGroupRepository.findById(riskGroup.getId()).get();
        // Disconnect from session so that the updates on updatedRiskGroup are not directly saved in db
        em.detach(updatedRiskGroup);
        updatedRiskGroup
            .sortNum(UPDATED_SORT_NUM)
            .riskGroupName(UPDATED_RISK_GROUP_NAME);
        RiskGroupDTO riskGroupDTO = riskGroupMapper.toDto(updatedRiskGroup);

        restRiskGroupMockMvc.perform(put("/api/risk-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskGroupDTO)))
            .andExpect(status().isOk());

        // Validate the RiskGroup in the database
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeUpdate);
        RiskGroup testRiskGroup = riskGroupList.get(riskGroupList.size() - 1);
        assertThat(testRiskGroup.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testRiskGroup.getRiskGroupName()).isEqualTo(UPDATED_RISK_GROUP_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingRiskGroup() throws Exception {
        int databaseSizeBeforeUpdate = riskGroupRepository.findAll().size();

        // Create the RiskGroup
        RiskGroupDTO riskGroupDTO = riskGroupMapper.toDto(riskGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskGroupMockMvc.perform(put("/api/risk-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RiskGroup in the database
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRiskGroup() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        int databaseSizeBeforeDelete = riskGroupRepository.findAll().size();

        // Delete the riskGroup
        restRiskGroupMockMvc.perform(delete("/api/risk-groups/{id}", riskGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskGroup.class);
        RiskGroup riskGroup1 = new RiskGroup();
        riskGroup1.setId(1L);
        RiskGroup riskGroup2 = new RiskGroup();
        riskGroup2.setId(riskGroup1.getId());
        assertThat(riskGroup1).isEqualTo(riskGroup2);
        riskGroup2.setId(2L);
        assertThat(riskGroup1).isNotEqualTo(riskGroup2);
        riskGroup1.setId(null);
        assertThat(riskGroup1).isNotEqualTo(riskGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskGroupDTO.class);
        RiskGroupDTO riskGroupDTO1 = new RiskGroupDTO();
        riskGroupDTO1.setId(1L);
        RiskGroupDTO riskGroupDTO2 = new RiskGroupDTO();
        assertThat(riskGroupDTO1).isNotEqualTo(riskGroupDTO2);
        riskGroupDTO2.setId(riskGroupDTO1.getId());
        assertThat(riskGroupDTO1).isEqualTo(riskGroupDTO2);
        riskGroupDTO2.setId(2L);
        assertThat(riskGroupDTO1).isNotEqualTo(riskGroupDTO2);
        riskGroupDTO1.setId(null);
        assertThat(riskGroupDTO1).isNotEqualTo(riskGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(riskGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(riskGroupMapper.fromId(null)).isNull();
    }
}
