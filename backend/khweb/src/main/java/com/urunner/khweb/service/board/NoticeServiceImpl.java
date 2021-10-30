package com.urunner.khweb.service.board;

import com.urunner.khweb.entity.board.Notice;
import com.urunner.khweb.repository.board.NoticeRepository;
import com.urunner.khweb.repository.request.NoticeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public void register(NoticeRequest noticeRequest) throws Exception {

        Notice noticeEntity = new Notice(
                noticeRequest.getTitle(), noticeRequest.getWriter(), noticeRequest.getContent());

        noticeRepository.save(noticeEntity);
    }

    @Override
    public List<Notice> noticeList() throws Exception {
        return noticeRepository.findAll();
    }

    @Override
    public Optional<Notice> readNotice(Long noticeNo) throws Exception {
        return noticeRepository.findById(noticeNo);
    }

    @Override
    public void modifyNotice(Notice Notice) throws Exception {
        noticeRepository.save(Notice);
    }

    @Override
    public void deleteNotice(Long noticeNo) throws Exception {
        noticeRepository.deleteById(noticeNo);
    }
}


