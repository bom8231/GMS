package com.sl.yu.GMS.Service;

import com.sl.yu.GMS.model.maintable;
import com.sl.yu.GMS.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    /**
     * 게시글 전체 조회
     */

    //게시글리스트처리
    public HashMap<String, Object> boardList(Pageable pageable) {

        HashMap<String, Object> listMap = new HashMap<>();
        Page<maintable> list = boardRepository.findAll(pageable);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());
        return listMap;
    }

    /**
    * 특정 상태에 대한 게시글 조회
    * */
    public HashMap<String, Object> findStatusAll(String stateID, Pageable page) {
        HashMap<String, Object> listMap = new HashMap<>();
        Page<maintable> list = boardRepository.findByStateID(stateID,page);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());
        return listMap;
    }
    /**
     * 특정 상태에 대한 게시글 조회 + 단어 검색
     * */
    public HashMap<String, Object> findStatusAllByKeyword(String stateID,String searchKeyword, String type, Pageable page) {
        HashMap<String, Object> listMap = new HashMap<>();
        Page<maintable> list = null;

        list = boardRepository.findByStateID(stateID,page);
        if(type.equals("visitor_name"))
            list = boardRepository.findByVISITORContainingAndStateID(searchKeyword, stateID, page);
        else if(type.equals("register_name"))
            list = boardRepository.findByuserNameContainingAndStateID(searchKeyword, stateID, page);
        else if(type.equals("register_company"))
            list = boardRepository.findByvisitAssignContainingAndStateID(searchKeyword, stateID, page);
        else if(type.equals("title"))
            list = boardRepository.findByTITLEContainingAndStateID(searchKeyword, stateID, page);
        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());
        return listMap;
    }

    /**
     * 특정 상태 및 날짜에 대한 게시글 조회
     * */
    public HashMap<String, Object> findStatusDateAll(String stateID, Pageable page) {
        HashMap<String, Object> listMap = new HashMap<>();

        // 현재 날짜 가져오기
        String currentDate = LocalDate.now().toString();

        Page<maintable> list = boardRepository.findByStateIDAndDeDate(stateID,  currentDate, page);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());
        return listMap;
    }

    /**
     * 특정 상태 및 날짜에 대한 게시글 조회 + 단어 검색
     * */
    public HashMap<String, Object> findStatusDateByKeyword(String stateID,String searchKeyword, String type,  Pageable page) {
        HashMap<String, Object> listMap = new HashMap<>();

        // 현재 날짜 가져오기
        String currentDate = LocalDate.now().toString();
        Page<maintable> list = null;

        if(type.equals("visitor_name"))
            list = boardRepository.findByVISITORContainingAndStateIDAndDeDate(searchKeyword, stateID,  currentDate, page);
        else if(type.equals("register_name"))
            list = boardRepository.findByuserNameContainingAndStateIDAndDeDate(searchKeyword, stateID,  currentDate, page);
        else if(type.equals("register_company"))
            list = boardRepository.findByvisitAssignContainingAndStateIDAndDeDate(searchKeyword, stateID,  currentDate, page);
        else if(type.equals("title"))
            list = boardRepository.findByTITLEContainingAndStateIDAndDeDate(searchKeyword, stateID,  currentDate, page);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());
        return listMap;
    }

    /**
     * 게시글 단건 조회
     */
    public Optional<maintable> findOne(Long id) {
        return boardRepository.findById(id);
    }

    /**
     * 게시글 검색
     */
    public HashMap<String, Object> boardSearchList(String searchKeyword, Pageable pageable, String type, String[] state) {
        HashMap<String, Object> listMap = new HashMap<>();

        Page<maintable> list = null;

        if(type.equals("visitor_name"))
            list = boardRepository.findByVISITORContaining(searchKeyword, pageable);
        else if(type.equals("register_name"))
            list = boardRepository.findByuserNameContaining(searchKeyword, pageable);
        else if(type.equals("register_company"))
            list = boardRepository.findByvisitAssignContaining(searchKeyword, pageable);
        else if(type.equals("title"))
            list = boardRepository.findByTITLEContaining(searchKeyword, pageable);
        else
            list = boardRepository.findAll(pageable);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());
        return listMap;
    }

    /**
     * 상태 업데이트
     */

}
