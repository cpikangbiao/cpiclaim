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

import com.cpi.claim.service.CaseCloseLogService;
import com.cpi.claim.domain.CaseCloseLog;
import com.cpi.claim.repository.CaseCloseLogRepository;
import com.cpi.claim.service.dto.CaseCloseLogDTO;
import com.cpi.claim.service.mapper.CaseCloseLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CaseCloseLog.
 */
@Service
@Transactional
public class CaseCloseLogServiceImpl implements CaseCloseLogService {

    private final Logger log = LoggerFactory.getLogger(CaseCloseLogServiceImpl.class);

    private final CaseCloseLogRepository caseCloseLogRepository;

    private final CaseCloseLogMapper caseCloseLogMapper;

    public CaseCloseLogServiceImpl(CaseCloseLogRepository caseCloseLogRepository, CaseCloseLogMapper caseCloseLogMapper) {
        this.caseCloseLogRepository = caseCloseLogRepository;
        this.caseCloseLogMapper = caseCloseLogMapper;
    }

    /**
     * Save a caseCloseLog.
     *
     * @param caseCloseLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseCloseLogDTO save(CaseCloseLogDTO caseCloseLogDTO) {
        log.debug("Request to save CaseCloseLog : {}", caseCloseLogDTO);
        CaseCloseLog caseCloseLog = caseCloseLogMapper.toEntity(caseCloseLogDTO);
        caseCloseLog = caseCloseLogRepository.save(caseCloseLog);
        return caseCloseLogMapper.toDto(caseCloseLog);
    }

    /**
     * Get all the caseCloseLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseCloseLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseCloseLogs");
        return caseCloseLogRepository.findAll(pageable)
            .map(caseCloseLogMapper::toDto);
    }


    /**
     * Get one caseCloseLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseCloseLogDTO> findOne(Long id) {
        log.debug("Request to get CaseCloseLog : {}", id);
        return caseCloseLogRepository.findById(id)
            .map(caseCloseLogMapper::toDto);
    }

    /**
     * Delete the caseCloseLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseCloseLog : {}", id);
        caseCloseLogRepository.deleteById(id);
    }
}
