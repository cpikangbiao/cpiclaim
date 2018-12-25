/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-20 下午1:55
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

package com.cpi.claim.service;

import com.cpi.claim.domain.CaseStatusType;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.service.dto.uaa.UserDTO;
import com.cpi.claim.service.mapper.VesselCaseMapper;
import com.cpi.claim.service.user.UserService;
import com.cpi.claim.service.utility.ClaimToolUtility;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class VesselCaseOperateService extends QueryService<VesselCase> {

    private final Logger log = LoggerFactory.getLogger(VesselCaseOperateService.class);

    private final VesselCaseRepository vesselCaseRepository;

    private final VesselCaseMapper vesselCaseMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ClaimToolUtility claimToolUtility;

    @Autowired
    private CaseCloseLogExtService caseCloseLogExtService;

    @Autowired
    private CaseAssignLogExtService caseAssignLogExtService;

    @Autowired
    private CaseRegisterLogExtService caseRegisterLogExtService;

    public VesselCaseOperateService(VesselCaseRepository vesselCaseRepository, VesselCaseMapper vesselCaseMapper) {
        this.vesselCaseRepository = vesselCaseRepository;
        this.vesselCaseMapper = vesselCaseMapper;
    }

    @Transactional
    public void changeCaseAssignedHandler(Long vesselCaseId, Long assignedHandlerId) {
        VesselCase vesselCase = vesselCaseRepository.getOne(vesselCaseId);
        assignCase(vesselCase, assignedHandlerId);

        UserDTO userDTO = claimToolUtility.userRepository.findUserByID(assignedHandlerId);
        caseAssignLogExtService.saveCaseAssignLog(vesselCase,userDTO.getLogin());
    }

    @Transactional
    public void changeCaseRegisteredHandler(Long vesselCaseId, Long registeredHandlerId) {
        VesselCase vesselCase = vesselCaseRepository.getOne(vesselCaseId);
        registeredCase(vesselCase, registeredHandlerId);

        UserDTO userDTO = claimToolUtility.userRepository.findUserByID(registeredHandlerId);
        caseRegisterLogExtService.saveCaseRegisterLog(vesselCase,userDTO.getLogin());
    }


    @Transactional
    public void closeCase(Long vesselCaseId) {
        VesselCase vesselCase = vesselCaseRepository.getOne(vesselCaseId);
        operateCaseStatus(vesselCase, CaseStatusType.CASE_STATUS_CLOSE);

        caseCloseLogExtService.saveCaseCloseLog(vesselCase,"Close");
    }

    @Transactional
    public void reopenCase(Long vesselCaseId) {
        VesselCase vesselCase = vesselCaseRepository.getOne(vesselCaseId);
        operateCaseStatus(vesselCase, CaseStatusType.CASE_STATUS_OPEN);

        caseCloseLogExtService.saveCaseCloseLog(vesselCase,"Close");
    }

    @Transactional
    protected void operateCaseStatus(VesselCase vesselCase, Long caseStatusTypeId) {
        if (vesselCase != null) {
            CaseStatusType caseStatusType =
                claimToolUtility.caseStatusTypeRepository.getOne(caseStatusTypeId);
            vesselCase.setCaseStatus(caseStatusType);
            vesselCase.setCloseDate(Instant.now());
//            Optional<UserDTO> optional = userService.getCurrentUser();
//            if (optional.isPresent()) {
//                vesselCase.setCloseHandler(optional.get().getId());
//            }

            vesselCaseRepository.save(vesselCase);
        }
    }

    @Transactional
    protected void assignCase(VesselCase vesselCase, Long assignedUserId) {
        vesselCase.setAssignedHandler(assignedUserId);
        vesselCase.setAssignedTime(Instant.now());

        vesselCaseRepository.saveAndFlush(vesselCase);
    }

    @Transactional
    protected void registeredCase(VesselCase vesselCase, Long registeredUserId) {
        vesselCase.setRegisteredHandler(registeredUserId);
        vesselCase.setRegisteredDate(Instant.now());

        vesselCaseRepository.saveAndFlush(vesselCase);
    }


}
