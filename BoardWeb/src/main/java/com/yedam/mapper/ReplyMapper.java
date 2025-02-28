package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.ReplyVO;
// @Param(파라메타 이름)
// 사용하는이유= 파라메타값의 이름을 다르게 지정할수있고 순서에 영향을 안받을수있다.
public interface ReplyMapper {
	public List<Map<String, Object>> chartData();
	public int replyCount(int boardNo);

	public List<ReplyVO> replyList(@Param("boardNo")int boardNo, int page);
	public List<ReplyVO> replyListAll(int boardNo);
	public int insertReply(ReplyVO reply);
	
}
