package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.StudyRequest;
import com.urunner.khweb.entity.board.Study;
import com.urunner.khweb.entity.board.StudyMember;

import java.util.List;
import java.util.Optional;

public interface StudyMemberBoardService {
    StudyMember register(StudyMember studyMember);
}