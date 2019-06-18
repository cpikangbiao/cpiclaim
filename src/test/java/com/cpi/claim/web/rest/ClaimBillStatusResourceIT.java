package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.ClaimBillStatus;
import com.cpi.claim.repository.ClaimBillStatusRepository;
import com.cpi.claim.service.ClaimBillStatusService;
import com.cpi.claim.service.dto.ClaimBillStatusDTO;
import com.cpi.claim.service.mapper.ClaimBillStatusMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.ClaimBillStatusCriteria;
import com.cpi.claim.service.ClaimBillStatusQueryService;

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
 * Integration tests for the {@Link ClaimBillStatusResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class ClaimBillStatusResourceIT {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_CLAIM_BILL_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_BILL_STATUS_NAME = "BBBBBBBBBB";

    @Autowired
    private ClaimBillStatusRepository claimBillStatusRepository;

    @Autowired
    private ClaimBillStatusMapper claimBillStatusMapper;

    @Autowired
    private ClaimBillStatusService claimBillStatusService;

    @Autowired
    private ClaimBillStatusQueryService claimBillStatusQueryService;

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

    private MockMvc restClaimBillStatusMockMvc;

    private ClaimBillStatus claimBillStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimBillStatusResource claimBillStatusResource = new ClaimBillStatusResource(claimBillStatusService, claimBillStatusQueryService);
        this.restClaimBillStatusMockMvc = MockMvcBuilders.standaloneSetup(claimBillStatusResource)
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
    public static ClaimBillStatus createEntity(EntityManager em) {
        ClaimBillStatus claimBillStatus = new ClaimBillStatus()
            .sortNum(DEFAULT_SORT_NUM)
            .claimBillStatusName(DEFAULT_CLAIM_BILL_STATUS_NAME);
        return claimBillStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimBillStatus createUpdatedEntity(EntityManager em) {
        ClaimBillStatus claimBillStatus = new ClaimBillStatus()
            .sortNum(UPDATED_SORT_NUM)
            .claimBillStatusName(UPDATED_CLAIM_BILL_STATUS_NAME);
        return claimBillStatus;
    }

    @BeforeEach
    public void initTest() {
        claimBillStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimBillStatus() throws Exception {
        int databaseSizeBeforeCreate = claimBillStatusRepository.findAll().size();

        // Create the ClaimBillStatus
        ClaimBillStatusDTO claimBillStatusDTO = claimBillStatusMapper.toDto(claimBillStatus);
        restClaimBillStatusMockMvc.perform(post("/api/claim-bill-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimBillStatus in the database
        List<ClaimBillStatus> claimBillStatusList = claimBillStatusRepository.findAll();
        assertThat(claimBillStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimBillStatus testClaimBillStatus = claimBillStatusList.get(claimBillStatusList.size() - 1);
        assertThat(testClaimBillStatus.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testClaimBillStatus.getClaimBillStatusName()).isEqualTo(DEFAULT_CLAIM_BILL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void createClaimBillStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimBillStatusRepository.findAll().size();

        // Create the ClaimBillStatus with an existing ID
        claimBillStatus.setId(1L);
        ClaimBillStatusDTO claimBillStatusDTO = claimBillStatusMapper.toDto(claimBillStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimBillStatusMockMvc.perform(post("/api/claim-bill-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimBillStatus in the database
        List<ClaimBillStatus> claimBillStatusList = claimBillStatusRepository.findAll();
        assertThat(claimBillStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClaimBillStatusNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = claimBillStatusRepository.findAll().size();
        // set the field null
        claimBillStatus.setClaimBillStatusName(null);

        // Create the ClaimBillStatus, which fails.
        ClaimBillStatusDTO claimBillStatusDTO = claimBillStatusMapper.toDto(claimBillStatus);

        restClaimBillStatusMockMvc.perform(post("/api/claim-bill-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ClaimBillStatus> claimBillStatusList = claimBillStatusRepository.findAll();
        assertThat(claimBillStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClaimBillStatuses() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get all the claimBillStatusList
        restClaimBillStatusMockMvc.perform(get("/api/claim-bill-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimBillStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].claimBillStatusName").value(hasItem(DEFAULT_CLAIM_BILL_STATUS_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getClaimBillStatus() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get the claimBillStatus
        restClaimBillStatusMockMvc.perform(get("/api/claim-bill-statuses/{id}", claimBillStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimBillStatus.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.claimBillStatusName").value(DEFAULT_CLAIM_BILL_STATUS_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllClaimBillStatusesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get all the claimBillStatusList where sortNum equals to DEFAULT_SORT_NUM
        defaultClaimBillStatusShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the claimBillStatusList where sortNum equals to UPDATED_SORT_NUM
        defaultClaimBillStatusShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllClaimBillStatusesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get all the claimBillStatusList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultClaimBillStatusShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the claimBillStatusList where sortNum equals to UPDATED_SORT_NUM
        defaultClaimBillStatusShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllClaimBillStatusesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get all the claimBillStatusList where sortNum is not null
        defaultClaimBillStatusShouldBeFound("sortNum.specified=true");

        // Get all the claimBillStatusList where sortNum is null
        defaultClaimBillStatusShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllClaimBillStatusesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get all the claimBillStatusList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultClaimBillStatusShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the claimBillStatusList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultClaimBillStatusShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllClaimBillStatusesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get all the claimBillStatusList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultClaimBillStatusShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the claimBillStatusList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultClaimBillStatusShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllClaimBillStatusesByClaimBillStatusNameIsEqualToSomething() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get all the claimBillStatusList where claimBillStatusName equals to DEFAULT_CLAIM_BILL_STATUS_NAME
        defaultClaimBillStatusShouldBeFound("claimBillStatusName.equals=" + DEFAULT_CLAIM_BILL_STATUS_NAME);

        // Get all the claimBillStatusList where claimBillStatusName equals to UPDATED_CLAIM_BILL_STATUS_NAME
        defaultClaimBillStatusShouldNotBeFound("claimBillStatusName.equals=" + UPDATED_CLAIM_BILL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllClaimBillStatusesByClaimBillStatusNameIsInShouldWork() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get all the claimBillStatusList where claimBillStatusName in DEFAULT_CLAIM_BILL_STATUS_NAME or UPDATED_CLAIM_BILL_STATUS_NAME
        defaultClaimBillStatusShouldBeFound("claimBillStatusName.in=" + DEFAULT_CLAIM_BILL_STATUS_NAME + "," + UPDATED_CLAIM_BILL_STATUS_NAME);

        // Get all the claimBillStatusList where claimBillStatusName equals to UPDATED_CLAIM_BILL_STATUS_NAME
        defaultClaimBillStatusShouldNotBeFound("claimBillStatusName.in=" + UPDATED_CLAIM_BILL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllClaimBillStatusesByClaimBillStatusNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        // Get all the claimBillStatusList where claimBillStatusName is not null
        defaultClaimBillStatusShouldBeFound("claimBillStatusName.specified=true");

        // Get all the claimBillStatusList where claimBillStatusName is null
        defaultClaimBillStatusShouldNotBeFound("claimBillStatusName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultClaimBillStatusShouldBeFound(String filter) throws Exception {
        restClaimBillStatusMockMvc.perform(get("/api/claim-bill-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimBillStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].claimBillStatusName").value(hasItem(DEFAULT_CLAIM_BILL_STATUS_NAME)));

        // Check, that the count call also returns 1
        restClaimBillStatusMockMvc.perform(get("/api/claim-bill-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultClaimBillStatusShouldNotBeFound(String filter) throws Exception {
        restClaimBillStatusMockMvc.perform(get("/api/claim-bill-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restClaimBillStatusMockMvc.perform(get("/api/claim-bill-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingClaimBillStatus() throws Exception {
        // Get the claimBillStatus
        restClaimBillStatusMockMvc.perform(get("/api/claim-bill-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimBillStatus() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        int databaseSizeBeforeUpdate = claimBillStatusRepository.findAll().size();

        // Update the claimBillStatus
        ClaimBillStatus updatedClaimBillStatus = claimBillStatusRepository.findById(claimBillStatus.getId()).get();
        // Disconnect from session so that the updates on updatedClaimBillStatus are not directly saved in db
        em.detach(updatedClaimBillStatus);
        updatedClaimBillStatus
            .sortNum(UPDATED_SORT_NUM)
            .claimBillStatusName(UPDATED_CLAIM_BILL_STATUS_NAME);
        ClaimBillStatusDTO claimBillStatusDTO = claimBillStatusMapper.toDto(updatedClaimBillStatus);

        restClaimBillStatusMockMvc.perform(put("/api/claim-bill-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimBillStatus in the database
        List<ClaimBillStatus> claimBillStatusList = claimBillStatusRepository.findAll();
        assertThat(claimBillStatusList).hasSize(databaseSizeBeforeUpdate);
        ClaimBillStatus testClaimBillStatus = claimBillStatusList.get(claimBillStatusList.size() - 1);
        assertThat(testClaimBillStatus.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testClaimBillStatus.getClaimBillStatusName()).isEqualTo(UPDATED_CLAIM_BILL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimBillStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimBillStatusRepository.findAll().size();

        // Create the ClaimBillStatus
        ClaimBillStatusDTO claimBillStatusDTO = claimBillStatusMapper.toDto(claimBillStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimBillStatusMockMvc.perform(put("/api/claim-bill-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimBillStatus in the database
        List<ClaimBillStatus> claimBillStatusList = claimBillStatusRepository.findAll();
        assertThat(claimBillStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimBillStatus() throws Exception {
        // Initialize the database
        claimBillStatusRepository.saveAndFlush(claimBillStatus);

        int databaseSizeBeforeDelete = claimBillStatusRepository.findAll().size();

        // Delete the claimBillStatus
        restClaimBillStatusMockMvc.perform(delete("/api/claim-bill-statuses/{id}", claimBillStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ClaimBillStatus> claimBillStatusList = claimBillStatusRepository.findAll();
        assertThat(claimBillStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimBillStatus.class);
        ClaimBillStatus claimBillStatus1 = new ClaimBillStatus();
        claimBillStatus1.setId(1L);
        ClaimBillStatus claimBillStatus2 = new ClaimBillStatus();
        claimBillStatus2.setId(claimBillStatus1.getId());
        assertThat(claimBillStatus1).isEqualTo(claimBillStatus2);
        claimBillStatus2.setId(2L);
        assertThat(claimBillStatus1).isNotEqualTo(claimBillStatus2);
        claimBillStatus1.setId(null);
        assertThat(claimBillStatus1).isNotEqualTo(claimBillStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimBillStatusDTO.class);
        ClaimBillStatusDTO claimBillStatusDTO1 = new ClaimBillStatusDTO();
        claimBillStatusDTO1.setId(1L);
        ClaimBillStatusDTO claimBillStatusDTO2 = new ClaimBillStatusDTO();
        assertThat(claimBillStatusDTO1).isNotEqualTo(claimBillStatusDTO2);
        claimBillStatusDTO2.setId(claimBillStatusDTO1.getId());
        assertThat(claimBillStatusDTO1).isEqualTo(claimBillStatusDTO2);
        claimBillStatusDTO2.setId(2L);
        assertThat(claimBillStatusDTO1).isNotEqualTo(claimBillStatusDTO2);
        claimBillStatusDTO1.setId(null);
        assertThat(claimBillStatusDTO1).isNotEqualTo(claimBillStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(claimBillStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(claimBillStatusMapper.fromId(null)).isNull();
    }
}
