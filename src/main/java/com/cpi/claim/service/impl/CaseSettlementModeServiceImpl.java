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

package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseSettlementModeService;
import com.cpi.claim.domain.CaseSettlementMode;
import com.cpi.claim.repository.CaseSettlementModeRepository;
import com.cpi.claim.service.dto.CaseSettlementModeDTO;
import com.cpi.claim.service.mapper.CaseSettlementModeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseSettlementMode}.
 */
@Service
@Transactional
public class CaseSettlementModeServiceImpl implements CaseSettlementModeService {

    private final Logger log = LoggerFactory.getLogger(CaseSettlementModeServiceImpl.class);

    private final CaseSettlementModeRepository caseSettlementModeRepository;

    private final CaseSettlementModeMapper caseSettlementModeMapper;

    public CaseSettlementModeServiceImpl(CaseSettlementModeRepository caseSettlementModeRepository, CaseSettlementModeMapper caseSettlementModeMapper) {
        this.caseSettlementModeRepository = caseSettlementModeRepository;
        this.caseSettlementModeMapper = caseSettlementModeMapper;
    }

    /**
     * Save a caseSettlementMode.
     *
     * @param caseSettlementModeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CaseSettlementModeDTO save(CaseSettlementModeDTO caseSettlementModeDTO) {
        log.debug("Request to save CaseSettlementMode : {}", caseSettlementModeDTO);
        CaseSettlementMode caseSettlementMode = caseSettlementModeMapper.toEntity(caseSettlementModeDTO);
        caseSettlementMode = caseSettlementModeRepository.save(caseSettlementMode);
        return caseSettlementModeMapper.toDto(caseSettlementMode);
    }

    /**
     * Get all the caseSettlementModes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseSettlementModeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseSettlementModes");
        return caseSettlementModeRepository.findAll(pageable)
            .map(caseSettlementModeMapper::toDto);
    }


    /**
     * Get one caseSettlementMode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseSettlementModeDTO> findOne(Long id) {
        log.debug("Request to get CaseSettlementMode : {}", id);
        return caseSettlementModeRepository.findById(id)
            .map(caseSettlementModeMapper::toDto);
    }

    /**
     * Delete the caseSettlementMode by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseSettlementMode : {}", id);
        caseSettlementModeRepository.deleteById(id);
    }
}
