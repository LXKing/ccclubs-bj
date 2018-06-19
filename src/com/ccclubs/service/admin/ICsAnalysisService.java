package com.ccclubs.service.admin;

import java.util.Map;

import com.ccclubs.entity.MemberConsuMe;
import com.ccclubs.entity.RemoteData;
import com.lazy3q.web.util.Page;

public interface ICsAnalysisService {

	public Page<RemoteData> getReMoteDataPage(int page,int size,Map params);

	public Page<MemberConsuMe> getMemberConsuMePage(int page, int size,
			Map params);
}
